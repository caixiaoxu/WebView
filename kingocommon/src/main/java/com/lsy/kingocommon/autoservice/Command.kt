package com.lsy.kingocommon.autoservice

import com.lsy.common.ICallbackFromMainprocessToWebViewProcessInterface


interface Command {
    /**
     * 设置命令名
     * @return 命令名称
     */
    fun name(): String

    /**
     * 执行指令
     * @param parameters 参数
     * @param callback 回调
     */
    fun execute(
        parameters: Map<*, *>,
        callback: ICallbackFromMainprocessToWebViewProcessInterface?
    )
}