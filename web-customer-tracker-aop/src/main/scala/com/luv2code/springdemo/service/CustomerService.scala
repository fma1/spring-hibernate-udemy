package com.luv2code.springdemo.service

import org.springframework.stereotype.Service
import java.util.{List => JList}

import com.luv2code.springdemo.dao.CustomerDAO
import com.luv2code.springdemo.entity.Customer
import javax.transaction.Transactional
import org.springframework.beans.factory.annotation.Autowired

@Service
class CustomerService {
  @Autowired
  private var customerDAO: CustomerDAO = _

  @Transactional
  def getCustomerById(theId: Int): Customer = {
    customerDAO.getCustomerById(theId)
  }

  @Transactional
  def deleteCustomerById(theId: Int): Unit = {
    customerDAO.deleteCustomerById(theId)
  }

  @Transactional
  def getCustomers: JList[Customer] = {
    customerDAO.getCustomers
  }

  @Transactional
  def saveCustomer(customer: Customer): Unit = {
    customerDAO.saveCustomer(customer)
  }
}
