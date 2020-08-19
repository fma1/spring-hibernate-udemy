package com.luv2code.aopdemo.config

import org.springframework.context.annotation.{ComponentScan, Configuration, EnableAspectJAutoProxy}

@Configuration
@EnableAspectJAutoProxy
@ComponentScan(Array("com.luv2code.aopdemo"))
class AppConfig {

}
