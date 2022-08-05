package com.suprize.app.shareddata.model

import com.google.gson.JsonElement
import com.google.gson.annotations.SerializedName

/**
 * Created by stllpt065 on 2/11/17.
 */
data class GlobalResponse(
        @SerializedName("status")
        val status: String,
        @SerializedName("data")
        val dataaaa: String,
        @SerializedName("data")
        val data: JsonElement)