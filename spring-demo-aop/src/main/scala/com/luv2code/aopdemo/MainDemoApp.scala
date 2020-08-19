package com.luv2code.aopdemo

import com.luv2code.aopdemo.config.AppConfig
import com.luv2code.aopdemo.dao.AccountDAO
import org.springframework.context.annotation.AnnotationConfigApplicationContext

object MainDemoApp {
  def main(args: Array[String]): Unit = {
    val context = new AnnotationConfigApplicationContext(classOf[AppConfig])
    val theDAO = context.getBean("accountDAO", classOf[AccountDAO])
    theDAO.addAccount()
    context.close()
  }
}
