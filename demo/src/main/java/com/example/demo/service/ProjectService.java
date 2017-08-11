package com.example.demo.service;

import com.example.demo.entity.ProjectDO;
import org.springframework.stereotype.Component;

/**
 * Created by evan.qi on 2017/6/16.
 */
public interface ProjectService {

	ProjectDO getProjectById(Long id);

	String getTestName(String id);

	String updatePro(String id);
}
