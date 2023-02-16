package com.yido.clubd.common.utils;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import com.amazonaws.SdkClientException;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.AccessControlList;
import com.amazonaws.services.s3.model.AmazonS3Exception;
import com.amazonaws.services.s3.model.CompleteMultipartUploadRequest;
import com.amazonaws.services.s3.model.CompleteMultipartUploadResult;
import com.amazonaws.services.s3.model.GroupGrantee;
import com.amazonaws.services.s3.model.InitiateMultipartUploadRequest;
import com.amazonaws.services.s3.model.InitiateMultipartUploadResult;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PartETag;
import com.amazonaws.services.s3.model.Permission;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.services.s3.model.UploadPartRequest;
import com.amazonaws.services.s3.model.UploadPartResult;
/**
 * Naver Cloud Platform 업로드
 * 
 * @author YOO
 *
 */
public class AWSFileUtil {
	
	final static String endPoint = "https://kr.object.ncloudstorage.com";
	final static String regionName = "kr-standard";
	
//	@Value("${awsS3.accessKey}")
//	private  static String accessKey;
//	@Value("${awsS3.secretKey}")
//	private  static String secretKey;
//	@Value("${awsS3.bucketName}")
//	private  static String bucketName;
	
	static String accessKey = Globals.accessKey;
	static String secretKey = Globals.secretKey;
	static String bucketName = Globals.bucketName;
	
	// 썸네일 기본 너비 300
	private static final int IMG_WIDTH = 120;
	
	// S3 client
	final static AmazonS3 s3 = AmazonS3ClientBuilder.standard()
	    .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(endPoint, regionName))
	    .withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(accessKey, secretKey)))
	    .build();
	
	// 파일 업로드
	public static void uploadFile(String folderName, String objectName, String extName, String filePath) throws IOException {

		ObjectMetadata objectMetadata = new ObjectMetadata();
		objectMetadata.setContentLength(0L);
		objectMetadata.setContentType("application/x-directory");
		PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, folderName, new ByteArrayInputStream(new byte[0]), objectMetadata);
		
		// create folder
		try {
		    s3.putObject(putObjectRequest);
		    System.out.format("Folder %s has been created.\n", folderName);
		} catch (AmazonS3Exception e) {
		    e.printStackTrace();
		} catch(SdkClientException e) {
		    e.printStackTrace();
		}
		
		// upload local file
		try {
		    s3.putObject(bucketName, objectName, new File(filePath));
		    System.out.format("Object %s has been created.\n", objectName);
		    setObjectACL(objectName);
		    
		 // 썸네일 생성
 			if (extName.equals("png") || extName.equals("jpg") || extName.equals("jpeg")) {
 				uploadThumb(folderName, objectName, extName, filePath);
 			}
 			
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
	
	// 파일 삭제
	public static void deleteFile(String objectName) {
		try {
		    s3.deleteObject(bucketName, objectName);
		    System.out.format("Object %s has been deleted.\n", objectName);
		} catch (AmazonS3Exception e) {
		    e.printStackTrace();
		} catch(SdkClientException e) {
		    e.printStackTrace();
		}
	}
	
	// 멀티파트 파일 업로드
	public static void mulipartUpload(String objectName, File file) {
		long contentLength = file.length();
		long partSize = 10 * 1024 * 1024;

		try {
		    // initialize and get upload ID
		    InitiateMultipartUploadResult initiateMultipartUploadResult = s3.initiateMultipartUpload(new InitiateMultipartUploadRequest(bucketName, objectName));
		    String uploadId = initiateMultipartUploadResult.getUploadId();

		    // upload parts
		    List<PartETag> partETagList = new ArrayList<PartETag>();

		    long fileOffset = 0;
		    for (int i = 1; fileOffset < contentLength; i++) {
		        partSize = Math.min(partSize, (contentLength - fileOffset));

		        UploadPartRequest uploadPartRequest = new UploadPartRequest()
		            .withBucketName(bucketName)
		            .withKey(objectName)
		            .withUploadId(uploadId)
		            .withPartNumber(i)
		            .withFile(file)
		            .withFileOffset(fileOffset)
		            .withPartSize(partSize);

		        UploadPartResult uploadPartResult = s3.uploadPart(uploadPartRequest);
		        partETagList.add(uploadPartResult.getPartETag());

		        fileOffset += partSize;
		    }

		    // abort
		    // s3.abortMultipartUpload(new AbortMultipartUploadRequest(bucketName, objectName, uploadId));

		    // complete
		    CompleteMultipartUploadResult completeMultipartUploadResult = s3.completeMultipartUpload(new CompleteMultipartUploadRequest(bucketName, objectName, uploadId, partETagList));
		} catch (AmazonS3Exception e) {
		    e.printStackTrace();
		} catch(SdkClientException e) {
		    e.printStackTrace();
		}
	}

	public static void uploadThumb(String folderName, String objectName, String extName, String filePath) throws IOException {
		
		ObjectMetadata objectMetadata = new ObjectMetadata();
		objectMetadata.setContentLength(0L);
		objectMetadata.setContentType("application/x-directory");
		PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, folderName, new ByteArrayInputStream(new byte[0]), objectMetadata);
			
		// 기존 이미지 로컬 경로
		File srcFile = new File(filePath);
		
		// 생성할 임시 썸네일 로컬 경로
		StringBuffer str = new StringBuffer(filePath);
		str.insert(filePath.lastIndexOf("."), "_thumb");
		String destFilePath = str.toString();
		System.out.println(destFilePath);
		
		// 업로드할 파일 경로
		StringBuffer str2 = new StringBuffer(objectName);
		str2.insert(objectName.lastIndexOf("/") + 1, "thumb/");
		String thumbObjectName = str2.toString();
		System.out.println(thumbObjectName);
		
	
		Image src = ImageIO.read(srcFile);
		
		float ratio = src.getWidth(null) / IMG_WIDTH;
		//System.out.println(src.getWidth(null));
		//System.out.println(src.getHeight(null));
		//System.out.println(ratio);
		
		int newHeight = (int)(src.getHeight(null) / ratio);
		
		//System.out.println(newHeight);
		
		BufferedImage resizeImage = new BufferedImage(IMG_WIDTH, newHeight, BufferedImage.TYPE_INT_RGB);
		resizeImage.getGraphics().drawImage(src.getScaledInstance(IMG_WIDTH, newHeight, Image.SCALE_SMOOTH), 0, 0, null);
	
		// resizeImage.getGraphics().drawImage(src.getScaledInstance(widthdist,
		// heightdist, // Image.SCALE_AREA_AVERAGING), 0, 0, null);
	
		FileOutputStream out = new FileOutputStream(destFilePath);
		ImageIO.write(resizeImage, extName, out);
		
		try {
			s3.putObject(bucketName, thumbObjectName, new File(destFilePath));
			setObjectACL(thumbObjectName);
		} catch (AmazonS3Exception e) {
		    e.printStackTrace();
		} catch(SdkClientException e) {
		    e.printStackTrace();
		}
			
		out.close();
		File destFile = new File(destFilePath);
		if(destFile.exists()) destFile.delete();
		
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
