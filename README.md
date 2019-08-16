

#### MobilePay 集成微信和支付宝支付

#### 使用说明

##### 1、在工程build.gradle中添加MobilePayPlugin引用 classpath 'com.github.whxceg:MobilePayPlugin:0.0.1-alpha02'


```
buildscript {
    repositories {
        maven { url 'https://www.jitpack.io' }
    }
    dependencies {
        classpath 'com.android.tools.build:gradle:3.2.1'
        classpath "org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlin_version"
        classpath 'com.github.whxceg:MobilePayPlugin:0.0.1-alpha02'
    }
}

```

##### 2、在项目build.gradle中添加MobilePay引用和支付参数配置


```
dependencies {
    implementation 'com.github.whxceg:MobilePay:0.0.1-beta04'
}

apply plugin: 'mobile-pay'

mobilePay{
    ali{
        appid = 'you appid'
        notify = 'you notify url'
        partner = 'you partner'
        rsaprivate = 'you rsa'
        seller = 'you seller'
    }
    wechat{
        appid = 'you appid'
        mchid = 'you mchid'
    }
}
```

##### 3、支付宝调用支付调用

```
MobilePay.aliPay(activity,subject,body,price,orderNo,new PayAction(){
    void paySuccess(){}
    void paying(){}
    void payFailure(String status){}
})

```

##### 4、微信调用支付调用

###### 1、创建 {applicationId}.wxapi.WXPayEntryActivity继承BaseWXPayEntryActivity
###### 2、在AndroidManifest注册WXPayEntryActivity
###### 3、调起微信前先初始化

```
MobilePay.initWX(context)

```
###### 4、调起微信
```
MobilePay.wxPay(prepayId,nonceStr,timeStamp,sign,new PayAction(){
    void paySuccess(){}
    void paying(){}
    void payFailure(String status){}
 })

```
##### 5、添加混淆

```
# 支付宝支付
-dontwarn com.alipay.**
-keep class com.alipay.**{*;}
-dontwarn com.ta.utdid2.**
-keep class com.ta.utdid2.**{*;}
-dontwarn com.json.alipay.**
-keep class com.json.alipay.**{*;}


# 微信支付
-dontwarn com.tencent.**
-keep class com.tencent.** { *; }

```
