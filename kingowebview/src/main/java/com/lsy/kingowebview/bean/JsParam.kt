package com.lsy.kingowebview.bean

import com.google.gson.JsonObject

/**
 * Js传递参数
 * @param name 调用指令的名
 * @param jsonObject 指令内容
 */
data class JsParam(val name: String, val param: JsonObject)