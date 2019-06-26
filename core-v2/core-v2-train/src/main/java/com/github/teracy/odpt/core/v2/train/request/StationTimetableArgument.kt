package com.github.teracy.odpt.core.v2.train.request

import com.github.teracy.odpt.model.*

/**
 * 駅時刻表情報検索引数
 */
sealed class StationTimetableArgument(
    /**
     * 固有識別子(ucode)
     */
    val id: String? = null,
    /**
     * 固有識別子（駅時刻表情報ID）
     */
    val sameAs: String? = null,
    /**
     * 駅ID
     */
    val station: String? = null,
    /**
     * 路線ID
     */
    val railway: String? = null,
    /**
     * 運行会社の事業者ID
     */
    val operator: String? = null,
    /**
     * 鉄道方面ID
     */
    val railDirection: String? = null
) {
    /**
     * 固有識別子による検索
     *
     * @param id 固有識別子(ucode)
     */
    class ById(id: String) : StationTimetableArgument(id = id)

    /**
     * 駅時刻表情報IDによる検索
     *
     * @param stationTimetableId 駅時刻表情報ID
     */
    class ByStationTimetableId(stationTimetableId: StationTimetableId) :
        StationTimetableArgument(sameAs = stationTimetableId.id)

    /**
     * 事業者IDによる検索
     *
     * @param operatorId 事業者ID
     */
    class ByOperatorId(operatorId: OperatorId) :
        StationTimetableArgument(operator = operatorId.id)

    /**
     * 駅IDによる検索
     *
     * @param stationId 駅時刻表情報ID（必須）
     * @param railDirectionId 鉄道方面ID（任意）
     */
    class ByStationId(stationId: StationId, railDirectionId: RailDirectionId? = null) :
        StationTimetableArgument(station = stationId.id, railDirection = railDirectionId?.id)

    /**
     * 鉄道路線IDによる検索
     *
     * @param railwayId 鉄道路線ID（必須）
     * @param railDirectionId 鉄道方面ID（任意）
     */
    class ByRailwayId(railwayId: RailwayId, railDirectionId: RailDirectionId? = null) :
        StationTimetableArgument(railway = railwayId.id, railDirection = railDirectionId?.id)
}
