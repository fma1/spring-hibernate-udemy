package com.luv2code.springdemo.controller

import com.luv2code.springdemo.entity.Customer
import com.luv2code.springdemo.service.CustomerService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.{GetMapping, ModelAttribute, PostMapping, RequestParam}

@Controller
@GetMapping(Array("customer"))
class CustomerController {
  @Autowired
  private var customerService: CustomerService = _

  @GetMapping(Array("/list"))
  def listCustomers(theModel: Model): String = {
    theModel.addAttribute("customers", customerService.getCustomers)
    "list-customers"
  }

  @PostMapping(Array("/saveCustomer"))
  def saveCustomer(@ModelAttribute("customer") customer: Customer): String = {
    customerService.saveCustomer(customer)
    "redirect:/customer/list"
  }

  @PostMapping(Array("/deleteCustomer"))
  def deleteCustomer(@RequestParam("customerId") theId: Int, theModel: Model): String = {
    customerService.deleteCustomerById(theId)
    "redirect:/customer/list"
  }

  @GetMapping(Array("/showFormForAdd"))
  def showFormForAdd(theModel: Model): String = {
    theModel.addAttribute("customer", new Customer())
    "customer-form"
  }

  @GetMapping(Array("/showFormForUpdate"))
  def showFormForUpdate(@RequestParam("customerId") theId: Int, theModel: Model): String = {
    theModel.addAttribute("customer", customerService.getCustomerById(theId))
    "customer-form"
  }
}
