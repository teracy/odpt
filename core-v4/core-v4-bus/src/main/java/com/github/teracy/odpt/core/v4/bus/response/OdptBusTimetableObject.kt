package com.github.teracy.odpt.core.v4.bus.response

import com.github.teracy.odpt.model.BusStopPoleId
import com.squareup.moshi.Json

/**
 * v4版バス時刻表APIレスポンス.時分情報
 */
data class OdptBusTimetableObject(
    /**
     * 標柱通過順
     */
    @field:Json(name = "odpt:index")
    val index: Int,
    /**
     * バス停標柱のID
     * @see OdptBusStopPole.sameAs
     */
    @field:Json(name = "odpt:busstopPole")
    val busStopPole: String,
    /**
     * バス到着時刻
     */
    @field:Json(name = "odpt:arrivalTime")
    val arrivalTime: String?,
    /**
     * バス出発時刻
     */
    @field:Json(name = "odpt:departureTime")
    val departureTime: String?,
    /**
     * 行先（方向幕）情報
     */
    @field:Json(name = "odpt:destinationSign")
    val destinationSign: String?,

    @field:Json(name = "odpt:isNonStepBus")
    private val _isNonStepBus: Boolean?,

    @field:Json(name = "odpt:isMidnight")
    private val _isMidnight: Boolean?,

    @field:Json(name = "odpt:canGetOn")
    private val _canGetOn: Boolean?,

    @field:Json(name = "odpt:canGetOff")
    private val _canGetOff: Boolean?,

    /**
     * 注記
     */
    @field:Json(name = "odpt:note")
    val note: String?
) {
    /**
     * バス停のバス停標柱ID
     */
    val busStopPoleId: BusStopPoleId
        get() = BusStopPoleId(busStopPole)
    /**
     * ノンステップバスの場合、true
     */
    val isNonStepBus: Boolean
        get() = _isNonStepBus ?: false
    /**
     * 深夜バスの場合、true
     */
    val isMidnight: Boolean
        get() = _isMidnight ?: false
    /**
     * 乗車可能な場合、true
     */
    val canGetOn: Boolean
        get() = _canGetOn ?: false
    /**
     * 降車可能な場合、true
     */
    val canGetOff: Boolean
        get() = _canGetOff ?: false
}
