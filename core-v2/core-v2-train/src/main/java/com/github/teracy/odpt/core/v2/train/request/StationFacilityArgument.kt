package com.github.teracy.odpt.core.v2.train.request

import com.github.teracy.odpt.model.StationFacilityId

/**
 * 駅施設情報検索引数
 */
sealed class StationFacilityArgument(
    /**
     * 固有識別子(ucode)
     */
    val id: String? = null,
    /**
     * 固有識別子（駅施設情報ID）
     */
    val sameAs: String? = null
) {
    /**
     * 固有識別子による検索
     *
     * @param id 固有識別子(ucode)
     */
    class ById(id: String) : StationFacilityArgument(id = id)

    /**
     * 駅施設情報IDによる検索
     *
     * @param stationFacilityId 駅施設情報ID
     */
    class ByStationFacilityId(stationFacilityId: StationFacilityId) :
        StationFacilityArgument(sameAs = stationFacilityId.id)
}
