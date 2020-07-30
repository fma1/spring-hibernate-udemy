package com.luv2code.springdemo.controller

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.{GetMapping, RequestMapping, RestController}

@Controller
@RequestMapping(Array("customer"))
class CustomerController {
  @RequestMapping(Array("/list"))
  def listCustomers(theModel: Model): String = {
    System.err.println("list-customers")
    "list-customers"
  }
}
