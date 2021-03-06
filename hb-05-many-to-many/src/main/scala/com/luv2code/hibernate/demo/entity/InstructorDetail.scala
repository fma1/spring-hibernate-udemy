package com.luv2code.hibernate.demo.entity

import javax.persistence._

import scala.beans.BeanProperty

@Entity
@Table(name = "instructor_detail")
class InstructorDetail(_youtubeChannel: String, _hobby: String) {
  @Id
  @BeanProperty
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @TableGenerator(name="myTableGen1", initialValue=0)
  @Column(name="id")
  var id: Int = _

  @BeanProperty
  @Column(name="youtube_channel")
  var youtubeChannel: String = _youtubeChannel

  @BeanProperty
  @Column(name="hobby")
  var hobby: String = _hobby

  @BeanProperty
  @OneToOne(mappedBy = "instructorDetail", cascade = Array(CascadeType.ALL))
  // Only deletes instructor detail
  // @OneToOne(mappedBy = "instructorDetail", cascade = Array(CascadeType.DETACH))
  var instructor: Instructor = _

  private def this() = this(null, null)

  override def toString = s"InstructorDetail($id, $youtubeChannel, $hobby)"
}

object InstructorDetail {
  def apply(youtubeChannel: String, hobby: String): InstructorDetail = new InstructorDetail(youtubeChannel, hobby)
}



