package com.github.teracy.odpt.core.v4.train.response

import com.github.teracy.odpt.model.StationId
import com.squareup.moshi.Json

/**
 * v4版列車時刻表情報APIレスポンス詳細情報
 */
data class OdptTrainTimetableObject(
    /**
     * 到着時刻（ISO8601 時刻形式）
     */
    @field:Json(name = "odpt:arrivalTime")
    val arrivalTime: String?,
    /**
     * 到着駅のID
     * @see OdptRailway.sameAs
     */
    @field:Json(name = "odpt:arrivalStation")
    val arrivalStation: String?,
    /**
     * 出発時間（ISO8601 時刻形式）
     */
    @field:Json(name = "odpt:departureTime")
    val departureTime: String?,
    /**
     * 出発駅のID
     * @see OdptRailway.sameAs
     */
    @field:Json(name = "odpt:departureStation")
    val departureStation: String?,
    /**
     * 列車が到着するプラットフォームの番号
     * @see OdptStationTimetableObject.platformNumber
     */
    @field:Json(name = "odpt:platformNumber")
    val platformNumber: Int?,
    /**
     * 列車が到着又は出発するプラットフォームの名称（多言語対応）
     * @see OdptStationTimetableObject.platformNameMap
     */
    @field:Json(name = "odpt:platformName")
    val platformNameMap: Map<String, String>?,
    /**
     * その他プロパティとして定義されていない注釈情報の自然言語による記載（多言語対応）
     */
    @field:Json(name = "odpt:note")
    val note: Map<String, String>?
) {
    /**
     * 到着駅の駅ID
     */
    val arrivalStationId: StationId?
        get() = arrivalStation?.let { StationId(it) }
    /**
     * 出発駅の駅ID
     */
    val departureStationId: StationId?
        get() = departureStation?.let { StationId(it) }
}
