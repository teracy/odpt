package com.github.teracy.odpt.core.v2.train.response

import com.github.teracy.odpt.model.*
import com.squareup.moshi.Json

/**
 * v2版列車時刻表情報APIレスポンス
 */
data class OdptTrainTimetable(
    /**
     * 固有識別子（ucode）
     */
    @field:Json(name = "@id")
    val id: String,
    /**
     * 固有識別子（列車時刻表情報ID）
     */
    @field:Json(name = "owl:sameAs")
    val sameAs: String,
    /**
     * データ生成日時（ISO8601 日付時刻形式）
     */
    @field:Json(name = "dc:date")
    val date: String,
    /**
     * 列車番号
     */
    @field:Json(name = "odpt:trainNumber")
    val trainNumber: String,
    /**
     * 路線のID
     * @see OdptRailway.sameAs
     */
    @field:Json(name = "odpt:railway")
    val railway: String,
    /**
     * 列車のID（列車ロケーション情報ID）
     * @see OdptTrain.sameAs
     */
    @field:Json(name = "odpt:train")
    val train: String,
    /**
     * 運行会社のID
     */
    @field:Json(name = "odpt:operator")
    val operator: String,
    /**
     * 列車種別のID
     */
    @field:Json(name = "odpt:trainType")
    val trainType: String,
    /**
     * 方面を表すID
     */
    @field:Json(name = "odpt:railDirection")
    val railDirection: String,
    /**
     * 列車の始発駅のID。他社線始発の場合のみ格納する
     * @see OdptStation.sameAs
     */
    @field:Json(name = "odpt:startingStation")
    val startingStation: String?,
    /**
     * 列車の終着駅のID
     * @see OdptStation.sameAs
     */
    @field:Json(name = "odpt:terminalStation")
    val terminalStation: String,
    /**
     * 車両の所属会社のID（判明する場合のみ格納）
     */
    @field:Json(name = "odpt:trainOwner")
    val trainOwner: String?,
    /**
     * 詳細情報リスト（平日）
     */
    @field:Json(name = "odpt:weekdays")
    val weekdayTrainTimetableObjectList: List<OdptTrainTimetableObject>?,
    /**
     * 詳細情報リスト（平日）
     */
    @field:Json(name = "odpt:saturdays")
    val saturdayTrainTimetableObjectList: List<OdptTrainTimetableObject>?,
    /**
     * 詳細情報リスト（平日）
     */
    @field:Json(name = "odpt:holidays")
    val holidayTrainTimetableObjectList: List<OdptTrainTimetableObject>?
) {
    /**
     * 固有識別子を表す列車時刻表情報ID
     */
    val trainTimetableId: TrainTimetableId
        get() = TrainTimetableId(sameAs)
    /**
     * 路線ID
     */
    val railwayId: RailwayId
        get() = RailwayId(railway)
    /**
     * 列車ID
     */
    val trainId: TrainId
        get() = TrainId(train)
    /**
     * 運行会社の事業者ID
     */
    val operatorId: OperatorId
        get() = OperatorId(operator)
    /**
     * 列車種別ID
     */
    val trainTypeId: TrainTypeId
        get() = TrainTypeId(trainType)
    /**
     * 鉄道方面ID
     */
    val railDirectionId: RailDirectionId
        get() = RailDirectionId(railDirection)
    /**
     * 列車の始発駅の駅ID。他社線始発の場合のみ格納する
     */
    val startingStationId: StationId?
        get() = startingStation?.let { StationId(it) }
    /**
     * 列車の終着駅の駅ID
     */
    val terminalStationId: StationId
        get() = StationId(terminalStation)
    /**
     * 車両の所属会社ID（判明する場合のみ格納）
     */
    val trainOwnerId: TrainOwnerId?
        get() = trainOwner?.let { TrainOwnerId(it) }
}
