SUMMARY = "YEOM LCD user application (5x16 demo)"
LICENSE = "CLOSED"
SRC_URI = "file://lcd_app_copy.c \
	  file://lcd_copy.c \
	  file://lcd_copy.h \
	  file://char_copy.h \
	  file://README.md" 

S ="${WORKDIR}"

do_compile() {
	${CC} -I${STAGING_INCDIR} -o lcd_app lcd_app_copy.c lcd_copy.c gpio_copy.c -lpthread
}

do_install() {
	install -d ${D}${bindir}
	install -m 0755 lcd_app ${D}${bindir}/lcd_app

	# Install config/README etc
	install -d ${D}${datadir}/lcd-app
	install -m 0644 README.md ${D}${datadir}/lcd-app/README.md 
}

FILES_${PN} += "${datadir}/lcd_app"
