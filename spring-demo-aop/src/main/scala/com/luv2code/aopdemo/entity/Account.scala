package com.luv2code.aopdemo.entity

import scala.beans.BeanProperty

case class Account(@BeanProperty var name: String, @BeanProperty var level: String) {
  private def this() = this(null, null)
}
