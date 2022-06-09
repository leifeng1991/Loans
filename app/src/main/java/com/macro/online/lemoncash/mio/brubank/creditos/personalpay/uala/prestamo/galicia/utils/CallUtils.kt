package com.macro.online.lemoncash.mio.brubank.creditos.personalpay.uala.prestamo.galicia.utils

import android.Manifest
import android.app.Activity
import com.hjq.permissions.OnPermissionCallback
import com.hjq.permissions.XXPermissions

object CallUtils {
    fun showCallDialog(activity: Activity) {
        val xp = XXPermissions.with(activity)
        xp.permission(Manifest.permission.CALL_PHONE)
        xp.request(object : OnPermissionCallback {
            override fun onGranted(permissions: List<String>, all: Boolean) {
                if (all) {
                    CommonDialogUtil.showCallPhone(activity, "")
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
}