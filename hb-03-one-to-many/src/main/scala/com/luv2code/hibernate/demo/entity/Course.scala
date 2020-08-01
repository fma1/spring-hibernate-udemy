package com.luv2code.hibernate.demo.entity

import javax.persistence._

import scala.beans.BeanProperty

@Entity
@Table(name = "course")
class Course(_title: String) {
  @Id
  @BeanProperty
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name="id")
  var id: Int = _

  @BeanProperty
  @Column(name="title")
  var title: String = _title

  @BeanProperty
  @ManyToOne(cascade = Array(CascadeType.PERSIST, CascadeType.MERGE,
    CascadeType.DETACH, CascadeType.REFRESH))
  @JoinColumn(name="instructor_id")
  var instructor: Instructor = _

  private def this() = this(null)

  override def toString = s"Course($id, $title, $instructor)"
}

object Course {
  def apply(title: String) = new Course(title)
}
