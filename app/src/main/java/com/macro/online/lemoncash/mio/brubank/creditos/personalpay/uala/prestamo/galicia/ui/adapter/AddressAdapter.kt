package com.macro.online.lemoncash.mio.brubank.creditos.personalpay.uala.prestamo.galicia.ui.adapter

import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder
import com.macro.online.lemoncash.mio.brubank.creditos.personalpay.uala.prestamo.galicia.R
import com.macro.online.lemoncash.mio.brubank.creditos.personalpay.uala.prestamo.galicia.api.rep.AddressDataBean
import com.macro.online.lemoncash.mio.brubank.creditos.personalpay.uala.prestamo.galicia.databinding.CommonItemSelectAddressBinding
import com.moufans.lib_base.base.adapter.BaseAdapter

class AddressAdapter(layoutResId: Int = R.layout.common_item_select_address, data: MutableList<AddressDataBean> = mutableListOf()) : BaseAdapter<AddressDataBean, CommonItemSelectAddressBinding>(layoutResId, data) {
    private var selectedPosition = -1

    override fun convert(holder: BaseDataBindingHolder<CommonItemSelectAddressBinding>, position: Int, item: AddressDataBean) {

        holder.dataBinding?.apply {
            mAddressTitleTv.text = item.provideText()
        }
    }

    fun setSelectedPositionAndNotify(position: Int) {
        val oldSelectedPosition = selectedPosition
        this.selectedPosition = position
        notifyItemChanged(oldSelectedPosition)// 通知老的
        notifyItemChanged(position)// 通知新的
    }

    fun setSelectedPosition(position: Int) {
        this.selectedPosition = position
    }

    fun getSelectedPosition() = selectedPosition
}