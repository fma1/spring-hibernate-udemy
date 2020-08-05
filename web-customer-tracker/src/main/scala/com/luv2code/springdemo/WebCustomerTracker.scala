package com.luv2code.springdemo

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.builder.SpringApplicationBuilder
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer

object WebCustomerTracker {
  System.setProperty("server.port", "8082")

  def main(args: Array[String]): Unit = {
    SpringApplication.run(classOf[WebCustomerTracker], args: _*)
  }
}

@SpringBootApplication
class WebCustomerTracker extends SpringBootServletInitializer {
  override def configure(builder: SpringApplicationBuilder): SpringApplicationBuilder = {
    builder.sources(classOf[WebCustomerTracker])
  }
}
