package com.lsy.kingowebview.webviewprocess

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.IBinder
import com.lsy.common.ICallbackFromMainprocessToWebViewProcessInterface
import com.lsy.common.IWebviewProcessToMainProcessInterface
import com.lsy.kingobase.BaseApplication
import com.lsy.kingowebview.mainprocess.MainProcessCommandService

/**
 * WebView进程的指令颁发
 */
object WebViewProcessCommandsDispatcher : ServiceConnection {
    private var iWebviewProcessToMainProcessInterface: IWebviewProcessToMainProcessInterface? = null

    /**
     * 初始化AIDL连接
     */
    fun initAidlConnection() {
        val intent = Intent(BaseApplication.sApplication, MainProcessCommandService::class.java)
        BaseApplication.sApplication?.bindService(intent, this, Context.BIND_AUTO_CREATE)
    }

    override fun onServiceConnected(componentName: ComponentName?, iBinder: IBinder?) {
        iWebviewProcessToMainProcessInterface =
            IWebviewProcessToMainProcessInterface.Stub.asInterface(iBinder)
    }

    override fun onServiceDisconnected(componentName: ComponentName?) {
        iWebviewProcessToMainProcessInterface = null
        initAidlConnection()
    }

    /**
     * 执行指令颁发
     * @param commandName 指令名
     * @param parameters 参数
     * @param baseWebView 用于回调js
     */
    fun executeCommand(commandName: String, parameters: String, baseWebView: BaseWebView) {
        //调用AIDL的方法
        iWebviewProcessToMainProcessInterface?.handleWebCommand(commandName, parameters,
            object: ICallbackFromMainprocessToWebViewProcessInterface.Stub() {
                override fun onResult(callbackname: String?, response: String?) {
                    //回调AIDL的返回数据
                    baseWebView.handleCallback(callbackname, response)
                }
            })
    }
}