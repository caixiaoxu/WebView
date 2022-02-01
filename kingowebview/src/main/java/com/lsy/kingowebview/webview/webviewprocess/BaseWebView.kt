package com.lsy.kingowebview.webview.webviewprocess

import android.content.Context
import android.util.AttributeSet
import android.webkit.WebView
import com.lsy.kingowebview.webview.WebViewCallBack
import com.lsy.kingowebview.webview.webviewclient.KingoWebViewClient
import com.lsy.kingowebview.webview.webchromeclient.KingoWebChromeClient

class BaseWebView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : WebView(context, attrs) {
    private val TAG = "XiangxueWebView"

    fun registerWebViewCallBack(webViewCallBack: WebViewCallBack?) {
        webViewClient = KingoWebViewClient(webViewCallBack)
        webChromeClient = KingoWebChromeClient(webViewCallBack)
    }
}