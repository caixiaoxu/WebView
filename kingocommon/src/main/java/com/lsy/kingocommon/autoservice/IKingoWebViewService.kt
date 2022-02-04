package com.lsy.kingocommon.autoservice

import android.content.Context
import androidx.fragment.app.Fragment

/**
 * Title : WebView启动服务接口
 * Author: Lsy
 * Date: 2022/1/30 6:34 下午
 * Version: 1.0
 * Description: 用于启动WebView服务
 * History:
 * <author> <time> <version> <desc>
 * 作者姓名 修改时间 版本号 描述
 */
interface IKingoWebViewService {

    /**
     * 启动WebView Activity
     * @param context 上下文
     * @param url 网址
     * @param title 标题
     * @param showActionBar 是否显示标题
     */
    fun startWebViewActivity(
        context: Context,
        url: String,
        title: String,
        showActionBar: Boolean = true,
        canNativeRefresh: Boolean = true
    )

    /**
     * 获取WebView Fragment
     * @param url 网址
     * @param canNativeRefresh 是否本地刷新
     * @return WebView Fragment
     */
    fun getWebViewFragment(url: String, canNativeRefresh: Boolean = true): Fragment
}