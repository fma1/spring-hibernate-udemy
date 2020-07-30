package com.luv2code.hibernate.demo.app

import com.luv2code.hibernate.demo.entity.{Instructor, InstructorDetail}
import org.hibernate.cfg.Configuration
import org.hibernatewrapper.SessionFactoryWrapper

object DeleteInstructorDetailDemo extends App {
  val sessionFactory = new Configuration().configure("hibernate.cfg.xml")
    .addAnnotatedClass(classOf[Instructor])
    .addAnnotatedClass(classOf[InstructorDetail])
    .buildSessionFactory()
  val sfw = new SessionFactoryWrapper(sessionFactory)

  sfw.withTransaction() { session =>
    val theId = 6
    val tempInstructorDetail = session.get(classOf[InstructorDetail], theId)
    println(s"Found instructor detail: $tempInstructorDetail")

    Option(tempInstructorDetail.instructor).foreach(tempInstructor =>
      println(s"Associated instructor: $tempInstructor"))

    println(s"Deleting instructor detail: $tempInstructorDetail")
    session.delete(tempInstructorDetail)
  }

  println("Done!")
  sessionFactory.close()
}
