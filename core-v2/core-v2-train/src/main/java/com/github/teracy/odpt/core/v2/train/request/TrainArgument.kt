package com.github.teracy.odpt.core.v2.train.request

import com.github.teracy.odpt.model.*

/**
 * 列車ロケーション情報検索引数
 * NOTE: 「遅れ」について、遅れが5分以内の列車（delay=0）は検索できるが、5分以上遅れている列車はピンポイントで遅れを秒数で指定する必要があり現実的でないため省略している（クライアント側に実装を残している）
 */
sealed class TrainArgument(
    /**
     * 固有識別子(ucode)
     */
    val id: String? = null,
    /**
     * 固有識別子（列車ID）
     */
    val sameAs: String? = null,
    /**
     * 列車番号
     */
    val trainNumber: String? = null,
    /**
     * 列車種別ID
     */
    val trainType: String? = null,
    /**
     * 鉄道路線ID
     */
    val railway: String? = null,
    /**
     * 列車所属会社ID
     */
    val trainOwner: String? = null,
    /**
     * 鉄道方面ID
     */
    val railDirection: String? = null,
    /**
     * 列車の始発駅の駅ID
     */
    val startingStation: String? = null,
    /**
     * 列車の終着駅の駅ID
     */
    val terminalStation: String? = null,
    /**
     * 列車が出発した駅の駅ID
     */
    val fromStation: String? = null,
    /**
     * 列車が向かっている駅の駅ID
     */
    val toStation: String? = null
) {
    /**
     * 固有識別子による検索
     *
     * @param id 固有識別子(ucode)
     */
    class ById(id: String) : TrainArgument(id = id)

    /**
     * 列車IDによる検索
     *
     * @param trainId 列車ID
     */
    class ByTrainId(trainId: TrainId) : TrainArgument(sameAs = trainId.id)

    /**
     * 列車番号による検索（固有識別子または列車IDによる検索と同等）
     *
     * @param railwayId 鉄道路線ID（必須）
     * @param trainNumber 列車番号（必須）
     */
    class ByTrainNumber(railwayId: RailwayId, trainNumber: String) :
        TrainArgument(railway = railwayId.id, trainNumber = trainNumber)

    /**
     * 路線IDによる検索（対象路線の列車を現在の位置を問わず検索する場合）
     *
     * @param railwayId 鉄道路線ID（必須）
     * @param railDirectionId 鉄道方面ID（任意）
     * @param trainTypeId 列車種別ID（任意）
     * @param trainOwnerId 列車所属会社ID（任意）
     * @param startingStationId 列車の始発駅の駅ID（任意）
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
        TrainArgument(
            railway = railwayId.id,
            railDirection = railDirectionId?.id,
            trainType = trainTypeId?.id,
            trainOwner = trainOwnerId?.id,
            startingStation = startingStationId?.id,
            terminalStation = terminalStationId?.id
        )

    /**
     * 列車が出発した駅の駅IDによる検索（対象路線の列車を現在の列車位置で検索する場合。発駅基準）
     *
     * @param railwayId 鉄道路線ID（必須）
     * @param fromStationId 列車が出発した駅の駅ID（必須）
     * @param railDirectionId 鉄道方面ID（任意）
     * @param trainTypeId 列車種別ID（任意）
     * @param trainOwnerId 列車所属会社ID（任意）
     */
    class ByFromStationId(
        railwayId: RailwayId,
        fromStationId: StationId,
        railDirectionId: RailDirectionId? = null,
        trainTypeId: TrainTypeId? = null,
        trainOwnerId: TrainOwnerId? = null
    ) :
        TrainArgument(
            railway = railwayId.id,
            fromStation = fromStationId.id,
            railDirection = railDirectionId?.id,
            trainType = trainTypeId?.id,
            trainOwner = trainOwnerId?.id
        )

    /**
     * 列車が向かっている駅の駅IDによる検索（対象路線の列車を現在の列車位置で検索する場合。着駅基準）
     *
     * @param railwayId 鉄道路線ID（必須）
     * @param toStationId 列車が向かっている駅の駅ID（必須）
     * @param railDirectionId 鉄道方面ID（任意）
     * @param trainTypeId 列車種別ID（任意）
     * @param trainOwnerId 列車所属会社ID（任意）
     */
    class ByToStationId(
        railwayId: RailwayId,
        toStationId: StationId,
        railDirectionId: RailDirectionId? = null,
        trainTypeId: TrainTypeId? = null,
        trainOwnerId: TrainOwnerId? = null
    ) :
        TrainArgument(
            railway = railwayId.id,
            toStation = toStationId.id,
            railDirection = railDirectionId?.id,
            trainType = trainTypeId?.id,
            trainOwner = trainOwnerId?.id
        )

    /**
     * 列車が出発した駅の駅IDと列車が向かっている駅の駅IDによる検索（対象路線の列車を現在の列車位置で検索する場合。区間基準）
     *
     * @param railwayId 鉄道路線ID（必須）
     * @param fromStationId 列車が出発した駅の駅ID（必須）
     * @param toStationId 列車が向かっている駅の駅ID（必須）
     * @param trainTypeId 列車種別ID（任意）
     * @param trainOwnerId 列車所属会社ID（任意）
     */
    class ByFromToStationId(
        railwayId: RailwayId,
        fromStationId: StationId,
        toStationId: StationId,
        trainTypeId: TrainTypeId? = null,
        trainOwnerId: TrainOwnerId? = null
    ) :
        TrainArgument(
            railway = railwayId.id,
            fromStation = fromStationId.id,
            toStation = toStationId.id,
            trainType = trainTypeId?.id,
            trainOwner = trainOwnerId?.id
        )
}
