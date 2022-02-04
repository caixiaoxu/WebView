package com.lsy.kingobase.loadsir

import com.kingja.loadsir.callback.Callback
import com.lsy.kingobase.R

/**
 * Description:
 * Create Time:2017/9/4 10:22
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
class LottieEmptyCallback : Callback() {
    override fun onCreateView(): Int {
        return R.layout.layout_lottie_empty
    }
}