package com.luv2code.springdemo.aspect

import org.aspectj.lang.annotation.Pointcut

class AopExpressions {
  @Pointcut("execution(* com.luv2code.springdemo.controller.*.*(..))")
  def forControllerPackage(): Unit = ()

  @Pointcut("execution(* com.luv2code.springdemo.service.*.*(..))")
  def forServicePackage(): Unit = ()

  @Pointcut("execution(* com.luv2code.springdemo.dao.*.*(..))")
  def forDaoPackage(): Unit = ()

  @Pointcut("execution(* com.luv2code.springdemo.entity.*.*(..))")
  def forEntityPackage(): Unit = ()

  @Pointcut("forControllerPackage() || forServicePackage() || forDaoPackage() || forEntityPackage()")
  def forAppFlow(): Unit = ()
}
