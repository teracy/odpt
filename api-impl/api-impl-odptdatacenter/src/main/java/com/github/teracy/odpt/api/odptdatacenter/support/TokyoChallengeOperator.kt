package com.github.teracy.odpt.api.odptdatacenter.support

import com.github.teracy.odpt.model.OperatorId

/**
 * 公共交通オープンデータセンターAPIに参加している事業者
 * 正式な事業者名等は[事業者情報取得処理][com.github.teracy.odpt.api.odptdatacenter.OdptDataCenterCommonApiClient.getOperator]で別途取得すること
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
 * 東京都交通局
 */
object Toei : TokyoChallengeOperator("Toei", providesTrainData = true, providesBusData = true)
