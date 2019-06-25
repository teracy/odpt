package com.github.teracy.odpt.core.v4.airplane.response

import com.github.teracy.odpt.model.FlightStatusId
import com.squareup.moshi.Json

/**
 * v4版フライト状況情報APIレスポンス
 */
data class OdptFlightStatus(
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
     * 固有識別子（フライト状況ID）
     */
    @field:Json(name = "owl:sameAs")
    val sameAs: String,
    /**
     * フライト状況（日本語）
     */
    @field:Json(name = "dc:title")
    val title: String?,
    /**
     * フライト状況（多言語対応）
     */
    @field:Json(name = "odpt:flightStatusTitle")
    val titleMap: Map<String, String>?
) {
    /**
     * 固有識別子を表すフライト状況ID
     */
    val flightStatusId: FlightStatusId
        get() = FlightStatusId(sameAs)
}
