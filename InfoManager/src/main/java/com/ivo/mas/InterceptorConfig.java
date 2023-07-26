package com.ivo.mas;

import com.ivo.mas.service.MainService;
import com.ivo.mas.system.Interceptor.LoginInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;
import java.util.Collections;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Resource
    MainService mainService;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor(mainService)).addPathPatterns("/**").excludePathPatterns("/school/**","/test/**","/register","/static/**","/logout","/login");
    }

//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        //1、添加资源处理器路径 即每次访问静态资源都得添加"/static/",例如localhost:8080/static/j1.jpg
//        //若registry.addResourceHandler("/s/**") 则必须访问localhost:8080/s/j1.jpg
//        registry.addResourceHandler("/static/**")
//                //2、添加了资源处理器路径后对应的映射资源路径
//                .addResourceLocations("classpath:/static","classpath:/static/img/**","classpath:/static/Info","classpath:/static/Manager");
//
//    }

   // 解决跨域问题
//    @Bean
//    public CorsFilter corsFilter() {
//        CorsConfiguration corsConfiguration = new CorsConfiguration();
//        //1,允许任何来源
//        corsConfiguration.setAllowedOriginPatterns(Collections.singletonList("*"));
//        //2,允许任何请求头
//        corsConfiguration.addAllowedHeader(CorsConfiguration.ALL);
//        //3,允许任何方法
//        corsConfiguration.addAllowedMethod(CorsConfiguration.ALL);
//        //4,允许凭证
//        corsConfiguration.setAllowCredentials(true);
//
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", corsConfiguration);
//        return new CorsFilter(source);
//    }
}
