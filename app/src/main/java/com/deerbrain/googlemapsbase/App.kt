package com.deerbrain.googlemapsbase

import android.app.Application
import android.content.Context
import io.realm.Realm

class App: Application() {
    override fun onCreate() {
        super.onCreate()
        //realm init
        Realm.init(this)


        context = applicationContext

        //FirebaseApp.initializeApp(context)
    }

    companion object{
        lateinit var context: Context
    }

}