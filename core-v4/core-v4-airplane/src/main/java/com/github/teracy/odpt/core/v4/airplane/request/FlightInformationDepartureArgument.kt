package com.github.teracy.odpt.core.v4.airplane.request

import com.github.teracy.odpt.model.*

/**
 * フライト出発情報検索引数
 */
sealed class FlightInformationDepartureArgument(
    /**
     * 固有識別子(ucode)のリスト
     */
    val idList: List<String>? = null,
    /**
     * 固有識別子別名（フライト出発情報ID）のリスト
     */
    val sameAsList: List<String>? = null,
    /**
     * フライト出発情報を提供する空港事業者または航空事業者の事業者IDのリスト
     */
    val operatorList: List<String>? = null,
    /**
     * エアラインの運行会社の事業者IDのリスト
     */
    val airlineList: List<String>? = null,
    /**
     * フライト状況IDのリスト
     */
    val flightStatusList: List<String>? = null,
    /**
     * 出発空港の空港IDのリスト
     */
    val departureAirportList: List<String>? = null,
    /**
     * 出発空港のターミナルの空港ターミナルIDのリスト
     */
    val departureAirportTerminalList: List<String>? = null,
    /**
     * 出発空港ゲート番号のリスト
     */
    val departureGateList: List<String>? = null,
    /**
     * 目的地の空港IDのリスト
     */
    val destinationAirportList: List<String>? = null
) {
    /**
     * 固有識別子による検索
     * @param idList 固有識別子のリスト
     */
    class ById(idList: List<String>) :
        FlightInformationDepartureArgument(idList = idList)

    /**
     * フライト出発情報IDによる検索
     * @param flightInformationArrivalIdList フライト出発情報IDのリスト
     */
    class ByFlightInformationArrivalId(flightInformationArrivalIdList: List<FlightInformationArrivalId>) :
        FlightInformationDepartureArgument(sameAsList = flightInformationArrivalIdList.map { it.id })

    /**
     * 事業者IDによる検索
     * @param operatorIdList フライト出発情報を提供する空港事業者または航空事業者の事業者IDのリスト（必須）
     * @param airlineOperatorIdList エアラインの運行会社の事業者IDのリスト（任意）
     * @param destinationAirportIdList 目的地の空港IDのリスト（任意）
     * @param departureAirportIdList 出発空港の空港IDのリスト（任意）
     * @param departureAirportTerminalIdList 出発空港のターミナルの空港ターミナルIDのリスト（任意）
     * @param departureGateList 出発空港ゲート番号のリスト（任意）
     * @param flightStatusIdList フライト状況IDのリスト（任意）
     */
    class ByOperatorId(
        operatorIdList: List<OperatorId>,
        airlineOperatorIdList: List<OperatorId>? = null,
        destinationAirportIdList: List<AirportId>? = null,
        departureAirportIdList: List<AirportId>? = null,
        departureAirportTerminalIdList: List<AirportTerminalId>? = null,
        departureGateList: List<String>? = null,
        flightStatusIdList: List<FlightStatusId>? = null
    ) : FlightInformationDepartureArgument(
        operatorList = operatorIdList.map { it.id },
        airlineList = airlineOperatorIdList?.map { it.id },
        destinationAirportList = destinationAirportIdList?.map { it.id },
        departureAirportList = departureAirportIdList?.map { it.id },
        departureAirportTerminalList = departureAirportTerminalIdList?.map { it.id },
        departureGateList = departureGateList,
        flightStatusList = flightStatusIdList?.map { it.id })

    /**
     * 出発空港の空港IDによる検索
     * @param departureAirportIdList 到着空港の空港IDのリスト（必須）
     * @param airlineOperatorIdList エアラインの運行会社の事業者IDのリスト（任意）
     * @param destinationAirportIdList 目的地の空港IDのリスト（任意）
     * @param flightStatusIdList フライト状況IDのリスト（任意）
     */
    class ByDepartureAirportId(
        departureAirportIdList: List<AirportId>,
        airlineOperatorIdList: List<OperatorId>? = null,
        destinationAirportIdList: List<AirportId>? = null,
        flightStatusIdList: List<FlightStatusId>? = null
    ) : FlightInformationDepartureArgument(
        departureAirportList = departureAirportIdList.map { it.id },
        airlineList = airlineOperatorIdList?.map { it.id },
        destinationAirportList = destinationAirportIdList?.map { it.id },
        flightStatusList = flightStatusIdList?.map { it.id })

    /**
     * 出発空港のターミナルの空港ターミナルIDによる検索
     * @param departureAirportTerminalIdList 出発空港のターミナルの空港ターミナルIDのリスト（必須）
     * @param departureGateList 出発空港ゲート番号のリスト（任意）
     * @param airlineOperatorIdList エアラインの運行会社の事業者IDのリスト（任意）
     * @param destinationAirportIdList 目的地の空港IDのリスト（任意）
     * @param flightStatusIdList フライト状況IDのリスト（任意）
     */
    class ByDepartureAAirportTerminalId(
        departureAirportTerminalIdList: List<AirportTerminalId>,
        departureGateList: List<String>? = null,
        airlineOperatorIdList: List<OperatorId>? = null,
        destinationAirportIdList: List<AirportId>? = null,
        flightStatusIdList: List<FlightStatusId>? = null
    ) : FlightInformationDepartureArgument(
        departureAirportTerminalList = departureAirportTerminalIdList.map { it.id },
        departureGateList = departureGateList,
        airlineList = airlineOperatorIdList?.map { it.id },
        destinationAirportList = destinationAirportIdList?.map { it.id },
        flightStatusList = flightStatusIdList?.map { it.id })
}
