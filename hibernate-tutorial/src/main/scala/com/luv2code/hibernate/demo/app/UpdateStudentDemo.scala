package com.luv2code.hibernate.demo.app

import com.luv2code.hibernate.demo.entity.Student
import org.hibernate.Session
import org.hibernate.cfg.Configuration

class UpdateStudentDemo {}

object UpdateStudentDemo extends App {
  val factory = new Configuration()
    .configure("hibernate.cfg.xml")
    .addAnnotatedClass(classOf[Student])
    .buildSessionFactory()
  var session: Session = _
  val id = 1

  try {
    println("Creating student...")

    session = factory.getCurrentSession
    session.beginTransaction()
    println(s"\nGetting student with id: $id")
    val myStudent = session.get(classOf[Student], id)
    println(s"Updating student...")
    myStudent.setFirstName("Scooby")
    session.getTransaction.commit()

    session = factory.getCurrentSession
    session.beginTransaction()

    println("Update email for all students")
    session.createQuery("update Student set email='foo@gmail.com'")
        .executeUpdate()

    session.getTransaction.commit()
  } finally {
    factory.close()
  }
}

