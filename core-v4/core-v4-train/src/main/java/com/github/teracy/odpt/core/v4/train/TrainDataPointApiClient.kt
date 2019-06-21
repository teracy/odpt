package com.github.teracy.odpt.core.v4.train

import com.github.teracy.odpt.core.v4.train.request.*
import com.github.teracy.odpt.core.v4.train.response.*
import com.github.teracy.odpt.model.ApiClient

/**
 * v4版データ取得・検索APIを利用した鉄道情報クライアント
 *
 * 個々のパラメータについてはリスト内でのOR条件、別パラメータはAND条件となる
 *
 * 例：[駅乗降人員数情報取得][getPassengerSurvey]の場合
 * 駅IDのリストのみを与え、その値が「odpt.Station:Toei.Mita.Hibiya」と「odpt.Station:Toei.Shinjuku.BakuroYokoyama」の2つの場合は都営三田線日比谷駅と都営新宿線馬喰横山駅のデータが取得できる
 * 加えて路線IDのリストを与え、その値が「odpt.Railway:Toei.Mita」のみの場合は「駅が都営三田線日比谷駅または都営新宿線馬喰横山駅、かつ路線が都営三田線」になるので都営三田線日比谷駅のデータのみが取得できる
 */
interface TrainDataPointApiClient : ApiClient {
    /**
     * v4版鉄道データ取得・検索API接続サービスのインスタンスを取得する
     */
    fun trainDataPointApiService(): TrainDataPointApiService

    /**
     * 駅乗降人員数情報取得
     *
     * @param idList 固有識別子(ucode)のリスト
     * @param sameAsList 固有識別子別名（駅乗降人員数情報ID）のリスト
     * @param operatorList 運行会社の事業者IDのリスト
     * @param station 駅ID
     * @param railway 路線ID
     * @return 駅乗降人員数情報リスト
     */
    suspend fun getPassengerSurvey(
        idList: List<String>? = null,
        sameAsList: List<String>? = null,
        operatorList: List<String>? = null,
        station: String? = null,
        railway: String? = null
    ): List<OdptPassengerSurvey> {
        return trainDataPointApiService().getPassengerSurveyAsync(
            consumerKey = consumerKey(),
            id = idList.concatenate(),
            sameAs = sameAsList.concatenate(),
            operator = operatorList.concatenate(),
            station = station,
            railway = railway
        ).await()
    }

    /**
     * 進行方向情報取得
     *
     * @param idList 固有識別子(ucode)のリスト
     * @param sameAsList 固有識別子別名（鉄道方面ID）のリスト
     * @return 進行方向情報リスト
     */
    suspend fun getRailDirection(
        idList: List<String>? = null,
        sameAsList: List<String>? = null
    ): List<OdptRailDirection> {
        return trainDataPointApiService().getRailDirectionAsync(
            consumerKey = consumerKey(),
            id = idList.concatenate(),
            sameAs = sameAsList.concatenate()
        ).await()
    }

    /**
     * 鉄道路線情報取得
     *
     * @param idList 固有識別子(ucode)のリスト
     * @param sameAsList 固有識別子別名（鉄道路線ID）のリスト
     * @param titleList 運行系統名（完全一致）のリスト
     * @param operatorList 運行会社の事業者IDのリスト
     * @param lineCodeList 路線コード（完全一致）のリスト
     * @return 鉄道路線情報リスト
     */
    suspend fun getRailway(
        idList: List<String>? = null,
        sameAsList: List<String>? = null,
        titleList: List<String>? = null,
        operatorList: List<String>? = null,
        lineCodeList: List<String>? = null
    ): List<OdptRailway> {
        return trainDataPointApiService().getRailwayAsync(
            consumerKey = consumerKey(),
            id = idList.concatenate(),
            sameAs = sameAsList.concatenate(),
            title = titleList.concatenate(),
            operator = operatorList.concatenate(),
            lineCode = lineCodeList.concatenate()
        ).await()
    }

