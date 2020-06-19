package com.luv2code.springdemo

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class AnnotationDemoApp {

}

object AnnotationDemoApp extends App {
  val context = SpringApplication.run(classOf[AnnotationDemoApp], args:_*)
  val theCoach = context.getBean("tennisCoach", classOf[Coach])
  val alphaCoach = context.getBean("tennisCoach", classOf[Coach])

  println(theCoach.getDailyWorkout)
  println(theCoach.getDailyFortune)

  println(theCoach.eq(alphaCoach))

  context.close()
}
