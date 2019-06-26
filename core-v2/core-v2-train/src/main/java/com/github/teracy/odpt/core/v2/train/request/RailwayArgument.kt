package com.github.teracy.odpt.core.v2.train.request

import com.github.teracy.odpt.model.OperatorId
import com.github.teracy.odpt.model.RailwayId

/**
 * 鉄道路線情報検索引数
 */
sealed class RailwayArgument(
    /**
     * 固有識別子(ucode)
     */
    val id: String? = null,
    /**
     * 固有識別子（路線ID）
     */
    val sameAs: String? = null,
    /**
     * 運行会社の事業者ID
     */
    val operator: String? = null,
    /**
     * 運行系統名（「線」を含まない部分一致）
     */
    val title: String? = null,
    /**
     * 路線コード（大文字小文字を区別しない部分一致）
     */
    val lineCode: String? = null
) {
    /**
     * 固有識別子による検索
     *
     * @param id 固有識別子
     */
    class ById(id: String) : RailwayArgument(id = id)

    /**
     * 鉄道路線IDによる検索
     *
     * @param railwayId 鉄道路線ID
     */
    class ByRailwayId(railwayId: RailwayId) : RailwayArgument(sameAs = railwayId.id)

    /**
     * 事業者IDによる検索
     *
     * @param operatorId 事業者ID
     */
    class ByOperatorId(operatorId: OperatorId) : RailwayArgument(operator = operatorId.id)

    /**
     * 運行系統名による検索
     *
     * @param railwayName 運行系統名（必須。「線」を含まない部分一致。例えば「楽」で検索すると有楽町線が該当するが、「有楽町線」では該当する路線はない）
     * @param operatorId 事業者ID（任意）
     */
    class ByRailwayName(railwayName: String, operatorId: OperatorId?) :
        RailwayArgument(title = railwayName, operator = operatorId?.id)

    /**
     * 路線コードによる検索
     *
     * @param lineCode 路線コード（大文字小文字を区別しない部分一致。例えば「m」で検索すると「M（丸ノ内線）」と「Mb（丸ノ内支線）」が、「B」で検索すると「Mb（丸ノ内支線）」が該当する）
     * @param operatorId 事業者ID（任意）
     */
    class ByLineCode(lineCode: String, operatorId: OperatorId?) :
        RailwayArgument(lineCode = lineCode, operator = operatorId?.id)
}
