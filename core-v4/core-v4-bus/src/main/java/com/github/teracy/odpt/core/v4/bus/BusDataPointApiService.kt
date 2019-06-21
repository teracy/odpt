package com.github.teracy.odpt.core.v4.bus

import com.github.teracy.odpt.core.v4.bus.response.*
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

/**
 * v4版バスデータ取得・検索API接続サービスinterface
 */
interface BusDataPointApiService {
    /**
     * バス運行情報取得
     *
     * @param consumerKey APIアクセス用のアクセストークン
     * @param id 固有識別子(ucode)。複数件の場合はカンマで連結必要
     * @param sameAs 固有識別子別名（バス運行情報ID）。複数件の場合はカンマで連結必要
     * @param busRoutePattern バス車両の運行系統のID。複数件の場合はカンマで連結必要
     * @param operator 運行会社の事業者ID。複数件の場合はカンマで連結必要
     * @param fromBusStopPole 直近に通過したバス停のID。複数件の場合はカンマで連結必要
     * @param toBusStopPole 次に到着するバス停のID。複数件の場合はカンマで連結必要
     * @return バス運行情報リスト
     */
    @Headers("connection: close")
    @GET("/api/v4/odpt:Bus")
    fun getBusAsync(
        @Query("acl:consumerKey") consumerKey: String,
        @Query("@id") id: String? = null,
        @Query("owl:sameAs") sameAs: String? = null,
        @Query("odpt:busroutePattern") busRoutePattern: String? = null,
        @Query("odpt:operator") operator: String? = null,
        @Query("odpt:fromBusstopPole") fromBusStopPole: String? = null,
        @Query("odpt:toBusstopPole") toBusStopPole: String? = null
    ): Deferred<List<OdptBus>>

    /**
     * バス時刻表情報取得
     *
     * @param consumerKey APIアクセス用のアクセストークン
     * @param id 固有識別子(ucode)。複数件の場合はカンマで連結必要
     * @param sameAs 固有識別子別名（バス時刻表情報ID）。複数件の場合はカンマで連結必要
     * @param operator 運行会社の事業者ID。複数件の場合はカンマで連結必要（API定義ではrequiredになっているがoptional）
     * @param busRoutePattern バス車両の運行系統のID。複数件の場合はカンマで連結必要
     * @param title バス路線名称（系統名等) 。複数件の場合はカンマで連結必要
     * @param calendar 運行する日付・曜日情報のID。複数件の場合はカンマで連結必要
     * @return バス時刻表情報リスト
     */
    @Headers("connection: close")
    @GET("/api/v4/odpt:BusTimetable")
    fun getBusTimetableAsync(
        @Query("acl:consumerKey") consumerKey: String,
        @Query("@id") id: String? = null,
        @Query("owl:sameAs") sameAs: String? = null,
        @Query("odpt:operator") operator: String? = null,
        @Query("odpt:busroutePattern") busRoutePattern: String? = null,
        @Query("dc:title") title: String? = null,
        @Query("odpt:fromBusstopPole") fromBusStopPole: String? = null,
        @Query("odpt:calendar") calendar: String? = null
    ): Deferred<List<OdptBusTimetable>>

    /**
     * バス運行系統情報取得
     *
     * @param consumerKey APIアクセス用のアクセストークン
     * @param id 固有識別子(ucode)。複数件の場合はカンマで連結必要
     * @param sameAs 固有識別子別名（バス運行系統情報ID）。複数件の場合はカンマで連結必要
     * @param title バス路線名称（系統名等)。複数件の場合はカンマで連結必要
     * @param operator 運行会社の事業者ID。複数件の場合はカンマで連結必要（API定義ではrequiredになっているがoptional）
     * @param busRoute 路線のID 。複数件の場合はカンマで連結必要
     * @return バス運行系統情報リスト
     */
    @Headers("connection: close")
    @GET("/api/v4/odpt:BusroutePattern")
    fun getBusRoutePatternAsync(
        @Query("acl:consumerKey") consumerKey: String,
        @Query("@id") id: String? = null,
        @Query("owl:sameAs") sameAs: String? = null,
        @Query("dc:title") title: String? = null,
        @Query("odpt:operator") operator: String? = null,
        @Query("odpt:busroute") busRoute: String? = null
    ): Deferred<List<OdptBusRoutePattern>>

