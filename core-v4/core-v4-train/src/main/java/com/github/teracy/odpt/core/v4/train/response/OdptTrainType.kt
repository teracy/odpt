package com.github.teracy.odpt.core.v4.train.response

import com.github.teracy.odpt.model.OperatorId
import com.github.teracy.odpt.model.TrainTypeId
import com.squareup.moshi.Json

/**
 * v4版列車種別情報APIレスポンス
 */
data class OdptTrainType(
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
     * 固有識別子（列車種別ID）
     */
    @field:Json(name = "owl:sameAs")
    val sameAs: String,
    /**
     * 運行会社のID
     */
    @field:Json(name = "odpt:operator")
    val operator: String,
    /**
     * 列車種別（日本語）
     */
    @field:Json(name = "dc:title")
    val title: String?,
    /**
     * 列車種別（多言語対応）
     */
    @field:Json(name = "odpt:trainTypeTitle")
    val titleMap: Map<String, String>?
) {
    /**
     * 固有識別子を表す列車種別ID
     */
    val trainTypeId: TrainTypeId
        get() = TrainTypeId(sameAs)
    /**
     * 運行会社の事業者ID
     */
    val operatorId: OperatorId
        get() = OperatorId(operator)
}
