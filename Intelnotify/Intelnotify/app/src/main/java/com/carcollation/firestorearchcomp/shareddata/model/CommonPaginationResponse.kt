package com.carcollation.firestorearchcomp.shareddata.model

import com.google.gson.JsonElement
import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * Created by aakash on 9/8/18.
 */
data class CommonPaginationResponse(

    @SerializedName("data")
    val data: JsonElement?,

    @SerializedName("status")
    val status: String = "",

    @SerializedName("user_id")
    val user_id: String = "",

    @SerializedName("logged_in")
    val boolean: Boolean = false,

    @SerializedName("message")
val message: String = "",

)

data class PostsItem(
    @SerializedName("id")
    var id:  String = "",
    @SerializedName("user_id")
    var user_id: String = "",
    @SerializedName("plat_number")
    val plat_number: String = "",
    @SerializedName("vehicle_color")
    val vehicle_color: String = "",
    @SerializedName("regi_reason")
    val regi_reason: String = "",
    @SerializedName("detected_time")
    val detected_time: String = "",
    @SerializedName("main_img")
    val main_img: String = "",
    @SerializedName("plate_img")
    val plate_img: String = "",
    @SerializedName("timestamp")
    val timestamp: String = "",


    @SerializedName("store_id")
    var store_id:  String = "",
    @SerializedName("store_name")
    var store_name: String = "",




) : Serializable

data class RecordData(
    @SerializedName("records")
    val records: JsonElement?,

    @SerializedName("business")
    val business: JsonElement?
)

data class BusinessResponse(
    @SerializedName("data")
    val data: JsonElement?,
    @SerializedName("authentication")
    val authentication: String = "",
    @SerializedName("authorization")
    val authorization: String = "",
    @SerializedName("status")
    val status: String = ""
)


data class Records(
    @SerializedName("id")
    val posts: JsonElement?,
    @SerializedName("title")
    val data: RecordData? = null,
    @SerializedName("status")
    val status: String = ""
)


data class AddLink(
    @SerializedName("posts")
    val posts: String = "",
    @SerializedName("status")
    val status: String = "",
    @SerializedName("message")
    val message: String = "",
    @SerializedName("data")
    val data: JsonElement? = null
)


data class LinkData(
    @SerializedName("live_link")
    val live_link: String = ""
)


data class Categorydata(
    @SerializedName("data")
    val data: JsonElement? = null,
    @SerializedName("status")
    val status: String = ""
)


data class CategoryListItem(
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("title")
    val title: String = "",
    @SerializedName("alias")
    val alias: String = "",
    @SerializedName("permalink")
    val permalink: String = "",
    @SerializedName("children")
    val categories: List<ChildCategory>? = null
)

data class ChildCategory(
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("title")
    val title: String = "",
    @SerializedName("alias")
    val alias: String = "",
    @SerializedName("permalink")
    val permalink: String = "",
    @SerializedName("children")
    val categories: List<ChildCategory1>? = null
)


data class ChildCategory1(
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("title")
    val title: String = "",
    @SerializedName("alias")
    val alias: String = "",
    @SerializedName("permalink")
    val permalink: String = ""
)