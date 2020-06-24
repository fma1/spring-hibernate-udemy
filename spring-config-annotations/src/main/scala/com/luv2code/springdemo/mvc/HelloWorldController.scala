package com.luv2code.springdemo.mvc

import javax.servlet.http.HttpServletRequest
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.RequestMapping

@Controller
class HelloWorldController {
  @RequestMapping(Array("/showForm"))
  def showForm(): String = "helloworld-form"

  @RequestMapping(Array("/processForm"))
  def processForm(): String = "helloworld"

  @RequestMapping(Array("/processFormVersionTwo"))
  def letsShoutDude(request: HttpServletRequest, model: Model): String = {
    model.addAttribute("message", s"Yo! ${request.getParameter("studentName").toUpperCase()}")
    "helloworld"
  }
}
