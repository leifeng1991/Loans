package com.macro.online.lemoncash.mio.brubank.creditos.personalpay.uala.prestamo.galicia.ui.activity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.macro.online.lemoncash.mio.brubank.creditos.personalpay.uala.prestamo.galicia.MainActivity
import com.macro.online.lemoncash.mio.brubank.creditos.personalpay.uala.prestamo.galicia.R
import com.macro.online.lemoncash.mio.brubank.creditos.personalpay.uala.prestamo.galicia.databinding.ActivityLoadingBinding
import com.macro.online.lemoncash.mio.brubank.creditos.personalpay.uala.prestamo.galicia.databinding.ActivitySplashBinding
import com.moufans.lib_base.base.activity.BaseActivity
import com.moufans.lib_base.utils.StatusBarUtil
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class LoadingActivity : BaseActivity<ActivityLoadingBinding>() {
    private var mJob: Job? = null

    override fun getDataBindingLayoutResId(): Int {
        return R.layout.activity_loading
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
        mDataBinding.apply {
            mGigTextView.setOnClickListener {
                mJob?.cancel()
                startActivity(AmountChoiceActivity.newIntent(this@LoadingActivity))
            }
        }
    }

    override fun processingLogic() {
        mJob = lifecycleScope.launch {
            repeat(REPEAT_TIMES) {
                val time = REPEAT_TIMES - it
                mDataBinding.mLoadingTimeTextView.text = "00:00:${if (time > 9) time else "0$time"}"

                if (it == REPEAT_TIMES - 1) {
                    startActivity(AmountChoiceActivity.newIntent(this@LoadingActivity))
                }

                delay(1000)
            }

        }
    }

    companion object {
        private const val REPEAT_TIMES = 60

        fun newIntent(context: Context): Intent {
            return Intent(context, LoadingActivity::class.java)
        }
    }
}