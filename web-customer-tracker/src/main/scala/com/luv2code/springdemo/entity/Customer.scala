package com.luv2code.springdemo.entity

import com.luv2code.springdemo.annotations.Annotations.JColumn
import javax.persistence.{Column, Entity, GeneratedValue, GenerationType, Id, Table}

import scala.beans.BeanProperty

@Entity
@Table(name = "customer")
case class Customer(
                     @JColumn(name = "") @BeanProperty var firstName: String,
                     @JColumn(name = "") @BeanProperty var lastName: String,
                     @JColumn(name = "") @BeanProperty var email: String
                   ) {
  @Id
  @BeanProperty
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  var id: Int = _
}
