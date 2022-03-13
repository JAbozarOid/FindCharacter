package com.truecaller.testassignment.di

import com.truecaller.testassignment.data.network.ContentApi
import com.truecaller.testassignment.utils.constants.ApiConstants.Companion.BASE_URL
import com.truecaller.testassignment.utils.network.LiveDataCallAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitNetworkModule {

    @CloudRetrofit
    @Provides
    @Singleton
    fun provideOkhttp(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        val okhttpBuilder =
            OkHttpClient().newBuilder().readTimeout(15, TimeUnit.SECONDS)
                .connectTimeout(15, TimeUnit.SECONDS)
                .addInterceptor(interceptor)
        return okhttpBuilder.build()
    }

    @CloudRetrofit
    @Singleton
    @Provides
    fun provideConverterFactory(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }

    @CloudRetrofit
    @Singleton
    @Provides
    fun provideRetrofitInstance(
        @CloudRetrofit okHttpClient: OkHttpClient,
        @CloudRetrofit gsonConverterFactory: GsonConverterFactory
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(ScalarsConverterFactory.create())
            .addCallAdapterFactory(LiveDataCallAdapterFactory())
            .addConverterFactory(gsonConverterFactory)
            .build()
    }

    @Provides
    @Singleton
    fun provideContentApiService(
        @CloudRetrofit
        retrofit: Retrofit
    ): ContentApi {
        return retrofit.create(ContentApi::class.java)
    }


}

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class CloudRetrofit

