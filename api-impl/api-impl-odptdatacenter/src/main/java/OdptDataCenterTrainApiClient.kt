package com.github.teracy.odpt.api.odptdatacenter

import com.github.teracy.odpt.core.geojson.adapter.GeometryAdapter
import com.github.teracy.odpt.core.v4.train.TrainDataPointApiClient
import com.github.teracy.odpt.core.v4.train.TrainDataPointApiService
import com.github.teracy.odpt.core.v4.train.TrainPlaceApiClient
import com.github.teracy.odpt.core.v4.train.TrainPlaceApiService
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Inject

/**
 * 公共交通オープンデータセンター鉄道API向けクライアント実装
 *
 * @param okHttpClient OKHttpClientインスタンス
 * @param consumerKey APIアクセス用のアクセストークン
 */
class OdptDataCenterTrainApiClient @Inject constructor(
    private val okHttpClient: OkHttpClient,
    private val consumerKey: String
) : OdptDataCenterApiClient, TrainDataPointApiClient, TrainPlaceApiClient {
    private val moshi: Moshi by lazy {
        Moshi.Builder()
            .add(GeometryAdapter.FACTORY)
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

    override fun trainDataPointApiService(): TrainDataPointApiService {
        return trainDataPointApiService
    }

    override fun trainPlaceApiService(): TrainPlaceApiService {
        return trainPlaceApiService
    }
}
