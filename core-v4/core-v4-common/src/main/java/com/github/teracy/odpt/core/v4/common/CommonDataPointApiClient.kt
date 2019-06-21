package com.github.teracy.odpt.core.v4.common

import com.github.teracy.odpt.core.v4.common.request.CalendarArgument
import com.github.teracy.odpt.core.v4.common.request.OperatorArgument
import com.github.teracy.odpt.core.v4.common.response.OdptCalendar
import com.github.teracy.odpt.core.v4.common.response.OdptOperator
import com.github.teracy.odpt.model.ApiClient

/**
 * v4版データ取得・検索APIを利用した共通情報クライアント
 */
interface CommonDataPointApiClient : ApiClient {
    /**
     * v4版鉄道データ取得・検索API接続サービスのインスタンス
     */
    fun commonDataPointApiService(): CommonDataPointApiService

    /**
     * 曜日・日付区分情報取得
     *
     * @param idList 固有識別子(ucode)のリスト
     * @param sameAsList 固有識別子別名（曜日・日付区分情報ID）のリスト
     * @return 曜日・日付区分情報リスト
     */
    suspend fun getCalendar(
        idList: List<String>? = null,
        sameAsList: List<String>? = null
    ): List<OdptCalendar> {
        return commonDataPointApiService()
            .getCalendarAsync(
                consumerKey = consumerKey(),
                id = idList.concatenate(),
                sameAs = sameAsList.concatenate()
            )
            .await()
    }

    /**
     * 事業者情報取得
     * API参加事業者だけではなく、APIに参加している空港事業者に離発着する航空会社、あるいはAPIに参加している鉄道事業者と相互乗り入れしている鉄道会社の情報なども配信される
     *
     * @param idList 固有識別子(ucode)のリスト
     * @param sameAsList 固有識別子別名（事業者情報ID）のリスト
     * @return 事業者情報リスト
     */
    suspend fun getOperator(
        idList: List<String>? = null,
        sameAsList: List<String>? = null
    ): List<OdptOperator> {
        return commonDataPointApiService()
            .getOperatorAsync(
                consumerKey = consumerKey(),
                id = idList.concatenate(),
                sameAs = sameAsList.concatenate()
            )
            .await()
    }

    /**
     * 曜日・日付区分情報取得
     *
     * @param argument 検索引数
     * @return 曜日・日付区分情報リスト
     */
    suspend fun getCalendar(argument: CalendarArgument): List<OdptCalendar> {
        return getCalendar(
            idList = argument.idList,
            sameAsList = argument.sameAsList
        )
    }

    /**
     * 事業者情報取得
     *
     * @param argument 検索引数
     * @return 事業者情報リスト
     */
    suspend fun getOperator(argument: OperatorArgument): List<OdptOperator> {
        return getOperator(
            idList = argument.idList,
            sameAsList = argument.sameAsList
        )
    }

    private fun List<String>?.concatenate(): String? {
        return this?.joinToString(separator = ",")
    }
}
