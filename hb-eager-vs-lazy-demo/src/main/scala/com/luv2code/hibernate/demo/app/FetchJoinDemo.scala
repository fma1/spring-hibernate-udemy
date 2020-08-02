package com.luv2code.hibernate.demo.app

import com.luv2code.hibernate.demo.entity._
import org.apache.logging.log4j.{LogManager, Logger}
import org.hibernate.cfg.Configuration
import org.hibernate.query.Query

import scala.jdk.CollectionConverters._

object FetchJoinDemo {
  val logger: Logger = LogManager.getLogger(getClass)

  def main(args: Array[String]): Unit = {
    val sessionFactory = new Configuration().configure("hibernate.cfg.xml")
      .addAnnotatedClass(classOf[Instructor])
      .addAnnotatedClass(classOf[InstructorDetail])
      .addAnnotatedClass(classOf[Course])
      .buildSessionFactory()

    val session = sessionFactory.getCurrentSession

    session.beginTransaction()

    val theId = 1

    val query: Query[Instructor] = session.createQuery(
      """select i from Instructor i
        |JOIN FETCH i.courses
        |where i.id=:theInstructorId
        |""".stripMargin, classOf[Instructor])
    query.setParameter("theInstructorId", theId)

    val tempInstructor = query.getSingleResult

    session.getTransaction.commit()

    session.close()
    logger.info("The session is now closed!")

    sessionFactory.close()
    logger.info("Done!")

    logger.info(s"Associated courses: ${tempInstructor.courses.asScala.mkString(",")}")
  }
}
