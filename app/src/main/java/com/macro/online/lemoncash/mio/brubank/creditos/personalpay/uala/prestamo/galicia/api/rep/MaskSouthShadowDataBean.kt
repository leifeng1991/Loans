package com.macro.online.lemoncash.mio.brubank.creditos.personalpay.uala.prestamo.galicia.api.rep

import com.github.gzuliyujiang.wheelview.contract.TextProvider
import com.macro.online.lemoncash.mio.brubank.creditos.personalpay.uala.prestamo.galicia.api.rep.MaskSouthShadowDataBean.SorryAngryScreenBean

class MaskSouthShadowDataBean {
    /**
     * sorryAngryScreen : [{"pleasantProudMemorialModel":3000,"betterStoneRainfall":100,"naturalSuggestionRadioactiveSunnyIce":3000,"fastEverythingBrightYear":0.22,"saltyDistantForest":14,"halfNylonSeed":102}]
     * nearbyPossibilityDuckFeather : 3006
     * fatAfternoonDangerousPublicThread : 2022-06-07 08:54:19
     * stupidDullCocoa : DrLoan
     */
    var nearbyPossibilityDuckFeather: String? = null
    var fatAfternoonDangerousPublicThread: String? = null
    var stupidDullCocoa: String? = null

    /**
     * pleasantProudMemorialModel : 3000.0
     * betterStoneRainfall : 100.0
     * naturalSuggestionRadioactiveSunnyIce : 3000.0
     * fastEverythingBrightYear : 0.22
     * saltyDistantForest : 14
     * halfNylonSeed : 102
     */
    var sorryAngryScreen: List<SorryAngryScreenBean>? = null

    class SorryAngryScreenBean : TextProvider {
        var pleasantProudMemorialModel = 0.00
        var betterStoneRainfall = 0.00
        var naturalSuggestionRadioactiveSunnyIce = 0.00
        var fastEverythingBrightYear = 0.0
        var saltyDistantForest = 0
        var halfNylonSeed = 0
        override fun provideText(): String {
            return "$naturalSuggestionRadioactiveSunnyIce"
        }
    }
}