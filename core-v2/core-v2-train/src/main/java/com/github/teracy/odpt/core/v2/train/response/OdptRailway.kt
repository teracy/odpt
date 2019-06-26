package com.github.teracy.odpt.core.v2.train.response

import com.github.teracy.odpt.model.OperatorId
import com.github.teracy.odpt.model.RailwayId
import com.squareup.moshi.Json

/**
 * v2版鉄道路線情報APIレスポンス
 */
data class OdptRailway(
    /**
     * 固有識別子（ucode）
     */
    @field:Json(name = "@id")
    val id: String,
    /**
     * 固有識別子（鉄道路線ID）
     */
    @field:Json(name = "owl:sameAs")
    val sameAs: String,
    /**
     * データ生成日時（ISO8601 日付時刻形式）
     */
    @field:Json(name = "dc:date")
    val date: String,
    /**
     * 運行系統名
     */
    @field:Json(name = "dc:title")
    val title: String,
    /**
     * 地物情報へのURL
     */
    @field:Json(name = "ug:region")
    val region: String,
    /**
     * 運行会社のID
     */
    @field:Json(name = "odpt:operator")
    val operator: String,
    /**
     * 駅の順序リスト
     */
    @field:Json(name = "odpt:stationOrder")
    val stationOrder: List<OdptStationOrder>,
    /**
     * 駅間の標準所要時間リスト
     */
    @field:Json(name = "odpt:travelTime")
    val travelTime: List<OdptTravelTime>,
    /**
     * 路線コード
     */
    @field:Json(name = "odpt:lineCode")
    val lineCode: String?,
    /**
     * 女性専用車両情報リスト
     */
    @field:Json(name = "odpt:womenOnlyCar")
    val womenOnlyCar: List<OdptWomenOnlyCar>?
) {
    /**
     * 固有識別子を表す鉄道路線ID
     */
    val railwayId: RailwayId
        get() = RailwayId(sameAs)
    /**
     * 運行会社の事業者ID
     */
    val operatorId: OperatorId
        get() = OperatorId(operator)
}
