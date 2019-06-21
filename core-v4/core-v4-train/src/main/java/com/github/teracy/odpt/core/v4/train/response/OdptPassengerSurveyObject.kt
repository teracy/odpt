package com.github.teracy.odpt.core.v4.train.response

import com.squareup.moshi.Json

/**
 * v4版駅乗降人員数詳細情報
 */
data class OdptPassengerSurveyObject(
    /**
     * 調査年度
     */
    @field:Json(name = "odpt:surveyYear")
    val surveyYear: Int,
    /**
     * 駅の1日あたりの平均乗降人員数（または乗車人員数）
     * @see OdptPassengerSurvey.includeAlighting
     */
    @field:Json(name = "odpt:passengerJourneys")
    val passengerJourneys: Int
)
