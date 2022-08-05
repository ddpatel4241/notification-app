package com.carcollation.firestorearchcomp.commons.extensions


import com.carcollation.firestorearchcomp.shareddata.model.*
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.suprize.app.shareddata.model.GlobalResponse

import retrofit2.Response


/**
 * Created by stllpt031 on 17/2/18.
 */



fun Response<GlobalResponse>.getObject(targetObj: Any): Any = Gson().fromJson(this.body()?.dataaaa,
        TypeToken.getParameterized(targetObj::class.java).type)



fun Response<CommonPaginationResponse>.getObjectList(targetObj: Any): Any {
    return if (this.body()?.data != null) {
        Gson().fromJson(this.body()?.data?.asJsonArray,
            TypeToken.getParameterized(ArrayList::class.java, targetObj::class.java).type)
    } else {
        ArrayList<Any>()
    }
}

fun Response<CommonPaginationResponse>.getLinkObject(targetObj: Any): Any = Gson().fromJson(this.body()?.data?.asJsonObject,
    TypeToken.getParameterized(targetObj::class.java).type)


fun Response<BusinessResponse>.getBussinessObjectList(targetObj: Any): Any {
    return if (this.body()?.data != null) {
        Gson().fromJson(this.body()?.data?.asJsonArray,
            TypeToken.getParameterized(ArrayList::class.java, targetObj::class.java).type)
    } else {
        ArrayList<Any>()
    }
}

fun Response<Categorydata>.geCategoryObject(targetObj: Any): Any {
    return if (this.body()?.data != null) {
        Gson().fromJson(this.body()?.data?.asJsonArray,
            TypeToken.getParameterized(ArrayList::class.java, targetObj::class.java).type)
    } else {
        ArrayList<Any>()
    }
}
