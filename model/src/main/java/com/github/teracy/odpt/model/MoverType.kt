package com.github.teracy.odpt.model

/**
 * 身障者向け移動手段対応状況
 */
enum class MoverType {
    /**
     * ハンドル型電動車いす利用可能
     */
    AVAILABLE_TO_MOBILITY_SCOOTER,
    /**
     * 車いす対応
     */
    AVAILABLE_TO_WHEELCHAIR;

    companion object {
        /**
         * 文字列からの変換
         */
        fun fromTypeString(string: String?): MoverType? {
            return when (string) {
                "spac:MobilityScooter" -> AVAILABLE_TO_MOBILITY_SCOOTER
                "spac:Wheelchair" -> AVAILABLE_TO_WHEELCHAIR
                else -> null
            }
        }
    }
}
