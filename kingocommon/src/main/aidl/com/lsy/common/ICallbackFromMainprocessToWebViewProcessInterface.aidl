// ICallbackFromMainprocessToWebViewProcessInterface.aidl
package com.lsy.common;

//AIDL回调接口
interface ICallbackFromMainprocessToWebViewProcessInterface {
    /**
      * 回调方法
      * @param kotlinToJavescriptCallBackName 从主进程回调JS的方法
      * @param response 回调JSON数据
      */
    void onResult(String kotlinToJavescriptCallBackName, String response);
}
