package com.github.teracy.odpt.core.v4.train.request

import com.github.teracy.odpt.model.*

/**
 * 駅時刻表情報検索引数
 */
sealed class StationTimetableArgument(
    /**
     * 固有識別子(ucode)のリスト
     */
    val idList: List<String>? = null,
    /**
     * 固有識別子別名（駅時刻表情報ID）のリスト
     */
    val sameAsList: List<String>? = null,
    /**
     * 駅IDのリスト
     */
    val stationList: List<String>? = null,
    /**
     * 路線IDのリスト
     */
    val railwayList: List<String>? = null,
    /**
     * 運行会社の事業者IDのリスト
     */
    val operatorList: List<String>? = null,
    /**
     * 鉄道方面IDのリスト
     */
    val railDirectionList: List<String>? = null,
    /**
     * 時刻表を取得したい曜日・日付の日付情報IDのリスト
     */
    val calendarList: List<String>? = null,
    /**
     * 時刻表を取得したい特定日付（ISO8601 日付時刻形式）のリスト
     */
    val dateList: List<String>? = null
) {
    /**
     * 固有識別子による検索
     *
     * @param idList 固有識別子のリスト
     */
    class ById(idList: List<String>) :
        StationTimetableArgument(idList = idList)

    /**
     * 駅時刻表情報IDによる検索
     *
     * @param stationTimetableIdList 駅時刻表情報IDのリスト
     */
    class ByStationTimetableId(stationTimetableIdList: List<StationTimetableId>) :
        StationTimetableArgument(sameAsList = stationTimetableIdList.map { it.id })

    /**
     * 事業者IDによる検索
     *
     * @param operatorIdList 事業者IDのリスト（必須）
     * @param railDirectionIdList 鉄道方面IDのリスト（任意）
     * @param calendarIdList 時刻表を取得したい曜日・日付の日付情報IDのリスト（任意）
     * @param dateList 時刻表を取得したい特定日付のリスト（任意）
     */
    class ByOperatorId(
        operatorIdList: List<OperatorId>,
        railDirectionIdList: List<RailDirectionId>? = null,
        calendarIdList: List<CalendarId>? = null,
        dateList: List<String>? = null
    ) :
        StationTimetableArgument(
            operatorList = operatorIdList.map { it.id },
            railDirectionList = railDirectionIdList?.map { it.id },
            calendarList = calendarIdList?.map { it.id },
            dateList = dateList
        )

    /**
     * 路線IDによる検索
     *
     * @param railwayIdList 路線IDのリスト（必須）
     * @param railDirectionIdList 鉄道方面IDのリスト（任意）
     * @param calendarIdList 時刻表を取得したい曜日・日付の日付情報IDのリスト（任意）
     * @param dateList 時刻表を取得したい特定日付のリスト（任意）
     */
    class ByRailwayId(
        railwayIdList: List<RailwayId>,
        railDirectionIdList: List<RailDirectionId>? = null,
        calendarIdList: List<CalendarId>? = null,
        dateList: List<String>? = null
    ) :
        StationTimetableArgument(
            railwayList = railwayIdList.map { it.id },
            railDirectionList = railDirectionIdList?.map { it.id },
            calendarList = calendarIdList?.map { it.id },
            dateList = dateList
        )

    /**
     * 駅IDによる検索
     *
     * @param stationIdList 駅IDのリスト（必須）
     * @param railDirectionIdList 鉄道方面IDのリスト（任意）
     * @param calendarIdList 時刻表を取得したい曜日・日付の日付情報IDのリスト（任意）
     * @param dateList 時刻表を取得したい特定日付のリスト（任意）
     */
    class ByStationId(
        stationIdList: List<StationId>,
        railDirectionIdList: List<RailDirectionId>? = null,
        calendarIdList: List<CalendarId>? = null,
        dateList: List<String>? = null
    ) :
        StationTimetableArgument(
            stationList = stationIdList.map { it.id },
            railDirectionList = railDirectionIdList?.map { it.id },
            calendarList = calendarIdList?.map { it.id },
            dateList = dateList
        )
}
