package com.example.demo.dao;

import com.example.demo.entity.ProjectDO;
import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by evan.qi on 6/8/2017.
 */
@Component
public interface ProjectDao {

	List<ProjectDO> list();


	ProjectDO getPrjById(@Param("id") Long id);
}
