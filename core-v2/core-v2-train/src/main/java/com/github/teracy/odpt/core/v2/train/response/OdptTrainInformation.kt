package com.github.teracy.odpt.core.v2.train.response

import com.github.teracy.odpt.model.OperatorId
import com.github.teracy.odpt.model.RailwayId
import com.squareup.moshi.Json

/**
 * v2版列車運行情報APIレスポンス
 */
data class OdptTrainInformation(
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
     * 有効期限（ISO8601 日付時刻形式）
     */
    @field:Json(name = "dct:valid")
    val valid: String,
    /**
     * 運行会社の事業者ID
     */
    @field:Json(name = "odpt:operator")
    val operator: String,
    /**
     * 発生時刻（ISO8601 日付時刻形式）平常運転時には情報取得日時が入る場合があり、odpt:trainInformationText が変更されない場合でもodpt:timeOfOrigin が更新される場合がある
     */
    @field:Json(name = "odpt:timeOfOrigin")
    val timeOfOrigin: String,
    /**
     * 発生路線のID
     * @see OdptRailway.sameAs
     */
    @field:Json(name = "odpt:railway")
    val railway: String,
    /**
     * 平常時は省略。運行情報が存在する場合は「運行情報あり」を格納。遅延などの情報を取得可能な場合は、「遅延」等のテキストを格納
     */
    @field:Json(name = "odpt:trainInformationStatus")
    val trainInformationStatus: String?,
    /**
     * 運行情報テキスト
     */
    @field:Json(name = "odpt:trainInformationText")
    val trainInformationText: String
) {
    /**
     * 運行会社の事業者ID
     */
    val operatorId: OperatorId
        get() = OperatorId(operator)
    /**
     * 発生路線の路線ID
     */
    val railwayId: RailwayId
        get() = RailwayId(railway)
}