    /**
     * 運賃情報取得
     * 事業者を跨いで乗り継いだ場合の運賃は取得できない
     *
     * @param idList 固有識別子(ucode)のリスト
     * @param sameAsList 固有識別子別名（運賃情報ID）のリスト
     * @param operatorList 運行会社の事業者IDのリスト
     * @param fromStationList 駅間の始点駅の駅IDのリスト
     * @param toStationList 駅間の終点駅の駅IDのリスト
     * @return 運賃情報リスト
     */
    suspend fun getRailwayFare(
        idList: List<String>? = null,
        sameAsList: List<String>? = null,
        operatorList: List<String>? = null,
        fromStationList: List<String>? = null,
        toStationList: List<String>? = null
    ): List<OdptRailwayFare> {
        return trainDataPointApiService().getRailwayFareAsync(
            consumerKey = consumerKey(),
            id = idList.concatenate(),
            sameAs = sameAsList.concatenate(),
            operator = operatorList.concatenate(),
            fromStation = fromStationList.concatenate(),
            toStation = toStationList.concatenate()
        ).await()
    }

    /**
     * 駅情報取得（データ検索API利用）
     *
     * @param idList 固有識別子(ucode)のリスト
     * @param sameAsList 固有識別子別名（駅ID）のリスト
     * @param titleList 駅名（完全一致）のリスト
     * @param operatorList 運行会社の事業者IDのリスト
     * @param railwayList 路線IDのリスト
     * @param stationCodeList 駅コード（完全一致）のリスト
     * @return 駅情報リスト
     */
    suspend fun getStation(
        idList: List<String>? = null,
        sameAsList: List<String>? = null,
        titleList: List<String>? = null,
        operatorList: List<String>? = null,
        railwayList: List<String>? = null,
        stationCodeList: List<String>? = null
    ): List<OdptStation> {
        return trainDataPointApiService().getStationAsync(
            consumerKey = consumerKey(),
            id = idList.concatenate(),
            sameAs = sameAsList.concatenate(),
            title = titleList.concatenate(),
            operator = operatorList.concatenate(),
            railway = railwayList.concatenate(),
            stationCode = stationCodeList.concatenate()
        ).await()
    }

    /**
     * 駅時刻表情報取得
     *
     * @param idList 固有識別子(ucode)のリスト
     * @param sameAsList 固有識別子別名（駅時刻表情報ID）のリスト
     * @param stationList 駅IDのリスト
     * @param railwayList 路線IDのリスト
     * @param operatorList 運行会社の事業者IDのリスト
     * @param railDirectionList 鉄道方面IDのリスト
     * @param calendarList 時刻表を取得したい曜日・日付の日付情報IDのリスト
     * @param dateList 時刻表を取得したい特定日付（ISO8601 日付時刻形式）のリスト
     * @return 駅時刻表情報リスト
     */
    suspend fun getStationTimetable(
        idList: List<String>? = null,
        sameAsList: List<String>? = null,
        stationList: List<String>? = null,
        railwayList: List<String>? = null,
        operatorList: List<String>? = null,
        railDirectionList: List<String>? = null,
        calendarList: List<String>? = null,
        dateList: List<String>? = null
    ): List<OdptStationTimetable> {
        return trainDataPointApiService().getStationTimetableAsync(
            consumerKey = consumerKey(),
            id = idList.concatenate(),
            sameAs = sameAsList.concatenate(),
            station = stationList.concatenate(),
            railway = railwayList.concatenate(),
            operator = operatorList.concatenate(),
            railDirection = railDirectionList.concatenate(),
            calendar = calendarList.concatenate(),
            date = dateList.concatenate()
        ).await()
    }

