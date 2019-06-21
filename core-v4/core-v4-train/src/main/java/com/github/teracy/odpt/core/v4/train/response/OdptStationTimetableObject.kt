package com.github.teracy.odpt.core.v4.train.response

import com.github.teracy.odpt.model.*
import com.squareup.moshi.Json

/**
 * v4版駅時刻表情報APIレスポンス詳細
 */
data class OdptStationTimetableObject(
    /**
     * 到着時刻（ISO8601 時刻形式）
     */
    @field:Json(name = "odpt:arrivalTime")
    val arrivalTime: String?,
    /**
     * 出発時間（ISO8601 時刻形式）
     */
    @field:Json(name = "odpt:departureTime")
    val departureTime: String?,
    /**
     * 始発駅のIDのリスト
     * @see OdptStation.sameAs
     */
    @field:Json(name = "odpt:originStation")
    val originStationList: List<String>?,
    /**
     * 終着駅(行先駅)のIDのリスト
     * @see OdptStation.sameAs
     */
    @field:Json(name = "odpt:destinationStation")
    val destinationStationList: List<String>?,
    /**
     * 経由駅のIDのリスト
     * @see OdptStation.sameAs
     */
    @field:Json(name = "odpt:viaStation")
    val viaStationList: List<String>?,
    /**
     * 経由路線のIDのリスト
     * @see OdptRailway.sameAs
     */
    @field:Json(name = "odpt:viaRailway")
    val viaRailwayList: List<String>?,
    /**
     * 到着または出発する列車のID
     * @see OdptTrain.sameAs
     */
    @field:Json(name = "odpt:train")
    val train: String?,
    /**
     * 列車番号、運行管理に用いられる運用番号
     */
    @field:Json(name = "odpt:trainNumber")
    val trainNumber: String?,
    /**
     * 列車種別のID
     */
    @field:Json(name = "odpt:trainType")
    val trainType: String?,
    /**
     * 編成の名称・愛称のリスト（多言語対応）
     */
    @field:Json(name = "odpt:trainName")
    val trainName: List<Map<String, String>>?,
    /**
     * 車両の所属会社の事業者ID
     */
    @field:Json(name = "odpt:trainOwner")
    val trainOwner: String?,
    /**
     * 最終電車の場合true。最終電車でない場合は省略
     */
    @field:Json(name = "odpt:isLast")
    private val _isLast: Boolean?,
    /**
     * 始発駅の場合true。始発駅ではない場合は省略
     */
    @field:Json(name = "odpt:isOrigin")
    private val _isOrigin: Boolean?,
    /**
     * 列車が到着するプラットフォームの番号
     */
    @field:Json(name = "odpt:platformNumber")
    val platformNumber: Int?,
    /**
     * 列車が到着 又は出発するプラットフォームの名称（多言語対応）
     */
    @field:Json(name = "odpt:platformName")
    val platformNameMap: Map<String, String>?,
    /**
     * 車両数（駅に停車する車両数が列車毎に異なる場合に格納する）
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
     * 始発駅の駅IDのリスト
     */
    val originStationIdList: List<StationId>?
        get() = originStationList?.map { StationId(it) }
    /**
     * 終着駅(行先駅)の駅IDのリスト
     */
    val destinationStationIdList: List<StationId>?
        get() = destinationStationList?.map { StationId(it) }
    /**
     * 経由駅の駅IDのリスト
     */
    val viaStationIdList: List<StationId>?
        get() = viaStationList?.map { StationId(it) }
    /**
     * 経由路線の鉄道路線IDのリスト
     */
    val viaRailwayIdList: List<RailwayId>?
        get() = viaRailwayList?.map { RailwayId(it) }
    /**
     * 到着または出発する列車ID
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
     * 最終電車の場合true
     */
    val isLast: Boolean
        get() = _isLast ?: false
    /**
     * 始発駅の場合true
     */
    val isOrigin: Boolean
        get() = _isOrigin ?: false
}
