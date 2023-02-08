package com.yido.clubd.component;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.yido.clubd.component.ImageUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class FileUtil {

	@Value("${file.root}")
    static String rootDir = "";

    @Value("${file.root}")
    private void setValue(String fileRoot){
    	rootDir = fileRoot + "/";
    }

    /**
	 * 파일 업로드
	 */
    public static void uploadObject(String projectNm, String folderName, String objectName, String filePath) throws IOException {

    	String fullPath = null;
		fullPath = folderName + "/" + objectName;

		makeDir(folderName);
		log.debug(folderName);
		log.debug(filePath);
		log.debug(fullPath);

		writeFile(filePath, fullPath);

		String[] tempName = objectName.split("\\.");
		String ext = "."+tempName[1];
		log.debug(ext);

		if (ext.equals(".png") || ext.equals(".jpg") || ext.equals(".jpeg")) {
			ImageUtil.reduceImg(rootDir + folderName + "/", rootDir + folderName + "/thumb/", ext, objectName);
		}

    }

    /**
	 * 파일 생성
	 */
    private static boolean writeFile(String filePath, String saveFileName) throws IOException{
		boolean result = false;
		//byte[] data = multipartFile.getBytes();
		byte[] data = Files.readAllBytes(new File(filePath).toPath());

		FileOutputStream fos = new FileOutputStream(rootDir + saveFileName);

		fos.write(data);
		fos.close();

		return result;
	}

    /**
     * 파일 삭제
     */
    public static void deleteObject(String folderName, String objectName) {
    	File file = new File("/usr/local" + folderName + "/" + objectName);
    	if(file.exists()) file.delete();
    }

    /**
     * 폴더 생성
     */
	public static void makeDir(String... paths) {

		if(new File(rootDir + paths[paths.length - 1]).exists()){
			return;
		}

		for(String path : paths){
			File dirPath = new File(rootDir + path);

			if(!dirPath.exists()){
				dirPath.mkdirs();
			}
		}
	}

}
