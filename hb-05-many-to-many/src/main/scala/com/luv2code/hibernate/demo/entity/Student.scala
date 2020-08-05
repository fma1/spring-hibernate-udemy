package com.luv2code.hibernate.demo.entity

import java.util.{List => JList}

import com.luv2code.hibernate.demo.annotations.MyAnnotations.{Column => MyColumn}
import javax.persistence.{CascadeType, Column, Entity, FetchType, GeneratedValue, GenerationType, Id, JoinColumn, JoinTable, ManyToMany, Table, TableGenerator}

import scala.beans.BeanProperty

@Entity
@Table(name = "student")
case class Student(
                  @MyColumn(name = "first_name") @BeanProperty var firstName: String,
                  @MyColumn(name = "last_name") @BeanProperty var lastName: String,
                  @MyColumn(name = "email") @BeanProperty var email: String
                  ) {
  @Id
  @BeanProperty
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name="id")
  var id: Int = _

  @ManyToMany(fetch = FetchType.LAZY,
    cascade = Array(CascadeType.PERSIST, CascadeType.MERGE,
    CascadeType.DETACH, CascadeType.REFRESH))
  @JoinTable(name = "course_student",
    joinColumns = Array(new JoinColumn(name = "student_id")),
    inverseJoinColumns = Array(new JoinColumn(name = "course_id")) )
  @BeanProperty
  var courses: JList[Course] = new java.util.ArrayList[Course]()

  private def this() = this(null, null, null)
}
