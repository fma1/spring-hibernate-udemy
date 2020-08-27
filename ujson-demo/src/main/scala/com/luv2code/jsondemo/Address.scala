package com.luv2code.jsondemo

case class Address(street: String,
                   city: String,
                   state: String,
                   zip: String,
                   country: String)
  extends Stringification
