package com.lsy.kingowebview.mainprocess

import android.util.Log
import com.google.gson.Gson
import com.lsy.common.ICallbackFromMainprocessToWebViewProcessInterface
import com.lsy.common.IWebviewProcessToMainProcessInterface
import com.lsy.kingocommon.autoservice.kingowebview.Command
import java.util.*

/**
 * 主进程通信指令处理管理
 */
object MainProcessCommandsManager : IWebviewProcessToMainProcessInterface.Stub() {
    private val TAG = "CommandsManager"

    //配对指令名和指令方法
    private val mCommands: HashMap<String, Command> = HashMap<String, Command>()

    init {
        //加载所有指令类，并进行配对
        val serviceLoader = ServiceLoader.load(Command::class.java)
        for (command in serviceLoader) {
            if (!mCommands.containsKey(command.name())) {
                mCommands[command.name()] = command
            }
        }
    }

    override fun handleWebCommand(
        commandName: String?,
        jsonParams: String?,
        callback: ICallbackFromMainprocessToWebViewProcessInterface?
    ) {
        Log.i(TAG, "Main process commands manager handle web command")
        //通过指令名或者指令方法，并执行
        mCommands[commandName]?.execute(
            Gson().fromJson(jsonParams, MutableMap::class.java), callback
        )
    }
}