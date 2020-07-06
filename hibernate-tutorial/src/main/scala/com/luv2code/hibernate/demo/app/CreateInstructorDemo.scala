package com.luv2code.hibernate.demo.app

import com.luv2code.hibernate.demo.entity.{Instructor, InstructorDetail}
import org.hibernate.cfg.Configuration

object CreateInstructorDemo extends App {
  val factory = new Configuration()
    .configure("hibernate.cfg.xml")
    .addAnnotatedClass(classOf[Instructor])
    .addAnnotatedClass(classOf[InstructorDetail])
    .buildSessionFactory()
  val session = factory.getCurrentSession

  try {
    println("Creating instructor and instructor detail...")
    val tempInstructor = Instructor("Chad", "Darby", "darby@luv2code.com")
    val tempInstructorDetail = InstructorDetail("https://www.luv2code.com/youtube", "Luv 2 code!!!")

    println("Associating tempInstructorDetail with tempInstructor")
    tempInstructor.instructorDetail = tempInstructorDetail

    session.beginTransaction()

    // This will also save tempInstructorDetail due to CascadeType.ALL
    println(s"Saving instructor: $tempInstructor")
    session.save(tempInstructor)

    session.getTransaction.commit()
    println("Done!")
  } finally {
    factory.close()
  }
}
