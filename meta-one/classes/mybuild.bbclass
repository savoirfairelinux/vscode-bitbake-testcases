# addtask should be highlighted
# Hovering addtask should show its definition. Where is it defined in the doc?
addtask build

# mybuild and do_build should be highlighted different colors
mybuild_do_build () {
  echo "running mybuild_do_build."
}

# EXPORT_FUNCTIONS and do_build should be highlighted differently
# Hovering EXPORT_FUNCTIONS should show its definition. Where is it defined in the doc?
# Hovering do_build should should show its declaration.
# Clicking do_build should bring to its declaration
# build and do_build should be the same (https://docs.yoctoproject.org/bitbake/bitbake-user-manual/bitbake-user-manual-metadata.html?highlight=addtask#promoting-a-function-to-a-task
EXPORT_FUNCTIONS do_build
