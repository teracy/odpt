package com.github.teracy.odpt.core.v4.train.response

import com.github.teracy.odpt.model.OperatorId
import com.github.teracy.odpt.model.PassengerSurveyId
import com.github.teracy.odpt.model.RailwayId
import com.github.teracy.odpt.model.StationId
import com.squareup.moshi.Json

/**
 * v4版駅乗降人員数情報APIレスポンス
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
     * データ生成日時（ISO8601 日付時刻形式）
     */
    @field:Json(name = "dc:date")
    val date: String,
    /**
     * 運行会社のID
     */
    @field:Json(name = "odpt:operator")
    val operator: String,
    /**
     * 駅のIDのリスト
     * @see OdptStation.sameAs
     */
    @field:Json(name = "odpt:station")
    val stationList: List<String>,
    /**
     * 鉄道路線のIDのリスト
     * @see OdptRailway.sameAs
     */
    @field:Json(name = "odpt:railway")
    val railwayList: List<String>,
    /**
     * 乗降人員(降車を含む)の場合はtrue、乗車人員(降車を含まない)の場合はfalse
     */
    @field:Json(name = "odpt:includeAlighting")
    val includeAlighting: Boolean,
    /**
     * 調査年度と平均乗降人員数(または乗車人員数)の組のリスト
     */
    @field:Json(name = "odpt:passengerSurveyObject")
    val passengerSurveyObjectList: List<OdptPassengerSurveyObject>
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
    /**
     * 駅IDのリスト
     */
    val stationIdList: List<StationId>
        get() = stationList.map { StationId(it) }
    /**
     * 鉄道路線IDのリスト
     */
    val railwayIdList: List<RailwayId>
        get() = railwayList.map { RailwayId(it) }
}
