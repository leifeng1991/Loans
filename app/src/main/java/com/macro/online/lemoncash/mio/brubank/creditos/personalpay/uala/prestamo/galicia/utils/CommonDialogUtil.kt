package com.macro.online.lemoncash.mio.brubank.creditos.personalpay.uala.prestamo.galicia.utils

import android.R.attr.phoneNumber
import android.app.Activity
import android.app.AlertDialog
import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.haibin.calendarview.Calendar
import com.haibin.calendarview.CalendarView
import com.macro.online.lemoncash.mio.brubank.creditos.personalpay.uala.prestamo.galicia.R
import com.macro.online.lemoncash.mio.brubank.creditos.personalpay.uala.prestamo.galicia.ui.adapter.RecommendOtherProductsAdapter


object CommonDialogUtil {

    fun showRawDialogNoButton(activity: Activity, message: String) {
        AlertDialog.Builder(activity)
            .setTitle("提示")
            .setMessage(message)
            .show()
    }

    @JvmStatic
    fun showDeferredPaymentDialog(activity: Activity): Dialog {
        val loadDataView = View.inflate(activity, R.layout.dialog_layout_deferred_payment, null)

        val dialogBuilder = DialogBuilder
            .create(activity)
            .setView(loadDataView)
            .setWidthScale(0.92)
            .show()
        // 左侧按钮点击事件
        dialogBuilder.setCancelable(false)

        loadDataView.findViewById<ImageView>(R.id.mCloseImageView).setOnClickListener {
            dialogBuilder.dismiss()
        }
        return dialogBuilder.dialog
    }

    fun showConfirmAmountDialog(activity: Activity): Dialog {
        val loadDataView = View.inflate(activity, R.layout.dialog_layout_confirm_amount, null)

        val dialogBuilder = DialogBuilder
            .create(activity)
            .setView(loadDataView)
            .setWidthScale(0.92)
            .show()
        // 左侧按钮点击事件
        dialogBuilder.setCancelable(false)

        loadDataView.findViewById<ImageView>(R.id.mCloseImageView).setOnClickListener {
            dialogBuilder.dismiss()
        }

        loadDataView.findViewById<TextView>(R.id.mLeftTextView).setOnClickListener {
            dialogBuilder.dismiss()
        }
        loadDataView.findViewById<TextView>(R.id.mRightTextView).setOnClickListener {
            showConfirmAmountCountdownDialog(activity)
            dialogBuilder.dismiss()
        }
        return dialogBuilder.dialog
    }

    fun showConfirmAmountCountdownDialog(activity: Activity): Dialog {
        val loadDataView = View.inflate(activity, R.layout.dialog_layout_confirm_amount_countdown, null)

        val dialogBuilder = DialogBuilder
            .create(activity)
            .setView(loadDataView)
            .setWidthScale(0.92)
            .show()
        // 左侧按钮点击事件
        dialogBuilder.setCancelable(false)

        loadDataView.findViewById<ImageView>(R.id.mCloseImageView).setOnClickListener {
            dialogBuilder.dismiss()
        }

        return dialogBuilder.dialog
    }

