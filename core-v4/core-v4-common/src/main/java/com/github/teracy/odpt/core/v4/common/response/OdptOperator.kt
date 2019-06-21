package com.github.teracy.odpt.core.v4.common.response

import com.github.teracy.odpt.model.OperatorId
import com.squareup.moshi.Json

/**
 * v4版事業者情報APIレスポンス
 */
data class OdptOperator(
    /**
     * 固有識別子(ucode)
     */
    @field:Json(name = "@id")
    val id: String,
    /**
     * データ生成日時（ISO8601 日付時刻形式）
     */
    @field:Json(name = "dc:date")
    val date: String?,
    /**
     * 固有識別子。命名ルール：odpt.Operator:名称
     */
    @field:Json(name = "owl:sameAs")
    val sameAs: String,
    /**
     * 事業者名称（日本語）
     */
    @field:Json(name = "dc:title")
    val title: String?,
    /**
     * 事業者名称（多言語対応）
     */
    @field:Json(name = "odpt:operatorTitle")
    val operatorTitle: Map<String, String>?
) {
    /**
     * 固有識別子を表す事業者ID
     */
    val operatorId: OperatorId
        get() = OperatorId(sameAs)
}
