package com.luv2code.hibernate.demo.entity

import javax.persistence._

import scala.beans.BeanProperty

@Entity
@Table(name = "instructor_detail")
class InstructorDetail(_youtubeChannel: String, _hobby: String) {
  @Id
  @BeanProperty
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name="id")
  var id: Int = _

  @BeanProperty
  @Column(name="youtube_channel")
  var youtubeChannel: String = _youtubeChannel

  @BeanProperty
  @Column(name="hobby")
  var hobby: String = _hobby

  private def this() = this(null, null)

  override def toString = s"InstructorDetail($id, $youtubeChannel, $hobby)"
}

object InstructorDetail {
  def apply(youtubeChannel: String, hobby: String): InstructorDetail = new InstructorDetail(youtubeChannel, hobby)
}



