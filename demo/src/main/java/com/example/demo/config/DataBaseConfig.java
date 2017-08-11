package com.example.demo.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * Created by evan.qi on 6/8/2017.
 */
@Configuration
@MapperScan(basePackages={"com/example/demo/dao"})
public class DataBaseConfig implements EnvironmentAware {

	private RelaxedPropertyResolver relaxedPropertyResolver ;

	@Value("${mybatis.mapperLocations}")
	private String locations;



	@Override
	public void setEnvironment(Environment environment) {
		this.relaxedPropertyResolver = new RelaxedPropertyResolver(environment,"spring.datasource.");
	}

	/**
	 * 获取database 源
	 * @return
	 */

	@Bean
	public DataSource dataSource(){
		HikariConfig config = new HikariConfig();
		config.setDriverClassName(relaxedPropertyResolver.getProperty("driver-class-name"));
		config.setAutoCommit(false);
		config.setJdbcUrl(relaxedPropertyResolver.getProperty("url"));
		config.setUsername(relaxedPropertyResolver.getProperty("username"));
		config.setPassword(relaxedPropertyResolver.getProperty("password"));
		return new HikariDataSource(config);
	}


	/**
	 * 事务
	 */
	@Bean
	public DataSourceTransactionManager transactionManager() {
		return new DataSourceTransactionManager(dataSource());
	}

	@Bean
	public SqlSessionFactory sqlSessionFactory() throws Exception {
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(dataSource());
		sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver()
				.getResources(locations));
		System.out.printf("locations is "+locations);
		return sqlSessionFactoryBean.getObject();
	}
}
