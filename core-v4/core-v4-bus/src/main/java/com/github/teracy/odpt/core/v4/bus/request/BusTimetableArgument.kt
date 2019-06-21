package com.github.teracy.odpt.core.v4.bus.request

import com.github.teracy.odpt.model.BusRoutePatternId
import com.github.teracy.odpt.model.BusTimetableId
import com.github.teracy.odpt.model.CalendarId
import com.github.teracy.odpt.model.OperatorId

/**
 * バス時刻表情報検索引数
 */
sealed class BusTimetableArgument(
    /**
     * 固有識別子(ucode)のリスト
     */
    val idList: List<String>? = null,
    /**
     * 固有識別子別名（バス時刻表情報ID）のリスト
     */
    val sameAsList: List<String>? = null,
    /**
     * 運行会社の事業者IDのリスト
     */
    val operatorList: List<String>? = null,
    /**
     * バス車両の運行系統のIDのリスト
     */
    val busRoutePatternList: List<String>? = null,
    /**
     * 路線・系統名称のリスト
     */
    val titleList: List<String>? = null,
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
        BusTimetableArgument(idList = idList)

    /**
     * バス時刻表情報IDによる検索
     *
     * @param busTimetableIdList バス時刻表情報IDのリスト
     */
    class ByBusTimetableId(busTimetableIdList: List<BusTimetableId>) :
        BusTimetableArgument(sameAsList = busTimetableIdList.map { it.id })

    /**
     * 事業者IDによる検索
     * 利用は推奨しない
     *
     * @param operatorIdList 運行会社の事業者IDのリスト（必須）
     * @param calendarIdList 時刻表を取得したい曜日・日付の日付情報IDのリスト（任意）
     */
    class ByOperatorId(operatorIdList: List<OperatorId>, calendarIdList: List<CalendarId>? = null) :
        BusTimetableArgument(operatorList = operatorIdList.map { it.id }, calendarList = calendarIdList?.map { it.id })

    /**
     * バス系統IDによる検索
     *
     * @param busRoutePatternIdList バス系統IDのリスト（必須）
     * @param calendarIdList 時刻表を取得したい曜日・日付の日付情報IDのリスト（任意）
     */
    class ByBusRoutePatternId(
        busRoutePatternIdList: List<BusRoutePatternId>,
        calendarIdList: List<CalendarId>? = null
    ) :
        BusTimetableArgument(
            busRoutePatternList = busRoutePatternIdList.map { it.id },
            calendarList = calendarIdList?.map { it.id })

    /**
     * 路線・系統名称による検索
     *
     * @param busRouteNameList 路線・系統名称のリスト（必須）
     * @param operatorId 事業者ID（任意。同じ路線・系統名称が存在する場合の絞り込み条件として利用）
     * @param calendarIdList 時刻表を取得したい曜日・日付の日付情報IDのリスト（任意）
     */
    class ByBusRouteName(
        busRouteNameList: List<String>,
        operatorId: OperatorId? = null,
        calendarIdList: List<CalendarId>? = null
    ) :
        BusTimetableArgument(
            titleList = busRouteNameList,
            operatorList = operatorId?.let { listOf(it.id) },
            calendarList = calendarIdList?.map { it.id }
        )
}
