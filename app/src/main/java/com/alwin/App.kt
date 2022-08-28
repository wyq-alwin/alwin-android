package com.alwin

import android.app.Application
import com.alwin.android.BuildConfig
import com.alwin.util.SystemUtil
import com.facebook.drawee.backends.pipeline.Fresco

class App: Application() {
    override fun onCreate() {
        super.onCreate()
        SystemUtil.application = this
        Fresco.initialize(this)
        // // 必须在初始化ARouter之前配置
        // if (BuildConfig.DEBUG){
        //     // 日志开启
        //     ARouter.openLog();
        //     // 调试模式开启，如果在install run模式下运行，则必须开启调试模式
        //     ARouter.openDebug();
        // }
        // ARouter.init(this);
    }
}