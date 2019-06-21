package com.github.teracy.odpt.core.v4.train

import com.github.teracy.odpt.core.v4.train.request.StationArgument
import com.github.teracy.odpt.core.v4.train.response.OdptStation
import com.github.teracy.odpt.model.ApiClient

/**
 * v4版地物情報検索APIを利用した鉄道情報クライアント
 */
interface TrainPlaceApiClient : ApiClient {
    /**
     * v4版鉄道地物情報取得・検索API接続サービスのインスタンスを取得する
     */
    fun trainPlaceApiService(): TrainPlaceApiService

    /**
     * 駅情報取得（地物情報検索API利用）
     *
     * @param longitude 検索範囲の中心点の経度、10進表記
     * @param latitude 検索範囲の中心点の緯度、10進表記
     * @param radius 検索範囲の半径(m)
     * @param predicate 検索フィルタリング
     * @return 駅情報リスト
     */
    suspend fun getStation(
        longitude: Double,
        latitude: Double,
        radius: Double,
        predicate: Map<String, String>? = null
    ): List<OdptStation> {
        predicate?.let {
            return trainPlaceApiService().getStationAsync(
                consumerKey = consumerKey(),
                longitude = longitude,
                latitude = latitude,
                radius = radius,
                predicate = predicate
            ).await()
        }
        return trainPlaceApiService().getStationAsync(
            consumerKey = consumerKey(),
            longitude = longitude,
            latitude = latitude,
            radius = radius
        ).await()
    }

    /**
     * 駅情報取得（地物情報検索API利用）
     *
     * @param longitude 検索範囲の中心点の経度、10進表記
     * @param latitude 検索範囲の中心点の緯度、10進表記
     * @param radius 検索範囲の半径(m)
     * @param argument 駅情報検索引数
     * @return 駅情報リスト
     */
    suspend fun getStation(
        longitude: Double,
        latitude: Double,
        radius: Double,
        argument: StationArgument
    ): List<OdptStation> {
        val predicate = argument.let { arg ->
            val map = mutableMapOf<String, String>()
            map.putIfNotNull("@id", arg.idList)
            map.putIfNotNull("owl:sameAs", arg.sameAsList)
            map.putIfNotNull("dc:title", arg.titleList)
            map.putIfNotNull("odpt:railway", arg.railwayList)
            map.putIfNotNull("odpt:operator", arg.operatorList)
            map.putIfNotNull("odpt:stationCode", arg.stationCodeList)
            return@let if (map.isEmpty()) {
                null
            } else {
                map
            }
        }
        return getStation(
            longitude = longitude,
            latitude = latitude,
            radius = radius,
            predicate = predicate
        )
    }

    private fun List<String>?.concatenate(): String? {
        return this?.joinToString(separator = ",")
    }

    private fun MutableMap<String, String>.putIfNotNull(key: String, valueList: List<String>?) {
        valueList?.let { list ->
            if (list.any { it.isNotBlank() }) {
                put(key, list.concatenate()!!)
            }
        }
    }
}
