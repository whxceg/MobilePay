package com.cnki.lib.mobilepay;

import android.support.annotation.Keep;

/**
 * Created by Administrator on 2016/9/23.
 */

@Keep
public interface PayAction {
    void paySuccess();
    void paying();
    void payFailure(String status);
}
