package com.github.teracy.odpt.core.v4.train.request

import com.github.teracy.odpt.model.OperatorId
import com.github.teracy.odpt.model.RailwayId
import com.github.teracy.odpt.model.TrainId

/**
 * 列車ロケーション情報検索引数
 */
sealed class TrainArgument(
    /**
     * 固有識別子(ucode又はuuid)のリスト
     */
    val idList: List<String>? = null,
    /**
     * 固有識別子別名（列車ID）のリスト
     */
    val sameAsList: List<String>? = null,
    /**
     * 運行会社の事業者IDのリスト
     */
    val operatorList: List<String>? = null,
    /**
     * 路線IDのリスト
     */
    val railwayList: List<String>? = null
) {
    /**
     * 固有識別子による検索
     *
     * @param idList 固有識別子のリスト
     */
    class ById(idList: List<String>) :
        TrainArgument(idList = idList)

    /**
     * 列車IDによる検索
     *
     * @param trainIdList 列車IDのリスト
     */
    class ByTrainId(trainIdList: List<TrainId>) :
        TrainArgument(sameAsList = trainIdList.map { it.id })

    /**
     * 事業者IDによる検索
     *
     * @param operatorIdList 事業者IDのリスト
     */
    class ByOperatorId(operatorIdList: List<OperatorId>) :
        TrainArgument(operatorList = operatorIdList.map { it.id })

    /**
     * 路線IDによる検索
     *
     * @param railwayIdList 路線IDのリスト
     */
    class ByRailwayId(railwayIdList: List<RailwayId>) :
        TrainArgument(railwayList = railwayIdList.map { it.id })
}
