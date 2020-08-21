package com.luv2code.aopdemo.aspect

import org.apache.logging.log4j.{LogManager, Logger}
import org.aspectj.lang.annotation.{Aspect, Before, Pointcut}
import org.springframework.core.annotation.Order
import org.springframework.stereotype.Component

@Aspect
@Component
@Order(3)
class ApiAnalyticsAspect {
  val logger: Logger = LogManager.getLogger(classOf[ApiAnalyticsAspect])

  @Before("com.luv2code.aopdemo.aspect.AopExpressions.forDaoPackageNoGetterSetter()")
  def performApiAnalytics(): Unit = {
    logger.info("=====>> Performing API Analytics")
  }
}
