package com.luv2code.aopdemo

import com.luv2code.aopdemo.config.AppConfig
import com.luv2code.aopdemo.dao.{AccountDAO, MembershipDAO}
import com.luv2code.aopdemo.entity.Account
import org.apache.logging.log4j.{LogManager, Logger}
import org.springframework.context.annotation.AnnotationConfigApplicationContext

object MainDemoApp {
  val logger: Logger = LogManager.getLogger("MainDemoApp")

  def main(args: Array[String]): Unit = {
    val context = new AnnotationConfigApplicationContext(classOf[AppConfig])
    val theAccountDAO = context.getBean("accountDAO", classOf[AccountDAO])
    val theMembershipDAO = context.getBean("membershipDAO", classOf[MembershipDAO])

    theAccountDAO.addAccount(Account(null, null))
    theAccountDAO.doWork()

    theMembershipDAO.addSillyMember()
    theMembershipDAO.goToSleep()

    context.close()
  }
}
