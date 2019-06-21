package com.github.teracy.odpt.model

/**
 * トイレ内のバリアフリー施設
 */
enum class ToiletAssistant {
    /**
     * 車いす対応トイレ
     */
    WHEELCHAIR_ACCESSIBLE,
    /**
     * ベビーチェア
     */
    BABY_CHAIR,
    /**
     * おむつ交換台
     */
    BABY_CHANGING_TABLE,
    /**
     * オストメイト
     */
    FOR_OSTOMATE;

    companion object {
        /**
         * 文字列からの変換
         */
        fun fromTypeString(string: String?): ToiletAssistant? {
            return when (string) {
                "spac:WheelchairAccessible" -> WHEELCHAIR_ACCESSIBLE
                "ug:BabyChair" -> BABY_CHAIR
                "ug:BabyChangingTable" -> BABY_CHANGING_TABLE
                "ug:ToiletForOstomate" -> FOR_OSTOMATE
                else -> null
            }
        }
    }
}
