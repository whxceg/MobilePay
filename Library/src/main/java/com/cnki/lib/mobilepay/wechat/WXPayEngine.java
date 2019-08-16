package com.cnki.lib.mobilepay.wechat;

import android.content.Context;
import com.cnki.lib.mobilepay.PayAction;
import com.cnki.lib.mobilepay.PayConfig;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

/**
 * Created by Administrator on 2016/7/25.
 */
public class WXPayEngine {

    private static PayReq mWxPayReq;
    private static IWXAPI mIWXAPI;

    public static void initWX(Context context) {
        mIWXAPI = WXAPIFactory.createWXAPI(context, null);
        mWxPayReq = new PayReq();
        mIWXAPI.registerApp(PayConfig.WeiXin_APP_ID);
    }

    /**
     * 调起微信支付操作
     */
    public static void pay(String prepayId, String nonceStr, String timeStamp, String sign) {

//        mWxTradeNum = entity.getTradeNumber();
        //封装微信支付参数
        mWxPayReq.appId = PayConfig.WeiXin_APP_ID;
        mWxPayReq.partnerId = PayConfig.WeiXin_MCH_ID;
        mWxPayReq.prepayId = prepayId;
        mWxPayReq.packageValue = "Sign=WXPay";
        mWxPayReq.nonceStr = nonceStr;
        mWxPayReq.timeStamp = timeStamp;
        mWxPayReq.sign = sign;
        //调起微信支付
//        mIWXAPI.registerApp(Constant.APP_ID);
        mIWXAPI.sendReq(mWxPayReq);
    }

    public static void pay(String prepayId, String nonceStr, String timeStamp, String sign, PayAction action) {
        mAction = action;
//        mWxTradeNum = entity.getTradeNumber();
        //封装微信支付参数
        mWxPayReq.appId = PayConfig.WeiXin_APP_ID;
        mWxPayReq.partnerId = PayConfig.WeiXin_MCH_ID;
        mWxPayReq.prepayId = prepayId;
        mWxPayReq.packageValue = "Sign=WXPay";
        mWxPayReq.nonceStr = nonceStr;
        mWxPayReq.timeStamp = timeStamp;
        mWxPayReq.sign = sign;
        //调起微信支付
//        mIWXAPI.registerApp(Constant.APP_ID);
        mIWXAPI.sendReq(mWxPayReq);
    }

    public static void cancel() {
        if (mAction != null) {
            mAction = null;
        }
    }

    static void paySuccess() {
        if (mAction != null) {
            mAction.paySuccess();
            mAction = null;
        }
    }

    static void payFailure() {
        if (mAction != null) {
            mAction.payFailure("支付失败");
            mAction = null;
        }
    }

    /**
     * 判断设备是否支持微信支付(支持返回true, 未安装微信或当前微信不支持支付API 返回false)
     */


    public static boolean isWXAppInstalled() {
        return mIWXAPI.isWXAppInstalled();
    }

    private static PayAction mAction;


}
