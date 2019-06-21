package com.github.teracy.odpt.model

/**
 * 利用可能なドア
 */
enum class OpeningDoor {
    /**
     * 前扉
     */
    FRONT_SIDE,
    /**
     * 後扉
     */
    REAR_SIDE;

    companion object {
        /**
         * 文字列からの変換
         */
        fun fromDoorIdString(string: String?): OpeningDoor? {
            return when (string) {
                "odpt:OpeningDoor:FrontSide" -> FRONT_SIDE
                "odpt:OpeningDoor:RearSide" -> REAR_SIDE
                else -> null
            }
        }
    }
}
