package com.macro.online.lemoncash.mio.brubank.creditos.personalpay.uala.prestamo.galicia.ui.fragment

import android.os.Bundle
import com.macro.online.lemoncash.mio.brubank.creditos.personalpay.uala.prestamo.galicia.R
import com.macro.online.lemoncash.mio.brubank.creditos.personalpay.uala.prestamo.galicia.constants.AppConstants
import com.macro.online.lemoncash.mio.brubank.creditos.personalpay.uala.prestamo.galicia.databinding.FragmentMineBinding
import com.macro.online.lemoncash.mio.brubank.creditos.personalpay.uala.prestamo.galicia.ui.activity.SettingActivity
import com.macro.online.lemoncash.mio.brubank.creditos.personalpay.uala.prestamo.galicia.utils.CallUtils
import com.macro.online.lemoncash.mio.brubank.creditos.personalpay.uala.prestamo.galicia.utils.SharedPrefUtil
import com.moufans.lib_base.base.fragment.ViewPageFragment
import com.moufans.lib_base.base.webview.WebViewActivity


class MineFragment : ViewPageFragment<FragmentMineBinding>() {

    override fun getLayoutId(): Int {
        return R.layout.fragment_mine
    }

    override fun initView() {
    }

    override fun initListener() {
        mFragmentDataBinding.apply {
            mLogoutTextView.setOnClickListener {
                SharedPrefUtil.put(AppConstants.USER_ID, "")
                SharedPrefUtil.put(AppConstants.USER_TOKEN, "")
            }
            // 设置
            mAjuLayout.setOnClickListener {
                startActivity(SettingActivity.newIntent(requireContext()))
            }
            // 隐私协议
            mAviDeLayout.setOnClickListener {
                startActivity(WebViewActivity.newIntent(requireContext(), ""))
            }
            mServLayout.setOnClickListener {
                CallUtils.showCallDialog(requireActivity())
            }
        }
    }

    override fun processingLogic() {
    }

    override fun refreshOnceData() {
    }

    companion object {

        fun newInstance() = MineFragment().apply { arguments = Bundle().apply {} }
    }
}
