package com.macro.online.lemoncash.mio.brubank.creditos.personalpay.uala.prestamo.galicia.ui.activity

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
import com.macro.online.lemoncash.mio.brubank.creditos.personalpay.uala.prestamo.galicia.api.rep.SexDataBean
import com.macro.online.lemoncash.mio.brubank.creditos.personalpay.uala.prestamo.galicia.databinding.ActivityIdentityInformationTwoBinding
import com.macro.online.lemoncash.mio.brubank.creditos.personalpay.uala.prestamo.galicia.utils.CallUtils
import com.moufans.lib_base.base.activity.BaseActivity
import com.moufans.lib_base.ext.convertReqExecute
import com.moufans.lib_base.utils.ToastUtil
import kotlinx.coroutines.launch
import java.util.HashMap

class IdentityInformationTwoActivity : AppBaseActivity<ActivityIdentityInformationTwoBinding>(), TextWatcher {
    private val mType by lazy {
        intent.getIntExtra(INTENT_P_TYPE, 0)
    }
    private var mBankOptionPicker: OptionPicker? = null
    private var mBankNameOptionPicker: OptionPicker? = null
    private var mBankNameDataBean: SexDataBean? = null

    override fun getDataBindingLayoutResId(): Int {
        return R.layout.activity_identity_information_two
    }

    override fun initView() {
        setHeaderTitle("Método de pago")
        mDataBinding.mGigTextView.isEnabled = false
        mDataBinding.mGigTextView.isSelected = false
        val content = if (mType == 0)
            "*Todos los datos que proporciones serán tratados correctamente y nunca se revelará su información del perfil"
        else
            " *Los recibos y pagos pueden ser afectados por el horario bancario. Por favor usa la banca en línea para recibir y realizar pagos."
        mDataBinding.mBottomContentTextView.text = content
    }

    override fun initListener() {
        setHeaderRightRightImage(R.mipmap.ic_white_phone) { CallUtils.showCallDialog(this@IdentityInformationTwoActivity) }
        mDataBinding.apply {
            mNumCvuEditText.addTextChangedListener(this@IdentityInformationTwoActivity)
            mConfNumCvuEditText.addTextChangedListener(this@IdentityInformationTwoActivity)
            mmNumCbuEditText.addTextChangedListener(this@IdentityInformationTwoActivity)
            mFecVenEditText.addTextChangedListener(this@IdentityInformationTwoActivity)
            mCvvEditText.addTextChangedListener(this@IdentityInformationTwoActivity)
            mCvuTextView.setOnClickListener {
                showBank()
            }
            mNomApeEditText.setOnClickListener {
                selectedBankName()
            }
            mGigTextView.setOnClickListener {
                checkInput(true)
//                startActivity(LoadingActivity.newIntent(this@IdentityInformationTwoActivity))
            }
        }
    }

    override fun processingLogic() {
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        checkInput()
    }

    override fun afterTextChanged(s: Editable?) {
    }

    private fun showBank() {
        if (mBankOptionPicker == null) {
            mBankOptionPicker = OptionPicker(this@IdentityInformationTwoActivity).apply {
                setBackgroundColor(CornerRound.Top, resources.getDimension(R.dimen.sw_6dp).toInt(), Color.WHITE)
                cancelView.text = "Cerrar"
                cancelView.setTextColor(Color.parseColor("#333333"))
                okView.text = "Confirmar"
                okView.setTextColor(Color.parseColor("#F98D4F"))
                wheelLayout.setIndicatorColor(Color.parseColor("#E9E9E9"))
                topLineView.visibility = View.GONE
                setData(mutableListOf("CVU", "CBU"))
                setOnOptionPickedListener { position, item ->
                    if (mDataBinding.mCvuTextView.text.toString() == item.toString())
                        return@setOnOptionPickedListener
                    mDataBinding.mCvuTextView.text = item.toString()
                    mDataBinding.mNomApeEditText.text = ""
                    mDataBinding.mNumCvuEditText.setText("")
                    mDataBinding.mConfNumCvuEditText.setText("")
                    mDataBinding.mNomApeEditText.text = ""
                    mDataBinding.mmNumCbuEditText.setText("")
                    mDataBinding.mFecVenEditText.setText("")
                    mDataBinding.mCvvEditText.setText("")
                    mBankNameDataBean = null
                    if (position == 0) {
                        mDataBinding.mFirstLayout.visibility = View.VISIBLE
                        mDataBinding.mSecondLayout.visibility = View.GONE
                        mDataBinding.mSwitchLeftImageView.visibility = View.VISIBLE
                        mDataBinding.mSwitchRightImageView.visibility = View.GONE
                        mDataBinding.mSwitchLeftTextView.setTextColor(Color.parseColor("#FFFFFF"))
                        mDataBinding.mSwitchRightTextView.setTextColor(Color.parseColor("#2D2D2D"))
                    } else {
                        mDataBinding.mFirstLayout.visibility = View.GONE
                        mDataBinding.mSecondLayout.visibility = View.VISIBLE
                        mDataBinding.mSwitchLeftImageView.visibility = View.GONE
                        mDataBinding.mSwitchRightImageView.visibility = View.VISIBLE
                        mDataBinding.mSwitchLeftTextView.setTextColor(Color.parseColor("#2D2D2D"))
                        mDataBinding.mSwitchRightTextView.setTextColor(Color.parseColor("#FFFFFF"))
                    }
                    checkInput()
                }
            }
            mBankOptionPicker!!.show()
        } else {
            mBankOptionPicker!!.show()
        }

    }

