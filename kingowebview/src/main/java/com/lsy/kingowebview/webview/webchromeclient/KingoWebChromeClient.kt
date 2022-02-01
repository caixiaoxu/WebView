package com.lsy.kingowebview.webview.webchromeclient

import android.util.Log
import com.lsy.kingowebview.webview.WebViewCallBack
import android.webkit.WebChromeClient
import android.webkit.ConsoleMessage
import android.webkit.WebView

class KingoWebChromeClient(private val mWebViewCallBack: WebViewCallBack?) : WebChromeClient() {
    private val TAG = "XiangxueWebChromeClient"

    override fun onReceivedTitle(view: WebView, title: String) {
        mWebViewCallBack?.updateTitle(title) ?: Log.e(TAG, "WebViewCallBack is null.")
    }

    /**
     * Report a JavaScript console message to the host application. The ChromeClient
     * should override this to process the log message as they see fit.
     * @param consoleMessage Object containing details of the console message.
     * @return `true` if the message is handled by the client.
     */
    override fun onConsoleMessage(consoleMessage: ConsoleMessage): Boolean {
        // Call the old version of this function for backwards compatability.
        Log.d(TAG, consoleMessage.message())
        return super.onConsoleMessage(consoleMessage)
    }
}