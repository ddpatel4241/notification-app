package com.carcollation.firestorearchcomp.commons.helper

import android.content.Context
import android.content.pm.PackageManager
import android.text.TextUtils
import android.util.Log
import com.google.firebase.remoteconfig.FirebaseRemoteConfig


class ForceUpdateChecker(var context: Context?, private var onUpdateNeededListener: OnUpdateNeededListener?) {

    private val TAG = ForceUpdateChecker::class.java.simpleName

    companion object {
        private const val ARG_CATEGORY_NAME = "categoryName"
        val KEY_UPDATE_REQUIRED = "force_update_required"
        val KEY_CURRENT_VERSION = "force_update_current_version"
        val KEY_UPDATE_URL = "force_update_store_url"

    }

    interface OnUpdateNeededListener {
        fun onUpdateNeeded(updateUrl: String)
    }

    fun with(context: Context): Builder {
        return Builder(context)
    }


    fun check() {
        val remoteConfig = FirebaseRemoteConfig.getInstance()

        if (remoteConfig.getBoolean(KEY_UPDATE_REQUIRED)) {
            val currentVersion = remoteConfig.getString(KEY_CURRENT_VERSION)
            val appVersion = getAppVersion(this.context!!)
            val updateUrl = remoteConfig.getString(KEY_UPDATE_URL)

            if (!TextUtils.equals(currentVersion, appVersion) && onUpdateNeededListener != null) {
                onUpdateNeededListener!!.onUpdateNeeded(updateUrl)
            }
        }
    }

    private fun getAppVersion(context: Context): String {
        var result = ""

        try {

            val pInfo = context.packageManager.getPackageInfo(context.packageName, 0)
            result = pInfo.versionName

        } catch (e: PackageManager.NameNotFoundException) {
            Log.e(TAG, e.message.toString())
        }

        return result
    }


    class Builder( val context: Context) {
        var onUpdateNeededListener: OnUpdateNeededListener? = null

        fun onUpdateNeeded(onUpdateNeededListener: OnUpdateNeededListener): Builder {
            this.onUpdateNeededListener = onUpdateNeededListener
            return this
        }

        fun build(): ForceUpdateChecker {
            return ForceUpdateChecker(context, onUpdateNeededListener)
        }


        fun check(): ForceUpdateChecker {
            val forceUpdateChecker = build()
            forceUpdateChecker.check()

            return forceUpdateChecker
        }
    }


}