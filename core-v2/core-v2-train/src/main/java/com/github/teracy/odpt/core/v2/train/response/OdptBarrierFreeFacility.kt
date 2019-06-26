package com.github.teracy.odpt.core.v2.train.response

import com.github.teracy.odpt.model.*
import com.squareup.moshi.Json

/**
 * v2版駅施設情報APIレスポンスの駅施設情報
 */
data class OdptBarrierFreeFacility(
    /**
     * 固有識別子（ucode）
     */
    @field:Json(name = "@id")
    val id: String?,

    @field:Json(name = "@type")
    private val _category: String?,

    /**
     * 固有識別子（駅施設ID）
     */
    @field:Json(name = "owl:sameAs")
    val sameAs: String?,
    /**
     * 施設のカテゴリ名（トイレ、階段昇降機、エレベーター、エスカレーター、ハンドル型電動車いす利用可能経路）
     */
    @field:Json(name = "ugsrv:categoryName")
    val categoryName: String?,
    /**
     * 施設情報の詳細リスト
     */
    @field:Json(name = "odpt:serviceDetail")
    val serviceDetail: List<OdptServiceDetail>?,
    /**
     * 施設の設置されている場所の名前
     */
    @field:Json(name = "odpt:placeName")
    val placeName: String?,
    /**
     * 施設の設置場所（改札内／改札外）
     */
    @field:Json(name = "odpt:locatedAreaName")
    val locatedAreaName: String?,
    /**
     * 補足事項
     */
    @field:Json(name = "ugsrv:remark")
    val remark: String?,

    @field:Json(name = "spac:hasAssistant")
    private val _toiletAssistant: List<String>?,

    @field:Json(name = "spac:isAvailableTo")
    private val _moverType: String?
) {
    /**
     * バリアフリー施設カテゴリ（"ug:Toilet", "spac:Stairlift", "ug:Elevator", "ug:Escalator", "ug:Link"より変換）
     */
    val category: BarrierFreeFacilityType?
        get() = BarrierFreeFacilityType.fromTypeString(_category)
    /**
     * 固有識別子を表す駅施設ID
     */
    val stationFacilityId: StationFacilityId?
        get() = sameAs?.let { StationFacilityId(it) }
    /**
     * 設置個所
     */
    val locatedArea: LocatedArea?
        get() = LocatedArea.fromName(locatedAreaName)
    /**
     * トイレ内のバリアフリー施設状況（ "spac:WheelchairAccessible", "ug:BabyChangingTable", "ug:ToiletForOstomate"より変換）
     */
    val toiletAssistant: List<ToiletAssistant>?
        get() = _toiletAssistant?.map { ToiletAssistant.fromTypeString(it) }?.filterNotNull()
    /**
     * 身障者向け移動手段対応状況（"spac:MobilityScooter", "spac:Wheelchair"より変換）
     */
    val moverType: MoverType?
        get() = MoverType.fromTypeString(_moverType)
}
