package com.cnki.lib.mobilepay;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.Keep;
import android.text.TextUtils;
import android.util.Log;
import com.cnki.lib.mobilepay.alipay.AliPayEngine;
import com.cnki.lib.mobilepay.wechat.WXPayEngine;

/**
 *
 */
@Keep
public class MobilePay {

    public static void initWX(Context context) {
        if (isConfigWeChat()) {
            WXPayEngine.initWX(context);
        } else {
            Log.e("MobilePay", "initWX: error please config pay params ");
        }
    }

    public static boolean isWXAppInstalled() {
        return WXPayEngine.isWXAppInstalled();
    }

    public static void wxPay(String prepayId, String nonceStr, String timeStamp, String sign, PayAction action) {
        if (isConfigWeChat()) {
            WXPayEngine.pay(prepayId, nonceStr, timeStamp, sign, action);
        } else {
            Log.e("MobilePay", "wxPay: error please config pay params ");
        }
    }

    public static void aliPay(Activity activity, String subject, String body, String price, String orderNo, PayAction payAction) {
        if (isConfigAliPay()) {
            AliPayEngine.pay(activity, subject, body, price, orderNo, payAction);
        } else {
            Log.e("MobilePay", "aliPay: error please config pay params ");
        }
    }

    private static boolean isConfigWeChat() {
        return !TextUtils.isEmpty(PayConfig.WeiXin_APP_ID) && !TextUtils.isEmpty(PayConfig.WeiXin_APP_ID);
    }

    private static boolean isConfigAliPay() {
        return !TextUtils.isEmpty(PayConfig.AliPay_APPID) &&
                !TextUtils.isEmpty(PayConfig.AliPay_NOTIFY_URL) &&
                !TextUtils.isEmpty(PayConfig.AliPay_PARTNER) &&
                !TextUtils.isEmpty(PayConfig.AliPay_SELLER) &&
                !TextUtils.isEmpty(PayConfig.AliPay_RSA_PRIVATE);
    }

}
