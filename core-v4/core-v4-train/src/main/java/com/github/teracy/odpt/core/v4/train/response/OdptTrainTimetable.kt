package com.github.teracy.odpt.core.v4.train.response

import com.github.teracy.odpt.model.*
import com.squareup.moshi.Json

/**
 * v4版列車時刻表情報APIレスポンス
 */
data class OdptTrainTimetable(
    /**
     * 固有識別子（ucode）
     */
    @field:Json(name = "@id")
    val id: String,
    /**
     * データ生成日時（ISO8601 日付時刻形式）
     */
    @field:Json(name = "dc:date")
    val date: String,
    /**
     * ダイヤ改正日（ISO8601 日付時刻形式）
     */
    @field:Json(name = "dct:issued")
    val issued: String?,
    /**
     * データの保証期限（ISO8601 日付時刻形式）
     */
    @field:Json(name = "dct:valid")
    val valid: String?,
    /**
     * 固有識別子（列車時刻表情報ID）
     */
    @field:Json(name = "owl:sameAs")
    val sameAs: String,
    /**
     * 運行会社のID
     */
    @field:Json(name = "odpt:operator")
    val operator: String,
    /**
     * 路線のID
     */
    @field:Json(name = "odpt:railway")
    val railway: String,
    /**
     * 進行方向を表す方面のID
     */
    @field:Json(name = "odpt:railDirection")
    val railDirection: String?,
    /**
     * 運行を行う曜日・日付情報のID
     */
    @field:Json(name = "odpt:calendar")
    val calendar: String?,
    /**
     * 列車のID
     */
    @field:Json(name = "odpt:train")
    val train: String?,
    /**
     * 列車番号
     */
    @field:Json(name = "odpt:trainNumber")
    val trainNumber: String,
    /**
     * 列車種別のID
     */
    @field:Json(name = "odpt:trainType")
    val trainType: String?,
    /**
     * 編成の名称・愛称のリスト（多言語対応）
     */
    @field:Json(name = "odpt:trainName")
    val trainNameMap: Map<String, String>?,
    /**
     * 車両の所属会社のID
     */
    @field:Json(name = "odpt:trainOwner")
    val trainOwner: String?,
    /**
     * 列車の始発駅のIDのリスト
     */
    @field:Json(name = "odpt:originStation")
    val originStationList: List<String>?,
    /**
     * 列車の終着駅(行先駅)のIDのリスト
     */
    @field:Json(name = "odpt:destinationStation")
    val destinationStationList: List<String>?,
    /**
     * 列車の経由駅のIDのリスト
     */
    @field:Json(name = "odpt:viaStation")
    val viaStationList: List<String>?,
    /**
     * 列車の経由路線のIDのリスト
     */
    @field:Json(name = "odpt:viaRailway")
    val viaRailwayList: List<String>?,
    /**
     * 同一の列車の列車時刻表が複数に分かれている場合、直前の列車時刻表のIDのリスト
     */
    @field:Json(name = "odpt:previousTrainTimetable")
    val previousTrainTimetableList: List<String>?,
    /**
     * 同一の列車の列車時刻表が複数に分かれている場合、直後の列車時刻表のIDのリスト
     */
    @field:Json(name = "odpt:nextTrainTimetable")
    val nextTrainTimetableList: List<String>?,
    /**
     * 詳細情報リスト
     */
    @field:Json(name = "odpt:trainTimetableObject")
    val trainTimetableObject: List<OdptTrainTimetableObject>,

    @field:Json(name = "odpt:needExtraFee")
    private val _needsExtraFee: Boolean?,

    /**
     * その他プロパティとして定義されていない注釈情報の自然言語による記載（多言語対応）
     */
    @field:Json(name = "odpt:note")
    val note: Map<String, String>?
) {
    /**
     * 固有識別子を表す列車時刻表ID
     */
    val trainTimetableId: TrainTimetableId
        get() = TrainTimetableId(sameAs)
    /**
     * 運行会社の事業者ID
     */
    val operatorId: OperatorId
        get() = OperatorId(operator)
    /**
     * 路線ID
     */
    val railwayId: RailwayId
        get() = RailwayId(railway)
    /**
     * 進行方向の鉄道方面ID
     */
    val railDirectionId: RailDirectionId?
        get() = railDirection?.let { RailDirectionId(it) }
    /**
     * 運行を行う曜日・日付を表す曜日・日付情報ID
     */
    val calendarId: CalendarId?
        get() = calendar?.let { CalendarId(it) }
    /**
     * 列車ID
     */
    val trainId: TrainId?
        get() = train?.let { TrainId(it) }
    /**
     * 列車種別ID
     */
    val trainTypeId: TrainTypeId?
        get() = trainType?.let { TrainTypeId(it) }
    /**
     * 車両の所属会社の事業者ID
     */
    val trainOwnerOperatorId: OperatorId?
        get() = trainOwner?.let { OperatorId(it) }
    /**
     * 列車の始発駅の駅IDのリスト
     */
    val originStationIdList: List<StationId>?
        get() = originStationList?.map { StationId(it) }
    /**
     * 列車の終着駅(行先駅)の駅IDのリスト
     */
    val destinationStationIdList: List<StationId>?
        get() = destinationStationList?.map { StationId(it) }
    /**
     * 列車の経由駅の駅IDのリスト
     */
    val viaStationIdList: List<StationId>?
        get() = viaStationList?.map { StationId(it) }
    /**
     * 列車の経由路線の路線IDのリスト
     */
    val viaRailwayIdList: List<RailwayId>?
        get() = viaRailwayList?.map { RailwayId(it) }
    /**
     * 同一の列車の列車時刻表が複数に分かれている場合、直前の列車時刻表IDのリスト
     */
    val previousTrainTimetableIdList: List<TrainTimetableId>?
        get() = previousTrainTimetableList?.map { TrainTimetableId(it) }
    /**
     * 同一の列車の列車時刻表が複数に分かれている場合、直後の列車時刻表IDのリスト
     */
    val nextTrainTimetableIdList: List<TrainTimetableId>?
        get() = nextTrainTimetableList?.map { TrainTimetableId(it) }
    /**
     * 乗車券の他に別料金が必要か
     */
    val needsExtraFee: Boolean
        get() = _needsExtraFee ?: false
}
