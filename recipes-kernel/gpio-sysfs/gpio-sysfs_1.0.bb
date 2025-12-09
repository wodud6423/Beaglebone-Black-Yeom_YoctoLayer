# meta-yeom/recipes-kernel/gpio-sysfs/gpio-sysfs_1.0.bb
SUMMARY="YEOM GPIO sysfs kernel module for LCD"
DESRIPTION="Out-of-tree kernel module for controlling GPIO and 1602A LCD"
LICENSE="GPL-2.0"
LIC_FILES_CHKSUM="file://COPYING;md5=801f80980d171dd6425610833a22dbe6"

SRC_URQ ="file://gpio-sysfs_copy.c \
	  file://gpio_copy.c \
	  file://char_copy.h \
	  file://gpio_copy.h \
	  file://Makefile"

S = "${WORKDIR}"

inherit module

# Build (Basic build if Makefile is kernel module build )
do_compile() {
	# Use kernel build environment
	oe_runmake -C $(STAGING_KERNELDIR) M=${S} \
	ARCH=${KERNEL_ARCH} CROSS_COMPILE=${TARGET_PREFIX} modules
}

do_install() {
	install -d ${D}${base_libdir}/modules/${KERNEL_VERSION}
	install -m 0644 ${B}/gpio-sysfs_copy.ko ${D}${base_libdir}/modules/${KERNEL_VERSION}/
}

FILES_${PN}+="{base_libdir}/modules/${KERNEL_VERSION}/gpio-sysfs_copy.ko" 
