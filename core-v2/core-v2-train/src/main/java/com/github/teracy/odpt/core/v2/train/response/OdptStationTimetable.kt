package com.github.teracy.odpt.core.v2.train.response

import com.github.teracy.odpt.model.*
import com.squareup.moshi.Json

/**
 * v2版駅時刻表情報APIレスポンス
 */
data class OdptStationTimetable(
    /**
     * 固有識別子(ucode)
     */
    @field:Json(name = "@id")
    val id: String,
    /**
     * 固有識別子（駅時刻表情報ID）
     */
    @field:Json(name = "owl:sameAs")
    val sameAs: String,
    /**
     * データ生成日時（ISO8601 日付時刻形式）
     */
    @field:Json(name = "dc:date")
    val date: String,
    /**
     * 駅のID
     * @see OdptStation.sameAs
     */
    @field:Json(name = "odpt:station")
    val station: String,
    /**
     * 路線のID
     * @see OdptRailway.sameAs
     */
    @field:Json(name = "odpt:railway")
    val railway: String,
    /**
     * 運行会社のID
     */
    @field:Json(name = "odpt:operator")
    val operator: String,
    /**
     * 方面を表すID
     */
    @field:Json(name = "odpt:railDirection")
    val railDirection: String,
    /**
     * 詳細情報リスト（平日）
     */
    @field:Json(name = "odpt:weekdays")
    val weekdayStationTimetableObjectList: List<OdptStationTimetableObject>?,
    /**
     * 詳細情報リスト（平日）
     */
    @field:Json(name = "odpt:saturdays")
    val saturdayStationTimetableObjectList: List<OdptStationTimetableObject>?,
    /**
     * 詳細情報リスト（平日）
     */
    @field:Json(name = "odpt:holidays")
    val holidayStationTimetableObjectList: List<OdptStationTimetableObject>?
) {
    /**
     * 固有識別子を表す駅時刻表情報ID
     */
    val stationTimetableId: StationTimetableId
        get() = StationTimetableId(sameAs)
    /**
     * 駅ID
     */
    val stationId: StationId
        get() = StationId(station)
    /**
     * 鉄道路線ID
     */
    val railwayId: RailwayId
        get() = RailwayId(railway)
    /**
     * 運行会社の事業者ID
     */
    val operatorId: OperatorId
        get() = OperatorId(operator)
    /**
     * 鉄道方面ID
     */
    val railDirectionId: RailDirectionId
        get() = RailDirectionId(railDirection)
}
