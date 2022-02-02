package com.lsy.webviewassembly.command

import android.content.ComponentName
import android.content.Intent
import android.text.TextUtils
import com.google.auto.service.AutoService
import com.lsy.common.ICallbackFromMainprocessToWebViewProcessInterface
import com.lsy.kingobase.BaseApplication
import com.lsy.kingocommon.autoservice.Command

/**
 * Title :
 * Author: Lsy
 * Date: 2022/2/2 9:58 下午
 * Version:
 * Description:
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
        val targetClass = parameters["target_class"].toString()
        if (!targetClass.isNullOrEmpty() && null != BaseApplication.sApplication) {
            val intent = Intent()
            intent.component = ComponentName(BaseApplication.sApplication!!, targetClass)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            BaseApplication.sApplication!!.startActivity(intent)
        }
    }
}