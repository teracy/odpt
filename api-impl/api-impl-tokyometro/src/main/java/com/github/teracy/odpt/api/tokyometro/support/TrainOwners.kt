package com.github.teracy.odpt.api.tokyometro.support

import com.github.teracy.odpt.model.TrainOwnerId

/**
 * 東京メトロオープンデータAPIで利用可能な列車所有会社
 *
 * @param trainOwnerId 列車所有会社ID
 * @param title 列車所有会社名
 */
enum class TrainOwners(val trainOwnerId: TrainOwnerId, val title: String) {
    /**
     * 東京メトロ
     */
    TOKYO_METRO(TrainOwnerId("odpt.TrainOwner:TokyoMetro"), "東京メトロ"),
    /**
     * 西武鉄道
     */
    SEIBU(TrainOwnerId("odpt.TrainOwner:Seibu"), "西武鉄道"),
    /**
     * 埼玉高速鉄道
     */
    SAITAMA_RAILWAY(TrainOwnerId("odpt.TrainOwner:SaitamaRailway"), "埼玉高速鉄道"),
    /**
     * 東武鉄道
     */
    TOBU(TrainOwnerId("odpt.TrainOwner:Tobu"), "東武鉄道"),
    /**
     * 東葉高速鉄道
     */
    TOYO_RAPID_RAILWAY(TrainOwnerId("odpt.TrainOwner:ToyoRapidRailway"), "東葉高速鉄道"),
    /**
     * 都営地下鉄
     */
    TOEI(TrainOwnerId("odpt.TrainOwner:Toei"), "都営地下鉄"),
    /**
     * 東急電鉄
     */
    TOKYU(TrainOwnerId("odpt.TrainOwner:Tokyu"), "東急電鉄"),
    /**
     * JR東日本
     */
    JR_EAST(TrainOwnerId("odpt.TrainOwner:JR-East"), "JR東日本"),
    /**
     * 小田急電鉄
     */
    ODAKYU(TrainOwnerId("odpt.TrainOwner:Odakyu"), "小田急電鉄");

    companion object {
        /**
         * ID文字列からの変換
         * NOTE: 列車所有会社の情報を取得するAPIがないため必要
         */
        fun fromId(id: String?): TrainOwners? {
            return values().firstOrNull { it.trainOwnerId.id == id }
        }

        /**
         * 列車所有会社IDからの変換
         * NOTE: 列車所有会社の情報を取得するAPIがないため必要
         */
        fun fromId(trainOwnerId: TrainOwnerId): TrainOwners? {
            return fromId(trainOwnerId.id)
        }
    }
}
