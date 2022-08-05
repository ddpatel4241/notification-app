package com.carcollation.firestorearchcomp.injection.module

import com.carcollation.firestorearchcomp.AppApplication
import com.carcollation.firestorearchcomp.commons.extensions.getPrefInstance
import com.carcollation.firestorearchcomp.commons.pref.SessionHolder
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by aakash on 9/8/18.
 */
@Module
class SessionHolderModule {

    @Provides
    @Singleton
    fun provideUserHolder(): SessionHolder =
        SessionHolder(
            AppApplication.instance.getPrefInstance(
                "SessionData"
            )
        )

}