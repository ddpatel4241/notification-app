package com.carcollation.firestorearchcomp.commons.extensions

import com.carcollation.firestorearchcomp.commons.pref.SessionHolder


/**
 * Created by aakash on 1/9/18.
 */
fun SessionHolder.getQueryMap(): HashMap<String, String> {
    val map = HashMap<String, String>()
    if (USER_ID != 0 || !USER_ID.toString().isNull()) {
        map["nrauth"] = ACCESS_TOKEN.toString()
        map["user_id"] = USER_ID.toString()
    }
    map["brand"] = BRAND.toString()
    map["model"] = MODEL.toString()
    map["os_version"] = OS_VERSION.toString()
    map["device_id"] = DEVICE_ID.toString()
    return map
}