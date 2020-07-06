package com.luv2code.jdbc

import java.sql.DriverManager

object TestJdbc extends App {
  val jdbcUrl = "jdbc:mysql://localhost:3306/hb-01-one-to-one-uni?useSSL=false&serverTimezone=UTC"
  val user = "hbstudent"
  val pass = "hbstudent"

  println(s"Connecting to database: $jdbcUrl")
  DriverManager.getConnection(jdbcUrl, user, pass)
  println("Connection successful!!!")
}
