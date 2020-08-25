package com.luv2code.springdemo.initializer

import com.luv2code.springdemo.config.AppConfig
import javax.servlet.ServletContext
import org.springframework.web.WebApplicationInitializer
import org.springframework.web.context.ContextLoaderListener
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext
import org.springframework.web.servlet.DispatcherServlet

class WebAppInitializer extends WebApplicationInitializer {
  override def onStartup(container: ServletContext): Unit = {
    // Create the 'root' Spring application context
    // This is also dispatcher context as Spring Boot does not separate them
    val rootContext = new AnnotationConfigWebApplicationContext
    rootContext.register(classOf[AppConfig])

    // Manage the lifecycle of the root application context
    container.addListener(new ContextLoaderListener(rootContext))

    // Register and map the dispatcher servlet
    val dispatcher = container.addServlet("dispatcher", new DispatcherServlet(rootContext))
    dispatcher.setLoadOnStartup(1)
    dispatcher.addMapping("/")
  }
}
