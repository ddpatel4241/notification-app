package com.carcollation.firestorearchcomp.shareddata.base

import android.content.Context
import io.reactivex.disposables.CompositeDisposable

interface BaseHomeView {

    val mContext: Context?
    val mCompositeDisposable: CompositeDisposable
    fun showProgress(visibility: Boolean)
    fun showContent(visibility: Boolean)
    fun showEmptyView(visibility: Boolean = true, message: String = "")
    fun showBannerEmptyView(visibility: Boolean = true, message: String = "")
    fun showScheduleEmptyView(visibility: Boolean = true, message: String = "")
    fun showVideoEmptyView(visibility: Boolean = true, message: String = "")
    fun handleNoInternet()
    fun showBannerViewContent(visibility: Boolean)
    fun showscheduleViewContent(visibility: Boolean)

    fun showscheduleProgress(visibility: Boolean)

    fun showallvideoProgress(visibility: Boolean)
    fun showallvideoViewContent(visibility: Boolean)



}