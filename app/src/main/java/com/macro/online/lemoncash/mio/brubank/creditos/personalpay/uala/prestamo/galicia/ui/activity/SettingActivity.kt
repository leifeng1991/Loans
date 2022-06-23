package com.macro.online.lemoncash.mio.brubank.creditos.personalpay.uala.prestamo.galicia.ui.activity

import android.content.Context
import android.content.Intent
import com.macro.online.lemoncash.mio.brubank.creditos.personalpay.uala.prestamo.galicia.R
import com.macro.online.lemoncash.mio.brubank.creditos.personalpay.uala.prestamo.galicia.databinding.ActivitySettingBinding
import com.macro.online.lemoncash.mio.brubank.creditos.personalpay.uala.prestamo.galicia.utils.CallUtils
import com.moufans.lib_base.utils.LogUtil
import java.util.*

class SettingActivity : AppBaseActivity<ActivitySettingBinding>() {

    override fun getDataBindingLayoutResId(): Int {
        return R.layout.activity_setting
    }

    override fun initView() {
        setHeaderTitle(resources.getString(R.string.setting_title))
    }

    override fun initListener() {
        setHeaderRightRightImage(R.mipmap.ic_white_phone) { CallUtils.showCallDialog(this@SettingActivity) }
        mDataBinding.mEnglishTextView.setOnClickListener {
            val res = resources.configuration.locale
            if (res.language == "es"){
                val config = resources.configuration
                val display = resources.displayMetrics
                config.locale = Locale("es", "AR")
                resources.updateConfiguration(config, display)
            }

        }
        mDataBinding.mEspTextView.setOnClickListener {
            val res = resources.configuration.locale
            LogUtil.e("=========$res")
        }
    }

    override fun processingLogic() {


    }

    companion object {

        fun newIntent(context: Context): Intent {
            return Intent(context, SettingActivity::class.java)
        }
    }
}