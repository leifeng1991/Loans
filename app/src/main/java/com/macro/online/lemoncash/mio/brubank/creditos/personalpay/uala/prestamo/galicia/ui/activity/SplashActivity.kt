package com.macro.online.lemoncash.mio.brubank.creditos.personalpay.uala.prestamo.galicia.ui.activity

import android.text.TextUtils
import androidx.lifecycle.lifecycleScope
import com.macro.online.lemoncash.mio.brubank.creditos.personalpay.uala.prestamo.galicia.MainActivity
import com.macro.online.lemoncash.mio.brubank.creditos.personalpay.uala.prestamo.galicia.R
import com.macro.online.lemoncash.mio.brubank.creditos.personalpay.uala.prestamo.galicia.constants.AppConstants
import com.macro.online.lemoncash.mio.brubank.creditos.personalpay.uala.prestamo.galicia.databinding.ActivitySplashBinding
import com.macro.online.lemoncash.mio.brubank.creditos.personalpay.uala.prestamo.galicia.utils.SharedPrefUtil
import com.moufans.lib_base.base.activity.BaseActivity
import com.moufans.lib_base.utils.StatusBarUtil
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashActivity : BaseActivity<ActivitySplashBinding>() {

    override fun getDataBindingLayoutResId(): Int {
        return R.layout.activity_splash
    }

    override fun setStatusBar() {
        StatusBarUtil.setTransparentForImageViewInFragment(this, null)
        StatusBarUtil.setLightMode(this)
    }

    override fun addHeaderView() {

    }

    override fun initView() {

    }

    override fun initListener() {
    }

    override fun processingLogic() {
        lifecycleScope.launch {
            repeat(REPEAT_TIMES) {
                delay(1000)
                if (it == REPEAT_TIMES - 1) {
                    if (TextUtils.isEmpty(SharedPrefUtil.get(AppConstants.IS_FIRST_LAUNCH, "")))
                        startActivity(AgreementActivity.newIntent(this@SplashActivity))
                    else
                    // 登录，直接跳转到首页
                        startActivity(MainActivity.newIntent(this@SplashActivity))
                    finish()
                }
            }

        }
    }

    companion object {
        private const val REPEAT_TIMES = 3
    }


}