package com.luv2code.hibernate.demo.app

import com.luv2code.hibernate.demo.entity._
import org.apache.logging.log4j.LogManager
import org.hibernate.cfg.Configuration
import org.hibernatewrapper.SessionFactoryWrapper

object DeletePacmanCourseDemo extends App {
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
    val criteriaQuery = criteriaBuilder.createQuery(classOf[Course])
    val root = criteriaQuery.from(classOf[Course])
    val predicate = criteriaBuilder.like(root.get("title"), "Pacman%")
    criteriaQuery.select(root).where(Array(predicate):_*)
    val pacmanCourse = session.createQuery(criteriaQuery).getSingleResult

    logger.info(s"Deleting course: $pacmanCourse")
    session.delete(pacmanCourse)
  }

  logger.info("Done!")
  sessionFactory.close()
}
