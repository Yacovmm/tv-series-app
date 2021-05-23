package com.zygotecnologia.zygotv.di

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.zygotecnologia.zygotv.data.network.interceptors.RegionInterceptor
import com.zygotecnologia.zygotv.data.network.TmdbApi
import com.zygotecnologia.zygotv.data.network.interceptors.TokenInterceptor
import com.zygotecnologia.zygotv.domain.ITmdbRepository
import com.zygotecnologia.zygotv.domain.TmdbRespositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MainModule {

    @Provides
    @Singleton
    fun provideOkHttpClient() = OkHttpClient.Builder()
        .addInterceptor(TokenInterceptor())
        .addInterceptor(RegionInterceptor())
        .addInterceptor(
            HttpLoggingInterceptor()
                .setLevel(HttpLoggingInterceptor.Level.BODY)
        )
        .build()

    @Provides
    @Singleton
    fun provideMoshiAdapter(): MoshiConverterFactory = MoshiConverterFactory.create(
        Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    )

    @Provides
    @Singleton
    fun provideRetrofitInstance(client: OkHttpClient, moshi: MoshiConverterFactory): Retrofit =
        Retrofit.Builder()
            .baseUrl(TmdbApi.TMDB_BASE_URL)
            .addConverterFactory(moshi)
            .client(client)
            .build()

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): TmdbApi = retrofit
        .create(TmdbApi::class.java)

    @Provides
    @Singleton
    fun provideDefaultDispatcher(): CoroutineDispatcher = Dispatchers.IO

    @Provides
    @Singleton
    fun provideTmdbRepository(
        service: TmdbApi,
        dispatcher: CoroutineDispatcher
    ): ITmdbRepository = TmdbRespositoryImpl(service, dispatcher)
}
