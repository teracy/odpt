package com.github.teracy.odpt.core.v4.airplane.request

import com.github.teracy.odpt.model.AirportId
import com.github.teracy.odpt.model.AirportTerminalId

/**
 * 空港ターミナル情報検索引数
 */
sealed class AirportTerminalArgument(
    /**
     * 固有識別子(ucode)のリスト
     */
    val idList: List<String>? = null,
    /**
     * 固有識別子別名（空港ターミナルID）のリスト
     */
    val sameAsList: List<String>? = null,
    /**
     * 空港IDのリスト
     */
    val airportList: List<String>? = null
) {
    /**
     * 固有識別子による検索
     * @param idList 固有識別子のリスト
     */
    class ById(idList: List<String>) :
        AirportTerminalArgument(idList = idList)

    /**
     * 空港ターミナルIDによる検索
     * @param airportTerminalIdList 空港IDのリスト
     */
    class ByAirportTerminalId(airportTerminalIdList: List<AirportTerminalId>) :
        AirportTerminalArgument(sameAsList = airportTerminalIdList.map { it.id })

    /**
     * 空港IDによる検索
     *
     * @param airportIdList 空港IDのリスト
     */
    class ByAirportId(airportIdList: List<AirportId>) :
        AirportTerminalArgument(airportList = airportIdList.map { it.id })
}