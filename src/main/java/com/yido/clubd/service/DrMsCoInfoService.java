package com.yido.clubd.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.yido.clubd.model.DrMsCoInfo;
import com.yido.clubd.repository.DrMsCoInfoMapper;
import lombok.extern.slf4j.Slf4j;

/**
 * 회원지점연결정보
 * 
 * @author bae
 *
 */
@Slf4j
@Service
public class DrMsCoInfoService {

	@Autowired
    private DrMsCoInfoMapper drMsCoInfoMapper;

    public DrMsCoInfo selectList(DrMsCoInfo drMsCoInfo) {
    	return drMsCoInfoMapper.selectList(drMsCoInfo);
    }
}
