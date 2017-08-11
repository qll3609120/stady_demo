package com.example.demo.service.impl;

import com.example.demo.dao.ProjectDao;
import com.example.demo.entity.ProjectDO;
import com.example.demo.service.ProjectService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.logging.Logger;

/**
 * Created by evan.qi on 2017/6/16.
 */
@Service
public class ProjectServiceImpl implements ProjectService {
	@Autowired
	private ProjectDao projectDao;

	private static Log log = LogFactory.getLog(ProjectServiceImpl.class);

	@Override
	@Cacheable(value="projectDO")
	public ProjectDO getProjectById(Long id) {
		log.info("id is "+id);
		return projectDao.getPrjById(id);
	}

	@Override
	@Cacheable(value="testkey")
	public String getTestName(String id) {
		log.info("id is "+id);
		return "redis1";
	}

	@Override
	@Cacheable(value = "testkey")
	public String updatePro(String id) {

		return "redis";
	}

}
