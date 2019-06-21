package com.github.teracy.odpt.core.v4.bus.request

import com.github.teracy.odpt.model.*

/**
 * バス停標柱時刻表情報検索引数
 */
sealed class BusStopPoleTimetableArgument(
    /**
     * 固有識別子(ucode)のリスト
     */
    val idList: List<String>? = null,
    /**
     * 固有識別子別名（バス停標柱時刻表情報ID）のリスト
     */
    val sameAsList: List<String>? = null,
    /**
     * バス停標柱IDのリスト
     */
    val busStopPoleList: List<String>? = null,
    /**
     * バス方面IDのリスト
     */
    val busDirectionList: List<String>? = null,
    /**
     * バス路線IDのリスト
     */
    val busRouteList: List<String>? = null,
    /**
     * 運行会社の事業者IDのリスト
     */
    val operatorList: List<String>? = null,
    /**
     * 時刻表を取得したい曜日・日付の日付情報IDのリスト
     */
    val calendarList: List<String>? = null
) {
    /**
     * 固有識別子による検索
     *
     * @param idList 固有識別子のリスト
     */
    class ById(idList: List<String>) :
        BusStopPoleTimetableArgument(idList = idList)

    /**
     * バス停標柱時刻表情報IDによる検索
     *
     * @param busStopPoleIdList バス停標柱時刻表情報IDのリスト
     */
    class ByBusStopPoleTimetableId(busStopPoleIdList: List<BusStopPoleTimetableId>) :
        BusStopPoleTimetableArgument(sameAsList = busStopPoleIdList.map { it.id })

    /**
     * バス停標柱IDによる検索
     *
     * @param busStopPoleIdList バス停標柱IDのリスト（必須）
     * @param busDirectionIdList バス方面IDのリスト（任意）
     * @param busRouteIdList バス路線IDのリスト（任意）
     * @param calendarIdList 時刻表を取得したい曜日・日付の日付情報IDのリスト（任意）
     *
     */
    class ByBusStopPoleId(
        busStopPoleIdList: List<BusStopPoleId>,
        busDirectionIdList: List<BusDirectionId>? = null,
        busRouteIdList: List<BusRouteId>? = null,
        calendarIdList: List<CalendarId>? = null
    ) :
        BusStopPoleTimetableArgument(
            busStopPoleList = busStopPoleIdList.map { it.id },
            busDirectionList = busDirectionIdList?.map { it.id },
            busRouteList = busRouteIdList?.map { it.id },
            calendarList = calendarIdList?.map { it.id }
        )

    /**
     * 事業者IDによる検索
     * 利用は推奨しない
     *
     * @param operatorIdList 事業者IDのリスト（必須）
     * @param calendarIdList 時刻表を取得したい曜日・日付の日付情報IDのリスト（任意）
     */
    class ByOperatorId(operatorIdList: List<OperatorId>, calendarIdList: List<CalendarId>? = null) :
        BusStopPoleTimetableArgument(
            operatorList = operatorIdList.map { it.id },
            calendarList = calendarIdList?.map { it.id }
        )
}