    /**
     * 列車ロケーション情報取得
     *
     * @param idList 固有識別子(ucode)のリスト
     * @param sameAsList 固有識別子別名（列車ID）のリスト
     * @param operatorList 列車情報を配信する事業者のIDのリスト
     * @param railwayList 当該列車が運行している路線のIDのリスト
     * @return 列車ロケーション情報リスト
     */
    suspend fun getTrain(
        idList: List<String>? = null,
        sameAsList: List<String>? = null,
        operatorList: List<String>? = null,
        railwayList: List<String>? = null
    ): List<OdptTrain> {
        return trainDataPointApiService().getTrainAsync(
            consumerKey = consumerKey(),
            id = idList.concatenate(),
            sameAs = sameAsList.concatenate(),
            operator = operatorList.concatenate(),
            railway = railwayList.concatenate()
        ).await()
    }

    /**
     * 列車運行情報取得
     *
     * @param idList 固有識別子(ucode)のリスト
     * @param sameAsList 固有識別子別名（列車運行情報ID）のリスト
     * @param operatorList 運行情報を配信する事業者のIDのリスト
     * @param railwayList 運行情報が発生した路線のIDのリスト
     * @return 列車運行情報リスト
     */
    suspend fun getTrainInformation(
        idList: List<String>? = null,
        sameAsList: List<String>? = null,
        operatorList: List<String>? = null,
        railwayList: List<String>? = null
    ): List<OdptTrainInformation> {
        return trainDataPointApiService().getTrainInformationAsync(
            consumerKey = consumerKey(),
            id = idList.concatenate(),
            sameAs = sameAsList.concatenate(),
            operator = operatorList.concatenate(),
            railway = railwayList.concatenate()
        ).await()
    }

    /**
     * 列車時刻表情報取得
     *
     * @param idList 固有識別子(ucode)のリスト
     * @param sameAsList 固有識別子別名（列車時刻表情報ID）のリスト
     * @param trainNumberList 列車番号のリスト
     * @param railwayList 路線IDのリスト
     * @param operatorList 運行会社の事業者IDのリスト
     * @param trainTypeList 列車種別IDのリスト
     * @param trainList 列車IDのリスト
     * @param calendarList 時刻表を取得したい曜日・日付の日付情報IDのリスト
     * @return 列車時刻表情報リスト
     */
    suspend fun getTrainTimetable(
        idList: List<String>? = null,
        sameAsList: List<String>? = null,
        trainNumberList: List<String>? = null,
        railwayList: List<String>? = null,
        operatorList: List<String>? = null,
        trainTypeList: List<String>? = null,
        trainList: List<String>? = null,
        calendarList: List<String>? = null
    ): List<OdptTrainTimetable> {
        return trainDataPointApiService().getTrainTimetableAsync(
            consumerKey = consumerKey(),
            id = idList.concatenate(),
            sameAs = sameAsList.concatenate(),
            trainNumber = trainNumberList.concatenate(),
            railway = railwayList.concatenate(),
            operator = operatorList.concatenate(),
            trainType = trainTypeList.concatenate(),
            train = trainList.concatenate(),
            calendar = calendarList.concatenate()
        ).await()
    }

    /**
     * 列車種別情報取得
     *
     * @param idList 固有識別子(ucode)のリスト
     * @param sameAsList 固有識別子別名（鉄道路線ID）のリスト
     * @param operatorList 運行会社の事業者IDのリスト
     * @return 列車種別情報リスト
     */
    suspend fun getTrainType(
        idList: List<String>? = null,
        sameAsList: List<String>? = null,
        operatorList: List<String>? = null
    ): List<OdptTrainType> {
        return trainDataPointApiService().getTrainTypeAsync(
            consumerKey = consumerKey(),
            id = idList.concatenate(),
            sameAs = sameAsList.concatenate(),
            operator = operatorList.concatenate()
        ).await()
    }

    /**
     * 駅乗降人員数情報取得
     *
     * @param argument 検索引数
     * @return 駅乗降人員数情報リスト
     */
    suspend fun getPassengerSurvey(argument: PassengerSurveyArgument): List<OdptPassengerSurvey> {
        return getPassengerSurvey(
            idList = argument.idList,
            sameAsList = argument.sameAsList,
            operatorList = argument.operatorList,
            station = argument.station,
            railway = argument.railway
        )
    }

