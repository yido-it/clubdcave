package com.yido.clubd.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yido.clubd.model.ProNotice;
import com.yido.clubd.repository.ProNoticeMapper;
import lombok.extern.slf4j.Slf4j;

/**
 * 레슨프로특이사항
 * 
 * @author bae
 *
 */
@Slf4j
@Service
public class ProNoticeService {

	@Autowired
    private ProNoticeMapper proNoticeMapper;

	/**
	 * 레슨프로특이사항 등록
	 * 
	 * @param proNotice
	 * @return
	 */
	public int insertProNotice(ProNotice proNotice) {
		return proNoticeMapper.insertProNotice(proNotice);
	}
	
	/**
	 * 레슨프로특이사항 수정
	 * 
	 * @param proNotice
	 * @return
	 */
	public int updateProNotice(ProNotice proNotice) {
		return proNoticeMapper.updateProNotice(proNotice);
	}
}
