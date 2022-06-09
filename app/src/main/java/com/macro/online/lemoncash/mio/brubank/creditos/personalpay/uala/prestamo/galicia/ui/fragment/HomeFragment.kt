package com.macro.online.lemoncash.mio.brubank.creditos.personalpay.uala.prestamo.galicia.ui.fragment

import android.Manifest
import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.hjq.permissions.OnPermissionCallback
import com.hjq.permissions.XXPermissions
import com.macro.online.lemoncash.mio.brubank.creditos.personalpay.uala.prestamo.galicia.R
import com.macro.online.lemoncash.mio.brubank.creditos.personalpay.uala.prestamo.galicia.api.BaseFormDataUtils
import com.macro.online.lemoncash.mio.brubank.creditos.personalpay.uala.prestamo.galicia.api.ext.appApi
import com.macro.online.lemoncash.mio.brubank.creditos.personalpay.uala.prestamo.galicia.api.rep.HomeStatusDataBean
import com.macro.online.lemoncash.mio.brubank.creditos.personalpay.uala.prestamo.galicia.constants.AppConstants
import com.macro.online.lemoncash.mio.brubank.creditos.personalpay.uala.prestamo.galicia.databinding.FragmentNewHomeBinding
import com.macro.online.lemoncash.mio.brubank.creditos.personalpay.uala.prestamo.galicia.databinding.LayoutHomeFirstBinding
import com.macro.online.lemoncash.mio.brubank.creditos.personalpay.uala.prestamo.galicia.databinding.LayoutHomeSecondBinding
import com.macro.online.lemoncash.mio.brubank.creditos.personalpay.uala.prestamo.galicia.ui.activity.*
import com.macro.online.lemoncash.mio.brubank.creditos.personalpay.uala.prestamo.galicia.ui.adapter.HomeCardAdapter
import com.macro.online.lemoncash.mio.brubank.creditos.personalpay.uala.prestamo.galicia.utils.CallUtils
import com.macro.online.lemoncash.mio.brubank.creditos.personalpay.uala.prestamo.galicia.utils.CommonDialogUtil
import com.macro.online.lemoncash.mio.brubank.creditos.personalpay.uala.prestamo.galicia.utils.SharedPrefUtil
import com.moufans.lib_base.base.fragment.HintRefreshFragment
import com.moufans.lib_base.ext.convertReqExecute
import com.moufans.lib_base.utils.ImageLoader
import com.moufans.lib_base.utils.LogUtil
import com.moufans.lib_base.utils.ToastUtil
import com.moufans.lib_base.utils.span.AndroidSpan
import kotlinx.coroutines.launch


class HomeFragment : HintRefreshFragment<FragmentNewHomeBinding>() {
    private val mHomeCardAdapter by lazy {
        HomeCardAdapter()
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_new_home
    }

    override fun initView() {

    }

    override fun initListener() {
    }

    override fun processingLogic() {

    }

    override fun onResume() {
        super.onResume()
        homeStatus()

    }

    override fun hintRefreshData() {

    }

    private fun homeStatus() {
        val hashMap: HashMap<String, String> = BaseFormDataUtils.getBaseHasMap(1)
        lifecycleScope.launch {
            convertReqExecute({ appApi.homeStatus(hashMap) }, onSuccess = {
                setLayoutByStatus(it)
            }, onFailure = null, this@HomeFragment)

        }
    }

    @SuppressLint("SetTextI18n")
    private fun setLayoutByStatus(homeStatusDataBean: HomeStatusDataBean) {

        when (homeStatusDataBean.harmfulGreengrocerScissorsJustCarriage) {
            -1 -> {
                statusOne(homeStatusDataBean)
            }
            0 -> {

            }
            1 -> {

            }
            2 -> {
                statusTwo(0, homeStatusDataBean)
            }
            3 -> {

            }
        }
    }

