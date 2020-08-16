package com.luv2code.springdemo.dao

import java.util.{List => JList}

import com.luv2code.springdemo.entity.Customer
import org.apache.logging.log4j.{LogManager, Logger}
import org.hibernatewrapper.SessionFactoryWrapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository

@Repository
class CustomerDAO {
  @Autowired
  private var sessionFactoryWrapper: SessionFactoryWrapper = _

  def getCustomerById(theId: Int): Customer = {
    sessionFactoryWrapper.withTransaction() { session =>
      session.get(classOf[Customer], theId)
    }
  }

  def getCustomers: JList[Customer] = {
    sessionFactoryWrapper.withTransaction() { session =>
      session.createQuery("from Customer order by lastName", classOf[Customer]).getResultList
    }
  }

  def saveCustomer(customer: Customer): Unit = {
    sessionFactoryWrapper.withTransaction() { session =>
      session.saveOrUpdate(customer)
    }
  }
}

object CustomerDAO {
  val logger: Logger = LogManager.getLogger(classOf[CustomerDAO])
}
