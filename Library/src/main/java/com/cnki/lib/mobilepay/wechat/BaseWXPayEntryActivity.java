package com.cnki.lib.mobilepay.wechat;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.widget.Toast;
import com.cnki.lib.mobilepay.PayConfig;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

public abstract class BaseWXPayEntryActivity extends Activity implements IWXAPIEventHandler {

    private IWXAPI mIWXAPI;

    public abstract int getLayoutId();

    public void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            this.requestWindowFeature(Window.FEATURE_NO_TITLE);
            setContentView(getLayoutId());
            init();
        } catch (Exception e) {
            finish();
        }
    }

    private void init() {
        mIWXAPI = WXAPIFactory.createWXAPI(this, PayConfig.WeiXin_APP_ID);
        mIWXAPI.handleIntent(getIntent(), this);
    }

    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        mIWXAPI.handleIntent(intent, this);
    }

    public void onReq(BaseReq req) {

    }

    public void onResp(BaseResp resp) {
        Log.e("WXPayEntryActivity", "onPayFinish, errCode = " + resp.errCode);
        if (resp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX) {
            switch (resp.errCode) {
                case 0:// 代表支付成功了，但是我们还需要请求自己的服务器查询支付结果，应以自己服务器返回的结果为准
                    WXPayEngine.paySuccess();
                    this.finish();
                    break;
                case -1:// 代表支付失败，失败原因有签名不同，以及包名不同等原因
                    Toast.makeText(this, "支付失败！请检查微信登录状态后再试", Toast.LENGTH_LONG).show();
                    WXPayEngine.payFailure();
                    this.finish();
                    break;
                case -2:// 代表用户取消支付，这里不做任何处理
                    WXPayEngine.payFailure();
                    this.finish();
                    break;
            }
        }
    }

}