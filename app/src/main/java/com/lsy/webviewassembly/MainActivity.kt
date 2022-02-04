package com.lsy.webviewassembly

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.lsy.kingobase.serviceloader.KingoServiceLoader
import com.lsy.kingocommon.autoservice.IKingoWebViewService

class MainActivity : AppCompatActivity() {
    private val kingoWebViewService = KingoServiceLoader.load(IKingoWebViewService::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.start_demo).setOnClickListener {
            kingoWebViewService?.startWebViewActivity(
                this, "https://www.baidu.com", "测试WebView"
            )
        }

        findViewById<Button>(R.id.start_local_demo_html).setOnClickListener {
            kingoWebViewService?.startWebViewActivity(
                this, "file:///android_asset/demo.html", "测试标题"
            )
        }
    }
}