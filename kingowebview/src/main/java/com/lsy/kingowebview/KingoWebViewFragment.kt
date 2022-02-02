package com.lsy.kingowebview

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.kingja.loadsir.core.LoadService
import com.kingja.loadsir.core.LoadSir
import com.lsy.kingobase.loadsir.ErrorCallback
import com.lsy.kingobase.loadsir.LoadingCallback
import com.lsy.kingowebview.databinding.FragmentKingoWebViewBinding
import com.lsy.kingowebview.webviewprocess.WebViewCallBack
import com.scwang.smart.refresh.layout.api.RefreshLayout
import com.scwang.smart.refresh.layout.listener.OnRefreshListener

/**
 * Title : WebView Fragment
 * Author: Lsy
 * Date: 2022/1/31 2:14 下午
 * Version: 1.0
 * Description: WebView Fragment
 * History:
 * <author> <time> <version> <desc>
 * 作者姓名 修改时间 版本号 描述
 */
class KingoWebViewFragment : Fragment(), OnRefreshListener,
    WebViewCallBack {
    private val TAG = "WebViewFragment"

    private var url: String? = null
    private var mCanNativeRefresh = true
    private lateinit var mBinding: FragmentKingoWebViewBinding
    private lateinit var mLoadService: LoadService<*>

    private var mIsError = false

    companion object {
        fun newInstance(url: String?, canNativeRefresh: Boolean = true): Fragment =
            KingoWebViewFragment().apply {
                val bundle = Bundle()
                bundle.putString(WEBVIEW_PARAM_URL, url)
                bundle.putBoolean(WEBVIEW_FRAGMENT_CAN_NATIVE_REFRESH, canNativeRefresh)
                arguments = bundle
            }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        url = arguments?.getString(WEBVIEW_PARAM_URL)
        mCanNativeRefresh = arguments?.getBoolean(WEBVIEW_FRAGMENT_CAN_NATIVE_REFRESH) ?: true
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_kingo_web_view, container, false)
        mLoadService = LoadSir.getDefault().register(mBinding.smartRefresh) {
            mLoadService.showCallback(LoadingCallback::class.java)
            mBinding.webview.reload()
        }
        mBinding.smartRefresh.setEnableRefresh(mCanNativeRefresh)
        mBinding.smartRefresh.setEnableLoadMore(false)
        mBinding.smartRefresh.setOnRefreshListener(this)
        url?.let {
            mBinding.webview.registerWebViewCallBack(this)
            mBinding.webview.loadUrl(it)
        }
        return mLoadService.loadLayout
    }

    override fun onRefresh(refreshLayout: RefreshLayout) {
        mBinding.webview.reload()
    }

    override fun pageStarted(url: String?) {
        mLoadService.showCallback(LoadingCallback::class.java)
    }

    override fun pageFinished(url: String?) {
        Log.d(TAG, "pageFinished")
        if (mIsError) {
            mBinding.smartRefresh.setEnableRefresh(true)
            mLoadService.showCallback(ErrorCallback::class.java)
        } else {
            mLoadService.showSuccess()
            mBinding.smartRefresh.setEnableRefresh(mCanNativeRefresh)
        }
        mBinding.smartRefresh.finishRefresh()
        mIsError = false
    }

    override fun onError() {
        Log.e(TAG, "onError")
        mBinding.smartRefresh.finishRefresh()
        mIsError = true
    }

    override fun updateTitle(title: String?) {
        if (activity is KingoWebViewActivity) {
            (activity as KingoWebViewActivity).updateTitle(title)
        }
    }
}