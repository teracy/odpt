package com.github.teracy.odpt.core.v4.train.request

import com.github.teracy.odpt.model.OperatorId
import com.github.teracy.odpt.model.PassengerSurveyId
import com.github.teracy.odpt.model.RailwayId
import com.github.teracy.odpt.model.StationId

/**
 * 駅乗降人員数情報検索引数
 */
sealed class PassengerSurveyArgument(
    /**
     * 固有識別子(ucode)のリスト
     */
    val idList: List<String>? = null,
    /**
     * 固有識別子別名（駅乗降人員数情報ID）のリスト
     */
    val sameAsList: List<String>? = null,
    /**
     * 運行会社の事業者IDのリスト
     */
    val operatorList: List<String>? = null,
    /**
     * 駅ID
     */
    val station: String? = null,
    /**
     * 路線ID
     */
    val railway: String? = null
) {
    /**
     * 固有識別子による検索
     *
     * @param idList 固有識別子のリスト
     */
    class ById(idList: List<String>) :
        PassengerSurveyArgument(idList = idList)

    /**
     * 駅乗降人員数情報IDによる検索
     *
     * @param passengerSurveyIdList 駅乗降人員数情報IDのリスト
     */
    class ByPassengerSurveyId(passengerSurveyIdList: List<PassengerSurveyId>) :
        PassengerSurveyArgument(sameAsList = passengerSurveyIdList.map { it.id })

    /**
     * 事業者IDによる検索
     *
     * @param operatorIdList 運行会社の事業者IDのリスト
     */
    class ByOperatorId(operatorIdList: List<OperatorId>) :
        PassengerSurveyArgument(operatorList = operatorIdList.map { it.id })

    /**
     * 駅IDによる検索
     *
     * @param stationId 駅ID
     */
    class ByStationId(stationId: StationId) :
        PassengerSurveyArgument(station = stationId.id)

    /**
     * 路線IDによる検索
     *
     * @param railwayId 路線ID
     */
    class ByRailwayId(railwayId: RailwayId) :
        PassengerSurveyArgument(railway = railwayId.id)
}
