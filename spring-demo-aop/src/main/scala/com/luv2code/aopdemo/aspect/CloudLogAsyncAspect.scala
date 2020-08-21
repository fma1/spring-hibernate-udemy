package com.luv2code.aopdemo.aspect

import org.apache.logging.log4j.{LogManager, Logger}
import org.aspectj.lang.annotation.{Aspect, Before, Pointcut}
import org.springframework.core.annotation.Order
import org.springframework.stereotype.Component

@Aspect
@Component
@Order(1)
class CloudLogAsyncAspect {
  val logger: Logger = LogManager.getLogger(classOf[CloudLogAsyncAspect])

  @Before("com.luv2code.aopdemo.aspect.AopExpressions.forDaoPackageNoGetterSetter()")
  def logToCloudAsync(): Unit = {
    logger.info("=====>> Logging to Cloud in async fashion")
  }
}
