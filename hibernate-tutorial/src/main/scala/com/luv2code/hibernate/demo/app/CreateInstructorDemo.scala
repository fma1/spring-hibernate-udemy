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
    val tempInstructor1 = Instructor("Chad", "Darby", "darby@luv2code.com")
    val tempInstructorDetail1 = InstructorDetail("https://www.luv2code.com/youtube", "Luv 2 code!!!")
    val tempInstructor2 = Instructor("Madhu", "Patel", "madhu@luv2code.com")
    val tempInstructorDetail2 = InstructorDetail("https://www.youtube.com/", "Luv 2 code!!!")

    println("Associating tempInstructorDetail1 with tempInstructor1")
    tempInstructor1.instructorDetail = tempInstructorDetail1
    tempInstructor2.instructorDetail = tempInstructorDetail2

    session.beginTransaction()

    // This will also save tempInstructorDetail1 due to CascadeType.ALL
    println(s"Saving instructor: $tempInstructor1")
    session.save(tempInstructor1)
    println(s"Saving instructor: $tempInstructor2")
    session.save(tempInstructor2)

    session.getTransaction.commit()
    println("Done!")
  } finally {
    factory.close()
  }
}
