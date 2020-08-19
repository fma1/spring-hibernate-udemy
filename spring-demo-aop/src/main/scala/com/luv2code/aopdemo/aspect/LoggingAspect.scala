package com.luv2code.aopdemo.aspect

import org.aspectj.lang.annotation.{Aspect, Before}
import org.springframework.stereotype.Component

@Aspect
@Component
class LoggingAspect {
  @Before("execution(public void addAccount())")
  def beforeAddAcountAdvice(): Unit = {
    println("\n=====>> Executing @Before advice on addAcount()")
  }
}
