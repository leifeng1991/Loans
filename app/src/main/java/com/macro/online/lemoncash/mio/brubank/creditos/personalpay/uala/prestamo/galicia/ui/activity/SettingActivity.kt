package com.macro.online.lemoncash.mio.brubank.creditos.personalpay.uala.prestamo.galicia.ui.activity

import android.content.Context
import android.content.Intent
import com.macro.online.lemoncash.mio.brubank.creditos.personalpay.uala.prestamo.galicia.R
import com.macro.online.lemoncash.mio.brubank.creditos.personalpay.uala.prestamo.galicia.databinding.ActivitySettingBinding
import com.macro.online.lemoncash.mio.brubank.creditos.personalpay.uala.prestamo.galicia.utils.CallUtils

class SettingActivity : AppBaseActivity<ActivitySettingBinding>() {

    override fun getDataBindingLayoutResId(): Int {
        return R.layout.activity_setting
    }

    override fun initView() {
        setHeaderTitle("Información personal")
        mDataBinding.mEnglishTextView.isSelected = true
    }

    override fun initListener() {
        setHeaderRightRightImage(R.mipmap.ic_white_phone) { CallUtils.showCallDialog(this@SettingActivity) }
    }

    override fun processingLogic() {
    }

    companion object {

        fun newIntent(context: Context): Intent {
            return Intent(context, SettingActivity::class.java)
        }
    }
}