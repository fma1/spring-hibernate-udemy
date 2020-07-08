package com.luv2code.hibernate.demo.entity

import javax.persistence.{Column, Entity, GeneratedValue, GenerationType, Id, Table}

import scala.beans.BeanProperty

@Entity
@Table(name = "employee")
class Employee(_firstName: String, _lastName: String, _company: String) {
  @Id
  @BeanProperty
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name="id")
  var id: Int = _

  @BeanProperty
  @Column(name = "first_name")
  var firstName: String = _firstName

  @BeanProperty
  @Column(name = "last_name")
  var lastName: String = _lastName

  @BeanProperty
  @Column(name = "company")
  var company: String = _company

  private def this() = this(null, null, null)

  override def toString = s"Employee($id, $firstName, $lastName, $company)"
}

object Employee {
  def apply(firstName: String, lastName: String, company: String) = new Employee(firstName, lastName, company)
}
