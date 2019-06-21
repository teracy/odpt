package com.github.teracy.odpt.core.v4.bus

import com.github.teracy.odpt.core.v4.bus.request.*
import com.github.teracy.odpt.core.v4.bus.response.*
import com.github.teracy.odpt.model.ApiClient

/**
 * v4版データ取得・検索APIを利用したバス情報クライアント
 *
 * 個々のパラメータについてはリスト内でのOR条件、別パラメータはAND条件となる
 */
interface BusDataPointApiClient : ApiClient {
    /**
     * v4版バスデータ取得・検索API接続サービスのインスタンスを取得する
     */
    fun busDataPointApiService(): BusDataPointApiService

    /**
     * バス運行情報取得
     *
     * @param idList 固有識別子(ucode)のリスト
     * @param sameAsList 固有識別子別名（バス運行情報ID）のリスト
     * @param busRoutePatternList バス車両の運行系統のIDのリスト
     * @param operatorList 運行会社の事業者IDのリスト
     * @param fromBusStopPoleList 直近に通過したバス停のIDのリスト
     * @param toBusStopPoleList 次に到着するバス停のIDのリスト
     * @return バス運行情報リスト
     */
    suspend fun getBus(
        idList: List<String>? = null,
        sameAsList: List<String>? = null,
        busRoutePatternList: List<String>? = null,
        operatorList: List<String>? = null,
        fromBusStopPoleList: List<String>? = null,
        toBusStopPoleList: List<String>? = null
    ): List<OdptBus> {
        return busDataPointApiService().getBusAsync(
            consumerKey = consumerKey(),
            id = idList.concatenate(),
            sameAs = sameAsList.concatenate(),
            busRoutePattern = busRoutePatternList.concatenate(),
            operator = operatorList.concatenate(),
            fromBusStopPole = fromBusStopPoleList.concatenate(),
            toBusStopPole = toBusStopPoleList.concatenate()
        ).await()
    }

    /**
     * バス時刻表情報取得
     *
     * @param idList 固有識別子(ucode)のリスト
     * @param sameAsList 固有識別子別名（バス時刻表情報ID）のリスト
     * @param operatorList 運行会社の事業者IDのリスト
     * @param busRoutePatternList バス車両の運行系統のIDのリスト
     * @param titleList バス路線名称（系統名等) のリスト
     * @param calendarList 運行する日付・曜日情報のIDのリスト
     * @return バス時刻表情報リスト
     */
    suspend fun getBusTimetable(
        idList: List<String>? = null,
        sameAsList: List<String>? = null,
        operatorList: List<String>? = null,
        busRoutePatternList: List<String>? = null,
        titleList: List<String>? = null,
        calendarList: List<String>? = null
    ): List<OdptBusTimetable> {
        return busDataPointApiService().getBusTimetableAsync(
            consumerKey = consumerKey(),
            id = idList.concatenate(),
            sameAs = sameAsList.concatenate(),
            operator = operatorList.concatenate(),
            busRoutePattern = busRoutePatternList.concatenate(),
            title = titleList.concatenate(),
            calendar = calendarList.concatenate()
        ).await()
    }

    /**
     * バス運行系統情報取得
     *
     * @param idList 固有識別子(ucode)のリスト
     * @param sameAsList 固有識別子別名（バス運行系統情報ID）のリスト
     * @param titleList バス路線名称（系統名等) のリスト
     * @param operatorList 運行会社の事業者IDのリスト
     * @param busRouteList 路線のIDのリスト
     * @return バス運行系統情報リスト
     */
    suspend fun getBusRoutePattern(
        idList: List<String>? = null,
        sameAsList: List<String>? = null,
        titleList: List<String>? = null,
        operatorList: List<String>? = null,
        busRouteList: List<String>? = null
    ): List<OdptBusRoutePattern> {
        return busDataPointApiService().getBusRoutePatternAsync(
            consumerKey = consumerKey(),
            id = idList.concatenate(),
            sameAs = sameAsList.concatenate(),
            title = titleList.concatenate(),
            operator = operatorList.concatenate(),
            busRoute = busRouteList.concatenate()
        ).await()
    }

