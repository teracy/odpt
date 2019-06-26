package com.github.teracy.odpt.core.v2.train.request

import com.github.teracy.odpt.model.*

/**
 * 列車時刻表情報検索引数
 */
sealed class TrainTimetableArgument(
    /**
     * 固有識別子(ucode)
     */
    val id: String? = null,
    /**
     * 固有識別子（列車時刻表情報ID）
     */
    val sameAs: String? = null,
    /**
     *  列車番号
     */
    val trainNumber: String? = null,
    /**
     *  路線ID
     */
    val railway: String? = null,
    /**
     * 運行会社の事業者ID
     */
    val operator: String? = null,
    /**
     *  列車種別ID
     */
    val trainType: String? = null,
    /**
     *  鉄道方面ID
     */
    val railDirection: String? = null,
    /**
     *  列車の始発駅の駅ID
     */
    val startingStation: String? = null,
    /**
     *  列車の終着駅の駅ID
     */
    val terminalStation: String? = null,
    /**
     *  車両の所属会社のID
     */
    val trainOwner: String? = null,
    /**
     *  列車ID
     */
    val train: String? = null
) {
    /**
     * 固有識別子による検索
     *
     * @param id 固有識別子(ucode)
     */
    class ById(id: String) : TrainTimetableArgument(id = id)

    /**
     * 列車時刻表情報IDによる検索
     *
     * @param trainTimetableId 列車時刻表情報ID
     */
    class ByTrainTimetableId(trainTimetableId: TrainTimetableId) : TrainTimetableArgument(sameAs = trainTimetableId.id)

    /**
     * 列車番号による検索
     *
     * @param railwayId 鉄道路線ID（必須）
     * @param trainNumber 列車番号（必須）
     */
    class ByTrainNumber(railwayId: RailwayId, trainNumber: String) :
        TrainTimetableArgument(railway = railwayId.id, trainNumber = trainNumber)

    /**
     * 列車IDによる検索
     *
     * @param trainId 列車ID
     */
    class ByTrainId(trainId: TrainId) :
        TrainTimetableArgument(train = trainId.id)

    /**
     * 事業者IDによる検索
     *
     * @param operatorId 事業者ID（必須）
     * @param railDirectionId 鉄道方面ID（任意）
     * @param trainTypeId 列車種別ID（任意）
     * @param trainOwnerId 列車所属会社ID（任意）
     * @param startingStationId 列車の始発駅の駅ID（任意）。他社線始発の場合のみ指定可能
     * @param terminalStationId 列車の終着駅の駅ID（任意）
     */
    class ByOperatorId(
        operatorId: OperatorId,
        railDirectionId: RailDirectionId? = null,
        trainTypeId: TrainTypeId? = null,
        trainOwnerId: TrainOwnerId? = null,
        startingStationId: StationId? = null,
        terminalStationId: StationId? = null
    ) :
        TrainTimetableArgument(
            operator = operatorId.id,
            railDirection = railDirectionId?.id,
            trainType = trainTypeId?.id,
            trainOwner = trainOwnerId?.id,
            startingStation = startingStationId?.id,
            terminalStation = terminalStationId?.id
        )

    /**
     * 鉄道路線IDによる検索
     *
     * @param railwayId 鉄道路線ID（必須）
     * @param railDirectionId 鉄道方面ID（任意）
     * @param trainTypeId 列車種別ID（任意）
     * @param trainOwnerId 列車所属会社ID（任意）
     * @param startingStationId 列車の始発駅の駅ID（任意）。他社線始発の場合のみ指定可能
     * @param terminalStationId 列車の終着駅の駅ID（任意）
     */
    class ByRailwayId(
        railwayId: RailwayId,
        railDirectionId: RailDirectionId? = null,
        trainTypeId: TrainTypeId? = null,
        trainOwnerId: TrainOwnerId? = null,
        startingStationId: StationId? = null,
        terminalStationId: StationId? = null
    ) :
        TrainTimetableArgument(
            railway = railwayId.id,
            railDirection = railDirectionId?.id,
            trainType = trainTypeId?.id,
            trainOwner = trainOwnerId?.id,
            startingStation = startingStationId?.id,
            terminalStation = terminalStationId?.id
        )
}
