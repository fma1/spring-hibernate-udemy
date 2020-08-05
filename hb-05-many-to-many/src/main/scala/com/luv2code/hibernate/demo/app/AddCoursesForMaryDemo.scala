package com.luv2code.hibernate.demo.app

import com.luv2code.hibernate.demo.entity._
import org.apache.logging.log4j.LogManager
import org.hibernate.cfg.Configuration
import org.hibernatewrapper.SessionFactoryWrapper

object AddCoursesForMaryDemo extends App {
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
    val criteriaBuilder = session.getCriteriaBuilder
    val criteriaQuery = criteriaBuilder.createQuery(classOf[Student])
    val root = criteriaQuery.from(classOf[Student])
    val predicate = criteriaBuilder.equal(root.get("firstName"), "Mary")
    criteriaQuery.select(root).where(Array(predicate):_*)
    val studentMary = session.createQuery(criteriaQuery).getSingleResult

    logger.info(s"Loaded student: $studentMary")
    logger.info(s"Courses: ${studentMary.getCourses}")

    val tempCourse1 = Course("Rubik's Cube - How to Speed Cube")
    val tempCourse2 = Course("Atari 2600 - Game Development")

    tempCourse1.addStudent(studentMary)
    tempCourse2.addStudent(studentMary)

    logger.info("Saving courses...")
    session.save(tempCourse1)
    session.save(tempCourse2)
  }

  logger.info("Done!")
  sessionFactory.close()
}
