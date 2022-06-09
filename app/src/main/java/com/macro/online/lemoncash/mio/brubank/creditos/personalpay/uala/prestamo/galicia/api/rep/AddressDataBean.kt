package com.macro.online.lemoncash.mio.brubank.creditos.personalpay.uala.prestamo.galicia.api.rep

import com.github.gzuliyujiang.wheelview.contract.TextProvider

class AddressDataBean : TextProvider {
    /**
     * neitherImportanceMadBoot : 26
     * silentNovemberMiddlePollution : 2
     * unimportantBirdcageDoubleApology : 1
     * rightSkillTibetan : 25 DE MAYO
     */
    var neitherImportanceMadBoot: String? = null
    var silentNovemberMiddlePollution: String? = null
    var unimportantBirdcageDoubleApology: String? = null
    var rightSkillTibetan: String? = null
    override fun provideText(): String {
        return rightSkillTibetan ?: ""
    }
}