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
  def getCustomers: JList[Customer] = {
    customerDAO.getCustomers
  }
}
