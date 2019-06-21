package com.github.teracy.odpt.core.v4.train.request

import com.github.teracy.odpt.model.OperatorId
import com.github.teracy.odpt.model.RailwayId

/**
 * 鉄道路線情報検索引数
 */
sealed class RailwayArgument(
    /**
     * 固有識別子(ucode)のリスト
     */
    val idList: List<String>? = null,
    /**
     * 固有識別子別名（鉄道路線ID）のリスト
     */
    val sameAsList: List<String>? = null,
    /**
     * 運行系統名（完全一致）のリスト
     */
    val titleList: List<String>? = null,
    /**
     * 運行会社の事業者IDのリスト
     */
    val operatorList: List<String>? = null,
    /**
     * 路線コード（完全一致）のリスト
     */
    val lineCodeList: List<String>? = null
) {
    /**
     * 固有識別子による検索
     *
     * @param idList 固有識別子のリスト
     */
    class ById(idList: List<String>) :
        RailwayArgument(idList = idList)

    /**
     * 鉄道路線IDによる検索
     *
     * @param railwayIdList 鉄道路線IDのリスト
     */
    class ByRailwayId(railwayIdList: List<RailwayId>) :
        RailwayArgument(sameAsList = railwayIdList.map { it.id })

    /**
     * 事業者IDによる検索
     *
     * @param operatorIdList 事業者IDのリスト
     */
    class ByOperatorId(operatorIdList: List<OperatorId>) :
        RailwayArgument(operatorList = operatorIdList.map { it.id })

    /**
     * 運行系統名による検索
     *
     * @param railwayNameList 運行系統名（完全一致）のリスト（必須）
     * @param operatorId 事業者ID（任意。同じ運行系統名が存在する場合の絞り込み条件として利用）
     */
    class ByRailwayName(railwayNameList: List<String>, operatorId: OperatorId? = null) :
        RailwayArgument(titleList = railwayNameList, operatorList = operatorId?.let { listOf(it.id) })

    /**
     * 路線コードによる検索
     *
     * @param lineCodeList 路線コード（完全一致）のリスト（必須）
     * @param operatorId 事業者ID（任意。路線コードが存在する場合の絞り込み条件として利用）
     */
    class ByLineCode(lineCodeList: List<String>, operatorId: OperatorId? = null) :
        RailwayArgument(lineCodeList = lineCodeList, operatorList = operatorId?.let { listOf(it.id) })
}
