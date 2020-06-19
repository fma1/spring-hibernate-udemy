package com.luv2code.springdemo

import java.util.logging.{ConsoleHandler, Level, Logger, SimpleFormatter}

import javax.annotation.PostConstruct
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.{AnnotationConfigApplicationContext, Configuration, PropertySource}

@Configuration
@PropertySource(Array("classpath:logger.properties"))
class LoggerConfig {

  @Value("${root.logger.level}")
  private val rootLoggerLevel: String = null

  @Value("${printed.logger.level}")
  private val printedLoggerLevel: String = null

  @PostConstruct
  def initLogger(): Unit = {
    // parse levels
    val rootLevel = Level.parse(rootLoggerLevel)
    val printedLevel = Level.parse(printedLoggerLevel)
    // get logger for app context
    val applicationContextLogger = Logger.getLogger(classOf[AnnotationConfigApplicationContext].getName)
    // get parent logger
    val loggerParent = applicationContextLogger.getParent
    // set root logging level
    loggerParent.setLevel(rootLevel)
    // set up console handler
    val consoleHandler = new ConsoleHandler()
    consoleHandler.setLevel(printedLevel)
    consoleHandler.setFormatter(new SimpleFormatter())
    // add handler to the logger
    loggerParent.addHandler(consoleHandler)
  }

}
