package com.github.teracy.odpt.core.v2.train

import com.github.teracy.odpt.core.geojson.response.Geometry
import com.github.teracy.odpt.core.v2.train.response.*
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query
import retrofit2.http.Url

/**
 * v2版鉄道地物情報取得・検索API接続サービスinterface
 */
interface TrainPlaceApiService {
    /**
     * 駅情報取得（地物情報検索API利用）
     *
     * @param consumerKey APIアクセス用のアクセストークン
     * @param longitude 検索範囲の中心点の経度、10進表記
     * @param latitude 検索範囲の中心点の緯度、10進表記
     * @param radius 検索範囲の半径(m)
     * @return 駅情報リスト
     */
    @Headers("connection: close")
    @GET("api/v2/places?rdf:type=odpt:Station")
    fun getStationAsync(
        @Query("acl:consumerKey") consumerKey: String,
        @Query("lon") longitude: Double,
        @Query("lat") latitude: Double,
        @Query("radius") radius: Double
    ): Deferred<List<OdptStation>>

    /**
     * 鉄道路線情報取得（地物情報検索API利用）
     *
     * @param consumerKey APIアクセス用のアクセストークン
     * @param longitude 検索範囲の中心点の経度、10進表記
     * @param latitude 検索範囲の中心点の緯度、10進表記
     * @param radius 検索範囲の半径(m)
     * @return 鉄道路線情報リスト
     */
    @Headers("connection: close")
    @GET("api/v2/places?rdf:type=odpt:Railway")
    fun getRailwayAsync(
        @Query("acl:consumerKey") consumerKey: String,
        @Query("lon") longitude: Double,
        @Query("lat") latitude: Double,
        @Query("radius") radius: Double
    ): Deferred<List<OdptRailway>>

    /**
     * 国土交通省国土数値情報の駅情報取得
     *
     * @param consumerKey APIアクセス用のアクセストークン
     * @param longitude 検索範囲の中心点の経度、10進表記
     * @param latitude 検索範囲の中心点の緯度、10進表記
     * @param radius 検索範囲の半径(m)
     * @return 駅情報リスト
     */
    @Headers("connection: close")
    @GET("api/v2/places?rdf:type=mlit:Station")
    fun getMlitStationAsync(
        @Query("acl:consumerKey") consumerKey: String,
        @Query("lon") longitude: Double,
        @Query("lat") latitude: Double,
        @Query("radius") radius: Double
    ): Deferred<List<MlitStation>>

    /**
     * 国土交通省国土数値情報の鉄道路線情報取得
     *
     * @param consumerKey APIアクセス用のアクセストークン
     * @param longitude 検索範囲の中心点の経度、10進表記
     * @param latitude 検索範囲の中心点の緯度、10進表記
     * @param radius 検索範囲の半径(m)
     * @return 鉄道路線情報リスト
     */
    @Headers("connection: close")
    @GET("api/v2/places?rdf:type=mlit:Railway")
    fun getMlitRailwayAsync(
        @Query("acl:consumerKey") consumerKey: String,
        @Query("lon") longitude: Double,
        @Query("lat") latitude: Double,
        @Query("radius") radius: Double
    ): Deferred<List<MlitRailway>>

    /**
     * 駅出入口情報取得（地物情報検索API利用）
     *
     * @param consumerKey APIアクセス用のアクセストークン
     * @param id 固有識別子(ucode)
     * @param longitude 検索範囲の中心点の経度、10進表記
     * @param latitude 検索範囲の中心点の緯度、10進表記
     * @param radius 検索範囲の半径(m)
     * @return 駅出入口情報リスト
     */
    @Headers("connection: close")
    @GET("api/v2/places?rdf:type=ug:Poi")
    fun getUgPoiAsync(
        @Query("acl:consumerKey") consumerKey: String,
        @Query("@id") id: String? = null,
        @Query("lon") longitude: Double,
        @Query("lat") latitude: Double,
        @Query("radius") radius: Double
    ): Deferred<List<UgPoi>>

    /**
     * 地物の形状データを取得する
     * NOTE: APIの定義上はObjectを返すが、リクエストが空振りした場合に空Arrayを返すこと、また他のAPIがArrayを返すことから揃えている
     * @see com.github.teracy.odpt.core.v2.train.adapter.GeometryOrEmptyListAdapter
     *
     * @param url 地物の形状データをGeoJSONで取得するためのURL
     * @return 地物の形状データのリスト
     */
    @Headers("connection: close")
    @GET
    fun getGeometryAsync(@Url url: String): Deferred<List<Geometry>>
}
