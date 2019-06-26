package com.github.teracy.odpt.core.v2.train.response

import com.github.teracy.odpt.model.StationId
import com.squareup.moshi.Json

/**
 * v2版列車時刻表情報APIレスポンス詳細情報
 */
data class OdptTrainTimetableObject(
    /**
     * 出発時間（ISO8601 時刻形式）
     */
    @field:Json(name = "odpt:departureTime")
    val departureTime: String?,
    /**
     * 出発駅のID
     * @see OdptStation.sameAs
     */
    @field:Json(name = "odpt:departureStation")
    val departureStation: String?,
    /**
     * 到着時間（ISO8601 時刻形式）
     */
    @field:Json(name = "odpt:arrivalTime")
    val arrivalTime: String?,
    /**
     * 到着駅のID
     * @see OdptStation.sameAs
     */
    @field:Json(name = "odpt:arrivalStation")
    val arrivalStation: String?
) {
    /**
     * 出発駅の駅ID
     */
    val departureStationId: StationId?
        get() = departureStation?.let { StationId(it) }
    /**
     * 到着駅の駅ID
     */
    val arrivalStationId: StationId?
        get() = arrivalStation?.let { StationId(it) }
}
