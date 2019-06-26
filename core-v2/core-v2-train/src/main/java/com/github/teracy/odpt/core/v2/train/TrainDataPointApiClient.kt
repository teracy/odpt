package com.github.teracy.odpt.core.v2.train

import com.github.teracy.odpt.core.v2.train.request.*
import com.github.teracy.odpt.core.v2.train.response.*
import com.github.teracy.odpt.model.ApiClient

/**
 * v2版データ取得・検索APIを利用した鉄道情報クライアント
 */
interface TrainDataPointApiClient : ApiClient {
    /**
     * v2版鉄道データ取得・検索API接続サービスのインスタンス
     */
    fun trainDataPointApiService(): TrainDataPointApiService

    /**
     * 駅時刻表情報取得
     *
     * @param id 固有識別子(ucode)
     * @param sameAs 固有識別子（駅時刻表情報ID）
     * @param station 駅ID
     * @param railway 路線ID
     * @param operator 運行会社の事業者ID
     * @param railDirection 鉄道方面ID
     * @return 駅時刻表情報リスト
     */
    suspend fun getStationTimetable(
        id: String? = null,
        sameAs: String? = null,
        station: String? = null,
        railway: String? = null,
        operator: String? = null,
        railDirection: String? = null
    ): List<OdptStationTimetable> {
        return trainDataPointApiService().getStationTimetableAsync(
            consumerKey = consumerKey(),
            id = id,
            sameAs = sameAs,
            station = station,
            railway = railway,
            operator = operator,
            railDirection = railDirection
        ).await()
    }

    /**
     * 列車運行情報取得
     *
     * @param id 固有識別子(ucode)
     * @param operator 運行会社の事業者ID
     * @param railway 路線ID
     * @param trainInformationStatus 運行状況ステータス
     * @return 列車運行情報リスト
     */
    suspend fun getTrainInformation(
        id: String? = null,
        operator: String? = null,
        railway: String? = null,
        trainInformationStatus: String? = null
    ): List<OdptTrainInformation> {
        return trainDataPointApiService().getTrainInformationAsync(
            consumerKey = consumerKey(),
            id = id,
            operator = operator,
            railway = railway,
            trainInformationStatus = trainInformationStatus
        ).await()
    }

    /**
     * 列車ロケーション情報取得
     *
     * @param id 固有識別子(ucode)
     * @param sameAs 固有識別子（列車ID）
     * @param trainNumber 列車番号
     * @param trainType 列車種別ID
     * @param railway 鉄道路線ID
     * @param trainOwner 列車所属会社ID
     * @param railDirection 鉄道方面ID
     * @param delay 遅延時間（秒）
     * @param startingStation 列車の始発駅の駅ID
     * @param terminalStation 列車の終着駅の駅ID
     * @param fromStation 列車が出発した駅の駅ID
     * @param toStation 列車が向かっている駅の駅ID
     * @return 列車ロケーション情報リスト
     */
    suspend fun getTrain(
        id: String? = null,
        sameAs: String? = null,
        trainNumber: String? = null,
        trainType: String? = null,
        railway: String? = null,
        trainOwner: String? = null,
        railDirection: String? = null,
        delay: Int? = null,
        startingStation: String? = null,
        terminalStation: String? = null,
        fromStation: String? = null,
        toStation: String? = null
    ): List<OdptTrain> {
        return trainDataPointApiService().getTrainAsync(
            consumerKey = consumerKey(),
            id = id,
            sameAs = sameAs,
            trainNumber = trainNumber,
            trainType = trainType,
            railway = railway,
            trainOwner = trainOwner,
            railDirection = railDirection,
            delay = delay,
            startingStation = startingStation,
            terminalStation = terminalStation,
            fromStation = fromStation,
            toStation = toStation
        ).await()
    }

