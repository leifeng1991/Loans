package com.macro.online.lemoncash.mio.brubank.creditos.personalpay.uala.prestamo.galicia.utils

import android.Manifest
import android.app.Activity
import android.text.TextUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.google.gson.Gson
import com.hjq.permissions.OnPermissionCallback
import com.hjq.permissions.XXPermissions
import com.macro.online.lemoncash.mio.brubank.creditos.personalpay.uala.prestamo.galicia.api.BaseFormDataUtils
import com.macro.online.lemoncash.mio.brubank.creditos.personalpay.uala.prestamo.galicia.api.ext.appApi
import com.macro.online.lemoncash.mio.brubank.creditos.personalpay.uala.prestamo.galicia.constants.AppConstants
import com.moufans.lib_base.ext.convertReqExecute
import kotlinx.coroutines.launch

object CallUtils {
    fun showCallDialog(activity: AppCompatActivity) {
        val xp = XXPermissions.with(activity)
        xp.permission(Manifest.permission.CALL_PHONE)
        xp.request(object : OnPermissionCallback {
            override fun onGranted(permissions: List<String>, all: Boolean) {
                if (all) {
                    val appInfoJson = SharedPrefUtil.get(AppConstants.APP_INFO, "")
                    if (!TextUtils.isEmpty(appInfoJson)) {
                        CommonDialogUtil.showCallPhone(activity)
                    } else {
                        getAppInfo(activity)
                    }

                }
            }

            override fun onDenied(permissions: List<String>, never: Boolean) {
                if (never) {
                    // 如果是被永久拒绝就跳转到应用权限系统设置页面
                    XXPermissions.startPermissionActivity(activity, permissions)
                }
            }
        })

    }

    private fun getAppInfo(activity: AppCompatActivity) {
        activity.lifecycleScope.launch {
            val hashMap: HashMap<String, String> = BaseFormDataUtils.getBaseHasMap()
            convertReqExecute({ appApi.appInfo(hashMap) }, onSuccess = {
                SharedPrefUtil.put(AppConstants.APP_INFO, Gson().toJson(it))
                CommonDialogUtil.showCallPhone(activity)
            })
        }
    }
}