package com.luv2code.springdemo.entity

import com.luv2code.springdemo.annotations.Annotations.JColumn
import javax.persistence.{Column, Entity, GeneratedValue, GenerationType, Id, Table}

import scala.beans.BeanProperty

@Entity
@Table(name = "customer")
case class Customer(
                     @JColumn(name = "first_name") @BeanProperty var firstName: String,
                     @JColumn(name = "last_name") @BeanProperty var lastName: String,
                     @JColumn(name = "email") @BeanProperty var email: String
                   ) {
  @Id
  @BeanProperty
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  var id: Int = _

  private def this() = this(null, null, null)
}
