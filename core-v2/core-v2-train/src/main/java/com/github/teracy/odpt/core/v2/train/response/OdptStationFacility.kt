package com.github.teracy.odpt.core.v2.train.response

import com.github.teracy.odpt.model.StationFacilityId
import com.squareup.moshi.Json

/**
 * v2版駅施設情報APIレスポンス
 */
data class OdptStationFacility(
    /**
     * 固有識別子（ucode）
     */
    @field:Json(name = "@id")
    val id: String,
    /**
     * 固有識別子（駅施設ID）
     */
    @field:Json(name = "owl:sameAs")
    val sameAs: String,
    /**
     * 駅情報の生成時刻（ISO8601 日付時刻形式）
     */
    @field:Json(name = "dc:date")
    val date: String,
    /**
     * 駅の施設一覧
     */
    @field:Json(name = "odpt:barrierfreeFacility")
    val barrierFreeFacilityList: List<OdptBarrierFreeFacility>?,
    /**
     * プラットフォームに車両が停車している時の、車両毎の最寄りの施設・出口等の情報
     */
    @field:Json(name = "odpt:platformInformation")
    val platformInformationList: List<OdptPlatformInformation>
) {
    /**
     * 固有識別子を表す駅施設ID
     */
    val stationFacilityId: StationFacilityId
        get() = StationFacilityId(sameAs)
}
