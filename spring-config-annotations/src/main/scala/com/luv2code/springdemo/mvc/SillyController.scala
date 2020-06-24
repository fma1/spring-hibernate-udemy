package com.luv2code.springdemo.mvc

import javax.servlet.http.HttpServletRequest
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.{RequestMapping, RequestParam}

@Controller
class SillyController {
  @RequestMapping(Array("/showForm"))
  def displayTheForm(): String = "helloworld-form"
}
