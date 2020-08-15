package com.luv2code.springdemo.controller

import com.luv2code.springdemo.service.CustomerService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping

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
}
