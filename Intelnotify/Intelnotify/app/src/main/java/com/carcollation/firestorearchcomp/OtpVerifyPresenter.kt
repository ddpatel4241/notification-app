package com.carcollation.firestorearchcomp

import android.util.Log
import com.carcollation.firestorearchcomp.commons.extensions.getLinkObject
import com.carcollation.firestorearchcomp.commons.extensions.getObjectList
import com.carcollation.firestorearchcomp.commons.extensions.isInternetAvailable
import com.carcollation.firestorearchcomp.commons.extensions.log
import com.carcollation.firestorearchcomp.commons.pref.SessionHolder
import com.carcollation.firestorearchcomp.shareddata.base.BaseView
import com.carcollation.firestorearchcomp.shareddata.endPoints.EndPoints
import com.carcollation.firestorearchcomp.shareddata.model.CommonPaginationResponse
import com.carcollation.firestorearchcomp.shareddata.model.PostsItem


import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import okhttp3.MultipartBody
import retrofit2.Response
import javax.inject.Inject

class OtpVerifyPresenter @Inject constructor(private val mApi: EndPoints) {

    @Inject
    lateinit var mSessionHolder: SessionHolder
    private lateinit var fragmentView: FragmentView
    private var pageIndex = 1
    private var itemCount = 10

    fun injectView(fragmentView: FragmentView) {
        this.fragmentView = fragmentView
    }

    fun fetchNotificationList(stroid:String,isFromSwipe: Boolean = false) {
        if (!fragmentView.mContext.isInternetAvailable()) {
            fragmentView.handleNoInternet()
        } else {
            if (isFromSwipe) {
                pageIndex = 1
            }
            val requestBody = MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("user_id", mSessionHolder.USER_ID.toString())
                .addFormDataPart("store_id", stroid)
                .build()
            mApi.get_notification_list(requestBody)
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe {
                    fragmentView.showProgress(true)
                }
                .subscribeBy(
                    onNext = { it ->
                        fragmentView.showProgress(false)
                        it.body()?.let { it1 ->
                            if (it1.status == "sucess") {

                                fragmentView.showContent(true)
                                val itemList = it.getObjectList(PostsItem()) as ArrayList<PostsItem?>
                                fragmentView.setNewsList(itemList, it1.message,isFromSwipe = isFromSwipe)

                            } else {
                                log("ERROR : status : ${it1.status}")
                                handleFailure1(it)
                            }

                        } ?: handleFailure1(it)
                    },
                    onError = {
                        fragmentView.mContext?.let { _ ->
                            it.printStackTrace()
                            handleException(it)
                        }
                    }
                ).addTo(fragmentView.mCompositeDisposable)
        }
    }

    fun fetchcategoryList(isFromSwipe: Boolean = false) {
        if (!fragmentView.mContext.isInternetAvailable()) {
            fragmentView.handleNoInternet()
        } else {
            if (isFromSwipe) {
                pageIndex = 1
            }
            mApi.get_store_list()
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe {
                    fragmentView.showProgress(true)
                }
                .subscribeBy(
                    onNext = { it ->
                        fragmentView.showProgress(false)
                        it.body()?.let { it1 ->
                            if (it1.status == "sucess") {

                                fragmentView.showContent(true)
                                val itemList = it.getObjectList(PostsItem()) as ArrayList<PostsItem?>
                                fragmentView.setCategoryList(itemList, it1.message,isFromSwipe = isFromSwipe)

                            } else {
                                log("ERROR : status : ${it1.status}")
                                handleFailure1(it)
                            }

                        } ?: handleFailure1(it)
                    },
                    onError = {
                        fragmentView.mContext?.let { _ ->
                            it.printStackTrace()
                            handleException(it)
                        }
                    }
                ).addTo(fragmentView.mCompositeDisposable)
        }
    }
    fun LoginApi(userid : String,password : String) {
        fragmentView.let { activityView ->
            if (!activityView.mContext.isInternetAvailable()) {
                activityView.handleNoInternet()
            } else {
                val requestBody = MultipartBody.Builder()
                    .setType(MultipartBody.FORM)
                    .addFormDataPart("username", userid)
                    .addFormDataPart("password", password)
                    .addFormDataPart("device_token", AppApplication.refreshtoken.setrefreshtoken.toString())

                    .build()

                mApi.user_login(requestBody)
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnSubscribe {
                        fragmentView.showProgress(true)
                    }
                    .subscribeBy(
                        onNext = { it ->

                            activityView.showProgress(false)
                            it.body()?.let { it1 ->
                                if (it1.status.equals("sucess")) {
                                    val itemList = it.getLinkObject(PostsItem()) as PostsItem
                                    activityView.getuserlogindata(itemList.user_id,it1.status)
                                } else {
                                    activityView.showErrorSnack(it1.status)
                                }

                            }
                        },
                        onError = {
                            activityView.mContext?.let { _ ->
                                it.printStackTrace()
                                handleException(it)
                            }
                        }
                    ).addTo(activityView.mCompositeDisposable)
            }

        }
    }

    fun RegisterApi(name : String,emaildi : String,password : String) {
        fragmentView.let { activityView ->
            if (!activityView.mContext.isInternetAvailable()) {
                activityView.handleNoInternet()
            } else {
                val requestBody = MultipartBody.Builder()
                    .setType(MultipartBody.FORM)
                    .addFormDataPart("username", name)
                    .addFormDataPart("email", emaildi)
                    .addFormDataPart("password", password)
                    .addFormDataPart("device_token", AppApplication.refreshtoken.setrefreshtoken.toString())
                    .build()

                mApi.user_register(requestBody)
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnSubscribe {
                        fragmentView.showProgress(true)
                    }
                    .subscribeBy(
                        onNext = { it ->

                            activityView.showProgress(false)
                            it.body()?.let { it1 ->
                                if (it1.status == "sucess") {
                                    activityView.getuserlogindata("",it1.message)
                                } else {
                                    activityView.showErrorSnack(it1.message)
                                }

                            }
                        },
                        onError = {

                            activityView.mContext?.let { _ ->
                                it.printStackTrace()
                                handleException(it)
                            }
                        }
                    ).addTo(activityView.mCompositeDisposable)
            }

        }
    }




    private fun handleFailure1(response: Response<CommonPaginationResponse>) {
        log("ERROR : status : ${response.code()} \n message : ${response.message()}")
        fragmentView.showContent(false)
        fragmentView.showProgress(false)
        fragmentView.showEmptyView(message = "Something unexpected happen")
    }



    private fun handleFailure2(response: Response<CommonPaginationResponse>) {
        log("ERROR : status : ${response.code()} \n message : ${response.message()}")
        fragmentView.showContent(false)
        fragmentView.showProgress(false)
        fragmentView.showEmptyView(message = "Something unexpected happen")
    }

    private fun handleException(e: Throwable) {
        Log.e("ERRORmessage", e.message.toString())
        /*  fragmentView.showContent(false)
          fragmentView.showProgress(false)
          fragmentView.showEmptyView(message = "Something unexpected happen")*/
    }


    interface FragmentView : BaseView {
        fun showErrorSnack(msg:String)
        fun getuserlogindata(userid:String,msg:String)
        fun setNewsList(itemList: ArrayList<PostsItem?>, msg:String,resetFlag: Boolean = false, isFromSwipe: Boolean = false)
        fun setCategoryList(itemList: ArrayList<PostsItem?>, msg:String,resetFlag: Boolean = false, isFromSwipe: Boolean = false)

    }


}