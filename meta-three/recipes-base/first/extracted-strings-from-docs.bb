   VARIABLE = "value"
   VARIABLE = " value"
   VARIABLE = "value "
   VARIABLE = ""
   VARIABLE = " "
   VARIABLE = 'I have a " in my value'
   Unlike in Bourne shells, single quotes work identically to double
   quotes in all other ways. They do not suppress variable expansions.
   FOO = "bar \
          baz \
          qaz"
   FOO = "barbaz"
   FOO = "bar\
   baz"
   BitBake does not interpret escape sequences like "\\n" in variable
   values. For these to have an effect, the value must be passed to some
   utility that interprets escape sequences, such as
   ``printf`` or ``echo -n``.
   A = "aval"
   B = "pre${A}post"
   Unlike in Bourne shells, the curly braces are mandatory: Only ``${FOO}`` and not
   ``$FOO`` is recognized as an expansion of ``FOO``.
   A = "${B} baz"
   B = "${C} bar"
   C = "foo"
   *At this point, ${A} equals "foo bar baz"*
   C = "qux"
   *At this point, ${A} equals "qux bar baz"*
   B = "norf"
   *At this point, ${A} equals "norf baz"*
   BAR = "${FOO}"
   A ?= "aval"
   This assignment is immediate. Consequently, if multiple "?="
   assignments to a single variable exist, the first of those ends up
   getting used.
   W ??= "x"
   A := "${W}" # Immediate variable expansion
   W ??= "y"
   B := "${W}" # Immediate variable expansion
   W ??= "z"
   C = "${W}"
   W ?= "i"
   A = "x"
   B = "y"
   C = "i"
   W = "i"
   W ??= "x"
   W += "y"
   W = " y"
   W ??= "x"
   W:append = "y"
   W = "xy"
   T = "123"
   A := "test ${T}"
   T = "456"
   B := "${T} ${C}"
   C = "cval"
   C := "${C}append"
   B = "bval"
   B += "additionaldata"
   C = "cval"
   C =+ "test"
   B = "bval"
   B .= "additionaldata"
   C = "cval"
   C =. "test"
   B = "bval"
   B:append = " additional data"
   C = "cval"
   C:prepend = "additional data "
   D = "dval"
   D:append = "additional data"
   You must control all spacing when you use the override syntax.
   The overrides are applied in this order, ":append", ":prepend", ":remove".
   FOO = "123 456 789 123456 123 456 123 456"
   FOO:remove = "123"
   FOO:remove = "456"
   FOO2 = " abc def ghi abcdef abc def abc def def"
   FOO2:remove = "\
       def \
       abc \
       ghi \
       "
   The overrides are applied in this order, ":append", ":prepend", ":remove".
   This implies it is not possible to re-append previously removed strings.
   However, one can undo a ":remove" by using an intermediate variable whose
   content is passed to the ":remove" so that modifying the intermediate
     FOOREMOVE = "123 456 789"
     FOO:remove = "${FOOREMOVE}"
     ...
     FOOREMOVE = "123 789"
   Override application order may not match variable parse history, i.e.
   the output of ``bitbake -e`` may contain ":remove" before ":append",
   but the result will be removed string, because ":remove" is handled
   last.
   inherit foo
   FOO = "initial"
   FOO += "val"
   FOO:append = " val"
   It is never necessary to use "+=" together with ":append". The following
       FOO:append = "bar"
       FOO:append = "baz"
   FOO[a] = "abc"
   FOO[b] = "123"
   FOO[a] += "456"
   CACHE[doc] = "The directory holding the cache of the metadata."
   Variable flag names starting with an underscore (``_``) character
   are allowed but are ignored by ``d.getVarFlags("VAR")``
   in Python code. Such flag names are used internally by BitBake.
   DATE = "${@time.strftime('%Y%m%d',time.gmtime())}"
   PN = "${@bb.parse.vars_from_file(d.getVar('FILE', False),d)[0] or 'defaultpkgname'}"
   PV = "${@bb.parse.vars_from_file(d.getVar('FILE', False),d)[1] or '1.0'}"
   Inline Python expressions work just like variable expansions insofar as the
   "=" and ":=" operators are concerned. Given the following assignment, foo()
      FOO = "${@foo()}"
      FOO := "${@foo()}"
   unset DATE
   unset do_fetch[noexec]
   BBLAYERS ?= " \
       /home/scott-lenovo/LayerA \
   "
   export ENV_VARIABLE
   ENV_VARIABLE = "value from the environment"
   BitBake does not expand ``$ENV_VARIABLE`` in this case because it lacks the
   obligatory ``{}`` . Rather, ``$ENV_VARIABLE`` is expanded by the shell.
   export ENV_VARIABLE = "variable-value"
   Overrides can only use lower-case characters, digits and dashes.
   In particular, colons are not permitted in override names as they are used to
   separate overrides from each other and from the variable name.
      OVERRIDES = "architecture:os:machine"
      TEST = "default"
      TEST:os = "osspecific"
      TEST:nooverride = "othercondvalue"
      KBRANCH = "standard/base"
      KBRANCH:qemuarm = "standard/arm-versatile-926ejs"
      KBRANCH:qemumips = "standard/mti-malta32"
      KBRANCH:qemuppc = "standard/qemuppc"
      KBRANCH:qemux86 = "standard/common-pc/base"
      KBRANCH:qemux86-64 = "standard/common-pc-64/base"
      KBRANCH:qemumips64 = "standard/mti-malta64"
      DEPENDS = "glibc ncurses"
      OVERRIDES = "machine:local"
      DEPENDS:append:machine = "libmad"
      KERNEL_FEATURES:append = " ${KERNEL_EXTRA_FEATURES}"
      KERNEL_FEATURES:append:qemux86=" cfg/sound.scc cfg/paravirt_kvm.scc"
      KERNEL_FEATURES:append:qemux86-64=" cfg/sound.scc cfg/paravirt_kvm.scc"
      FOO:task-configure = "val 1"
      FOO:task-compile = "val 2"
      EXTRA_OEMAKE:prepend:task-compile = "${PARALLEL_MAKE} "
 
   A${B} = "X"
   B = "2"
   A2 = "Y"
   OVERRIDES = "foo"
   A = "Z"
   A:foo:append = "X"
   OVERRIDES = "foo"
   A = "Z"
   A:append:foo = "X"
   OVERRIDES = "foo"
   A = "Y"
   A:foo:append = "Z"
   A:foo:append = "X"
   A = "1"
   A:append = "2"
   A:append = "3"
   A += "4"
   A .= "5"
   The BBPATH variable is analogous to the environment variable PATH .
   inherit autotools
   You can override any values and functions of the inherited class
   within your recipe by doing so after the "inherit" statement.
   inherit buildhistory rm_work
   inherit ${VARNAME}
   VARIABLE = ""
   VARIABLE:someoverride = "myclass"
   python () {
       if condition == value:
           d.setVar('VARIABLE', 'myclass')
       else:
           d.setVar('VARIABLE', '')
   }
   inherit ${@'classname' if condition else ''}
   inherit ${@functionname(params)}
   include test_defs.inc
   
   require foo.inc
   INHERIT += "abc"
  
   INHERIT += "autotools pkgconfig"
   some_function () {
       echo "Hello World"
   }
   do_foo() {
       bbplain first
       fn
   }
  
   
   python some_python_function () {
       d.setVar("TEXT", "Hello World")
       print d.getVar("TEXT")
   }
   
   python do_foo:prepend() {
       bb.plain("first")
   }
  
   def get_depends(d):
       if d.getVar('SOMECONDITION'):
           return "dependencywithcond"
       else:
           return "dependency"
      bb.build.exec_func("my_bitbake_style_function", d)
    
      
   python () {
       if d.getVar('SOMEVAR') == 'value':
           d.setVar('ANOTHERVAR', 'value2')
   }
   python () {
       d.setVar('FOO', 'foo 2')
   }
   FOO = "foo 1"
   BAR = "bar 1"
   FOO = "foo 2"
   BAR += "bar 2"
   FOO = "foo"
   FOO:append = " from outside"
      classname_functionname
      bar_do_foo
      EXPORT_FUNCTIONS functionname
      EXPORT_FUNCTIONS do_foo
      do_foo() {
          if [ somecondition ] ; then
              bar_do_foo
          else
              # Do something else
          fi
      }
   python do_printdate () {
       import time
       print time.strftime('%Y%m%d', time.gmtime())
   }
   addtask printdate after do_fetch before do_build
   ``do_printdate``
   task is only run the first time you build the recipe with the
   ``bitbake``
   command. This is because BitBake considers the task "up-to-date"
   after that initial run. If you want to force the task to always be
   rerun for experimentation purposes, you can make BitBake always
   consider the task "out-of-date" by using the
   :ref:`[nostamp] <bitbake-user-manual/bitbake-user-manual-metadata:Variable Flags>`
      do_printdate[nostamp] = "1"
      $ bitbake recipe -c printdate -f
   addtask printdate
   $ bitbake recipe -c listtasks
   
     addtask package_write_tar before do_build after do_packagedata do_package
   deltask printdate
   do_b[noexec] = "1"
   
      export BB_ENV_PASSTHROUGH_ADDITIONS="$BB_ENV_PASSTHROUGH_ADDITIONS CCACHE_DIR"
      export CCACHE_DIR
     
   origenv = d.getVar("BB_ORIGENV", False)
   bar = origenv.getVar("BAR", False)
   variable = d.getVarFlags("variable")
   self.d.setVarFlags("FOO", {"func": True})
    do_configure[file-checksums] += "${MY_DIRPATH}/my-file.txt:True"
      
   python myclass_eventhandler() {
       from bb.event import getName
       print("The name of the Event is %s" % getName(e))
       print("The file we run for is %s" % d.getVar('FILE'))
   }
   myclass_eventhandler[eventmask] = "bb.event.BuildStarted
   bb.event.BuildCompleted"
      BBCLASSEXTEND = "native"
   
   addtask printdate after do_fetch before do_build
   For a task to run, it must be a direct or indirect dependency of some
   other task that is scheduled to run.
         $ bitbake recipe -c mytask
   do_configure[deptask] = "do_populate_sysroot"
   do_package_qa[rdeptask] = "do_packagedata"
   do_rootfs[recrdeptask] += "do_packagedata"
   do_a[recrdeptask] = "do_a do_b"
   do_patch[depends] = "quilt-native:do_populate_sysroot"
   :widths: auto
   :header-rows: 1
   * - *Operation*
     - *Description*
   * - ``d.getVar("X", expand)``
     - Returns the value of variable "X". Using "expand=True" expands the
       value. Returns "None" if the variable "X" does not exist.
   * - ``d.setVar("X", "value")``
     - Sets the variable "X" to "value"
   * - ``d.appendVar("X", "value")``
     - Adds "value" to the end of the variable "X". Acts like ``d.setVar("X",
       "value")`` if the variable "X" does not exist.
   * - ``d.prependVar("X", "value")``
     - Adds "value" to the start of the variable "X". Acts like
       ``d.setVar("X","value")`` if the variable "X" does not exist.
   * - ``d.delVar("X")``
     - Deletes the variable "X" from the datastore. Does nothing if the variable
       "X" does not exist.
   * - ``d.renameVar("X", "Y")``
     - Renames the variable "X" to "Y". Does nothing if the variable "X" does
       not exist.
   * - ``d.getVarFlag("X", flag, expand)``
     - Returns the value of variable "X". Using "expand=True" expands the
       value. Returns "None" if either the variable "X" or the named flag does
       not exist.
   * - ``d.setVarFlag("X", flag, "value")``
     - Sets the named flag for variable "X" to "value".
   * - ``d.appendVarFlag("X", flag, "value")``
     - Appends "value" to the named flag on the variable "X". Acts like
       ``d.setVarFlag("X", flag, "value")`` if the named flag does not exist.
   * - ``d.prependVarFlag("X", flag, "value")``
     - Prepends "value" to the named flag on the variable "X". Acts like
       ``d.setVarFlag("X", flag, "value")`` if the named flag does not exist.
   * - ``d.delVarFlag("X", flag)``
     - Deletes the named flag on the variable "X" from the datastore.
   * - ``d.setVarFlags("X", flagsdict)``
     - Sets the flags specified in the ``flagsdict()``
       parameter. ``setVarFlags`` does not clear previous flags. Think of this
       operation as ``addVarFlags``.
   * - ``d.getVarFlags("X")``
     - Returns a ``flagsdict`` of the flags for the variable "X". Returns "None"
       if the variable "X" does not exist.
   * - ``d.delVarFlags("X")``
     - Deletes all the flags for the variable "X". Does nothing if the variable
       "X" does not exist.
   * - ``d.expand(expression)``
     - Expands variable references in the specified string
       expression. References to variables that do not exist are left as is. For
       example, ``d.expand("foo ${X}")`` expands to the literal string "foo
       ${X}" if the variable "X" does not exist.
   addpylib <directory> <namespace>
   $ bitbake-dumpsigs
