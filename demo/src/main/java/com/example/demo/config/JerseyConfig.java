package com.example.demo.config;

import com.example.demo.resource.JerseyController;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Created by evan.qi on 6/8/2017.
 */
@Component
public class JerseyConfig extends ResourceConfig {

    private Logger logger = LoggerFactory.getLogger(JerseyConfig.class);

    public JerseyConfig() {
        logger.info("%s日志","测试");

        packages("com.example.demo.resource");
        property(ServletProperties.FILTER_STATIC_CONTENT_REGEX, "/.*\\.(html|css|js)");
        property(ServletProperties.FILTER_FORWARD_ON_404, true);
        register(JerseyController.class);
    }
}
