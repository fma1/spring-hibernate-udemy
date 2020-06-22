package com.luv2code.springdemo

class SadFortuneService extends FortuneService {
  override def getFortune: String = "Today is a sad day"
}
