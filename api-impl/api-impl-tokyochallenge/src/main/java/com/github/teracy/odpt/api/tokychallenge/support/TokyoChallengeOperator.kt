package com.github.teracy.odpt.api.tokychallenge.support

import com.github.teracy.odpt.model.OperatorId

/**
 * 東京公共交通オープンデータチャレンジAPIに参加している事業者
 * 正式な事業者名等は[事業者情報取得処理][com.github.teracy.odpt.api.tokychallenge.TokyoChallengeCommonApiClient.getOperator]で別途取得すること
 *
 * @param providesTrainData true:鉄道API対象の事業者である
 * @param providesBusData true:バスAPI対象の事業者である
 * @param providesAirplaneData true:航空機API対象の事業者である
 */
sealed class TokyoChallengeOperator(
    private val _operatorName: String,
    val providesTrainData: Boolean = false,
    val providesBusData: Boolean = false,
    val providesAirplaneData: Boolean = false
) {
    /**
     * 事業者ID
     */
    val operatorId: OperatorId
        get() = OperatorId("odpt.Operator:$_operatorName")

}

/**
 * 全日空
 */
object AllNipponAirways : TokyoChallengeOperator("ANA", providesAirplaneData = true)

/**
 * 日本航空
 */
object JapanAirlines : TokyoChallengeOperator("JAL", providesAirplaneData = true)

/**
 * 日本空港ビルデング
 */
object JapanAirportTerminal : TokyoChallengeOperator("HND-JAT", providesAirplaneData = true)

/**
 * JRバス関東
 */
object JrBusKanto : TokyoChallengeOperator("JRBusKanto", providesBusData = true)

/**
 * JR東日本
 */
object JrEast : TokyoChallengeOperator("JR-East", providesTrainData = true)

/**
 * 関東バス
 */
object KantoBus : TokyoChallengeOperator("KantoBus", providesBusData = true)

/**
 * 京急電鉄
 */
object Keikyu : TokyoChallengeOperator("Keikyu", providesTrainData = true)

/**
 * 京王電鉄
 */
object Keio : TokyoChallengeOperator("Keio", providesTrainData = true)

/**
 * 京王バス
 */
object KeioBus : TokyoChallengeOperator("KeioBus", providesBusData = true)

/**
 * 京成電鉄
 */
object Keisei : TokyoChallengeOperator("Keisei", providesTrainData = true)

/**
 * 国際興業
 */
object KokusaiKogyo : TokyoChallengeOperator("KokusaiKogyoBus", providesBusData = true)

/**
 * 成田国際空港
 */
object NaritaInternationalAirport : TokyoChallengeOperator("NAA", providesAirplaneData = true)

/**
 * 西東京バス
 */
object NishiTokyoBus : TokyoChallengeOperator("NishiTokyoBus", providesBusData = true)

/**
 * 小田急電鉄
 */
object Odakyu : TokyoChallengeOperator("Odakyu", providesTrainData = true)

/**
 * 小田急バス
 */
object OdakyuBus : TokyoChallengeOperator("OdakyuBus", providesBusData = true)

/**
 * 西武鉄道
 */
object Seibu : TokyoChallengeOperator("Seibu", providesTrainData = true)

/**
 * 西武バス
 */
object SeibuBus : TokyoChallengeOperator("SeibuBus", providesBusData = true)

/**
 * 東京国際空港ターミナル
 */
object TokyoInternationalAirTerminal : TokyoChallengeOperator("HND-TIAT", providesAirplaneData = true)

/**
 * 東武鉄道
 */
object Tobu : TokyoChallengeOperator("Tobu", providesTrainData = true)

/**
 * 東武バス
 */
object TobuBus : TokyoChallengeOperator("TobuBus", providesBusData = true)

/**
 * 東京都交通局
 */
object Toei : TokyoChallengeOperator("Toei", providesTrainData = true, providesBusData = true)

/**
 * 東京メトロ
 */
object TokyoMetro : TokyoChallengeOperator("TokyoMetro", providesTrainData = true)

/**
 * 東急電鉄
 */
object Tokyu : TokyoChallengeOperator("Tokyu", providesTrainData = true)

/**
 * 東急バス
 */
object TokyuBus : TokyoChallengeOperator("TokyuBus", providesBusData = true)

/**
 * 東京臨海高速鉄道
 */
object TwrRinkaiLine : TokyoChallengeOperator("TWR", providesTrainData = true)

/**
 * ゆりかもめ
 */
object Yurikamome : TokyoChallengeOperator("Yurikamome", providesTrainData = true)
