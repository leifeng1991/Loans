package com.base.devices.data;

import static com.base.devices.utils.OtherUtils.checkDeviceHasNavigationBar;
import static com.base.devices.utils.OtherUtils.checkVPN;
import static com.base.devices.utils.OtherUtils.getBootTime;
import static com.base.devices.utils.OtherUtils.getHostAndPort;
import static com.base.devices.utils.OtherUtils.getIsWifiProxy;
import static com.base.devices.utils.OtherUtils.getMobileDbm;
import static com.base.devices.utils.OtherUtils.getPhoneMode;
import static com.base.devices.utils.OtherUtils.getProxyAddress;
import static com.base.devices.utils.OtherUtils.isAirplaneModeOn;
import static com.base.devices.utils.OtherUtils.isAppDebug;
import static com.base.devices.utils.OtherUtils.isAppRoot;
import static com.base.devices.utils.OtherUtils.isEmulator;
import static com.base.devices.utils.OtherUtils.isMockLocation;

public class OtherData {


    /**
     * root_jailbreak : 1
     * simulator : 0
     * emulator : 0
     * keyboard : 1
     * dbm : -105
     * last_boot_time : 1598955552645
     * is_using_proxy_port : false
     * is_using_vpn : false
     * is_usb_debug : false
     * elapsed_realtime : 394093510
     * is_mock_location : false
     */

    public int root_jailbreak;
    public int simulator;
    public int keyboard;
    public int ringer_mode;
    public String dbm;
    public long last_boot_time;
    public String http_proxy_host_port;
    public String vpn_address;
    public int is_using_proxy_port;
    public int is_using_vpn;
    public int is_usb_debug;
    public int is_mock_location;
    public int is_airplane_mode;

    {
        root_jailbreak = isAppRoot();
        simulator = isEmulator();
        keyboard = checkDeviceHasNavigationBar();
        ringer_mode = getPhoneMode();
        dbm = getMobileDbm();
        last_boot_time = getBootTime();
        is_usb_debug = isAppDebug();
        is_using_proxy_port = getIsWifiProxy();
        is_using_vpn = checkVPN();
        vpn_address = getProxyAddress();
        http_proxy_host_port = getHostAndPort();
        is_mock_location = isMockLocation();
        is_airplane_mode = isAirplaneModeOn();

    }

}
