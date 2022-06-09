package com.base.devices.data;

import android.text.TextUtils;

import com.base.devices.utils.LocationManagerUtils;

public class LocationAddressData {

    String longitude = "";
    String latitude = "";
    String gps_address_city = "";
    String gps_address_street = "";
    String gps_address_province  = "";


    public LocationAddressData() {
        LocationManagerUtils locationManagerUtils = new LocationManagerUtils();
        this.latitude = locationManagerUtils.latitude;
        this.longitude = locationManagerUtils.longitude;
        this.gps_address_street = locationManagerUtils.address_details;
        this.gps_address_city = locationManagerUtils.city;
        this.gps_address_province  = locationManagerUtils.provice;
    }


    public boolean locationIsNull() {
        if (TextUtils.isEmpty(longitude) && TextUtils.isEmpty(longitude)) {
            return true;
        } else {
            return false;
        }
    }

}
