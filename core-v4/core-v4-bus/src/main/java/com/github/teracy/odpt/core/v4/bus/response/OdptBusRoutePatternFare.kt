package com.github.teracy.odpt.core.v4.bus.response

import com.github.teracy.odpt.model.BusRoutePatternFareId
import com.github.teracy.odpt.model.BusRoutePatternId
import com.github.teracy.odpt.model.BusStopPoleId
import com.github.teracy.odpt.model.OperatorId
import com.squareup.moshi.Json

/**
 * v4版バス運賃情報APIレスポンス
 */
data class OdptBusRoutePatternFare(
    /**
     * 固有識別子（ucode）
     */
    @field:Json(name = "@id")
    val id: String,
    /**
     * 固有識別子（バス運賃情報ID）
     */
    @field:Json(name = "owl:sameAs")
    val sameAs: String,
    /**
     * データ生成日時（ISO8601 日付時刻形式）
     */
    @field:Json(name = "dc:date")
    val date: String,
    /**
     * 運賃改定日（ISO8601 日付時刻形式）
     */
    @field:Json(name = "dct:issued")
    val issued: String?,
    /**
     * データ保証期限（ISO8601 日付時刻形式）
     */
    @field:Json(name = "dct:valid")
    val valid: String?,
    /**
     * 運行会社のID
     */
    @field:Json(name = "odpt:operator")
    val operator: String,
    /**
     * 乗車系統パターン
     * @see OdptBusRoutePattern.sameAs
     */
    @field:Json(name = "odpt:fromBusroutePattern")
    val fromBusRoutePattern: String,
    /**
     * 乗車停留所の系統パターン内の停留所（標柱）通過順
     */
    @field:Json(name = "odpt:fromBusstopPoleOrder")
    val fromBusStopPoleOrder: Int,
    /**
     * 乗車バス停標柱を表すID
     * @see OdptBusStopPole.sameAs
     */
    @field:Json(name = "odpt:fromBusstopPole")
    val fromBusStopPole: String,
    /**
     * 降車系統パターン
     * @see OdptBusRoutePattern.sameAs
     */
    @field:Json(name = "odpt:toBusroutePattern")
    val toBusRoutePattern: String,
    /**
     * 降車停留所の系統パターン内の停留所（標柱）通過順
     */
    @field:Json(name = "odpt:toBusstopPoleOrder")
    val toBusStopPoleOrder: Int,
    /**
     * 降車バス停標柱を表すID
     * @see OdptBusStopPole.sameAs
     */
    @field:Json(name = "odpt:toBusstopPole")
    val toBusStopPole: String,
    /**
     * 切符利用時の運賃
     */
    @field:Json(name = "odpt:ticketFare")
    val ticketFare: Int,
    /**
     * ICカード利用時の運賃
     */
    @field:Json(name = "odpt:icCardFare")
    val icCardFare: Int?,
    /**
     * 切符利用時の子供運賃
     */
    @field:Json(name = "odpt:childTicketFare")
    val childTicketFare: Int?,
    /**
     * ICカード利用時の子供運賃
     */
    @field:Json(name = "odpt:childIcCardFare")
    val childIcCardFare: Int?
) {
    /**
     * 固有識別子を表すバス運賃情報ID
     */
    val busRoutePatternFareId: BusRoutePatternFareId
        get() = BusRoutePatternFareId(sameAs)
    /**
     * 運行会社の事業者ID
     */
    val operatorId: OperatorId
        get() = OperatorId(operator)
    /**
     * 乗車系統パターンのバス系統ID
     */
    val fromBusRoutePatternId: BusRoutePatternId?
        get() = BusRoutePatternId(fromBusRoutePattern)
    /**
     * 乗車バス停標柱ID
     */
    val fromBusStopPoleId: BusStopPoleId?
        get() = BusStopPoleId(fromBusStopPole)
    /**
     * 降車系統パターンのバス系統ID
     */
    val toBusRoutePatternId: BusRoutePatternId?
        get() = BusRoutePatternId(toBusRoutePattern)
    /**
     * 降車バス停標柱ID
     */
    val toBusStopPoleId: BusStopPoleId?
        get() = BusStopPoleId(toBusStopPole)
}
