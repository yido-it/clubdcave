package com.yido.clubd.component;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

import javax.imageio.ImageIO;

public class ImageUtil {
	// 썸네일 기본 너비 300
	private static final int IMG_WIDTH = 100;
	

	/**
	 * * 원본 이미지를 리사이즈 한다. * *
	 * 
	 * @param String imgsrc 원본 이미지 경로
	 * @param String imgdist 대상 경로 *
	 * @param String fileName 파일명 *
	 * @param String suffix 확장자 ( .jpg , .png ) *
	 * @throws IOException
	 */

	public static void reduceImg(String imgsrc, String imgdist, String suffix, String fileName) throws IOException {
		File srcfile = new File(imgsrc + fileName);
		File destFolder = new File(imgdist);
//		String convertUUID = UUID.randomUUID().toString().replace("-", "");
		
		if (!destFolder.exists()) {
			destFolder.mkdirs();
		}
		
		Image src = ImageIO.read(srcfile);
		
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

		FileOutputStream out = new FileOutputStream(imgdist + fileName);
		ImageIO.write(resizeImage, suffix.substring(1), out);
		
		out.close();
	}

}
