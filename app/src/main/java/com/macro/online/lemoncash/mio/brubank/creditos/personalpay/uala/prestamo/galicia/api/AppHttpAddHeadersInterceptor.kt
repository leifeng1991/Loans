package com.macro.online.lemoncash.mio.brubank.creditos.personalpay.uala.prestamo.galicia.api

import com.macro.online.lemoncash.mio.brubank.creditos.personalpay.uala.prestamo.galicia.constants.AppConstants
import com.macro.online.lemoncash.mio.brubank.creditos.personalpay.uala.prestamo.galicia.utils.SharedPrefUtil
import com.moufans.lib_base.request.net.HttpAddHeadersInterceptor
import com.moufans.lib_base.utils.DeviceUtils
import com.moufans.lib_base.utils.InitUtils
import com.moufans.lib_base.utils.InitUtils.getApplication
import okhttp3.Request

class AppHttpAddHeadersInterceptor : HttpAddHeadersInterceptor() {
    override fun setHeader(request: Request.Builder) {
        super.setHeader(request)
        // appssid:client-id
        request.addHeader("facialRapidMiniskirt", "01")
        // token
        request.addHeader("mercifulAircraftAfricanHoneyDailySoil", SharedPrefUtil.get(AppConstants.USER_TOKEN, ""))
        // userId
        request.addHeader("safeContinentGreetingDeepSatisfaction", SharedPrefUtil.get(AppConstants.USER_ID, ""))
        // googlePlay
        request.addHeader("roundCaveDuckling", "googleplay")
        // versionName
        request.addHeader("thickArrivalQueen",  DeviceUtils.getVersionName(getApplication()))
        // versionCode
        request.addHeader("greyExchangeRichRelationProudAtmosphere", DeviceUtils.getVersionCode(getApplication()).toString())
        // deviceId
        request.addHeader("goldenBrunchEggplantHobby", DeviceUtils.getDeviceId())
        // imei
        request.addHeader("energeticRegulationSunnyReasonRoughTeenager", DeviceUtils.getImei(getApplication()))
        // device-id
        request.addHeader("arabTrickRapidDryer",  DeviceUtils.getDeviceId())
        //
        request.addHeader("unfitBlouseVariousUniversity", "true")
    }
}