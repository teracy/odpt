package com.github.teracy.odpt.core.v4.airplane.response

import com.github.teracy.odpt.model.AirportTerminalId
import com.github.teracy.odpt.model.OperatorId
import com.squareup.moshi.Json

/**
 * v4版航空機予定時刻表オブジェクト
 */
data class OdptFlightScheduleObject(
    /**
     * エアラインの運行会社のID
     */
    @field:Json(name = "odpt:airline")
    val airline: String,
    /**
     * フライト番号のリスト。共同運航便では複数となる
     */
    @field:Json(name = "odpt:flightNumber")
    val flightNumberList: List<String>,
    /**
     * 出発地の空港からの出発予定時刻（ISO8601 時刻形式、現地時間）
     */
    @field:Json(name = "odpt:originTime")
    val originTime: String,
    /**
     * 出発日とカレンダー情報(odpt:FlightScheduleのodpt:calendar)の日数差。例: 0は当日発、1は翌日発、-1は前日発
     */
    @field:Json(name = "odpt:originDayDifference")
    val originDayDifference: Int?,
    /**
     * 目的地の空港への到着予定時刻（ISO8601 時刻形式、現地時間）
     */
    @field:Json(name = "odpt:destinationTime")
    val destinationTime: String,
    /**
     * 到着日とカレンダー情報(odpt:FlightScheduleのodpt:calendar)の日数差。例: 0は当日発、1は翌日発、-1は前日発
     */
    @field:Json(name = "odpt:destinationDayDifference")
    val destinationDayDifference: Int?,
    /**
     * 経由地の空港のIDのリスト
     */
    @field:Json(name = "odpt:viaAirport")
    val viaAirportList: List<String>?,
    /**
     * 航空機の機種
     */
    @field:Json(name = "odpt:aircraftType")
    val aircraftType: String?,
    /**
     * データ適用開始日時（ISO8601 日付時刻形式）
     */
    @field:Json(name = "odpt:isValidFrom")
    val validFrom: String?,
    /**
     * データ適用開始日時（ISO8601 日付時刻形式）
     */
    @field:Json(name = "odpt:isValidTo")
    val validTo: String?,
    /**
     * その他プロパティとして定義されていない注釈情報の自然言語による記載（多言語対応）
     */
    @field:Json(name = "odpt:note")
    val note: Map<String, String>?
) {
    /**
     * エアラインの運行会社の事業者ID
     */
    val airlineOperatorId: OperatorId
        get() = OperatorId(airline)
    /**
     * 経由地の空港IDのリスト
     */
    val viaAirportIdList: List<AirportTerminalId>?
        get() = viaAirportList?.map { AirportTerminalId(it) }
}
