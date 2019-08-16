package com.cnki.lib.mobilepay.Sample

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.cnki.lib.mobilepay.PayConfig

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val textView = findViewById<TextView>(R.id.tv)

        textView.text = "payconfig \n wechat \n" +
                " appid -> ${PayConfig.WeiXin_APP_ID},\n" +
                " machid -> ${PayConfig.WeiXin_MCH_ID} ,\n" +
                " appid -> ${PayConfig.AliPay_APPID} ,\n" +
                " partner -> ${PayConfig.AliPay_PARTNER} ,\n" +
                " notify -> ${PayConfig.AliPay_NOTIFY_URL} ,\n" +
                " seller -> ${PayConfig.AliPay_SELLER} ,\n" +
                " rsa -> ${PayConfig.AliPay_RSA_PRIVATE} ,\n"

    }
}
