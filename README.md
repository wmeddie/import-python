# Use Python modules on the JVM

```
# idea
Python.pipInstall("tensorflow==1.12")

Python.init()


# Import python modules
val sys = Python.importModule("sys")
val info = sys.version_info
val major = info.major
assert(major.toString == "3")
```
