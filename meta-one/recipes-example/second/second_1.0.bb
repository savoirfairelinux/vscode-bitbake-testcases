DESCRIPTION = "I am the second recipe"
PR = "r1"

# Typing "inherit" should propose "mybuild"
# Clicking "mybuild" should bring to its file
inherit mybuild

def pyfunc(o):
    print(dir(o))

python do_mypatch () {
  # Should show declaration of d.getVar on hovering
  # Should bring to "d.getVar" declaration on clicking
  bb.note ("runnin mypatch")
  # Hovering "pyfunc" should show its declaration
  # Clicking on "pyfunc" should bring to its declaration
  pyfunc(d)
}

# "addtask" and "before" should be highlighted same color
# "mypatch" and "do_build" should be highlighted same color
addtask mypatch before do_build