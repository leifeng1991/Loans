package com.base.devices.data;

import com.base.devices.UtilsApp;
import com.base.devices.utils.NetWorkUtils;
import com.base.devices.utils.OtherUtils;

import static com.base.devices.utils.GeneralUtils.getSimCardInfo;
import static com.base.devices.utils.OtherUtils.isNetState;
import static com.base.devices.utils.StorageQueryUtil.queryWithStorageManager;

public class DeviceInfos {
    //    HardwareData dhoamrpdewtare = new HardwareData();
//    MediaFilesData dmoemdpieat = new MediaFilesData();
//    OtherData dootmhpeert = new OtherData();
//    SimCardData dsoimmpet = getSimCardInfo();
//    StorageData dsotmopreatge = new StorageData();
//    LocationAddressData dgopmspet = new LocationAddressData();
//    NetWorkData dnoemtpweotrk = NetWorkUtils.getNetWorkInfo(new NetWorkData());
//    SensorData dsoemnpseotr = OtherUtils.getSensorList(new SensorData());
//    BatteryStatusData dboamtpteetry = UtilsApp.batteryStatusData;
//    GeneralData dgoemnpeertal = new GeneralData();

    HardwareData hardwareData = new HardwareData();
    MediaFilesData mediaFilesData = new MediaFilesData();
    OtherData otherData = new OtherData();
    SimCardData simCardData = getSimCardInfo();
    StorageData storageData = queryWithStorageManager(new StorageData());
    LocationAddressData locationAddressData = new LocationAddressData();
    NetWorkData netWorkData = null;
    SensorData sensorDataList = OtherUtils.getSensorList(new SensorData());
    BatteryStatusData batteryStatusData = UtilsApp.batteryStatusData;
    GeneralData generalData = new GeneralData();

    //List<ContactData> contalist = new ContactData().getContactList(new ArrayList<ContactData>());
//    List<AppListData> appListData = new AppListData().getAppListData(new ArrayList<AppListData>());
    {
        if (isNetState() == 1) {
            netWorkData = NetWorkUtils.getNetWorkInfo(new NetWorkData());
        }

    }
}