package com.github.teracy.odpt.core.v4.common

import com.github.teracy.odpt.core.v4.common.response.OdptCalendar
import com.github.teracy.odpt.core.v4.common.response.OdptOperator
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

/**
 * v4版共通データ取得・検索API接続サービスinterface
 */
interface CommonDataPointApiService {
    /**
     * 曜日・日付区分情報取得
     *
     * @param consumerKey APIアクセス用のアクセストークン
     * @param id 固有識別子(ucode)。複数件の場合はカンマで連結必要
     * @param sameAs 固有識別子別名（曜日・日付区分情報ID）。複数件の場合はカンマで連結必要
     * @return 曜日・日付区分情報リスト
     */
    @Headers("connection: close")
    @GET("api/v4/odpt:Calendar")
    fun getCalendarAsync(
        @Query("acl:consumerKey") consumerKey: String,
        @Query("@id") id: String? = null,
        @Query("owl:sameAs") sameAs: String? = null
    ): Deferred<List<OdptCalendar>>

    /**
     * 事業者情報取得
     *
     * @param consumerKey APIアクセス用のアクセストークン
     * @param id 固有識別子(ucode)。複数件の場合はカンマで連結必要
     * @param sameAs 固有識別子別名（事業者情報ID）。複数件の場合はカンマで連結必要
     * @return 事業者情報リスト
     */
    @Headers("connection: close")
    @GET("api/v4/odpt:Operator")
    fun getOperatorAsync(
        @Query("acl:consumerKey") consumerKey: String,
        @Query("@id") id: String? = null,
        @Query("owl:sameAs") sameAs: String? = null
    ): Deferred<List<OdptOperator>>
}
