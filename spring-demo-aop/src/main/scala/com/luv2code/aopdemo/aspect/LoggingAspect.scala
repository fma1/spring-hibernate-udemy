package com.luv2code.aopdemo.aspect

import org.apache.logging.log4j.{LogManager, Logger}
import org.aspectj.lang.annotation.{Aspect, Before, Pointcut}
import org.springframework.core.annotation.Order
import org.springframework.stereotype.Component

@Aspect
@Component
@Order(2)
class LoggingAspect {
  val logger: Logger = LogManager.getLogger(classOf[LoggingAspect])

  @Before("com.luv2code.aopdemo.aspect.AopExpressions.forDaoPackageNoGetterSetter()")
  def beforeAddAccountAdvice(): Unit = {
    logger.info("=====>> Executing @Before advice on addAccount()")
  }
}
