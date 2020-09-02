package com.luv2code.springdemo.controller

import com.luv2code.springdemo.entity.Student
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.{GetMapping, PathVariable, RequestMapping, RestController}

@Component
@RestController
@RequestMapping(Array("/api"))
class StudentRestController {

  lazy val theStudents = List(Student("Poornima", "Patel"), Student("Mario", "Rossi"), Student("Mary", "Smith"))
  lazy val theStudentsArray: Array[Student] = theStudents.toArray

  @GetMapping(path = Array("/students"), produces = Array(MediaType.APPLICATION_JSON_VALUE))
  def getStudents: List[Student] = {
    theStudents
  }

  @GetMapping(path = Array("/students/{studentId}"), produces = Array(MediaType.APPLICATION_JSON_VALUE))
  def getStudent(@PathVariable studentId: Int): Student = {
    theStudentsArray(studentId)
  }
}
