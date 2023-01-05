package com.yido.clubd.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.yido.clubd.model.DrMsBasic;
import com.yido.clubd.repository.DrMsBasicMapper;
import lombok.extern.slf4j.Slf4j;

/**
 * 회원추가정보
 * 
 * @author bae
 *
 */
@Slf4j
@Service
public class DrMsBasicService {

	@Autowired
    private DrMsBasicMapper drMsBasicMapper;
    
    /**
     * 회원추가정보 등록 
     * 
     * @param drMsBasic
     * @return
     */
	public int insertDrMsBasic(DrMsBasic drMsBasic) {
		return drMsBasicMapper.insertDrMsBasic(drMsBasic);
	}
	
	/**
	 * 회원추가정보 수정
	 * 
	 * @param drMsBasic
	 * @return
	 */
	public int updateDrMsBasic(DrMsBasic drMsBasic) {
		return drMsBasicMapper.updateDrMsBasic(drMsBasic);
	}
}
