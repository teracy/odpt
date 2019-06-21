package com.github.teracy.odpt.core.v4.bus

import com.github.teracy.odpt.core.v4.bus.request.BusStopPoleArgument
import com.github.teracy.odpt.core.v4.bus.response.OdptBusStopPole
import com.github.teracy.odpt.model.ApiClient

/**
 * v4版地物情報検索APIを利用したバス情報クライアント
 */
interface BusPlaceApiClient : ApiClient {
    /**
     * v4版バス地物情報取得・検索API接続サービスのインスタンスを取得する
     */
    fun busPlaceApiService(): BusPlaceApiService

    /**
     * バス停標柱情報取得（地物情報検索API利用）
     *
     * @param longitude 検索範囲の中心点の経度、10進表記
     * @param latitude 検索範囲の中心点の緯度、10進表記
     * @param radius 検索範囲の半径(m)
     * @param predicate 検索フィルタリング
     * @return バス停標柱情報リスト
     */
    suspend fun getBusStopPole(
        longitude: Double,
        latitude: Double,
        radius: Double,
        predicate: Map<String, String>? = null
    ): List<OdptBusStopPole> {
        predicate?.let {
            return busPlaceApiService().getBusStopPoleAsync(
                consumerKey = consumerKey(),
                longitude = longitude,
                latitude = latitude,
                radius = radius,
                predicate = predicate
            ).await()
        }
        return busPlaceApiService().getBusStopPoleAsync(
            consumerKey = consumerKey(),
            longitude = longitude,
            latitude = latitude,
            radius = radius
        ).await()
    }

    /**
     * バス停標柱情報取得（地物情報検索API利用）
     *
     * @param longitude 検索範囲の中心点の経度、10進表記
     * @param latitude 検索範囲の中心点の緯度、10進表記
     * @param radius 検索範囲の半径(m)
     * @param argument 駅情報検索引数
     * @return バス停標柱情報リスト
     */
    suspend fun getBusStopPole(
        longitude: Double,
        latitude: Double,
        radius: Double,
        argument: BusStopPoleArgument
    ): List<OdptBusStopPole> {
        val predicate = argument.let { arg ->
            val map = mutableMapOf<String, String>()
            map.putIfNotNull("@id", arg.idList)
            map.putIfNotNull("owl:sameAs", arg.sameAsList)
            map.putIfNotNull("dc:title", arg.titleList)
            map.putIfNotNull("odpt:busstopPoleNumber", arg.busStopPoleNumberList)
            map.putIfNotNull("odpt:busroutePattern", arg.busRoutePatternList)
            map.putIfNotNull("odpt:operator", arg.operatorList)
            return@let if (map.isEmpty()) {
                null
            } else {
                map
            }
        }
        return getBusStopPole(
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
