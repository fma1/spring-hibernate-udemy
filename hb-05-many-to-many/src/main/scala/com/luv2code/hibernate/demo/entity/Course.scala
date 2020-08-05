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

  @ManyToMany(fetch = FetchType.LAZY,
    cascade = Array(CascadeType.PERSIST, CascadeType.MERGE,
    CascadeType.DETACH, CascadeType.REFRESH))
  @JoinTable(name = "course_student",
    joinColumns = Array(new JoinColumn(name = "course_id")),
    inverseJoinColumns = Array(new JoinColumn(name = "student_id")) )
  @BeanProperty
  var students: JList[Student] = _

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

  def initStudents(): Unit = {
    students = Option(students).getOrElse(new java.util.ArrayList[Student]())
  }

  def addStudent(student: Student): Unit = {
    initStudents()
    students.add(student)
  }

  def addStudents(students: Iterable[Student]): Unit = {
    initStudents()
    this.students.addAll(students.asJavaCollection)
  }
}

object Course {
  def apply(title: String) = new Course(title)
}
