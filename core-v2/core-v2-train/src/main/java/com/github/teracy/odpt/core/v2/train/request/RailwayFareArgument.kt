package com.github.teracy.odpt.core.v2.train.request

import com.github.teracy.odpt.model.OperatorId
import com.github.teracy.odpt.model.RailwayFareId
import com.github.teracy.odpt.model.StationId

/**
 * 運賃情報検索引数
 */
sealed class RailwayFareArgument(
    /**
     * 固有識別子(ucode)
     */
    val id: String? = null,
    /**
     * 固有識別子（運賃情報ID）
     */
    val sameAs: String? = null,
    /**
     * 運行会社の事業者ID
     */
    val operator: String? = null,
    /**
     * 駅間の始点駅の駅ID
     */
    val fromStation: String? = null,
    /**
     * 駅間の終点駅の駅ID
     */
    val toStation: String? = null
) {
    /**
     * 固有識別子による検索
     *
     * @param id 固有識別子
     */
    class ById(id: String) : RailwayFareArgument(id = id)

    /**
     * 運賃情報IDによる検索
     *
     * @param railwayFaId 運賃情報ID
     */
    class ByRailwayFareId(railwayFaId: RailwayFareId) : RailwayFareArgument(sameAs = railwayFaId.id)

    /**
     * 事業者IDによる検索
     *
     * @param operatorId 事業者ID
     */
    class ByOperatorId(operatorId: OperatorId) : RailwayFareArgument(operator = operatorId.id)

    /**
     * 駅間の始点駅の駅IDによる検索
     *
     * @param fromStationId 駅間の始点駅の駅ID（必須）
     * @param toStationId 駅間の終点駅の駅ID（任意）
     */
    class ByFromStationId(fromStationId: StationId, toStationId: StationId? = null) :
        RailwayFareArgument(fromStation = fromStationId.id, toStation = toStationId?.id)
}
