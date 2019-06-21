package com.github.teracy.odpt.core.v4.common.request

import com.github.teracy.odpt.model.OperatorId

/**
 * 事業者情報検索引数
 */
sealed class OperatorArgument(
    /**
     * 固有識別子(ucode)のリスト
     */
    val idList: List<String>? = null,
    /**
     * 固有識別子別名（事業者ID）のリスト
     */
    val sameAsList: List<String>? = null
) {
    /**
     * 固有識別子による検索
     *
     * @param idList 固有識別子のリスト
     */
    class ById(idList: List<String>) :
        OperatorArgument(idList = idList)

    /**
     * 事業者IDによる検索
     *
     * @param operatorIdList 事業者IDのリスト
     */
    class ByOperatorId(operatorIdList: List<OperatorId>) :
        OperatorArgument(sameAsList = operatorIdList.map { it.id })
}
