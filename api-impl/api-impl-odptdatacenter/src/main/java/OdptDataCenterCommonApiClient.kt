package com.github.teracy.odpt.api.odptdatacenter

import com.github.teracy.odpt.core.v4.common.CommonDataPointApiClient
import com.github.teracy.odpt.core.v4.common.CommonDataPointApiService
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Inject

/**
 * 公共交通オープンデータセンター共通API向けクライアント実装
 *
 * @param okHttpClient OKHttpClientインスタンス
 * @param consumerKey APIアクセス用のアクセストークン
 */
class OdptDataCenterCommonApiClient @Inject constructor(
    private val okHttpClient: OkHttpClient,
    private val consumerKey: String
) : OdptDataCenterApiClient, CommonDataPointApiClient {
    private val moshi: Moshi by lazy {
        Moshi.Builder().build()
    }
    private val commonDataPointApiService: CommonDataPointApiService  by lazy {
        Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(baseUrl())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(CommonDataPointApiService::class.java)
    }

    override fun consumerKey(): String {
        return consumerKey
    }

    override fun commonDataPointApiService(): CommonDataPointApiService {
        return commonDataPointApiService
    }
}
