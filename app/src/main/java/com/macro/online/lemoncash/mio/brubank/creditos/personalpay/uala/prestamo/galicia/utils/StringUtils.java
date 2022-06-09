package com.macro.online.lemoncash.mio.brubank.creditos.personalpay.uala.prestamo.galicia.utils;

import android.text.TextUtils;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class StringUtils {
    public static String bigDecimalMoney(String money) {
        if (!TextUtils.isEmpty(money)) {
            BigDecimal bd = new BigDecimal(Integer.parseInt(money));
            DecimalFormat df = new DecimalFormat(",###,####.00");
            return df.format(bd);
        }
        return "";
    }

    public static String bigDecimalMoneyZero(String money) {
        if (!TextUtils.isEmpty(money)) {
            BigDecimal bd = new BigDecimal(Integer.parseInt(money));
            DecimalFormat df = new DecimalFormat(",###,####");
            return df.format(bd);
        }
        return "";
    }
}
