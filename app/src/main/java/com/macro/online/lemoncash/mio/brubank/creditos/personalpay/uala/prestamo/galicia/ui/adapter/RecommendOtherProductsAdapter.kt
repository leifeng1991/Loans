package com.macro.online.lemoncash.mio.brubank.creditos.personalpay.uala.prestamo.galicia.ui.adapter

import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder
import com.macro.online.lemoncash.mio.brubank.creditos.personalpay.uala.prestamo.galicia.R
import com.macro.online.lemoncash.mio.brubank.creditos.personalpay.uala.prestamo.galicia.databinding.ItemLayoutRecommentOtherProductsBinding
import com.moufans.lib_base.base.adapter.BaseAdapter

class RecommendOtherProductsAdapter(layoutResId: Int = R.layout.item_layout_recomment_other_products, data: MutableList<String> = mutableListOf()) : BaseAdapter<String, ItemLayoutRecommentOtherProductsBinding>(layoutResId, data) {

    override fun convert(holder: BaseDataBindingHolder<ItemLayoutRecommentOtherProductsBinding>, position: Int, item: String) {

        holder.dataBinding?.apply {


        }
    }
}