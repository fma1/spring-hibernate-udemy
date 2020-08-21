package com.luv2code.aopdemo.aspect

import org.apache.logging.log4j.{LogManager, Logger}
import org.aspectj.lang.annotation.{Aspect, Before, Pointcut}
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

  /*
  @Before("execution(public void addAccount(com.luv2code.aopdemo.entity.Account, ..))")
  def beforeAddAccountAdvice(): Unit = {
    logger.info("=====>> Executing @Before advice on addAccount()")
  }
   */

  @Pointcut("execution(* com.luv2code.aopdemo.dao.*.*(..))")
  private def forDaoPackage(): Unit = ()

  @Pointcut("execution(* com.luv2code.aopdemo.dao.*.get*(..))")
  private def getter(): Unit = ()

  @Pointcut("execution(* com.luv2code.aopdemo.dao.*.set*(..))")
  private def setter(): Unit = ()

  @Pointcut("forDaoPackage() && !(getter() || setter())")
  private def forDaoPackageNoGetterSetter(): Unit = ()

  @Before("forDaoPackageNoGetterSetter()")
  def beforeAddAccountAdvice(): Unit = {
    logger.info("=====>> Executing @Before advice on addAccount()")
  }

  @Before("forDaoPackageNoGetterSetter()")
  def performApiAnalytics(): Unit = {
    logger.info("=====>> Performing API Analytics")
  }

  /*
  @Before("execution(* com.luv2code.aopdemo.dao.MembershipDAO.add*())")
  def beforeAddMembershipAnyAdvice(): Unit = {
    logger.info("=====>> Executing @Before advice on Membership#add*()")
  }

  @Before("execution(* com.luv2code.aopdemo.dao.*.*())")
  def beforeAddMembershipAnyAdvice(): Unit = {
    logger.info("=====>> Executing @Before advice on all DAO methods")
  }
   */
}
