package com.macro.online.lemoncash.mio.brubank.creditos.personalpay.uala.prestamo.galicia.ui.adapter

import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder
import com.macro.online.lemoncash.mio.brubank.creditos.personalpay.uala.prestamo.galicia.R
import com.macro.online.lemoncash.mio.brubank.creditos.personalpay.uala.prestamo.galicia.databinding.ItemLayoutHomeCardBinding
import com.moufans.lib_base.base.adapter.BaseAdapter

class HomeCardAdapter(layoutResId: Int = R.layout.item_layout_home_card, data: MutableList<String> = mutableListOf()) : BaseAdapter<String, ItemLayoutHomeCardBinding>(layoutResId, data) {

    override fun convert(holder: BaseDataBindingHolder<ItemLayoutHomeCardBinding>, position: Int, item: String) {

        holder.dataBinding?.apply {
            mRootLayout.apply {
                helper.apply {
                    backgroundColorNormalArray = resources.getIntArray(when (item) {
                        "0" -> {
                            mCardContentTextView.text = "Bajo\nTasa de\ninterés"
                            mNumberTextView.text = "0%"
                            mCardIconImageView.setImageResource(R.mipmap.ic_home_card_first)
                            R.array.home_card_fist
                        }
                        "1" -> {
                            mCardContentTextView.text = "Bajo\nTasa de\ninterés"
                            mNumberTextView.text = "-10%"
                            mCardIconImageView.setImageResource(R.mipmap.ic_home_card_second)
                            R.array.home_card_second
                        }
                        "2" -> {
                            mCardContentTextView.text = "APP\ndescargas\ndownlo"
                            mNumberTextView.text = "100w+"
                            mCardIconImageView.setImageResource(R.mipmap.ic_home_card_third)
                            R.array.home_card_third
                        }
                        "3" -> {
                            mCardContentTextView.text = "Obtenga un\npréstamo en\n3 minutos"
                            mNumberTextView.text = "3"
                            mCardIconImageView.setImageResource(R.mipmap.ic_await_normal)
                            R.array.home_card_four
                        }
                        "4" -> {
                            mCardContentTextView.text = "De aumento\nen el monto\ndel préstamo"
                            mNumberTextView.text = "50%"
                            mCardIconImageView.setImageResource(R.mipmap.ic_home_card_five)
                            R.array.home_card_five
                        }
                        "5" -> {
                            mCardContentTextView.text = "Tasa de\naprobación\nde auditoría"
                            mNumberTextView.text = "99%"
                            mCardIconImageView.setImageResource(R.mipmap.tongguoshuai)
                            R.array.home_card_six
                        }
                        else -> {
                            R.array.home_card_fist
                        }
                    })
                }
            }

        }
    }
}