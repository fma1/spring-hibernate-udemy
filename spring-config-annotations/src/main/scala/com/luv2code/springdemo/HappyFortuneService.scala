package com.luv2code.springdemo

import org.springframework.stereotype.Component

@Component
class HappyFortuneService extends FortuneService {
  override def getFortune: String = "Today is your lucky day!"
}
