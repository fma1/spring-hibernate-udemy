package com.luv2code.springdemo

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class TennisCoach(@Autowired fortuneService: FortuneService) extends Coach {
  override def getDailyWorkout: String = "Practice your backend volley"
}
