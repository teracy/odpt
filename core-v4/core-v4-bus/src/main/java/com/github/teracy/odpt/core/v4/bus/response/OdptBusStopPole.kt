package com.github.teracy.odpt.core.v4.bus.response

import com.github.teracy.odpt.core.geojson.response.Geometry
import com.github.teracy.odpt.model.BusRoutePatternId
import com.github.teracy.odpt.model.BusStopPoleId
import com.github.teracy.odpt.model.OperatorId
import com.squareup.moshi.Json

/**
 * v4版バス停標柱情報APIレスポンス
 */
data class OdptBusStopPole(
    /**
     * 固有識別子（ucode）
     */
    @field:Json(name = "@id")
    val id: String,
    /**
     * 固有識別子（バス停標柱ID）
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
     * バス停名
     */
    @field:Json(name = "dc:title")
    val title: String,
    /**
     * バス停名のよみがな
     */
    @field:Json(name = "odpt:kana")
    val kana: String?,
    /**
     * 標柱の緯度 (10進表記、測地系はWGS84)
     */
    @field:Json(name = "geo:lat")
    val latitude: Double?,
    /**
     * 標柱の経度 (10進表記、測地系はWGS84)
     */
    @field:Json(name = "geo:long")
    val longitude: Double?,
    /**
     * GeoJSON形式による地物情報
     */
    @field:Json(name = "ug:region")
    val region: Geometry?,
    /**
     * バス系統のIDのリスト
     * @see OdptBusRoutePattern.sameAs
     */
    @field:Json(name = "odpt:busroutePattern")
    val busRoutePatternList: List<String>?,
    /**
     * 運行会社のIDのリスト
     */
    @field:Json(name = "odpt:operator")
    val operatorList: List<String>,
    /**
     * 標柱番号。同一停留所の別標柱を区別するものであり、のりば番号とは一致する保証はない
     */
    @field:Json(name = "odpt:busstopPoleNumber")
    val busStopPoleNumber: String?,
    /**
     * 時分情報のリスト
     */
    @field:Json(name = "odpt:busstopTimetableObject")
    val busStopTimetableObjectList: List<OdptBusStopPoleTimetableObject>
) {
    /**
     * 固有識別子を表すバス停標柱ID
     */
    val busStopPoleId: BusStopPoleId
        get() = BusStopPoleId(sameAs)
    /**
     * バス系統IDのリスト
     */
    val busRoutePatternIdList: List<BusRoutePatternId>?
        get() = busRoutePatternList?.map { BusRoutePatternId(it) }
    /**
     * 運行会社の事業者IDのリスト
     */
    val operatorIdList: List<OperatorId>
        get() = operatorList.map { OperatorId(it) }
}
