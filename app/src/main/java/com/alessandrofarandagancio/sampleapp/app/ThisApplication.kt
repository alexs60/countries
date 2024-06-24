package com.alessandrofarandagancio.sampleapp.app

import android.app.Application

class ThisApplication : Application() {

    companion object {
        lateinit var appModule: AppModule
        lateinit var domainModule: DomainModule
    }

    override fun onCreate() {
        super.onCreate()
        appModule = AppModuleImpl(this)
        domainModule = DomainModuleImpl()
    }

}