# Hovering DESCRIPTION should show its definition (https://docs.yoctoproject.org/ref-manual/variables.html?highlight=tmpdir#term-DESCRIPTION)
DESCRIPTION = "I am the first recipe"

# Hovering PR should should show its definition (https://docs.yoctoproject.org/ref-manual/variables.html?highlight=tmpdir#term-PR)
PR = "r1"

# Hovering do_build should show its definition (https://docs.yoctoproject.org/ref-manual/tasks.html#do-build)
do_build () {
  echo "first: some shell script running as build"
}
