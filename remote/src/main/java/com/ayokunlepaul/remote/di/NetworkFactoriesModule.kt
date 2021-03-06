package com.ayokunlepaul.remote.di

import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
class NetworkFactoriesModule {

    @Provides
    internal fun provideGsonConverterFactory(
        gson: Gson
    ): GsonConverterFactory {
        return GsonConverterFactory.create(gson)
    }

    @Provides
    internal fun provideGson() = Gson()

    @Provides
    internal fun provideRxJavaAdapterFactory(): RxJava2CallAdapterFactory {
//        return RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io())
        return RxJava2CallAdapterFactory.create()
    }
}