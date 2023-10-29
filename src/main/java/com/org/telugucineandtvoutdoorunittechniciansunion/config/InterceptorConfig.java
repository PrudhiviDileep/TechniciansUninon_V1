package com.org.telugucineandtvoutdoorunittechniciansunion.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.org.telugucineandtvoutdoorunittechniciansunion.interceptor.AppInterceptor;

@Component
public class InterceptorConfig extends WebMvcConfigurerAdapter {
   @Autowired
   AppInterceptor productServiceInterceptor;

   @Override
   public void addInterceptors(InterceptorRegistry registry) {
      registry.addInterceptor(productServiceInterceptor);
   }
}