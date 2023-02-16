package com.yido.clubd.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yido.clubd.model.BBS;
import com.yido.clubd.model.ClDayInfo;
import com.yido.clubd.repository.BBSMapper;
import com.yido.clubd.repository.ClDayInfoMapper;
import lombok.extern.slf4j.Slf4j;

/**
 * 게시판
 * 
 * @author bae
 *
 */
@Slf4j
@Service
public class BBSService {

	@Autowired
    private BBSMapper bbsMapper;

	/**
	 * 게시판목록 
	 * 
	 * @param map
	 * @return
	 */
    public List<BBS> selectList(Map<String, Object> map) {
    	return bbsMapper.selectList(map);
    }
    
    /**
     * 게시판 상세
     * 
     * @param map
     * @return
     */
	public BBS getNotice(Map<String, Object> map) {
    	return bbsMapper.getNotice(map);
	}

}
