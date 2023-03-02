package com.yido.clubd.common.utils;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.imageio.ImageIO;

import com.amazonaws.SdkClientException;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.AccessControlList;
import com.amazonaws.services.s3.model.AmazonS3Exception;
import com.amazonaws.services.s3.model.GroupGrantee;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.Permission;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;

import lombok.extern.slf4j.Slf4j;

/**
 * Naver Cloud Platform 업로드
 * 
 * @author YOO
 *
 */
@Slf4j
public class AWSFileUtil {
	
	final static String endPoint = "https://kr.object.ncloudstorage.com";
	final static String regionName = "kr-standard";

	static String accessKey = Globals.accessKey;
	static String secretKey = Globals.secretKey;
	static String bucketName = Globals.bucketName;
	
	// 썸네일 기본 크기
	//private static final int IMG_HEIGHT = 90;
	private static final int IMG_WIDTH = 143;
	// 썸네일 기본 형식
	private static final String IMAGE_FORMAT = "jpg";
	// 동영상 썸네일 프레임
	private static final int FRAME_NUMBER = 200;
	
	// S3 client
	final static AmazonS3 s3 = AmazonS3ClientBuilder.standard()
	    .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(endPoint, regionName))
	    .withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(accessKey, secretKey)))
	    .build();
	
	/**
	 * 파일 업로드
	 * 
	 * @param folderName 업로드 경로
	 * @param objectName 업로드 경로 + 파일명
	 * @param extName 확장자명
	 * @param filePath 기존 파일경로
	 * @return
	 * @throws IOException 
	 */
	public static void uploadFile(String folderName, String fileName, String extNm, File file, String contentType) throws IOException {
	
		String objectName = folderName + fileName;
		
		System.out.format("Upload Content Type : %s\n", contentType);
		
		// 모바일 이미지 뒤집어져서 변환해버림...
		if (contentType != null && contentType.contains("image/")) {			
			BufferedImage image = ImageIO.read(file);
			ImageIO.write(image, extNm, file);
		} 
		
		// 비디오 업로드 전용 버킷
		if (contentType != null && contentType.contains("video/")) {
			bucketName = Globals.videoBucketName;
		}
		
		System.out.format("bucketName : %s\n", bucketName);

		// 1) create folder
		//createFolder(folderName);		
		
		// 2) upload local file			
		/*
		String contentType = multipartFile.getContentType();
		ObjectMetadata objectMetadata = new ObjectMetadata();
		objectMetadata.setContentLength(multipartFile.getSize());		
		objectMetadata.setContentType(contentType);		
		
		PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, objectName, multipartFile.getInputStream(), objectMetadata);

    	// MultipartFile -> File
		File file = new File(multipartFile.getOriginalFilename());
	    FileOutputStream fos = new FileOutputStream(file);
	    fos.write(multipartFile.getBytes());
	    fos.close();	
	    */			
				
		try {
			 s3.putObject(bucketName, objectName, file);
			 System.out.format("Object %s has been created.\n", objectName);
			 setObjectACL(objectName);
			 
			 if(contentType != null && contentType.contains("image/")) { // 썸네일 생성
				 getImgThumb(folderName, fileName, file);
			 }
			 bucketName = Globals.bucketName;
		} catch (AmazonS3Exception e) {
		    e.printStackTrace();
		} catch(SdkClientException e) {
		    e.printStackTrace();
		}
	}
	
	/**
	 * 폴더 생성
	 * 
	 * @param folderName 업로드 경로
	 */
	
	private static void createFolder(String folderName) {
		ObjectMetadata objectMetadata = new ObjectMetadata();
		objectMetadata.setContentLength(0L);
		objectMetadata.setContentType("application/x-directory");
		PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, folderName, new ByteArrayInputStream(new byte[0]), objectMetadata);
		
		try {			
		    s3.putObject(putObjectRequest);
		    System.out.format("Folder %s has been created.\n", folderName);
		} catch (AmazonS3Exception e) {
		    e.printStackTrace();
		} catch(SdkClientException e) {
		    e.printStackTrace();
		}
	}

	// 파일 다운로드	
	public static void downloadFile(String objectName, String downloadFilePath) throws IOException {
		try {
		    S3Object s3Object = s3.getObject(bucketName, objectName);
		    S3ObjectInputStream s3ObjectInputStream = s3Object.getObjectContent();

		    OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(downloadFilePath));
		    byte[] bytesArray = new byte[4096];
		    int bytesRead = -1;
		    while ((bytesRead = s3ObjectInputStream.read(bytesArray)) != -1) {
		        outputStream.write(bytesArray, 0, bytesRead);
		    }

		    outputStream.close();
		    s3ObjectInputStream.close();
		    System.out.format("Object %s has been downloaded.\n", objectName);
		} catch (AmazonS3Exception e) {
		    e.printStackTrace();
		} catch(SdkClientException e) {
		    e.printStackTrace();
		}
	}
	
	/**
	 * 이미지파일 삭제
	 * 
	 * @param objectName 업로드 경로 + 파일명
	 * @return
	 */
	public static void deleteFile(String objectName) {

		try {
		    s3.deleteObject(bucketName, objectName);
		    System.out.format("Object %s in %s has been deleted.\n", objectName, bucketName);
		} catch (AmazonS3Exception e) {
		    e.printStackTrace();
		} catch(SdkClientException e) {
		    e.printStackTrace();
		}
	}
		
	/**
	 * 썸네일 파일 삭제
	 * 
	 * @param objectName 업로드 경로 + 파일명
	 * @return
	 */
	public static void deleteThumbnail(String objectName) {
		
		StringBuffer str = new StringBuffer(objectName);
		str.insert(objectName.lastIndexOf("/"), "/thumb");		
		String newObjectName = str.toString().substring(0, str.lastIndexOf(".") + 1).concat(IMAGE_FORMAT);
		try {
		    s3.deleteObject(bucketName, newObjectName);
		    System.out.format("Object(thumbnail) %s has been deleted.\n", newObjectName);
		} catch (AmazonS3Exception e) {
		    e.printStackTrace();
		} catch(SdkClientException e) {
		    e.printStackTrace();
		}
	}
	
	/**
	 * 비디오 썸네일 파일 삭제
	 * 
	 * @param objectName 업로드 경로 + 파일명
	 * @return
	 */
	public static void deleteVideoThumbnail(String objectName) {
	
		objectName = objectName.replace("_default.mp4", ("_01.jpg"));
		try {
		    s3.deleteObject(bucketName, objectName);
		    System.out.format("Object(thumbnail) %s has been deleted.\n", objectName);
		} catch (AmazonS3Exception e) {
		    e.printStackTrace();
		} catch(SdkClientException e) {
		    e.printStackTrace();
		}
	}

	/**
	 * 이미지 썸네일 생성 (로컬)
	 * 
	 * @param objectName 업로드 경로
	 * @param fileName 파일명
	 * @param filePath 기존 파일 경로
	 * @return
	 */
	public static void getImgThumb(String folderName, String fileName, File file) throws IOException {
		
		/*
		// 기존 이미지 로컬 파일
		File srcFile = new File(filePath);
		
		// 생성할 임시 썸네일 로컬 경로
		StringBuffer str = new StringBuffer(fileName);
		str.insert(fileName.lastIndexOf("."), "_thumb");
		String thumbFilePath = folderName + str.toString();
		*/	
		
		// 업로드할 경로
		String thumbObjectName = folderName + "thumb/" + fileName.substring(0, fileName.lastIndexOf(".") + 1).concat(IMAGE_FORMAT);
					
		Image src = ImageIO.read(file);
		
		float ratio = Math.round((float)src.getWidth(null) / (float)IMG_WIDTH);
		int newHeight = (int)(src.getHeight(null) / ratio);
		
		/* 새 세로 길이가 IMG_HEIGHT보다 작으면 IMG_HEIGHT로 늘리기
		if(newHeight < IMG_HEIGHT ) {
			newHeight = IMG_HEIGHT;
		}*/		
		// 가로 길이에 맞춰 resize
		src = src.getScaledInstance(IMG_WIDTH, newHeight, Image.SCALE_SMOOTH);		
		BufferedImage resizeImage = new BufferedImage(IMG_WIDTH, newHeight, BufferedImage.TYPE_INT_RGB);
		resizeImage.getGraphics().drawImage(src, 0, 0, null);
		
		/* 이미지 세로 길이만큼 crop
		if(resizeImage.getHeight() > IMG_HEIGHT) {			
			int yPos = (resizeImage.getHeight() / 2) - (IMG_HEIGHT / 2);
			resizeImage = resizeImage.getSubimage(0, yPos, IMG_WIDTH, IMG_HEIGHT); 		
		}*/
		
		// BufferedImage -> Input Stream
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ImageIO.write(resizeImage, IMAGE_FORMAT, baos);
		InputStream is = new ByteArrayInputStream(baos.toByteArray());
		
		// 썸네일 업로드		
		ObjectMetadata objectMetadata = new ObjectMetadata();
		objectMetadata.setContentLength(baos.size());		
		objectMetadata.setContentType("image/" + IMAGE_FORMAT);		
		
		PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, thumbObjectName, is, objectMetadata);
		
		try {
			s3.putObject(putObjectRequest);
			setObjectACL(thumbObjectName);
		} catch (AmazonS3Exception e) {
		    e.printStackTrace();
		} catch(SdkClientException e) {
		    e.printStackTrace();
		}
		
		file.delete();
	
		/*
		FileOutputStream out = new FileOutputStream(thumbFilePath);
		ImageIO.write(resizeImage, IMAGE_FORMAT, out);
		out.close();		
		
		File thumbFile = new File(thumbFilePath);		
		uploadThumb(uploadFilePath, thumbFile);
		*/
		
	}
	
	// ACL 줘야 공개처리됨....
	public static void setObjectACL (String objectName) {
		try {
		    // get the current ACL
		    AccessControlList accessControlList = s3.getObjectAcl(bucketName, objectName);

		    // add read permission to user by ID
		    accessControlList.grantPermission(GroupGrantee.AllUsers, Permission.Read);

		    s3.setObjectAcl(bucketName, objectName, accessControlList);
		    System.out.format("Object %s has been granted permission.\n", objectName);
		} catch (AmazonS3Exception e) {
		    e.printStackTrace();
		} catch(SdkClientException e) {
		    e.printStackTrace();
		}
		
	}
}
