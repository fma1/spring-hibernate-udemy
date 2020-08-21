package com.luv2code.aopdemo.dao

import com.luv2code.aopdemo.entity.Account
import org.apache.logging.log4j.{LogManager, Logger}
import org.springframework.stereotype.Component

import scala.beans.BeanProperty

@Component
class AccountDAO {
  @BeanProperty
  var name: String = _
  @BeanProperty
  var serviceCode: String = _

  val logger: Logger = LogManager.getLogger(classOf[AccountDAO])

  def addAccount(account: Account): Unit = {
    logger.info(s"$getClass: DOING MY DB WORK: ADDING AN ACCOUNT")
  }

  def doWork(): Boolean = {
    logger.info(s"$getClass: doWork()")
    true
  }
}
