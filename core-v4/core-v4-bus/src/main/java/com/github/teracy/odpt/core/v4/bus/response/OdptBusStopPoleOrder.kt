package com.github.teracy.odpt.core.v4.bus.response

import com.github.teracy.odpt.model.BusStopPoleId
import com.github.teracy.odpt.model.OpeningDoor
import com.squareup.moshi.Json

/**
 * v4版バス停標柱情報APIレスポンスのバス停標柱の順序情報
 */
data class OdptBusStopPoleOrder(
    /**
     * バス停標柱のID
     * @see OdptBusStopPole.sameAs
     */
    @field:Json(name = "odpt:busstopPole")
    val busStopPole: String,
    /**
     * 停留所通過順。通過順の昇順の値となる
     */
    @field:Json(name = "odpt:index")
    val index: Int,

    @field:Json(name = "odpt:openingDoorsToGetOn")
    private val _openingDoorsToGetOnList: List<String>?,

    @field:Json(name = "odpt:openingDoorsToGetOff")
    private val _openingDoorsToGetOffList: List<String>?,

    /**
     * 注記
     */
    @field:Json(name = "odpt:note")
    val note: String?
) {
    /**
     * バス停標柱ID
     */
    val busStopPoleId: BusStopPoleId
        get() = BusStopPoleId(busStopPole)
    /**
     * 乗車時に利用可能なドアのリスト
     */
    val openingDoorsToGetOnList: List<OpeningDoor>?
        get() = _openingDoorsToGetOnList?.map { OpeningDoor.fromDoorIdString(it) }?.requireNoNulls()
    /**
     * 降車時に利用可能なドアのリスト
     */
    val openingDoorsToGetOffList: List<OpeningDoor>?
        get() = _openingDoorsToGetOffList?.map { OpeningDoor.fromDoorIdString(it) }?.requireNoNulls()
}
