package com.lsy.kingowebview.mainprocess

import android.util.Log
import com.google.gson.Gson
import com.lsy.common.ICallbackFromMainprocessToWebViewProcessInterface
import com.lsy.common.IWebviewProcessToMainProcessInterface
import com.lsy.kingocommon.autoservice.Command
import java.util.*

object MainProcessCommandsManager : IWebviewProcessToMainProcessInterface.Stub() {
    private val mCommands: HashMap<String, Command> = HashMap<String, Command>()

    init {
        val serviceLoader = ServiceLoader.load(
            Command::class.java
        )
        for (command in serviceLoader) {
            if (!mCommands.containsKey(command.name())) {
                mCommands[command.name()] = command
            }
        }
    }

    const val TAG = "CommandsManager"
    override fun handleWebCommand(
        commandName: String?,
        jsonParams: String?,
        callback: ICallbackFromMainprocessToWebViewProcessInterface?
    ) {
        Log.i(TAG, "Main process commands manager handle web command")
        mCommands[commandName]?.execute(Gson().fromJson(jsonParams, MutableMap::class.java), callback)
    }
}