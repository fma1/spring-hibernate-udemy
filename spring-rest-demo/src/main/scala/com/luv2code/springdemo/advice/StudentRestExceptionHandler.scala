package com.luv2code.springdemo.advice

import com.luv2code.springdemo.exception.{StudentErrorResponse, StudentNotFoundException}
import org.springframework.http.{HttpStatus, ResponseEntity}
import org.springframework.web.bind.annotation.{ControllerAdvice, ExceptionHandler}

@ControllerAdvice
class StudentRestExceptionHandler {
  @ExceptionHandler
  def handleException(exception: StudentNotFoundException): ResponseEntity[StudentErrorResponse] = {
    new ResponseEntity(
      StudentErrorResponse(HttpStatus.NOT_FOUND.value(), exception.getMessage, System.currentTimeMillis()), HttpStatus.NOT_FOUND)
  }

  @ExceptionHandler
  def handleException(exception: Exception): ResponseEntity[StudentErrorResponse] = {
    new ResponseEntity(
      StudentErrorResponse(HttpStatus.BAD_REQUEST.value(), exception.getMessage, System.currentTimeMillis()), HttpStatus.BAD_REQUEST)
  }
}
