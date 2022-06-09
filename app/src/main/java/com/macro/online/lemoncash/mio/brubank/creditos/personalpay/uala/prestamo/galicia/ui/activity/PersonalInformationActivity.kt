package com.macro.online.lemoncash.mio.brubank.creditos.personalpay.uala.prestamo.galicia.ui.activity

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.view.View
import androidx.lifecycle.lifecycleScope
import com.github.gzuliyujiang.dialog.CornerRound
import com.github.gzuliyujiang.wheelpicker.BirthdayPicker
import com.github.gzuliyujiang.wheelpicker.OptionPicker
import com.macro.online.lemoncash.mio.brubank.creditos.personalpay.uala.prestamo.galicia.R
import com.macro.online.lemoncash.mio.brubank.creditos.personalpay.uala.prestamo.galicia.api.BaseFormDataUtils
import com.macro.online.lemoncash.mio.brubank.creditos.personalpay.uala.prestamo.galicia.api.ext.appApi
import com.macro.online.lemoncash.mio.brubank.creditos.personalpay.uala.prestamo.galicia.api.rep.AddressDataBean
import com.macro.online.lemoncash.mio.brubank.creditos.personalpay.uala.prestamo.galicia.api.rep.SexDataBean
import com.macro.online.lemoncash.mio.brubank.creditos.personalpay.uala.prestamo.galicia.databinding.ActivityPersonalInformationBinding
import com.macro.online.lemoncash.mio.brubank.creditos.personalpay.uala.prestamo.galicia.ui.fragment.SelectAddressFragmentDialog
import com.moufans.lib_base.ext.convertReqExecute
import com.moufans.lib_base.utils.LogUtil
import com.moufans.lib_base.utils.ToastUtil
import kotlinx.coroutines.launch
import java.util.*
import java.util.regex.Matcher
import java.util.regex.Pattern

class PersonalInformationActivity : AppBaseActivity<ActivityPersonalInformationBinding>(), TextWatcher {
    private var mSexOptionPicker: OptionPicker? = null
    private var mSexDataBean: SexDataBean? = null
    private var mSelectedProvinceAddress: AddressDataBean? = null
    private var mSelectedCityAddress: AddressDataBean? = null
    private var mSelectedCountyAddress: AddressDataBean? = null

    override fun getDataBindingLayoutResId(): Int {
        return R.layout.activity_personal_information
    }

    override fun initView() {
        setHeaderTitle("Información personal")
        mDataBinding.mGigTextView.isSelected = false
        mDataBinding.mGigTextView.isEnabled = false
    }

    override fun initListener() {
        setHeaderRightRightImage(R.mipmap.ic_white_phone) { com.macro.online.lemoncash.mio.brubank.creditos.personalpay.uala.prestamo.galicia.utils.CallUtils.showCallDialog(this@PersonalInformationActivity) }
        mDataBinding.apply {
            mNomEditText.addTextChangedListener(this@PersonalInformationActivity)
            mApeEditText.addTextChangedListener(this@PersonalInformationActivity)
            mCorrEditText.addTextChangedListener(this@PersonalInformationActivity)
            mDireEditText.addTextChangedListener(this@PersonalInformationActivity)
            mNumDeEditText.addTextChangedListener(this@PersonalInformationActivity)
            // 选择性别
            mGeneEditText.setOnClickListener {
                selectedSex()
            }
            // 选择生日
            mFecEditText.setOnClickListener {
                showBirthdayDialog()
            }
            // 选择省市
            mDirecResEditText.setOnClickListener {
                selectAddress()
            }
            mGigTextView.setOnClickListener {
                checkData(true)
//                startActivity(ContactInformationActivity.newIntent(this@PersonalInformationActivity))
            }
        }
    }

    override fun processingLogic() {
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        checkData()
    }

    override fun afterTextChanged(s: Editable?) {
    }

