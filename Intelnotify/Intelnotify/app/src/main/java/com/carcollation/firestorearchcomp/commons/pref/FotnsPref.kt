package com.carcollation.firestorearchcomp.commons.pref

import android.content.Context
import android.content.SharedPreferences

class FontsPref(context: Context) {

    private val PREFS_FILENAME = "ampledev.vyomtech.com.newsreach.commons.pref.FontsPref"
    private val PREFS_setfragment = "setfragment"
    private val prefs: SharedPreferences = context.getSharedPreferences(PREFS_FILENAME, 0)
    private val PREFS_sethomevalue= "sethomevalue"
    private val DisplayAds = "displayads"
    private val TimeInterval = "timeinterval"


    var setfragment: String?
        get() = prefs.getString(PREFS_setfragment, "save")
        set(value) = prefs.edit().putString(PREFS_setfragment, value).apply()

    var sethomevalue: String?
        get() = prefs.getString(PREFS_sethomevalue, "home")
        set(value) = prefs.edit().putString(PREFS_sethomevalue, value).apply()

    var sessionadsdisplay: String?
        get() = prefs.getString(DisplayAds, "5")
        set(value) = prefs.edit().putString(DisplayAds, value).apply()


    var sessionadtimeinterval: String?
        get() = prefs.getString(TimeInterval, "")
        set(value) = prefs.edit().putString(TimeInterval, value).apply()
}