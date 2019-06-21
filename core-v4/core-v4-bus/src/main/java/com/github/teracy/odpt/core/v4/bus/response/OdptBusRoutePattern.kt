package com.github.teracy.odpt.core.v4.bus.response

import com.github.teracy.odpt.core.geojson.response.Geometry
import com.github.teracy.odpt.model.BusRouteId
import com.github.teracy.odpt.model.BusRoutePatternId
import com.github.teracy.odpt.model.OperatorId
import com.squareup.moshi.Json

/**
 * v4版バス系統情報APIレスポンス
 */
data class OdptBusRoutePattern(
    /**
     * 固有識別子（ucode）
     */
    @field:Json(name = "@id")
    val id: String,
    /**
     * 固有識別子（バス系統ID）
     */
    @field:Json(name = "owl:sameAs")
    val sameAs: String,
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
     * バス路線名称（系統名・系統番号等）
     */
    @field:Json(name = "dc:title")
    val title: String,
    /**
     * バス路線名称のよみがな
     */
    @field:Json(name = "odpt:kana")
    val kana: String?,
    /**
     * 運行会社のIDのリスト
     */
    @field:Json(name = "odpt:operator")
    val operatorList: List<String>,
    /**
     * バス路線を表すID
     */
    @field:Json(name = "odpt:busroute")
    val busRoute: String?,
    /**
     * 系統パターン
     */
    @field:Json(name = "odpt:pattern")
    val pattern: String,
    /**
     * 方向を表す値
     */
    @field:Json(name = "odpt:direction")
    val direction: String?,
    /**
     * GeoJSON形式による地物情報
     */
    @field:Json(name = "ug:region")
    val region: Geometry?,
    /**
     * バス停標柱の順序情報のリスト
     */
    @field:Json(name = "odpt:busstopPoleOrder")
    val busStopPoleOrder: List<OdptBusStopPoleOrder>?,
    /**
     * 注記
     */
    @field:Json(name = "odpt:note")
    val note: String?,
    /**
     * バス位置情報を示すWebSiteのURL
     */
    @field:Json(name = "odpt:busLocationURL")
    val busLocationUrl: String?
) {
    /**
     * 固有識別子を表すバス系統ID
     */
    val busRoutePatternId: BusRoutePatternId
        get() = BusRoutePatternId(sameAs)
    /**
     * 運行会社の事業者IDのリスト
     */
    val operatorIdList: List<OperatorId>
        get() = operatorList.map { OperatorId(it) }
    /**
     * バス路線ID
     */
    val busRouteId: BusRouteId?
        get() = busRoute?.let { BusRouteId(it) }
}
