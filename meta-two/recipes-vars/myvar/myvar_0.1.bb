DESCRIPTION = "Show access to global MYVAR"
PR = "r1"

do_build(){
  # Should suggest "MYVAR" on typing
  # "${MYVAR}" should have its own color
  echo "myvar_sh: ${MYVAR}"
}

python do_myvar_py () {
  # Should suggest "MYVAR" on typing
  # Should "d.getVar" on typing
  # Should show declaration of d.getVar on hovering
  # Should bring to "d.getVar" declaration on clicking
  print ("myvar_py:" + d.getVar('MYVAR', True))
}

addtask myvar_py before do_build