package com.carcollation.firestorearchcomp.commons.extensions

import android.Manifest
import android.app.Activity
import android.content.*
import android.content.pm.PackageManager
import android.graphics.drawable.Drawable
import android.net.ConnectivityManager
import android.net.Uri
import android.os.Build
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.RequiresApi
import androidx.annotation.StringRes
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.core.content.ContextCompat
import android.telephony.TelephonyManager
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import com.carcollation.firestorearchcomp.BuildConfig
import org.jetbrains.anko.internals.AnkoInternals
import org.jetbrains.anko.layoutInflater


/**
 * Created by stllpt065 on 6/7/17.
 */
fun Context.charToast(char: CharSequence) = Toast.makeText(this, char, Toast.LENGTH_SHORT).show()

fun Context.longCharToast(char: CharSequence) = Toast.makeText(this, char, Toast.LENGTH_LONG).show()

@RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
inline fun <reified T : Activity> Activity.startActivityIfRunning(vararg params: Pair<String, Any>) {

    if (!isDestroyed) {
        AnkoInternals.internalStartActivity(this, T::class.java, params)
    }
}

fun Context.getIMEINo() : String {
    try {
        val telephonyManager = this.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            return ""
        }

        val imei = telephonyManager.deviceId
        Log.e("imei", "=" + imei!!)
        return if (imei != null && !imei.isEmpty()) {
            imei
        } else {
            android.os.Build.SERIAL
        }


    } catch (e: Exception) {
        e.printStackTrace()
    }
    return "not_found"
}

fun Activity.resToast(@StringRes res: Int) {
    val li = this.layoutInflater
    val toast = Toast(this)
    toast.apply {
        duration = Toast.LENGTH_SHORT
        setText(res)
        show()
    }
}

fun Activity.resToast(message: String) {
    val li = this.layoutInflater
    val toast = Toast(this)
    toast.apply {
        duration = Toast.LENGTH_SHORT
        setText(message)
        show()
    }
}

fun Context.resToast(message: String) {
    val li = this.layoutInflater
    val toast = Toast(this)
    toast.apply {
        duration = Toast.LENGTH_SHORT
        setText(message)
        show()
    }
}
fun Context.closeKeyboard(context: Context, view: View?) {
    try {
        if (view != null) {
            val imm =
                context.getSystemService("input_method") as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    } catch (var3: java.lang.Exception) {
        var3.printStackTrace()
    }
}



fun Context.hideKeyboard(view: View?) {
    val imm = this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(view?.windowToken, 0)
    imm.showSoftInput(view, 0)
}


fun Context.showKeyboard(view: View?) {
    val imm = this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    view!!.requestFocus()
    imm.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT)

}

fun Context.getPrefInstance(prefName: String): SharedPreferences =
        this.getSharedPreferences(prefName, android.content.Context.MODE_PRIVATE)

fun log(msg: Any?, tag: String = "NRLogs") {
    if (BuildConfig.DEBUG) {
        Log.d(tag, "$msg")
    }
}

fun Throwable.printDebugStackTrace() {
    if (BuildConfig.DEBUG) {
        this.printStackTrace()
    }
}

fun Activity.hideKeyboard() {
    val imm = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    //Find the currently focused view, so we can grab the correct window token from it.
    var view = currentFocus
    //If no view currently has focus, create a new one, just so we can grab a window token from it
    if (view == null) {
        view = View(this)
    }
    imm.hideSoftInputFromWindow(view.windowToken, 0)
}

fun Context?.isInternetAvailable(): Boolean {
    return try {
        val connectivityManager = this?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        connectivityManager.activeNetworkInfo != null &&
                (connectivityManager.activeNetworkInfo!!.isConnected)
    } catch (ex: Exception) {
        ex.printDebugStackTrace()
        false
    }
}

fun Context.getCompatDrawable(@DrawableRes id: Int): Drawable? =
        ContextCompat.getDrawable(this, id)

fun Context.getCompatColor(@ColorRes id: Int): Int =
        ContextCompat.getColor(this, id)

fun Activity.requestRequiredPermission(permissions: Array<String>, requestCode: Int) {
    ActivityCompat.requestPermissions(this, permissions, requestCode)
}

fun Fragment.requestRequiredPermission(permissions: Array<String>, requestCode: Int) {
    requestPermissions(permissions, requestCode)
}



fun Context.checkRequiredPermission(permission: String): Boolean {
    return ContextCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_GRANTED
}

fun Activity.openInstagramPage(page: String) {
    val uri = Uri.parse("http://instagram.com/_u/$page")
    val likeIng = Intent(Intent.ACTION_VIEW, uri)
    likeIng.`package` = "com.instagram.android"
    try {
        startActivity(likeIng)
    } catch (e: ActivityNotFoundException) {
        startActivity(Intent(Intent.ACTION_VIEW,
                Uri.parse("http://instagram.com/$page")))
    }
}

fun Activity.openSnapchatPage(page: String) {
    val uri = Uri.parse("snapchat://add/$page")
    val likeIng = Intent(Intent.ACTION_VIEW, uri)
    likeIng.`package` = "com.instagram.android"
    try {
        startActivity(likeIng)
    } catch (e: ActivityNotFoundException) {
        startActivity(Intent(Intent.ACTION_VIEW,
                Uri.parse("https://snapchat.com/add/$page")))
    }
}


