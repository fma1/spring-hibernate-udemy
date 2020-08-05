package com.luv2code.hibernate.demo.annotations

import javax.persistence

import scala.annotation.meta.field

object MyAnnotations {
  type Column = persistence.Column @field
  type JoinColumn = persistence.JoinColumn @field
}
