package com.luv2code.hibernate.demo.entity

import javax.persistence._

import scala.beans.BeanProperty

@Entity
@Table(name = "instructor")
class Instructor(_firstName: String, _lastName: String, _email: String) {
  @Id
  @BeanProperty
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name="id")
  var id: Int = _

  @BeanProperty
  @Column(name="first_name")
  var firstName: String = _firstName

  @BeanProperty
  @Column(name="last_name")
  var lastName: String = _lastName

  @BeanProperty
  @Column(name="email")
  var email: String = _email

  @BeanProperty
  @OneToOne(cascade = Array(CascadeType.ALL))
  @JoinColumn(name = "instructor_detail_id")
  var instructorDetail: InstructorDetail = _

  private def this() = this(null, null, null)

  override def toString = s"Instructor($id, $firstName, $lastName, $email, $instructorDetail)"
}

object Instructor {
  def apply(firstName: String, lastName: String, email: String): Instructor = new Instructor(firstName, lastName, email)
}


