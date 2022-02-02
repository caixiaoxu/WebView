package com.lsy.kingowebview.webviewprocess.settings

import android.content.Context
import android.net.ConnectivityManager
import android.util.Log
import android.webkit.WebSettings
import android.webkit.WebView

object WebViewDefaultSettings {
    private var mWebSettings: WebSettings? = null

    private fun isNetworkConnected(context: Context): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = cm.activeNetworkInfo
        return networkInfo?.isConnected ?: false
    }

    fun setSettings(webView: WebView) {
        WebView.enableSlowWholeDocumentDraw()
        mWebSettings = webView.settings.apply {
            setJavaScriptEnabled(true)
            setSupportZoom(true)
            setBuiltInZoomControls(false)
            if (isNetworkConnected(webView.context)) {
                setCacheMode(WebSettings.LOAD_DEFAULT)
            } else {
                setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK)
            }
            setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW)

            // 硬件加速兼容性问题有点多
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            webView.setLayerType(View.LAYER_TYPE_HARDWARE, null);
//        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            webView.setLayerType(View.LAYER_TYPE_HARDWARE, null);
//        } else if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
//            webView.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
//        }
            setTextZoom(100)
            setDatabaseEnabled(true)
            setAppCacheEnabled(true)
            setLoadsImagesAutomatically(true)
            setSupportMultipleWindows(false)
            setBlockNetworkImage(false) //是否阻塞加载网络图片  协议http or https
            setAllowFileAccess(true) //允许加载本地文件html  file协议
            setAllowFileAccessFromFileURLs(false) //通过 file url 加载的 Javascript 读取其他的本地文件 .建议关闭
            setAllowUniversalAccessFromFileURLs(false) //允许通过 file url 加载的 Javascript 可以访问其他的源，包括其他的文件和 http，https 等其他的源
            setJavaScriptCanOpenWindowsAutomatically(true)
            setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN)
            setSavePassword(false)
            setSaveFormData(false)
            setLoadWithOverviewMode(true)
            setUseWideViewPort(true)
            setDomStorageEnabled(true)
            setNeedInitialFocus(true)
            setDefaultTextEncodingName("utf-8") //设置编码格式
            setDefaultFontSize(16)
            setMinimumFontSize(10) //设置 WebView 支持的最小字体大小，默认为 8
            setGeolocationEnabled(true)
            setUseWideViewPort(true)
            val appCacheDir = webView.context.getDir("cache", Context.MODE_PRIVATE).path
            Log.i("WebSetting", "WebView cache dir: $appCacheDir")
            setDatabasePath(appCacheDir)
            setAppCachePath(appCacheDir)
            setAppCacheMaxSize((1024 * 1024 * 80).toLong())

            // 用户可以自己设置useragent
            // setUserAgentString("webprogress/build you agent info");
            WebView.setWebContentsDebuggingEnabled(true)
        }
    }
}