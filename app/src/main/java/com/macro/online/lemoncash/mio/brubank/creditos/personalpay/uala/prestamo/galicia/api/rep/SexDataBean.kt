package com.macro.online.lemoncash.mio.brubank.creditos.personalpay.uala.prestamo.galicia.api.rep

import com.github.gzuliyujiang.wheelview.contract.TextProvider

class SexDataBean : TextProvider {
    /**
     * likelyFruitUndergroundBridge : 0
     * forgetfulEntertainmentUnusualQuiz : Masculino
     */
    var likelyFruitUndergroundBridge: String? = null
    var forgetfulEntertainmentUnusualQuiz: String? = null
    override fun provideText(): String {
        return forgetfulEntertainmentUnusualQuiz ?: "--"
    }
}