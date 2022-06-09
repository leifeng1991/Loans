package com.macro.online.lemoncash.mio.brubank.creditos.personalpay.uala.prestamo.galicia

import android.Manifest
import android.content.Context
import android.content.Intent
import android.os.Build
import android.text.TextUtils
import android.view.View
import android.widget.ImageView
import android.widget.TabHost
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTabHost
import androidx.lifecycle.lifecycleScope
import com.base.devices.data.DeviceInfos
import com.google.gson.Gson
import com.hjq.permissions.OnPermissionCallback
import com.hjq.permissions.Permission
import com.hjq.permissions.XXPermissions
import com.macro.online.lemoncash.mio.brubank.creditos.personalpay.uala.prestamo.galicia.constants.AppConstants
import com.macro.online.lemoncash.mio.brubank.creditos.personalpay.uala.prestamo.galicia.databinding.ActivityMainBinding
import com.macro.online.lemoncash.mio.brubank.creditos.personalpay.uala.prestamo.galicia.ui.activity.LoginRegisterActivity
import com.macro.online.lemoncash.mio.brubank.creditos.personalpay.uala.prestamo.galicia.ui.bean.DeviceInfoBean
import com.macro.online.lemoncash.mio.brubank.creditos.personalpay.uala.prestamo.galicia.ui.fragment.HomeFragment
import com.macro.online.lemoncash.mio.brubank.creditos.personalpay.uala.prestamo.galicia.ui.fragment.MineFragment
import com.macro.online.lemoncash.mio.brubank.creditos.personalpay.uala.prestamo.galicia.utils.SharedPrefUtil
import com.moufans.lib_base.base.activity.BaseActivity
import com.moufans.lib_base.utils.LogUtil
import com.moufans.lib_base.utils.StatusBarUtil
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class MainActivity : BaseActivity<ActivityMainBinding>() {
    private val mFragmentList = listOf(HomeFragment::class.java, MineFragment::class.java)
    private val mTabImageList = listOf(R.drawable.selector_main_tab_home, R.drawable.selector_main_tab_mine)
    private val mTabTextList = listOf("Inicio", "Perfil")
    private var mFragmentTabHost: FragmentTabHost? = null

    override fun getDataBindingLayoutResId(): Int {
        return R.layout.activity_main
    }

    override fun addHeaderView() {

    }

    override fun setStatusBar() {
        StatusBarUtil.setTransparentForImageViewInFragment(this, null)
        StatusBarUtil.setLightMode(this)
    }

    override fun initView() {
        mFragmentTabHost = findViewById(R.id.mMainTabBottomTabHost)
        mFragmentTabHost!!.setup(this, supportFragmentManager, R.id.mMainTabContentFl)
        mFragmentTabHost!!.tabWidget?.dividerDrawable = null
        val count = mFragmentList.size
        for (i in 0 until count) {
            val tabItemView = getTabItemView(i)
            val tabSpec: TabHost.TabSpec = mFragmentTabHost!!.newTabSpec(mTabTextList[i]).setIndicator(tabItemView)
            mFragmentTabHost?.addTab(tabSpec, mFragmentList[i], null)
            tabItemView.id = i
            tabItemView.setOnClickListener { setCurrentIndex(i) }
        }
    }

    override fun initListener() {
    }

    override fun processingLogic() {
        val xp = XXPermissions.with(this)
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.R) {
            xp.permission(Manifest.permission.MANAGE_EXTERNAL_STORAGE, Manifest.permission.CAMERA,
                Manifest.permission.CALL_PHONE, Manifest.permission.READ_SMS,
                Manifest.permission.READ_PHONE_STATE, Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.READ_CONTACTS)
        } else if (Build.VERSION.SDK_INT == Build.VERSION_CODES.R) {
            xp.permission(Permission.Group.STORAGE)
            xp.permission(Manifest.permission.CAMERA, Manifest.permission.CALL_PHONE, Manifest.permission.READ_SMS,
                Manifest.permission.READ_PHONE_STATE, Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.READ_CONTACTS)
        } else {
            xp.permission(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.CAMERA, Manifest.permission.CALL_PHONE, Manifest.permission.READ_SMS,
                Manifest.permission.READ_PHONE_STATE, Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.READ_CONTACTS)
        }
        xp.request(object : OnPermissionCallback {
            override fun onGranted(permissions: List<String>, all: Boolean) {

            }

            override fun onDenied(permissions: List<String>, never: Boolean) {

            }
        })
    }

    private fun getTabItemView(index: Int): View {
        val view = View.inflate(applicationContext, R.layout.layout_main_tab_item_view, null)
        val imageView = view.findViewById<ImageView>(R.id.iv_tab_icon)
        val textView = view.findViewById<TextView>(R.id.tv_tab_title)
        // 设置值
        imageView.visibility = View.VISIBLE
        textView.visibility = View.VISIBLE
        imageView.setImageResource(mTabImageList[index])
        textView.text = mTabTextList[index]
        return view
    }

    private fun setCurrentIndex(currentIndex: Int) {
        if (mFragmentTabHost?.currentTab == currentIndex) return
        if (TextUtils.isEmpty(SharedPrefUtil.get(AppConstants.USER_TOKEN, "")))
            startActivity(LoginRegisterActivity.newIntent(this))
        else
            mFragmentTabHost?.currentTab = currentIndex
    }

    companion object {

        fun newIntent(context: Context): Intent {
            return Intent(context, MainActivity::class.java)
        }
    }
}