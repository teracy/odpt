package com.github.teracy.odpt.core.v4.train

import com.github.teracy.odpt.core.v4.train.response.*
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

/**
 * v4版鉄道データ取得・検索API接続サービスinterface
 */
interface TrainDataPointApiService {
    /**
     * 駅乗降人員数情報取得
     *
     * @param consumerKey APIアクセス用のアクセストークン
     * @param id 固有識別子(ucode)。複数件の場合はカンマで連結必要
     * @param sameAs 固有識別子別名（駅乗降人員数情報ID）。複数件の場合はカンマで連結必要
     * @param operator 運行会社の事業者ID。複数件の場合はカンマで連結必要
     * @param station 駅ID。複数件不可
     * @param railway 路線ID。複数件不可
     * @return 駅乗降人員数情報リスト
     */
    @Headers("connection: close")
    @GET("/api/v4/odpt:PassengerSurvey")
    fun getPassengerSurveyAsync(
        @Query("acl:consumerKey") consumerKey: String,
        @Query("@id") id: String? = null,
        @Query("owl:sameAs") sameAs: String? = null,
        @Query("odpt:operator") operator: String? = null,
        @Query("odpt:station") station: String? = null,
        @Query("odpt:railway") railway: String? = null
    ): Deferred<List<OdptPassengerSurvey>>

    /**
     * 進行方向情報取得
     *
     * @param consumerKey APIアクセス用のアクセストークン
     * @param id 固有識別子(ucode)。複数件の場合はカンマで連結必要
     * @param sameAs 固有識別子（鉄道方面ID）。複数件の場合はカンマで連結必要
     * @return 進行方向情報リスト
     */
    @Headers("connection: close")
    @GET("/api/v4/odpt:RailDirection")
    fun getRailDirectionAsync(
        @Query("acl:consumerKey") consumerKey: String,
        @Query("@id") id: String? = null,
        @Query("owl:sameAs") sameAs: String? = null
    ): Deferred<List<OdptRailDirection>>

    /**
     * 鉄道路線情報取得
     *
     * @param consumerKey APIアクセス用のアクセストークン
     * @param id 固有識別子(ucode)。複数件の場合はカンマで連結必要
     * @param sameAs 固有識別子別名（鉄道路線ID）。複数件の場合はカンマで連結必要
     * @param title 運行系統名。複数件の場合はカンマで連結必要
     * @param operator 運行会社の事業者ID。複数件の場合はカンマで連結必要
     * @param lineCode 路線コード。複数件の場合はカンマで連結必要
     * @return 鉄道路線情報リスト
     */
    @Headers("connection: close")
    @GET("/api/v4/odpt:Railway")
    fun getRailwayAsync(
        @Query("acl:consumerKey") consumerKey: String,
        @Query("@id") id: String? = null,
        @Query("owl:sameAs") sameAs: String? = null,
        @Query("dc:title") title: String? = null,
        @Query("odpt:operator") operator: String? = null,
        @Query("odpt:lineCode") lineCode: String? = null
    ): Deferred<List<OdptRailway>>

    /**
     * 運賃情報取得
     *
     * @param consumerKey APIアクセス用のアクセストークン
     * @param id 固有識別子(ucode)。複数件の場合はカンマで連結必要
     * @param sameAs 固有識別子別名（運賃情報ID）。複数件の場合はカンマで連結必要
     * @param operator 運行会社の事業者ID。複数件の場合はカンマで連結必要
     * @param fromStation 駅間の始点駅の駅ID。複数件の場合はカンマで連結必要
     * @param toStation 駅間の終点駅の駅ID。複数件の場合はカンマで連結必要
     * @return 運賃情報リスト
     */
    @Headers("connection: close")
    @GET("/api/v4/odpt:RailwayFare")
    fun getRailwayFareAsync(
        @Query("acl:consumerKey") consumerKey: String,
        @Query("@id") id: String? = null,
        @Query("owl:sameAs") sameAs: String? = null,
        @Query("odpt:operator") operator: String? = null,
        @Query("odpt:fromStation") fromStation: String? = null,
        @Query("odpt:toStation") toStation: String? = null
    ): Deferred<List<OdptRailwayFare>>

    /**
     * 駅情報取得
     *
     * @param consumerKey APIアクセス用のアクセストークン
     * @param id 固有識別子(ucode)。複数件の場合はカンマで連結必要
     * @param sameAs 固有識別子別名（駅ID）
     * @param title 駅名。複数件の場合はカンマで連結必要
     * @param operator 運行会社の事業者ID。複数件の場合はカンマで連結必要
     * @param railway 路線ID。複数件の場合はカンマで連結必要
     * @param stationCode 駅コード。複数件の場合はカンマで連結必要
     * @return 駅情報リスト
     */
    @Headers("connection: close")
    @GET("/api/v4/odpt:Station")
    fun getStationAsync(
        @Query("acl:consumerKey") consumerKey: String,
        @Query("@id") id: String? = null,
        @Query("owl:sameAs") sameAs: String? = null,
        @Query("dc:title") title: String? = null,
        @Query("odpt:operator") operator: String? = null,
        @Query("odpt:railway") railway: String? = null,
        @Query("odpt:stationCode") stationCode: String? = null
    ): Deferred<List<OdptStation>>

