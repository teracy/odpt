package com.github.teracy.odpt.core.v4.train.request

import com.github.teracy.odpt.model.RailDirectionId

/**
 * 進行方向情報検索引数
 */
sealed class RailDirectionArgument(
    /**
     * 固有識別子(ucode)のリスト
     */
    val idList: List<String>? = null,
    /**
     * 固有識別子別名（鉄道方面ID）のリスト
     */
    val sameAsList: List<String>? = null
) {
    /**
     * 固有識別子による検索
     *
     * @param idList 固有識別子のリスト
     */
    class ById(idList: List<String>) :
        RailDirectionArgument(idList = idList)

    /**
     * 鉄道方面IDによる検索
     *
     * @param railDirectionIdList 鉄道方面IDのリスト
     */
    class ByRailDirectionId(railDirectionIdList: List<RailDirectionId>) :
        RailDirectionArgument(sameAsList = railDirectionIdList.map { it.id })
}
