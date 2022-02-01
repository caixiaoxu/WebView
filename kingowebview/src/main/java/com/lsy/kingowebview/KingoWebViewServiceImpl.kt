package com.lsy.kingowebview

import android.content.Context
import android.content.Intent
import androidx.fragment.app.Fragment
import com.google.auto.service.AutoService
import com.lsy.kingocommon.autoservice.IKingoWebViewService

/**
 * Title :
 * Author: Lsy
 * Date: 2022/1/30 6:39 下午
 * Version:
 * Description:
 * History:
 * <author> <time> <version> <desc>
 * 作者姓名 修改时间 版本号 描述
 */
@AutoService(IKingoWebViewService::class)
class KingoWebViewServiceImpl : IKingoWebViewService {
    override fun startWebViewActivity(
        context: Context,
        url: String,
        title: String,
        showActionBar: Boolean,
        canNativeRefresh: Boolean
    ) {
        val intent = Intent(context, KingoWebViewActivity::class.java)
        intent.putExtra(WEBVIEW_PARAM_URL, url)
        intent.putExtra(WEBVIEW_PARAM_TITLE, title)
        intent.putExtra(WEBVIEW_PARAM_SHOWACTIONBAR, showActionBar)
        intent.putExtra(WEBVIEW_FRAGMENT_CAN_NATIVE_REFRESH, canNativeRefresh)
        context.startActivity(intent)
    }

    override fun getWebViewFragment(url: String, canNativeRefresh: Boolean): Fragment =
        KingoWebViewFragment.newInstance(url, canNativeRefresh)
}