    private fun selectedBankName() {
        if (mBankNameOptionPicker == null) {
            val hashMap: HashMap<String, String> = BaseFormDataUtils.getBaseHasMap(1)
            hashMap["modestFriedSunlightRacialCertificate"] = "sex"
            lifecycleScope.launch {
                convertReqExecute({ appApi.sex(hashMap) }, onSuccess = {
                    mBankNameOptionPicker = OptionPicker(this@IdentityInformationTwoActivity).apply {
                        setBackgroundColor(CornerRound.Top, resources.getDimension(R.dimen.sw_6dp).toInt(), Color.WHITE)
                        cancelView.text = "Cerrar"
                        cancelView.setTextColor(Color.parseColor("#333333"))
                        okView.text = "Confirmar"
                        okView.setTextColor(Color.parseColor("#F98D4F"))
                        wheelLayout.setIndicatorColor(Color.parseColor("#E9E9E9"))
                        topLineView.visibility = View.GONE
                        setData(it)
                        setOnOptionPickedListener { position, _ ->
                            mBankNameDataBean = it[position]
                            mDataBinding.mNomApeEditText.text = mBankNameDataBean?.provideText()
                            checkInput()
                        }
                    }
                    mBankNameOptionPicker!!.show()
                }, baseView = this@IdentityInformationTwoActivity)

            }
        } else {
            mBankNameOptionPicker!!.show()
        }

    }

    private fun checkInput(isSubmit: Boolean = false) {
        if (!isSubmit) {
            mDataBinding.mGigTextView.isEnabled = false
            mDataBinding.mGigTextView.isSelected = false
        }
        val type = mDataBinding.mCvuTextView.text.toString()
        if (type == "CVU") {
            val mNumCvuStr = mDataBinding.mNumCvuEditText.text.toString()
            if (TextUtils.isEmpty(mNumCvuStr)) {
                if (isSubmit)
                    ToastUtil.showShort("Por favor complete toda la información completamente")
                return
            }

            val mConfNumCvuStr = mDataBinding.mConfNumCvuEditText.text.toString()
            if (TextUtils.isEmpty(mConfNumCvuStr)) {
                if (isSubmit)
                    ToastUtil.showShort("Por favor complete toda la información completamente")
                return
            }

            if (isSubmit) {
                if (mNumCvuStr != mConfNumCvuStr) {
                    ToastUtil.showShort("Por favor complete la misma información")
                    return
                }
            }

            mDataBinding.mGigTextView.isEnabled = true
            mDataBinding.mGigTextView.isSelected = true

            if (isSubmit) {
                submit("1", mNumCvuStr, mConfNumCvuStr, "", "", "")
            }
        } else {
            if (mBankNameDataBean == null) {
                if (isSubmit)
                    ToastUtil.showShort("Por favor complete toda la información completamente")
                return
            }

            val mNumCbuStr = mDataBinding.mmNumCbuEditText.text.toString()
            if (TextUtils.isEmpty(mNumCbuStr)) {
                if (isSubmit)
                    ToastUtil.showShort("Por favor complete toda la información completamente")
                return
            }

            val mFecVenStr = mDataBinding.mFecVenEditText.text.toString()
            if (TextUtils.isEmpty(mFecVenStr)) {
                if (isSubmit)
                    ToastUtil.showShort("Por favor complete toda la información completamente")
                return
            }

            val mCvvStr = mDataBinding.mCvvEditText.text.toString()
            if (TextUtils.isEmpty(mCvvStr)) {
                if (isSubmit)
                    ToastUtil.showShort("Por favor complete toda la información completamente")
                return
            }

            mDataBinding.mGigTextView.isEnabled = true
            mDataBinding.mGigTextView.isSelected = true

            if (isSubmit) {
                submit("0", mBankNameDataBean!!.provideText(), mBankNameDataBean!!.likelyFruitUndergroundBridge ?: "", mNumCbuStr, mFecVenStr, mCvvStr)
            }
        }

    }

    private fun submit(bankType: String, bankName: String, bankCode: String, bankAccountNumber: String, expireDate: String, safeCvv: String) {
        val hashMap: java.util.HashMap<String, String> = BaseFormDataUtils.getBaseHasMap(1)
        hashMap["maximumPinkCentDoubleCitizen"] = bankType
        if (bankType == "0") {
            // 银行卡
            hashMap["arcticItalianFireworksPinkSpoon"] = bankName
            hashMap["steadyCertainTopCrop"] = bankCode
            hashMap["manFairClockNovember"] = bankAccountNumber
            hashMap["suchBookstorePassportConversation"] = expireDate
            hashMap["basicCanHostess"] = safeCvv
        } else {
            // 钱包
            hashMap["harmlessPhysicsInsuranceInlandDevotion"] = bankName
            hashMap["humorousDisabilitySimilarPosition"] = bankCode
        }

        lifecycleScope.launch {
            convertReqExecute({ appApi.loseCrowdedMeaning(hashMap) }, onSuccess = {
                if (mType == 0)
                    startActivity(LoadingActivity.newIntent(this@IdentityInformationTwoActivity))
                else
                    finish()
            }, onFailure = { _, status, _ ->
                if (status == 1000) {
                    if (mType == 0)
                        startActivity(LoadingActivity.newIntent(this@IdentityInformationTwoActivity))
                    else
                        finish()
                }
            }, this@IdentityInformationTwoActivity)

        }
    }


    companion object {
        private const val INTENT_P_TYPE = "type"

        fun newIntent(context: Context, type: Int = 0): Intent {
            return Intent(context, IdentityInformationTwoActivity::class.java).apply {
                putExtra(INTENT_P_TYPE, type)
            }
        }
    }


}