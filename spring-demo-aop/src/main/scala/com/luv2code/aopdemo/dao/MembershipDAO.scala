package com.luv2code.aopdemo.dao

import org.apache.logging.log4j.{LogManager, Logger}
import org.springframework.stereotype.Component

@Component
class MembershipDAO {
  val logger: Logger = LogManager.getLogger(classOf[MembershipDAO])

  def addAccount(): Unit = {
    logger.info(s"$getClass: DOING STUFF: ADDING A MEMBERSHIP ACCOUNT")
  }

  def addSillyMember(): Boolean = {
    logger.info(s"$getClass: DOING STUFF: ADDING A MEMBERSHIP ACCOUNT")
    true
  }

  def goToSleep(): Unit = {
    logger.info(s"$getClass: I'm going to sleep now...")
  }
}
