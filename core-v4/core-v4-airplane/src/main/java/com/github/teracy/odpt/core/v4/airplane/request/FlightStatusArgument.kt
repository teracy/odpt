package com.github.teracy.odpt.core.v4.airplane.request

import com.github.teracy.odpt.model.FlightStatusId

/**
 * フライト状況情報検索引数
 */
sealed class FlightStatusArgument(
    /**
     * 固有識別子(ucode)のリスト
     */
    val idList: List<String>? = null,
    /**
     * 固有識別子別名（フライト状況ID）のリスト
     */
    val sameAsList: List<String>? = null
) {
    /**
     * 固有識別子による検索
     * @param idList 固有識別子のリスト
     */
    class ById(idList: List<String>) :
        FlightStatusArgument(idList = idList)

    /**
     * フライト状況IDによる検索
     * @param flightStatusIdList フライト状況IDのリスト
     */
    class ByFlightStatusId(flightStatusIdList: List<FlightStatusId>) :
        FlightStatusArgument(sameAsList = flightStatusIdList.map { it.id })
}
