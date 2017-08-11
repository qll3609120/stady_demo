package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.internet.MimeMessage;
import javax.ws.rs.PathParam;
import java.io.File;

/**
 * Created by evan.qi on 2017/7/19.
 */
@RestController
@RequestMapping("/demo")
public class DemoController {

	@Autowired
	private RedisTemplate redisTemplate;

	@Autowired
	private JavaMailSender javaMailSender;

	@RequestMapping("/data")
	public  String demo(){
		return "aa";
	}

	@RequestMapping("/redis")
	public void setRedisData(@RequestParam("data") String data){
		Girl girl = new Girl();
		girl.setAge(20);
		girl.setName("小人物");
		System.out.println(girl.getAge()+"  "+girl.getName());
		redisTemplate.opsForValue().set("redis_cn",girl);
		System.out.println(girl.getAge()+"  "+girl.getName());
		girl = (Girl)redisTemplate.opsForValue().get("redis_cn");
		System.out.println(girl.getAge()+"  "+girl.getName());
	}

	@RequestMapping("/exp")
	public String exception() throws Exception {
		throw new Exception("exception");
	}
	@RequestMapping("/mail")
	public String sendMail(){

		try{
			//简单邮件
			/*SimpleMailMessage msg = new SimpleMailMessage();
			msg.setFrom("1272735532@qq.com");
			msg.setTo("826031686@qq.com");
			msg.setSubject("Test");
			msg.setText("this is test mail");
			javaMailSender.send(msg);*/

			//附件的邮件
			MimeMessage message = javaMailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			helper.setFrom("1272735532@qq.com");
			helper.setTo("826031686@qq.com");
			helper.setSubject("Test");
			helper.setText("this is test mail");
			FileSystemResource file = new FileSystemResource(new File("D:\\upload\\app-debug.apk"));

			helper.addAttachment("file",file);

			javaMailSender.send(message);

		}catch(Exception e){
			e.printStackTrace();
		}
		return "success";
	}
}
