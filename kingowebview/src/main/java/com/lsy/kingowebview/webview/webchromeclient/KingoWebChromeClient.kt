package com.lsy.kingowebview.webview.webchromeclient

import android.util.Log
import com.lsy.kingowebview.webview.WebViewCallBack
import android.webkit.WebChromeClient
import android.webkit.ConsoleMessage
import android.webkit.WebView

class KingoWebChromeClient(private val mWebViewCallBack: WebViewCallBack?) : WebChromeClient() {
    private val TAG = "XiangxueWebChromeClient"

    override fun onReceivedTitle(view: WebView?, title: String?) {
        mWebViewCallBack?.updateTitle(title) ?: Log.e(TAG, "WebViewCallBack is null.")
    }

    override fun onConsoleMessage(consoleMessage: ConsoleMessage?): Boolean {
        Log.d(TAG, consoleMessage?.message() ?: "")
        return super.onConsoleMessage(consoleMessage)
    }
}