package com.github.teracy.odpt.core.v4.airplane

import com.github.teracy.odpt.core.v4.airplane.response.*
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

/**
 * v4版航空機データ取得・検索API接続サービスinterface
 */
interface AirplaneDataPointApiService {
    /**
     * 空港情報取得
     *
     * @param consumerKey APIアクセス用のアクセストークン
     * @param id 固有識別子(ucode)。複数件の場合はカンマで連結必要
     * @param sameAs 固有識別子別名（空港ID）。複数件の場合はカンマで連結必要
     * @return 空港情報リスト
     */
    @Headers("connection: close")
    @GET("/api/v4/odpt:Airport")
    fun getAirportAsync(
        @Query("acl:consumerKey") consumerKey: String,
        @Query("@id") id: String? = null,
        @Query("owl:sameAs") sameAs: String? = null
    ): Deferred<List<OdptAirport>>

    /**
     * 空港ターミナル情報取得
     *
     * @param consumerKey APIアクセス用のアクセストークン
     * @param id 固有識別子(ucode)。複数件の場合はカンマで連結必要
     * @param sameAs 固有識別子別名（空港ターミナルID）。複数件の場合はカンマで連結必要
     * @param airport 空港ID。複数件の場合はカンマで連結必要
     * @return 空港ターミナル情報リスト
     */
    @Headers("connection: close")
    @GET("/api/v4/odpt:AirportTerminal")
    fun getAirportTerminalAsync(
        @Query("acl:consumerKey") consumerKey: String,
        @Query("@id") id: String? = null,
        @Query("owl:sameAs") sameAs: String? = null,
        @Query("odpt:airport") airport: String? = null
    ): Deferred<List<OdptAirportTerminal>>

    /**
     * フライト到着情報取得
     *
     * @param consumerKey APIアクセス用のアクセストークン
     * @param id 固有識別子(ucode)。複数件の場合はカンマで連結必要
     * @param sameAs 固有識別子別名（フライト到着情報ID）。複数件の場合はカンマで連結必要
     * @param operator フライト到着情報を提供する空港事業者または航空事業者の事業者ID。複数件の場合はカンマで連結必要
     * @param airline エアラインの運行会社の事業者ID。複数件の場合はカンマで連結必要
     * @param flightStatus フライト状況ID。複数件の場合はカンマで連結必要
     * @param arrivalAirport 到着空港の空港ID。複数件の場合はカンマで連結必要
     * @param arrivalAirportTerminal 到着空港のターミナルの空港ターミナルID。複数件の場合はカンマで連結必要
     * @param arrivalGate 到着空港ゲート番号。複数件の場合はカンマで連結必要
     * @param originAirport 出発空港の空港ID。複数件の場合はカンマで連結必要
     * @return フライト到着情報リスト
     */
    @Headers("connection: close")
    @GET("/api/v4/odpt:FlightInformationArrival")
    fun getFlightInformationArrivalAsync(
        @Query("acl:consumerKey") consumerKey: String,
        @Query("@id") id: String? = null,
        @Query("owl:sameAs") sameAs: String? = null,
        @Query("odpt:operator") operator: String? = null,
        @Query("odpt:airline") airline: String? = null,
        @Query("odpt:flightStatus") flightStatus: String? = null,
        @Query("odpt:arrivalAirport") arrivalAirport: String? = null,
        @Query("odpt:arrivalAirportTerminal") arrivalAirportTerminal: String? = null,
        @Query("odpt:arrivalGate") arrivalGate: String? = null,
        @Query("odpt:originAirport") originAirport: String? = null
    ): Deferred<List<OdptFlightInformationArrival>>

    /**
     * フライト出発情報取得
     *
     * @param consumerKey APIアクセス用のアクセストークン
     * @param id 固有識別子(ucode)。複数件の場合はカンマで連結必要
     * @param sameAs 固有識別子別名（フライト出発情報ID）。複数件の場合はカンマで連結必要
     * @param operator フライト到着情報を提供する空港事業者または航空事業者の事業者ID。複数件の場合はカンマで連結必要
     * @param airline エアラインの運行会社の事業者ID。複数件の場合はカンマで連結必要
     * @param flightStatus フライト状況ID。複数件の場合はカンマで連結必要
     * @param departureAirport 出発空港の空港ID。複数件の場合はカンマで連結必要
     * @param departureAirportTerminal 出発空港のターミナルの空港ターミナルID。複数件の場合はカンマで連結必要
     * @param departureGate 出発空港ゲート番号。複数件の場合はカンマで連結必要
     * @param destinationAirport 目的地の空港ID。複数件の場合はカンマで連結必要
     * @return フライト出発情報リスト
     */
    @Headers("connection: close")
    @GET("/api/v4/odpt:FlightInformationDeparture")
    fun getFlightInformationDepartureAsync(
        @Query("acl:consumerKey") consumerKey: String,
        @Query("@id") id: String? = null,
        @Query("owl:sameAs") sameAs: String? = null,
        @Query("odpt:operator") operator: String? = null,
        @Query("odpt:airline") airline: String? = null,
        @Query("odpt:flightStatus") flightStatus: String? = null,
        @Query("odpt:departureAirport") departureAirport: String? = null,
        @Query("odpt:departureAirportTerminal") departureAirportTerminal: String? = null,
        @Query("odpt:departureGate") departureGate: String? = null,
        @Query("odpt:destinationAirport") destinationAirport: String? = null
    ): Deferred<List<OdptFlightInformationDeparture>>

    /**
     * フライト時刻表情報取得
     *
     * @param consumerKey APIアクセス用のアクセストークン
     * @param id 固有識別子(ucode)。複数件の場合はカンマで連結必要
     * @param sameAs 固有識別子別名（フライト時刻表情報ID）。複数件の場合はカンマで連結必要
     * @param operator フライト到着情報を提供する空港事業者または航空事業者の事業者ID。複数件の場合はカンマで連結必要
     * @param calendar 運航する曜日・日付情報のID。複数件の場合はカンマで連結必要
     * @param originAirport 出発地の空港ID。複数件の場合はカンマで連結必要
     * @param destinationAirport 目的地の空港ID。複数件の場合はカンマで連結必要
     * @return フライト時刻表情報リスト
     */
    @Headers("connection: close")
    @GET("/api/v4/odpt:FlightSchedule")
    fun getFlightScheduleAsync(
        @Query("acl:consumerKey") consumerKey: String,
        @Query("@id") id: String? = null,
        @Query("owl:sameAs") sameAs: String? = null,
        @Query("odpt:operator") operator: String? = null,
        @Query("odpt:calendar") calendar: String? = null,
        @Query("odpt:originAirport") originAirport: String? = null,
        @Query("odpt:destinationAirport") destinationAirport: String? = null
    ): Deferred<List<OdptFlightSchedule>>

    /**
     * フライト状況情報取得
     *
     * @param consumerKey APIアクセス用のアクセストークン
     * @param id 固有識別子(ucode)。複数件の場合はカンマで連結必要
     * @param sameAs 固有識別子別名（フライト状況ID）。複数件の場合はカンマで連結必要
     * @return フライト状況情報リスト
     */
    @Headers("connection: close")
    @GET("/api/v4/odpt:FlightStatus")
    fun getFlightStatusAsync(
        @Query("acl:consumerKey") consumerKey: String,
        @Query("@id") id: String? = null,
        @Query("owl:sameAs") sameAs: String? = null
    ): Deferred<List<OdptFlightStatus>>
}
