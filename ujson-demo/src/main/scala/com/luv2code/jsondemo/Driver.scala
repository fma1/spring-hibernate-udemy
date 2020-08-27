package com.luv2code.jsondemo

import java.io.File

import org.apache.logging.log4j.{LogManager, Logger}
import upickle.default

import scala.io.Source

object Driver {
  val logger: Logger = LogManager.getLogger(getClass)

  implicit val addressRW: default.ReadWriter[Address] = upickle.default.macroRW[Address]
  implicit val studentRW: default.ReadWriter[Student] = upickle.default.macroRW[Student]

  def main(args: Array[String]): Unit = {
    val student = upickle.default.read[Student](
      ujson.read(
        ujson.Readable.fromString(
          /*Source.fromResource("data/sample-full.json")*/
          Source.fromResource("data/sample-full-extra-prop.json")
            .getLines()
            .mkString("\n"))))

    logger.info(s"Student: $student")
    logger.info(s"Address: ${student.address}")
    logger.info(s"Languages: ${student.languages.mkString(", ")}")
  }
}
