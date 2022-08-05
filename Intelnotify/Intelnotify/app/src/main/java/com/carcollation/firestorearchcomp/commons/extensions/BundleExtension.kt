package com.carcollation.firestorearchcomp.commons.extensions

import android.os.Bundle

/**
 * Created by aakash on 28/8/18.
 */
operator fun Bundle.set(key: String, value: String) {
    putString(key, value)
}
