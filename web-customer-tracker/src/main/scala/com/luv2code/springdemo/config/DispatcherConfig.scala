package com.luv2code.springdemo.config

import org.springframework.context.annotation.{Bean, ComponentScan, Configuration}
import org.springframework.web.servlet.ViewResolver
import org.springframework.web.servlet.config.annotation.{DefaultServletHandlerConfigurer, EnableWebMvc}
import org.springframework.web.servlet.view.InternalResourceViewResolver

/*
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = Array("com.luv2code.springdemo.config"))

 */
class DispatcherConfig {

  /*
  def configureDefaultServletHandling(configurer: DefaultServletHandlerConfigurer): Unit = {
    configurer.enable()
  }

  @Bean
  def getViewResolver: ViewResolver = {
    val viewResolver = new InternalResourceViewResolver
    viewResolver.setPrefix("/web/view/")
    viewResolver.setSuffix(".jsp")
    viewResolver
  }

   */
}
