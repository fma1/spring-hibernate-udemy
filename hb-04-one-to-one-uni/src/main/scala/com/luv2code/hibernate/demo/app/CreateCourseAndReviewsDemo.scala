package com.luv2code.hibernate.demo.app

import com.luv2code.hibernate.demo.entity._
import org.apache.logging.log4j.LogManager
import org.hibernate.cfg.Configuration
import org.hibernatewrapper.SessionFactoryWrapper

object CreateCourseAndReviewsDemo extends App {
  val logger = LogManager.getLogger(getClass)

  val sessionFactory = new Configuration().configure("hibernate.cfg.xml")
    .addAnnotatedClass(classOf[Instructor])
    .addAnnotatedClass(classOf[InstructorDetail])
    .addAnnotatedClass(classOf[Course])
    .addAnnotatedClass(classOf[Review])
    .buildSessionFactory()
  val sfw = new SessionFactoryWrapper(sessionFactory)

  sfw.withTransaction() { session =>
    val tempCourse = Course("Pacman - How To Score One Million Points")

    tempCourse.addReviews(List(Review("Great course"), Review("Good course"), Review("Dumb course")))

    // Leveraging CascadeType.ALL
    logger.info(s"Saving course: $tempCourse")
    session.save(tempCourse)
  }

  logger.info("Done!")
  sessionFactory.close()
}
