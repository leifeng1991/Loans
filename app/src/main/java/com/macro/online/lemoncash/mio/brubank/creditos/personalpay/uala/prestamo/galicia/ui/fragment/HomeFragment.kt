package com.macro.online.lemoncash.mio.brubank.creditos.personalpay.uala.prestamo.galicia.ui.fragment

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.google.gson.Gson
import com.macro.online.lemoncash.mio.brubank.creditos.personalpay.uala.prestamo.galicia.R
import com.macro.online.lemoncash.mio.brubank.creditos.personalpay.uala.prestamo.galicia.api.BaseFormDataUtils
import com.macro.online.lemoncash.mio.brubank.creditos.personalpay.uala.prestamo.galicia.api.ext.appApi
import com.macro.online.lemoncash.mio.brubank.creditos.personalpay.uala.prestamo.galicia.api.rep.HomeStatusDataBean
import com.macro.online.lemoncash.mio.brubank.creditos.personalpay.uala.prestamo.galicia.constants.AppConstants
import com.macro.online.lemoncash.mio.brubank.creditos.personalpay.uala.prestamo.galicia.databinding.FragmentNewHomeBinding
import com.macro.online.lemoncash.mio.brubank.creditos.personalpay.uala.prestamo.galicia.databinding.LayoutHomeFirstBinding
import com.macro.online.lemoncash.mio.brubank.creditos.personalpay.uala.prestamo.galicia.databinding.LayoutHomeSecondBinding
import com.macro.online.lemoncash.mio.brubank.creditos.personalpay.uala.prestamo.galicia.ui.activity.AmountChoiceActivity
import com.macro.online.lemoncash.mio.brubank.creditos.personalpay.uala.prestamo.galicia.ui.activity.IdentityInformationTwoActivity
import com.macro.online.lemoncash.mio.brubank.creditos.personalpay.uala.prestamo.galicia.ui.activity.LoginRegisterActivity
import com.macro.online.lemoncash.mio.brubank.creditos.personalpay.uala.prestamo.galicia.ui.activity.PersonalInformationActivity
import com.macro.online.lemoncash.mio.brubank.creditos.personalpay.uala.prestamo.galicia.ui.adapter.HomeCardAdapter
import com.macro.online.lemoncash.mio.brubank.creditos.personalpay.uala.prestamo.galicia.utils.CallUtils
import com.macro.online.lemoncash.mio.brubank.creditos.personalpay.uala.prestamo.galicia.utils.CommonDialogUtil
import com.macro.online.lemoncash.mio.brubank.creditos.personalpay.uala.prestamo.galicia.utils.SharedPrefUtil
import com.macro.online.lemoncash.mio.brubank.creditos.personalpay.uala.prestamo.galicia.utils.StringUtil
import com.moufans.lib_base.base.fragment.HintRefreshFragment
import com.moufans.lib_base.ext.convertReqExecute
import com.moufans.lib_base.utils.span.AndroidSpan
import kotlinx.coroutines.launch


