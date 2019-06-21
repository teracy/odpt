package com.github.teracy.odpt.core.v4.bus.request

import com.github.teracy.odpt.model.BusId
import com.github.teracy.odpt.model.BusRoutePatternId
import com.github.teracy.odpt.model.BusStopPoleId
import com.github.teracy.odpt.model.OperatorId

/**
 * バス運行情報検索引数
 */
sealed class BusArgument(
    /**
     * 固有識別子(ucode)のリスト
     */
    val idList: List<String>? = null,
    /**
     * 固有識別子別名（バス運行情報ID）のリスト
     */
    val sameAsList: List<String>? = null,
    /**
     * バス車両の運行系統のIDのリスト
     */
    val busRoutePatternList: List<String>? = null,
    /**
     * 運行会社の事業者IDのリスト
     */
    val operatorList: List<String>? = null,
    /**
     * 直近に通過したバス停のIDのリスト
     */
    val fromBusStopPoleList: List<String>? = null,
    /**
     * 次に到着するバス停のIDのリスト
     */
    val toBusStopPoleList: List<String>? = null
) {
    /**
     * 固有識別子による検索
     *
     * @param idList 固有識別子のリスト
     */
    class ById(idList: List<String>) :
        BusArgument(idList = idList)

    /**
     * バス運行情報IDによる検索
     *
     * @param busIdList バス運行情報IDのリスト
     */
    class ByBusId(busIdList: List<BusId>) :
        BusArgument(sameAsList = busIdList.map { it.id })

    /**
     * バス系統IDによる検索
     *
     * @param busRoutePatternIdList バス系統IDのリスト
     */
    class ByBusRoutePatternId(busRoutePatternIdList: List<BusRoutePatternId>) :
        BusArgument(busRoutePatternList = busRoutePatternIdList.map { it.id })

    /**
     * 事業者IDによる検索
     * 利用は推奨しない
     *
     * @param operatorIdList 運行会社の事業者IDのリスト
     */
    class ByOperatorId(operatorIdList: List<OperatorId>) :
        BusArgument(operatorList = operatorIdList.map { it.id })

    /**
     * 直近に通過したバス停のIDによる検索
     * 特定のバス停（群）を通過したバスの運行状況の検索を想定
     *
     * @param fromBusStopPoleIdList 直近に通過したバス停のIDのリスト
     */
    class ByFromBusStopPoleId(fromBusStopPoleIdList: List<BusStopPoleId>) :
        BusArgument(fromBusStopPoleList = fromBusStopPoleIdList.map { it.id })

    /**
     * 次に到着するバス停のIDによる検索
     * 特定のバス停（群）に到着するバスの運行状況の検索を想定
     *
     * @param toBusStopPoleIdList 次に到着するバス停のIDのリスト
     */
    class ByToBusStopPoleId(toBusStopPoleIdList: List<BusStopPoleId>) :
        BusArgument(toBusStopPoleList = toBusStopPoleIdList.map { it.id })

    /**
     * 直近に通過したバス停のID及び次に到着するバス停のIDによる検索
     * 特定の区間を走るバスの運行状況の検索を想定
     *
     * @param fromBusStopPoleId 直近に通過したバス停のID
     * @param toBusStopPoleId 次に到着するバス停のID
     */
    class ByFromToBusStopPoleId(fromBusStopPoleId: BusStopPoleId, toBusStopPoleId: BusStopPoleId) :
        BusArgument(fromBusStopPoleList = listOf(fromBusStopPoleId.id), toBusStopPoleList = listOf(toBusStopPoleId.id))
}
