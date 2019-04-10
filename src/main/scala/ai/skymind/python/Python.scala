package ai.skymind.python

import java.nio.charset.Charset

import scala.language.dynamics
import org.bytedeco.javacpp._
import org.bytedeco.javacpp.python._

class PyModuleProxy(module: String) extends Dynamic {
  val mod: PyObject = PyImport_ImportModule(module)

  def applyDynamic(name: String)(args: Any*): PyObjectProxy = {
    println(s"method '$name' called with arguments ${args.mkString("'", "', '", "'")}")

    val func = PyObject_GetAttrString(mod, name)
    if (func != null && PyCallable_Check(func) > 0) {
      val argTuple = PyTuple_New(args.length)

      for (arg <- args) {

      }

      val res = PyObject_CallObject(func, argTuple)
      new PyObjectProxy(res)
    } else {
      null
    }
  }

  def selectDynamic(name: String): PyObjectProxy = {
    new PyObjectProxy(PyObject_GetAttrString(mod, name))
  }
}

class PyObjectProxy(obj: PyObject) extends Dynamic {
  def applyDynamic(name: String): PyObjectProxy = {
    ???
  }

  def selectDynamic(name: String): PyObjectProxy = {
    new PyObjectProxy(PyObject_GetAttrString(obj, name))
  }

  override def toString: String = {
    val pyObject = PyObject_Repr(obj)
    val pointer = PyUnicode_AsUTF8(pyObject)
    pointer.getString
  }
}

object Python {

  def init(): Unit = {
    val program = Py_DecodeLocale("Python", null)
    Py_SetProgramName(program) /* optional but recommended */
    Py_Initialize()
  }

  def importModule(mod: String): PyModuleProxy = new PyModuleProxy(mod)

  def from(mod: String, importModule: String): PyModuleProxy = new PyModuleProxy(mod + "." + importModule)
}


