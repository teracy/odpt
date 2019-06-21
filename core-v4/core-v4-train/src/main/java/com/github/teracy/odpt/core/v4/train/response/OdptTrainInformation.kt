package com.github.teracy.odpt.core.v4.train.response

import com.github.teracy.odpt.model.*
import com.squareup.moshi.Json

/**
 * v4版列車運行情報APIレスポンス
 */
data class OdptTrainInformation(
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
     * 有効期限（ISO8601 日付時刻形式）
     */
    @field:Json(name = "dct:valid")
    val valid: String?,
    /**
     * 固有識別子（列車運行情報ID）
     */
    @field:Json(name = "owl:sameAs")
    val sameAs: String,
    /**
     * 発生時刻（ISO8601 日付時刻形式）
     */
    @field:Json(name = "odpt:timeOfOrigin")
    val timeOfOrigin: String,
    /**
     * 運行会社のID
     */
    @field:Json(name = "odpt:operator")
    val operator: String,
    /**
     * 発生路線のID
     * @see OdptRailway.sameAs
     */
    @field:Json(name = "odpt:railway")
    val railway: String?,
    /**
     * 平常時は省略。運行情報が存在する場合は「運行情報あり」を格納。遅延などの情報を取得可能な場合は、「遅延」等のテキストを格納（多言語対応）
     */
    @field:Json(name = "odpt:trainInformationStatus")
    val trainInformationStatus: Map<String, String>?,
    /**
     * 運行情報テキスト（多言語対応）
     */
    @field:Json(name = "odpt:trainInformationText")
    val trainInformationText: Map<String, String>?,
    /**
     * 運行情報の適用される方向のID。取得不可能な場合は省略
     * @see OdptRailDirection.sameAs
     */
    @field:Json(name = "odpt:railDirection")
    val railDirection: String?,
    /**
     * 発生エリア。取得不可能な場合は省略（多言語対応）
     */
    @field:Json(name = "odpt:trainInformationArea")
    val trainInformationArea: Map<String, String>?,
    /**
     * 鉄道種類。取得不可能な場合は省略（多言語対応）
     */
    @field:Json(name = "odpt:trainInformationKind")
    val trainInformationKind: Map<String, String>?,
    /**
     * 発生場所起点の駅。取得不可能な場合は省略
     * @see OdptStation.sameAs
     */
    @field:Json(name = "odpt:stationFrom")
    val fromStation: String?,
    /**
     * 発生場所終点の駅。取得不可能な場合は省略
     * @see OdptStation.sameAs
     */
    @field:Json(name = "odpt:stationTo")
    val toStation: String?,
    /**
     * 発生区間。取得不可能な場合は省略（多言語対応）
     */
    @field:Json(name = "odpt:trainInformationRange")
    val trainInformationRange: Map<String, String>?,
    /**
     * 発生理由。取得不可能な場合は省略（多言語対応）
     */
    @field:Json(name = "odpt:trainInformationCause")
    val trainInformationCause: Map<String, String>?,
    /**
     * 振替路線の鉄道路線IDのリスト
     * @see OdptRailway.sameAs
     */
    @field:Json(name = "odpt:transferRailways")
    val transferRailwayList: List<String>?,
    /**
     * 復旧見込み時刻。ただし配信されない場合が多い（ISO8601 日付時刻形式）
     */
    @field:Json(name = "odpt:resumeEstimate")
    val resumeEstimate: String?
) {
    /**
     * 固有識別子を表す列車運行情報ID
     */
    val trainInformationId: TrainInformationId
        get() = TrainInformationId(sameAs)
    /**
     * 運行会社の事業者ID
     */
    val operatorId: OperatorId
        get() = OperatorId(operator)
    /**
     * 発生路線の路線ID
     */
    val railwayId: RailwayId?
        get() = railway?.let { RailwayId(it) }
    /**
     * 運行情報の適用される方向の鉄道方面ID。取得不可能な場合は省略
     */
    val railDirectionId: RailDirectionId?
        get() = railDirection?.let { RailDirectionId(it) }
    /**
     * 発生場所起点の駅ID。取得不可能な場合は省略
     */
    val fromStationId: StationId?
        get() = fromStation?.let { StationId(it) }
    /**
     * 発生場所終点の駅ID。取得不可能な場合は省略
     */
    val toStationId: StationId?
        get() = toStation?.let { StationId(it) }
    /**
     * 振替路線の鉄道路線IDのリスト
     */
    val transferRailwayIdList: List<RailwayId>?
        get() = transferRailwayList?.map { RailwayId(it) }
}
