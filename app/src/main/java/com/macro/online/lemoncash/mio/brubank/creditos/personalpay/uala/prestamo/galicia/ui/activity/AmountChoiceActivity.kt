package com.macro.online.lemoncash.mio.brubank.creditos.personalpay.uala.prestamo.galicia.ui.activity

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.text.format.DateUtils
import android.view.View
import androidx.lifecycle.lifecycleScope
import com.github.gzuliyujiang.dialog.CornerRound
import com.github.gzuliyujiang.wheelpicker.OptionPicker
import com.google.gson.Gson
import com.macro.online.lemoncash.mio.brubank.creditos.personalpay.uala.prestamo.galicia.R
import com.macro.online.lemoncash.mio.brubank.creditos.personalpay.uala.prestamo.galicia.api.BaseFormDataUtils
import com.macro.online.lemoncash.mio.brubank.creditos.personalpay.uala.prestamo.galicia.api.ext.appApi
import com.macro.online.lemoncash.mio.brubank.creditos.personalpay.uala.prestamo.galicia.api.rep.MaskSouthShadowDataBean
import com.macro.online.lemoncash.mio.brubank.creditos.personalpay.uala.prestamo.galicia.databinding.ActivityAmountChoiceBinding
import com.macro.online.lemoncash.mio.brubank.creditos.personalpay.uala.prestamo.galicia.ui.bean.DeviceInfoBean
import com.macro.online.lemoncash.mio.brubank.creditos.personalpay.uala.prestamo.galicia.utils.CallUtils
import com.macro.online.lemoncash.mio.brubank.creditos.personalpay.uala.prestamo.galicia.utils.CommonDialogUtil
import com.macro.online.lemoncash.mio.brubank.creditos.personalpay.uala.prestamo.galicia.utils.StringUtil
import com.macro.online.lemoncash.mio.brubank.creditos.personalpay.uala.prestamo.galicia.utils.StringUtils
import com.moufans.lib_base.base.activity.BaseActivity
import com.moufans.lib_base.ext.convertReqExecute
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody

class AmountChoiceActivity : AppBaseActivity<ActivityAmountChoiceBinding>() {
    private var mMoneyPicker: OptionPicker? = null
    private var mMaskSouthShadowDataBean: MaskSouthShadowDataBean? = null
    private var mSorryAngryScreenBean: MaskSouthShadowDataBean.SorryAngryScreenBean? = null

    override fun getDataBindingLayoutResId(): Int {
        return R.layout.activity_amount_choice
    }

    override fun initView() {
        setHeaderTitle("App_Name")
        mDataBinding.mDayOneTextView.isSelected = true
    }

    override fun initListener() {
        setHeaderRightRightImage(R.mipmap.ic_white_phone) { CallUtils.showCallDialog(this@AmountChoiceActivity) }
        mDataBinding.apply {
            mConfTextView.setOnClickListener {
                CommonDialogUtil.showConfirmAmountDialog(this@AmountChoiceActivity)
            }
            mSelectPriceLayout.setOnClickListener {
                showMoneyPicker()
            }
        }
    }

    override fun processingLogic() {
        maskSouthShadow()
    }

    private fun maskSouthShadow() {
        lifecycleScope.launch {
            val hashMap: HashMap<String, String> = BaseFormDataUtils.getBaseHasMap()
            convertReqExecute({ appApi.maskSouthShadow(hashMap) }, onSuccess = {
                mMaskSouthShadowDataBean = it
                if (mMaskSouthShadowDataBean == null) return@convertReqExecute
                val day = mMaskSouthShadowDataBean!!.fatAfternoonDangerousPublicThread?.split(" ")
                mDataBinding.mDayOneTextView.text = if ((day?.size ?: 0) > 0) day?.get(0) else ""
                mDataBinding.mDayTwoTextView.text = if ((day?.size ?: 0) > 0) day?.get(0) else ""
                mDataBinding.mDayThreeTextView.text = if ((day?.size ?: 0) > 0) day?.get(0) else ""
                if (mMaskSouthShadowDataBean == null || (mMaskSouthShadowDataBean!!.sorryAngryScreen?.size ?: 0) <= 0) return@convertReqExecute
                mSorryAngryScreenBean = mMaskSouthShadowDataBean!!.sorryAngryScreen!![0]
                mDataBinding.mMoneyTextView.text = "$${StringUtil.parseMoney(mSorryAngryScreenBean!!.naturalSuggestionRadioactiveSunnyIce.toString())}"

                dreamPhysicalRelationship()
            }, baseView = this@AmountChoiceActivity)


        }
    }