    private fun selectedSex() {
        if (mSexOptionPicker == null) {
            val hashMap: HashMap<String, String> = BaseFormDataUtils.getBaseHasMap(1)
            hashMap["modestFriedSunlightRacialCertificate"] = "sex"
            lifecycleScope.launch {
                convertReqExecute({ appApi.sex(hashMap) }, onSuccess = {
                    mSexOptionPicker = OptionPicker(this@PersonalInformationActivity).apply {
                        setBackgroundColor(CornerRound.Top, resources.getDimension(R.dimen.sw_6dp).toInt(), Color.WHITE)
                        cancelView.text = "Cerrar"
                        cancelView.setTextColor(Color.parseColor("#333333"))
                        okView.text = "Confirmar"
                        okView.setTextColor(Color.parseColor("#F98D4F"))
                        wheelLayout.setIndicatorColor(Color.parseColor("#E9E9E9"))
                        topLineView.visibility = View.GONE
                        setData(it)
                        setOnOptionPickedListener { position, _ ->
                            mSexDataBean = it[position]
                            mDataBinding.mGeneEditText.text = mSexDataBean?.provideText()
                            checkData()
                        }
                    }
                    mSexOptionPicker!!.show()
                }, baseView = this@PersonalInformationActivity)

            }
        } else {
            mSexOptionPicker!!.show()
        }

    }

