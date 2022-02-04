package com.lsy.webviewassembly.command

import android.os.Handler
import android.os.Looper
import android.widget.Toast
import com.google.auto.service.AutoService
import com.lsy.common.ICallbackFromMainprocessToWebViewProcessInterface
import com.lsy.kingobase.BaseApplication
import com.lsy.kingocommon.autoservice.kingowebview.Command

/**
 * Title : 命令->显示Toast
 * Author: Lsy
 * Date: 2022/2/2 12:28 下午
 * Version: 1.0
 * Description: 显示出Toast，内容为传递参数
 * History:
 * <author> <time> <version> <desc>
 * 作者姓名 修改时间 版本号 描述
 */
@AutoService(Command::class)
class CommandShowToast : Command {
    override fun name(): String = "showToast"

    override fun execute(
        parameters: Map<*, *>,
        callback: ICallbackFromMainprocessToWebViewProcessInterface?
    ) {
        val handler = Handler(Looper.getMainLooper())
        handler.post {
            Toast.makeText(
                BaseApplication.sApplication, parameters["message"].toString(), Toast.LENGTH_SHORT
            ).show()
        }
    }
}