    /**
     * 進行方向情報取得
     *
     * @param argument 検索引数
     * @return 進行方向情報リスト
     */
    suspend fun getRailDirection(argument: RailDirectionArgument): List<OdptRailDirection> {
        return getRailDirection(idList = argument.idList, sameAsList = argument.sameAsList)
    }

    /**
     * 鉄道路線情報取得
     *
     * @param argument 検索引数
     * @return 鉄道路線情報リスト
     */
    suspend fun getRailway(argument: RailwayArgument): List<OdptRailway> {
        return getRailway(
            idList = argument.idList,
            sameAsList = argument.sameAsList,
            titleList = argument.titleList,
            operatorList = argument.operatorList,
            lineCodeList = argument.lineCodeList
        )
    }

    /**
     * 運賃情報取得
     *
     * @param argument 検索引数
     * @return 運賃情報リスト
     */
    suspend fun getRailwayFare(argument: RailwayFareArgument): List<OdptRailwayFare> {
        return getRailwayFare(
            idList = argument.idList,
            sameAsList = argument.sameAsList,
            operatorList = argument.operatorList,
            fromStationList = argument.fromStationList,
            toStationList = argument.toStationList
        )
    }

    /**
     * 駅情報取得（データ検索API利用）
     *
     * @param argument 検索引数
     * @return 駅情報リスト
     */
    suspend fun getStation(argument: StationArgument): List<OdptStation> {
        return getStation(
            idList = argument.idList,
            sameAsList = argument.sameAsList,
            titleList = argument.titleList,
            operatorList = argument.operatorList,
            railwayList = argument.railwayList,
            stationCodeList = argument.stationCodeList
        )
    }

    /**
     * 駅時刻表情報取得
     *
     * @param argument 検索引数
     * @return 駅時刻表情報リスト
     */
    suspend fun getStationTimetable(argument: StationTimetableArgument): List<OdptStationTimetable> {
        return getStationTimetable(
            idList = argument.idList,
            sameAsList = argument.sameAsList,
            stationList = argument.stationList,
            railwayList = argument.railwayList,
            operatorList = argument.operatorList,
            railDirectionList = argument.railDirectionList,
            calendarList = argument.calendarList,
            dateList = argument.dateList
        )
    }

    /**
     * 列車ロケーション情報取得
     *
     * @param argument 検索引数
     * @return 列車ロケーション情報リスト
     */
    suspend fun getTrain(argument: TrainArgument): List<OdptTrain> {
        return getTrain(
            idList = argument.idList,
            sameAsList = argument.sameAsList,
            operatorList = argument.operatorList,
            railwayList = argument.railwayList
        )
    }

    /**
     * 列車運行情報取得
     *
     * @param argument 検索引数
     * @return 列車運行情報リスト
     */
    suspend fun getTrainInformation(argument: TrainInformationArgument): List<OdptTrainInformation> {
        return getTrainInformation(
            idList = argument.idList,
            sameAsList = argument.sameAsList,
            operatorList = argument.operatorList,
            railwayList = argument.railwayList
        )
    }

    /**
     * 列車時刻表情報取得
     *
     * @param argument 検索引数
     * @return 列車時刻表情報リスト
     */
    suspend fun getTrainTimetable(argument: TrainTimetableArgument): List<OdptTrainTimetable> {
        return getTrainTimetable(
            idList = argument.idList,
            sameAsList = argument.sameAsList,
            trainNumberList = argument.trainNumberList,
            railwayList = argument.railwayList,
            operatorList = argument.operatorList,
            trainTypeList = argument.trainTypeList,
            trainList = argument.trainList,
            calendarList = argument.calendarList
        )
    }

    /**
     * 列車種別情報取得
     *
     * @param argument 検索引数
     * @return 列車種別情報リスト
     */
    suspend fun getTrainType(argument: TrainTypeArgument): List<OdptTrainType> {
        return getTrainType(
            idList = argument.idList,
            sameAsList = argument.sameAsList,
            operatorList = argument.operatorList
        )
    }

    fun List<String>?.concatenate(): String? {
        return this?.joinToString(separator = ",")
    }
}
