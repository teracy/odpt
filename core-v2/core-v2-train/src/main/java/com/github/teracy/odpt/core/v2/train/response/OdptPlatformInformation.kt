package com.github.teracy.odpt.core.v2.train.response

import com.github.teracy.odpt.model.RailDirectionId
import com.github.teracy.odpt.model.RailwayId
import com.github.teracy.odpt.model.StationFacilityId
import com.squareup.moshi.Json

/**
 * v2版駅施設情報APIレスポンスのプラットフォーム停車時施設情報
 */
data class OdptPlatformInformation(
    /**
     * 所属路線のID
     * @see OdptRailway.sameAs
     */
    @field:Json(name = "odpt:railway")
    val railway: String,
    /**
     * 車両編成数
     */
    @field:Json(name = "odpt:carComposition")
    val carComposition: Int,
    /**
     * 車両の号車番号
     */
    @field:Json(name = "odpt:carNumber")
    val carNumber: Int,
    /**
     * プラットフォームに停車する列車の方面を表すID
     */
    @field:Json(name = "odpt:railDirection")
    val railDirection: String,
    /**
     * 最寄りの乗り換え可能な路線と所要時間の情報のリスト
     */
    @field:Json(name = "odpt:transferInformation")
    val transferInformationList: List<OdptTransferInformation>?,
    /**
     * 最寄りのバリアフリー施設のIDのリスト
     */
    @field:Json(name = "odpt:barrierfreeFacility")
    val barrierFreeFacilityList: List<String>?,
    /**
     * 改札外の最寄り施設
     */
    @field:Json(name = "odpt:surroundingArea")
    val surroundingArea: List<String>?
) {
    /**
     * 所属路線の鉄道路線ID
     */
    val railwayId: RailwayId
        get() = RailwayId(railway)
    /**
     * プラットフォームに停車する列車の方面ID
     */
    val railDirectionId: RailDirectionId
        get() = RailDirectionId(railDirection)
    /**
     * 最寄りのバリアフリー施設の施設IDリスト
     */
    val barrierFreeFacilityIdList: List<StationFacilityId>?
        get() = barrierFreeFacilityList?.map { StationFacilityId(it) }
}
