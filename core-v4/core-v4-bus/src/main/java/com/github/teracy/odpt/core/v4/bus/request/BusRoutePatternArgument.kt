package com.github.teracy.odpt.core.v4.bus.request

import com.github.teracy.odpt.model.BusRouteId
import com.github.teracy.odpt.model.BusRoutePatternId
import com.github.teracy.odpt.model.OperatorId

/**
 * バス運行系統情報検索引数
 */
sealed class BusRoutePatternArgument(
    /**
     * 固有識別子(ucode)のリスト
     */
    val idList: List<String>? = null,
    /**
     * 固有識別子別名（運行系統ID）のリスト
     */
    val sameAsList: List<String>? = null,
    /**
     * 路線・系統名称のリスト
     */
    val titleList: List<String>? = null,
    /**
     * 運行会社の事業者IDのリスト
     */
    val operatorList: List<String>? = null,
    /**
     * バス路線IDのリスト
     */
    val busRouteList: List<String>? = null
) {
    /**
     * 固有識別子による検索
     *
     * @param idList 固有識別子のリスト
     */
    class ById(idList: List<String>) :
        BusRoutePatternArgument(idList = idList)

    /**
     * バス系統IDによる検索
     *
     * @param busRoutePatternIdList バス系統IDのリスト
     */
    class ByBusRoutePatternId(busRoutePatternIdList: List<BusRoutePatternId>) :
        BusRoutePatternArgument(sameAsList = busRoutePatternIdList.map { it.id })

    /**
     * 事業者IDによる検索
     * 利用は推奨しない
     *
     * @param operatorIdList 運行会社の事業者IDのリスト
     */
    class ByOperatorId(operatorIdList: List<OperatorId>) :
        BusRoutePatternArgument(operatorList = operatorIdList.map { it.id })

    /**
     * 路線・系統名称による検索
     *
     * @param busRouteNameList 路線・系統名称のリスト（必須）
     * @param operatorId 事業者ID（任意。同じ路線・系統名称が存在する場合の絞り込み条件として利用）
     * @param busRouteIdList バス路線IDのリスト（任意。同じ路線・系統名称が存在する場合の絞り込み条件として利用）
     */
    class ByBusRouteName(
        busRouteNameList: List<String>,
        operatorId: OperatorId? = null,
        busRouteIdList: List<BusRouteId>? = null
    ) :
        BusRoutePatternArgument(
            titleList = busRouteNameList,
            operatorList = operatorId?.let { listOf(it.id) },
            busRouteList = busRouteIdList?.map { it.id }
        )

    /**
     * バス路線IDによる検索
     *
     * @param busRouteIdList バス路線IDのリスト
     */
    class ByBusRouteId(busRouteIdList: List<BusRouteId>) :
        BusRoutePatternArgument(busRouteList = busRouteIdList.map { it.id })
}
