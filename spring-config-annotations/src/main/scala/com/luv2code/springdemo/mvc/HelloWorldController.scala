package com.luv2code.springdemo.mvc

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping

@Controller
class HelloWorldController {
  @RequestMapping(Array("/showForm"))
  def showForm(): String = "helloworld-form"

  @RequestMapping(Array("/processForm"))
  def processForm(): String = "helloworld"
}
