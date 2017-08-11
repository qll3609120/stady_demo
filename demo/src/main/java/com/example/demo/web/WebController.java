package com.example.demo.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by evan.qi on 2017/8/8.
 */
@Controller
@RequestMapping("/web")
public class WebController {
	@RequestMapping("/home")
	public String home(){
		return "home";
	}

	@RequestMapping("/login")
	public String login(){
		return "login";
	}

	@RequestMapping("/hello")
	public String hello(){
		return "hello";
	}
}
