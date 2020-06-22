package com.luv2code.springdemo

import org.springframework.context.annotation.AnnotationConfigApplicationContext

class AnnotationDemoApp {

}

object AnnotationDemoApp extends App {
  val context = new AnnotationConfigApplicationContext(classOf[SportConfig])
  val theCoach = context.getBean("swimCoach", classOf[SwimCoach])

  println(theCoach.getDailyWorkout)
  println(theCoach.getDailyFortune)

  println(s"email: ${theCoach.getEmail}")
  println(s"team: ${theCoach.getTeam}")

  context.close()
}
