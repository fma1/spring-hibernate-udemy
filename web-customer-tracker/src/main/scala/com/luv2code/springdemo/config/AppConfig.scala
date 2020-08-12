package com.luv2code.springdemo.config

import java.util.Properties

import com.mchange.v2.c3p0.ComboPooledDataSource
import org.springframework.context.annotation.{Bean, Configuration}
import org.springframework.orm.hibernate5.{HibernateTransactionManager, LocalSessionFactoryBean}
import org.springframework.transaction.PlatformTransactionManager
import org.springframework.transaction.annotation.EnableTransactionManagement
import org.thymeleaf.spring5.SpringTemplateEngine
import org.thymeleaf.spring5.view.ThymeleafViewResolver
import org.thymeleaf.templatemode.TemplateMode
import org.thymeleaf.templateresolver.{ClassLoaderTemplateResolver, ITemplateResolver}

@Configuration
@EnableTransactionManagement
class AppConfig {
  @Bean
  def viewResolver: ThymeleafViewResolver = {
    val viewResolver = new ThymeleafViewResolver
    viewResolver.setTemplateEngine(templateEngine)
    // NOTE 'order' and 'viewNames' are optional
    viewResolver.setOrder(1)
    viewResolver.setViewNames(Array[String](".html", ".xhtml"))
    viewResolver
  }

  @Bean
  def templateResolver: ITemplateResolver = { // SpringResourceTemplateResolver automatically integrates with Spring's own
    // resource resolution infrastructure, which is highly recommended.
    val templateResolver = new ClassLoaderTemplateResolver()
    templateResolver.setPrefix("templates/")
    templateResolver.setSuffix(".html")
    // HTML is the default value, added here for the sake of clarity.
    templateResolver.setTemplateMode(TemplateMode.HTML)
    /*
    // Template cache is true by default. Set to false if you want
    // templates to be automatically updated when modified.
    templateResolver.setCacheable(true)
     */
    templateResolver.setCacheable(false)
    templateResolver
  }

  @Bean
  def templateEngine: SpringTemplateEngine = { // SpringTemplateEngine automatically applies SpringStandardDialect and
    // enables Spring's own MessageSource message resolution mechanisms.
    val templateEngine = new SpringTemplateEngine
    templateEngine.setTemplateResolver(templateResolver)
    // Enabling the SpringEL compiler with Spring 4.2.4 or newer can
    // speed up execution in most scenarios, but might be incompatible
    // with specific cases when expressions in one template are reused
    // across different data types, so this flag is "false" by default
    // for safer backwards compatibility.
    templateEngine.setEnableSpringELCompiler(true)
    templateEngine
  }

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
