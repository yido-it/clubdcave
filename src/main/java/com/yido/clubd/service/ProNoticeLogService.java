package com.yido.clubd.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yido.clubd.model.ProNoticeLog;
import com.yido.clubd.repository.ProNoticeLogMapper;
import lombok.extern.slf4j.Slf4j;

/**
 * 레슨프로특이사항 로그
 * 
 * @author bae
 *
 */
@Slf4j
@Service
public class ProNoticeLogService {

	@Autowired
    private ProNoticeLogMapper proNoticeLogMapper;

	/**
	 * 로그 등록
	 * 
	 * @param proNotice
	 * @return
	 */
	public int insertProNoticeLog(ProNoticeLog proNoticeLog) {
		return proNoticeLogMapper.insertProNoticeLog(proNoticeLog);
	}
}
