package com.github.teracy.odpt.api.tokyometro.support

import com.github.teracy.odpt.model.RailDirectionId

/**
 * 東京メトロオープンデータAPIで利用可能な方面
 *
 * @param railDirectionId 鉄道方面ID
 * @param title 方面名
 */
enum class RailDirections(val railDirectionId: RailDirectionId, val title: String) {
    /**
     * 浅草方面
     */
    Asakusa(RailDirectionId("odpt.RailDirection:TokyoMetro.Asakusa"), "浅草方面"),
    /**
     * 荻窪方面
     */
    Ogikubo(RailDirectionId("odpt.RailDirection:TokyoMetro.Ogikubo"), "荻窪方面"),
    /**
     * 池袋方面（丸ノ内線及び有楽町線共通）
     */
    Ikebukuro(RailDirectionId("odpt.RailDirection:TokyoMetro.Ikebukuro"), "池袋方面"),
    /**
     * 方南町方面
     */
    Honancho(RailDirectionId("odpt.RailDirection:TokyoMetro.Honancho"), "方南町方面"),
    /**
     * 中野坂上方面
     */
    NakanoSakaue(RailDirectionId("odpt.RailDirection:TokyoMetro.NakanoSakaue"), "中野坂上方面"),
    /**
     * 中目黒方面
     */
    NakaMeguro(RailDirectionId("odpt.RailDirection:TokyoMetro.NakaMeguro"), "中目黒方面"),
    /**
     * 北千住方面
     */
    KitaSenju(RailDirectionId("odpt.RailDirection:TokyoMetro.KitaSenju"), "北千住方面"),
    /**
     * 西船橋方面
     */
    NishiFunabashi(RailDirectionId("odpt.RailDirection:TokyoMetro.NishiFunabashi"), "西船橋方面"),
    /**
     * 中野方面
     */
    Nakano(RailDirectionId("odpt.RailDirection:TokyoMetro.Nakano"), "中野方面"),
    /**
     * 代々木上原方面
     */
    YoyogiUehara(RailDirectionId("odpt.RailDirection:TokyoMetro.YoyogiUehara"), "代々木上原方面"),
    /**
     * 綾瀬方面
     */
    Ayase(RailDirectionId("odpt.RailDirection:TokyoMetro.Ayase"), "綾瀬方面"),
    /**
     * 北綾瀬方面
     */
    KitaAyase(RailDirectionId("odpt.RailDirection:TokyoMetro.KitaAyase"), "北綾瀬方面"),
    /**
     * 新木場方面
     */
    ShinKiba(RailDirectionId("odpt.RailDirection:TokyoMetro.ShinKiba"), "新木場方面"),
    /**
     * 押上方面
     */
    Oshiage(RailDirectionId("odpt.RailDirection:TokyoMetro.Oshiage"), "押上方面"),
    /**
     * 渋谷方面
     */
    Shibuya(RailDirectionId("odpt.RailDirection:TokyoMetro.Shibuya"), "渋谷方面"),
    /**
     * 赤羽岩淵方面
     */
    AkabaneIwabuchi(RailDirectionId("odpt.RailDirection:TokyoMetro.AkabaneIwabuchi"), "赤羽岩淵方面"),
    /**
     * 目黒方面
     */
    Meguro(RailDirectionId("odpt.RailDirection:TokyoMetro.Meguro"), "目黒方面"),
    /**
     * 白金高輪方面
     */
    ShirokaneTakanawa(RailDirectionId("odpt.RailDirection:TokyoMetro.ShirokaneTakanawa"), "白金高輪方面"),
    /**
     * 和光市方面
     */
    Wakoshi(RailDirectionId("odpt.RailDirection:TokyoMetro.Wakoshi"), "和光市方面"),
    /**
     * 小竹向原方面
     */
    KotakeMukaihara(RailDirectionId("odpt.RailDirection:TokyoMetro.KotakeMukaihara"), "小竹向原方面");

    companion object {
        /**
         * ID文字列からの変換
         * NOTE: v2版には方面を取得するAPIがないため必要
         */
        fun fromId(id: String?): RailDirections? {
            return values().firstOrNull { it.railDirectionId.id == id }
        }

        /**
         * 鉄道方面IDからの変換
         * NOTE: v2版には方面を取得するAPIがないため必要
         */
        fun fromId(railDirectionId: RailDirectionId): RailDirections? {
            return fromId(railDirectionId.id)
        }
    }
}
