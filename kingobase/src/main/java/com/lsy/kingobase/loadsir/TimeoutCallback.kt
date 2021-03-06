package com.lsy.kingobase.loadsir

import android.content.Context
import android.view.View
import com.lsy.kingobase.R
import android.widget.Toast
import com.kingja.loadsir.callback.Callback

/**
 * Description:
 * Create Time:2017/9/2 16:17
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
class TimeoutCallback : Callback() {
    override fun onCreateView(): Int {
        return R.layout.layout_timeout
    }

    override fun onReloadEvent(context: Context, view: View): Boolean {
        Toast.makeText(
            context.applicationContext,
            "Connecting to the network again!",
            Toast.LENGTH_SHORT
        ).show()
        return false
    }
}