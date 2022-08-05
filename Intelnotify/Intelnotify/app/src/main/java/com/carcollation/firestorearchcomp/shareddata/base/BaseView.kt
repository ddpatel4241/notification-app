package com.carcollation.firestorearchcomp.shareddata.base

import android.content.Context
import io.reactivex.disposables.CompositeDisposable


interface BaseView {
    val mContext: Context?
    val mCompositeDisposable: CompositeDisposable
    fun showProgress(visibility: Boolean)
    fun showContent(visibility: Boolean)
    fun showEmptyView(visibility: Boolean = true, message: String = "")
    fun handleNoInternet()
}