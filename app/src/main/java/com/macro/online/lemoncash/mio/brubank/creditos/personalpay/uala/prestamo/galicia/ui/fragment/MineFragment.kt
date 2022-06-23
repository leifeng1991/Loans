package com.macro.online.lemoncash.mio.brubank.creditos.personalpay.uala.prestamo.galicia.ui.fragment

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.google.gson.Gson
import com.macro.online.lemoncash.mio.brubank.creditos.personalpay.uala.prestamo.galicia.MainActivity
import com.macro.online.lemoncash.mio.brubank.creditos.personalpay.uala.prestamo.galicia.R
import com.macro.online.lemoncash.mio.brubank.creditos.personalpay.uala.prestamo.galicia.api.BaseFormDataUtils
import com.macro.online.lemoncash.mio.brubank.creditos.personalpay.uala.prestamo.galicia.api.ext.appApi
import com.macro.online.lemoncash.mio.brubank.creditos.personalpay.uala.prestamo.galicia.constants.AppConstants
import com.macro.online.lemoncash.mio.brubank.creditos.personalpay.uala.prestamo.galicia.databinding.FragmentMineBinding
import com.macro.online.lemoncash.mio.brubank.creditos.personalpay.uala.prestamo.galicia.ui.activity.SettingActivity
import com.macro.online.lemoncash.mio.brubank.creditos.personalpay.uala.prestamo.galicia.utils.CallUtils
import com.macro.online.lemoncash.mio.brubank.creditos.personalpay.uala.prestamo.galicia.utils.SharedPrefUtil
import com.moufans.lib_base.base.fragment.ViewPageFragment
import com.moufans.lib_base.base.webview.WebViewActivity
import com.moufans.lib_base.ext.convertReqExecute
import com.moufans.lib_base.utils.ImageLoader
import kotlinx.coroutines.launch


class MineFragment : ViewPageFragment<FragmentMineBinding>() {

    override fun getLayoutId(): Int {
        return R.layout.fragment_mine
    }

    override fun initView() {
        mFragmentDataBinding.mMinePhoneTextView.text = "+54 ${SharedPrefUtil.get(AppConstants.USER_PHONE, "")}"
    }

    override fun initListener() {
        mFragmentDataBinding.apply {
            mLogoutTextView.setOnClickListener {
                SharedPrefUtil.put(AppConstants.USER_ID, "")
                SharedPrefUtil.put(AppConstants.USER_TOKEN, "")
                SharedPrefUtil.put(AppConstants.USER_PHONE, "")
                requireActivity().finish()
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
                CallUtils.showCallDialog(requireActivity() as AppCompatActivity)
            }
        }
    }

    override fun processingLogic() {
    }

    override fun refreshOnceData() {
    }

    override fun onResume() {
        super.onResume()
        begRichBit()
    }

    private fun begRichBit() {
        lifecycleScope.launch {
            val hashMap: HashMap<String, String> = BaseFormDataUtils.getBaseHasMap()
            convertReqExecute({ appApi.begRichBit(hashMap) }, onSuccess = {
                mFragmentDataBinding.apply {
                    mMineNameTextView.text = it.names
                    ImageLoader.setImage(it.enjoyableAbleDrunkSalesgirl, mMineIconImageView)
                }
            })
        }
    }

    companion object {

        fun newInstance() = MineFragment().apply { arguments = Bundle().apply {} }
    }
}
