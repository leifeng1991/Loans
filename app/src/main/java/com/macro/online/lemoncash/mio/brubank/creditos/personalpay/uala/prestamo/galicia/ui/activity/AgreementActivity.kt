package com.macro.online.lemoncash.mio.brubank.creditos.personalpay.uala.prestamo.galicia.ui.activity

import android.content.Context
import android.content.Intent
import com.macro.online.lemoncash.mio.brubank.creditos.personalpay.uala.prestamo.galicia.MainActivity
import com.macro.online.lemoncash.mio.brubank.creditos.personalpay.uala.prestamo.galicia.R
import com.macro.online.lemoncash.mio.brubank.creditos.personalpay.uala.prestamo.galicia.constants.AppConstants
import com.macro.online.lemoncash.mio.brubank.creditos.personalpay.uala.prestamo.galicia.databinding.ActivityAgreementBinding
import com.macro.online.lemoncash.mio.brubank.creditos.personalpay.uala.prestamo.galicia.utils.SharedPrefUtil
import com.moufans.lib_base.base.activity.BaseActivity
import com.moufans.lib_base.base.webview.WebViewActivity

class AgreementActivity : BaseActivity<ActivityAgreementBinding>() {

    override fun getDataBindingLayoutResId(): Int {
        return R.layout.activity_agreement
    }

    override fun initView() {
    }

    override fun initListener() {
        mDataBinding.mSaltTextView.setOnClickListener {
            startActivity(WebViewActivity.newIntent(this,"https://www.baidu.com/"))
        }
        mDataBinding.mAcceptTextView.setOnClickListener {
            SharedPrefUtil.put(AppConstants.IS_FIRST_LAUNCH, "=======")
            startActivity(MainActivity.newIntent(this))
            finish()
        }
    }

    override fun processingLogic() {
    }

    companion object {

        fun newIntent(context: Context): Intent {
            return Intent(context, AgreementActivity::class.java)
        }
    }
}