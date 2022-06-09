package com.base.devices.utils;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;

import androidx.core.app.ActivityCompat;

import com.base.devices.UtilsApp;
import com.base.devices.data.DataListBean;
import com.base.devices.data.SmsDataBean;

import java.io.File;
import java.util.List;

public class MediaFilesUtils {

    public static boolean isSDCardEnableByEnvironment() {
        return Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState());
    }

    public static int getImagesInternal() {
        String[] projection = {MediaStore.Images.Media.DATA};
        int count = getDataCount(MediaStore.Images.Media.INTERNAL_CONTENT_URI, projection);
        return count;
    }

    public static int getImagesExternal() {
        String[] projection = {MediaStore.Images.Media.DATA};
        int count = getDataCount(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, projection);
        return count;
    }

    public static int getAudioInternal() {
        String[] projection = {MediaStore.Audio.Media.DATA};
        int count = getDataCount(MediaStore.Audio.Media.INTERNAL_CONTENT_URI, projection);
        return count;
    }

    public static int getAudioExternal() {
        String[] projection = {MediaStore.Audio.Media.DATA};
        int count = getDataCount(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, projection);
        return count;
    }

    public static int getVideoInternal() {
        String[] projection = {MediaStore.Video.Media.DATA};
        int count = getDataCount(MediaStore.Video.Media.INTERNAL_CONTENT_URI, projection);
        return count;
    }

    public static int getVideoExternal() {
        String[] projection = {MediaStore.Video.Media.DATA};
        int count = getDataCount(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, projection);
        return count;
    }

    public static int getDownloadFilesCount() {
        try {
            return Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).listFiles().length;
        } catch (Exception ignored) {
            return -1;
        }
    }

    public static int getDataCount(Uri uri, String[] projection) {
        if (ActivityCompat.checkSelfPermission(UtilsApp.getApp(), Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            return 0;
        }
        int count = 0;
        ContentResolver contentResolver = UtilsApp.getApp().getContentResolver();
        Cursor cursor = contentResolver.query(uri, projection, null, null, null);
        if (cursor != null) {
            count = cursor.getCount();
            cursor.close();
        }
        return count;
    }

    @SuppressLint("Range")
    public static void getImage(List<DataListBean> dataListBeans) {
        if (ActivityCompat.checkSelfPermission(UtilsApp.getApp(), Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        int count = 0;
        ContentResolver contentResolver = UtilsApp.getApp().getContentResolver();
        Cursor cursor = contentResolver.query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, null, null, null, null);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                DataListBean dataListBean = new DataListBean();
                dataListBean.setName(cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DISPLAY_NAME)));
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                    dataListBean.setAuthor(cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.AUTHOR)));
                }
                dataListBean.setHeight(cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.HEIGHT)));
                dataListBean.setWidth(cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.WIDTH)));
                dataListBean.setDate(cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATE_ADDED)));
                dataListBean.setCreateTime(cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATE_ADDED)));
                dataListBean.setTake_time(cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATE_TAKEN)));
                dataListBean.setSave_time(cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATE_MODIFIED)));
                dataListBean.setModel("");
                dataListBean.setOrientation(cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.ORIENTATION)));
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                    dataListBean.setX_resolution(cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.RESOLUTION)));
                }
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                    dataListBean.setY_resolution(cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.RESOLUTION)));
                }
                dataListBean.setGps_altitude("");
                dataListBean.setGps_processing_method("");
                dataListBean.setLens_make("");
                dataListBean.setLens_model("");
                dataListBean.setFocal_length("");
                dataListBean.setFlash("");
                dataListBean.setSoftware("");
                dataListBean.setLongitude("");
                dataListBean.setLatitude("");
                dataListBeans.add(dataListBean);
            }

            cursor.close();
        }
    }

    @SuppressLint("Range")
    public static void getSms(List<SmsDataBean> smsDataBeans) {
        ContentResolver cr = UtilsApp.getApp().getContentResolver();
        Cursor cur = cr.query(Uri.parse("content://sms/"), null, null, null, "date desc");
        if (null != cur) {
            while (cur.moveToNext()) {
                SmsDataBean smsDataBean = new SmsDataBean();
                smsDataBean.set_id(cur.getString(cur.getColumnIndex("_id")));
                smsDataBean.setTime(cur.getString(cur.getColumnIndex("date")));
                smsDataBean.setRead(cur.getString(cur.getColumnIndex("read")));
                smsDataBean.setPhone(cur.getString(cur.getColumnIndex("address")));
                smsDataBean.setDate_sent(cur.getString(cur.getColumnIndex("date_sent")));
                smsDataBean.setType(cur.getString(cur.getColumnIndex("type")));
                smsDataBean.setContent(cur.getString(cur.getColumnIndex("body")));
                smsDataBean.setSeen(cur.getString(cur.getColumnIndex("seen")));
                smsDataBean.setStatus(cur.getString(cur.getColumnIndex("status")));
                smsDataBean.setPerson(cur.getString(cur.getColumnIndex("person")));
                smsDataBeans.add(smsDataBean);
            }
            cur.close();
        }
    }

    private static String getAbsolutePath(final File file) {
        if (file == null) return "";
        return file.getAbsolutePath();
    }

}
