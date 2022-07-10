package com.alwin.webview

import android.net.Uri
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.alwin.android.R
import com.alwin.webview.base.RobustWebView
import com.alwin.webview.base.WebViewCacheHolder
import com.alwin.webview.base.WebViewListener
import com.alwin.widget.AToolbar

class WebActivity : AppCompatActivity() {

    private val webViewContainer by lazy {
        findViewById<ViewGroup>(R.id.webViewContainer)
    }

    // private val toolbar by lazy {
    //     findViewById<AToolbar>(R.id.toolbar)
    // }

    private lateinit var webView: RobustWebView

    private val webViewListener = object : WebViewListener {
        override fun onProgressChanged(webView: RobustWebView, progress: Int) {
            // tvProgress.text = progress.toString()
        }

        override fun onReceivedTitle(webView: RobustWebView, title: String) {
            // toolbar.title = title
        }

        override fun onPageFinished(webView: RobustWebView, url: String) {
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web)
        webView = WebViewCacheHolder.acquireWebViewInternal(this)
        webView.webViewListener = webViewListener
        webViewContainer.addView(
            webView, LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
        )
        webView.loadUrl(intent?.getStringExtra("web_uri"))
        // toolbar.navigationIcon = resources.getDrawable(R.drawable.ic_launcher_background)
    }

    override fun onBackPressed() {
        if (webView.toGoBack()) {
            super.onBackPressed()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        WebViewCacheHolder.prepareWebView()
    }
}