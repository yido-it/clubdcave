package com.yido.clubd.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.yido.clubd.model.CoPlace;
import com.yido.clubd.repository.CoPlaceMapper;
import lombok.extern.slf4j.Slf4j;

/**
 * 지점코드
 * 
 * @author bae
 *
 */
@Slf4j
@Service
public class CoPlaceService {

	@Autowired
    private CoPlaceMapper coPlaceMapper;

    public List<CoPlace> selectList(Map<String, Object> map) {
    	return coPlaceMapper.selectList(map);
    }

	public List<CoPlace> selectPlaceList() {
		return coPlaceMapper.selectPlaceList();
	}
}
