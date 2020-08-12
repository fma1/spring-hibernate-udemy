package com.luv2code.springdemo.config

import org.springframework.context.annotation.{Bean, ComponentScan, Configuration}
import org.springframework.web.servlet.config.annotation.{DefaultServletHandlerConfigurer, EnableWebMvc, ResourceHandlerRegistry, ViewResolverRegistry, WebMvcConfigurer, WebMvcConfigurerAdapter}

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = Array("com.luv2code.springdemo.config"))
class WebMvcConfig extends WebMvcConfigurer {
  override def configureDefaultServletHandling(configurer: DefaultServletHandlerConfigurer): Unit = {
    configurer.enable()
  }

  override def addResourceHandlers(registry: ResourceHandlerRegistry): Unit = {
    registry.addResourceHandler("/resources/**").addResourceLocations("/resources/")
    registry.addResourceHandler("/css/**").addResourceLocations("classpath:/static/css/")
  }
}
