package com.github.teracy.odpt.api.tokyometro

import com.github.teracy.odpt.core.v2.train.TrainDataPointApiClient
import com.github.teracy.odpt.core.v2.train.TrainDataPointApiService
import com.github.teracy.odpt.core.v2.train.TrainPlaceApiClient
import com.github.teracy.odpt.core.v2.train.TrainPlaceApiService
import com.github.teracy.odpt.core.v2.train.adapter.GeometryOrEmptyListAdapter
import com.github.teracy.odpt.model.adapter.ArrayOrObjectAdapter
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Inject

/**
 * 東京メトロオープンデータAPI向けクライアント実装
 *
 * @param okHttpClient OKHttpClientインスタンス
 * @param consumerKey APIアクセス用のアクセストークン
 */
class TokyoMetroApiClient @Inject constructor(private val okHttpClient: OkHttpClient, private val consumerKey: String) :
    TrainDataPointApiClient, TrainPlaceApiClient {
    companion object {
        private const val BASE_URL = "https://api.tokyometroapp.jp/"
    }

    private val moshi: Moshi by lazy {
        Moshi.Builder()
            .add(ArrayOrObjectAdapter.FACTORY)
            .add(GeometryOrEmptyListAdapter.FACTORY)
            .build()
    }
    private val trainDataPointApiService: TrainDataPointApiService  by lazy {
        Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(baseUrl())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(TrainDataPointApiService::class.java)
    }
    private val trainPlaceApiService: TrainPlaceApiService by lazy {
        Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(baseUrl())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(TrainPlaceApiService::class.java)
    }

    override fun consumerKey(): String {
        return consumerKey
    }

    override fun baseUrl(): String {
        return BASE_URL
    }

    override fun trainDataPointApiService(): TrainDataPointApiService {
        return trainDataPointApiService
    }

    override fun trainPlaceApiService(): TrainPlaceApiService {
        return trainPlaceApiService
    }
}
