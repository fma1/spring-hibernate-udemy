package com.luv2code.springdemo.initializer

import com.luv2code.springdemo.config.{AppConfig, WebMvcConfig}
import javax.servlet.ServletContext
import org.springframework.web.WebApplicationInitializer
import org.springframework.web.context.ContextLoaderListener
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext
import org.springframework.web.servlet.DispatcherServlet

class WebAppInitializer extends WebApplicationInitializer {
  override def onStartup(container: ServletContext): Unit = {
    // Create the 'root' Spring application context
    val rootContext = new AnnotationConfigWebApplicationContext
    rootContext.register(classOf[AppConfig])

    // Manage the lifecycle of the root application context
    container.addListener(new ContextLoaderListener(rootContext))

    // Create the dispatcher servlet's Spring application context
    val dispatcherContext = new AnnotationConfigWebApplicationContext
    dispatcherContext.register(classOf[WebMvcConfig])

    // Register and map the dispatcher servlet
    val dispatcher = container.addServlet("dispatcher", new DispatcherServlet(dispatcherContext))
    dispatcher.setLoadOnStartup(1)
    dispatcher.addMapping("/")
  }
}
