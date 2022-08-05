package com.carcollation.firestorearchcomp.commons.extensions

import com.google.gson.Gson


/**
 * Created by aakash on 25/8/18.
 */
fun Any?.getJsonString() = this?.let { Gson().toJson(this) } ?: ""

fun String?.getJsonObject(a: Any) = if (this.isNull()) {
    Gson().fromJson(this, a::class.java)
} else null