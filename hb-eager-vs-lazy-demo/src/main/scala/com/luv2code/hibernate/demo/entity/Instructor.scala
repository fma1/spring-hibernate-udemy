package com.luv2code.hibernate.demo.entity

import java.util.{List => JavaList}

import javax.persistence._

import scala.beans.BeanProperty
import scala.jdk.CollectionConverters._

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

  @BeanProperty
  // @OneToMany(fetch = FetchType.EAGER, mappedBy="instructor",
  @OneToMany(fetch = FetchType.LAZY, mappedBy="instructor",
    cascade = Array(CascadeType.PERSIST, CascadeType.MERGE,
      CascadeType.DETACH, CascadeType.REFRESH))
  var courses: JavaList[Course] = new java.util.ArrayList[Course]()

  private def this() = this(null, null, null)

  override def toString = s"Instructor($id, $firstName, $lastName, $email, $instructorDetail)"

  private def initCourses(): Unit = {
    Option(courses) match {
      case None =>
        courses = List().asJava
      case _ => ()
    }
  }

  def add(course: Course): Unit = {
    initCourses()

    courses.add(course)
    course.instructor = this
  }

  def addAll(courses: Iterable[Course]): Unit = {
    initCourses()

    this.courses.addAll(courses.asJavaCollection)
    courses.foreach(course => course.instructor = this)
  }
}

object Instructor {
  def apply(firstName: String, lastName: String, email: String): Instructor = new Instructor(firstName, lastName, email)
}


