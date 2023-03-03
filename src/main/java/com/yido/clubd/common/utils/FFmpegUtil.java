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
		
	public static double getVideoDuration(String filePath) throws IOException {
		double duration = 0;			
		FFprobe ffprobe = new FFprobe(ffprobePath);
		
		try {
			FFmpegProbeResult probeResult = ffprobe.probe(filePath); // 동영상 경로
			FFmpegFormat format = probeResult.getFormat();
			duration = format.duration; // 초단위				

		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return duration;		
	}

}
