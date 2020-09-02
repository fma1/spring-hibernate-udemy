package com.luv2code.springdemo.config

import java.util.Properties

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.module.SimpleModule
import com.fasterxml.jackson.module.scala.DefaultScalaModule
import com.luv2code.springdemo.deserializer.StudentDeserializer
import com.luv2code.springdemo.entity.Student
import com.luv2code.springdemo.serializer.StudentSerializer
import com.mchange.v2.c3p0.ComboPooledDataSource
import org.hibernatewrapper.SessionFactoryWrapper
import org.springframework.context.annotation.{Bean, ComponentScan, Configuration, EnableAspectJAutoProxy}
import org.springframework.http.converter.HttpMessageConverter
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter
import org.springframework.orm.hibernate5.{HibernateTransactionManager, LocalSessionFactoryBean}
import org.springframework.transaction.PlatformTransactionManager
import org.springframework.transaction.annotation.EnableTransactionManagement
import org.springframework.web.servlet.config.annotation._
import org.springframework.web.servlet.view.InternalResourceViewResolver

@Configuration
@EnableTransactionManagement
@EnableWebMvc
@ComponentScan(basePackages = Array("com.luv2code.springdemo.*"))
class AppConfig extends WebMvcConfigurer {
  override def configureMessageConverters(converters: java.util.List[HttpMessageConverter[_]]): Unit = {
    val converter = new MappingJackson2HttpMessageConverter()
    converter.setObjectMapper(objectMapper)
    converters.add(converter.asInstanceOf[HttpMessageConverter[_]])
    super.configureMessageConverters(converters)
  }

  override def configureDefaultServletHandling(configurer: DefaultServletHandlerConfigurer): Unit = {
    configurer.enable()
  }

  override def addResourceHandlers(registry: ResourceHandlerRegistry): Unit = {
    registry
      .addResourceHandler("/static/**")
      .addResourceLocations("classpath:/static/")
  }

  override def addViewControllers(registry: ViewControllerRegistry): Unit = {
    registry.addViewController("/").setViewName("index")
  }

  @Bean
  def viewResolver: InternalResourceViewResolver = {
    val viewResolver = new InternalResourceViewResolver()
    viewResolver.setPrefix("/WEB-INF/view/")
    viewResolver.setSuffix(".jsp")
    viewResolver
  }

  @Bean
  def objectMapper: ObjectMapper = {
    val objectMapper = new ObjectMapper()
    objectMapper.registerModule(DefaultScalaModule)
    val studentSerializerModule = new SimpleModule()
    studentSerializerModule.addSerializer(new StudentSerializer())
    studentSerializerModule.addDeserializer(classOf[Student], new StudentDeserializer())
    objectMapper.registerModule(studentSerializerModule)
    objectMapper
  }

  @Bean
  def sessionFactory(): LocalSessionFactoryBean = {
    val sessionFactory = new LocalSessionFactoryBean
    sessionFactory.setDataSource(myDataSource())
    sessionFactory.setPackagesToScan("com.luv2code.springdemo.entity")
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

  @Bean
  def sessionFactoryWrapper: SessionFactoryWrapper = {
    new SessionFactoryWrapper(sessionFactory().getObject)
  }

  private def hibernateProperties = {
    val hibernateProperties = new Properties
    hibernateProperties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect")
    hibernateProperties.setProperty("show-sql", "true")
    hibernateProperties
  }
}
