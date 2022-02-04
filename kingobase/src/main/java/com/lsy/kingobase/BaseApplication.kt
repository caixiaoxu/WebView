package com.lsy.kingobase

import android.app.Application

/**
 * Application基类
 */
open class BaseApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        sApplication = this
    }

    companion object {
        var sApplication: Application? = null
    }
}