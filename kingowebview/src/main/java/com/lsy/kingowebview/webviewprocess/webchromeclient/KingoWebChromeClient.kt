package com.lsy.kingowebview.webviewprocess.webchromeclient

import android.util.Log
import com.lsy.kingowebview.webviewprocess.webviewclient.WebViewCallBack
import android.webkit.WebChromeClient
import android.webkit.ConsoleMessage
import android.webkit.WebView

class KingoWebChromeClient(private val mWebViewCallBack: WebViewCallBack?) : WebChromeClient() {
    private val TAG = "XiangxueWebChromeClient"

    /**
     * 获取sfj标题
     */
    override fun onReceivedTitle(view: WebView?, title: String?) {
        mWebViewCallBack?.updateTitle(title) ?: Log.e(TAG, "WebViewCallBack is null.")
    }

    /**
     * 打印信息
     */
    override fun onConsoleMessage(consoleMessage: ConsoleMessage?): Boolean {
        Log.d(TAG, consoleMessage?.message() ?: "")
        return super.onConsoleMessage(consoleMessage)
    }
}