package com.luv2code.springdemo.aspect

import org.apache.logging.log4j.{LogManager, Logger}
import org.aspectj.lang.JoinPoint
import org.aspectj.lang.annotation.{AfterReturning, Aspect, Before}
import org.springframework.stereotype.Component

@Aspect
@Component
class CRMLoggingAspect {
  val logger: Logger = LogManager.getLogger(classOf[CRMLoggingAspect])

  @Before("com.luv2code.springdemo.aspect.AopExpressions.forAppFlow()")
  def before(joinPoint: JoinPoint): Unit = {
    logger.info(s"=====>> in @Before: calling method: ${joinPoint.getSignature.toShortString}")
    logger.info(s"=====>> in @Before: arguments: ${joinPoint.getArgs.mkString(",")}")
  }

  @AfterReturning(pointcut = "com.luv2code.springdemo.aspect.AopExpressions.forAppFlow()", returning = "result")
  def after(joinPoint: JoinPoint, result: AnyRef): Unit = {
    logger.info(s"=====>> in @AfterReturning: calling method: ${joinPoint.getSignature.toShortString}")
    logger.info(s"=====>> in @AfterReturning: result: $result")
  }
}
