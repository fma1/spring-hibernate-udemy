package com.luv2code.springdemo.util

trait Stringification { self: Product =>
  override def toString: String = {
    getClass.getDeclaredFields
      .zip(productIterator.toSeq)
      .map { case (a, b) => s"${a.getName}=$b" }
      .mkString(", ")
  }
}

