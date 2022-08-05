package com.carcollation.firestorearchcomp.commons.pref

import android.content.SharedPreferences
import com.carcollation.firestorearchcomp.commons.extensions.prefBoolean
import com.carcollation.firestorearchcomp.commons.extensions.prefInt
import com.carcollation.firestorearchcomp.commons.extensions.prefLong
import com.carcollation.firestorearchcomp.commons.extensions.prefString

class SessionHolder(val pref: SharedPreferences) {

    var ACCESS_TOKEN : String? by pref.prefString("")
    var API_TOKEN : String? by pref.prefString("")
    var BASE_URL : String? by pref.prefString("")
    var USER_LAT : Long by pref.prefLong(0L)
    var USER_LNG : Long by pref.prefLong(0L)
    var USER_ID : Int by pref.prefInt(0)
    var IS_ADMIN : Int by pref.prefInt(0)
    var DEVICE_ID : String? by pref.prefString()
    var BRAND : String? by pref.prefString()
    var MODEL : String? by pref.prefString()
    var OS_VERSION : String? by pref.prefString()
    var UserCookie : String? by pref.prefString()
    var USerLogin : String? by pref.prefString()
    var FirstTimeInstallApp : String? by pref.prefString()
    var IS_VIDEO_AUTOPLAY : Boolean by pref.prefBoolean(false)
    var SetFontsize : String? by pref.prefString()
    var RefreshedToken : String? by pref.prefString()
    var CheckfirsttimeLogin : String? by pref.prefString()
    var TextNotification : Boolean by pref.prefBoolean(true)
    var VideoNotification : Boolean by pref.prefBoolean(true)
    var AllNotification : Boolean by pref.prefBoolean(true)
    var VratkathaNotification : Boolean by pref.prefBoolean(true)
    var NotificationCount : String? by pref.prefString("0")

    var CheckSplashScreen : String? by pref.prefString("")

    var Display_Name : String? by pref.prefString()
    var User_EmailID : String? by pref.prefString()
    var User_Familyid : String? by pref.prefString()
    var Message : String? by pref.prefString()
    var RegisterMessage : String? by pref.prefString()

    var RegisterSucsess : String? by pref.prefString("")
    var pdffile : String? by pref.prefString()

    var LiveTvlink : String? by pref.prefString("")
}