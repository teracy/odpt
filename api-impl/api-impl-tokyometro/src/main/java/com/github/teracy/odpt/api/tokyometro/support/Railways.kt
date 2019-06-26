package com.github.teracy.odpt.api.tokyometro.support

import com.github.teracy.odpt.model.RailwayId

/**
 * 東京メトロオープンデータAPIで利用可能な路線
 *
 * @param railwayId 鉄道路線ID
 * @param title 路線名
 */
enum class Railways(val railwayId: RailwayId, val title: String) {
    /**
     * 銀座線
     */
    TOKYO_METRO_GINZA(RailwayId("odpt.Railway:TokyoMetro.Ginza"), "銀座線"),
    /**
     * 丸ノ内線
     */
    TOKYO_METRO_MARUNOUCHI(RailwayId("odpt.Railway:TokyoMetro.Marunouchi"), "丸ノ内線"),
    /**
     * 日比谷線
     */
    TOKYO_METRO_HIBIYA(RailwayId("odpt.Railway:TokyoMetro.Hibiya"), "日比谷線"),
    /**
     * 東西線
     */
    TOKYO_METRO_TOZAI(RailwayId("odpt.Railway:TokyoMetro.Tozai"), "東西線"),
    /**
     * 千代田線
     */
    TOKYO_METRO_CHIYODA(RailwayId("odpt.Railway:TokyoMetro.Chiyoda"), "千代田線"),
    /**
     * 有楽町線
     */
    TOKYO_METRO_YURAKUCHO(RailwayId("odpt.Railway:TokyoMetro.Yurakucho"), "有楽町線"),
    /**
     * 半蔵門線
     */
    TOKYO_METRO_HANZOMON(RailwayId("odpt.Railway:TokyoMetro.Hanzomon"), "半蔵門線"),
    /**
     * 南北線
     */
    TOKYO_METRO_NAMBOKU(RailwayId("odpt.Railway:TokyoMetro.Namboku"), "南北線"),
    /**
     * 副都心線
     */
    TOKYO_METRO_FUKUTOSHIN(RailwayId("odpt.Railway:TokyoMetro.Fukutoshin"), "副都心線"),
    /**
     * JR線
     */
    JR_EAST(RailwayId("odpt.Railway:JR-East"), "JR線"),
    /**
     * 中央線
     */
    JR_EAST_CHUO(RailwayId("odpt.Railway:JR-East.Chuo"), "中央線"),
    /**
     * 中央線快速
     */
    JR_EAST_CHUO_KAISOKU(RailwayId("odpt.Railway:JR-East.ChuoKaisoku"), "中央線快速"),
    /**
     * 中央・総武線各駅停車
     */
    JR_EAST_CHUO_SOBU(RailwayId("odpt.Railway:JR-East.ChuoSobu"), "中央・総武線各駅停車"),
    /**
     * 常磐線
     */
    JR_EAST_JOBAN(RailwayId("odpt.Railway:JR-East.Joban"), "常磐線"),
    /**
     * 京浜東北線
     */
    JR_EAST_KEIHIN_TOHOKU(RailwayId("odpt.Railway:JR-East.KeihinTohoku"), "京浜東北線"),
    /**
     * 京葉線
     */
    JR_EAST_KEIYO(RailwayId("odpt.Railway:JR-East.Keiyo"), "京葉線"),
    /**
     * 武蔵野線
     */
    JR_EAST_MUSASHINO(RailwayId("odpt.Railway:JR-East.Musashino"), "武蔵野線"),
    /**
     * 成田エクスプレス
     */
    JR_EAST_NARITA_EXPRESS(RailwayId("odpt.Railway:JR-East.NaritaExpress"), "成田エクスプレス"),
    /**
     * 埼京線
     */
    JR_EAST_SAIKYO(RailwayId("odpt.Railway:JR-East.Saikyo"), "埼京線"),
    /**
     * 湘南新宿ライン
     */
    JR_EAST_SHONAN_SHINJUKU(RailwayId("odpt.Railway:JR-East.ShonanShinjuku"), "湘南新宿ライン"),
    /**
     * 総武線
     */
    JR_EAST_SOBU(RailwayId("odpt.Railway:JR-East.Sobu"), "総武線"),
    /**
     * 高崎線
     */
    JR_EAST_TAKASAKI(RailwayId("odpt.Railway:JR-East.Takasaki"), "高崎線"),
    /**
     * 東海道線
     */
    JR_EAST_TOKAIDO(RailwayId("odpt.Railway:JR-East.Tokaido"), "東海道線"),
    /**
     * 宇都宮線
     */
    JR_EAST_UTSUNOMIYA(RailwayId("odpt.Railway:JR-East.Utsunomiya"), "宇都宮線"),
    /**
     * 山手線
     */
    JR_EAST_YAMANOTE(RailwayId("odpt.Railway:JR-East.Yamanote"), "山手線"),
    /**
     * 横須賀線
     */
    JR_EAST_YOKOSUKA(RailwayId("odpt.Railway:JR-East.Yokosuka"), "横須賀線"),
    /**
     * 井の頭線
     */
    KEIO_INOKASHIRA(RailwayId("odpt.Railway:Keio.Inokashira"), "井の頭線"),
    /**
     * 京王線
     */
    KEIO_KEIO(RailwayId("odpt.Railway:Keio.Keio"), "京王線"),
    /**
     * 京王新線
     */
    KEIO_NEW(RailwayId("odpt.Railway:Keio.New"), "京王新線"),
    /**
     * 京成本線
     */
    KEISEI_MAIN(RailwayId("odpt.Railway:Keisei.KeiseiMain"), "京成本線"),
    /**
     * 押上線
     */
    KEISEI_OSHIAGE(RailwayId("odpt.Railway:Keisei.KeiseiOshiage"), "押上線"),
    /**
     * つくばエクスプレス線
     */
    MIR_TX(RailwayId("odpt.Railway:MIR.TX"), "つくばエクスプレス線"),
    /**
     * 小田原線
     */
    ODAKYU_ODAWARA(RailwayId("odpt.Railway:Odakyu.Odawara"), "小田原線"),
    /**
     * 埼玉高速鉄道線
     */
    SAITAMA_RAILWAY(RailwayId("odpt.Railway:SaitamaRailway.SaitamaRailway"), "埼玉高速鉄道線"),
    /**
     * 池袋線
     */
    SEIBU_IKEBUKURO(RailwayId("odpt.Railway:Seibu.Ikebukuro"), "池袋線"),
    /**
     * 西武有楽町線
     */
    SEIBU_YURAKUCHO(RailwayId("odpt.Railway:Seibu.SeibuYurakucho"), "西武有楽町線"),
    /**
     * 新宿線
     */
    SEIBU_SHINJUKU(RailwayId("odpt.Railway:Seibu.Shinjuku"), "新宿線"),
    /**
     * りんかい線
     */
    TWR_RINKAI(RailwayId("odpt.Railway:TWR.Rinkai"), "りんかい線"),
    /**
     * 伊勢崎線
     */
    TOBU_ISESAKI(RailwayId("odpt.Railway:Tobu.Isesaki"), "伊勢崎線"),
    /**
     * 東上線
     */
    TOBU_TOJO(RailwayId("odpt.Railway:Tobu.Tojo"), "東上線"),
    /**
     * 浅草線
     */
    TOEI_ASAKUSA(RailwayId("odpt.Railway:Toei.Asakusa"), "浅草線"),
    /**
     * 三田線
     */
    TOEI_MITA(RailwayId("odpt.Railway:Toei.Mita"), "三田線"),
    /**
     * 日暮里・舎人ライナー
     */
    TOEI_NIPPORI_TONERI(RailwayId("odpt.Railway:Toei.NipporiToneri"), "日暮里・舎人ライナー"),
    /**
     * 大江戸線
     */
    TOEI_OEDO(RailwayId("odpt.Railway:Toei.Oedo"), "大江戸線"),
    /**
     * 新宿線
     */
    TOEI_SHINJUKU(RailwayId("odpt.Railway:Toei.Shinjuku"), "新宿線"),
    /**
     * 都電荒川線
     */
    TOEI_TODEN_ARAKAWA(RailwayId("odpt.Railway:Toei.TodenArakawa"), "都電荒川線"),
    /**
     * 田園都市線
     */
    TOKYU_DEN_EN_TOSHI(RailwayId("odpt.Railway:Tokyu.DenEnToshi"), "田園都市線"),
    /**
     * 目黒線
     */
    TOKYU_MEGURO(RailwayId("odpt.Railway:Tokyu.Meguro"), "目黒線"),
    /**
     * 東横線
     */
    TOKYU_TOYOKO(RailwayId("odpt.Railway:Tokyu.Toyoko"), "東横線"),
    /**
     * 東葉高速線
     */
    TOYO_RAPID_RAILWAY(RailwayId("odpt.Railway:ToyoRapidRailway.ToyoRapidRailway"), "東葉高速線"),
    /**
     * ゆりかもめ
     */
    YURIKAMOME(RailwayId("odpt.Railway:Yurikamome.Yurikamome"), "ゆりかもめ");

    companion object {
        /**
         * ID文字列からの変換
         * NOTE: v2版の場合、データ配信事業者以外の情報を鉄道路線APIで取得できないため必要
         */
        fun fromId(id: String?): Railways? {
            return values().firstOrNull { it.railwayId.id == id }
        }

        /**
         * 鉄道路線IDからの変換
         * NOTE: v2版の場合、データ配信事業者以外の情報を鉄道路線APIで取得できないため必要
         */
        fun fromId(railwayId: RailwayId): Railways? {
            return fromId(railwayId.id)
        }
    }
}
