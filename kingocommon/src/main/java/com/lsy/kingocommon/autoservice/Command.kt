package com.lsy.kingocommon.autoservice

import com.lsy.common.ICallbackFromMainprocessToWebViewProcessInterface


interface Command {
    fun name(): String?
    fun execute(
        parameters: Map<*, *>?,
        callback: ICallbackFromMainprocessToWebViewProcessInterface?
    )
}