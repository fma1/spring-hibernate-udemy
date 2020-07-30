package com.luv2code.springdemo

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer

object WebCustomerTracker {
  System.setProperty("server.port", "8082")
  System.setProperty("server.jsp-servlet.class-name", "org.apache.jasper.servlet.JspServlet")
  System.setProperty("server.jsp-servlet.registered", "true")
  System.setProperty("server.servlet-path", "/")
  System.setProperty("spring.mvc.view.prefix", "/")
  System.setProperty("spring.mvc.view.suffix", ".jsp")

  def main(args: Array[String]): Unit = {
    SpringApplication.run(classOf[WebCustomerTracker], args: _*)
  }
}

@SpringBootApplication
class WebCustomerTracker extends SpringBootServletInitializer {}
