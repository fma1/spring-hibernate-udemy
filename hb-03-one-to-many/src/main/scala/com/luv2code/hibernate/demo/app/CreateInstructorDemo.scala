package com.luv2code.hibernate.demo.app

import com.luv2code.hibernate.demo.entity._
import org.hibernate.cfg.Configuration
import org.hibernatewrapper.SessionFactoryWrapper

object CreateInstructorDemo extends App {
  val sessionFactory = new Configuration().configure("hibernate.cfg.xml")
    .addAnnotatedClass(classOf[Instructor])
    .addAnnotatedClass(classOf[InstructorDetail])
    .addAnnotatedClass(classOf[Course])
    .buildSessionFactory()
  val sfw = new SessionFactoryWrapper(sessionFactory)

  println("Creating instructor and instructor detail...")
  val tempInstructor1 = Instructor("Susan", "Public", "darby@luv2code.com")
  val tempInstructorDetail1 = InstructorDetail("https://www.youtube.com/", "Video Games")

  println("Associating tempInstructorDetail1 with tempInstructor1")
  tempInstructor1.instructorDetail = tempInstructorDetail1

  sfw.withTransaction() { session =>
    // This will also save tempInstructorDetail1 due to CascadeType.ALL
    println(s"Saving instructor: $tempInstructor1")
    session.save(tempInstructor1)
  }

  println("Done!")
  sessionFactory.close()
}
