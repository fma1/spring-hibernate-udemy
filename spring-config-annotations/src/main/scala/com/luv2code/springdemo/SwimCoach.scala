package com.luv2code.springdemo

// import org.springframework.beans.factory.annotation.Autowired

class SwimCoach(fortuneService: FortuneService) extends Coach {

  override def getDailyWorkout: String = "Swim 1000 meters as a warm-up."

  override def getDailyFortune: String = fortuneService.getFortune
}
