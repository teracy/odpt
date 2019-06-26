package com.github.teracy.odpt.core.v2.train.response

import com.github.teracy.odpt.model.*
import com.squareup.moshi.Json

/**
 * v2版列車ロケーション情報APIレスポンス
 */
data class OdptTrain(
    /**
     * 固有識別子（ucode）
     */
    @field:Json(name = "@id")
    val id: String,
    /**
     * 固有識別子（列車ID）
     */
    @field:Json(name = "owl:sameAs")
    val sameAs: String,
    /**
     * 列車番号
     */
    @field:Json(name = "odpt:trainNumber")
    val trainNumber: String,
    /**
     * 列車種別のID
     */
    @field:Json(name = "odpt:trainType")
    val trainType: String,
    /**
     * データ生成日時（ISO8601 日付時刻形式）
     */
    @field:Json(name = "dc:date")
    val date: String,
    /**
     * データ保証期限（ISO8601 日付時刻形式）
     */
    @field:Json(name = "dct:valid")
    val valid: String,
    /**
     * 更新頻度（秒）
     */
    @field:Json(name = "odpt:frequency")
    val frequency: Int,
    /**
     * 鉄道路線のID
     * @see OdptRailway.sameAs
     */
    @field:Json(name = "odpt:railway")
    val railway: String,
    /**
     * 車両の所属会社のID
     */
    @field:Json(name = "odpt:trainOwner")
    val trainOwner: String,
    /**
     * 方面を表すID
     */
    @field:Json(name = "odpt:railDirection")
    val railDirection: String,
    /**
     * 遅延時間（秒）5分未満は切り捨て
     */
    @field:Json(name = "odpt:delay")
    val delay: Int,
    /**
     * 列車が出発した駅のID
     * @see OdptStation.sameAs
     */
    @field:Json(name = "odpt:startingStation")
    val startingStation: String,
    /**
     * 	列車が向かっている駅のID
     * @see OdptStation.sameAs
     */
    @field:Json(name = "odpt:terminalStation")
    val terminalStation: String,
    /**
     * 列車が出発した駅のID
     * @see OdptStation.sameAs
     */
    @field:Json(name = "odpt:fromStation")
    val fromStation: String,
    /**
     * 	列車が向かっている駅のID
     * @see OdptStation.sameAs
     */
    @field:Json(name = "odpt:toStation")
    val toStation: String?
) {
    /**
     * 固有識別子を表す列車ID
     */
    val trainId: TrainId
        get() = TrainId(sameAs)
    /**
     * 列車種別ID
     */
    val trainTypeId: TrainTypeId
        get() = TrainTypeId(trainType)
    /**
     * 鉄道路線ID
     */
    val railwayId: RailwayId
        get() = RailwayId(railway)
    /**
     * 車両の所属会社ID
     */
    val trainOwnerId: TrainOwnerId
        get() = TrainOwnerId(trainOwner)
    /**
     * 鉄道方面ID
     */
    val railDirectionId: RailDirectionId
        get() = RailDirectionId(railDirection)
    /**
     * 列車が出発した駅の駅ID
     */
    val startingStationId: StationId
        get() = StationId(startingStation)
    /**
     * 	列車が向かっている駅の駅ID
     */
    val terminalStationId: StationId
        get() = StationId(terminalStation)
    /**
     * 列車が出発した駅の駅ID
     */
    val fromStationId: StationId
        get() = StationId(fromStation)
    /**
     * 	列車が向かっている駅の駅ID
     */
    val toStationId: StationId?
        get() = toStation?.let { StationId(it) }
}
