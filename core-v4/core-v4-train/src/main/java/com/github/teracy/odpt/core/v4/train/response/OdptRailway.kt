package com.github.teracy.odpt.core.v4.train.response

import com.github.teracy.odpt.core.geojson.response.Geometry
import com.github.teracy.odpt.model.OperatorId
import com.github.teracy.odpt.model.RailDirectionId
import com.github.teracy.odpt.model.RailwayId
import com.squareup.moshi.Json

/**
 * v4版鉄道路線情報APIレスポンス
 */
data class OdptRailway(
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
     * 固有識別子（鉄道路線ID）
     */
    @field:Json(name = "owl:sameAs")
    val sameAs: String,
    /**
     * 路線名（日本語）
     */
    @field:Json(name = "dc:title")
    val title: String,
    /**
     * 路線名（多言語対応）
     */
    @field:Json(name = "odpt:railwayTitle")
    val titleMap: Map<String, String>?,
    /**
     * 路線名のよみがな（ひらがな表記）
     */
    @field:Json(name = "odpt:kana")
    val kana: String?,
    /**
     * 運行会社のID
     */
    @field:Json(name = "odpt:operator")
    val operator: String,
    /**
     * 路線コード
     */
    @field:Json(name = "odpt:lineCode")
    val lineCode: String?,
    /**
     * 路線のラインカラーをHEX表記#RRGGBB、DIC表記DICnnn、PANTONE表記PANTONExxxxで表記する
     */
    @field:Json(name = "odpt:color")
    val lineColor: String?,
    /**
     * GeoJSON形式による地物情報
     */
    @field:Json(name = "ug:region")
    val region: Geometry?,
    /**
     * 昇順の進行方向を表すID。odpt:stationOrderのodpt:indexの昇順方向を、odpt:RailDirectionのowl:sameAsで表したIDで格納する
     * @see OdptRailDirection.sameAs
     */
    @field:Json(name = "odpt:ascendingRailDirection")
    val ascendingRailDirection: String?,
    /**
     * 降順の進行方向を表すD。odpt:stationOrderのodpt:indexの降順方向を、odpt:RailDirectionのowl:sameAsで表したIDで格納する
     * @see OdptRailDirection.sameAs
     */
    @field:Json(name = "odpt:descendingRailDirection")
    val descendingRailDirection: String?,
    /**
     * 駅の順序リスト
     */
    @field:Json(name = "odpt:stationOrder")
    val stationOrderList: List<OdptStationOrder>
) {
    /**
     * 固有識別子を表す駅ID
     */
    val railwayId: RailwayId
        get() = RailwayId(sameAs)
    /**
     * 運行会社の事業者ID
     */
    val operatorId: OperatorId
        get() = OperatorId(operator)
    /**
     * 昇順の進行方向を表す鉄道方面ID
     */
    val ascendingRailDirectionId: RailDirectionId?
        get() = ascendingRailDirection?.let { RailDirectionId(it) }
    /**
     * 降順の進行方向を表す鉄道方面ID
     */
    val descendingRailDirectionId: RailDirectionId?
        get() = descendingRailDirection?.let { RailDirectionId(it) }
}
