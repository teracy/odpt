package com.github.teracy.odpt.core.v2.train.request

import com.github.teracy.odpt.model.OperatorId
import com.github.teracy.odpt.model.PassengerSurveyId

/**
 * 駅乗降人員数情報検索引数
 */
sealed class PassengerSurveyArgument(
    /**
     * 固有識別子(ucode)
     */
    val id: String? = null,
    /**
     * 固有識別子（駅乗降人員数情報ID）
     */
    val sameAs: String? = null,
    /**
     * 運行会社の事業者ID
     */
    val operator: String? = null,
    /**
     * 調査年度
     */
    val surveyYear: String? = null
) {
    /**
     * 固有識別子による検索
     *
     * @param id 固有識別子（必須）
     * @param surveyYear 調査年度（任意）
     */
    class ById(id: String, surveyYear: Int? = null) :
        PassengerSurveyArgument(id = id, surveyYear = surveyYear?.toString())

    /**
     * 駅乗降人員数情報IDによる検索
     *
     * @param passengerSurveyId 駅乗降人員数情報ID（必須）
     * @param surveyYear 調査年度（任意）
     */
    class ByPassengerSurveyId(passengerSurveyId: PassengerSurveyId, surveyYear: Int? = null) :
        PassengerSurveyArgument(sameAs = passengerSurveyId.id, surveyYear = surveyYear?.toString())

    /**
     * 事業者IDによる検索
     *
     * @param operatorId 運行会社の事業者ID
     * @param surveyYear 調査年度（任意）
     */
    class ByOperatorId(operatorId: OperatorId, surveyYear: Int? = null) :
        PassengerSurveyArgument(operator = operatorId.id, surveyYear = surveyYear?.toString())

    /**
     * 調査年度による検索
     *
     * @param surveyYear 調査年度
     */
    class BySurveyYear(surveyYear: Int) : PassengerSurveyArgument(surveyYear = surveyYear.toString())
}
