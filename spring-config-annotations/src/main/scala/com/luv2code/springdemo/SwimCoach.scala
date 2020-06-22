package com.luv2code.springdemo

import org.springframework.beans.factory.annotation.Value

import scala.beans.BeanProperty

// import org.springframework.beans.factory.annotation.Autowired

class SwimCoach(fortuneService: FortuneService) extends Coach {
  @BeanProperty
  @Value("${foo.email}")
  var email: String = _
  @BeanProperty
  @Value("${foo.team}")
  var team: String = _

  override def getDailyWorkout: String = "Swim 1000 meters as a warm-up."

  override def getDailyFortune: String = fortuneService.getFortune
}