    private fun showBirthdayDialog() {
        DatePickerDialog(this@PersonalInformationActivity, 0, { _, year, month, dayOfMonth ->
            val m = month + 1
            val mStr = if (m < 10) "0$m" else m
            val d = if (dayOfMonth < 10) "0$dayOfMonth" else dayOfMonth
            mDataBinding.mFecEditText.text = "$d-$mStr-$year"
            checkData()
        }, Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH)).show()

    }

    private fun selectAddress() {
        val selectAddressFragmentDialog = SelectAddressFragmentDialog()
        selectAddressFragmentDialog.onSelectAddressFinishListener = object : SelectAddressFragmentDialog.OnSelectAddressFinishListener {
            @SuppressLint("SetTextI18n")
            override fun onSelectAddressFinish(selectedProvinceAddress: AddressDataBean, selectedCityAddress: AddressDataBean, selectedCountyAddress: AddressDataBean) {
                mSelectedProvinceAddress = selectedProvinceAddress
                mSelectedCityAddress = selectedCityAddress
                mSelectedCountyAddress = selectedCountyAddress
                mDataBinding.mDirecResEditText.text = "${selectedProvinceAddress.rightSkillTibetan} ${selectedCityAddress.rightSkillTibetan} ${selectedCountyAddress.rightSkillTibetan}"
                checkData(false)
            }

        }
        selectAddressFragmentDialog.show(supportFragmentManager, "selectAddressFragmentDialog")
    }

    private fun checkData(isSubmit: Boolean = false) {
        LogUtil.e("=========================1======================!!!!!!!!!!!!!!")
        if (!isSubmit) {
            mDataBinding.mGigTextView.isSelected = false
            mDataBinding.mGigTextView.isEnabled = false
        }
        mDataBinding.apply {
            val name = mNomEditText.text.toString()
            if (TextUtils.isEmpty(name)) {
                if (isSubmit)
                    ToastUtil.showShort("Por favor complete toda la información completamente")
                return
            }
            LogUtil.e("=========================2======================!!!!!!!!!!!!!!")
            val nameX = mApeEditText.text.toString()
            if (TextUtils.isEmpty(nameX)) {
                if (isSubmit)
                    ToastUtil.showShort("Por favor complete toda la información completamente")
                return
            }
            LogUtil.e("=========================3======================!!!!!!!!!!!!!!")
            if (mSexDataBean == null) {
                if (isSubmit)
                    ToastUtil.showShort("Por favor complete toda la información completamente")
                return
            }
            LogUtil.e("=========================4======================!!!!!!!!!!!!!!")
            val birthday = mFecEditText.text.toString()
            if (TextUtils.isEmpty(birthday)) {
                if (isSubmit)
                    ToastUtil.showShort("Por favor complete toda la información completamente")
                return
            }
            val corr = mCorrEditText.text.toString()
            if (TextUtils.isEmpty(corr)) {
                if (isSubmit)
                    ToastUtil.showShort("Por favor complete toda la información completamente")
                return
            }
            LogUtil.e("=========================5======================!!!!!!!!!!!!!!")
            if (!isEmail(corr)) {
                if (isSubmit)
                    ToastUtil.showShort("Error de formato de correo electrónico, vuelva a ingresar")
                return
            }
            LogUtil.e("=========================6======================!!!!!!!!!!!!!!")

            if (mSelectedProvinceAddress == null) {
                if (isSubmit)
                    ToastUtil.showShort("Por favor complete toda la información completamente")
                return
            }

            LogUtil.e("=========================7======================!!!!!!!!!!!!!!")

            val direc = mDireEditText.text.toString()
            if (TextUtils.isEmpty(direc)) {
                if (isSubmit)
                    ToastUtil.showShort("Por favor complete toda la información completamente")
                return
            }
            val numDe = mNumDeEditText.text.toString()
            if (TextUtils.isEmpty(numDe)) {
                if (isSubmit)
                    ToastUtil.showShort("Por favor complete toda la información completamente")
                return
            }
            LogUtil.e("=======================2========================!!!!!!!!!!!!!!")
            mDataBinding.mGigTextView.isSelected = true
            mDataBinding.mGigTextView.isEnabled = true

            if (isSubmit) {
                val hashMap: HashMap<String, String> = BaseFormDataUtils.getBaseHasMap(1)
                hashMap["rareYesterdaySettlementRawAtom"] = name
                hashMap["unhappyCubicExam"] = nameX
                hashMap["smartChannelHistory"] = mSexDataBean!!.likelyFruitUndergroundBridge ?: ""
                hashMap["popPurposePedestrianTennis"] = mSexDataBean!!.forgetfulEntertainmentUnusualQuiz ?: ""
                hashMap["skillfulElectricCubicSwimming"] = birthday
                hashMap["youngMushroomQuality"] = mSelectedProvinceAddress!!.rightSkillTibetan ?: ""
                hashMap["rightMusicalDate"] = mSelectedProvinceAddress!!.neitherImportanceMadBoot ?: ""
                hashMap["gladReligiousInterpreter"] = mSelectedCityAddress!!.rightSkillTibetan ?: ""
                hashMap["easternReasonNaturalPensionAngrySituation"] = mSelectedCityAddress!!.neitherImportanceMadBoot ?: ""
                hashMap["localSir"] = mSelectedCountyAddress!!.rightSkillTibetan ?: ""
                hashMap["quickCongratulationIndianApology"] = mSelectedCountyAddress!!.neitherImportanceMadBoot ?: ""
                hashMap["indeedSlice"] = "${mSelectedProvinceAddress!!.rightSkillTibetan} ${mSelectedCityAddress!!.rightSkillTibetan} ${mSelectedCountyAddress!!.rightSkillTibetan}"
                hashMap["irishThinLatestCigar"] = direc
                hashMap["dearLoafIceland"] = numDe
                lifecycleScope.launch {
                    convertReqExecute({ appApi.commandUnfairData(hashMap) }, onSuccess = {
                        startActivity(ContactInformationActivity.newIntent(this@PersonalInformationActivity))
                    }, onFailure = { _, status, _ ->
                        if (status == 1000) {
                            startActivity(ContactInformationActivity.newIntent(this@PersonalInformationActivity))
                        }
                    }, this@PersonalInformationActivity)

                }
            }


        }


    }

    fun isEmail(email: String): Boolean {
        val str = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$"
        val p: Pattern = Pattern.compile(str)
        val m: Matcher = p.matcher(email)
        return m.matches()
    }

    companion object {

        fun newIntent(context: Context): Intent {
            return Intent(context, PersonalInformationActivity::class.java)
        }
    }


}