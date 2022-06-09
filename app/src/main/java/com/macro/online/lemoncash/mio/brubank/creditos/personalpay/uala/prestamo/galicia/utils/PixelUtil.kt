package com.macro.online.lemoncash.mio.brubank.creditos.personalpay.uala.prestamo.galicia.utils

import android.content.Context
import android.content.res.Resources
import android.graphics.Point
import android.os.Build
import android.util.DisplayMetrics
import android.view.WindowManager
import java.math.BigDecimal
import java.text.DecimalFormat

/**
 * 手机显示屏相关的工具类
 *
 * @author zhangrq
 */
@Suppress("unused")
object PixelUtil {
    private val STATUS_BAR_HEIGHT_RES_NAME = "status_bar_height"
    private val NAV_BAR_HEIGHT_RES_NAME = "navigation_bar_height"
    private val NAV_BAR_HEIGHT_LANDSCAPE_RES_NAME = "navigation_bar_height_landscape"
    /**
     * 将px值转换为dip或dp值，保证尺寸大小不变
     */
    @JvmStatic
    fun px2dip(context: Context, pxValue: Float): Int {
        return (pxValue / getDensity(context) + 0.5f).toInt()
    }

    /**
     * 将dip或dp值转换为px值，保证尺寸大小不变
     */
    @JvmStatic
    fun dip2px(context: Context, dipValue: Float): Int {
        return (dipValue * getDensity(context) + 0.5f).toInt()
    }

    /**
     * 将px值转换为sp值，保证文字大小不变
     */
    @JvmStatic
    fun px2sp(context: Context, pxValue: Float): Int {
        return (pxValue / getScaledDensity(context) + 0.5f).toInt()
    }

    /**
     * 将sp值转换为px值，保证文字大小不变
     */
    @JvmStatic
    fun sp2px(context: Context, spValue: Float): Int {
        return (spValue * getScaledDensity(context) + 0.5f).toInt()
    }

    /**
     * 获取按比例缩放的屏幕密度
     */
    @JvmStatic
    fun getScaledDensity(context: Context): Float {
        return context.resources.displayMetrics.scaledDensity
    }

    /**
     * 获取屏幕密度
     */
    @JvmStatic
    fun getDensity(context: Context): Float {
        return context.resources.displayMetrics.density
    }

    /**
     * 获取屏幕宽度
     */
    @JvmStatic
    fun getScreenWidth(context: Context): Int {
        return getDisplayMetrics(context).widthPixels
    }

    /**
     * 获取屏幕高度
     */
    @JvmStatic
    fun getScreenHeight(context: Context): Int {
        return getDisplayMetrics(context).heightPixels
    }

    /**
     * 获取显示信息
     */
    @JvmStatic
    fun getDisplayMetrics(context: Context): DisplayMetrics {
        val wm = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val dm = DisplayMetrics()
        wm.defaultDisplay.getMetrics(dm)
        return dm
    }

    /**
     * 获取屏幕真正的宽
     */
    fun getScreenRealWidth(context: Context) = getRealSize(context).x

    /**
     * 获取屏幕真正的高
     */
    fun getScreenRealHeight(context: Context) = getRealSize(context).y

    /**
     * 获取真正的大小
     */
    private fun getRealSize(context: Context): Point {
        val wm = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val display = wm.defaultDisplay
        val outPoint = Point()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            // 可能有虚拟按键的情况
            display.getRealSize(outPoint)
        } else {
            // 不可能有虚拟按键
            display.getSize(outPoint)
        }
        return outPoint
    }

    /**
     * 获取状态栏高度
     */
    fun getStatusBarHeight(context: Context) = getInternalDimensionSize(context, STATUS_BAR_HEIGHT_RES_NAME)

    /**
     * 获取导航栏高度
     */
    fun getNavigationBarHeight(context: Context) = getInternalDimensionSize(context, NAV_BAR_HEIGHT_RES_NAME)

    /**
     * 获取水平页面导航栏高度
     */
    fun getNavigationBarLandscapeHeight(context: Context) = getInternalDimensionSize(context, NAV_BAR_HEIGHT_LANDSCAPE_RES_NAME)

    private fun getInternalDimensionSize(context: Context, key: String): Int {
        val result = 0
        try {
            val resourceId = Resources.getSystem().getIdentifier(key, "dimen", "android")
            if (resourceId > 0) {
                val sizeOne = context.resources.getDimensionPixelSize(resourceId)
                val sizeTwo = Resources.getSystem().getDimensionPixelSize(resourceId)

                return if (sizeTwo >= sizeOne) {
                    sizeTwo
                } else {
                    val densityOne = context.resources.displayMetrics.density
                    val densityTwo = Resources.getSystem().displayMetrics.density
                    Math.round(sizeOne * densityTwo / densityOne)
                }
            }
        } catch (ignored: Resources.NotFoundException) {
            return 0
        }
        return result
    }

    /**
     * 给金额显示添加千分位","
     *
     * @param val 金额
     */
    @JvmStatic
    fun parseMoney(`val`: Any?): String {
        val pattern = "##,###,##0.00"
        if (`val` == null || `val` == "")
            return ""
        var valStr = `val`.toString() + ""
        val df = DecimalFormat(pattern)
        valStr = df.format(BigDecimal(valStr))
        return subZeroAndDot(valStr)
    }

    /**
     * 字符串数字的加法
     *
     * @param num1 数字1
     * @param num2 数字2
     */
    @JvmStatic
    fun stringAdd(num1: String, num2: String): String {
        val vNum1 = BigDecimal(num1)
        val vNum2 = BigDecimal(num2)
        return vNum1.add(vNum2).toString()
    }

    /**
     * 字符串数字的减法
     *
     * @param num1 数字1
     * @param num2 数字2
     */
    @JvmStatic
    fun stringSubstract(num1: String, num2: String): String {
        val vNum1 = BigDecimal(num1)
        val vNum2 = BigDecimal(num2)
        return vNum1.subtract(vNum2).toString()
    }

    /**
     * 字符串数字的乘法
     *
     * @param num1 数字1
     * @param num2 数字2
     */
    @JvmStatic
    fun stringMultiple(num1: String, num2: String): String {
        val vNum1 = BigDecimal(num1)
        val vNum2 = BigDecimal(num2)
        return vNum1.multiply(vNum2).toString()
    }

    /**
     * 小数的 取小数点和零 如 12.30 -> 12.3 | 23.00 -> 23
     */
    @JvmStatic
    fun subZeroAndDot(s: String): String {
        var value = s
        if (value.indexOf(".") > 0) {
            value = value.replace("0+?$".toRegex(), "")// 去掉多余的0
            value = value.replace("[.]$".toRegex(), "")// 如最后一位是.则去掉
        }
        return value
    }

}
