package com.macro.online.lemoncash.mio.brubank.creditos.personalpay.uala.prestamo.galicia.ui.activity

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.text.format.DateUtils
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.listener.OnItemClickListener
import com.github.gzuliyujiang.dialog.CornerRound
import com.github.gzuliyujiang.wheelpicker.OptionPicker
import com.google.gson.Gson
import com.macro.online.lemoncash.mio.brubank.creditos.personalpay.uala.prestamo.galicia.MainActivity
import com.macro.online.lemoncash.mio.brubank.creditos.personalpay.uala.prestamo.galicia.R
import com.macro.online.lemoncash.mio.brubank.creditos.personalpay.uala.prestamo.galicia.api.BaseFormDataUtils
import com.macro.online.lemoncash.mio.brubank.creditos.personalpay.uala.prestamo.galicia.api.ext.appApi
import com.macro.online.lemoncash.mio.brubank.creditos.personalpay.uala.prestamo.galicia.api.rep.MaskSouthShadowDataBean
import com.macro.online.lemoncash.mio.brubank.creditos.personalpay.uala.prestamo.galicia.databinding.ActivityAmountChoiceBinding
import com.macro.online.lemoncash.mio.brubank.creditos.personalpay.uala.prestamo.galicia.ui.adapter.SelectDayAdapter
import com.macro.online.lemoncash.mio.brubank.creditos.personalpay.uala.prestamo.galicia.ui.bean.DeviceInfoBean
import com.macro.online.lemoncash.mio.brubank.creditos.personalpay.uala.prestamo.galicia.ui.bean.SelectDayBean
import com.macro.online.lemoncash.mio.brubank.creditos.personalpay.uala.prestamo.galicia.utils.*
import com.moufans.lib_base.base.activity.BaseActivity
import com.moufans.lib_base.ext.convertReqExecute
import com.moufans.lib_base.utils.LogUtil
import com.moufans.lib_base.utils.ToastUtil
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody

class AmountChoiceActivity : AppBaseActivity<ActivityAmountChoiceBinding>() {
    private val mSelectDayAdapter by lazy {
        SelectDayAdapter()
    }
    private var mMoneyPicker: OptionPicker? = null
    private var mMaskSouthShadowDataBean: MaskSouthShadowDataBean? = null
    private var mSorryAngryScreenBean: MaskSouthShadowDataBean.SorryAngryScreenBean? = null
    private var mSelectMoney = 0.0

    override fun getDataBindingLayoutResId(): Int {
        return R.layout.activity_amount_choice
    }

    override fun initView() {
        setHeaderTitle("App_Name")
        mDataBinding.mConfTextView.isSelected = true
        mDataBinding.mSelectDayRecyclerView.apply {
            layoutManager = GridLayoutManager(this@AmountChoiceActivity, 3, GridLayoutManager.VERTICAL, false)
            adapter = mSelectDayAdapter
        }

    }

