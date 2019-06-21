package com.github.teracy.odpt.core.v4.train.request

import com.github.teracy.odpt.model.OperatorId
import com.github.teracy.odpt.model.RailwayId
import com.github.teracy.odpt.model.StationId

/**
 * 駅情報検索引数
 */
sealed class StationArgument(
    /**
     * 固有識別子(ucode)のリスト
     */
    val idList: List<String>? = null,
    /**
     * 固有識別子別名（駅ID）のリスト
     */
    val sameAsList: List<String>? = null,
    /**
     * 駅名（完全一致）のリスト
     */
    val titleList: List<String>? = null,
    /**
     * 路線IDのリスト
     */
    val railwayList: List<String>? = null,
    /**
     * 運行会社の事業者IDのリスト
     */
    val operatorList: List<String>? = null,
    /**
     * 駅コード（完全一致）のリスト
     */
    val stationCodeList: List<String>? = null
) {
    /**
     * 固有識別子による検索
     *
     * @param idList 固有識別子のリスト
     */
    class ById(idList: List<String>) :
        StationArgument(idList = idList)

    /**
     * 駅IDによる検索
     *
     * @param stationIdList 駅IDのリスト
     */
    class ByStationId(stationIdList: List<StationId>) :
        StationArgument(sameAsList = stationIdList.map { it.id })

    /**
     * 事業者IDによる検索
     *
     * @param operatorIdList 事業者IDのリスト
     */
    class ByOperatorId(operatorIdList: List<OperatorId>) :
        StationArgument(operatorList = operatorIdList.map { it.id })

    /**
     * 路線IDによる検索
     *
     * @param railwayIdList 路線IDのリスト
     */
    class ByRailwayId(railwayIdList: List<RailwayId>) :
        StationArgument(railwayList = railwayIdList.map { it.id })

    /**
     * 駅名による検索
     *
     * @param stationNameList 駅名（完全一致）のリスト（必須）
     * @param operatorId 事業者ID（任意。同じ駅名が存在する場合の絞り込み条件として利用）
     * @param railwayId 路線ID（任意。同じ駅名が存在する場合の絞り込み条件として利用）
     */
    class ByStationName(stationNameList: List<String>, operatorId: OperatorId? = null, railwayId: RailwayId? = null) :
        StationArgument(
            titleList = stationNameList,
            operatorList = operatorId?.let { listOf(it.id) },
            railwayList = railwayId?.let { listOf(it.id) })

    /**
     * 駅コードによる検索
     *
     * @param stationCodeList 駅コード（完全一致）のリスト（必須）
     * @param operatorId 事業者ID（任意。同じ駅コードが存在する場合の絞り込み条件として利用）
     * @param railwayId 路線ID（任意。同じ駅コードが存在する場合の絞り込み条件として利用）
     */
    class ByStationCode(stationCodeList: List<String>, operatorId: OperatorId? = null, railwayId: RailwayId? = null) :
        StationArgument(
            stationCodeList = stationCodeList,
            operatorList = operatorId?.let { listOf(it.id) },
            railwayList = railwayId?.let { listOf(it.id) })
}
