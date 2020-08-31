package com.luv2code.springdemo.entity

import com.luv2code.springdemo.util.Stringification

case class Student(/*id: Int,*/
                   firstName: String,
                   lastName: String)
  extends Stringification
