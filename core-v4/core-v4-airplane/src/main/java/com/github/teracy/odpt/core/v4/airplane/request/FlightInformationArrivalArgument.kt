package com.github.teracy.odpt.core.v4.airplane.request

import com.github.teracy.odpt.model.*

/**
 * フライト到着情報検索引数
 */
sealed class FlightInformationArrivalArgument(
    /**
     * 固有識別子(ucode)のリスト
     */
    val idList: List<String>? = null,
    /**
     * 固有識別子別名（フライト到着情報ID）のリスト
     */
    val sameAsList: List<String>? = null,
    /**
     * フライト到着情報を提供する空港事業者または航空事業者の事業者IDのリスト
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
     * 到着空港の空港IDのリスト
     */
    val arrivalAirportList: List<String>? = null,
    /**
     * 到着空港のターミナルの空港ターミナルIDのリスト
     */
    val arrivalAirportTerminalList: List<String>? = null,
    /**
     * 到着空港ゲート番号のリスト
     */
    val arrivalGateList: List<String>? = null,
    /**
     * 出発空港の空港IDのリスト
     */
    val originAirportList: List<String>? = null
) {
    /**
     * 固有識別子による検索
     * @param idList 固有識別子のリスト
     */
    class ById(idList: List<String>) :
        FlightInformationArrivalArgument(idList = idList)

    /**
     * フライト到着情報IDによる検索
     * @param flightInformationArrivalIdList フライト到着情報IDのリスト
     */
    class ByFlightInformationArrivalId(flightInformationArrivalIdList: List<FlightInformationArrivalId>) :
        FlightInformationArrivalArgument(sameAsList = flightInformationArrivalIdList.map { it.id })

    /**
     * 事業者IDによる検索
     * @param operatorIdList フライト到着情報を提供する空港事業者または航空事業者の事業者IDのリスト（必須）
     * @param airlineOperatorIdList エアラインの運行会社の事業者IDのリスト（任意）
     * @param originAirportIdList 出発空港の空港IDのリスト（任意）
     * @param arrivalAirportIdList 到着空港の空港IDのリスト（任意）
     * @param arrivalAirportTerminalIdList 到着空港のターミナルの空港ターミナルIDのリスト（任意）
     * @param arrivalGateList 到着空港ゲート番号のリスト（任意）
     * @param flightStatusIdList フライト状況IDのリスト（任意）
     */
    class ByOperatorId(
        operatorIdList: List<OperatorId>,
        airlineOperatorIdList: List<OperatorId>? = null,
        originAirportIdList: List<AirportId>? = null,
        arrivalAirportIdList: List<AirportId>? = null,
        arrivalAirportTerminalIdList: List<AirportTerminalId>? = null,
        arrivalGateList: List<String>? = null,
        flightStatusIdList: List<FlightStatusId>? = null
    ) : FlightInformationArrivalArgument(
        operatorList = operatorIdList.map { it.id },
        airlineList = airlineOperatorIdList?.map { it.id },
        originAirportList = originAirportIdList?.map { it.id },
        arrivalAirportList = arrivalAirportIdList?.map { it.id },
        arrivalAirportTerminalList = arrivalAirportTerminalIdList?.map { it.id },
        arrivalGateList = arrivalGateList,
        flightStatusList = flightStatusIdList?.map { it.id })

    /**
     * 到着空港の空港IDによる検索
     * @param arrivalAirportIdList 到着空港の空港IDのリスト（必須）
     * @param airlineOperatorIdList エアラインの運行会社の事業者IDのリスト（任意）
     * @param originAirportIdList 出発空港の空港IDのリスト（任意）
     * @param flightStatusIdList フライト状況IDのリスト（任意）
     */
    class ByArrivalAirportId(
        arrivalAirportIdList: List<AirportId>,
        airlineOperatorIdList: List<OperatorId>? = null,
        originAirportIdList: List<AirportId>? = null,
        flightStatusIdList: List<FlightStatusId>? = null
    ) : FlightInformationArrivalArgument(
        arrivalAirportList = arrivalAirportIdList.map { it.id },
        airlineList = airlineOperatorIdList?.map { it.id },
        originAirportList = originAirportIdList?.map { it.id },
        flightStatusList = flightStatusIdList?.map { it.id })

    /**
     * 到着空港のターミナルの空港ターミナルIDによる検索
     * @param arrivalAirportTerminalIdList 到着空港のターミナルの空港ターミナルIDのリスト（必須）
     * @param arrivalGateList 到着空港ゲート番号のリスト（任意）
     * @param airlineOperatorIdList エアラインの運行会社の事業者IDのリスト（任意）
     * @param originAirportIdList 出発空港の空港IDのリスト（任意）
     * @param flightStatusIdList フライト状況IDのリスト（任意）
     */
    class ByArrivalAirportTerminalId(
        arrivalAirportTerminalIdList: List<AirportTerminalId>,
        arrivalGateList: List<String>? = null,
        airlineOperatorIdList: List<OperatorId>? = null,
        originAirportIdList: List<AirportId>? = null,
        flightStatusIdList: List<FlightStatusId>? = null
    ) : FlightInformationArrivalArgument(
        arrivalAirportTerminalList = arrivalAirportTerminalIdList.map { it.id },
        arrivalGateList = arrivalGateList,
        airlineList = airlineOperatorIdList?.map { it.id },
        originAirportList = originAirportIdList?.map { it.id },
        flightStatusList = flightStatusIdList?.map { it.id })
}