    override fun initListener() {
        setHeaderRightRightImage(R.mipmap.ic_white_phone) { CallUtils.showCallDialog(this@AmountChoiceActivity) }
        mDataBinding.apply {
            mConfTextView.setOnClickListener {
                intendSocialCanada()
            }
            mSelectPriceLayout.setOnClickListener {
                showMoneyPicker()
            }
        }
        mSelectDayAdapter.setOnItemClickListener { adapter, view, position ->
            val item = mSelectDayAdapter.data[position]
            if (item.isCanSelect) {
                if (mSelectDayAdapter.mSelectPosition != position) {
                    mSelectDayAdapter.mSelectPosition = position
                    mSelectDayAdapter.notifyDataSetChanged()
                    mDataBinding.mFecDayTextView.text = mSelectDayAdapter.data[mSelectDayAdapter.mSelectPosition].day
                }
            } else {
                CommonDialogUtil.showCalendarDialog(this)
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

                if (mMaskSouthShadowDataBean == null || (mMaskSouthShadowDataBean!!.sorryAngryScreen?.size ?: 0) <= 0) return@convertReqExecute
                mSorryAngryScreenBean = mMaskSouthShadowDataBean!!.sorryAngryScreen!![0]
                mSelectMoney = mMaskSouthShadowDataBean!!.sorryAngryScreen!![0].naturalSuggestionRadioactiveSunnyIce
                mDataBinding.mMoneyTextView.text = "$${StringUtil.parseMoney(mSelectMoney)}"
                getDay()
            }, baseView = this@AmountChoiceActivity)


        }
    }

    private fun getDay() {
        val list = mutableListOf<SelectDayBean>()
        val hashMap: java.util.HashMap<String, String> = BaseFormDataUtils.getBaseHasMap(1)
        hashMap["modestFriedSunlightRacialCertificate"] = "newrealterm"
        lifecycleScope.launch {
            convertReqExecute({ appApi.sex(hashMap) }, onSuccess = {
                LogUtil.e("========================" + mMaskSouthShadowDataBean!!.fatAfternoonDangerousPublicThread)
                val currentDay = DateTimeUtil.stringParserLong(mMaskSouthShadowDataBean!!.fatAfternoonDangerousPublicThread)
                LogUtil.e("================" + DateTimeUtil.formatDateTime(currentDay))
                val addDay = it[0].likelyFruitUndergroundBridge?.toInt() ?: 0
                for (item in mMaskSouthShadowDataBean!!.sorryAngryScreen ?: emptyList()) {
                    val mDay = item.saltyDistantForest.toInt() + addDay
                    val s1 = SelectDayBean().apply {
                        day = DateTimeUtil.formatDateTime((currentDay + mDay * 24 * 60 * 60 * 1000L), "MM-dd-yyyy")
                    }
                    val s2 = SelectDayBean().apply {
                        day = DateTimeUtil.formatDateTime((currentDay + 2 * mDay * 24 * 60 * 60 * 1000L), "MM-dd-yyyy")
                    }
                    val s3 = SelectDayBean().apply {
                        day = DateTimeUtil.formatDateTime((currentDay + 2 * 2 * mDay * 24 * 60 * 60 * 1000L), "MM-dd-yyyy")
                    }
                    list.add(s1)
                    list.add(s2)
                    list.add(s3)
                }

                list[list.size - 1].isCanSelect = false
                list[list.size - 2].isCanSelect = false

                mSelectDayAdapter.setList(list)

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
            hashMap["upsetDriverDowntown"] = "$mSelectMoney"
            convertReqExecute({ appApi.dreamPhysicalRelationship(hashMap) }, onSuccess = {
                mDataBinding.apply {
                    mCantRecPriceTextView.text = "$" + StringUtil.parseMoney(it.disabledPossibleSchoolTerribleInformation.toString())
                    mRegPriceTextView.text = "$" + StringUtil.parseMoney(it.sweetPotSmartSpeech.toString())
                    mCarPorSerPriceTextView.text = "$" + StringUtil.parseMoney(it.badFlowerColleague.toString())
                    mIvaPriceTextView.text = "$" + StringUtil.parseMoney(it.greyGardeningPieceLicense.toString())
                    ("$" + StringUtil.parseMoney(if (it.sureArticleFareNoisyPort == null || it.sureArticleFareNoisyPort?.isEmpty() == true) "0" else it.sureArticleFareNoisyPort?.get(0)!!.distantHopelessPoetTightPing)).also { mCarPorDeSerTextView.text = it }
                    mMonDeDePriceTextView.text = "$" + StringUtil.parseMoney(mSelectMoney)
                    mFecDayTextView.text = mSelectDayAdapter.data[mSelectDayAdapter.mSelectPosition].day
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
                val bean = mMaskSouthShadowDataBean!!.sorryAngryScreen?.get(0)
                val max = bean!!.naturalSuggestionRadioactiveSunnyIce
                val min = bean.pleasantProudMemorialModel
                val step = bean.betterStoneRainfall
                val list = mutableListOf<Double>()
                var temp = min
                list.add(min)

                while (max > temp) {
                    temp += step
                    list.add(temp)
                }
                setData(list)
                setOnOptionPickedListener { position, _ ->
                    mSelectMoney = list[position]
                    mSorryAngryScreenBean = mMaskSouthShadowDataBean!!.sorryAngryScreen?.get(position)
                    mDataBinding.mMoneyTextView.text = StringUtil.parseMoney(mSelectMoney)
                    dreamPhysicalRelationship()
                }
            }
            mMoneyPicker!!.show()
        } else {
            mMoneyPicker!!.show()
        }
    }

    @SuppressLint("SetTextI18n")
    private fun intendSocialCanada() {
        lifecycleScope.launch {
            val hashMap: HashMap<String, String> = BaseFormDataUtils.getBaseHasMap()
            hashMap["nearbyPossibilityDuckFeather"] = mMaskSouthShadowDataBean!!.nearbyPossibilityDuckFeather.toString()
            hashMap["halfNylonSeed"] = mSorryAngryScreenBean!!.halfNylonSeed.toString()
            hashMap["upsetDriverDowntown"] = "$mSelectMoney"
            convertReqExecute({ appApi.intendSocialCanada(hashMap) }, onSuccess = {
                mDataBinding.apply {
                    val mFirstStr = mCantRecPriceTextView.text.toString()
                    val mSecondStr = mMonDeDePriceTextView.text.toString()
                    val mThirdStr = mFecDayTextView.text.toString()
                    CommonDialogUtil.showConfirmAmountDialog(this@AmountChoiceActivity, mFirstStr, mSecondStr, mThirdStr,object : CommonDialogUtil.OnButtonClickListener{
                        override fun onLeftButtonClick(dialog: DialogBuilder) {

                        }

                        override fun onRightOrCenterButtonClick(dialog: DialogBuilder) {
                            raceHugeSteward()
                        }

                    })
                }
            }, baseView = this@AmountChoiceActivity)


        }
    }

    @SuppressLint("SetTextI18n")
    private fun raceHugeSteward() {
        lifecycleScope.launch {
            val hashMap: HashMap<String, String> = BaseFormDataUtils.getBaseHasMap()
            hashMap["nearbyPossibilityDuckFeather"] = mMaskSouthShadowDataBean!!.nearbyPossibilityDuckFeather.toString()
            hashMap["halfNylonSeed"] = mSorryAngryScreenBean!!.halfNylonSeed.toString()
            hashMap["upsetDriverDowntown"] = "$mSelectMoney"
            convertReqExecute({ appApi.raceHugeSteward(hashMap) }, onSuccess = {
                mDataBinding.apply {
                    CommonDialogUtil.showConfirmAmountCountdownDialog(this@AmountChoiceActivity,object :CommonDialogUtil.OnButtonClickListener{
                        override fun onLeftButtonClick(dialog: DialogBuilder) {

                        }

                        override fun onRightOrCenterButtonClick(dialog: DialogBuilder) {
                            startActivity(MainActivity.newIntent(this@AmountChoiceActivity))
                            finish()
                        }

                    })
                }
            }, baseView = this@AmountChoiceActivity)


        }
    }

    companion object {

        fun newIntent(context: Context): Intent {
            return Intent(context, AmountChoiceActivity::class.java)
        }
    }
}