    fun showCallPhone(activity: Activity, phone: String): Dialog {
        val loadDataView = View.inflate(activity, R.layout.dialog_layout_call_phone, null)
        val mPhoneTextView = loadDataView.findViewById<TextView>(R.id.mPhoneTextView)
        mPhoneTextView.text = "+54 111-111111"

        val dialogBuilder = DialogBuilder
            .create(activity)
            .setView(loadDataView)
            .setWidthScale(0.79)
            .show()

        dialogBuilder.setCancelable(false)

        loadDataView.findViewById<ImageView>(R.id.mCloseImageView).setOnClickListener {
            dialogBuilder.dismiss()
        }

        loadDataView.findViewById<ViewGroup>(R.id.mCallLayout).setOnClickListener {
            activity.startActivity(Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + mPhoneTextView.text.toString())))
            dialogBuilder.dismiss()
        }


        return dialogBuilder.dialog
    }

    fun showRecommendOtherProductsDialog(activity: Activity): Dialog {
        val loadDataView = View.inflate(activity, R.layout.dialog_layout_recommend_other_products, null)
        val mRecyclerView = loadDataView.findViewById<RecyclerView>(R.id.mRecyclerView)
        mRecyclerView.layoutManager = LinearLayoutManager(activity)
        mRecyclerView.adapter = RecommendOtherProductsAdapter(data = mutableListOf<String>().apply {
            add("")
            add("")
            add("")
        })
        val dialogBuilder = DialogBuilder
            .create(activity)
            .setView(loadDataView)
            .setWidthScale(0.92)
            .show()
        // 左侧按钮点击事件
        dialogBuilder.setCancelable(false)

        loadDataView.findViewById<ImageView>(R.id.mCloseImageView).setOnClickListener {
            dialogBuilder.dismiss()
        }
        return dialogBuilder.dialog
    }


    fun showCalendarDialog(activity: Activity): Dialog {
        val loadDataView = View.inflate(activity, R.layout.dialog_layout_payment_successful, null)
        val mCalendarView = loadDataView.findViewById<CalendarView>(R.id.mCalendarView)
        val year: Int = mCalendarView.curYear
        val month: Int = mCalendarView.curMonth


        val map: MutableMap<String, Calendar> = HashMap()
        map[getSchemeCalendar(year, month, 3, Color.parseColor("#F98D4F"), "20").toString()] = getSchemeCalendar(year, month, 3, Color.parseColor("#F98D4F"), "20")
        map[getSchemeCalendar(year, month, 6, Color.parseColor("#F98D4F"), "33").toString()] = getSchemeCalendar(year, month, 6, Color.parseColor("#F98D4F"), "33")
        map[getSchemeCalendar(year, month, 9, Color.parseColor("#F98D4F"), "25").toString()] = getSchemeCalendar(year, month, 9, Color.parseColor("#F98D4F"), "25")
        map[getSchemeCalendar(year, month, 13, Color.parseColor("#F98D4F"), "50").toString()] = getSchemeCalendar(year, month, 13, Color.parseColor("#F98D4F"), "50")
        map[getSchemeCalendar(year, month, 14, Color.parseColor("#F98D4F"), "80").toString()] = getSchemeCalendar(year, month, 14, Color.parseColor("#F98D4F"), "80")
        map[getSchemeCalendar(year, month, 15, Color.parseColor("#F98D4F"), "20").toString()] = getSchemeCalendar(year, month, 15, Color.parseColor("#F98D4F"), "20")
        map[getSchemeCalendar(year, month, 18, Color.parseColor("#F98D4F"), "70").toString()] = getSchemeCalendar(year, month, 18, Color.parseColor("#F98D4F"), "70")
        map[getSchemeCalendar(year, month, 25, Color.parseColor("#F98D4F"), "36").toString()] = getSchemeCalendar(year, month, 25, Color.parseColor("#F98D4F"), "36")
        map[getSchemeCalendar(year, month, 27, Color.parseColor("#F98D4F"), "95").toString()] = getSchemeCalendar(year, month, 27, Color.parseColor("#F98D4F"), "95")
        //此方法在巨大的数据量上不影响遍历性能，推荐使用
        //此方法在巨大的数据量上不影响遍历性能，推荐使用
        mCalendarView.setSchemeDate(map)

        val dialogBuilder = DialogBuilder
            .create(activity)
            .setView(loadDataView)
            .setWidthScale(0.92)
            .show()
        // 左侧按钮点击事件
        dialogBuilder.setCancelable(false)

        loadDataView.findViewById<ImageView>(R.id.mCloseImageView).setOnClickListener {
            dialogBuilder.dismiss()
        }
        return dialogBuilder.dialog
    }

    private fun getSchemeCalendar(year: Int, month: Int, day: Int, color: Int, text: String): Calendar {
        val calendar = Calendar()
        calendar.year = year
        calendar.month = month
        calendar.day = day
        calendar.schemeColor = color //如果单独标记颜色、则会使用这个颜色
        calendar.scheme = text
        return calendar
    }

    interface OnButtonClickListener {
        fun onLeftButtonClick(dialog: DialogBuilder)

        fun onRightOrCenterButtonClick(dialog: DialogBuilder)
    }

}
