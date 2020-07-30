package com.luv2code.hibernate.demo.app

// import java.util.logging.LogManager

import com.luv2code.hibernate.demo.entity.Student
import org.hibernate.Session
import org.hibernate.cfg.Configuration

import scala.jdk.CollectionConverters._

class QueryStudentDemo {}

object QueryStudentDemo extends App {
  /*
  System.setProperty("org.jboss.logging.provider", "jdk")
  LogManager.getLogManager.readConfiguration(classOf[QueryStudentDemo].getResourceAsStream("/logging.properties"))
   */

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

    session.createQuery("from Student").getResultList.asScala
      .foreach(println)

    println("\n\nStudents who have the last name of Doe:")
    session.createQuery("from Student s where s.lastName='Doe'").getResultList.asScala
      .foreach(println)

    println("\n\nStudents who have the last name of Doe or the first name of Daffy:")
    session.createQuery("from Student s where s.lastName='Doe' OR s.firstName='Daffy'").getResultList.asScala
      .foreach(println)

    println("\n\nStudents who email ends with luv2code.com: ")
    session.createQuery("from Student s where s.email LIKE '%luv2code.com'").getResultList.asScala
      .foreach(println)

    session.getTransaction.commit()
    println("Done!")
  } finally {
    factory.close()
  }
}
