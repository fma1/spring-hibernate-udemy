package com.luv2code.hibernate.demo.app

import com.luv2code.hibernate.demo.entity._
import org.apache.logging.log4j.LogManager
import org.hibernate.cfg.Configuration
import org.hibernatewrapper.SessionFactoryWrapper

object CreateCourseAndStudentsDemo extends App {
  val logger = LogManager.getLogger(getClass)

  val sessionFactory = new Configuration().configure("hibernate.cfg.xml")
    .addAnnotatedClass(classOf[Instructor])
    .addAnnotatedClass(classOf[InstructorDetail])
    .addAnnotatedClass(classOf[Course])
    .addAnnotatedClass(classOf[Review])
    .addAnnotatedClass(classOf[Student])
    .buildSessionFactory()
  val sfw = new SessionFactoryWrapper(sessionFactory)

  sfw.withTransaction() { session =>
    val tempCourse = Course("Pacman - How To Score One Million Points")

    tempCourse.addReviews(List(Review("Great course"), Review("Good course"), Review("Dumb course")))

    // Leveraging CascadeType.ALL
    logger.info(s"Saving course: $tempCourse")
    session.save(tempCourse)

    val tempStudent1 = Student("John", "Doe", "john@luv2code.com")
    val tempStudent2 = Student("Mary", "Public", "mary@luv2code.com")

    tempCourse.addStudents(List(tempStudent1, tempStudent2))
    logger.info("Saving students...")
    session.save(tempStudent1)
    session.save(tempStudent2)
    logger.info(s"Saved students: ${tempCourse.getStudents}")
  }

  logger.info("Done!")
  sessionFactory.close()
}
