package com.alwin.webview

import android.webkit.JavascriptInterface
import github.leavesczy.robustwebview.utils.log
import github.leavesczy.robustwebview.utils.showToast

class JsInterface {

    @JavascriptInterface
    fun showToastByAndroid(log: String) {
        log("showToastByAndroid:$log")
        showToast(log)
    }

}