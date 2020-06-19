package com.luv2code.springdemo

import javax.annotation.{PostConstruct, PreDestroy}
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Component

// This annotation auto-generates getters/setters for Spring Beans to Scala's getters/setters
// import scala.beans.BeanProperty

@Component
@Scope("prototype")
class TennisCoach extends Coach {
  println(">> TennisCoach: inside default constructor")

  // @BeanProperty
  var fortuneService: FortuneService = _

  @PostConstruct
  def doMyStartupStuff(): Unit = {
    println(">> TennisCoach: inside df doMyStartupStuff()")
  }

  // Not called for prototype beans
  @PreDestroy
  def doMyCleanupStuff(): Unit = {
    println(">> TennisCoach: inside df doMyStartupStuff()")
  }

  @Autowired
  def setFortuneService(fortuneService: FortuneService): Unit = {
    println(">> TennisCoach: inside setFortuneService() method")
    this.fortuneService = fortuneService
  }

  override def getDailyWorkout: String = "Practice your backend volley"

  override def getDailyFortune: String = fortuneService.getFortune
}
