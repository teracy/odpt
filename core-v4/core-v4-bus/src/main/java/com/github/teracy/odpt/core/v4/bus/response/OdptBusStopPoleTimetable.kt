package com.github.teracy.odpt.core.v4.bus.response

import com.github.teracy.odpt.model.*
import com.squareup.moshi.Json

/**
 * v4版バス停標柱時刻表APIレスポンス
 */
data class OdptBusStopPoleTimetable(
    /**
     * 固有識別子（ucode）
     */
    @field:Json(name = "@id")
    val id: String,
    /**
     * 固有識別子（バス停標柱時刻表ID）
     */
    @field:Json(name = "owl:sameAs")
    val sameAs: String,
    /**
     * データ生成日時（ISO8601 日付時刻形式）
     */
    @field:Json(name = "dc:date")
    val date: String,
    /**
     * ダイヤ改正日（ISO8601 日付形式）
     */
    @field:Json(name = "dct:issued")
    val issued: String?,
    /**
     * データ保証期限（ISO8601 日付時刻形式）
     */
    @field:Json(name = "dct:valid")
    val valid: String?,
    /**
     * バス路線名称（系統名等）
     */
    @field:Json(name = "dc:title")
    val title: String?,
    /**
     * バス停標柱のID
     * @see OdptBusStopPole.sameAs
     */
    @field:Json(name = "odpt:busstopPole")
    val busStopPole: String,
    /**
     * 方面を表すIDのリスト
     * NOTE:API定義ではStringだが、「arrayとなる場合もある」とあるためListで定義してある
     */
    @field:Json(name = "odpt:busDirection")
    val busDirectionList: List<String>,
    /**
     * 路線を表すIDのリスト
     * NOTE:API定義ではStringだが、「arrayとなる場合もある」とあるためListで定義してある
     * @see OdptBusRoutePattern.sameAs
     */
    @field:Json(name = "odpt:busroute")
    val busRouteList: List<String>,
    /**
     * 運行会社のIDのリスト
     * NOTE:API定義ではStringだが、実データではarrayで返す事業者もあるためListで定義してある
     */
    @field:Json(name = "odpt:operator")
    val operatorList: List<String>,
    /**
     * 運行する日付・曜日情報のID
     */
    @field:Json(name = "odpt:calendar")
    val calendar: String,
    /**
     * 時分情報のリスト
     */
    @field:Json(name = "odpt:busstopTimetableObject")
    val busStopTimetableObjectList: List<OdptBusStopPoleTimetableObject>
) {
    /**
     * 固有識別子を表すバス停標柱時刻表情報ID
     */
    val busStopPoleTimetableId: BusStopPoleTimetableId
        get() = BusStopPoleTimetableId(sameAs)
    /**
     * バス停標柱ID
     */
    val busStopPoleId: BusStopPoleId
        get() = BusStopPoleId(busStopPole)
    /**
     * 運行会社の事業者IDのリスト
     */
    val operatorListId: List<OperatorId>
        get() = operatorList.map { OperatorId(it) }
    /**
     * 方面IDのリスト
     */
    val busDirectionIdList: List<BusDirectionId>
        get() = busDirectionList.map { BusDirectionId(it) }
    /**
     * 路線IDのリスト
     */
    val busRouteIdList: List<BusRouteId>
        get() = busRouteList.map { BusRouteId(it) }
    /**
     * 運行する日付・曜日を表す日付・曜日情報ID
     */
    val calendarId: CalendarId
        get() = CalendarId(calendar)
}
