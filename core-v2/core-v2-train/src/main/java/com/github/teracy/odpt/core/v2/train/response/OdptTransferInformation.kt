package com.github.teracy.odpt.core.v2.train.response

import com.github.teracy.odpt.model.RailDirectionId
import com.github.teracy.odpt.model.RailwayId
import com.squareup.moshi.Json

/**
 * v2版駅施設情報APIレスポンスのプラットフォーム停車時施設情報に含まれる乗り換え情報
 */
data class OdptTransferInformation(
    /**
     * 乗り換え可能路線のID
     * @see OdptRailway.sameAs
     */
    @field:Json(name = "odpt:railway")
    val railway: String,
    /**
     * 乗り換え可能路線の方面を表すID。乗り換え可能な方面を特記する必要がある場合にのみ記載
     */
    @field:Json(name = "odpt:railDirection")
    val railDirection: String?,
    /**
     * 所要時間（分）
     */
    @field:Json(name = "odpt:necessaryTime")
    val necessaryTime: Int
) {
    /**
     * 乗り換え可能路線の路線ID
     */
    val railwayId: RailwayId
        get() = RailwayId(railway)
    /**
     * 乗り換え可能路線の鉄道方面ID。乗り換え可能な方面を特記する必要がある場合にのみ記載
     */
    val railDirectionId: RailDirectionId?
        get() = railDirection?.let { RailDirectionId(it) }
}