    /**
     * 駅情報取得（データ検索API利用）
     *
     * @param id 固有識別子(ucode)
     * @param sameAs 固有識別子（駅ID）
     * @param title 駅名（部分一致。例えば「銀座」で検索すると銀座線・丸ノ内線・日比谷線の「銀座」、日比谷線の「東銀座」、有楽町線の「銀座一丁目」が該当する）
     * @param operator 運行会社の事業者ID
     * @param railway 路線ID
     * @param stationCode 駅コード（前方一致。例えば「G」で検索すると銀座線の全駅が、「G0」で検索すると「G01（銀座線渋谷）」～「G09（銀座線銀座）」が該当する）
     * @return 駅情報リスト
     */
    suspend fun getStation(
        id: String? = null,
        sameAs: String? = null,
        title: String? = null,
        operator: String? = null,
        railway: String? = null,
        stationCode: String? = null
    ): List<OdptStation> {
        return trainDataPointApiService().getStationAsync(
            consumerKey = consumerKey(),
            id = id,
            sameAs = sameAs,
            title = title,
            operator = operator,
            railway = railway,
            stationCode = stationCode
        ).await()
    }

    /**
     * 駅施設情報取得
     *
     * @param id 固有識別子(ucode)
     * @param sameAs 固有識別子（駅施設情報ID）
     * @return 駅施設情報リスト
     */
    suspend fun getStationFacility(
        id: String? = null,
        sameAs: String? = null
    ): List<OdptStationFacility> {
        return trainDataPointApiService().getStationFacilityAsync(
            consumerKey = consumerKey(),
            id = id,
            sameAs = sameAs
        ).await()
    }

    /**
     * 駅乗降人員数情報取得
     *
     * @param id 固有識別子(ucode)
     * @param sameAs 固有識別子（駅乗降人員数情報ID）
     * @param operator 運行会社の事業者ID
     * @param surveyYear 調査年度
     * @return 駅乗降人員数情報リスト
     */
    suspend fun getPassengerSurvey(
        id: String? = null,
        sameAs: String? = null,
        operator: String? = null,
        surveyYear: String? = null
    ): List<OdptPassengerSurvey> {
        return trainDataPointApiService().getPassengerSurveyAsync(
            consumerKey = consumerKey(),
            id = id,
            sameAs = sameAs,
            operator = operator,
            surveyYear = surveyYear
        ).await()
    }

    /**
     * 鉄道路線情報取得（データ検索API利用）
     *
     * @param id 固有識別子(ucode)
     * @param sameAs 固有識別子（鉄道路線ID）
     * @param title 運行系統名（「線」を含まない部分一致。例えば「楽」で検索すると有楽町線が該当するが、「有楽町線」では該当する路線はない）
     * @param operator 運行会社の事業者ID
     * @param lineCode 路線コード（部分一致。大文字小文字を区別しない。例えば「m」で検索すると「M（丸ノ内線）」と「Mb（丸ノ内支線）」が、「B」で検索すると「Mb（丸ノ内支線）」が該当する）
     * @return 鉄道路線情報リスト
     */
    suspend fun getRailway(
        id: String? = null,
        sameAs: String? = null,
        title: String? = null,
        operator: String? = null,
        lineCode: String? = null
    ): List<OdptRailway> {
        return trainDataPointApiService().getRailwayAsync(
            consumerKey = consumerKey(),
            id = id,
            sameAs = sameAs,
            title = title,
            operator = operator,
            lineCode = lineCode
        ).await()
    }

    /**
     * 運賃情報取得
     *
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
    suspend fun getRailwayFare(
        id: String? = null,
        sameAs: String? = null,
        operator: String? = null,
        fromStation: String? = null,
        toStation: String? = null,
        ticketFare: Int? = null,
        childTicketFare: Int? = null,
        icCardFare: Int? = null,
        childIcCardFare: Int? = null
    ): List<OdptRailwayFare> {
        return trainDataPointApiService().getRailwayFareAsync(
            consumerKey = consumerKey(),
            id = id,
            sameAs = sameAs,
            operator = operator,
            fromStation = fromStation,
            toStation = toStation,
            ticketFare = ticketFare,
            childTicketFare = childTicketFare,
            icCardFare = icCardFare,
            childIcCardFare = childIcCardFare
        ).await()
    }

    /**
     * 列車時刻表情報取得
     *
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
     * @return 列車時刻表情報リスト
     */
    suspend fun getTrainTimetable(
        id: String? = null,
        sameAs: String? = null,
        trainNumber: String? = null,
        railway: String? = null,
        operator: String? = null,
        trainType: String? = null,
        railDirection: String? = null,
        startingStation: String? = null,
        terminalStation: String? = null,
        trainOwner: String? = null,
        train: String? = null
    ): List<OdptTrainTimetable> {
        return trainDataPointApiService().getTrainTimetableAsync(
            consumerKey = consumerKey(),
            id = id,
            sameAs = sameAs,
            trainNumber = trainNumber,
            railway = railway,
            operator = operator,
            trainType = trainType,
            railDirection = railDirection,
            startingStation = startingStation,
            terminalStation = terminalStation,
            trainOwner = trainOwner,
            train = train
        ).await()
    }

