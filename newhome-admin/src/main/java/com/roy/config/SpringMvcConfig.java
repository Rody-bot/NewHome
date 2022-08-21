package com.roy.config;

import com.roy.utils.AdminInterceptor;
import com.roy.utils.JacksonObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Slf4j
@Configuration
public class SpringMvcConfig implements WebMvcConfigurer {
    /*
    静态资源映射
     */

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/backend/**").addResourceLocations("classpath:backend/");
        registry.addResourceHandler("/front/**").addResourceLocations("classpath:front/");

    }
    @Bean
    public AdminInterceptor adminInterceptor(){
        return new AdminInterceptor();
    }
    /*
        拦截器
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(adminInterceptor()).addPathPatterns("/**").excludePathPatterns("/backend/**", "/front/**", "/employee/login", "/employee/logout","/user/sendMsg","/user/login");
    }
    /*
     扩展MVC的消息转换器
     */

    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter(new JacksonObjectMapper());
        converters.add(0,mappingJackson2HttpMessageConverter);
    }
}
