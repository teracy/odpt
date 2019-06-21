package com.github.teracy.odpt.core.v4.train.response

import com.github.teracy.odpt.model.OperatorId
import com.github.teracy.odpt.model.RailwayFareId
import com.github.teracy.odpt.model.RailwayId
import com.github.teracy.odpt.model.StationId
import com.squareup.moshi.Json

/**
 * v4版鉄道運賃情報APIレスポンス
 */
data class OdptRailwayFare(
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
     * 運賃改定日（ISO8601 日付時刻形式）
     */
    @field:Json(name = "dct:issued")
    val issued: String?,
    /**
     * データの保証期限（ISO8601 日付時刻形式）
     */
    @field:Json(name = "dct:valid")
    val valid: String?,
    /**
     * 固有識別子（鉄道運賃情報ID）
     */
    @field:Json(name = "owl:sameAs")
    val sameAs: String,
    /**
     * 運行会社のID
     */
    @field:Json(name = "odpt:operator")
    val operator: String,
    /**
     * 駅間の始点駅のID
     * @see OdptStation.sameAs
     */
    @field:Json(name = "odpt:fromStation")
    val fromStation: String,
    /**
     * 駅間の終点駅のID
     * @see OdptStation.sameAs
     */
    @field:Json(name = "odpt:toStation")
    val toStation: String,
    /**
     * 切符利用時の運賃
     */
    @field:Json(name = "odpt:ticketFare")
    val ticketFare: Int,
    /**
     * ICカード利用時の運賃
     */
    @field:Json(name = "odpt:icCardFare")
    val icCardFare: Int?,
    /**
     * 切符利用時の子供運賃
     */
    @field:Json(name = "odpt:childTicketFare")
    val childTicketFare: Int?,
    /**
     * ICカード利用時の子供運賃
     */
    @field:Json(name = "odpt:childIcCardFare")
    val childIcCardFare: Int?,
    /**
     * 運賃計算上の経由駅のIDのリスト
     * @see OdptStation.sameAs
     */
    @field:Json(name = "odpt:viaStation")
    val viaStationList: List<String>?,
    /**
     * 運賃計算上の経由路線のIDのリスト
     * @see OdptRailway.sameAs
     */
    @field:Json(name = "odpt:viaRailway")
    val viaRailwayList: List<String>?,
    /**
     * チケット種別（特急、ライナーなど列車種別によって料金が異なる場合に記載）
     */
    @field:Json(name = "odpt:ticketType")
    val ticketType: String?,
    /**
     * 支払い方法のリスト
     */
    @field:Json(name = "odpt:paymentMethod")
    val paymentMethodList: List<String>?
) {
    /**
     * 固有識別子を表す運賃情報ID
     */
    val railwayFareId: RailwayFareId
        get() = RailwayFareId(sameAs)
    /**
     * 運行会社の事業者ID
     */
    val operatorId: OperatorId
        get() = OperatorId(operator)
    /**
     * 駅間の始点駅の駅ID
     */
    val fromStationId: StationId
        get() = StationId(fromStation)
    /**
     * 駅間の終点駅の駅ID
     */
    val toStationId: StationId
        get() = StationId(toStation)
    /**
     * 運賃計算上の経由駅の駅IDのリスト
     */
    val viaStationIdList: List<StationId>?
        get() = viaStationList?.map { StationId(it) }
    /**
     * 運賃計算上の経由路線の路線IDのリスト
     */
    val viaRailwayIdList: List<RailwayId>?
        get() = viaRailwayList?.map { RailwayId(it) }
}
