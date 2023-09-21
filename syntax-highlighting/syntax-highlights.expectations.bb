### Strings
# texts within "" or '' should be highlighted as strings
DESCRIPTION = "I am the first recipe"
"I am the first recipe"
DATE = "${@time.strftime('%Y%m%d',time.gmtime())}"
myfunc (var = '123',var2) {}
FOO2:remove = "\
    def \
    abc \
    ghi \
    "
BBLAYERS ?= " \
  /home/scott-lenovo/LayerA \
"
### numeric
# numbers should be highlighted when they are used as values
123
VAR = 123
myfunc (var = '123',var2 = 123) {} #123
VAR[123]


### operators
# Scope assigned: keyword.operator
??= 
:=  
??= 
:=  
??= 
= 
?= 

### Variable expansion
# Bitbake allows variable reference inside the string
A = "${B} baz"
B = "${C} bar"

### BitBake operators/overrides
# These special operators follow a variable or function and a single :  
VAR:append
VAR:prepend
VAR:remove
VAR:os
VAR:qemuarm
VAR:qemumips
VAR:qemuppc
VAR:qemux86
VAR:qemux86-64
VAR:qemumips64

foo:append() {}


### Custom operators
# No specific scope assigned to them as of now, they are treated as variable when highlighted
VAR:foo

### Directives/keywords
# Common keywords/directives
from 
import 
require 
inherit 
addtask 
deltask 
after 
before 
export 
echo 
if 
fi 
else 
return 
unset 
print 
or 
EXPORT_FUNCTIONS 
INHERIT
fakeroot
# Built-in objects
bbplain
bb
self
# Function specific keywords
def
python 
# Constant
True
False

### Key Expansion
# Variable reference can be placed next to a variable
A${B} = "X"
B = "2"
A2 = "Y"

### Shell Functions
# Functions names should be highlighted, the function content should be highlighted as per their assigned scopes
do_build () {
  echo = "first: some shell script running as build"
}

do_foo() {
    bbplain first
    fn
}

fn:prepend() {
    bbplain second
}

fn() {
    bbplain third
}

do_foo:append() {
    bbplain fourth
}

### BitBake-Style Python Functions
# Functions names should be highlighted. The leading keywords, trailing operators and the function content should be highlighted as per their assigned scopes
python some_python_function () {
    d.setVar("TEXT", "Hello World")
    print d.getVar("TEXT")
}
python do_foo:prepend() {
    bb.plain("first")
}

python do_foo() {
    bb.plain("second")
}

python do_foo:append() {
    bb.plain("third")
}

### Python Functions
# Normal python functions that are expected in a .py file
def get_depends(d):
    if d.getVar('SOMECONDITION'):
        return "dependencywithcond"
    else:
        return "dependency"


### Flag
# Flags are placed within a []
# No specific scope is assigned to the flags as of now, they are treated as variable when highlighted
CACHE[doc] = "The directory holding the cache of the metadata."

### Anonymous Python Functions
# No functio name while the keyword `python` is present. The function content should be highlighted as per their assigned scope
python () {
    if d.getVar('SOMEVAR') == 'value':
      d.setVar('ANOTHERVAR', 'value2')
}

### Showcase of the strings that follow the keywords/directives
# Keywords import/inherit are followed by classes
# Others have no specific scope assigned as of now
EXPORT_FUNCTIONS getName
deltask getName
from class.event import getName
import getName
require getName
inherit getName
addtask getName
deltask getName
after getName
before getName
export getName
echo getName
echo "123"

# Examples of nested patterns
DATE = "${@time.strftime('%Y%m%d',time.gmtime())}"
PN = "${@bb.parse.vars_from_file(d.getVar('FILE', False),d)[0] or 'defaultpkgname'}"
PV = "${@bb.parse.vars_from_file(d.getVar('FILE', False),d)[1] or '1.0'}"
${@time.strftime('%Y%m%d',time.gmtime())}

fakeroot python base_do_build (var) {
    bb.note("The included, default BB base.bbclass does not define a useful default task.")
    bb.note("Try running the 'listtasks' task against a .bb to see what tasks are defined.")
}

fakeroot python base_do_build:append (var1 = '123', var2 = 123) {}


fakeroot python base_do_build (var2, True) { #123
  #123
} #123

python myclass_eventhandler() {
    from bb.event import getName
    print("The name of the Event is %s" % getName(e))
    print("The file we run for is %s" % d.getVar('FILE'))
}

myclass_eventhandler[eventmask] = "bb.event.BuildStarted
bb.event.BuildCompleted"