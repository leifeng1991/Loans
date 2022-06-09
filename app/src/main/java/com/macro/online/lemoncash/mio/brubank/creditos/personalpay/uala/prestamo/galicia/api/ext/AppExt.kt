package com.macro.online.lemoncash.mio.brubank.creditos.personalpay.uala.prestamo.galicia.api.ext

import com.macro.online.lemoncash.mio.brubank.creditos.personalpay.uala.prestamo.galicia.BuildConfig.BASE_URL
import com.macro.online.lemoncash.mio.brubank.creditos.personalpay.uala.prestamo.galicia.api.AppApi
import com.macro.online.lemoncash.mio.brubank.creditos.personalpay.uala.prestamo.galicia.api.AppHttpAddHeadersInterceptor
import com.moufans.lib_base.request.net.HttpLogInterceptor
import com.moufans.lib_base.request.net.RetrofitFactory

/**
 * 功能描述：扩展
 **/
val RetrofitFactory.Companion.appInstance
    get() = getInstance(BASE_URL, AppHttpAddHeadersInterceptor(), HttpLogInterceptor())

val appApi by lazy { RetrofitFactory.appInstance.create(AppApi::class.java) }