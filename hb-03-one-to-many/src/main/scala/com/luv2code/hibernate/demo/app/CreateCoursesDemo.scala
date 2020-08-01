package com.luv2code.hibernate.demo.app

import com.luv2code.hibernate.demo.entity._
import org.apache.logging.log4j.LogManager
import org.hibernate.cfg.Configuration
import org.hibernatewrapper.SessionFactoryWrapper

object CreateCoursesDemo extends App {
  val logger = LogManager.getLogger(getClass)

  val sessionFactory = new Configuration().configure("hibernate.cfg.xml")
    .addAnnotatedClass(classOf[Instructor])
    .addAnnotatedClass(classOf[InstructorDetail])
    .addAnnotatedClass(classOf[Course])
    .buildSessionFactory()
  val sfw = new SessionFactoryWrapper(sessionFactory)

  sfw.withTransaction() { session =>
    val theId = 1
    val tempInstructor = session.get(classOf[Instructor], theId)

    logger.info("Instantiating courses...")
    val tempCourse1 = Course("Air Guitar - The Ultimate Guide")
    val tempCourse2 = Course("The Pinball Masterclass")

    tempInstructor.addAll(List(tempCourse1, tempCourse2))

    logger.info(s"Saving course: $tempCourse1")
    session.save(tempCourse1)
    logger.info(s"Saving course: $tempCourse2")
    session.save(tempCourse2)
  }

  logger.info("Done!")
  sessionFactory.close()
}
