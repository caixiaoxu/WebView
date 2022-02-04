package com.lsy.kingowebview.webviewprocess

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.webkit.JavascriptInterface
import android.webkit.WebView
import com.google.gson.Gson
import com.lsy.kingowebview.bean.JsParam
import com.lsy.kingowebview.webviewprocess.settings.WebViewDefaultSettings
import com.lsy.kingowebview.webviewprocess.webchromeclient.KingoWebChromeClient
import com.lsy.kingowebview.webviewprocess.webviewclient.KingoWebViewClient
import com.lsy.kingowebview.webviewprocess.webviewclient.WebViewCallBack

/**
 * WebView的页面回调
 */
class BaseWebView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : WebView(context, attrs) {
    private val TAG = "XiangxueWebView"

    init {
        WebViewProcessCommandsDispatcher.initAidlConnection()
        addJavascriptInterface(this, "kingowebview")
        WebViewDefaultSettings.setSettings(this)
    }

    fun registerWebViewCallBack(webViewCallBack: WebViewCallBack?) {
        webViewClient = KingoWebViewClient(webViewCallBack)
        webChromeClient = KingoWebChromeClient(webViewCallBack)
    }

    @JavascriptInterface
    fun takeNativeAction(jsParam: String) {
        Log.e(TAG, "this is a call from html javascript.$jsParam")
        val jsParamObject = Gson().fromJson(jsParam, JsParam::class.java)
        jsParamObject?.let {
            WebViewProcessCommandsDispatcher.executeCommand(
                jsParamObject.name, Gson().toJson(jsParamObject.param), this
            )
        }
    }

    /**
     * 回调方法
     * @param callbackname 回调方法名
     * @param response 回调内容
     */
    fun handleCallback(callbackname: String?, response: String?) {
        if (!callbackname.isNullOrEmpty() && !response.isNullOrEmpty()) {
            post {
                val jscode = "javascript:kingojs.callback('$callbackname',$response)"
                evaluateJavascript(jscode, null)
            }
        }
    }
}