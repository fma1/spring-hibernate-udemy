package com.luv2code.springdemo.deserializer

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.{DeserializationContext, JsonNode}
import com.fasterxml.jackson.databind.deser.std.StdDeserializer
import com.luv2code.springdemo.entity.Student

class StudentDeserializer extends StdDeserializer[Student](classOf[Student]) {
  def this(t: Student.type) = this()

  override def deserialize(jsonParser: JsonParser, deserializationContext: DeserializationContext): Student = {
    val oc = jsonParser.getCodec
    val node: JsonNode = oc.readTree(jsonParser)
    val firstName = node.get("firstName").asText
    val lastName = node.get("lastName").asText
    Student(firstName, lastName)
  }
}
