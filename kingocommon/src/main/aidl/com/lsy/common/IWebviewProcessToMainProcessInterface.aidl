// IWebviewProcessToMainProcessInterface.aidl
package com.lsy.common;

// Declare any non-default types here with import statements
import com.lsy.common.ICallbackFromMainprocessToWebViewProcessInterface;

//WebView和主进程通信接口
interface IWebviewProcessToMainProcessInterface {
    /**
     * 处理WebView的指令
     * @param commandName 命令名
     * @param jsonParams Json参数
     * @param callback 回调接口
     */
    void handleWebCommand(String commandName, String jsonParams, in ICallbackFromMainprocessToWebViewProcessInterface callback);
}
