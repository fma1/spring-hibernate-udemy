package com.luv2code.springdemo.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.luv2code.springdemo.entity.Student
import javax.servlet.http.HttpServletResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.{GetMapping, RequestMapping, RestController}

@Component
@RestController
@RequestMapping(Array("/api"))
class StudentRestController {
  @Autowired
  var objectMapper: ObjectMapper = _

  // TODO: Automatic conversion
  @GetMapping(path = Array("/students"), produces = Array(MediaType.APPLICATION_JSON_VALUE))
  def sayHello(response: HttpServletResponse): String = {
    objectMapper.writeValueAsString(List(Student("Poornima", "Patel"), Student("Mario", "Rossi"), Student("Mary", "Smith")))
  }
}
