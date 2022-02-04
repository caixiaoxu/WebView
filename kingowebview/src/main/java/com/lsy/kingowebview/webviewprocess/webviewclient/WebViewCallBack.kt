package com.lsy.kingowebview.webviewprocess.webviewclient

/**
 * WebView回调接口
 */
interface WebViewCallBack {
    /**
     * 页面请求开始
     */
    fun pageStarted(url: String?)

    /**
     * 页面请求结束
     */
    fun pageFinished(url: String?)

    /**
     * 页面请求错误
     */
    fun onError()

    /**
     * 更新标题
     */
    fun updateTitle(title: String?)
}