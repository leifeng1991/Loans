package com.macro.online.lemoncash.mio.brubank.creditos.personalpay.uala.prestamo.galicia.base

import com.base.devices.UtilsApp
import com.bumptech.glide.Glide
import com.macro.online.lemoncash.mio.brubank.creditos.personalpay.uala.prestamo.galicia.utils.SharedPrefUtil
import com.moufans.lib_base.base.BaseApplication
import com.yuyh.library.imgsel.ISNav


class AppApplication : BaseApplication() {
    override fun onCreate() {
        super.onCreate()
        SharedPrefUtil.init(this)
        UtilsApp.init(this)
    }
}