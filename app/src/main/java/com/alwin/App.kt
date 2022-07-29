package com.alwin

import android.app.Application
import com.alwin.util.SystemUtil
import com.anythink.core.api.ATSDK
import com.facebook.drawee.backends.pipeline.Fresco

// @HiltAndroidApp
class App: Application() {
    override fun onCreate() {
        super.onCreate()
        SystemUtil.application = this
        Fresco.initialize(this)
    }
}