package com.github.teracy.odpt.core.v4.train.response

import com.github.teracy.odpt.model.*
import com.squareup.moshi.Json

/**
 * v4版列車ロケーション情報APIレスポンス
 */
data class OdptTrain(
    /**
     * 固有識別子（ucode又はuuid）
     */
    @field:Json(name = "@id")
    val id: String,
    /**
     * データ生成日時（ISO8601 日付時刻形式）
     */
    @field:Json(name = "dc:date")
    val date: String,
    /**
     * データ保証期限（ISO8601 日付時刻形式）
     */
    @field:Json(name = "dct:valid")
    val valid: String?,
    /**
     * 固有識別子（列車ID）
     */
    @field:Json(name = "owl:sameAs")
    val sameAs: String,
    /**
     * 運行会社のID
     */
    @field:Json(name = "odpt:operator")
    val operator: String,
    /**
     * 鉄道路線のID
     * @see OdptRailway.sameAs
     */
    @field:Json(name = "odpt:railway")
    val railway: String,
    /**
     * 方面を表すID
     * @see OdptRailDirection.sameAs
     */
    @field:Json(name = "odpt:railDirection")
    val railDirection: String?,
    /**
     * 列車番号
     */
    @field:Json(name = "odpt:trainNumber")
    val trainNumber: String,
    /**
     * 列車種別のID
     * @see OdptTrainType.sameAs
     */
    @field:Json(name = "odpt:trainType")
    val trainType: String?,
    /**
     * 編成の名称・愛称のリスト（多言語対応）
     */
    @field:Json(name = "odpt:trainName")
    val trainNameMap: Map<String, String>?,
    /**
     * 列車が出発した駅のID
     * @see OdptStation.sameAs
     */
    @field:Json(name = "odpt:fromStation")
    val fromStation: String?,
    /**
     * 列車が向かっている駅のID
     * @see OdptStation.sameAs
     */
    @field:Json(name = "odpt:toStation")
    val toStation: String?,
    /**
     * 列車の始発駅のIDのリスト
     * @see OdptStation.sameAs
     */
    @field:Json(name = "odpt:originStation")
    val originStationList: List<String>?,
    /**
     * 列車の終着駅(行先駅)のIDのリスト
     * @see OdptStation.sameAs
     */
    @field:Json(name = "odpt:destinationStation")
    val destinationStationList: List<String>?,
    /**
     * 列車の経由駅のIDのリスト
     * @see OdptStation.sameAs
     */
    @field:Json(name = "odpt:viaStation")
    val viaStationList: List<String>?,
    /**
     * 列車の経由路線のIDのリスト
     * @see OdptRailway.sameAs
     */
    @field:Json(name = "odpt:viaRailway")
    val viaRailwayList: List<String>?,
    /**
     * 車両の所属会社のID
     */
    @field:Json(name = "odpt:trainOwner")
    val trainOwner: String?,
    /**
     * 路線内の列車順序
     */
    @field:Json(name = "odpt:index")
    val index: Int?,
    /**
     * 遅延時間（秒）
     */
    @field:Json(name = "odpt:delay")
    val delay: Int?,
    /**
     * 車両数
     */
    @field:Json(name = "odpt:carComposition")
    val carComposition: Int?,
    /**
     * その他プロパティとして定義されていない注釈情報の自然言語による記載（多言語対応）
     */
    @field:Json(name = "odpt:note")
    val note: Map<String, String>?
) {
    /**
     * 固有識別子を表す列車ID
     */
    val trainId: TrainId
        get() = TrainId(sameAs)
    /**
     * 運行会社の事業者ID
     */
    val operatorId: OperatorId
        get() = OperatorId(operator)
    /**
     * 鉄道路線ID
     */
    val railwayId: RailwayId
        get() = RailwayId(railway)
    /**
     * 鉄道方面ID
     */
    val railDirectionId: RailDirectionId?
        get() = railDirection?.let { RailDirectionId(it) }
    /**
     * 列車種別ID
     */
    val trainTypeId: TrainTypeId?
        get() = trainType?.let { TrainTypeId(it) }
    /**
     * 列車が出発した駅の駅ID
     */
    val fromStationId: StationId?
        get() = fromStation?.let { StationId(it) }
    /**
     * 	列車が向かっている駅の駅ID
     */
    val toStationId: StationId?
        get() = toStation?.let { StationId(it) }
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
     * 車両の所属会社の事業者ID
     */
    val trainOwnerOperatorId: OperatorId?
        get() = trainOwner?.let { OperatorId(it) }
}
