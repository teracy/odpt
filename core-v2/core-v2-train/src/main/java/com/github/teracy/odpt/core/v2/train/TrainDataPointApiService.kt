package com.github.teracy.odpt.core.v2.train

import com.github.teracy.odpt.core.v2.train.response.*
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

/**
 * v2版鉄道データ取得・検索API接続サービスinterface
 */
interface TrainDataPointApiService {
    /**
     * 駅時刻表情報取得
     *
     * @param consumerKey APIアクセス用のアクセストークン
     * @param id 固有識別子(ucode)
     * @param sameAs 固有識別子別名（駅時刻表情報ID）
     * @param station 駅ID
     * @param railway 路線ID
     * @param operator 運行会社の事業者ID
     * @param railDirection 鉄道方面ID
     * @return 駅時刻表情報リスト
     */
    @Headers("connection: close")
    @GET("api/v2/datapoints?rdf:type=odpt:StationTimetable")
    fun getStationTimetableAsync(
        @Query("acl:consumerKey") consumerKey: String,
        @Query("@id") id: String? = null,
        @Query("owl:sameAs") sameAs: String? = null,
        @Query("odpt:station") station: String? = null,
        @Query("odpt:railway") railway: String? = null,
        @Query("odpt:operator") operator: String? = null,
        @Query("odpt:railDirection") railDirection: String? = null
    ): Deferred<List<OdptStationTimetable>>

    /**
     * 列車運行情報取得
     *
     * @param consumerKey APIアクセス用のアクセストークン
     * @param id 固有識別子(ucode)
     * @param operator 運行会社の事業者ID
     * @param railway 路線ID
     * @param trainInformationStatus 運行情報テキスト
     * @return 列車運行情報リスト
     */
    @Headers("connection: close")
    @GET("api/v2/datapoints?rdf:type=odpt:TrainInformation")
    fun getTrainInformationAsync(
        @Query("acl:consumerKey") consumerKey: String,
        @Query("@id") id: String? = null,
        @Query("odpt:operator") operator: String? = null,
        @Query("odpt:railway") railway: String? = null,
        @Query("odpt:trainInformationStatus") trainInformationStatus: String? = null
    ): Deferred<List<OdptTrainInformation>>

    /**
     * 列車ロケーション情報取得
     *
     * @param consumerKey APIアクセス用のアクセストークン
     * @param id 固有識別子(ucode)
     * @param sameAs 固有識別子（列車ID）
     * @param trainNumber 列車番号
     * @param trainType 列車種別ID
     * @param railway 路線ID
     * @param trainOwner 車両の所属会社の事業者ID
     * @param railDirection 鉄道方面ID
     * @param delay 遅延（秒）
     * @param startingStation 列車の始発駅の駅ID
     * @param terminalStation 列車の終着駅の駅ID
     * @param fromStation 列車が出発した駅の駅ID
     * @param toStation 列車が向かっている駅の駅ID
     * @return 列車ロケーション情報リスト
     */
    @Headers("connection: close")
    @GET("api/v2/datapoints?rdf:type=odpt:Train")
    fun getTrainAsync(
        @Query("acl:consumerKey") consumerKey: String,
        @Query("@id") id: String? = null,
        @Query("owl:sameAs") sameAs: String? = null,
        @Query("odpt:trainNumber") trainNumber: String? = null,
        @Query("odpt:trainType") trainType: String? = null,
        @Query("odpt:railway") railway: String? = null,
        @Query("odpt:trainOwner") trainOwner: String? = null,
        @Query("odpt:railDirection") railDirection: String? = null,
        @Query("odpt:delay") delay: Int? = null,
        @Query("odpt:startingStation") startingStation: String? = null,
        @Query("odpt:terminalStation") terminalStation: String? = null,
        @Query("odpt:fromStation") fromStation: String? = null,
        @Query("odpt:toStation") toStation: String? = null
    ): Deferred<List<OdptTrain>>

    /**
     * 駅情報取得（データ検索API利用）
     *
     * @param consumerKey APIアクセス用のアクセストークン
     * @param id 固有識別子(ucode)
     * @param sameAs 固有識別子（駅ID）
     * @param title 駅名
     * @param operator 運行会社の事業者ID
     * @param railway 路線ID
     * @param stationCode 駅コード
     * @return 駅情報リスト
     */
    @Headers("connection: close")
    @GET("api/v2/datapoints?rdf:type=odpt:Station")
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
     * 駅施設情報取得
     *
     * @param consumerKey APIアクセス用のアクセストークン
     * @param id 固有識別子(ucode)
     * @param sameAs 固有識別子（駅施設情報ID）
     * @return 駅施設情報リスト
     */
    @Headers("connection: close")
    @GET("api/v2/datapoints?rdf:type=odpt:StationFacility")
    fun getStationFacilityAsync(
        @Query("acl:consumerKey") consumerKey: String,
        @Query("@id") id: String? = null,
        @Query("owl:sameAs") sameAs: String? = null
    ): Deferred<List<OdptStationFacility>>

