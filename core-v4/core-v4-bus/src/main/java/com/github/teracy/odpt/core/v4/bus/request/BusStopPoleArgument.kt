package com.github.teracy.odpt.core.v4.bus.request

import com.github.teracy.odpt.model.BusRoutePatternId
import com.github.teracy.odpt.model.BusStopPoleId
import com.github.teracy.odpt.model.OperatorId

/**
 * バス停標柱情報検索引数
 */
sealed class BusStopPoleArgument(
    /**
     * 固有識別子(ucode)のリスト
     */
    val idList: List<String>? = null,
    /**
     * 固有識別子別名（バス停標柱ID）のリスト
     */
    val sameAsList: List<String>? = null,
    /**
     * 停留所名称のリスト
     */
    val titleList: List<String>? = null,
    /**
     * 標柱番号のリスト
     */
    val busStopPoleNumberList: List<String>? = null,
    /**
     * 標柱で発着する運行系統IDのリスト
     */
    val busRoutePatternList: List<String>? = null,
    /**
     * 運行会社の事業者IDのリスト
     */
    val operatorList: List<String>? = null
) {
    /**
     * 固有識別子による検索
     *
     * @param idList 固有識別子のリスト
     */
    class ById(idList: List<String>) :
        BusStopPoleArgument(idList = idList)

    /**
     * バス停標柱IDによる検索
     *
     * @param busStopPoleIdList バス停標柱IDのリスト
     */
    class ByBusStopPoleId(busStopPoleIdList: List<BusStopPoleId>) :
        BusStopPoleArgument(sameAsList = busStopPoleIdList.map { it.id })

    /**
     * 標柱で発着する運行系統IDによる検索
     *
     * @param busRoutePatternIdList バス系統IDのリスト（必須）
     * @param busStopPoleNameList 停留所名称のリスト（任意）
     * @param busStopPoleNumberList 標柱番号のリスト（任意）
     */
    class ByBusRoutePatternId(
        busRoutePatternIdList: List<BusRoutePatternId>,
        busStopPoleNameList: List<String>? = null,
        busStopPoleNumberList: List<String>? = null
    ) :
        BusStopPoleArgument(
            busRoutePatternList = busRoutePatternIdList.map { it.id },
            titleList = busStopPoleNameList,
            busStopPoleNumberList = busStopPoleNumberList
        )

    /**
     * 停留所名称による検索
     *
     * @param busStopPoleNameList 停留所名称のリスト（必須）
     * @param operatorIdList 事業者IDのリスト（任意）
     * @param busStopPoleNumberList 標柱番号のリスト（任意）
     */
    class ByBusStopPoleName(
        busStopPoleNameList: List<String>,
        operatorIdList: List<OperatorId>? = null,
        busStopPoleNumberList: List<String>? = null
    ) :
        BusStopPoleArgument(
            titleList = busStopPoleNameList,
            operatorList = operatorIdList?.map { it.id },
            busStopPoleNumberList = busStopPoleNumberList
        )

    /**
     * 事業者IDによる検索
     * 利用は推奨しない
     *
     * @param operatorIdList 事業者IDのリスト
     */
    class ByOperatorId(operatorIdList: List<OperatorId>) :
        BusStopPoleArgument(operatorList = operatorIdList.map { it.id })
}
