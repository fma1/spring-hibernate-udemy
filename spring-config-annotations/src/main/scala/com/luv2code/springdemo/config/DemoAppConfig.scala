package com.luv2code.springdemo.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.ViewResolver
import org.springframework.web.servlet.config.annotation.{EnableWebMvc, ResourceHandlerRegistry, WebMvcConfigurer}
import org.springframework.web.servlet.view.InternalResourceViewResolver

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = Array("com.luv2code.springdemo"))
class DemoAppConfig extends WebMvcConfigurer { // define a bean for ViewResolver
  @Bean
  def viewResolver: ViewResolver = {
    val viewResolver = new InternalResourceViewResolver()
    viewResolver.setPrefix("/web/view/")
    viewResolver.setSuffix(".jsp")
    viewResolver
  }

  override def addResourceHandlers(registry: ResourceHandlerRegistry): Unit = {
    registry.addResourceHandler("/resources/**").addResourceLocations("/resources/")
  }
}