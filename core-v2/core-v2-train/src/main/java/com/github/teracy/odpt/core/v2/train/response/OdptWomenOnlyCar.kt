package com.github.teracy.odpt.core.v2.train.response

import com.github.teracy.odpt.model.StationId
import com.squareup.moshi.Json

/**
 * v2版鉄道路線情報APIレスポンスの女性専用車両情報
 */
data class OdptWomenOnlyCar(
    /**
     * 女性専用車両開始駅のID
     * @see OdptStation.sameAs
     */
    @field:Json(name = "odpt:fromStation")
    val fromStation: String?,
    /**
     * 女性専用車両終了駅のID
     * @see OdptStation.sameAs
     */
    @field:Json(name = "odpt:toStation")
    val toStation: String?,
    /**
     * 女性専用車両実施曜日
     */
    @field:Json(name = "odpt:operationDay")
    val operationDay: String?,
    /**
     * 女性専用車両開始時間（ISO8601 日付時刻形式）
     */
    @field:Json(name = "odpt:availableTimeFrom")
    val availableTimeFrom: String?,
    /**
     * 女性専用車両終了時間（ISO8601 日付時刻形式）
     */
    @field:Json(name = "odpt:availableTimeUntil")
    val availableTimeUntil: String?,
    /**
     * 車両編成数
     */
    @field:Json(name = "odpt:carComposition")
    val carComposition: Int?,
    /**
     * 女性専用車両実施車両号車番号
     */
    @field:Json(name = "odpt:carNumber")
    val carNumber: Int?
) {
    /**
     * 女性専用車両開始駅の駅ID
     */
    val fromStationId: StationId?
        get() = fromStation?.let { StationId(it) }
    /**
     * 女性専用車両終了駅の駅ID
     */
    val toStationId: StationId?
        get() = toStation?.let { StationId(it) }
}
