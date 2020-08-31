package com.luv2code.springdemo.controller

import javax.servlet.http.HttpServletResponse
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.{GetMapping, RequestMapping, ResponseBody, RestController}

@Component
@RestController
@RequestMapping(Array("/test"))
class MyRestController {

  @GetMapping(path = Array("/hello"), produces = Array(MediaType.TEXT_PLAIN_VALUE))
  def sayHello(response: HttpServletResponse): String = {
    response.setContentType("text/plain")
    response.setCharacterEncoding("UTF-8")
    "Hello World!"
  }
}
