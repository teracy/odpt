package com.github.teracy.odpt.api.tokyometro.support

import com.github.teracy.odpt.model.TrainTypeId

/**
 * 東京メトロオープンデータAPIで利用可能な列車種別
 *
 * @param trainTypeId 列車種別ID
 * @param title 列車種別
 */
enum class TrainTypes(val trainTypeId: TrainTypeId, val title: String) {
    /**
     * 不明
     */
    UNKNOWN(TrainTypeId("odpt.TrainType:TokyoMetro.Unknown"), "不明"),
    /**
     * 各停
     */
    LOCAL(TrainTypeId("odpt.TrainType:TokyoMetro.Local"), "各停"),
    /**
     * 急行
     */
    EXPRESS(TrainTypeId("odpt.TrainType:TokyoMetro.Express"), "急行"),
    /**
     * 快速
     */
    RAPID(TrainTypeId("odpt.TrainType:TokyoMetro.Rapid"), "快速"),
    /**
     * 準急
     */
    SEMI_EXPRESS(TrainTypeId("odpt.TrainType:TokyoMetro.SemiExpress"), "準急"),
    /**
     * 多摩急行
     */
    TAMA_EXPRESS(TrainTypeId("odpt.TrainType:TokyoMetro.TamaExpress"), "多摩急行"),
    /**
     * 土休急行
     */
    HOLIDAY_EXPRESS(TrainTypeId("odpt.TrainType:TokyoMetro.HolidayExpress"), "土休急行"),
    /**
     * 通勤準急
     */
    COMMUTER_SEMI_EXPRESS(TrainTypeId("odpt.TrainType:TokyoMetro.CommuterSemiExpress"), "通勤準急"),
    /**
     * 臨時
     */
    EXTRA(TrainTypeId("odpt.TrainType:TokyoMetro.Extra"), "臨時"),
    /**
     * 特急ロマンスカー
     */
    ROMANCE_CAR(TrainTypeId("odpt.TrainType:TokyoMetro.RomanceCar"), "特急ロマンスカー"),
    /**
     * 快速急行
     */
    RAPID_EXPRESS(TrainTypeId("odpt.TrainType:TokyoMetro.RapidExpress"), "快速急行"),
    /**
     * 通勤急行
     */
    COMMUTER_EXPRESS(TrainTypeId("odpt.TrainType:TokyoMetro.CommuterExpress"), "通勤急行"),
    /**
     * 特急
     */
    LIMITED_EXPRESS(TrainTypeId("odpt.TrainType:TokyoMetro.LimitedExpress"), "特急"),
    /**
     * 通勤特急
     */
    COMMUTER_LIMITED_EXPRESS(TrainTypeId("odpt.TrainType:TokyoMetro.CommuterLimitedExpress"), "通勤特急"),
    /**
     * 通勤快速
     */
    COMMUTER_RAPID(TrainTypeId("odpt.TrainType:TokyoMetro.CommuterRapid"), "通勤快速"),
    /**
     * 東葉快速
     */
    TOYO_RAPID(TrainTypeId("odpt.TrainType:TokyoMetro.ToyoRapid"), "東葉快速"),
    /**
     * Fライナー
     */
    F_LINER(TrainTypeId("odpt.TrainType:TokyoMetro.F-Liner"), "Fライナー"),
    /**
     * S-TRAIN
     */
    S_TRAIN(TrainTypeId("odpt.TrainType:TokyoMetro.S-TRAIN"), "S-TRAIN");

    companion object {
        /**
         * ID文字列からの変換
         * NOTE: v2版には列車種別を取得するAPIがないため必要
         */
        fun fromId(id: String?): TrainTypes? {
            return values().firstOrNull { it.trainTypeId.id == id }
        }

        /**
         * 列車種別IDからの変換
         * NOTE: v2版には列車種別を取得するAPIがないため必要
         */
        fun fromId(trainTypeId: TrainTypeId): TrainTypes? {
            return values().firstOrNull { it.trainTypeId.id == trainTypeId.id }
        }
    }
}
