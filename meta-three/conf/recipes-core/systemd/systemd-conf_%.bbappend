FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

SRC_URI:append:examplemachine = " \
    ${@bb.utils.contains('MACHINE_FEATURES', 'wifi', 'file://wlan0.network', '', d)} \
    https://raw.githubusercontent.com/RobertCNelson/netifd/master/systemd/network/wlan0.network;destsuffix=systemd/network \
"

do_install:append:examplemachine() {
    install -d ${D}${systemd_unitdir}/network
    install -m 0644 ${WORKDIR}/wlan0.network ${D}${systemd_unitdir}/network
    bbnote "Installed wlan0.network"
}

FILES:${PN}:append:examplemachine = "\
    ${systemd_unitdir}/network/wlan0.network \
"
