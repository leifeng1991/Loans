package com.macro.online.lemoncash.mio.brubank.creditos.personalpay.uala.prestamo.galicia.api

import com.macro.online.lemoncash.mio.brubank.creditos.personalpay.uala.prestamo.galicia.constants.AppConstants
import com.macro.online.lemoncash.mio.brubank.creditos.personalpay.uala.prestamo.galicia.utils.SharedPrefUtil
import com.moufans.lib_base.request.net.HttpAddHeadersInterceptor
import okhttp3.Request

class AppHttpAddHeadersInterceptor : HttpAddHeadersInterceptor() {
    override fun setHeader(request: Request.Builder) {
        super.setHeader(request)
        // appssid:client-id
        request.addHeader("facialRapidMiniskirt", "02")
        // token
        request.addHeader("mercifulAircraftAfricanHoneyDailySoil", SharedPrefUtil.get(AppConstants.USER_TOKEN, ""))
        // userId
        request.addHeader("safeContinentGreetingDeepSatisfaction", SharedPrefUtil.get(AppConstants.USER_ID, ""))
        // googlePlay
        request.addHeader("roundCaveDuckling", "googleplay")
        // versionName
        request.addHeader("thickArrivalQueen", "1.0.0")
        // versionCode
        request.addHeader("greyExchangeRichRelationProudAtmosphere", "100")
        // deviceId
        request.addHeader("goldenBrunchEggplantHobby", "deviceId")
        // imei
        request.addHeader("energeticRegulationSunnyReasonRoughTeenager", "imei")
        // device-id
        request.addHeader("arabTrickRapidDryer", "device-id")
        //
        request.addHeader("unfitBlouseVariousUniversity", "true")
    }
}