package com.macro.online.lemoncash.mio.brubank.creditos.personalpay.uala.prestamo.galicia.ui.fragment

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.Gravity
import android.view.View
import android.view.WindowManager
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.macro.online.lemoncash.mio.brubank.creditos.personalpay.uala.prestamo.galicia.R
import com.macro.online.lemoncash.mio.brubank.creditos.personalpay.uala.prestamo.galicia.api.BaseFormDataUtils
import com.macro.online.lemoncash.mio.brubank.creditos.personalpay.uala.prestamo.galicia.api.ext.appApi
import com.macro.online.lemoncash.mio.brubank.creditos.personalpay.uala.prestamo.galicia.api.rep.AddressDataBean
import com.macro.online.lemoncash.mio.brubank.creditos.personalpay.uala.prestamo.galicia.databinding.CommonDialogFragmentSelectAddressBinding
import com.macro.online.lemoncash.mio.brubank.creditos.personalpay.uala.prestamo.galicia.ui.adapter.AddressAdapter
import com.macro.online.lemoncash.mio.brubank.creditos.personalpay.uala.prestamo.galicia.utils.PixelUtil
import com.moufans.lib_base.base.fragment.BaseDialogFragment
import com.moufans.lib_base.ext.convertReqExecute
import com.moufans.lib_base.utils.LogUtil
import com.moufans.lib_base.utils.ToastUtil
import kotlinx.coroutines.launch


class SelectAddressFragmentDialog : BaseDialogFragment<CommonDialogFragmentSelectAddressBinding>() {
    lateinit var mProvinceAdapterCommon: AddressAdapter// 省
    lateinit var mCityAdapterCommon: AddressAdapter// 市
    lateinit var mCountyAdapterCommon: AddressAdapter// 区县
    private var mSelectedProvinceAddress: AddressDataBean? = null// 选中的省
    private var mSelectedCityAddress: AddressDataBean? = null// 选中的市
    private var mSelectedCountyAddress: AddressDataBean? = null// 选中的区县
    var onSelectAddressFinishListener: OnSelectAddressFinishListener? = null// 监听
    private var unimportantBirdcageDoubleApology = "-1"
    private var unusualLaserSolidBallet = "1"


    override fun onStart() {
        super.onStart()
        // 设置在底部
        val window = dialog?.window
        window!!.setGravity(Gravity.BOTTOM)
        // 设置屏幕宽
        window.setBackgroundDrawable(ColorDrawable(Color.WHITE))
        val attributes = window.attributes
        attributes.width = WindowManager.LayoutParams.MATCH_PARENT
        window.attributes = attributes
    }

    override fun initView() {
        // 初始化
        mFragmentDataBinding.mSelectAddressContentRv.layoutManager = LinearLayoutManager(context)
        // 初始化adapter
        mProvinceAdapterCommon = AddressAdapter(R.layout.common_item_select_address)// 省
        mCityAdapterCommon = AddressAdapter(R.layout.common_item_select_address)// 市
        mCountyAdapterCommon = AddressAdapter(R.layout.common_item_select_address)// 区县
        // 设置监听
        mFragmentDataBinding.mSelectAddressTitleProvinceTv.setOnClickListener { switchTab(0) }// 省
        mFragmentDataBinding.mSelectAddressTitleCityTv.setOnClickListener { switchTab(1) }// 市
        mFragmentDataBinding.mSelectAddressTitleCountyTv.setOnClickListener { switchTab(2) }// 区县
        mFragmentDataBinding.mSelectAddressConfigTv.setOnClickListener { configSelect() }// 确定按钮
        // item点击监听
        mProvinceAdapterCommon.setOnItemClickListener { adapter, _, position ->
            // 记录选中并切换到市
            mSelectedProvinceAddress = adapter.getItem(position) as AddressDataBean?// 保存选中值
            mFragmentDataBinding.mSelectAddressTitleProvinceTv.text = mSelectedProvinceAddress?.provideText()// 设置选中的值
            mProvinceAdapterCommon.setSelectedPositionAndNotify(position)// 通知选中
            // 清空市
            mFragmentDataBinding.mSelectAddressTitleCityTv.text = null
            mSelectedCityAddress = null
            mCityAdapterCommon.setSelectedPosition(-1)
            mCityAdapterCommon.setNewData(null)

            // 清空县
            mSelectedCountyAddress = null
            mFragmentDataBinding.mSelectAddressTitleCountyTv.text = null
            mCountyAdapterCommon.setSelectedPosition(-1)
            mCountyAdapterCommon.setNewData(null)
            // 切换
            switchTabAndUpdateContent(1)
        }
        mCityAdapterCommon.setOnItemClickListener { adapter, _, position ->
            // 记录选中并切换到区县
            mSelectedCityAddress = adapter.getItem(position) as AddressDataBean?// 保存选中值
            mFragmentDataBinding.mSelectAddressTitleCityTv.text = mSelectedCityAddress?.provideText()// 设置选中的值
            mCityAdapterCommon.setSelectedPositionAndNotify(position)// 通知选中
            // 清空县
            mSelectedCountyAddress = null
            mFragmentDataBinding.mSelectAddressTitleCountyTv.text = null
            mCountyAdapterCommon.setSelectedPosition(-1)
            mCountyAdapterCommon.setNewData(null)
            // 切换
            switchTabAndUpdateContent(2)
        }
        mCountyAdapterCommon.setOnItemClickListener { adapter, _, position ->
            // 记录选中
            mSelectedCountyAddress = adapter.getItem(position) as AddressDataBean?// 保存选中值
            mFragmentDataBinding.mSelectAddressTitleCountyTv.text = mSelectedCountyAddress?.provideText()// 设置选中的值
            mCountyAdapterCommon.setSelectedPositionAndNotify(position)// 通知选中
        }
        // 设置默认获取省份数据
        switchTabAndUpdateContent(0)
    }

