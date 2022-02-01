// ICallbackFromMainprocessToWebViewProcessInterface.aidl
package com.lsy.common;

interface ICallbackFromMainprocessToWebViewProcessInterface {
    void onResult(String kotlinToJavescriptCallBackName, String response);
}
