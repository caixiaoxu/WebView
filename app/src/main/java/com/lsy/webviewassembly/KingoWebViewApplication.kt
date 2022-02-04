package com.lsy.webviewassembly

import com.kingja.loadsir.core.LoadSir
import com.lsy.kingobase.BaseApplication
import com.lsy.kingobase.loadsir.*

class KingoWebViewApplication : BaseApplication() {
    override fun onCreate() {
        super.onCreate()
        //初始化LoadSir
        LoadSir.beginBuilder()
            .addCallback(ErrorCallback()) //加载失败回调处理
            .addCallback(EmptyCallback()) //空回调处理
            .addCallback(LoadingCallback()) //加载回调处理
            .addCallback(TimeoutCallback()) //超时回调处理
            .addCallback(CustomCallback()) //自定义回调处理
            .addCallback(AnimateCallback())//自定义动画处理
            .setDefaultCallback(LoadingCallback::class.java) //默认回调处理
            .commit()
    }
}