package com.github.teracy.odpt.core.v4.airplane

import com.github.teracy.odpt.core.v4.airplane.request.*
import com.github.teracy.odpt.core.v4.airplane.response.*
import com.github.teracy.odpt.model.ApiClient

/**
 * v4版データ取得・検索APIを利用した航空機情報クライアント
 *
 * 個々のパラメータについてはリスト内でのOR条件、別パラメータはAND条件となる
 */
interface AirplaneDataPointApiClient : ApiClient {
    /**
     * v4版航空機データ取得・検索API接続サービスのインスタンスを取得する
     */
    fun airplaneDataPointApiService(): AirplaneDataPointApiService

    /**
     * 空港情報取得
     *
     * @param idList 固有識別子(ucode)のリスト
     * @param sameAsList 固有識別子別名（空港ID）のリスト
     * @return 空港情報リスト
     */
    suspend fun getAirport(
        idList: List<String>? = null,
        sameAsList: List<String>? = null
    ): List<OdptAirport> {
        return airplaneDataPointApiService().getAirportAsync(
            consumerKey = consumerKey(),
            id = idList.concatenate(),
            sameAs = sameAsList.concatenate()
        ).await()
    }

    /**
     * 空港ターミナル情報取得
     *
     * @param idList 固有識別子(ucode)のリスト
     * @param sameAsList 固有識別子別名（空港ID）のリスト
     * @param airportList 空港IDのリスト
     * @return 空港ターミナル情報リスト
     */
    suspend fun getAirportTerminal(
        idList: List<String>? = null,
        sameAsList: List<String>? = null,
        airportList: List<String>? = null
    ): List<OdptAirportTerminal> {
        return airplaneDataPointApiService().getAirportTerminalAsync(
            consumerKey = consumerKey(),
            id = idList.concatenate(),
            sameAs = sameAsList.concatenate(),
            airport = airportList.concatenate()
        ).await()
    }

    /**
     * フライト到着情報取得
     *
     * @param idList 固有識別子(ucodeのリスト
     * @param sameAsList 固有識別子別名（フライト到着情報ID）のリスト
     * @param operatorList フライト到着情報を提供する空港事業者または航空事業者の事業者IDのリスト
     * @param airlineList エアラインの運行会社の事業者IDのリスト
     * @param flightStatusList フライト状況IDのリスト
     * @param arrivalAirportList 到着空港の空港IDのリスト
     * @param arrivalAirportTerminalList 到着空港のターミナルの空港ターミナルIDのリスト
     * @param arrivalGateList 到着空港ゲート番号のリスト
     * @param originAirportList 出発空港の空港IDのリスト
     * @return フライト到着情報リスト
     */
    suspend fun getFlightInformationArrival(
        idList: List<String>? = null,
        sameAsList: List<String>? = null,
        operatorList: List<String>? = null,
        airlineList: List<String>? = null,
        flightStatusList: List<String>? = null,
        arrivalAirportList: List<String>? = null,
        arrivalAirportTerminalList: List<String>? = null,
        arrivalGateList: List<String>? = null,
        originAirportList: List<String>? = null
    ): List<OdptFlightInformationArrival> {
        return airplaneDataPointApiService().getFlightInformationArrivalAsync(
            consumerKey = consumerKey(),
            id = idList.concatenate(),
            sameAs = sameAsList.concatenate(),
            operator = operatorList.concatenate(),
            airline = airlineList.concatenate(),
            flightStatus = flightStatusList.concatenate(),
            arrivalAirport = arrivalAirportList.concatenate(),
            arrivalAirportTerminal = arrivalAirportTerminalList.concatenate(),
            arrivalGate = arrivalGateList.concatenate(),
            originAirport = originAirportList.concatenate()
        ).await()
    }

    /**
     * フライト出発情報取得
     *
     * @param idList 固有識別子(ucodeのリスト
     * @param sameAsList 固有識別子別名（フライト出発情報ID）のリスト
     * @param operatorList フライト到着情報を提供する空港事業者または航空事業者の事業者IDのリスト
     * @param airlineList エアラインの運行会社の事業者IDのリスト
     * @param flightStatusList フライト状況IDのリスト
     * @param departureAirportList 出発空港の空港IDのリスト
     * @param departureAirportTerminalList 出発空港のターミナルの空港ターミナルIDのリスト
     * @param departureGateList 出発空港ゲート番号のリスト
     * @param destinationAirportList 目的地の空港IDのリスト
     * @return フライト出発情報リスト
     */
    suspend fun getFlightInformationDeparture(
        idList: List<String>? = null,
        sameAsList: List<String>? = null,
        operatorList: List<String>? = null,
        airlineList: List<String>? = null,
        flightStatusList: List<String>? = null,
        departureAirportList: List<String>? = null,
        departureAirportTerminalList: List<String>? = null,
        departureGateList: List<String>? = null,
        destinationAirportList: List<String>? = null
    ): List<OdptFlightInformationDeparture> {
        return airplaneDataPointApiService().getFlightInformationDepartureAsync(
            consumerKey = consumerKey(),
            id = idList.concatenate(),
            sameAs = sameAsList.concatenate(),
            operator = operatorList.concatenate(),
            airline = airlineList.concatenate(),
            flightStatus = flightStatusList.concatenate(),
            departureAirport = departureAirportList.concatenate(),
            departureAirportTerminal = departureAirportTerminalList.concatenate(),
            departureGate = departureGateList.concatenate(),
            destinationAirport = destinationAirportList.concatenate()
        ).await()
    }