    @SuppressLint("SetTextI18n")
    private fun dreamPhysicalRelationship() {
        lifecycleScope.launch {
            val hashMap: HashMap<String, String> = BaseFormDataUtils.getBaseHasMap()
            hashMap["nearbyPossibilityDuckFeather"] = mMaskSouthShadowDataBean!!.nearbyPossibilityDuckFeather.toString()
            hashMap["halfNylonSeed"] = mSorryAngryScreenBean!!.halfNylonSeed.toString()
            hashMap["upsetDriverDowntown"] = mSorryAngryScreenBean!!.naturalSuggestionRadioactiveSunnyIce.toString()
            convertReqExecute({ appApi.dreamPhysicalRelationship(hashMap) }, onSuccess = {
                mDataBinding.apply {
                    mCantRecPriceTextView.text = "$" + StringUtil.parseMoney(it.mildSlaveryEveryCorrectionImmediateTomb.toString())
                    mCarPorSerPriceTextView.text = "$" + StringUtil.parseMoney(it.badFlowerColleague.toString())
                    mCantRecPriceTextView.text = "$" + StringUtil.parseMoney(it.greyGardeningPieceLicense.toString())
                    mMonDePrePriceTextView.text = "$" + StringUtil.parseMoney(it.mildSlaveryEveryCorrectionImmediateTomb.toString())
                }
            }, baseView = this@AmountChoiceActivity)


        }
    }

    private fun showMoneyPicker() {
        if (mMaskSouthShadowDataBean == null || (mMaskSouthShadowDataBean!!.sorryAngryScreen?.size ?: 0) <= 0) return
        if (mMoneyPicker == null) {
            mMoneyPicker = OptionPicker(this@AmountChoiceActivity).apply {
                setBackgroundColor(CornerRound.Top, resources.getDimension(R.dimen.sw_6dp).toInt(), Color.WHITE)
                cancelView.text = "Cerrar"
                cancelView.setTextColor(Color.parseColor("#333333"))
                okView.text = "Confirmar"
                okView.setTextColor(Color.parseColor("#F98D4F"))
                wheelLayout.setIndicatorColor(Color.parseColor("#E9E9E9"))
                topLineView.visibility = View.GONE
//                val bean = mMaskSouthShadowDataBean!!.sorryAngryScreen?.get(0)
//                val max = bean!!.naturalSuggestionRadioactiveSunnyIce
//                val min = bean.pleasantProudMemorialModel
//                val step = bean.betterStoneRainfall
//                val list = mutableListOf<Double>()
//                var temp = min
//                list.add(min)
//
//                while (max > temp) {
//                    temp += step
//                    list.add(temp)
//                }
                setData(mMaskSouthShadowDataBean!!.sorryAngryScreen)
                setOnOptionPickedListener { position, _ ->
                    mSorryAngryScreenBean = mMaskSouthShadowDataBean!!.sorryAngryScreen?.get(position)
                    mDataBinding.mMoneyTextView.text = StringUtil.parseMoney(mMaskSouthShadowDataBean!!.sorryAngryScreen?.get(position)?.provideText() ?: "3000")
                    dreamPhysicalRelationship()
                }
            }
            mMoneyPicker!!.show()
        } else {
            mMoneyPicker!!.show()
        }
    }

    companion object {

        fun newIntent(context: Context): Intent {
            return Intent(context, AmountChoiceActivity::class.java)
        }
    }
}