    /**
     * 駅時刻表情報取得
     *
     * @param consumerKey APIアクセス用のアクセストークン
     * @param id 固有識別子(ucode)。複数件の場合はカンマで連結必要
     * @param sameAs 固有識別子別名（駅時刻表情報ID）。複数件の場合はカンマで連結必要
     * @param station 駅ID。複数件の場合はカンマで連結必要
     * @param railway 路線ID。複数件の場合はカンマで連結必要
     * @param operator 運行会社の事業者ID。複数件の場合はカンマで連結必要
     * @param railDirection 鉄道方面ID。複数件の場合はカンマで連結必要
     * @param calendar 時刻表を取得したい曜日・日付の日付情報ID。複数件の場合はカンマで連結必要
     * @param date 時刻表を取得したい特定日付。複数件の場合はカンマで連結必要
     * @return 駅時刻表情報リスト
     */
    @Headers("connection: close")
    @GET("/api/v4/odpt:StationTimetable")
    fun getStationTimetableAsync(
        @Query("acl:consumerKey") consumerKey: String,
        @Query("@id") id: String? = null,
        @Query("owl:sameAs") sameAs: String? = null,
        @Query("odpt:station") station: String? = null,
        @Query("odpt:railway") railway: String? = null,
        @Query("odpt:operator") operator: String? = null,
        @Query("odpt:railDirection") railDirection: String? = null,
        @Query("odpt:calendar") calendar: String? = null,
        @Query("dc:date") date: String? = null
    ): Deferred<List<OdptStationTimetable>>

    /**
     * 列車ロケーション情報取得
     *
     * @param consumerKey APIアクセス用のアクセストークン
     * @param id 固有識別子(ucode)。複数件の場合はカンマで連結必要
     * @param sameAs 固有識別子別名（列車ID）。複数件の場合はカンマで連結必要
     * @param operator 列車情報を配信する事業者のID。複数件の場合はカンマで連結必要
     * @param railway 当該列車が運行している路線のID。複数件の場合はカンマで連結必要
     * @return 列車ロケーション情報リスト
     */
    @Headers("connection: close")
    @GET("/api/v4/odpt:Train")
    fun getTrainAsync(
        @Query("acl:consumerKey") consumerKey: String,
        @Query("@id") id: String? = null,
        @Query("owl:sameAs") sameAs: String? = null,
        @Query("odpt:operator") operator: String? = null,
        @Query("odpt:railway") railway: String? = null
    ): Deferred<List<OdptTrain>>

    /**
     * 列車運行情報取得
     *
     * @param consumerKey APIアクセス用のアクセストークン
     * @param id 固有識別子(ucode)。複数件の場合はカンマで連結必要
     * @param sameAs 固有識別子別名（列車運行情報ID）。複数件の場合はカンマで連結必要
     * @param operator 運行情報を配信する事業者のID。複数件の場合はカンマで連結必要
     * @param railway 運行情報が発生した路線のID。複数件の場合はカンマで連結必要
     * @return 列車運行情報リスト
     */
    @Headers("connection: close")
    @GET("/api/v4/odpt:TrainInformation")
    fun getTrainInformationAsync(
        @Query("acl:consumerKey") consumerKey: String,
        @Query("@id") id: String? = null,
        @Query("owl:sameAs") sameAs: String? = null,
        @Query("odpt:operator") operator: String? = null,
        @Query("odpt:railway") railway: String? = null
    ): Deferred<List<OdptTrainInformation>>

    /**
     * 列車時刻表情報取得
     *
     * @param consumerKey APIアクセス用のアクセストークン
     * @param id 固有識別子(ucode)。複数件の場合はカンマで連結必要
     * @param sameAs 固有識別子別名（列車時刻表情報ID）。複数件の場合はカンマで連結必要
     * @param trainNumber 列車番号。複数件の場合はカンマで連結必要
     * @param railway 路線ID。複数件の場合はカンマで連結必要
     * @param operator 運行会社の事業者ID。複数件の場合はカンマで連結必要
     * @param trainType 列車種別ID。複数件の場合はカンマで連結必要
     * @param train 列車ID。複数件の場合はカンマで連結必要
     * @param calendar 時刻表を取得したい曜日・日付の日付情報ID。複数件の場合はカンマで連結必要
     * @return 駅時刻表情報リスト
     */
    @Headers("connection: close")
    @GET("/api/v4/odpt:TrainTimetable")
    fun getTrainTimetableAsync(
        @Query("acl:consumerKey") consumerKey: String,
        @Query("@id") id: String? = null,
        @Query("owl:sameAs") sameAs: String? = null,
        @Query("odpt:trainNumber") trainNumber: String? = null,
        @Query("odpt:railway") railway: String? = null,
        @Query("odpt:operator") operator: String? = null,
        @Query("odpt:trainType") trainType: String? = null,
        @Query("odpt:train") train: String? = null,
        @Query("odpt:calendar") calendar: String? = null
    ): Deferred<List<OdptTrainTimetable>>

    /**
     * 列車種別情報取得
     *
     * @param consumerKey APIアクセス用のアクセストークン
     * @param id 固有識別子(ucode)。複数件の場合はカンマで連結必要
     * @param sameAs 固有識別子別名（鉄道路線ID）。複数件の場合はカンマで連結必要
     * @param operator 運行会社の事業者ID。複数件の場合はカンマで連結必要
     * @return 列車種別情報リスト
     */
    @Headers("connection: close")
    @GET("/api/v4/odpt:TrainType")
    fun getTrainTypeAsync(
        @Query("acl:consumerKey") consumerKey: String,
        @Query("@id") id: String? = null,
        @Query("owl:sameAs") sameAs: String? = null,
        @Query("odpt:operator") operator: String? = null
    ): Deferred<List<OdptTrainType>>
}
