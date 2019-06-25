package com.github.teracy.odpt.api.odptdatacenter

import com.github.teracy.odpt.core.geojson.adapter.GeometryAdapter
import com.github.teracy.odpt.core.v4.bus.BusDataPointApiClient
import com.github.teracy.odpt.core.v4.bus.BusDataPointApiService
import com.github.teracy.odpt.core.v4.bus.BusPlaceApiClient
import com.github.teracy.odpt.core.v4.bus.BusPlaceApiService
import com.github.teracy.odpt.model.adapter.ArrayOrObjectAdapter
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Inject

/**
 * 公共交通オープンデータセンターバスAPI向けクライアント実装
 *
 * @param okHttpClient OKHttpClientインスタンス
 * @param consumerKey APIアクセス用のアクセストークン
 */
class OdptDataCenterBusApiClient @Inject constructor(
    private val okHttpClient: OkHttpClient,
    private val consumerKey: String
) : OdptDataCenterApiClient, BusDataPointApiClient, BusPlaceApiClient {
    private val moshi: Moshi by lazy {
        Moshi.Builder()
            .add(ArrayOrObjectAdapter.FACTORY)
            .add(GeometryAdapter.FACTORY)
            .build()
    }
    private val busDataPointApiService: BusDataPointApiService  by lazy {
        Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(baseUrl())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(BusDataPointApiService::class.java)
    }
    private val busPlaceApiService: BusPlaceApiService by lazy {
        Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(baseUrl())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(BusPlaceApiService::class.java)
    }

    override fun consumerKey(): String {
        return consumerKey
    }

    override fun busDataPointApiService(): BusDataPointApiService {
        return busDataPointApiService
    }

    override fun busPlaceApiService(): BusPlaceApiService {
        return busPlaceApiService
    }
}
