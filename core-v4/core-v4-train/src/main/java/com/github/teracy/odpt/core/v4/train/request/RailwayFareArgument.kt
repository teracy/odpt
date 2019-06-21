package com.github.teracy.odpt.core.v4.train.request

import com.github.teracy.odpt.model.OperatorId
import com.github.teracy.odpt.model.RailwayFareId
import com.github.teracy.odpt.model.StationId

/**
 * 鉄道運賃情報検索引数
 */
sealed class RailwayFareArgument(
    /**
     * 固有識別子(ucode)のリスト
     */
    val idList: List<String>? = null,
    /**
     * 固有識別子別名（鉄道運賃情報ID）のリスト
     */
    val sameAsList: List<String>? = null,
    /**
     * 運行会社の事業者IDのリスト
     */
    val operatorList: List<String>? = null,
    /**
     * 駅間の始点駅の駅IDのリスト
     */
    val fromStationList: List<String>? = null,
    /**
     * 駅間の終点駅の駅IDのリスト
     */
    val toStationList: List<String>? = null
) {
    /**
     * 固有識別子による検索
     *
     * @param idList 固有識別子のリスト
     */
    class ById(idList: List<String>) :
        RailwayFareArgument(idList = idList)

    /**
     * 鉄道運賃情報IDによる検索
     *
     * @param railwayFareIdList 鉄道運賃情報IDのリスト
     */
    class ByRailwayFareId(railwayFareIdList: List<RailwayFareId>) :
        RailwayFareArgument(sameAsList = railwayFareIdList.map { it.id })

    /**
     * 事業者IDによる検索
     *
     * @param operatorIdList 事業者IDのリスト
     */
    class ByOperatorId(operatorIdList: List<OperatorId>) :
        RailwayFareArgument(operatorList = operatorIdList.map { it.id })

    /**
     * 駅間の始点駅の駅IDのみによる検索
     *
     * @param fromStationIdList 駅間の始点駅の駅IDのリスト
     */
    class ByFromStationId(fromStationIdList: List<StationId>) :
        RailwayFareArgument(fromStationList = fromStationIdList.map { it.id })

    /**
     * 駅間の終点駅の駅IDのみによる検索
     *
     * @param toStationIdList 駅間の終点駅の駅IDのリスト
     */
    class ByToStationId(toStationIdList: List<StationId>) :
        RailwayFareArgument(toStationList = toStationIdList.map { it.id })

    /**
     * 駅間の始発駅及び終点駅の駅IDによる検索
     *
     * @param fromStationIdList 駅間の始点駅の駅IDのリスト（必須）
     * @param toStationIdList 駅間の終点駅の駅IDのリスト（必須）
     */
    class ByFromToStationId(fromStationIdList: List<StationId>, toStationIdList: List<StationId>) :
        RailwayFareArgument(
            fromStationList = fromStationIdList.map { it.id },
            toStationList = toStationIdList.map { it.id })
}
