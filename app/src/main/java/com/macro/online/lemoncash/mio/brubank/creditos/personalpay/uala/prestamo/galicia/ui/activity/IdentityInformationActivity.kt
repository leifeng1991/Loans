package com.macro.online.lemoncash.mio.brubank.creditos.personalpay.uala.prestamo.galicia.ui.activity

import android.content.Context
import android.content.Intent
import android.text.TextUtils
import androidx.lifecycle.lifecycleScope
import com.base.devices.utils.LocationManagerUtils
import com.bumptech.glide.Glide
import com.macro.online.lemoncash.mio.brubank.creditos.personalpay.uala.prestamo.galicia.R
import com.macro.online.lemoncash.mio.brubank.creditos.personalpay.uala.prestamo.galicia.api.ext.appApi
import com.macro.online.lemoncash.mio.brubank.creditos.personalpay.uala.prestamo.galicia.constants.AppConstants
import com.macro.online.lemoncash.mio.brubank.creditos.personalpay.uala.prestamo.galicia.databinding.ActivityIdentityInformationBinding
import com.macro.online.lemoncash.mio.brubank.creditos.personalpay.uala.prestamo.galicia.utils.CallUtils
import com.macro.online.lemoncash.mio.brubank.creditos.personalpay.uala.prestamo.galicia.utils.SharedPrefUtil
import com.moufans.lib_base.ext.convertReqExecute
import com.moufans.lib_base.utils.ImageLoader
import com.moufans.lib_base.utils.LogUtil
import com.moufans.lib_base.utils.ToastUtil
import com.yuyh.library.imgsel.ISNav
import com.yuyh.library.imgsel.config.ISCameraConfig
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.create
import top.zibin.luban.Luban
import top.zibin.luban.OnCompressListener
import java.io.File


class IdentityInformationActivity : AppBaseActivity<ActivityIdentityInformationBinding>() {
    private val mConfig by lazy {
        ISCameraConfig.Builder().build()
    }
    private var mFirstImageUrl = ""
    private var mSecondImageUrl = ""
    private var mThirdImageUrl = ""
    private var mTempFirstImagePath = ""
    private var mTempSecondImagePath = ""

    override fun getDataBindingLayoutResId(): Int {
        return R.layout.activity_identity_information
    }

    override fun initView() {
        setHeaderTitle("Información DNI")
        ISNav.getInstance().init { context, path, imageView -> Glide.with(context).load(path).into(imageView) }
        mDataBinding.mGigTextView.isSelected = false
        mDataBinding.mGigTextView.isEnabled = false
    }

    override fun initListener() {
        setHeaderRightRightImage(R.mipmap.ic_white_phone) { CallUtils.showCallDialog(this@IdentityInformationActivity) }
        mDataBinding.apply {
            mAnvImageView.setOnClickListener {
                if (TextUtils.isEmpty(mThirdImageUrl))
                    ISNav.getInstance().toCameraActivity(this@IdentityInformationActivity, mConfig, REQUEST_IMAGE_1)
            }
            mRevImageView.setOnClickListener {
                if (TextUtils.isEmpty(mThirdImageUrl))
                    ISNav.getInstance().toCameraActivity(this@IdentityInformationActivity, mConfig, REQUEST_IMAGE_2)
            }
            mCameraImageView.setOnClickListener {
                if (!TextUtils.isEmpty(mFirstImageUrl) && !TextUtils.isEmpty(mSecondImageUrl))
                    ISNav.getInstance().toCameraActivity(this@IdentityInformationActivity, mConfig, REQUEST_IMAGE_3)
                else
                    ToastUtil.showShort("Por favor, cargue su ID primero")
            }
            mGigTextView.setOnClickListener {
                startActivity(IdentityInformationTwoActivity.newIntent(this@IdentityInformationActivity))
            }
        }
    }

    override fun processingLogic() {
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        // 选择的图片
        if (resultCode == RESULT_OK) {
            LogUtil.e("==============${data.toString()}")
            val images = data?.getStringExtra("result")
            if (!TextUtils.isEmpty(images)) {
                when (requestCode) {
                    REQUEST_IMAGE_1 -> {
                        mTempFirstImagePath = images!!
                        ImageLoader.setImage(mTempFirstImagePath, mDataBinding.mAnvImageView)
                    }
                    REQUEST_IMAGE_2 -> {
                        mTempSecondImagePath = images!!
                        ImageLoader.setImage(mTempSecondImagePath, mDataBinding.mRevImageView)
                        if (!TextUtils.isEmpty(mTempFirstImagePath) && !TextUtils.isEmpty(mTempSecondImagePath)) {
                            compress(mutableListOf<String>().apply {
                                add(mTempFirstImagePath)
                                add(mTempSecondImagePath)
                            }, 0)
                        }
                    }
                    REQUEST_IMAGE_3 -> {
                        compress(mutableListOf<String>().apply {
                            add(images!!)
                        }, 1)
                    }


                }
            }
        }

    }

