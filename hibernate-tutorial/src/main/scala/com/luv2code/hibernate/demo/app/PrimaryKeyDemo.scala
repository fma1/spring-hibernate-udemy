package com.luv2code.hibernate.demo.app

import com.luv2code.hibernate.demo.entity.Student
import org.hibernate.cfg.Configuration

object PrimaryKeyDemo extends App {
  val factory = new Configuration()
    .configure("hibernate.cfg.xml")
    .addAnnotatedClass(classOf[Student])
    .buildSessionFactory()
  val session = factory.getCurrentSession

  try {
    println("Creating student...")
    val tempStudent1 = Student("Paul", "Wall", "paul@luv2code.com")
    val tempStudent2 = Student("Mary", "Public", "mary@luv2code.com")
    val tempStudent3 = Student("Bonita", "Applebum", "bonita@luv2code.com")
    session.beginTransaction()
    println("Saving the student...")
    session.save(tempStudent1)
    session.save(tempStudent2)
    session.save(tempStudent3)
    session.getTransaction.commit()
    println("Done!")
  } finally {
    factory.close()
  }
}
