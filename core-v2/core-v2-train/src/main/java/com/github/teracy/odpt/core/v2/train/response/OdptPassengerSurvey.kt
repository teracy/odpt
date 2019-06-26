package com.github.teracy.odpt.core.v2.train.response

import com.github.teracy.odpt.model.OperatorId
import com.github.teracy.odpt.model.PassengerSurveyId
import com.squareup.moshi.Json

/**
 * v2版駅乗降人員数情報APIレスポンス
 */
data class OdptPassengerSurvey(
    /**
     * 固有識別子（ucode）
     */
    @field:Json(name = "@id")
    val id: String,
    /**
     * 固有識別子（駅乗降人員数情報ID）
     */
    @field:Json(name = "owl:sameAs")
    val sameAs: String,
    /**
     * 運行会社のID
     */
    @field:Json(name = "odpt:operator")
    val operator: String,
    /**
     * 調査年度
     */
    @field:Json(name = "odpt:surveyYear")
    val surveyYear: Int,
    /**
     * 駅の1日あたりの平均乗降人員数
     */
    @field:Json(name = "odpt:passengerJourneys")
    val passengerJourneys: Int
) {
    /**
     * 固有識別子を表す駅乗降人員数情報ID
     */
    val passengerSurveyId: PassengerSurveyId
        get() = PassengerSurveyId(sameAs)
    /**
     * 運行会社の事業者ID
     */
    val operatorId: OperatorId
        get() = OperatorId(operator)
}
