package com.github.teracy.odpt.core.v2.train.response

import com.github.teracy.odpt.model.StationId
import com.github.teracy.odpt.model.TrainTypeId
import com.squareup.moshi.Json

/**
 * v2版駅時刻表情報APIレスポンス詳細
 */
data class OdptStationTimetableObject(
    /**
     * 出発時間（ISO8601 時刻形式）
     */
    @field:Json(name = "odpt:departureTime")
    val departureTime: String,
    /**
     * 行き先駅のID
     * @see OdptStation.sameAs
     */
    @field:Json(name = "odpt:destinationStation")
    val destinationStation: String,
    /**
     * 列車種別のID
     */
    @field:Json(name = "odpt:trainType")
    val trainType: String,
    /**
     * 最終電車の場合、true。最終電車でない場合は省略
     */
    @field:Json(name = "odpt:isLast")
    private val _isLast: Boolean?,
    /**
     * 始発駅の場合、true。始発駅ではない場合は省略
     */
    @field:Json(name = "odpt:isOrigin")
    private val _isOrigin: Boolean?,
    /**
     * 車両数（駅に停車する車両数が列車毎に異なる場合に格納する）
     */
    @field:Json(name = "odpt:carComposition")
    val carComposition: Int?,
    /**
     * その他の注釈（接続、通過待ちなど）
     */
    @field:Json(name = "odpt:note")
    val note: String?
) {
    /**
     * 行き先駅の駅ID
     */
    val destinationStationId: StationId
        get() = StationId(destinationStation)
    /**
     * 列車種別ID
     */
    val trainTypeId: TrainTypeId
        get() = TrainTypeId(trainType)
    /**
     * 最終電車の場合、true
     */
    val isLast: Boolean
        get() = _isLast ?: false
    /**
     * 始発駅の場合、true
     */
    val isOrigin: Boolean
        get() = _isOrigin ?: false
}
