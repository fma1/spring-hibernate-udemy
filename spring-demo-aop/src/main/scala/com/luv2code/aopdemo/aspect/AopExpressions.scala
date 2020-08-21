package com.luv2code.aopdemo.aspect

import org.aspectj.lang.annotation.Pointcut

class AopExpressions {
  @Pointcut("execution(* com.luv2code.aopdemo.dao.*.*(..))")
  def forDaoPackage(): Unit = ()

  @Pointcut("execution(* com.luv2code.aopdemo.dao.*.get*(..))")
  def getter(): Unit = ()

  @Pointcut("execution(* com.luv2code.aopdemo.dao.*.set*(..))")
  def setter(): Unit = ()

  @Pointcut("forDaoPackage() && !(getter() || setter())")
  def forDaoPackageNoGetterSetter(): Unit = ()
}
