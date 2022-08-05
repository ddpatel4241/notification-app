package com.carcollation.firestorearchcomp.commons.extensions

import android.util.Base64
import java.nio.charset.Charset
import java.util.*

/**
 * Created by stllpt065 on 1/11/17.
 */
fun String.isValidEmail(): Boolean {
    return (android.util.Patterns.EMAIL_ADDRESS.matcher(this).matches())
}

fun isOsAboveM(): Boolean = android.os.Build.VERSION.SDK_INT > android.os.Build.VERSION_CODES.M

val random: Int
    get() {
        val random = Random()
        return random.nextInt(3500) + 1500
    }

fun encryptContactId(id: String?): String {
    var encryptedString = ""
    try {
        val data = id!!.toByteArray(charset("UTF-8"))
        encryptedString = Base64.encodeToString(data, Base64.DEFAULT)
    } catch (e: Exception) {
        e.printStackTrace()
    }

    return encryptedString
}

fun decryptContactId(id: String): String {
    var decryptedString = ""
    try {
        val data = Base64.decode(id, Base64.DEFAULT)
        decryptedString = String(data, Charset.forName("UTF-8"))
    } catch (e: Exception) {
        e.printStackTrace()
    }

    return decryptedString
}