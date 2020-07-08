package com.luv2code.hibernate.demo.app

import com.luv2code.hibernate.demo.entity.Employee
import org.hibernate.cfg.Configuration
import org.hibernatewrapper.SessionFactoryWrapper
import scala.jdk.CollectionConverters._

object HibernateDemo extends App {
  val sessionFactory = new Configuration().configure("hibernate.cfg.xml")
    .addAnnotatedClass(classOf[Employee])
    .buildSessionFactory()
  val sfw = new SessionFactoryWrapper(sessionFactory)

  val employee1 = Employee("John", "Doe", "Company A")
  val employee2 = Employee("Tim", "Brown", "Company B")

  System.err.println(s"Saving $employee1 and $employee2...")
  sfw.withTransaction() { session =>
    session.save(employee1)
    session.save(employee2)
  }

  System.err.println("Getting employees...")
  sfw.withTransaction() { session =>
    session.createQuery("from Employee").getResultList.asScala
      .foreach(System.err.println)
  }

  System.err.println("Getting employee with id 1...")
  sfw.withTransaction() { session =>
    session.createQuery("from Employee e where e.id = 1").getResultList.asScala
      .foreach(System.err.println)
  }

  System.err.println("Getting employee with company Company A...")
  sfw.withTransaction() { session =>
    session.createQuery("from Employee e where e.company = 'Company A'").getResultList.asScala
      .foreach(System.err.println)
  }

  System.err.println("Deleting employee with id 1...")
  sfw.withTransaction() { session =>
    session.createQuery("delete from Employee where id=1").executeUpdate()
  }

  System.err.println("Getting employees...")
  sfw.withTransaction() { session =>
    session.createQuery("from Employee").getResultList.asScala
      .foreach(System.err.println)
  }
}
