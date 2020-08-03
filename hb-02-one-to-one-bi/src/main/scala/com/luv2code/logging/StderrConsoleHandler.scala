package com.luv2code.logging

import java.io.OutputStream
import java.util.logging.ConsoleHandler

class StderrConsoleHandler extends ConsoleHandler {
  override protected def setOutputStream(out: OutputStream): Unit = {
    super.setOutputStream(System.err) // kitten killed here :-(
  }
}
