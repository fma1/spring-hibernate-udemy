package com.luv2code.aopdemo.aspect

import org.apache.logging.log4j.{LogManager, Logger}
import org.aspectj.lang.annotation.{Aspect, Before}
import org.springframework.stereotype.Component

@Aspect
@Component
class LoggingAspect {
  val logger: Logger = LogManager.getLogger(classOf[LoggingAspect])

  /*
  @Before("execution(public void addAccount())")
  def beforeAddAccountAdvice(): Unit = {
    logger.info("=====>> Executing @Before advice on addAccount()")
  }
   */

  @Before("execution(public void addAccount(com.luv2code.aopdemo.entity.Account, ..))")
  def beforeAddAccountAdvice(): Unit = {
    logger.info("=====>> Executing @Before advice on addAccount()")
  }

  /*
  @Before("execution(* com.luv2code.aopdemo.dao.MembershipDAO.add*())")
  def beforeAddMembershipAnyAdvice(): Unit = {
    logger.info("=====>> Executing @Before advice on Membership#add*()")
  }
   */

  @Before("execution(* com.luv2code.aopdemo.dao.*.*())")
  def beforeAddMembershipAnyAdvice(): Unit = {
    logger.info("=====>> Executing @Before advice on all DAO methods")
  }
}
