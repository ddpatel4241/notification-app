package com.carcollation.firestorearchcomp.commons.extensions

import android.graphics.Typeface
import android.text.SpannableString
import android.text.Spanned
import android.text.TextPaint
import android.text.style.ClickableSpan
import android.text.style.ForegroundColorSpan
import android.text.style.TypefaceSpan
import android.text.style.UnderlineSpan
import android.view.View
import com.eonian.reelo.commons.helper.CustomTypefaceSpanHelper

/**
 * Created by aakash on 7/2/18.
 */
fun String?.isNull(): Boolean = this == null || this.equals("")

fun String.format(vararg str: String) = String.format(this, str)

fun String.idFromUrl(): String = this.split("/").let {
    it[it.size - 1]
}

// extensions to highlight text
fun String.highlightText(startIndex: Int, endIndex: Int, spanColor: Int): SpannableString {
    return SpannableString(this).highlightText(startIndex, endIndex, spanColor)
}

fun SpannableString.highlightText(startIndex: Int, endIndex: Int, spanColor: Int): SpannableString {
    return this.apply {
        this.setSpan(ForegroundColorSpan(spanColor),
                startIndex,
                endIndex,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
    }
}

// extensions to set clickable text
fun String.clickEvent(startIndex: Int, endIndex: Int, isLink: Boolean, clickEvent: () -> Unit): SpannableString {
    return SpannableString(this).clickEvent(startIndex, endIndex, isLink, clickEvent)
}

fun SpannableString.clickEvent(startIndex: Int, endIndex: Int, isLink: Boolean, clickEvent: () -> Unit): SpannableString {
    return this.apply {
        this.setSpan(
            WordSpan(isLink, clickEvent), startIndex,
                endIndex,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
    }
}

// extension to underline text
fun String.underlineText(startIndex: Int, endIndex: Int): SpannableString {
    return SpannableString(this).underlineText(startIndex, endIndex)
}

fun SpannableString.underlineText(startIndex: Int, endIndex: Int): SpannableString {
    return this.apply {
        this.setSpan(UnderlineSpan(), startIndex,
                endIndex,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
    }
}

fun String.changeTextStyle(startIndex: Int, endIndex: Int, fontFamily: Typeface): SpannableString {
    return SpannableString(this).changeTextStyle(startIndex, endIndex, fontFamily)
}

fun SpannableString.changeTextStyle(startIndex: Int, endIndex: Int, fontFamily: Typeface): SpannableString {
    return this.apply {
        val fontFamilySpan: TypefaceSpan = CustomTypefaceSpanHelper("customTextStyle",
                fontFamily)
        this.setSpan(fontFamilySpan, startIndex,
                endIndex,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
    }
}

open class WordSpan(isLink: Boolean, onClickEvent: () -> Unit) : ClickableSpan() {
    private val onClickEvent = onClickEvent
    private val isLink = isLink
    override fun updateDrawState(ds: TextPaint) {
        ds.isUnderlineText = isLink
    }

    override fun onClick(widget: View) {
        onClickEvent()
    }
}