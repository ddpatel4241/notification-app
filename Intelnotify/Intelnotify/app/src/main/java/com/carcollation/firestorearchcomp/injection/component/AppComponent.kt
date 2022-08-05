package com.carcollation.firestorearchcomp.injection.component






import com.carcollation.firestorearchcomp.DetailsPageActivity
import com.carcollation.firestorearchcomp.LoginActivity
import com.carcollation.firestorearchcomp.MainActivity
import com.carcollation.firestorearchcomp.SignupActivity
import com.carcollation.firestorearchcomp.injection.module.RetrofitModule
import com.carcollation.firestorearchcomp.injection.module.SessionHolderModule



import dagger.Component
import javax.inject.Singleton

/**
 * Created by aakash on 9/8/18.
 */
@Singleton
@Component(modules = [(RetrofitModule::class), (SessionHolderModule::class)])
interface AppComponent {


    fun inject(activity: LoginActivity)
    fun inject(activity: SignupActivity)
    fun inject(activity: MainActivity)
    fun inject(activity: DetailsPageActivity)




}