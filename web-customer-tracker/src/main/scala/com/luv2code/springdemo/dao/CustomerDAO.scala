package com.luv2code.springdemo.dao

import java.util.{List => JList}

import com.luv2code.springdemo.dao.CustomerDAO.logger
import com.luv2code.springdemo.entity.Customer
import javax.annotation.PostConstruct
import org.apache.logging.log4j.{LogManager, Logger}
import org.hibernate.SessionFactory
import org.hibernatewrapper.SessionFactoryWrapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository

@Repository
class CustomerDAO {
  @Autowired
  private var sessionFactory: SessionFactory = _
  private var sessionFactoryWrapper: SessionFactoryWrapper = _

  @PostConstruct
  private def postConstruct(): Unit = {
    logger.info("CustomerDAO - PostConstruct")
    sessionFactoryWrapper = new SessionFactoryWrapper(sessionFactory)
  }

  def getCustomers: JList[Customer] = {
    sessionFactoryWrapper.withSession { session =>
      session.createQuery("from Customer order by lastName", classOf[Customer]).getResultList
    }
  }

  def saveCustomer(customer: Customer): Unit = {
    sessionFactoryWrapper.withSession { session =>
      session.save(customer)
    }
  }
}

object CustomerDAO {
  val logger: Logger = LogManager.getLogger(classOf[CustomerDAO])
}
