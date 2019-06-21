package com.github.teracy.odpt.core.v4.bus.response

import com.github.teracy.odpt.model.BusRoutePatternId
import com.github.teracy.odpt.model.BusTimetableId
import com.github.teracy.odpt.model.CalendarId
import com.github.teracy.odpt.model.OperatorId
import com.squareup.moshi.Json

/**
 * v4版バス時刻表APIレスポンス
 */
data class OdptBusTimetable(
    /**
     * 固有識別子（ucode）
     */
    @field:Json(name = "@id")
    val id: String,
    /**
     * 固有識別子（バス時刻表情報ID）
     */
    @field:Json(name = "owl:sameAs")
    val sameAs: String,
    /**
     * データ生成日時（ISO8601 日付時刻形式）
     */
    @field:Json(name = "dc:date")
    val date: String?,
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
     * バス路線名称のよみがな
     */
    @field:Json(name = "odpt:kana")
    val kana: String?,
    /**
     * 運行会社のID
     */
    @field:Json(name = "odpt:operator")
    val operator: String,
    /**
     * 運行系統のID
     * @see OdptBusRoutePattern.sameAs
     */
    @field:Json(name = "odpt:busroutePattern")
    val busRoutePattern: String,
    /**
     * 運行する日付・曜日情報のID
     */
    @field:Json(name = "odpt:calendar")
    val calendar: String,
    /**
     * 時分情報のリスト
     */
    @field:Json(name = "odpt:busTimetableObject")
    val busTimetableObjectList: List<OdptBusTimetableObject>
) {
    /**
     * 固有識別子を表すバス時刻表情報ID
     */
    val busTimetableId: BusTimetableId
        get() = BusTimetableId(sameAs)
    /**
     * 運行会社の事業者ID
     */
    val operatorId: OperatorId
        get() = OperatorId(operator)
    /**
     * 運行系統ID
     */
    val busRoutePatternId: BusRoutePatternId
        get() = BusRoutePatternId(busRoutePattern)
    /**
     * 運行する日付・曜日を表す日付・曜日情報ID
     */
    val calendarId: CalendarId
        get() = CalendarId(calendar)
}
