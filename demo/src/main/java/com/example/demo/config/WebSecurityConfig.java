package com.example.demo.config;


/**
 * Created by evan.qi on 2017/8/8.
 *  extends WebSecurityConfigurerAdapter
 */
public class WebSecurityConfig {

	/*@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
				.authorizeRequests()
				.antMatchers("/", "/home","/login","/eureka*//*").permitAll() //不需要权限验证
				.anyRequest().authenticated()//所有请求都需要权限验证
				.and()
				.formLogin()//表单登录，验证
				.loginPage("/web/login")//表单登录的请求地址
				.permitAll()
				.and()
				.logout()//注销
				.permitAll();
	}

	//内存模拟账户 登录
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth
				.inMemoryAuthentication()
				.withUser("user").password("123456").roles("USER");
	}*/
}
