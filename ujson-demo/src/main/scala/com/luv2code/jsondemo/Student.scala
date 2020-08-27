package com.luv2code.jsondemo

import scala.runtime.ScalaRunTime

case class Student(id: Int,
                   firstName: String,
                   lastName: String,
                   active: Boolean,
                   address: Address,
                   languages: Array[String])
  extends Stringification