    /**
     * バス運賃情報取得
     *
     * @param consumerKey APIアクセス用のアクセストークン
     * @param id 固有識別子(ucode)。複数件の場合はカンマで連結必要
     * @param sameAs 固有識別子別名（バス運賃情報ID）。複数件の場合はカンマで連結必要
     * @param operator 運行会社の事業者ID。複数件の場合はカンマで連結必要（API定義ではrequiredになっているがoptional）
     * @param fromBusStopPole 乗車バス停標柱のID。複数件の場合はカンマで連結必要
     * @param toBusStopPole 降車バス停標柱のID。複数件の場合はカンマで連結必要
     * @param ticketFare 切符利用時の運賃
     * @param childTicketFare 切符利用時の子供運賃
     * @param icCardFare ICカード利用時の運賃
     * @param childIcCardFare ICカード利用時の子供運賃
     * @return バス運賃情報リスト
     */
    @Headers("connection: close")
    @GET("/api/v4/odpt:BusroutePatternFare")
    fun getBusRoutePatternFareAsync(
        @Query("acl:consumerKey") consumerKey: String,
        @Query("@id") id: String? = null,
        @Query("owl:sameAs") sameAs: String? = null,
        @Query("odpt:operator") operator: String? = null,
        @Query("odpt:fromBusstopPole") fromBusStopPole: String? = null,
        @Query("odpt:toBusstopPole") toBusStopPole: String? = null,
        @Query("odpt:ticketFare") ticketFare: Int? = null,
        @Query("odpt:childTicketFare") childTicketFare: Int? = null,
        @Query("odpt:icCardFare") icCardFare: Int? = null,
        @Query("odpt:childIcCardFare") childIcCardFare: Int? = null
    ): Deferred<List<OdptBusRoutePatternFare>>

    /**
     * バス停標柱情報取得
     *
     * @param consumerKey APIアクセス用のアクセストークン
     * @param id 固有識別子(ucode)。複数件の場合はカンマで連結必要
     * @param sameAs 固有識別子別名（バス停標柱情報ID）。複数件の場合はカンマで連結必要
     * @param title 停留所名称。複数件の場合はカンマで連結必要
     * @param busStopPoleNumber 標柱番号。複数件の場合はカンマで連結必要
     * @param busRoutePattern 標柱で発着する系統のID。複数件の場合はカンマで連結必要
     * @param operator 運行会社の事業者ID。複数件の場合はカンマで連結必要（API定義ではrequiredになっているがoptional）
     * @return バス停標柱情報リスト
     */
    @Headers("connection: close")
    @GET("/api/v4/odpt:BusstopPole")
    fun getBusStopPoleAsync(
        @Query("acl:consumerKey") consumerKey: String,
        @Query("@id") id: String? = null,
        @Query("owl:sameAs") sameAs: String? = null,
        @Query("dc:title") title: String? = null,
        @Query("odpt:busstopPoleNumber") busStopPoleNumber: String? = null,
        @Query("odpt:busroutePattern") busRoutePattern: String? = null,
        @Query("odpt:operator") operator: String? = null
    ): Deferred<List<OdptBusStopPole>>

    /**
     * バス停標柱時刻表情報取得
     *
     * @param consumerKey APIアクセス用のアクセストークン
     * @param id 固有識別子(ucode)。複数件の場合はカンマで連結必要
     * @param sameAs 固有識別子別名（バス時刻表情報ID）。複数件の場合はカンマで連結必要
     * @param busStopPole バス停標柱のID。複数件の場合はカンマで連結必要
     * @param busDirection バス方面のID。複数件の場合はカンマで連結必要
     * @param busRoute バス路線のID。複数件の場合はカンマで連結必要
     * @param operator 運行会社の事業者ID。複数件の場合はカンマで連結必要（API定義ではrequiredになっているがoptional）
     * @param calendar 運行する日付・曜日情報のID。複数件の場合はカンマで連結必要
     * @param date 時刻表を取得したい特定日付。複数件の場合はカンマで連結必要
     * @return バス停標柱時刻表情報リスト
     */
    @Headers("connection: close")
    @GET("/api/v4/odpt:BusstopPole")
    fun getBusStopPoleTimetableAsync(
        @Query("acl:consumerKey") consumerKey: String,
        @Query("@id") id: String? = null,
        @Query("owl:sameAs") sameAs: String? = null,
        @Query("odpt:busstopPole") busStopPole: String? = null,
        @Query("odpt:busDirection") busDirection: String? = null,
        @Query("odpt:busroute") busRoute: String? = null,
        @Query("odpt:operator") operator: String? = null,
        @Query("odpt:calendar") calendar: String? = null,
        @Query("dc:date") date: String? = null
    ): Deferred<List<OdptBusStopPoleTimetable>>
}
