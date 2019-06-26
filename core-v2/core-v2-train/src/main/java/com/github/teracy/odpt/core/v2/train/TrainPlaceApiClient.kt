package com.github.teracy.odpt.core.v2.train

import com.github.teracy.odpt.core.geojson.response.Geometry
import com.github.teracy.odpt.core.v2.train.response.*
import com.github.teracy.odpt.model.ApiClient

/**
 * v2版地物情報検索APIを利用した鉄道情報クライアント
 */
interface TrainPlaceApiClient : ApiClient {
    /**
     * v2版鉄道地物情報取得・検索API接続サービスのインスタンス
     */
    fun trainPlaceApiService(): TrainPlaceApiService

    /**
     * 駅情報取得（地物情報検索API利用）
     *
     * @param longitude 検索範囲の中心点の経度、10進表記
     * @param latitude 検索範囲の中心点の緯度、10進表記
     * @param radius 検索範囲の半径(m)
     * @return 駅情報リスト
     */
    suspend fun getStation(
        longitude: Double,
        latitude: Double,
        radius: Double
    ): List<OdptStation> {
        return trainPlaceApiService().getStationAsync(
            consumerKey = consumerKey(),
            longitude = longitude,
            latitude = latitude,
            radius = radius
        ).await()
    }

    /**
     * 鉄道路線情報取得（地物情報検索API利用）
     *
     * @param longitude 検索範囲の中心点の経度、10進表記
     * @param latitude 検索範囲の中心点の緯度、10進表記
     * @param radius 検索範囲の半径(m)
     * @return 鉄道路線情報リスト
     */
    suspend fun getRailway(
        longitude: Double,
        latitude: Double,
        radius: Double
    ): List<OdptRailway> {
        return trainPlaceApiService().getRailwayAsync(
            consumerKey = consumerKey(),
            longitude = longitude,
            latitude = latitude,
            radius = radius
        ).await()
    }

    /**
     * 国土交通省国土数値情報の駅情報取得（地物情報検索API利用）
     *
     * @param longitude 検索範囲の中心点の経度、10進表記
     * @param latitude 検索範囲の中心点の緯度、10進表記
     * @param radius 検索範囲の半径(m)
     * @return 鉄道路線情報リスト
     */
    suspend fun getMlitStation(
        longitude: Double,
        latitude: Double,
        radius: Double
    ): List<MlitStation> {
        return trainPlaceApiService().getMlitStationAsync(
            consumerKey = consumerKey(),
            longitude = longitude,
            latitude = latitude,
            radius = radius
        ).await()
    }

    /**
     * 国土交通省国土数値情報の鉄道路線情報取得（地物情報検索API利用）
     *
     * @param longitude 検索範囲の中心点の経度、10進表記
     * @param latitude 検索範囲の中心点の緯度、10進表記
     * @param radius 検索範囲の半径(m)
     * @return 鉄道路線情報リスト
     */
    suspend fun getMlitRailway(
        longitude: Double,
        latitude: Double,
        radius: Double
    ): List<MlitRailway> {
        return trainPlaceApiService().getMlitRailwayAsync(
            consumerKey = consumerKey(),
            longitude = longitude,
            latitude = latitude,
            radius = radius
        ).await()
    }

    /**
     * 駅出入口情報取得（地物情報検索API利用）
     *
     * @param id 固有識別子(ucode)
     * @param longitude 検索範囲の中心点の経度、10進表記
     * @param latitude 検索範囲の中心点の緯度、10進表記
     * @param radius 検索範囲の半径(m)
     * @return 駅出入口情報リスト
     */
    suspend fun getUgPoi(
        id: String? = null,
        longitude: Double,
        latitude: Double,
        radius: Double
    ): List<UgPoi> {
        return trainPlaceApiService().getUgPoiAsync(
            consumerKey = consumerKey(),
            id = id,
            longitude = longitude,
            latitude = latitude,
            radius = radius
        ).await()
    }

    /**
     * 地物の形状データを取得する
     * @param url 地物の形状データをGeoJSONで取得するためのURL
     * @return 地物の形状データのリスト
     */
    suspend fun getGeometry(url: String): List<Geometry> {
        return trainPlaceApiService().getGeometryAsync("$url?acl:consumerKey=${consumerKey()}").await()
    }
}
