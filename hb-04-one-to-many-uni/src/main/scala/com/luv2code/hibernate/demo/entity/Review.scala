package com.luv2code.hibernate.demo.entity

import com.luv2code.hibernate.demo.annotations.MyAnnotations.{Column => MyColumn}
import java.util.{List => JavaList}

import javax.persistence.{Column, Entity, GeneratedValue, GenerationType, Id, OneToMany, Table, TableGenerator}

import scala.beans.BeanProperty

@Entity
@Table(name = "review")
case class Review(
                   @MyColumn(name = "comment")
                   @BeanProperty
                   var comment: String) {

  @Id
  @BeanProperty
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @TableGenerator(name="myTableGen1", initialValue=0)
  @Column(name="id")
  var id: Int = _
}