    /**
     * フライト時刻表情報取得
     *
     * @param idList 固有識別子(ucode)のリスト
     * @param sameAsList 固有識別子別名（フライト時刻表情報ID）のリスト
     * @param operatorList フライト時刻表情報を提供する空港事業者または航空事業者の事業者IDのリスト
     * @param calendarList 運航する曜日・日付情報のIDのリスト
     * @param originAirportList 出発地の空港IDのリスト
     * @param destinationAirportList 目的地の空港IDのリスト
     * @return フライト時刻表情報リスト
     */
    suspend fun getFlightSchedule(
        idList: List<String>? = null,
        sameAsList: List<String>? = null,
        operatorList: List<String>? = null,
        calendarList: List<String>? = null,
        originAirportList: List<String>? = null,
        destinationAirportList: List<String>? = null
    ): List<OdptFlightSchedule> {
        return airplaneDataPointApiService().getFlightScheduleAsync(
            consumerKey = consumerKey(),
            id = idList.concatenate(),
            sameAs = sameAsList.concatenate(),
            operator = operatorList.concatenate(),
            calendar = calendarList.concatenate(),
            originAirport = originAirportList.concatenate(),
            destinationAirport = destinationAirportList.concatenate()
        ).await()
    }

    /**
     * フライト状況情報取得
     *
     * @param idList 固有識別子(ucode)のリスト
     * @param sameAsList 固有識別子別名（フライト状況ID）のリスト
     * @return フライト状況情報リスト
     */
    suspend fun getFlightStatus(
        idList: List<String>? = null,
        sameAsList: List<String>? = null
    ): List<OdptFlightStatus> {
        return airplaneDataPointApiService().getFlightStatusAsync(
            consumerKey = consumerKey(),
            id = idList.concatenate(),
            sameAs = sameAsList.concatenate()
        ).await()
    }

    /**
     * 空港情報取得
     *
     * @param argument 検索引数
     * @return 空港情報リスト
     */
    suspend fun getAirport(argument: AirportArgument): List<OdptAirport> {
        return getAirport(idList = argument.idList, sameAsList = argument.sameAsList)
    }

    /**
     * 空港ターミナル情報取得
     *
     * @param argument 検索引数
     * @return 空港ターミナル情報リスト
     */
    suspend fun getAirportTerminal(argument: AirportTerminalArgument): List<OdptAirportTerminal> {
        return getAirportTerminal(
            idList = argument.idList,
            sameAsList = argument.sameAsList,
            airportList = argument.airportList
        )
    }

    /**
     * フライト到着情報取得
     *
     * @param argument 検索引数
     * @return フライト到着情報リスト
     */
    suspend fun getFlightInformationArrival(argument: FlightInformationArrivalArgument): List<OdptFlightInformationArrival> {
        return getFlightInformationArrival(
            idList = argument.idList,
            sameAsList = argument.sameAsList,
            operatorList = argument.operatorList,
            airlineList = argument.airlineList,
            flightStatusList = argument.flightStatusList,
            arrivalAirportList = argument.arrivalAirportList,
            arrivalAirportTerminalList = argument.arrivalAirportTerminalList,
            arrivalGateList = argument.arrivalGateList,
            originAirportList = argument.originAirportList
        )
    }

    /**
     * フライト出発情報取得
     *
     * @param argument 検索引数
     * @return フライト出発情報リスト
     */
    suspend fun getFlightInformationDeparture(argument: FlightInformationDepartureArgument): List<OdptFlightInformationDeparture> {
        return getFlightInformationDeparture(
            idList = argument.idList,
            sameAsList = argument.sameAsList,
            operatorList = argument.operatorList,
            airlineList = argument.airlineList,
            flightStatusList = argument.flightStatusList,
            departureAirportList = argument.departureAirportList,
            departureAirportTerminalList = argument.departureAirportTerminalList,
            departureGateList = argument.departureGateList,
            destinationAirportList = argument.destinationAirportList
        )
    }

    /**
     * フライト時刻表情報取得
     *
     * @param argument 検索引数
     * @return フライト時刻表情報リスト
     */
    suspend fun getFlightSchedule(argument: FlightScheduleArgument): List<OdptFlightSchedule> {
        return getFlightSchedule(
            idList = argument.idList,
            sameAsList = argument.sameAsList,
            operatorList = argument.operatorList,
            calendarList = argument.calendarList,
            originAirportList = argument.originAirportList,
            destinationAirportList = argument.destinationAirportList
        )
    }

    /**
     * フライト状況情報取得
     *
     * @param argument 検索引数
     * @return フライト状況情報リスト
     */
    suspend fun getFlightStatus(argument: FlightStatusArgument): List<OdptFlightStatus> {
        return getFlightStatus(idList = argument.idList, sameAsList = argument.sameAsList)
    }

    fun List<String>?.concatenate(): String? {
        return this?.joinToString(separator = ",")
    }
}
