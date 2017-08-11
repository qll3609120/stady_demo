package com.example.demo.resource;

import com.example.demo.dao.ProjectDao;
import com.example.demo.entity.ProjectDO;
import com.example.demo.Girl;
import com.example.demo.service.ProjectService;
import com.fasterxml.jackson.databind.annotation.JsonAppend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created by evan.qi on 6/8/2017.
 */
@Path("/reset")
public class JerseyController {
    @Autowired
    private Girl girl;
    @Autowired
    private ProjectDao projectDao;

    @Autowired
	private ProjectService projectService;

    @Autowired
	private RedisTemplate redisTemplate;

    @GET
    @Path("/user/{id}/search")
    @Produces(MediaType.APPLICATION_JSON)
    public Girl getEntity(@PathParam("id") Long id){
        return girl;
    }

    @GET
    @Path("/prj/list")
    @Produces(MediaType.APPLICATION_JSON+ ";charset=" + "UTF-8")
    public List<ProjectDO> getPrjList(){
        List<ProjectDO> projectDOList = projectDao.list();
        return projectDOList;
    }

    @GET
	@Path("/pri/{id}/detail")
	@Produces(MediaType.APPLICATION_JSON+";charset=utf-8")
	public ProjectDO getProDetail(@PathParam("id")Long id){

    	return projectService.getProjectById(id);
	}

	@GET
	@Path("/pri/{id}/test")
	@Produces(MediaType.APPLICATION_JSON+";charset=utf-8")
	public String getTest(@PathParam("id")Long id){

		return projectService.getTestName(id.toString());
	}

	@GET
	@Path("/redis")
	public boolean redisVal(@RequestParam("name") String name){
		ValueOperations valueOperations =  redisTemplate.opsForValue();
		valueOperations.set("name",name);
		System.out.println(valueOperations.get("name"));
		return true;
	}

	@GET
	@Path("/pri/{id}/update")
	@Produces(MediaType.APPLICATION_JSON+";charset=utf-8")
	public String getUpate(@PathParam("id")Long id){

		return projectService.updatePro(id.toString());
	}

	@Cacheable(value="name")
	@GET
	public String getName(String name){
		return name;
	}

}
