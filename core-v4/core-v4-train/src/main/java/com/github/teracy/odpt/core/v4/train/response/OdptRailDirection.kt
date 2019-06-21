package com.github.teracy.odpt.core.v4.train.response

import com.github.teracy.odpt.model.RailDirectionId
import com.squareup.moshi.Json

/**
 * v4版列車進行方向情報APIレスポンス
 */
data class OdptRailDirection(
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
     * 固有識別子（鉄道方面ID）
     */
    @field:Json(name = "owl:sameAs")
    val sameAs: String,
    /**
     * 進行方向（日本語）
     */
    @field:Json(name = "dc:title")
    val title: String?,
    /**
     * 進行方向（多言語対応）
     */
    @field:Json(name = "odpt:railDirectionTitle")
    val titleMap: Map<String, String>?
) {
    /**
     * 固有識別子を表す鉄道方面ID
     */
    val railDirectionId: RailDirectionId
        get() = RailDirectionId(sameAs)
}
