package com.macro.online.lemoncash.mio.brubank.creditos.personalpay.uala.prestamo.galicia.ui.activity

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import androidx.lifecycle.lifecycleScope
import com.macro.online.lemoncash.mio.brubank.creditos.personalpay.uala.prestamo.galicia.MainActivity
import com.macro.online.lemoncash.mio.brubank.creditos.personalpay.uala.prestamo.galicia.R
import com.macro.online.lemoncash.mio.brubank.creditos.personalpay.uala.prestamo.galicia.api.BaseFormDataUtils
import com.macro.online.lemoncash.mio.brubank.creditos.personalpay.uala.prestamo.galicia.api.ext.appApi
import com.macro.online.lemoncash.mio.brubank.creditos.personalpay.uala.prestamo.galicia.constants.AppConstants
import com.macro.online.lemoncash.mio.brubank.creditos.personalpay.uala.prestamo.galicia.databinding.ActivityLoginRegisterBinding
import com.macro.online.lemoncash.mio.brubank.creditos.personalpay.uala.prestamo.galicia.utils.SharedPrefUtil
import com.moufans.lib_base.base.activity.BaseActivity
import com.moufans.lib_base.ext.convertReqExecute
import com.moufans.lib_base.ext.rawReqExecute
import com.moufans.lib_base.utils.StatusBarUtil
import com.moufans.lib_base.utils.ToastUtil
import com.moufans.lib_base.utils.span.AndroidSpan
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class LoginRegisterActivity : BaseActivity<ActivityLoginRegisterBinding>(), TextWatcher {

    override fun getDataBindingLayoutResId(): Int {
        return R.layout.activity_login_register
    }

    override fun setStatusBar() {
        StatusBarUtil.setTransparentForImageViewInFragment(this, null)
        StatusBarUtil.setLightMode(this)
    }

    override fun addHeaderView() {

    }

    override fun initView() {
        mDataBinding.apply {
            mGetVerCodeTextView.isEnabled = false
            mLoginTextView.isEnabled = false
        }
    }

    override fun initListener() {
        mDataBinding.apply {
            mBackImageView.setOnClickListener {
                finish()
            }
            mGetVerCodeTextView.setOnClickListener {
                getCode()
            }
            mLoginTextView.setOnClickListener {
                login()
            }

            mPhoneEditTextView.addTextChangedListener(this@LoginRegisterActivity)
            mVerCodeEditTextView.addTextChangedListener(this@LoginRegisterActivity)
        }
    }

    override fun processingLogic() {
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        setButtonStatus()
    }

    override fun afterTextChanged(s: Editable?) {

    }

    private fun setButtonStatus() {
        val mPhone = mDataBinding.mPhoneEditTextView.text.toString().trim()
        val mCode = mDataBinding.mVerCodeEditTextView.text.toString().trim()
        mDataBinding.mLoginTextView.isEnabled = !TextUtils.isEmpty(mPhone) && mPhone.length >= 10 && !TextUtils.isEmpty(mCode) && mCode.length >= 4
        mDataBinding.mLoginTextView.isSelected = !TextUtils.isEmpty(mPhone) && mPhone.length >= 10 && !TextUtils.isEmpty(mCode) && mCode.length >= 4

        mDataBinding.mGetVerCodeTextView.isEnabled = !TextUtils.isEmpty(mPhone) && mPhone.length >= 10
        mDataBinding.mGetVerCodeTextView.isSelected = !TextUtils.isEmpty(mPhone) && mPhone.length >= 10
    }

    private fun getCode() {
        val mPhone = mDataBinding.mPhoneEditTextView.text.toString().trim()
        if (TextUtils.isEmpty(mPhone) || mPhone.length < 10) {
            ToastUtil.showShort("El número de teléfono es anormal, vuelva a ingresar")
            return
        }

        val hashMap: HashMap<String, String> = BaseFormDataUtils.getBaseHasMap()
//        hashMap["followingCanBentPoorAunt"] = "1820100008"
        hashMap["followingCanBentPoorAunt"] = mPhone

        lifecycleScope.launch {
            convertReqExecute({ appApi.getVerifyCode(hashMap) }, onSuccess = {
                lifecycleScope.launch {
                    repeat(60) {
                        if (it == 59) {
                            setButtonStatus()
                            mDataBinding.mGetVerCodeTextView.text = "Volver a adquirir"
                        } else {
                            mDataBinding.mGetVerCodeTextView.text = AndroidSpan().drawCommonSpan("Código de reserva en").drawForegroundColor(" ${59 - it}", Color.parseColor("#FE9647")).spanText
                            delay(1000)
                        }

                    }
                }
            }, onFailure = null, this@LoginRegisterActivity)
        }
    }

    private fun login() {
        val mPhone = mDataBinding.mPhoneEditTextView.text.toString().trim()
        if (TextUtils.isEmpty(mPhone) || mPhone.length < 10) {
            ToastUtil.showShort("El número de teléfono es anormal, vuelva a ingresar")
            return
        }

        val mCode = mDataBinding.mVerCodeEditTextView.text.toString().trim()
        if (TextUtils.isEmpty(mCode) || mCode.length < 4) {
            ToastUtil.showShort("El código de verificación no coincide, vuelva a ingresarlo")
            return
        }

        val hashMap: HashMap<String, String> = BaseFormDataUtils.getBaseHasMap(1)
        hashMap["followingCanBentPoorAunt"] = mPhone
        hashMap["contraryCleanerStationSpade"] = mCode

        lifecycleScope.launch {
            convertReqExecute({ appApi.login(hashMap) }, onSuccess = {
                SharedPrefUtil.put(AppConstants.USER_TOKEN, it.mercifulAircraftAfricanHoneyDailySoil)
                SharedPrefUtil.put(AppConstants.USER_ID, it.safeContinentGreetingDeepSatisfaction)
                SharedPrefUtil.put(AppConstants.USER_PHONE, mPhone)
                finish()
            }, onFailure = null, this@LoginRegisterActivity)

        }
    }

    companion object {

        fun newIntent(context: Context): Intent {
            return Intent(context, LoginRegisterActivity::class.java)
        }
    }


}