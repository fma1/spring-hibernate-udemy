package com.luv2code.springdemo

import org.springframework.stereotype.Component

@Component
class TennisCoach extends Coach {
  override def getDailyWorkout: String = "Practice your backend volley"
}
