package com.github.teracy.odpt.core.v4.train.response

import com.github.teracy.odpt.model.*
import com.squareup.moshi.Json

/**
 * v4版駅時刻表情報APIレスポンス
 */
data class OdptStationTimetable(
    /**
     * 固有識別子（ucode）
     */
    @field:Json(name = "@id")
    val id: String,
    /**
     * データ生成日時（ISO8601 日付時刻形式）
     */
    @field:Json(name = "dc:date")
    val date: String,
    /**
     * ダイヤ改正日（ISO8601 日付時刻形式）
     */
    @field:Json(name = "dct:issued")
    val issued: String?,
    /**
     * データの保証期限（ISO8601 日付時刻形式）
     */
    @field:Json(name = "dct:valid")
    val valid: String?,
    /**
     * 固有識別子（駅時刻表情報ID）
     */
    @field:Json(name = "owl:sameAs")
    val sameAs: String,
    /**
     * 運行会社のID
     */
    @field:Json(name = "odpt:operator")
    val operator: String,
    /**
     * 路線のID
     * @see OdptRailway.sameAs
     */
    @field:Json(name = "odpt:railway")
    val railway: String,
    /**
     * 路線名（多言語対応）
     * @see OdptRailway.titleMap
     */
    @field:Json(name = "odpt:railwayTitle")
    val railwayTitleMap: Map<String, String>?,
    /**
     * 駅のID
     * @see OdptStation.sameAs
     */
    @field:Json(name = "odpt:station")
    val station: String?,
    /**
     * 駅名（多言語対応）
     * @see OdptStation.titleMap
     */
    @field:Json(name = "odpt:stationTitle")
    val stationTitleMap: Map<String, String>?,
    /**
     * 方面のID
     * @see OdptRailDirection.sameAs
     */
    @field:Json(name = "odpt:railDirection")
    val railDirection: String?,
    /**
     * 運行を行う曜日・日付情報のID
     */
    @field:Json(name = "odpt:calendar")
    val calendar: String?,
    /**
     * 詳細情報リスト
     */
    @field:Json(name = "odpt:stationTimetableObject")
    val stationTimetableObjectList: List<OdptStationTimetableObject>,
    /**
     * その他プロパティとして定義されていない注釈情報の自然言語による記載（多言語対応）
     */
    @field:Json(name = "odpt:note")
    val note: Map<String, String>?
) {
    /**
     * 固有識別子を表す駅時刻表情報ID
     */
    val stationTimetableId: StationTimetableId
        get() = StationTimetableId(sameAs)
    /**
     * 運行会社の事業者ID
     */
    val operatorId: OperatorId
        get() = OperatorId(operator)
    /**
     * 路線ID
     */
    val railwayId: RailwayId
        get() = RailwayId(railway)
    /**
     * 駅ID
     */
    val stationId: StationId?
        get() = station?.let { StationId(it) }
    /**
     * 鉄道方面ID
     */
    val railDirectionId: RailDirectionId?
        get() = railDirection?.let { RailDirectionId(it) }
    /**
     * 運行を行う曜日・日付の曜日・日付情報ID
     */
    val calendarId: CalendarId?
        get() = calendar?.let { CalendarId(it) }
}
