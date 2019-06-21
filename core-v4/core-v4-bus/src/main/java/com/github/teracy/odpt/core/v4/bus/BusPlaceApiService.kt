package com.github.teracy.odpt.core.v4.bus

import com.github.teracy.odpt.core.v4.bus.response.OdptBusStopPole
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query
import retrofit2.http.QueryMap

/**
 * v4版バス地物情報取得・検索API接続サービスinterface
 */
interface BusPlaceApiService {
    /**
     * バス停標柱情報取得
     *
     * @param consumerKey APIアクセス用のアクセストークン
     * @param longitude 検索範囲の中心点の経度、10進表記
     * @param latitude 検索範囲の中心点の緯度、10進表記
     * @param radius 検索範囲の半径(m)
     * @return バス停標柱情報リスト
     */
    @Headers("connection: close")
    @GET("/api/v4/places/odpt:BusstopPole")
    fun getBusStopPoleAsync(
        @Query("acl:consumerKey") consumerKey: String,
        @Query("lon") longitude: Double,
        @Query("lat") latitude: Double,
        @Query("radius") radius: Double
    ): Deferred<List<OdptBusStopPole>>

    /**
     * バス停標柱情報取得（検索フィルタリングあり）
     *
     * @param consumerKey APIアクセス用のアクセストークン
     * @param longitude 検索範囲の中心点の経度、10進表記
     * @param latitude 検索範囲の中心点の緯度、10進表記
     * @param radius 検索範囲の半径(m)
     * @param predicate 検索フィルタリング
     * @return バス停標柱情報リスト
     */
    @Headers("connection: close")
    @GET("/api/v4/places/odpt:BusstopPole")
    fun getBusStopPoleAsync(
        @Query("acl:consumerKey") consumerKey: String,
        @Query("lon") longitude: Double,
        @Query("lat") latitude: Double,
        @Query("radius") radius: Double,
        @QueryMap predicate: Map<String, String>
    ): Deferred<List<OdptBusStopPole>>
}
