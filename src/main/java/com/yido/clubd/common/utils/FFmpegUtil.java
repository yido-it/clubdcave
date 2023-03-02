package com.yido.clubd.common.utils;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import org.bytedeco.javacpp.Loader;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import lombok.extern.slf4j.Slf4j;
import net.bramp.ffmpeg.FFprobe;
import net.bramp.ffmpeg.probe.FFmpegFormat;
import net.bramp.ffmpeg.probe.FFmpegProbeResult;
/**
 * FfmpegUtil
 * 
 * @author YOO
 *
 */
@Slf4j
public class FFmpegUtil {
	
	// org.bytedeco.ffmpeg 라이브러리에 있는 ffprobe 프로그램을 불러온다
	static String ffmpegPath = Loader.load(org.bytedeco.ffmpeg.ffmpeg.class);
    static String ffprobePath = Loader.load(org.bytedeco.ffmpeg.ffprobe.class);
		
	public static double getVideoDuration(MultipartHttpServletRequest mreq) throws IOException {
		double duration = 0;
		Iterator<String> iter = mreq.getFileNames();	
		while(iter.hasNext()) {
			MultipartFile mFile = mreq.getFile(iter.next());
			
			String orgFileNm = mFile.getOriginalFilename();
			File sameFile = new File(orgFileNm);
			String filePath = sameFile.getAbsolutePath();
			File tmpFile = new File(filePath);
			mFile.transferTo(tmpFile);
			
			FFprobe ffprobe = new FFprobe(ffprobePath);
			
			try {
				FFmpegProbeResult probeResult = ffprobe.probe(filePath); // 동영상 경로
				FFmpegFormat format = probeResult.getFormat();
				duration = format.duration; // 초단위				

			} catch (IOException e) {
				e.printStackTrace();
			}			
			if(tmpFile.exists()) tmpFile.delete();
		}			
		return duration;		
	}

}
