package com.lsy.kingowebview.webview.webviewclient

import com.lsy.kingowebview.webview.WebViewCallBack
import android.webkit.WebViewClient
import android.graphics.Bitmap
import android.util.Log
import android.webkit.WebResourceRequest
import android.webkit.WebResourceError
import android.webkit.WebView

class KingoWebViewClient(private val mWebViewCallBack: WebViewCallBack?) : WebViewClient() {
    private val TAG = "XiangxueWebViewClient"

    override fun onPageStarted(view: WebView, url: String, favicon: Bitmap) {
        mWebViewCallBack?.pageStarted(url) ?: Log.e(TAG, "WebViewCallBack is null.")
    }

    override fun onPageFinished(view: WebView, url: String) {
        mWebViewCallBack?.pageFinished(url) ?: Log.e(TAG, "WebViewCallBack is null.")
    }

    override fun onReceivedError(
        view: WebView, request: WebResourceRequest, error: WebResourceError
    ) {
        super.onReceivedError(view, request, error)
        mWebViewCallBack?.onError() ?: Log.e(TAG, "WebViewCallBack is null.")
    }
}