    /**
     * バス運賃情報取得
     *
     * @param idList 固有識別子(ucode)のリスト
     * @param sameAsList 固有識別子別名（バス運賃情報ID）のリスト
     * @param operatorList 運行会社の事業者IDのリスト
     * @param fromBusStopPoleList 乗車バス停標柱のIDのリスト
     * @param toBusStopPoleList 降車バス停標柱のIDのリスト
     * @param ticketFare 切符利用時の運賃
     * @param childTicketFare 切符利用時の子供運賃
     * @param icCardFare ICカード利用時の運賃
     * @param childIcCardFare ICカード利用時の子供運賃
     * @return バス運賃情報リスト
     */
    suspend fun getBusRoutePatternFare(
        idList: List<String>? = null,
        sameAsList: List<String>? = null,
        operatorList: List<String>? = null,
        fromBusStopPoleList: List<String>? = null,
        toBusStopPoleList: List<String>? = null,
        ticketFare: Int? = null,
        childTicketFare: Int? = null,
        icCardFare: Int? = null,
        childIcCardFare: Int? = null
    ): List<OdptBusRoutePatternFare> {
        return busDataPointApiService().getBusRoutePatternFareAsync(
            consumerKey = consumerKey(),
            id = idList.concatenate(),
            sameAs = sameAsList.concatenate(),
            operator = operatorList.concatenate(),
            fromBusStopPole = fromBusStopPoleList.concatenate(),
            toBusStopPole = toBusStopPoleList.concatenate(),
            ticketFare = ticketFare,
            childTicketFare = childTicketFare,
            icCardFare = icCardFare,
            childIcCardFare = childIcCardFare
        ).await()
    }

    /**
     * バス停標柱情報取得
     *
     * @param idList 固有識別子(ucode)のリスト
     * @param sameAsList 固有識別子別名（バス運行系統情報ID）のリスト
     * @param titleList バス路線名称（系統名等) のリスト
     * @param busStopPoleNumberList 標柱番号のリスト
     * @param busRoutePatternList 標柱で発着する系統のIDのリスト
     * @param operatorList 運行会社の事業者IDのリスト
     * @return バス停標柱情報リスト
     */
    suspend fun getBusStopPole(
        idList: List<String>? = null,
        sameAsList: List<String>? = null,
        titleList: List<String>? = null,
        busStopPoleNumberList: List<String>? = null,
        busRoutePatternList: List<String>? = null,
        operatorList: List<String>? = null
    ): List<OdptBusStopPole> {
        return busDataPointApiService().getBusStopPoleAsync(
            consumerKey = consumerKey(),
            id = idList.concatenate(),
            sameAs = sameAsList.concatenate(),
            title = titleList.concatenate(),
            busStopPoleNumber = busStopPoleNumberList.concatenate(),
            busRoutePattern = busRoutePatternList.concatenate(),
            operator = operatorList.concatenate()
        ).await()
    }

    /**
     * バス停標柱時刻表情報取得
     *
     * @param idList 固有識別子(ucode)のリスト
     * @param sameAsList 固有識別子別名（駅時刻表情報ID）のリスト
     * @param busStopPoleList バス停標柱のIDのリスト
     * @param busDirectionList バス方面のIDのリスト
     * @param busRouteList バス路線のIDのリスト
     * @param operatorList 運行会社の事業者IDのリスト
     * @param calendarList 時刻表を取得したい曜日・日付の日付情報IDのリスト
     * @param dateList 時刻表を取得したい特定日付（ISO8601 日付時刻形式）のリスト
     * @return バス停標柱時刻表情報リスト
     */
    suspend fun getBusStopPoleTimetable(
        idList: List<String>? = null,
        sameAsList: List<String>? = null,
        busStopPoleList: List<String>? = null,
        busDirectionList: List<String>? = null,
        busRouteList: List<String>? = null,
        operatorList: List<String>? = null,
        calendarList: List<String>? = null,
        dateList: List<String>? = null
    ): List<OdptBusStopPoleTimetable> {
        return busDataPointApiService().getBusStopPoleTimetableAsync(
            consumerKey = consumerKey(),
            id = idList.concatenate(),
            sameAs = sameAsList.concatenate(),
            busStopPole = busStopPoleList.concatenate(),
            busDirection = busDirectionList.concatenate(),
            busRoute = busRouteList.concatenate(),
            operator = operatorList.concatenate(),
            calendar = calendarList.concatenate(),
            date = dateList.concatenate()
        ).await()
    }

