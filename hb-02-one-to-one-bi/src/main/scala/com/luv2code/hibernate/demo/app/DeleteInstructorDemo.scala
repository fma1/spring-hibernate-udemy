package com.luv2code.hibernate.demo.app

import com.luv2code.hibernate.demo.entity.{Instructor, InstructorDetail}
import org.hibernate.cfg.Configuration
import org.hibernatewrapper.SessionFactoryWrapper

object DeleteInstructorDemo extends App {
  val sessionFactory = new Configuration().configure("hibernate.cfg.xml")
    .addAnnotatedClass(classOf[Instructor])
    .addAnnotatedClass(classOf[InstructorDetail])
    .buildSessionFactory()
  val sfw = new SessionFactoryWrapper(sessionFactory)

  sfw.withTransaction() { session =>
    val theId = 1
    val tempInstructor = session.get(classOf[Instructor], theId)
    println(s"Found instructor: $tempInstructor")

    Option(tempInstructor).foreach(_ => {
      println(s"Deleting instructor: $tempInstructor")
      // This will also also associated details object due to CascadeType.ALL
      session.delete(tempInstructor)
    })
  }

  println("Done!")
  sessionFactory.close()
}
