package com.carcollation.firestorearchcomp

import android.os.StrictMode
import androidx.multidex.MultiDex
import androidx.multidex.MultiDexApplication
import com.carcollation.firestorearchcomp.commons.helper.AppSignatureHelper
import com.carcollation.firestorearchcomp.commons.pref.FirebaseAccessToken
import com.carcollation.firestorearchcomp.commons.pref.FontsPref
import com.carcollation.firestorearchcomp.injection.component.AppComponent
import com.carcollation.firestorearchcomp.injection.component.DaggerAppComponent
import com.carcollation.firestorearchcomp.injection.module.RetrofitModule
import com.carcollation.firestorearchcomp.injection.module.SessionHolderModule

import com.facebook.FacebookSdk
import com.facebook.appevents.AppEventsLogger

import io.reactivex.subjects.PublishSubject
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit


class AppApplication : MultiDexApplication() {

    lateinit var mComponent: AppComponent
    val notificationPublishSubject = PublishSubject.create<String>()
    val StepPublishSubject = PublishSubject.create<Int>()
    private val AF_DEV_KEY = "eWj2rrokBqDPGoErngD6zk"
    companion object {
        lateinit var instance : AppApplication
        lateinit var countmemer : String
        fun getApplicationContext() = instance
        lateinit var prefs: FontsPref
        lateinit var refreshtoken: FirebaseAccessToken

    }


    override fun onCreate() {
        super.onCreate()
        instance = this
        methodchange()
        MultiDex.install(this)
        prefs =
            FontsPref(applicationContext)
        refreshtoken = FirebaseAccessToken(applicationContext)



        val builder = StrictMode.VmPolicy.Builder()
        StrictMode.setVmPolicy(builder.build())

    }

    fun methodchange() {

        mComponent = DaggerAppComponent.builder()
                .retrofitModule(RetrofitModule("http://serverstaging.com/"))
            .sessionHolderModule(SessionHolderModule())
                .build()
    }



}