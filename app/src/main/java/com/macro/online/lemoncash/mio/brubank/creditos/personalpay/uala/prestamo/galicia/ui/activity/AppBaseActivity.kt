package com.macro.online.lemoncash.mio.brubank.creditos.personalpay.uala.prestamo.galicia.ui.activity

import android.graphics.Color
import android.util.TypedValue
import androidx.core.view.setPadding
import androidx.databinding.ViewDataBinding
import com.macro.online.lemoncash.mio.brubank.creditos.personalpay.uala.prestamo.galicia.R
import com.moufans.lib_base.base.activity.BaseActivity
import com.moufans.lib_base.utils.StatusBarUtil

abstract class AppBaseActivity<DB : ViewDataBinding> : BaseActivity<DB>() {

    override fun addHeaderView() {
        super.addHeaderView()
        mHeaderBarDataBinding!!.mHeaderBarLayout.setBackgroundColor(Color.parseColor("#F98D4F"))
        setHeaderLeftImage(R.mipmap.app_ic_left_back)
    }

    override fun setStatusBar() {
        StatusBarUtil.setTranslucent(this)
        StatusBarUtil.setColor(this, Color.parseColor("#F98D4F"), 0)
        StatusBarUtil.setLightMode(this)
    }
}