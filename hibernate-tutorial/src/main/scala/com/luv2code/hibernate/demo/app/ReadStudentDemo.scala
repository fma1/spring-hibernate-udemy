package com.luv2code.hibernate.demo.app

import com.luv2code.hibernate.demo.entity.Student
import org.hibernate.Session
import org.hibernate.cfg.Configuration

object ReadStudentDemo extends App {
  val factory = new Configuration()
    .configure("hibernate.cfg.xml")
    .addAnnotatedClass(classOf[Student])
    .buildSessionFactory()
  var session: Session = _

  try {
    println("Creating student...")
    val tempStudent = Student("Daffy", "Duck", "daffy@luv2code.com")

    session = factory.getCurrentSession
    session.beginTransaction()
    println("Saving the student...")
    println(tempStudent)
    session.save(tempStudent)
    session.getTransaction.commit()
    println(s"Saved student. Generated id: ${tempStudent.getId}")
    println("Done!")

    session = factory.getCurrentSession
    session.beginTransaction()
    println(s"\nGetting student with id: ${tempStudent.getId}")
    val myStudent = session.get(classOf[Student], tempStudent.getId)
    println(s"Get complete: $myStudent")
    session.getTransaction.commit()
    println("Done!")
  } finally {
    factory.close()
  }
}
