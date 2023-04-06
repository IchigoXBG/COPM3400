package com.xbg3.config;

import com.xbg3.controller.interceptor.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    // In Maven pom.xml  <targetPath>
    String staticResourcesPath = "classpath:/META-INF/resources";
/*    String staticResourcesPath = "";*/
    @Autowired
    private LoginInterceptor loginInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
       InterceptorRegistration interceptor =  registry.addInterceptor(loginInterceptor).addPathPatterns("/**").excludePathPatterns("/user/**","/pages/login.html");
       interceptor.excludePathPatterns("/css/**","/js/**","/img/**","/element-ui/**");

    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**").addResourceLocations(staticResourcesPath);
        registry.addResourceHandler("/pages/**").addResourceLocations(staticResourcesPath+"/pages/");
        registry.addResourceHandler("/css/**").addResourceLocations(staticResourcesPath+"/css/");
        registry.addResourceHandler("/js/**").addResourceLocations(staticResourcesPath+"/js/");
        registry.addResourceHandler("/img/**").addResourceLocations(staticResourcesPath+"/img/");
        registry.addResourceHandler("/element-ui/**").addResourceLocations(staticResourcesPath+"/element-ui/");


    }

}
