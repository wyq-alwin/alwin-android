package com.alwin.webview

import android.webkit.JavascriptInterface
import com.alwin.webview.utils.log
import com.alwin.webview.utils.showToast

class JsInterface  {

    @JavascriptInterface
    fun showToastByAndroid(log: String) {
        log("showToastByAndroid:$log")
        showToast(log)
    }

}