package com.github.teracy.odpt.model

/**
 * バスの扉の開閉状態
 */
enum class DoorStatus {
    /**
     * 開いている
     */
    OPEM,
    /**
     * 閉じている
     */
    CLOSE,
    /**
     * 半自動扱い
     */
    SELF;

    companion object {
        /**
         * 文字列からの変換
         */
        fun fromStatusString(string: String?): DoorStatus? {
            return when (string) {
                "open" -> OPEM
                "close" -> CLOSE
                "self" -> SELF
                else -> null
            }
        }
    }
}
