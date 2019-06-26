package com.github.teracy.odpt.core.v2.train.request

import com.github.teracy.odpt.model.OperatorId
import com.github.teracy.odpt.model.RailwayId
import com.github.teracy.odpt.model.StationId

/**
 * 駅情報検索引数
 */
sealed class StationArgument(
    /**
     * 固有識別子(ucode)
     */
    val id: String? = null,
    /**
     * 固有識別子（駅ID）
     */
    val sameAs: String? = null,
    /**
     * 駅名
     */
    val title: String? = null,
    /**
     * 運行会社の事業者ID
     */
    val operator: String? = null,
    /**
     * 路線ID
     */
    val railway: String? = null,
    /**
     * 駅コード
     */
    val stationCode: String? = null
) {
    /**
     * 固有識別子による検索
     *
     * @param id 固有識別子(ucode)
     */
    class ById(id: String) : StationArgument(id = id)

    /**
     * 駅IDによる検索
     *
     * @param stationId 駅ID
     */
    class ByStationId(stationId: StationId) : StationArgument(sameAs = stationId.id)

    /**
     * 事業者IDによる検索
     *
     * @param operatorId 事業者ID
     */
    class ByOperatorId(operatorId: OperatorId) : StationArgument(operator = operatorId.id)

    /**
     * 鉄道路線IDによる検索
     *
     * @param railwayId 鉄道路線ID
     */
    class ByRailwayId(railwayId: RailwayId) : StationArgument(railway = railwayId.id)

    /**
     * 駅名による検索（部分一致）
     * 任意パラメータが指定されている場合、駅名リストに該当する駅情報の内、更に任意パラメータに該当するもののみ取得する
     *
     * @param stationName 駅名（必須。部分一致なので、例えば「銀座」で検索すると銀座線・丸ノ内線・日比谷線の「銀座」、日比谷線の「東銀座」、有楽町線の「銀座一丁目」が該当する）
     * @param railwayId 鉄道路線ID（任意）
     */
    class ByStationName(stationName: String, railwayId: RailwayId? = null) :
        StationArgument(title = stationName, railway = railwayId?.id)

    /**
     * 駅コードによる検索（前方一致）
     *
     * @param stationCode 駅コード（必須。前方一致なので、例えば「G」で検索すると銀座線の全駅が、「G0」で検索すると「G01（銀座線渋谷）」～「G09（銀座線銀座）」が該当する
     */
    class ByStationCode(stationCode: String) :
        StationArgument(stationCode = stationCode)
}