    /**
     * 切换tab
     */
    private fun switchTab(tabIndex: Int) {
        // 检测上一级，是否为选中，没选中不能跳转
        when (tabIndex) {
            1 -> if (mSelectedProvinceAddress == null) return// 市栏目，没选中省，不能点击
            2 -> if (mSelectedCityAddress == null) return// 区县栏目，没选中市，不能点击
        }
        val mAdapter = when (tabIndex) {
            0 -> mProvinceAdapterCommon
            1 -> mCityAdapterCommon
            else -> mCountyAdapterCommon
        }
        // 切换
        mFragmentDataBinding.mSelectAddressContentRv.adapter = mAdapter
       val left =  (resources.getDimension(R.dimen.sw_30dp) + resources.getDimension(R.dimen.sw_100dp) * tabIndex).toInt()
        LogUtil.e("======================$left")
        // 设置指示器位置
        val layoutParams = mFragmentDataBinding.mSelectAddressIndicatorView.layoutParams as ConstraintLayout.LayoutParams
        layoutParams.setMargins(left, layoutParams.topMargin, layoutParams.rightMargin, layoutParams.bottomMargin)
        mFragmentDataBinding.mSelectAddressIndicatorView.layoutParams = layoutParams
        // 滚动到上一个位置
        mFragmentDataBinding.mSelectAddressContentRv.scrollToPosition(mAdapter.getSelectedPosition())
        // 设置选中栏目如果没选择则文字加深
        when (tabIndex) {
            0 -> {
                // 选中的是省，判断省、市、区县有没有选中
                if (mSelectedProvinceAddress == null) mFragmentDataBinding.mSelectAddressTitleProvinceTv.text = "Por favor elige"// 省栏目，没选省，颜色加深
                if (mSelectedCityAddress == null) mFragmentDataBinding.mSelectAddressTitleCityTv.text = null// 市栏目，没选市，颜色加深
                if (mSelectedCountyAddress == null) mFragmentDataBinding.mSelectAddressTitleCountyTv.text = null// 区县栏目，没选区县，颜色加深
            }
            1 -> {
                // 选中的是市，判断市、区县有没有选中
                if (mSelectedCityAddress == null) mFragmentDataBinding.mSelectAddressTitleCityTv.text = "Ciudad"// 市栏目，没选市，颜色加深
                if (mSelectedCountyAddress == null) mFragmentDataBinding.mSelectAddressTitleCountyTv.text = null// 区县栏目，没选区县，颜色加深
            }
            2 -> {
                // 选中的是区县，判断区县有没有选中
                if (mSelectedCountyAddress == null) mFragmentDataBinding.mSelectAddressTitleCountyTv.text = "Distrito de"// 区县栏目，没选区县，颜色加深
            }
        }
    }

    /**
     * 切换tab并内容
     */
    private fun switchTabAndUpdateContent(tabIndex: Int) {
        val mAdapterCommon: AddressAdapter
        when (tabIndex) {
            0 -> {
                // 省
                mAdapterCommon = mProvinceAdapterCommon
                unusualLaserSolidBallet = "1"
            }
            1 -> {
                // 市
                mAdapterCommon = mCityAdapterCommon
                unimportantBirdcageDoubleApology = mSelectedProvinceAddress?.neitherImportanceMadBoot ?: ""
                unusualLaserSolidBallet = "2"
            }
            else -> {
                // 区县
                mAdapterCommon = mCountyAdapterCommon
                unimportantBirdcageDoubleApology = mSelectedCityAddress?.neitherImportanceMadBoot ?: ""
                unusualLaserSolidBallet = "3"
            }
        }
        // 切换tab
        switchTab(tabIndex)

        val hashMap: HashMap<String, String> = BaseFormDataUtils.getBaseHasMap(1)
        hashMap["unimportantBirdcageDoubleApology"] = unimportantBirdcageDoubleApology
        hashMap["unusualLaserSolidBallet"] = unusualLaserSolidBallet
        lifecycleScope.launch {
            convertReqExecute({ appApi.provinceCity(hashMap) }, onSuccess = {
                mAdapterCommon.setList(it)
            })

        }
    }

    private fun configSelect() {
        when {
            mSelectedProvinceAddress == null -> {
                ToastUtil.showShort("Por favor elige")
                return
            }
            mSelectedCityAddress == null -> {
                ToastUtil.showShort("Por favor elige")
                return
            }
            mSelectedCountyAddress == null -> {
                ToastUtil.showShort("Por favor elige")
                return
            }
            else -> {
                dismiss()
                onSelectAddressFinishListener?.onSelectAddressFinish(mSelectedProvinceAddress!!, mSelectedCityAddress!!, mSelectedCountyAddress!!)
            }
        }
    }

    interface OnSelectAddressFinishListener {
        /**
         * [selectedProvinceAddress] 选中的省
         * [selectedCityAddress] 选中的市
         * [selectedCountyAddress] 选中的区县
         */
        fun onSelectAddressFinish(selectedProvinceAddress: AddressDataBean, selectedCityAddress: AddressDataBean, selectedCountyAddress: AddressDataBean)
    }

    override fun getLayoutId(): Int {
        return R.layout.common_dialog_fragment_select_address
    }

    override fun initListener() {
    }

    override fun processingLogic() {
    }

}