    private fun compress(imagePath: List<String>, type: Int = 0) {
        val list = mutableListOf<String>()
        showLoading()
        Luban.with(this)
            .load(imagePath)
            .setCompressListener(object : OnCompressListener {
                override fun onStart() {
                    // 压缩开始前调用，可以在方法内启动 loading UI
                    showLoading()
                }

                override fun onSuccess(file: File) {
                    // 压缩成功后调用，返回压缩后的图片文件
                    list.add(file.path)
                    if (type == 0) {
                        if (list.size >= 2)
                            uploadImage(list, type)
                    }
                    if (type == 1) {
                        if (list.size >= 1)
                            uploadImage(list, type)
                    }

                }

                override fun onError(e: Throwable) {
                    // 当压缩过程出现问题时调用
                    ToastUtil.showShort("No se pudo cargar el fotos, por favor súbalo de nuevo")
                    hideLoading()
                }
            }).launch()
    }

    private fun uploadImage(imagePath: List<String>, type: Int) {
        val builder = MultipartBody.Builder().setType(MultipartBody.FORM)

        builder.addFormDataPart("disabledContainerAnotherMother", "02")
        builder.addFormDataPart("safeContinentGreetingDeepSatisfaction", SharedPrefUtil.get(AppConstants.USER_ID, ""))
        val locationManagerUtils = LocationManagerUtils()
        builder.addFormDataPart("stupidEnjoyablePasserSouthCup", "${locationManagerUtils.longitude},${locationManagerUtils.latitude}")
        builder.addFormDataPart("femaleShopBoundNurse", "es")
        builder.addFormDataPart("modestFriedSunlightRacialCertificate", if (type == 0) "00" else "01")

        if (type == 0) {
            val file = File(imagePath[0])
            val fileTwo = File(imagePath[1])
            builder.addFormDataPart("independentUglyLibrarian", file.name, create("application/octet-stream".toMediaTypeOrNull(), file))
            builder.addFormDataPart("importantUnfairPennyGram", fileTwo.name, create("application/octet-stream".toMediaTypeOrNull(), fileTwo))
        }
        if (type == 1) {
            val file = File(imagePath[0])
            builder.addFormDataPart("independentUglyLibrarian", file.name, create("application/octet-stream".toMediaTypeOrNull(), file))
        }


        lifecycleScope.launch {
            convertReqExecute({ appApi.encourageAustralianBrooms(builder.build()) }, onSuccess = {
                if (type == 0) {
                    mFirstImageUrl = imagePath[0]
                    mSecondImageUrl = imagePath[1]
                }
                if (type == 1) {
                    mThirdImageUrl = imagePath[0]
                    ImageLoader.setImage(mThirdImageUrl, mDataBinding.mCameraImageView)
                }

                ToastUtil.showShort("Subido con éxito")

            }, onFailure = { _, status, messge ->
                if (status == 1000) {
                    if (type == 0) {
                        mFirstImageUrl = imagePath[0]
                        mSecondImageUrl = imagePath[1]
                    }
                    if (type == 1) {
                        mThirdImageUrl = imagePath[0]
                        ImageLoader.setImage(mThirdImageUrl, mDataBinding.mCameraImageView)
                    }

                    if (!TextUtils.isEmpty(mFirstImageUrl) && !TextUtils.isEmpty(mSecondImageUrl) && !TextUtils.isEmpty(mThirdImageUrl)) {
                        mDataBinding.mGigTextView.isSelected = true
                        mDataBinding.mGigTextView.isEnabled = true
                    }

                    ToastUtil.showShort("Subido con éxito")
                } else {
                    if (type == 0) {
                        mFirstImageUrl = ""
                        mSecondImageUrl = ""
                        ImageLoader.setImage("", mDataBinding.mAnvImageView)
                        ImageLoader.setImage("", mDataBinding.mRevImageView)
                    }
                    if (type == 1) {
                        mThirdImageUrl = ""
                        ImageLoader.setImage("", mDataBinding.mCameraImageView)
                    }
                    ToastUtil.showShort("No se pudo cargar el fotos, por favor súbalo de nuevo")
                }
                LogUtil.e("================$messge")
            }, isShowToast = false, baseView = this@IdentityInformationActivity)
        }
    }

    companion object {
        private const val REQUEST_IMAGE_1 = 1231
        private const val REQUEST_IMAGE_2 = 1232
        private const val REQUEST_IMAGE_3 = 1233

        fun newIntent(context: Context): Intent {
            return Intent(context, IdentityInformationActivity::class.java)
        }
    }
}