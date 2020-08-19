package com.luv2code.aopdemo.dao

import org.springframework.stereotype.Component

@Component
class AccountDAO {
  def addAccount(): Unit = {
    println(s"$getClass: DOING MY DB WORK: ADDING AN ACCOUNT")
  }
}
