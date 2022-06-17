package com.macro.online.lemoncash.mio.brubank.creditos.personalpay.uala.prestamo.galicia.ui.adapter

import android.view.View
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder
import com.macro.online.lemoncash.mio.brubank.creditos.personalpay.uala.prestamo.galicia.R
import com.macro.online.lemoncash.mio.brubank.creditos.personalpay.uala.prestamo.galicia.databinding.ItemLayoutHomeCardBinding
import com.macro.online.lemoncash.mio.brubank.creditos.personalpay.uala.prestamo.galicia.databinding.ItemLayoutSelectDayBinding
import com.macro.online.lemoncash.mio.brubank.creditos.personalpay.uala.prestamo.galicia.ui.bean.SelectDayBean
import com.moufans.lib_base.base.adapter.BaseAdapter

class SelectDayAdapter(layoutResId: Int = R.layout.item_layout_select_day, data: MutableList<SelectDayBean> = mutableListOf()) : BaseAdapter<SelectDayBean, ItemLayoutSelectDayBinding>(layoutResId, data) {
    var mSelectPosition = 0

    override fun convert(holder: BaseDataBindingHolder<ItemLayoutSelectDayBinding>, position: Int, item: SelectDayBean) {

        holder.dataBinding?.apply {
            mProPreOneTextView.visibility = if (item.isCanSelect) View.INVISIBLE else View.VISIBLE
            mLockImageView.visibility = if (item.isCanSelect) View.GONE else View.VISIBLE
            mDayThreeTextView.text = item.day
            mDayThreeTextView.isSelected = mSelectPosition == position
        }
    }
}