    /**
     * @param type -1:未登录 0:正常 1：首贷 2：复贷
     */
    private fun statusOne(homeStatusDataBean: HomeStatusDataBean, type: Int = 0) {
        mFragmentDataBinding.mRootLayout.removeAllViews()
        val mView = LayoutInflater.from(requireContext()).inflate(R.layout.layout_home_first, null)
        mFragmentDataBinding.mRootLayout.addView(mView)
        val dataBinding = DataBindingUtil.bind<LayoutHomeFirstBinding>(mView)
        dataBinding?.apply {
            mMoneyTextView.text = if (type == -1) "$27,000" else "$${homeStatusDataBean.unfitTermCleverHat}"
            mCardMoneyTextView.text = if (type == -1) "$27,000" else "$${homeStatusDataBean.unfitTermCleverHat}"
            if (type == 1 || type == 2) {
                mLineView2.isSelected = true
                mLineView1.helper.apply {
                    setCornerRadius(50f, 0f, 0f, 50f)
                }
                mFirstTextView.visibility = View.GONE
            }

            mHomeCarItemRecyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            val list = mutableListOf<String>().apply {
                if (type == -1 || type == 0 || type == 2) {
                    add("0")
                    add("1")
                    add("2")
                } else if (type == 1) {
                    add("3")
                    add("4")
                }
            }
            mHomeCarItemRecyclerView.adapter = mHomeCardAdapter
            mHomeCardAdapter.setList(list)
            // 客服电话
            mCallImageView.setOnClickListener {
                CallUtils.showCallDialog(requireActivity())
            }
            // 申请贷款
            mApplyLayout.setOnClickListener {
                if (TextUtils.isEmpty(SharedPrefUtil.get(AppConstants.USER_TOKEN, "")))
                    startActivity(LoginRegisterActivity.newIntent(requireContext()))
                else
                    startActivity(AmountChoiceActivity.newIntent(requireContext()))
            }
            // banner
            mBannerView.setOnClickListener {

            }
        }

        if (!TextUtils.isEmpty(SharedPrefUtil.get(AppConstants.USER_TOKEN, ""))) {
            val hashMap: HashMap<String, String> = BaseFormDataUtils.getBaseHasMap(1)
            lifecycleScope.launch {
                convertReqExecute({ appApi.homeBanner(hashMap) }, onSuccess = {
                    if (!TextUtils.isEmpty(it.unpleasantCentigradeBee)) {
                        dataBinding?.apply {
                            Glide.with(this@HomeFragment).load(it.unpleasantCentigradeBee).into(mBannerView)
                            mBannerView.visibility = View.VISIBLE
                        }
                    } else {
                        dataBinding?.mBannerView?.visibility = View.GONE
                    }
                }, onFailure = null)
            }

        }
    }

    /**
     * @param type 0:等待审核 1：处理中 2:还款中 3：延期还款 4：放款失败 5:拒绝放款
     */
    private fun statusTwo(type: Int = 0, bean: HomeStatusDataBean) {
        mFragmentDataBinding.mRootLayout.removeAllViews()
        val mView = LayoutInflater.from(requireContext()).inflate(R.layout.layout_home_second, null)
        mFragmentDataBinding.mRootLayout.addView(mView)
        val dataBinding = DataBindingUtil.bind<LayoutHomeSecondBinding>(mView)
        dataBinding?.apply {
            mAwaitTextView.isSelected = (type == 0 || type == 1)
            mInPaymentTextView.isSelected = type == 2
            mDeferredPaymentTextView.isSelected = type == 3
            mRefusedLendTextView.isSelected = (type == 4 || type == 5)

            when (type) {
                0 -> {
                    mApplyLayout.visibility = View.VISIBLE
                    mFireImageView.visibility = View.VISIBLE
                    mWaitReviewLayout.visibility = View.VISIBLE
                    // 申请金额
                    mApplyMoneyTextView.text = AndroidSpan().drawRelativeSize("$", 0.625f).drawCommonSpan(bean.leadingLuckRepairs).spanText
                    // 申请时间
                    mApplyTimeTextView.text = "Fecha de aplicación\n${bean.mexicanEventRepairsPrize}"

                }
                1 -> {
                    mApplyLayout.visibility = View.VISIBLE
                    mInHandLayout.visibility = View.VISIBLE
                }
                2 -> {
                    mApplyLayout.visibility = View.VISIBLE
                    mApplyBottomLayout.visibility = View.VISIBLE
                    mInPaymentLayout.visibility = View.VISIBLE
                    mHbFirstTopBgView.visibility = View.GONE
                    mHbFirstLockImageView.visibility = View.GONE
                }
                3 -> {
                    mApplyLayout.visibility = View.VISIBLE
                    mApplyBottomLayout.visibility = View.VISIBLE
                    mInPaymentLayout.visibility = View.VISIBLE
                    mRepaymentLayout.visibility = View.VISIBLE
                    mHbFirstTopBgView.visibility = View.GONE
                    mHbFirstLockImageView.visibility = View.GONE
                }
                4 -> {
                    mFailedLayout.visibility = View.VISIBLE
                }
                5 -> {
                    mFailedLayout.visibility = View.VISIBLE
                    mFailedActTextView.visibility = View.GONE
                    mFailedTipTextView.text = "Solicitud rechazada"
                    mLosContentTextView.text = "El sistema ha detectado que la información de su cuenta bancaria no se ha completado correctamente, cárguela de nuevo."
                }
            }
        }
    }


    companion object {

        fun newInstance() = HomeFragment().apply { arguments = Bundle().apply {} }
    }
}
