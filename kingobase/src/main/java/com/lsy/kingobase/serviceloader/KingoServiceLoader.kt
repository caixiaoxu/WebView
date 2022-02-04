package com.lsy.kingobase.serviceloader

import java.lang.Exception
import java.util.*

/**
 * Title :
 * Author: Lsy
 * Date: 2022/1/30 7:26 下午
 * Version:
 * Description:
 * History:
 * <author> <time> <version> <desc>
 * 作者姓名 修改时间 版本号 描述
 */
object KingoServiceLoader {

    /**
     * 加载
     */
    fun <T> load(cls: Class<T>?): T? {
        return try {
            ServiceLoader.load(cls).iterator().next()
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}