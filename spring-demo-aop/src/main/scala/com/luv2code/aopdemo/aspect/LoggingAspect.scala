package com.luv2code.aopdemo.aspect

import com.luv2code.aopdemo.entity.Account
import org.apache.logging.log4j.{LogManager, Logger}
import org.aspectj.lang.JoinPoint
import org.aspectj.lang.annotation.{Aspect, Before}
import org.aspectj.lang.reflect.MethodSignature
import org.springframework.core.annotation.Order
import org.springframework.stereotype.Component

@Aspect
@Component
@Order(2)
class LoggingAspect {
  val logger: Logger = LogManager.getLogger(classOf[LoggingAspect])

  @Before("com.luv2code.aopdemo.aspect.AopExpressions.forDaoPackageNoGetterSetter()")
  def beforeAddAccountAdvice(theJoinPoint: JoinPoint): Unit = {
    logger.info("=====>> Executing @Before advice on addAccount()")

    val methodSignature = theJoinPoint.getSignature.asInstanceOf[MethodSignature]
    logger.info(s"Method: $methodSignature")

    theJoinPoint.getArgs.foreach(arg => {
      logger.info(s"Method Argument: $arg")
      arg match {
        case account: Account =>
          logger.info(s"account name: ${account.name}")
          logger.info(s"account level: ${account.level}")
      }
    })
  }
}
