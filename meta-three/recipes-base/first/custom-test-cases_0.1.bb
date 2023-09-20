# Hovering DESCRIPTION should show its definition (https://docs.yoctoproject.org/bitbake/bitbake-user-manual/bitbake-user-manual-ref-variables.html#term-DESCRIPTION)
DESCRIPTION = "I am the first recipe"

# Hovering PR should should show its definition (https://docs.yoctoproject.org/ref-manual/variables.html?highlight=tmpdir#term-PR)
PR = "r1"

# Hovering do_build should show its definition (https://docs.yoctoproject.org/ref-manual/tasks.html#do-build)
do_build () {
  echo = "first: some shell script running as build"
}

do_build () {
 echo "first: some shell script running as build"
}


 A:append = '123'
A = "aval"
A = 'aval${123}'
B = "pre${A123}post"

A ?= "aval"
A[123] = '123'

# inherit 

123
inherit "123" #123 
require "123" #123
include "123" #123
export "123" #123
echo
def
python 
include include

=
?=
??=
:=
+=
=+
=.

addtask
deltask 
 after () { # inherit \
   # inherit \
 add = '123'
 } # inherit \
before
 
VAR = "123 \
       123"
VAR1 = "'"       
   var = "123"
'123'

DATE = "${@time.strftime('%Y%m%d',time.gmtime())}"

fakeroot def myfunc (var = '123',var2):

fakeroot python base_do_build (var) {
    bb.note("The included, default BB base.bbclass does not define a useful default task.")
    bb.note("Try running the 'listtasks' task against a .bb to see what tasks are defined.")
}
note("Try running the 'listtasks' task against a .bb to see what tasks are defined.")


fakeroot python base_do_build (var2) { #123
  #123
} #123

 fakeroot python base_do_build:append (var) {}

fakeroot def myfunc (var = '123',var2):
 fakeroot def def def myfunc (var = '123',var2):

python base_do_build:append (var = '123',var2) {}
 python base_do_build1:append (var = '123',var2) {}


fakeroot base_do_build:append (var = '123',var2) {}
 fakeroot base_do_build:append (var = '123',var2) {}

base_do_build () {}
 base_do_build () {}

base_do_build:append 

base_do_build:append (var = '123',var2) {}
 base_do_build:append (var = '123',var2) {}
 
base_do_build (var2='123') {}
 base_do_build () {}

base_do_build (var = '123',var2) {}
 base_do_build (var = '123',var2) {}
 

def myfunc1 (var = '123',var2):
def myfunc (var = '123',var2): 

(var = '123',var2)

python (var = '123',var2) {}
 python (var = '123',var2) {}

python () {
  
}
 python () {}

 python() {}
python() {}