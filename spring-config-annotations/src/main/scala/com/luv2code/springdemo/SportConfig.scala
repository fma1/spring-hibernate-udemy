package com.luv2code.springdemo

import org.springframework.context.annotation.{Bean, ComponentScan, Configuration, PropertySource}

// @ComponentScan
@Configuration
@PropertySource(Array("classpath:sport.properties"))
class SportConfig {

  @Bean
  def happyFortuneService(): FortuneService = {
    new HappyFortuneService()
  }

  @Bean
  def sadFortuneService(): FortuneService = {
    new SadFortuneService()
  }

  @Bean
  def swimCoach(): SwimCoach = {
    new SwimCoach(sadFortuneService())
  }

}
