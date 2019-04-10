package ai.skymind.python

import org.scalatest.FunSuite

import scala.language.dynamics

class PythonTests extends FunSuite {
  Python.init()

  test("Test importing sys") {
    val sys = Python.importModule("sys")
    assert(sys != null)

    val info = sys.version_info
    assert(info != null)

    val major = info.major
    assert(major != null)

    assert(major.toString == "3")
  }

  /*
  test("import numpy") {
    val np = Python.importModule("numpy")

    assert(np.eye(3).toString == "")
  }
  */
}
