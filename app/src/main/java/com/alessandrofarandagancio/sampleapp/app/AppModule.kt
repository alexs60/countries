package com.alessandrofarandagancio.sampleapp.app;

import android.content.Context
import android.net.ConnectivityManager
import com.alessandrofarandagancio.sampleapp.common.NetworkHelper
import com.alessandrofarandagancio.sampleapp.common.NetworkHelperImpl

interface AppModule {
    val connectivityManager: ConnectivityManager
    val networkHelper: NetworkHelper
}

class AppModuleImpl(private val appContext: Context) : AppModule {
    override val connectivityManager: ConnectivityManager by lazy {
        appContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    }
    override val networkHelper: NetworkHelper by lazy {
        NetworkHelperImpl(connectivityManager)
    }
}
