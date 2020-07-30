package com.luv2code.testdb

import java.sql.DriverManager

import javax.servlet.ServletException
import javax.servlet.annotation.WebServlet
import javax.servlet.http.{HttpServlet, HttpServletRequest, HttpServletResponse}

import scala.util.Try

@WebServlet(Array("/TestDbServlet"))
class TestDbServlet extends HttpServlet {
  override def doGet(request: HttpServletRequest, response: HttpServletResponse): Unit = {
    val user = "springstudent"
    val pass = "springstudent"

    val jdbcUrl = "jdbc:mysql://localhost:3306/web_customer_tracker?useSSL=false&serverTimezone=UTC"
    val driver = "com.mysql.jdbc.Driver"

    Try({
      val out = response.getWriter

      out.println(s"Connecting to database: $jdbcUrl")

      Class.forName(driver)

      val conn = DriverManager.getConnection(jdbcUrl, user, pass)

      out.println("SUCCESS!!!")

      conn.close()
    }).recover(exception => {
      exception.printStackTrace()
      throw new ServletException(exception)
    })
  }
}
