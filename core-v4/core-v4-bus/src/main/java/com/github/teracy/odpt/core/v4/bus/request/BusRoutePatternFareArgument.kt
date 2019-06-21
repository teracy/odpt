package com.github.teracy.odpt.core.v4.bus.request

import com.github.teracy.odpt.model.BusRoutePatternFareId
import com.github.teracy.odpt.model.BusStopPoleId
import com.github.teracy.odpt.model.OperatorId
import com.github.teracy.odpt.model.StationId

/**
 * バス運賃情報検索引数
 */
sealed class BusRoutePatternFareArgument(
    /**
     * 固有識別子(ucode)のリスト
     */
    val idList: List<String>? = null,
    /**
     * 固有識別子別名（バス運賃情報ID）のリスト
     */
    val sameAsList: List<String>? = null,
    /**
     * 運行会社の事業者IDのリスト
     */
    val operatorList: List<String>? = null,
    /**
     * 駅間の始点駅の駅IDのリスト
     */
    val fromBusStopPoleList: List<String>? = null,
    /**
     * 駅間の終点駅の駅IDのリスト
     */
    val toBusStopPoleList: List<String>? = null
) {
    /**
     * 固有識別子による検索
     *
     * @param idList 固有識別子のリスト
     */
    class ById(idList: List<String>) :
        BusRoutePatternFareArgument(idList = idList)

    /**
     * バス運賃情報IDによる検索
     *
     * @param busRoutePatternFareId バス運賃情報IDのリスト
     */
    class ByBusRoutePatternFareId(busRoutePatternFareId: List<BusRoutePatternFareId>) :
        BusRoutePatternFareArgument(sameAsList = busRoutePatternFareId.map { it.id })

    /**
     * 事業者IDによる検索
     * 利用は推奨しない
     *
     * @param operatorIdList 事業者IDのリスト
     */
    class ByOperatorId(operatorIdList: List<OperatorId>) :
        BusRoutePatternFareArgument(operatorList = operatorIdList.map { it.id })

    /**
     * 乗車バス停標柱のバス停標柱IDのみによる検索
     *
     * @param fromBusStopPoleIdList 乗車バス停標柱のバス停標柱IDのリスト
     */
    class ByFromBusStopPoleId(fromBusStopPoleIdList: List<BusStopPoleId>) :
        BusRoutePatternFareArgument(fromBusStopPoleList = fromBusStopPoleIdList.map { it.id })

    /**
     * 降車バス停標柱のバス停標柱IDのみによる検索
     *
     * @param toBusStopPoleIdList 降車バス停標柱のバス停標柱IDのリスト
     */
    class ByToBusStopPoleId(toBusStopPoleIdList: List<BusStopPoleId>) :
        BusRoutePatternFareArgument(toBusStopPoleList = toBusStopPoleIdList.map { it.id })

    /**
     * 乗車バス停標柱のバス停標柱ID及び降車バス停標柱のバス停標柱IDによる検索
     *
     * @param fromBusStopPoleIdList 乗車バス停標柱のバス停標柱IDのリスト（必須）
     * @param toBusStopPoleIdList 降車バス停標柱のバス停標柱IDのリスト（必須）
     */
    class ByFromToBusStopPoleId(fromBusStopPoleIdList: List<StationId>, toBusStopPoleIdList: List<StationId>) :
        BusRoutePatternFareArgument(
            fromBusStopPoleList = toBusStopPoleIdList.map { it.id },
            toBusStopPoleList = fromBusStopPoleIdList.map { it.id })
}
