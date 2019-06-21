package com.github.teracy.odpt.core.v4.train.request

import com.github.teracy.odpt.model.OperatorId
import com.github.teracy.odpt.model.TrainTypeId

/**
 * 列車種別情報検索引数
 */
sealed class TrainTypeArgument(
    /**
     * 固有識別子(ucode)のリスト
     */
    val idList: List<String>? = null,
    /**
     * 固有識別子別名（列車種別情報ID）のリスト
     */
    val sameAsList: List<String>? = null,
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
        TrainTypeArgument(idList = idList)

    /**
     * 列車種別情報IDによる検索
     *
     * @param trainTypeIdList 列車種別情報IDのリスト
     */
    class ByTrainTypeId(trainTypeIdList: List<TrainTypeId>) :
        TrainTypeArgument(sameAsList = trainTypeIdList.map { it.id })

    /**
     * 事業者IDによる検索
     *
     * @param operatorIdList 事業者IDのリスト
     */
    class ByOperatorId(operatorIdList: List<OperatorId>) :
        TrainTypeArgument(operatorList = operatorIdList.map { it.id })
}
