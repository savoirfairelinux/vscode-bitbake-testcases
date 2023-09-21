VARIABLE = "value"
VARIABLE = " value"
VARIABLE = "value "
VARIABLE = ""
VARIABLE = " "
VARIABLE = 'I have a " in my value'
FOO = "bar \
baz \
qaz"
FOO = "barbaz"
FOO = "bar\
baz"

A = "aval"
B = "pre${A}post"

A = "${B} baz"
B = "${C} bar"
C = "foo"
C = "qux"
B = "norf"
BAR = "${FOO}"
A ?= "aval"

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

FOO = "123 456 789 123456 123 456 123 456"
FOO:remove = "123"
FOO:remove = "456"
FOO2 = " abc def ghi abcdef abc def abc def def"
FOO2:remove = "\
def \
abc \
ghi \
"

FOOREMOVE = "123 456 789"
FOO:remove = "${FOOREMOVE}"
FOOREMOVE = "123 789"

inherit foo
FOO = "initial"
FOO += "val"
FOO:append = " val"
FOO:append = "bar"
FOO:append = "baz"
FOO[a] = "abc"
FOO[b] = "123"
FOO[a] += "456"
CACHE[doc] = "The directory holding the cache of the metadata."

DATE = "${@time.strftime('%Y%m%d',time.gmtime())}"
PN = "${@bb.parse.vars_from_file(d.getVar('FILE', False),d)[0] or 'defaultpkgname'}"
PV = "${@bb.parse.vars_from_file(d.getVar('FILE', False),d)[1] or '1.0'}"
${@time.strftime('%Y%m%d',time.gmtime())}

FOO = "${@foo()}"
FOO := "${@foo()}"
unset DATE
unset do_fetch[noexec]
BBLAYERS ?= " \
/home/scott-lenovo/LayerA \
"
export ENV_VARIABLE
ENV_VARIABLE = "value from the environment"
do_foo() {
bbplain "$ENV_VARIABLE"
}
export ENV_VARIABLE = "variable-value"

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
inherit autotools
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
inherit ${@ 'classname' if condition else ''}
inherit ${@functionname(params)}
include test_defs.inc

require foo.inc
INHERIT += "abc"

INHERIT += "autotools pkgconfig"
some_function () {
    echo "Hello World"
}
do_foo(var = 123) {
    bbplain first
    fn
}
123

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

do_printdate[nostamp] = "1"
addtask printdate

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
do_configure[deptask] = "do_populate_sysroot"
do_package_qa[rdeptask] = "do_packagedata"
do_rootfs[recrdeptask] += "do_packagedata"
do_a[recrdeptask] = "do_a do_b"
do_patch[depends] = "quilt-native:do_populate_sysroot"
