package com.github.teracy.odpt.core.v4.train.response

import com.github.teracy.odpt.model.StationId
import com.squareup.moshi.Json

/**
 * v4版鉄道路線情報APIレスポンスの駅の順序情報
 */
data class OdptStationOrder(
    /**
     * 駅のID
     * @see OdptStation.sameAs
     */
    @field:Json(name = "odpt:station")
    val station: String,
    /**
     * 駅名（多言語対応）
     * @see OdptStation.titleMap
     */
    @field:Json(name = "odpt:stationTitle")
    val stationTitleMap: Map<String, String>?,
    /**
     * 駅の順序
     */
    @field:Json(name = "odpt:index")
    val index: Int
) {
    /**
     * 駅ID
     */
    val stationId: StationId
        get() = StationId(station)
}
