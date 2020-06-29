package com.luv2code.hibernate.demo.app

import com.luv2code.hibernate.demo.entity.Student
import org.hibernate.cfg.Configuration

object CreateUserDemoApp extends App {
  val factory = new Configuration()
    .configure("hibernate.cfg.xml")
    .addAnnotatedClass(classOf[Student])
    .buildSessionFactory()
  val session = factory.getCurrentSession

  try {
    println("Creating student...")
    val tempStudent = Student("Paul", "Wall", "paul@luv2code.com")
    session.beginTransaction()
    println("Saving the student...")
    session.save(tempStudent)
    session.getTransaction.commit()
    println("Done!")
  } finally {
    factory.close()
  }
}
