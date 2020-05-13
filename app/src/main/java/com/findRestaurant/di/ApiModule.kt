package com.findRestaurant.di


import com.findRestaurant.constant.AppConst
import com.findRestaurant.dataService.NetworkModule.getHttpClient
import com.findRestaurant.dataService.NetworkModule.loggingInterceptor
import com.findRestaurant.dataService.RestService
import com.findRestaurant.dataService.ToStringConverterFactory
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory


val apiModule = module (override = true){

        fun provideUserApi(): RestService {
        val retrofit = Retrofit.Builder().baseUrl(AppConst.BASE_URL())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(ToStringConverterFactory())
                .client(getHttpClient(loggingInterceptor))
                .build()
        return retrofit.create(RestService::class.java)
    }
    single(createOnStart = false) { provideUserApi()  }


}




