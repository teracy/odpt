package com.github.teracy.odpt.api.tokyometro.support

import com.github.teracy.odpt.model.OperatorId

/**
 * 東京メトロオープンデータAPIで利用可能な事業者
 *
 * @param operatorId 事業者ID
 * @param title 事業者名
 */
enum class Operators(val operatorId: OperatorId, val title: String) {
    /**
     * 東京メトロ（唯一の事業者）
     */
    TOKYO_METRO(OperatorId("odpt.Operator:TokyoMetro"), "東京メトロ");

    companion object {
        /**
         * ID文字列からの変換
         * NOTE: v2版には事業者を取得するAPIがないため必要
         */
        fun fromId(id: String?): Operators? {
            return values().firstOrNull { it.operatorId.id == id }
        }

        /**
         * 事業者IDからの変換
         * NOTE: v2版には事業者を取得するAPIがないため必要
         */
        fun fromId(operatorId: OperatorId): Operators? {
            return fromId(operatorId.id)
        }
    }
}
