package com.yido.clubd.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.yido.clubd.model.DrVoucherCodeLog;
import com.yido.clubd.repository.DrVoucherCodeLogMapper;
import lombok.extern.slf4j.Slf4j;

/**
 * 이용권코드 로그
 * 
 * @author bae
 *
 */
@Slf4j
@Service
public class DrVoucherCodeLogService {

	@Autowired
    private DrVoucherCodeLogMapper drVoucherCodeLogMapper;

    public DrVoucherCodeLog selectList(DrVoucherCodeLog drVoucherCodeLog) {
    	return drVoucherCodeLogMapper.selectList(drVoucherCodeLog);
    }
}
