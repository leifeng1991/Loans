package com.macro.online.lemoncash.mio.brubank.creditos.personalpay.uala.prestamo.galicia.api

import com.base.devices.utils.LocationManagerUtils
import com.macro.online.lemoncash.mio.brubank.creditos.personalpay.uala.prestamo.galicia.constants.AppConstants
import com.macro.online.lemoncash.mio.brubank.creditos.personalpay.uala.prestamo.galicia.utils.SharedPrefUtil

object BaseFormDataUtils {
    fun getBaseHasMap(type: Int = 0): HashMap<String, String> {
        val hashMap: HashMap<String, String> = hashMapOf()
        hashMap["disabledContainerAnotherMother"] = "02"
        hashMap["safeContinentGreetingDeepSatisfaction"] = SharedPrefUtil.get(AppConstants.USER_ID, "")
        val locationManagerUtils = LocationManagerUtils()
        hashMap["stupidEnjoyablePasserSouthCup"] = "${locationManagerUtils.longitude},${locationManagerUtils.latitude}"
        hashMap["femaleShopBoundNurse"] = "es"
        if (type == 1) {
            hashMap["primaryUnableEasyMud"] = "00000000-0000-0000-0000-000000000000"
            hashMap["normalRichRecentHim"] = "00000000000000000000000000000000"
        }
        return hashMap
    }
}