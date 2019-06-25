package com.github.teracy.odpt.core.v4.airplane.request

import com.github.teracy.odpt.model.AirportId
import com.github.teracy.odpt.model.CalendarId
import com.github.teracy.odpt.model.FlightScheduleId
import com.github.teracy.odpt.model.OperatorId

/**
 * フライト時刻表情報検索引数
 */
sealed class FlightScheduleArgument(
    /**
     * 固有識別子(ucode)のリスト
     */
    val idList: List<String>? = null,
    /**
     * 固有識別子別名（フライト時刻表情報ID）のリスト
     */
    val sameAsList: List<String>? = null,
    /**
     * フライト時刻表情報を提供する空港事業者または航空事業者の事業者IDのリスト
     */
    val operatorList: List<String>? = null,
    /**
     * 運航する曜日・日付情報のIDのリスト
     */
    val calendarList: List<String>? = null,
    /**
     * 出発地の空港IDのリスト
     */
    val originAirportList: List<String>? = null,
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
        FlightScheduleArgument(idList = idList)

    /**
     * フライト時刻表情報IDによる検索
     * @param flightScheduleIdList フライト時刻表情報IDのリスト
     */
    class ByFlightScheduleId(flightScheduleIdList: List<FlightScheduleId>) :
        FlightScheduleArgument(sameAsList = flightScheduleIdList.map { it.id })

    /**
     * 出発地の空港IDによる検索
     * @param originAirportIdList 出発地の空港IDのリスト（必須）
     * @param operatorIdList フライト時刻表情報を提供する空港事業者または航空事業者の事業者IDのリスト（任意）
     * @param destinationAirportIdList 目的地の空港IDのリスト（任意）
     * @param calendarIdList 運航する曜日・日付情報のIDのリスト（任意）
     */
    class ByOriginAirportId(
        originAirportIdList: List<AirportId>,
        operatorIdList: List<OperatorId>? = null,
        destinationAirportIdList: List<AirportId>? = null,
        calendarIdList: List<CalendarId>? = null
    ) : FlightScheduleArgument(
        originAirportList = originAirportIdList.map { it.id },
        operatorList = operatorIdList?.map { it.id },
        destinationAirportList = destinationAirportIdList?.map { it.id },
        calendarList = calendarIdList?.map { it.id }
    )

    /**
     * 目的地の空港IDによる検索
     * @param destinationAirportIdList 目的地の空港IDのリスト（必須）
     * @param operatorIdList フライト時刻表情報を提供する空港事業者または航空事業者の事業者IDのリスト（任意）
     * @param originAirportIdList 出発地の空港IDのリスト（任意）
     * @param calendarIdList 運航する曜日・日付情報のIDのリスト（任意）
     */
    class ByDestinationAirportId(
        destinationAirportIdList: List<AirportId>,
        operatorIdList: List<OperatorId>? = null,
        originAirportIdList: List<AirportId>? = null,
        calendarIdList: List<CalendarId>? = null
    ) : FlightScheduleArgument(
        destinationAirportList = destinationAirportIdList.map { it.id },
        operatorList = operatorIdList?.map { it.id },
        originAirportList = originAirportIdList?.map { it.id },
        calendarList = calendarIdList?.map { it.id }
    )
}