class HomeFragment : HintRefreshFragment<FragmentNewHomeBinding>() {
    private var mHomeStatusDataBean: HomeStatusDataBean? = null
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
                when (homeStatusDataBean.compressedEasternPassengerMetalSnow) {
                    -1 -> {
                        statusOne(homeStatusDataBean, -1)
                    }
                    0 -> {
                        statusOne(homeStatusDataBean, 1)
                    }
                    1 -> {
                        statusOne(homeStatusDataBean, 2)
                    }
                    else -> {
                        statusOne(homeStatusDataBean, 0)
                    }
                }

            }
            0 -> {
                statusTwo(2, homeStatusDataBean)
            }
            1 -> {
                statusTwo(3, homeStatusDataBean)
            }
            2 -> {
                if (homeStatusDataBean.scientificFlatTrickGrowth == 0) {
                    statusTwo(0, homeStatusDataBean)
                }
                if (homeStatusDataBean.scientificFlatTrickGrowth == 2) {
                    statusTwo(1, homeStatusDataBean)
                }
                if (homeStatusDataBean.scientificFlatTrickGrowth == 4) {
                    statusTwo(4, homeStatusDataBean)
                }

            }
            3 -> {
                statusTwo(5, homeStatusDataBean)
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
            mMoneyTextView.text = if (type == -1) "$27,000" else "$${StringUtil.parseMoneyZero(homeStatusDataBean.unfitTermCleverHat)}"
            mCardMoneyTextView.text = if (type == -1) "$27,000" else "$${StringUtil.parseMoneyZero(homeStatusDataBean.unfitTermCleverHat)}"
            if (type == 1 || type == 2) {
                mLineView2.isSelected = true
                mLineView1.helper.apply {
                    setCornerRadius(50f, 0f, 0f, 50f)
                }
                mFirstTextView.visibility = View.GONE
            }

            mHomeCarItemRecyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            val list = mutableListOf<String>().apply {
                if (type == -1 || type == 0) {
                    add("0")
                    add("1")
                    add("2")
                } else if (type == 1) {
                    add("3")
                    add("4")
                } else if (type == 2) {
                    mTitleNameTextView.text = "¿Por qué elegir el Préstamo mío?"
                    add("5")
                    add("3")
                    add("4")
                }
            }
            mHomeCarItemRecyclerView.adapter = mHomeCardAdapter
            mHomeCardAdapter.setList(list)
            // 客服电话
            mCallImageView.setOnClickListener {
                CallUtils.showCallDialog(requireActivity() as AppCompatActivity)
            }
            // 申请贷款
            mApplyLayout.setOnClickListener {
                if (TextUtils.isEmpty(SharedPrefUtil.get(AppConstants.USER_TOKEN, "")))
                    startActivity(LoginRegisterActivity.newIntent(requireContext()))
                else
                    startActivity(IdentityInformationTwoActivity.newIntent(requireContext()))
            }

            getBanner(mBannerView)

        }


    }

    private fun getBanner(mBannerView: ImageView) {
        if (!TextUtils.isEmpty(SharedPrefUtil.get(AppConstants.USER_TOKEN, ""))) {
            val hashMap: HashMap<String, String> = BaseFormDataUtils.getBaseHasMap(1)
            lifecycleScope.launch {
                convertReqExecute({ appApi.homeBanner(hashMap) }, onSuccess = {
                    if (!TextUtils.isEmpty(it.unpleasantCentigradeBee)) {
                        Glide.with(this@HomeFragment).load(it.unpleasantCentigradeBee).into(mBannerView)
                        mBannerView.visibility = View.VISIBLE
                    } else {
                        mBannerView.visibility = View.GONE
                    }

                    mBannerView.setOnClickListener {
                        CommonDialogUtil.showRecommendOtherProductsDialog(requireActivity())
                    }
                }, onFailure = null)
            }

        }
    }

    /**
     * @param type 0:等待审核 1：处理中 2:还款中 3：延期还款 4：放款失败 5:拒绝放款
     */
    private fun statusTwo(type: Int = 0, bean: HomeStatusDataBean) {
        mHomeStatusDataBean = bean
        mFragmentDataBinding.mRootLayout.removeAllViews()
        val mView = LayoutInflater.from(requireContext()).inflate(R.layout.layout_home_second, null)
        mFragmentDataBinding.mRootLayout.addView(mView)
        val dataBinding = DataBindingUtil.bind<LayoutHomeSecondBinding>(mView)
        dataBinding?.apply {
            // 客服电话
            mCallImageView.setOnClickListener {
                CallUtils.showCallDialog(requireActivity() as AppCompatActivity)
            }
            mAwaitTextView.isSelected = (type == 0 || type == 1)
            mAwaitImageView.setImageResource(if (type == 0 || type == 1) R.mipmap.ic_await_checked else R.mipmap.ic_await_normal)
            mInPaymentTextView.isSelected = type == 2
            mInPaymentImageView.setImageResource(if (type == 2) R.mipmap.ic_in_payment_checked else R.mipmap.ic_in_payment_normal)
            mDeferredPaymentTextView.isSelected = type == 3
            mDeferredPaymentImageView.setImageResource(if (type == 3) R.mipmap.ic_deferred_payment_checked else R.mipmap.ic_deferred_payment_normal)
            mRefusedLendTextView.isSelected = (type == 4 || type == 5)
            mRefusedLendImageView.setImageResource(if (type == 4 || type == 5) R.mipmap.ic_refused_lend_checked else R.mipmap.ic_refused_lend_normal)

            when (type) {
                0 -> {
                    mApplyLayout.visibility = View.VISIBLE
                    mFireImageView.visibility = View.VISIBLE
                    mWaitReviewLayout.visibility = View.VISIBLE
                    // 申请金额
                    mApplyMoneyTextView.text = AndroidSpan().drawRelativeSize("$", 0.625f).drawCommonSpan(StringUtil.parseMoney(bean.unfitTermCleverHat)).spanText
                    // 申请时间
                    mApplyTimeTextView.text = "Fecha de aplicación\n${bean.mexicanEventRepairsPrize}"

                }
                1 -> {
                    mApplyLayout.visibility = View.VISIBLE
                    mInHandLayout.visibility = View.VISIBLE
                    // 申请金额
                    mApplyMoneyTextView.text = AndroidSpan().drawRelativeSize("$", 0.625f).drawCommonSpan(StringUtil.parseMoney(bean.unfitTermCleverHat)).spanText
                    // 申请时间
                    mApplyTimeTextView.text = "Fecha de aplicación\n${bean.mexicanEventRepairsPrize}"
                    getBanner(mImageViewBanner)
                }
                2 -> {
                    mApplyLayout.visibility = View.VISIBLE
                    mApplyBottomLayout.visibility = View.VISIBLE
                    mInPaymentLayout.visibility = View.VISIBLE
                    mHbFirstTopBgView.visibility = View.GONE
                    mHbTwoTopBgView.visibility = View.GONE
                    mHbFirstLockImageView.visibility = View.GONE
                    mHbSecondLockImageView.visibility = View.GONE
                    mMonDeDeTextView.text = ""
                    mApplyMoneyTextView.text = AndroidSpan().drawRelativeSize("$", 0.625f).drawCommonSpan(StringUtil.parseMoney(bean.unfitTermCleverHat)).spanText
                    mApplyTimeTextView.text = "Fecha de pago de tu\n${bean.contentTeacherHappyGallon}"
                    mFecDePaTextView.text = "${bean.contentTeacherHappyGallon}"
                    mMonDePreTextView.text = AndroidSpan().drawCommonSpan("$").drawCommonSpan(StringUtil.parseMoney(bean.unfitTermCleverHat)).spanText

                    mDevYaTextView.setOnClickListener {
                        mNestedScroollView.smoothScrollBy(0, 100000)
                    }

                    mProLonTextView.setOnClickListener {
                        banLatestBride()
                    }
                }
                3 -> {
                    mApplyLayout.visibility = View.VISIBLE
                    mApplyBottomLayout.visibility = View.VISIBLE
                    mInPaymentLayout.visibility = View.VISIBLE
                    mRepaymentLayout.visibility = View.VISIBLE
                    mHbFirstTopBgView.visibility = View.GONE
                    mHbTwoTopBgView.visibility = View.GONE
                    mHbFirstLockImageView.visibility = View.GONE
                    mHbSecondLockImageView.visibility = View.GONE
                    mDeferredPaymentTextView.text = "Atrasado\n${bean.blueFriedClinic} Días"
                    mApplyMoneyTextView.text = AndroidSpan().drawRelativeSize("$", 0.625f).drawCommonSpan(StringUtil.parseMoney(bean.unfitTermCleverHat)).spanText
                    mApplyTimeTextView.text = "Fecha de pago de tu crédito\n${bean.contentTeacherHappyGallon}"
                    mDevYaTextView.text = "Pago la deuda ya"
                    mJgContentTextView.text = AndroidSpan().drawCommonSpan("Tu crédito tiene ").drawForegroundColor("${bean.blueFriedClinic} Días", Color.parseColor("#F98D4F"))
                        .drawCommonSpan(" vencido, pague de inmediato. Si hay una situación de vencimiento maliciosa, presentaremos una denuncia en su contra a través de los canales legales.").spanText

                    mDevYaTextView.setOnClickListener {
                        mNestedScroollView.smoothScrollBy(0, 100000)
                    }

                    mProLonTextView.setOnClickListener {
                        banLatestBride()
                    }
                }
                4 -> {
                    mFailedLayout.visibility = View.VISIBLE
                    getBanner(mFailedBannerImageView)
                    mFailedActTextView.setOnClickListener {
                        startActivity(IdentityInformationTwoActivity.newIntent(requireContext(), 1))
                    }
                }
                5 -> {
                    mFailedLayout.visibility = View.VISIBLE
                    mFailedActTextView.visibility = View.GONE
                    mFailedTipTextView.text = "Solicitud rechazada"
                    mLosContentTextView.text = "El sistema ha detectado que la información de su cuenta bancaria no se ha completado correctamente, cárguela de nuevo."
                    getBanner(mFailedBannerImageView)
                }
            }
        }
    }

    private fun banLatestBride() {
        if (mHomeStatusDataBean == null)
            return
        lifecycleScope.launch {
            val hashMap: HashMap<String, String> = BaseFormDataUtils.getBaseHasMap()
            hashMap["popularMessyLeague"] = mHomeStatusDataBean!!.popularMessyLeague ?: ""
//            hashMap["recentCoralChemicalGoldenElder"] = mHomeStatusDataBean!!.recentCoralChemicalGoldenElder ?: ""
            convertReqExecute({ appApi.banLatestBride(hashMap) }, onSuccess = {
                // 弹框
                CommonDialogUtil.showDeferredPaymentDialog(requireActivity())
            })
        }
    }


    companion object {

        fun newInstance() = HomeFragment().apply { arguments = Bundle().apply {} }
    }
}
