package com.github.teracy.odpt.core.v4.airplane.response

import com.github.teracy.odpt.model.*
import com.squareup.moshi.Json

/**
 * v4版フライト到着情報APIレスポンス
 */
data class OdptFlightInformationArrival(
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
     * データ保証期限（ISO8601 日付時刻形式）
     */
    @field:Json(name = "dct:valid")
    val valid: String?,
    /**
     * 固有識別（フライト到着情報ID）
     */
    @field:Json(name = "owl:sameAs")
    val sameAs: String,
    /**
     * フライト到着情報を提供する空港事業者または航空事業者を示すID
     */
    @field:Json(name = "odpt:operator")
    val operator: String,
    /**
     * エアラインの運行会社のID
     */
    @field:Json(name = "odpt:airline")
    val airline: String?,
    /**
     * フライト番号のリスト。共同運航便では複数となる
     */
    @field:Json(name = "odpt:flightNumber")
    val flightNumberList: List<String>,
    /**
     * フライト状況を表すID
     * @see OdptFlightStatus.sameAs
     */
    @field:Json(name = "odpt:flightStatus")
    val flightStatus: String?,
    /**
     * 運行障害発生時など、特記すべき情報がある場合に記載する、自然言語による情報の要約（多言語対応）
     */
    @field:Json(name = "odpt:flightInformationSummary")
    val summaryMap: Map<String, String>?,
    /**
     * 運行障害発生時など、特記すべき情報がある場合に記載する、自然言語による情報の記述（多言語対応）
     */
    @field:Json(name = "odpt:flightInformationText")
    val textMap: Map<String, String>?,
    /**
     * 定刻の到着時刻（ISO8601 時刻形式）
     */
    @field:Json(name = "odpt:scheduledArrivalTime")
    val scheduledArrivalTime: String?,
    /**
     * 変更後到着時刻（ISO8601 時刻形式）、到着以降は[actualArrivalTime]が生成され、本項目はnullとなる
     */
    @field:Json(name = "odpt:estimatedArrivalTime")
    val estimatedArrivalTime: String?,
    /**
     * 実際の到着時刻（ISO8601 時刻形式）、到着するまではnull
     */
    @field:Json(name = "odpt:actualArrivalTime")
    val actualArrivalTime: String?,
    /**
     * 到着空港のID
     * @see OdptAirport.sameAs
     */
    @field:Json(name = "odpt:arrivalAirport")
    val arrivalAirport: String,
    /**
     * 到着空港のターミナルのID
     * @see OdptAirportTerminal.sameAs
     */
    @field:Json(name = "odpt:arrivalAirportTerminal")
    val arrivalAirportTerminal: String?,
    /**
     * 到着空港のゲート番号
     */
    @field:Json(name = "odpt:arrivalGate")
    val arrivalGate: String?,
    /**
     * 到着空港の預け手荷物受取所
     */
    @field:Json(name = "odpt:baggageClaim")
    val baggageClaim: String?,
    /**
     * 出発地の空港のID
     * @see OdptAirport.sameAs
     */
    @field:Json(name = "odpt:originAirport")
    val originAirport: String?,
    /**
     * 経由地の空港のIDのリスト
     * @see OdptAirport.sameAs
     */
    @field:Json(name = "odpt:viaAirport")
    val viaAirportList: List<String>?,
    /**
     * 航空機の機種
     */
    @field:Json(name = "odpt:aircraftType")
    val aircraftType: String?
) {
    /**
     * 固有識別子を表すフライト到着情報ID
     */
    val flightInformationArrivalId: FlightInformationArrivalId
        get() = FlightInformationArrivalId(sameAs)
    /**
     * フライト到着情報を提供する空港事業者または航空事業者のID
     */
    val operatorId: OperatorId
        get() = OperatorId(operator)
    /**
     * エアラインの運行会社の事業者ID
     */
    val airlineOperatorId: OperatorId?
        get() = airline?.let { OperatorId(it) }
    /**
     * フライト状況ID
     */
    val flightStatusId: FlightStatusId?
        get() = flightStatus?.let { FlightStatusId(it) }
    /**
     * 到着地の空港ID
     */
    val arrivalAirportId: AirportId
        get() = AirportId(arrivalAirport)
    /**
     * 到着地の空港ターミナルID
     */
    val arrivalAirportTerminalId: AirportTerminalId?
        get() = arrivalAirportTerminal?.let { AirportTerminalId(it) }
    /**
     * 出発地の空港ID
     */
    val originAirportId: AirportId?
        get() = originAirport?.let { AirportId(it) }
    /**
     * 経由地の空港ID
     */
    val viaAirportIdList: List<AirportId>?
        get() = viaAirportList?.map { AirportId(it) }
}
