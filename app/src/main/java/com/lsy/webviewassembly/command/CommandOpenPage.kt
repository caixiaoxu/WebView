package com.lsy.webviewassembly.command

import android.content.ComponentName
import android.content.Intent
import com.google.auto.service.AutoService
import com.lsy.common.ICallbackFromMainprocessToWebViewProcessInterface
import com.lsy.kingobase.BaseApplication
import com.lsy.kingocommon.autoservice.kingowebview.Command

/**
 * Title : 指令——>打开页面
 * Author: Lsy
 * Date: 2022/2/2 9:58 下午
 * Version: 1.0
 * Description: 通过指定的Activity的打开页面
 * History:
 * <author> <time> <version> <desc>
 * 作者姓名 修改时间 版本号 描述
 */
@AutoService(Command::class)
class CommandOpenPage : Command {
    override fun name(): String = "openPage"

    override fun execute(
        parameters: Map<*, *>,
        callback: ICallbackFromMainprocessToWebViewProcessInterface?
    ) {
        //获取指定的Activity class路径
        val targetClass = parameters["target_class"].toString()
        if (targetClass.isNotEmpty() && null != BaseApplication.sApplication) {
            //通过路径打开Activity
            val intent = Intent()
            intent.component = ComponentName(BaseApplication.sApplication!!, targetClass)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            BaseApplication.sApplication!!.startActivity(intent)
        }
    }
}