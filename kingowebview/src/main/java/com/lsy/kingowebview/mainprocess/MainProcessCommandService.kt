package com.lsy.kingowebview.mainprocess

import android.app.Service
import android.content.Intent
import android.os.IBinder

/**
 * 主进程通信服输
 */
class MainProcessCommandService: Service() {

    override fun onBind(intent: Intent?): IBinder? {
        return MainProcessCommandsManager.asBinder()
    }
}