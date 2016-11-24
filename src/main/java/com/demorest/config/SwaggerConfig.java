package com.demorest.config;

import io.swagger.jaxrs.config.BeanConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
/**
 * Created by marcus on 14/11/2016.
 */
@Configuration
public class SwaggerConfig {
        @Bean
        public BeanConfig beanConfig() {
            BeanConfig beanConfig = new BeanConfig();
            beanConfig.setVersion("1.0.2");
            beanConfig.setSchemes(new String[]{"http"});
            beanConfig.setHost("localhost:8080");
            beanConfig.setResourcePackage("com.demorest.endpoint");
            beanConfig.setScan(true);
            return beanConfig;
        }
}
