package com.carcollation.firestorearchcomp.commons.pref

import android.content.Context
import android.content.SharedPreferences

class FirebaseAccessToken(context: Context) {
    private val PREFS_FILENAME = "com.gujaratdarshansamachar.userapp.commons.pref.FirebaseAccessToken"
    private val PREFS_refreshedToken = "setrefreshtoken"
    private val prefs: SharedPreferences = context.getSharedPreferences(PREFS_FILENAME, 0)

    var setrefreshtoken: String?
        get() = prefs.getString(PREFS_refreshedToken, "")
        set(value) = prefs.edit().putString(PREFS_refreshedToken, value).apply()
}