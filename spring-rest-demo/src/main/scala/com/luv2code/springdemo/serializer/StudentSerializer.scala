package com.luv2code.springdemo.serializer

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.databind.SerializerProvider
import com.fasterxml.jackson.databind.ser.std.StdSerializer
import com.luv2code.springdemo.entity.Student

class StudentSerializer extends StdSerializer[Student](classOf[Student]) {
  def this(t: Student.type) = this()

  override def serialize(student: Student, jsonGenerator: JsonGenerator, serializerProvider: SerializerProvider): Unit = {
    jsonGenerator.writeStartObject()
    jsonGenerator.writeStringField("firstName", student.firstName)
    jsonGenerator.writeStringField("lastName", student.lastName)
    jsonGenerator.writeEndObject()
  }
}
