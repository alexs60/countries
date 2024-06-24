package com.alessandrofarandagancio.sampleapp.app;

import android.content.Context
import android.net.ConnectivityManager
import com.alessandrofarandagancio.sampleapp.common.Constants
import com.alessandrofarandagancio.sampleapp.common.NetworkHelper
import com.alessandrofarandagancio.sampleapp.common.NetworkHelperImpl
import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

interface AppModule {
    val connectivityManager: ConnectivityManager
    val networkHelper: NetworkHelper
    val retrofit: Retrofit
}

class AppModuleImpl(private val appContext: Context) : AppModule {
    override val connectivityManager: ConnectivityManager by lazy {
        appContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    }
    override val networkHelper: NetworkHelper by lazy {
        NetworkHelperImpl(connectivityManager)
    }
    override val retrofit: Retrofit by lazy {
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BASIC
        }

        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()

        Retrofit.Builder()
            .baseUrl(Constants.BASE_COUNTRY_URL)
            .addConverterFactory(GsonConverterFactory.create(Gson()))
            .client(okHttpClient)
            .build()
    }
}
