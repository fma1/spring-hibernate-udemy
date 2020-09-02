package com.luv2code.springdemo.controller

import com.luv2code.springdemo.entity.Student
import com.luv2code.springdemo.exception.{StudentErrorResponse, StudentNotFoundException}
import org.springframework.http.{HttpStatus, MediaType, ResponseEntity}
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.{ExceptionHandler, GetMapping, PathVariable, RequestMapping, RestController}

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
    if (studentId < 0 || studentId > theStudentsArray.length)
      throw new StudentNotFoundException(s"Student id not found - $studentId")
    else
      theStudentsArray(studentId)
  }
}
