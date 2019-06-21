package com.github.teracy.odpt.core.v4.train.request

import com.github.teracy.odpt.model.*

/**
 * 列車時刻表情報検索引数
 */
sealed class TrainTimetableArgument(
    /**
     * 固有識別子(ucode)のリスト
     */
    val idList: List<String>? = null,
    /**
     * 固有識別子別名（列車時刻表情報ID）のリスト
     */
    val sameAsList: List<String>? = null,
    /**
     * 列車番号のリスト
     */
    val trainNumberList: List<String>? = null,
    /**
     * 路線IDのリスト
     */
    val railwayList: List<String>? = null,
    /**
     * 運行会社の事業者IDのリスト
     */
    val operatorList: List<String>? = null,
    /**
     * 列車種別IDのリスト
     */
    val trainTypeList: List<String>? = null,
    /**
     * 列車IDのリスト
     */
    val trainList: List<String>? = null,
    /**
     * 時刻表を取得したい曜日・日付の日付情報IDのリスト
     */
    val calendarList: List<String>? = null
) {
    /**
     * 固有識別子による検索
     *
     * @param idList 固有識別子のリスト
     */
    class ById(idList: List<String>) :
        TrainTimetableArgument(idList = idList)

    /**
     * 列車時刻表情報IDによる検索
     *
     * @param trainTimetableIdList 列車時刻表情報IDのリスト
     */
    class ByTrainTimetableId(trainTimetableIdList: List<TrainTimetableId>) :
        TrainTimetableArgument(sameAsList = trainTimetableIdList.map { it.id })

    /**
     * 事業者IDによる検索
     *
     * @param operatorIdList 事業者IDのリスト（必須）
     * @param trainNumberList 列車番号のリスト（任意）
     * @param trainTypeIdList 列車種別IDのリスト（任意）
     * @param calendarIdList 時刻表を取得したい曜日・日付の日付情報IDのリスト（任意）
     */
    class ByOperatorId(
        operatorIdList: List<OperatorId>,
        trainNumberList: List<String>? = null,
        trainTypeIdList: List<TrainTypeId>? = null,
        calendarIdList: List<CalendarId>? = null
    ) :
        TrainTimetableArgument(
            operatorList = operatorIdList.map { it.id },
            trainNumberList = trainNumberList,
            trainTypeList = trainTypeIdList?.map { it.id },
            calendarList = calendarIdList?.map { it.id })

    /**
     * 路線IDによる検索
     *
     * @param railwayIdList 路線IDのリスト（必須）
     * @param trainNumberList 列車番号のリスト（任意）
     * @param trainTypeIdList 列車種別IDのリスト（任意）
     * @param calendarIdList 時刻表を取得したい曜日・日付の日付情報IDのリスト（任意）
     */
    class ByRailwayId(
        railwayIdList: List<RailwayId>,
        trainNumberList: List<String>? = null,
        trainTypeIdList: List<TrainTypeId>? = null,
        calendarIdList: List<CalendarId>? = null
    ) :
        TrainTimetableArgument(railwayList = railwayIdList.map { it.id },
            trainNumberList = trainNumberList,
            trainTypeList = trainTypeIdList?.map { it.id },
            calendarList = calendarIdList?.map { it.id })

    /**
     * 列車IDによる検索
     *
     * @param trainIdList 列車IDのリスト（必須）
     * @param calendarIdList 時刻表を取得したい曜日・日付の日付情報IDのリスト（任意）
     */
    class ByTrainId(
        trainIdList: List<TrainId>,
        calendarIdList: List<CalendarId>? = null
    ) :
        TrainTimetableArgument(trainList = trainIdList.map { it.id },
            calendarList = calendarIdList?.map { it.id })
}
