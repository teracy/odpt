package com.github.teracy.odpt.core.v2.train.request

import com.github.teracy.odpt.model.OperatorId
import com.github.teracy.odpt.model.RailwayId

/**
 * 列車運行情報検索引数
 */
sealed class TrainInformationArgument(
    /**
     * 固有識別子(ucode)
     */
    val id: String? = null,
    /**
     * 運行会社の事業者ID
     */
    val operator: String? = null,
    /**
     * 路線ID
     */
    val railway: String? = null
) {
    /**
     * 固有識別子による検索
     *
     * @param id 固有識別子(ucode)
     */
    class ById(id: String) : TrainInformationArgument(id = id)

    /**
     * 事業者IDによる検索
     *
     * @param operatorId 事業者ID
     */
    class ByOperatorId(operatorId: OperatorId) : TrainInformationArgument(operator = operatorId.id)

    /**
     * 鉄道路線IDによる検索
     *
     * @param railwayId 鉄道路線ID
     */
    class ByRailwayId(railwayId: RailwayId) : TrainInformationArgument(railway = railwayId.id)
}