    /**
     * 駅時刻表情報取得
     *
     * @param argument 検索引数
     * @return 駅時刻表情報リスト
     */
    suspend fun getStationTimetable(argument: StationTimetableArgument): List<OdptStationTimetable> {
        return getStationTimetable(
            id = argument.id,
            sameAs = argument.sameAs,
            station = argument.station,
            railway = argument.railway,
            operator = argument.operator,
            railDirection = argument.railDirection
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
            id = argument.id,
            operator = argument.operator,
            railway = argument.railway
        )
    }

    /**
     * 列車ロケーション情報取得
     *
     * @param argument 検索引数
     * @param delay 遅延時間（任意。単位は秒。5分以内の遅れは切り捨て）
     * @return 列車ロケーション情報リスト
     */
    suspend fun getTrain(argument: TrainArgument, delay: Int? = null): List<OdptTrain> {
        return getTrain(
            id = argument.id,
            sameAs = argument.sameAs,
            trainNumber = argument.trainNumber,
            trainType = argument.trainType,
            railway = argument.railway,
            trainOwner = argument.trainOwner,
            railDirection = argument.railDirection,
            delay = delay,
            startingStation = argument.startingStation,
            terminalStation = argument.terminalStation,
            fromStation = argument.fromStation,
            toStation = argument.toStation
        )
    }


    /**
     * 駅情報取得
     *
     * @param argument 検索引数
     * @return 駅情報リスト
     */
    suspend fun getStation(argument: StationArgument): List<OdptStation> {
        return getStation(
            id = argument.id,
            sameAs = argument.sameAs,
            title = argument.title,
            operator = argument.operator,
            railway = argument.railway,
            stationCode = argument.stationCode
        )
    }

    /**
     * 駅施設情報取得
     *
     * @param argument 検索引数
     * @return 駅施設情報リスト
     */
    suspend fun getStationFacility(argument: StationFacilityArgument): List<OdptStationFacility> {
        return getStationFacility(
            id = argument.id,
            sameAs = argument.sameAs
        )
    }

    /**
     * 駅乗降人員数情報取得
     *
     * @param argument 検索引数
     * @return 駅乗降人員数情報リスト
     */
    suspend fun getPassengerSurvey(argument: PassengerSurveyArgument): List<OdptPassengerSurvey> {
        return getPassengerSurvey(
            id = argument.id,
            sameAs = argument.sameAs,
            operator = argument.operator,
            surveyYear = argument.surveyYear
        )
    }

    /**
     * 鉄道路線情報取得（データ検索API利用）
     *
     * @param argument 検索引数
     * @return 鉄道路線情報リスト
     */
    suspend fun getRailway(argument: RailwayArgument): List<OdptRailway> {
        return getRailway(
            id = argument.id,
            sameAs = argument.sameAs,
            title = argument.title,
            operator = argument.operator,
            lineCode = argument.lineCode
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
            id = argument.id,
            sameAs = argument.sameAs,
            operator = argument.operator,
            fromStation = argument.fromStation,
            toStation = argument.toStation
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
            id = argument.id,
            sameAs = argument.sameAs,
            trainNumber = argument.trainNumber,
            railway = argument.railway,
            operator = argument.operator,
            trainType = argument.trainType,
            railDirection = argument.railDirection,
            startingStation = argument.startingStation,
            terminalStation = argument.terminalStation,
            trainOwner = argument.trainOwner,
            train = argument.train
        )
    }

    /**
     * 駅出入口情報取得（データ検索API利用）
     *
     * @param id 固有識別子(ucode)
     * @param title 地物名。エレベータには「エレベータ」という文字列を含む。「出入口」の文字列の後に出口番号が続く
     * @return 駅出入口情報リスト
     */
    suspend fun getUgPoi(id: String? = null, title: String? = null): List<UgPoi> {
        return trainDataPointApiService().getUgPoiAsync(
            consumerKey = consumerKey(),
            id = id,
            title = title
        ).await()
    }
}
