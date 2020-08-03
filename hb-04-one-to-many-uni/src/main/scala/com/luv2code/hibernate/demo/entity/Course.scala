package com.luv2code.hibernate.demo.entity

import java.util.{List => JList}

import javax.persistence._

import scala.beans.BeanProperty
import scala.jdk.CollectionConverters._

@Entity
@Table(name = "course")
class Course(_title: String) {
  @Id
  @BeanProperty
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  var id: Int = _

  @BeanProperty
  @Column(name = "title")
  var title: String = _title

  @BeanProperty
  @ManyToOne(cascade = Array(CascadeType.PERSIST, CascadeType.MERGE,
    CascadeType.DETACH, CascadeType.REFRESH))
  @JoinColumn(name="instructor_id")
  var instructor: Instructor = _

  @BeanProperty
  @OneToMany(fetch = FetchType.LAZY, cascade = Array(CascadeType.ALL))
  @JoinColumn(name = "course_id")
  var courseReviews: JList[Review] = _

  private def this() = this(null)

  override def toString = s"Course($id, $title, $instructor)"

  def initReviews(): Unit = {
    courseReviews = Option(courseReviews).getOrElse(new java.util.ArrayList[Review]())
  }

  def addReview(review: Review): Unit = {
    initReviews()
    courseReviews.add(review)
  }

  def addReviews(reviews: Iterable[Review]): Unit = {
    initReviews()
    courseReviews.addAll(reviews.asJavaCollection)
  }
}

object Course {
  def apply(title: String) = new Course(title)
}
