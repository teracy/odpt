package com.github.teracy.odpt.core.v4.airplane.request

import com.github.teracy.odpt.model.AirportId

/**
 * 空港情報検索引数
 */
sealed class AirportArgument(
    /**
     * 固有識別子(ucode)のリスト
     */
    val idList: List<String>? = null,
    /**
     * 固有識別子別名（空港ID）のリスト
     */
    val sameAsList: List<String>? = null
) {
    /**
     * 固有識別子による検索
     * @param idList 固有識別子のリスト
     */
    class ById(idList: List<String>) :
        AirportArgument(idList = idList)

    /**
     * 空港IDによる検索
     * @param airportIdList 空港IDのリスト
     */
    class ByAirportId(airportIdList: List<AirportId>) :
        AirportArgument(sameAsList = airportIdList.map { it.id })
}
