package com.github.teracy.odpt.core.v2.train.response

import com.github.teracy.odpt.model.StationId
import com.github.teracy.odpt.model.TrainTypeId
import com.squareup.moshi.Json

/**
 * v2版鉄道路線情報APIレスポンスの駅間の標準所要時間情報
 */
data class OdptTravelTime(
    /**
     * 駅間の起点となる駅のID
     * @see OdptStation.sameAs
     */
    @field:Json(name = "odpt:fromStation")
    val fromStation: String,
    /**
     * 駅間の終点となる駅のID
     * @see OdptStation.sameAs
     */
    @field:Json(name = "odpt:toStation")
    val toStation: String,
    /**
     * 駅間の所要時間（分）
     */
    @field:Json(name = "odpt:necessaryTime")
    val necessaryTime: Int,
    /**
     * 列車種別のID
     */
    @field:Json(name = "odpt:trainType")
    val trainType: String
) {
    /**
     * 駅間の起点となる駅の駅ID
     */
    val fromStationId: StationId
        get() = StationId(fromStation)
    /**
     * 駅間の終点となる駅の駅ID
     */
    val toStationId: StationId
        get() = StationId(toStation)
    /**
     * 列車種別ID
     */
    val trainTypeId: TrainTypeId
        get() = TrainTypeId(trainType)
}
