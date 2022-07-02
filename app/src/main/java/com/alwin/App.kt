package com.alwin

import android.app.Application
import com.alwin.util.SystemUtil
import com.facebook.drawee.backends.pipeline.Fresco
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App: Application() {
    override fun onCreate() {
        super.onCreate()
        SystemUtil.application = this
        Fresco.initialize(this)
    }
}