package com.luv2code.hibernate.demo.app

import com.luv2code.hibernate.demo.entity._
import org.apache.logging.log4j.{LogManager, Logger}
import org.hibernate.cfg.Configuration
import org.hibernatewrapper.SessionFactoryWrapper

import scala.jdk.CollectionConverters._

object EagerLazyDemo {
  val logger: Logger = LogManager.getLogger(getClass)

  def main(args: Array[String]): Unit = {
    val sessionFactory = new Configuration().configure("hibernate.cfg.xml")
      .addAnnotatedClass(classOf[Instructor])
      .addAnnotatedClass(classOf[InstructorDetail])
      .addAnnotatedClass(classOf[Course])
      .buildSessionFactory()
    val sfw = new SessionFactoryWrapper(sessionFactory)

    sfw.withTransaction() { session =>
      val theId = 1
      val tempInstructor = session.get(classOf[Instructor], theId)
      logger.info(s"Found instructor: $tempInstructor")

      Option(tempInstructor)
        .map(_.courses.asScala)
        .foreach(courses => logger.info(s"Associated courses: ${courses.mkString(",")}"))
    }

    logger.info("Done!")
    sessionFactory.close()
  }
}
