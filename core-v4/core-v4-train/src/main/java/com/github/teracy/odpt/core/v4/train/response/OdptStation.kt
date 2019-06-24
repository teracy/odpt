package com.github.teracy.odpt.core.v4.train.response

import com.github.teracy.odpt.core.geojson.response.Geometry
import com.github.teracy.odpt.model.*
import com.squareup.moshi.Json

/**
 * v4版駅情報APIレスポンス
 */
data class OdptStation(
    /**
     * 固有識別子（ucode）
     */
    @field:Json(name = "@id")
    val id: String,
    /**
     * データ生成日時（ISO8601 日付時刻形式）
     */
    @field:Json(name = "dc:date")
    val date: String,
    /**
     * 固有識別（駅ID）
     */
    @field:Json(name = "owl:sameAs")
    val sameAs: String,
    /**
     * 駅名（日本語）
     */
    @field:Json(name = "dc:title")
    val title: String?,
    /**
     * 駅名（多言語対応）
     */
    @field:Json(name = "odpt:stationTitle")
    val titleMap: Map<String, String>?,
    /**
     * 運行会社のID
     */
    @field:Json(name = "odpt:operator")
    val operator: String,
    /**
     * 駅が所属する鉄道路線のID
     * @see OdptRailway.sameAs
     */
    @field:Json(name = "odpt:railway")
    val railway: String,
    /**
     * 駅コード
     */
    @field:Json(name = "odpt:stationCode")
    val stationCode: String?,
    /**
     * 代表点の経度 (10進表記、測地系はWGS84)
     */
    @field:Json(name = "geo:long")
    val longitude: Double?,
    /**
     * 代表点の緯度 (10進表記、測地系はWGS84)
     */
    @field:Json(name = "geo:lat")
    val latitude: Double?,
    /**
     * GeoJSON形式による地物情報
     */
    @field:Json(name = "ug:region")
    val region: Geometry?,
    /**
     * 駅出入口のIDのリスト
     */
    @field:Json(name = "odpt:exit")
    val exitList: List<String>?,
    /**
     * 乗り換え可能路線のIDのリスト
     * @see OdptRailway.sameAs
     */
    @field:Json(name = "odpt:connectingRailway")
    val connectingRailwayList: List<String>?,
    /**
     * 駅時刻表のIDのリスト
     * @see OdptStation.sameAs
     */
    @field:Json(name = "odpt:stationTimetable")
    val stationTimetableList: List<String>?,
    /**
     * 駅乗降人員数情報のIDのリスト
     * @see OdptPassengerSurvey.sameAs
     */
    @field:Json(name = "odpt:passengerSurvey")
    val passengerSurveyList: List<String>?
) {
    /**
     * 固有識別子を表す駅ID
     */
    val stationId: StationId
        get() = StationId(sameAs)
    /**
     * 運行会社の事業者ID
     */
    val operatorId: OperatorId
        get() = OperatorId(operator)
    /**
     * 駅が所属する鉄道路線の鉄道路線ID
     */
    val railwayId: RailwayId
        get() = RailwayId(railway)
    /**
     * 乗り換え可能路線の鉄道路線IDのリスト
     */
    val connectingRailwayIdList: List<RailwayId>?
        get() = connectingRailwayList?.map { RailwayId(it) }
    /**
     * 駅時刻表情報IDのリスト
     */
    val stationTimetableIdList: List<StationTimetableId>?
        get() = stationTimetableList?.map { StationTimetableId(it) }
    /**
     * 駅乗降人員数情報IDのリスト
     */
    val passengerSurveyIdList: List<PassengerSurveyId>?
        get() = passengerSurveyList?.map { PassengerSurveyId(it) }
}
