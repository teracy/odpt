package com.github.teracy.odpt.core.v4.airplane.response

import com.github.teracy.odpt.core.geojson.response.Geometry
import com.github.teracy.odpt.model.*
import com.squareup.moshi.Json

/**
 * v4版空港情報APIレスポンス
 */
data class OdptAirport(
    /**
     * 固有識別子（ucode）
     */
    @field:Json(name = "@id")
    val id: String,
    /**
     * データ生成日時（ISO8601 日付時刻形式）
     */
    @field:Json(name = "dc:date")
    val date: String?,
    /**
     * 固有識別（空港ID）
     */
    @field:Json(name = "owl:sameAs")
    val sameAs: String,
    /**
     * 空港名（日本語）
     */
    @field:Json(name = "dc:title")
    val title: String?,
    /**
     * 空港名（多言語対応）
     */
    @field:Json(name = "odpt:airportTitle")
    val titleMap: Map<String, String>?,
    /**
     * 空港のターミナルのリスト
     */
    @field:Json(name = "odpt:airportTerminal")
    val airportTerminalList: List<String>?,
    /**
     * 代表点の経度 (10進表記、測地系はWGS84)
     */
    @field:Json(name = "geo:long")
    val longitude: Double?,
    /**
     * 代表点の緯度 (10進表記、測地系はWGS84)
     */
    @field:Json(name = "geo:lat")
    val latitude: Double?,
    /**
     * GeoJSON形式による地物情報
     */
    @field:Json(name = "ug:region")
    val region: Geometry?
) {
    /**
     * 固有識別子を表す空港ID
     */
    val airportId: AirportId
        get() = AirportId(sameAs)
    /**
     * 空港ターミナルIDのリスト
     */
    val airportTerminalIdList: List<AirportTerminalId>?
        get() = airportTerminalList?.map { AirportTerminalId(it) }
}
