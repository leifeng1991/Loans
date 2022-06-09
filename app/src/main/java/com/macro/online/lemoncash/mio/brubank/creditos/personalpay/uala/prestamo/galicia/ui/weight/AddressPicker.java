/*
 * Copyright (c) 2016-present 贵州纳雍穿青人李裕江<1032694760@qq.com>
 *
 * The software is licensed under the Mulan PSL v2.
 * You can use this software according to the terms and conditions of the Mulan PSL v2.
 * You may obtain a copy of Mulan PSL v2 at:
 *     http://license.coscl.org.cn/MulanPSL2
 * THIS SOFTWARE IS PROVIDED ON AN "AS IS" BASIS, WITHOUT WARRANTIES OF ANY KIND, EITHER EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO NON-INFRINGEMENT, MERCHANTABILITY OR FIT FOR A PARTICULAR
 * PURPOSE.
 * See the Mulan PSL v2 for more details.
 */

package com.macro.online.lemoncash.mio.brubank.creditos.personalpay.uala.prestamo.galicia.ui.weight;

import android.app.Activity;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.StyleRes;

import com.github.gzuliyujiang.wheelpicker.LinkagePicker;
import com.github.gzuliyujiang.wheelpicker.contract.LinkageProvider;
import com.github.gzuliyujiang.wheelpicker.contract.OnCarPlatePickedListener;
import com.github.gzuliyujiang.wheelpicker.contract.OnLinkagePickedListener;
import com.github.gzuliyujiang.wheelpicker.widget.CarPlateWheelLayout;

@SuppressWarnings({"unused"})
public class AddressPicker extends LinkagePicker {
    private OnCarPlatePickedListener onCarPlatePickedListener;

    public AddressPicker(@NonNull Activity activity) {
        super(activity);
    }

    public AddressPicker(@NonNull Activity activity, @StyleRes int themeResId) {
        super(activity, themeResId);
    }

    @Deprecated
    @Override
    public void setData(@NonNull LinkageProvider data) {
        throw new UnsupportedOperationException("Data already preset");
    }

    @Deprecated
    @Override
    public void setOnLinkagePickedListener(OnLinkagePickedListener onLinkagePickedListener) {
        throw new UnsupportedOperationException("Use setOnCarPlatePickedListener instead");
    }

    @NonNull
    @Override
    protected View createBodyView() {
        wheelLayout = new AddressWheelLayout(activity);
        return wheelLayout;
    }

    @Override
    protected void onOk() {
        if (onCarPlatePickedListener != null) {
            String province = wheelLayout.getFirstWheelView().getCurrentItem();
            String letter = wheelLayout.getSecondWheelView().getCurrentItem();
            onCarPlatePickedListener.onCarNumberPicked(province, letter);
        }
    }


    public void setOnCarPlatePickedListener(OnCarPlatePickedListener onCarPlatePickedListener) {
        this.onCarPlatePickedListener = onCarPlatePickedListener;
    }

}
