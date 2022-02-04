package com.lsy.kingowebview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.lsy.kingowebview.databinding.ActivityKingoWebViewBinding

/**
 * WebView Activity
 */
class KingoWebViewActivity : AppCompatActivity() {
    private lateinit var mBinding: ActivityKingoWebViewBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kingo_web_view)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_kingo_web_view)
        //标题
        val title = intent.getStringExtra(WEBVIEW_PARAM_TITLE)
        val showActionBar = intent.getBooleanExtra(WEBVIEW_PARAM_SHOWACTIONBAR, true)
        mBinding.actionBar.visibility = if (showActionBar) View.VISIBLE else View.GONE
        mBinding.tvWebViewTitle.text = title
        mBinding.btnWebViewBack.setOnClickListener { finish() }
        //WebView
        val fragmentManager = supportFragmentManager
        val transaction = fragmentManager.beginTransaction()
        val fragment: Fragment =
            KingoWebViewFragment.newInstance(
                intent.getStringExtra(WEBVIEW_PARAM_URL),
                intent.getBooleanExtra(WEBVIEW_FRAGMENT_CAN_NATIVE_REFRESH, true)
            )
        transaction.replace(R.id.web_view_fragment, fragment).commit()

    }

    /**
     * 更新标题
     */
    fun updateTitle(title: String?) {
        mBinding.tvWebViewTitle.text = title
    }
}