package com.example.demo.exception;

import io.swagger.models.Model;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Created by evan.qi on 2017/7/19.
 */
@Configuration
@ControllerAdvice
public class ExceptionHandle {

	@ExceptionHandler(value=Exception.class)
	@ResponseBody
	@Produces(MediaType.APPLICATION_JSON)
	public ModelAndView handler(HttpServletRequest req, Exception e) throws Exception{
		ExceptionInfo er = new ExceptionInfo();
		er.setMessage(e.getMessage());
		er.setCode(100);
		ModelAndView mav = new ModelAndView();
		mav.addObject("url",req.getRequestURL());
		mav.addObject("exception",e);
		mav.setViewName("error");
		return mav;
	}
}
