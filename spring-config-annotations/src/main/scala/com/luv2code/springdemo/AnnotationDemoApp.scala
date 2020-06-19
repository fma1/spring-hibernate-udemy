package com.luv2code.springdemo

import org.springframework.context.annotation.AnnotationConfigApplicationContext

class AnnotationDemoApp {

}

object AnnotationDemoApp extends App {
  val context = new AnnotationConfigApplicationContext(classOf[SportConfig])
  val theCoach = context.getBean("tennisCoach", classOf[Coach])
  val alphaCoach = context.getBean("tennisCoach", classOf[Coach])

  println(theCoach.getDailyWorkout)
  println(theCoach.getDailyFortune)

  println(theCoach.eq(alphaCoach))

  context.close()
}
