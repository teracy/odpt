package com.github.teracy.odpt.model

/**
 * バリアフリー施設区分
 */
enum class BarrierFreeFacilityType {
    /**
     * トイレ
     */
    TOILET,
    /**
     * 階段昇降機
     */
    STAIR_LIFT,
    /**
     * エレベーター
     */
    ELEVATOR,
    /**
     * エスカレーター
     */
    ESCALATOR,
    /**
     * 経路（ハンドル型電動車いす利用可能経路）
     */
    LINK;

    companion object {
        /**
         * 文字列からの変換
         */
        fun fromTypeString(string: String?): BarrierFreeFacilityType? {
            return when (string) {
                "ug:Toilet" -> TOILET
                "spac:Stairlift" -> STAIR_LIFT
                "ug:Elevator" -> ELEVATOR
                "ug:Escalator" -> ESCALATOR
                "ug:Link" -> LINK
                else -> null
            }
        }
    }
}
