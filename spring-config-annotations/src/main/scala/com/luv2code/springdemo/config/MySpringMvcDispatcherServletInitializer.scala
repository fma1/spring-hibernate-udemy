package com.luv2code.springdemo.config

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer


class MySpringMvcDispatcherServletInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
  override protected def getRootConfigClasses: Array[Class[_]] = null

  override protected def getServletConfigClasses: Array[Class[_]] = Array[Class[_]](classOf[DemoAppConfig])

  override protected def getServletMappings: Array[String] = Array[String]("/")
}