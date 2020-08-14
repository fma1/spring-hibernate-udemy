package com.luv2code.springdemo.config

import java.util.Properties

import com.mchange.v2.c3p0.ComboPooledDataSource
import org.springframework.context.annotation.{Bean, ComponentScan, Configuration}
import org.springframework.orm.hibernate5.{HibernateTransactionManager, LocalSessionFactoryBean}
import org.springframework.transaction.PlatformTransactionManager
import org.springframework.transaction.annotation.EnableTransactionManagement

@Configuration
@EnableTransactionManagement
@ComponentScan(basePackages = Array("com.luv2code.springdemo.dao", "com.luv2code.springdemo.entity"))
class AppConfig {
  @Bean
  def sessionFactory(): LocalSessionFactoryBean = {
    val sessionFactory = new LocalSessionFactoryBean
    sessionFactory.setDataSource(myDataSource())
    sessionFactory.setPackagesToScan("com.luv2code.springdemo.entity");
    sessionFactory.setHibernateProperties(hibernateProperties)
    sessionFactory
  }

  @Bean
  def myDataSource(): ComboPooledDataSource = {
    val dataSource = new ComboPooledDataSource
    dataSource.setDriverClass(classOf[com.mysql.cj.jdbc.Driver].getName)
    dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/web_customer_tracker?useSSL=false&serverTimezone=UTC")
    dataSource.setUser("springstudent")
    dataSource.setPassword("springstudent")
    dataSource.setMinPoolSize(5)
    dataSource.setMaxPoolSize(20)
    dataSource.setMaxIdleTime(30000)
    dataSource
  }

  @Bean
  def hibernateTransactionManager: PlatformTransactionManager = {
    val transactionManager = new HibernateTransactionManager
    transactionManager.setSessionFactory(sessionFactory().getObject)
    transactionManager
  }

  private def hibernateProperties = {
    val hibernateProperties = new Properties
    hibernateProperties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect")
    hibernateProperties.setProperty("show-sql", "true")
    hibernateProperties
  }
}