    /**
     * バス運行情報取得
     *
     * @param argument 検索引数
     * @return バス運行情報リスト
     */
    suspend fun getBus(argument: BusArgument): List<OdptBus> {
        return getBus(
            idList = argument.idList,
            sameAsList = argument.sameAsList,
            busRoutePatternList = argument.busRoutePatternList,
            operatorList = argument.operatorList,
            fromBusStopPoleList = argument.fromBusStopPoleList,
            toBusStopPoleList = argument.toBusStopPoleList
        )
    }

    /**
     * バス時刻表情報取得
     *
     * @param argument 検索引数
     * @return バス時刻表情報リスト
     */
    suspend fun getBusTimetable(argument: BusTimetableArgument): List<OdptBusTimetable> {
        return getBusTimetable(
            idList = argument.idList,
            sameAsList = argument.sameAsList,
            operatorList = argument.operatorList,
            busRoutePatternList = argument.busRoutePatternList,
            titleList = argument.titleList,
            calendarList = argument.calendarList
        )
    }

    /**
     * バス運行系統情報取得
     *
     * @param argument 検索引数
     * @return バス運行系統情報リスト
     */
    suspend fun getBusRoutePattern(argument: BusRoutePatternArgument): List<OdptBusRoutePattern> {
        return getBusRoutePattern(
            idList = argument.idList,
            sameAsList = argument.sameAsList,
            titleList = argument.titleList,
            operatorList = argument.operatorList,
            busRouteList = argument.busRouteList
        )
    }

    /**
     * バス運賃情報取得
     *
     * @param argument 検索引数
     * @return バス運賃情報リスト
     */
    suspend fun getBusRoutePatternFare(argument: BusRoutePatternFareArgument): List<OdptBusRoutePatternFare> {
        return getBusRoutePatternFare(
            idList = argument.idList,
            sameAsList = argument.sameAsList,
            operatorList = argument.operatorList,
            fromBusStopPoleList = argument.fromBusStopPoleList,
            toBusStopPoleList = argument.toBusStopPoleList
        )
    }

    /**
     * バス停標柱情報取得
     *
     * @param argument 検索引数
     * @return バス停標柱情報リスト
     */
    suspend fun getBusStopPole(argument: BusStopPoleArgument): List<OdptBusStopPole> {
        return getBusStopPole(
            idList = argument.idList,
            sameAsList = argument.sameAsList,
            titleList = argument.titleList,
            busStopPoleNumberList = argument.busStopPoleNumberList,
            busRoutePatternList = argument.busRoutePatternList,
            operatorList = argument.operatorList
        )
    }

    /**
     * バス停標柱時刻表情報取得
     *
     * @param argument 検索引数
     * @return バス停標柱時刻表情報リスト
     */
    suspend fun getBusStopPoleTimetable(argument: BusStopPoleTimetableArgument): List<OdptBusStopPoleTimetable> {
        return getBusStopPoleTimetable(
            idList = argument.idList,
            sameAsList = argument.sameAsList,
            busStopPoleList = argument.busStopPoleList,
            busDirectionList = argument.busDirectionList,
            busRouteList = argument.busRouteList,
            operatorList = argument.operatorList
        )
    }

    private fun List<String>?.concatenate(): String? {
        return this?.joinToString(separator = ",")
    }
}
