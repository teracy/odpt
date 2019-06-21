package com.github.teracy.odpt.core.v4.bus.response

import com.github.teracy.odpt.model.*
import com.squareup.moshi.Json

/**
 * v4版バス運行情報APIレスポンス
 */
data class OdptBus(
    /**
     * 固有識別子（ucode）
     */
    @field:Json(name = "@id")
    val id: String,
    /**
     * 固有識別子（バス運行情報ID）
     */
    @field:Json(name = "owl:sameAs")
    val sameAs: String,
    /**
     * バス車両番号
     */
    @field:Json(name = "odpt:busNumber")
    val busNumber: String,
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
     * 更新頻度（秒）、指定された秒数以降にリクエストを行うことで、最新値が取得される
     */
    @field:Json(name = "odpt:frequency")
    val frequency: Int,
    /**
     * 運行中の系統のID
     * @see OdptBusRoutePattern.sameAs
     */
    @field:Json(name = "odpt:busroutePattern")
    val busRoutePattern: String,
    /**
     * 運行会社のID
     */
    @field:Json(name = "odpt:operator")
    val operator: String,
    /**
     * 運行中系統の始発バス停標柱のID
     * @see OdptBusStopPole.sameAs
     */
    @field:Json(name = "odpt:startingBusstopPole")
    val startingBusStopPole: String?,
    /**
     * 運行中系統の終着バス停標柱のID
     * @see OdptBusStopPole.sameAs
     */
    @field:Json(name = "odpt:terminalBusstopPole")
    val terminalBusStopPole: String?,
    /**
     * 直近に通過したバス停標柱のID。nullの場合、[toBusStopPole]で示すバス停へ接近中であることを示す。この場合、[fromBusStopPoleTime]もnullとなる。
     * @see OdptBusStopPole.sameAs
     */
    @field:Json(name = "odpt:fromBusstopPole")
    val fromBusStopPole: String?,
    /**
     * 直近に通過したバス停を発車した時刻
     */
    @field:Json(name = "odpt:fromBusstopPoleTime")
    val fromBusStopPoleTime: String?,
    /**
     * 次のバス停標柱のID。バス停に停車中の場合はnull
     * @see OdptBusStopPole.sameAs
     */
    @field:Json(name = "odpt:toBusstopPole")
    val toBusStopPole: String?,
    /**
     * Fromを0、toを1とした際の現在位置（割合）
     */
    @field:Json(name = "odpt:progress")
    val progress: Double?,
    /**
     * 対象となるバスの緯度 (10進表記、測地系はWGS84)
     */
    @field:Json(name = "geo:lat")
    val latitude: Double?,
    /**
     * 対象となるバスの経度 (10進表記、測地系はWGS84)
     */
    @field:Json(name = "geo:long")
    val longitude: Double?,
    /**
     * 対象となるバスの速度(km/h)
     */
    @field:Json(name = "odpt:speed")
    val speed: Double?,
    /**
     * 対象となるバスの進行方向方位角を示す。単位は度（°）。北が0度で、時計回り(東回り)に増加する
     */
    @field:Json(name = "odpt:azimuth")
    val azimuth: Double?,

    @field:Json(name = "odpt:doorStatus")
    private val _doorStatus: String?
) {
    /**
     * 固有識別子を表すバス運行情報ID
     */
    val busId: BusId
        get() = BusId(sameAs)
    /**
     * 運行中の系統ID
     */
    val busRoutePatternId: BusRoutePatternId
        get() = BusRoutePatternId(busRoutePattern)
    /**
     * 運行会社の事業者ID
     */
    val operatorId: OperatorId
        get() = OperatorId(operator)
    /**
     * 運行中系統の始発バス停のバス停標柱ID
     */
    val startingBusStopPoleId: BusStopPoleId?
        get() = startingBusStopPole?.let { BusStopPoleId(it) }
    /**
     * 運行中系統の終着バス停のバス停標柱ID
     */
    val terminalBusStopPoleId: BusStopPoleId?
        get() = terminalBusStopPole?.let { BusStopPoleId(it) }
    /**
     * 直近に通過したバス停のID。[fromBusStopPole]= nullの場合、[toBusStopPole]で示すバス停へ接近中であることを示す。この場合、[fromBusStopPoleTime]もnullとなる。
     */
    val fromBusStopPoleId: BusStopPoleId?
        get() = fromBusStopPole?.let { BusStopPoleId(it) }
    /**
     * 次のバス停のID。バス停に停車中の場合には、[toBusStopPole]は存在しない
     */
    val toBusStopPoleId: BusStopPoleId?
        get() = toBusStopPole?.let { BusStopPoleId(it) }
    /**
     * 対象となるバスの扉の開閉状態
     */
    val doorStatus: DoorStatus?
        get() = DoorStatus.fromStatusString(_doorStatus)
}
