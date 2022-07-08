package com.alwin

import android.app.Application
import android.util.Log
import com.alwin.util.SystemUtil
import com.facebook.drawee.backends.pipeline.Fresco

// @HiltAndroidApp
class App: Application() {
    override fun onCreate() {
        super.onCreate()
        SystemUtil.application = this
        Fresco.initialize(this)

//         ATSDK.setNetworkLogDebug(true) //SDK日志功能，集成测试阶段建议开启，上线前必须关闭
//
//         Log.i(TAG, "TopOn SDK version: " + ATSDK.getSDKVersionName()) //SDK版本
//
//         ATSDK.integrationChecking(applicationContext) //检查广告平台的集成状态
//
// //(v5.7.77新增) 打印当前设备的设备信息(IMEI、OAID、GAID、AndroidID等)
// //(v5.7.77新增) 打印当前设备的设备信息(IMEI、OAID、GAID、AndroidID等)
//         ATSDK.testModeDeviceInfo(this, object : DeviceInfoCallback() {
//             fun deviceInfo(deviceInfo: String) {
//                 Log.i(TAG, "deviceInfo: $deviceInfo")
//             }
//         })
//
//
//         ATSDK.init(applicationContext, TopOnAppID, TopOnAppKey) //初始化SDK
    }
}