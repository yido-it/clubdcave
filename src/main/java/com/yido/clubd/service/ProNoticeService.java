package com.yido.clubd.service;

import java.util.List;
import java.util.Map;

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
	
	/**
	 * 레슨프로특이사항 조회
	 * 
	 * @param map
	 * @return
	 */
	public List<ProNotice> selectProNoticeList(Map<String, Object> map) {
		return proNoticeMapper.selectProNoticeList(map);
	}
	
	/**
	 * 레슨프로이미지 조회
	 * 
	 * @param map
	 * @return
	 */
	public List<ProNotice> selectProImageList(Map<String, Object> map) {
		return proNoticeMapper.selectProImageList(map);
	}
}
