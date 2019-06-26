package com.github.teracy.odpt.core.v2.train.response

import com.squareup.moshi.Json

/**
 * v2版国土交通省国土数値情報の鉄道駅情報APIレスポンス
 */
data class MlitStation(
    /**
     * 固有識別子（ucode）
     */
    @field:Json(name = "@id")
    val id: String,
    /**
     * 代表点の経度、10進表記
     */
    @field:Json(name = "geo:long")
    val longitude: Double?,
    /**
     * 代表点の緯度、10進表記
     */
    @field:Json(name = "geo:lat")
    val latitude: Double?,
    /**
     * 駅形状データデータをGeoJSONで取得するためのURL
     */
    @field:Json(name = "ug:region")
    val region: String?,
    /**
     * 駅名
     */
    @field:Json(name = "mlit:stationName")
    val stationName: String,
    /**
     * 運営会社名
     */
    @field:Json(name = "mlit:operatorName")
    val operatorName: String,
    /**
     * 路線名
     */
    @field:Json(name = "mlit:railwayName")
    val railwayName: String
)
