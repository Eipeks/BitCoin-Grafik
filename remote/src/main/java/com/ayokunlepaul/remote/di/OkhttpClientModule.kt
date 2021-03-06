package com.ayokunlepaul.remote.di

import android.content.Context
import com.ayokunlepaul.remote.utils.BlockchainGrafikConstants
import com.ayokunlepaul.remote.utils.okhttp
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.io.File
import java.util.concurrent.TimeUnit

@Module(includes = [
    InterceptorsModule::class
])
class OkhttpClientModule {

    @Provides
    fun provideFile(context: Context): File {
        return File(context.cacheDir, BlockchainGrafikConstants.OKHTTP_CACHE)
    }

    @Provides
    fun provideCache(file: File): Cache {
        return Cache(file, 10 * 1000 * 1000)
    }

    @Provides
    fun provideOkhttpClient(
        cache: Cache,
        httpInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {
        return okhttp {
            addInterceptor(httpInterceptor)
            followSslRedirects(true)
            followRedirects(true)
            cache(cache)
            connectTimeout(30, TimeUnit.SECONDS)
            readTimeout(30, TimeUnit.SECONDS)
            writeTimeout(30, TimeUnit.SECONDS)
        }
    }
}