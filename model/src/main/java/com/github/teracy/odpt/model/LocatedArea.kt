package com.github.teracy.odpt.model

/**
 * 施設設置個所
 */
enum class LocatedArea {
    /**
     * 改札内
     */
    INSIDE,
    /**
     * 改札外
     */
    OUTSIDE;

    companion object {
        /**
         * 文字列からの変換
         */
        fun fromName(string: String?): LocatedArea? {
            return when (string) {
                "改札内" -> INSIDE
                "改札外" -> OUTSIDE
                else -> null
            }
        }
    }
}
