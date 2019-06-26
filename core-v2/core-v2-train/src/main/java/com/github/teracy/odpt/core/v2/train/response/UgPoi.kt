package com.github.teracy.odpt.core.v2.train.response

import com.squareup.moshi.Json

/**
 * v2版駅出入口情報APIレスポンス
 */
data class UgPoi(
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
     * 地物の形状データをGeoJSONで取得するためのURL
     */
    @field:Json(name = "ug:region")
    val region: String?,
    /**
     * 地物の階数（高さ情報）
     */
    @field:Json(name = "ug:floor")
    val floor: Double,
    /**
     * 地物名。エレベータには「エレベータ」という文字列を含む。「出入口」の文字列の後に出口番号が続く
     */
    @field:Json(name = "dc:title")
    val title: String,
    /**
     * 地物のカテゴリ（必ず「出入口」となる）
     */
    @field:Json(name = "ugsrv:categoryName")
    val categoryName: String
)
