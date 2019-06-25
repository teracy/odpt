package com.github.teracy.odpt.core.v4.airplane.response

import com.github.teracy.odpt.model.AirportId
import com.github.teracy.odpt.model.CalendarId
import com.github.teracy.odpt.model.FlightScheduleId
import com.github.teracy.odpt.model.OperatorId
import com.squareup.moshi.Json

/**
 * v4版フライト時刻表情報APIレスポンス
 */
data class OdptFlightSchedule(
    /**
     * 固有識別子（ucode）
     */
    @field:Json(name = "@id")
    val id: String,
    /**
     * データ生成日時（ISO8601 日付時刻形式）
     */
    @field:Json(name = "dc:date")
    val date: String?,
    /**
     * 固有識別（フライト時刻表情報ID）
     */
    @field:Json(name = "owl:sameAs")
    val sameAs: String,
    /**
     * フライト時刻表を提供する空港事業者または航空事業者を示すID
     */
    @field:Json(name = "odpt:operator")
    val operator: String,
    /**
     * 運航する曜日・日付情報のID
     */
    @field:Json(name = "odpt:calendar")
    val calendar: String,
    /**
     * 出発地の空港のID
     */
    @field:Json(name = "odpt:originAirport")
    val originAirport: String,
    /**
     * 目的地の空港のID
     */
    @field:Json(name = "odpt:destinationAirport")
    val destinationAirport: String,
    /**
     * 航空機予定時刻表オブジェクトのリスト
     */
    @field:Json(name = "odpt:flightScheduleObject")
    val flightScheduleObjectList: List<OdptFlightScheduleObject>
) {
    /**
     * 固有識別子を表すフライト時刻表情報ID
     */
    val flightScheduleId: FlightScheduleId
        get() = FlightScheduleId(sameAs)
    /**
     * フライト時刻表を提供する空港事業者または航空事業者のID
     */
    val operatorId: OperatorId
        get() = OperatorId(operator)
    /**
     * 運航する曜日・日付の曜日・日付情報ID
     */
    val calendarId: CalendarId?
        get() = CalendarId(calendar)
    /**
     * 出発地の空港ID
     */
    val originAirportId: AirportId
        get() = AirportId(originAirport)
    /**
     * 目的地の空港ID
     */
    val destinationAirportId: AirportId
        get() = AirportId(destinationAirport)
}