    /**
     * 駅乗降人員数情報取得
     *
     * @param consumerKey APIアクセス用のアクセストークン
     * @param id 固有識別子(ucode)
     * @param sameAs 固有識別子（駅乗降人員数情報ID）
     * @param operator 運行会社の事業者ID
     * @param surveyYear 調査年度
     * @return 駅乗降人員数情報リスト
     */
    @Headers("connection: close")
    @GET("api/v2/datapoints?rdf:type=odpt:PassengerSurvey")
    fun getPassengerSurveyAsync(
        @Query("acl:consumerKey") consumerKey: String,
        @Query("@id") id: String? = null,
        @Query("owl:sameAs") sameAs: String? = null,
        @Query("odpt:operator") operator: String? = null,
        @Query("odpt:surveyYear") surveyYear: String? = null
    ): Deferred<List<OdptPassengerSurvey>>

    /**
     * 鉄道路線情報取得（データ検索API利用）
     *
     * @param consumerKey APIアクセス用のアクセストークン
     * @param id 固有識別子(ucode)
     * @param sameAs 固有識別子（鉄道路線ID）
     * @param title 運行系統名
     * @param operator 運行会社の事業者ID
     * @param lineCode 路線コード
     * @return 鉄道路線情報リスト
     */
    @Headers("connection: close")
    @GET("api/v2/datapoints?rdf:type=odpt:Railway")
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
     * @param id 固有識別子(ucode)
     * @param sameAs 固有識別子（運賃情報ID）
     * @param operator 運行会社の事業者ID
     * @param fromStation 駅間の始点駅の駅ID
     * @param toStation 駅間の終点駅の駅ID
     * @param ticketFare 切符利用時の運賃
     * @param childTicketFare 切符利用時の子供運賃
     * @param icCardFare ICカード利用時の運賃
     * @param childIcCardFare ICカード利用時の子供運賃
     * @return 運賃情報リスト
     */
    @Headers("connection: close")
    @GET("api/v2/datapoints?rdf:type=odpt:RailwayFare")
    fun getRailwayFareAsync(
        @Query("acl:consumerKey") consumerKey: String,
        @Query("@id") id: String? = null,
        @Query("owl:sameAs") sameAs: String? = null,
        @Query("odpt:operator") operator: String? = null,
        @Query("odpt:fromStation") fromStation: String? = null,
        @Query("odpt:toStation") toStation: String? = null,
        @Query("odpt:ticketFare") ticketFare: Int? = null,
        @Query("odpt:childTicketFare") childTicketFare: Int? = null,
        @Query("odpt:icCardFare") icCardFare: Int? = null,
        @Query("odpt:childIcCardFare") childIcCardFare: Int? = null
    ): Deferred<List<OdptRailwayFare>>

    /**
     * 列車時刻表情報取得
     *
     * @param consumerKey APIアクセス用のアクセストークン
     * @param id 固有識別子(ucode)
     * @param sameAs 固有識別子（列車時刻表情報ID）
     * @param trainNumber 列車番号
     * @param railway 路線ID
     * @param operator 運行会社の事業者ID
     * @param trainType 列車種別ID
     * @param railDirection 鉄道方面ID
     * @param startingStation 列車の始発駅の駅ID
     * @param terminalStation 列車の終着駅の駅ID
     * @param trainOwner 車両の所属会社のID
     * @param train 列車ID
     * @return 駅時刻表情報リスト
     */
    @Headers("connection: close")
    @GET("api/v2/datapoints?rdf:type=odpt:TrainTimetable")
    fun getTrainTimetableAsync(
        @Query("acl:consumerKey") consumerKey: String,
        @Query("@id") id: String? = null,
        @Query("owl:sameAs") sameAs: String? = null,
        @Query("odpt:trainNumber") trainNumber: String? = null,
        @Query("odpt:railway") railway: String? = null,
        @Query("odpt:operator") operator: String? = null,
        @Query("odpt:trainType") trainType: String? = null,
        @Query("odpt:railDirection") railDirection: String? = null,
        @Query("odpt:startingStation") startingStation: String? = null,
        @Query("odpt:terminalStation") terminalStation: String? = null,
        @Query("odpt:trainOwner") trainOwner: String? = null,
        @Query("odpt:train") train: String? = null
    ): Deferred<List<OdptTrainTimetable>>

    /**
     * 駅出入口情報取得（データ検索API利用）
     *
     * @param consumerKey APIアクセス用のアクセストークン
     * @param id 固有識別子(ucode)
     * @param title 地物名
     * @return 駅出入口情報リスト
     */
    @Headers("connection: close")
    @GET("api/v2/datapoints?rdf:type=ug:Poi&ugsrv:categoryName=出入口")
    fun getUgPoiAsync(
        @Query("acl:consumerKey") consumerKey: String,
        @Query("@id") id: String? = null,
        @Query("dc:title") title: String? = null
    ): Deferred<List<UgPoi>>
}
