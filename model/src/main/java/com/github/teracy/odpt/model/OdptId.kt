package com.github.teracy.odpt.model

/**
 * 公共交通オープンデータ協議会のAPIで利用されるID情報の基底クラス
 * @param id IDの値
 */
sealed class OdptId(val id: String) {
// NOTE: バスAPIに関わるIDが事業者間で統一の命名規則になっていないため、基底で定義するのは一旦あきらめる
//    /**
//     * IDの値が正しいか判定する（クライアント側で作成した場合に利用する）
//     */
//    abstract fun validate(): Boolean
}

/**
 * 空港情報ID
 */
class AirportId(id: String) : OdptId(id)

/**
 * 空港ターミナル情報ID
 */
class AirportTerminalId(id: String) : OdptId(id)

/**
 * バス方面ID
 * @param id IDの値。命名ルール例：「odpt.BusDirection:会社名.方面名」
 * NOTE: APIで明確な命名ルールが定義されていない。基本的に会社毎に別の命名ルールであり、鉄道APIの駅時刻表情報ID→駅ID→路線ID→事業者IDのような他のIDの導出は不可能と考えた方が無難
 */
class BusDirectionId(id: String) : OdptId(id) {
    /**
     * [事業者ID][OperatorId]に変換
     */
    fun toOperatorId(): OperatorId {
        val operatorName = id.split(":")[1].split(".")[0]
        return OperatorId("odpt.Operator:$operatorName")
    }
}

/**
 * バス運行情報ID
 * @param id IDの値。命名ルール例：「odpt.Bus:会社名.路線名.系統パターン.方面番号.バス車両番号」
 * NOTE: APIで明確な命名ルールが定義されていない。基本的に会社毎に別の命名ルールであり、鉄道APIの駅時刻表情報ID→駅ID→路線ID→事業者IDのような他のIDの導出は不可能と考えた方が無難
 */
class BusId(id: String) : OdptId(id) {
    /**
     * [事業者ID][OperatorId]に変換
     */
    fun toOperatorId(): OperatorId {
        val operatorName = id.split(":")[1].split(".")[0]
        return OperatorId("odpt.Operator:$operatorName")
    }
}

/**
 * バス路線ID
 * @param id IDの値。命名ルール例：「odpt.Busroute:会社名.路線名」
 * NOTE: APIで明確な命名ルールが定義されていない。基本的に会社毎に別の命名ルールであり、鉄道APIの駅時刻表情報ID→駅ID→路線ID→事業者IDのような他のIDの導出は不可能と考えた方が無難
 */
class BusRouteId(id: String) : OdptId(id)

/**
 * バス運賃情報ID
 * @param id IDの値。命名ルール例：「odpt.BusroutePatternFare:乗車バス停標柱の会社名.乗車バス停標柱の路線名.乗車バス停標柱の系統パターン.乗車バス停標柱の方面番号.乗車バス停標柱の標柱順序.乗車バス停標柱.降車バス停標柱の会社名.降車バス停標柱の路線名.降車バス停標柱の系統パターン.降車バス停標柱の方面番号.降車バス停標柱の標柱順序.乗車バス停標柱」
 * NOTE: APIで明確な命名ルールが定義されていない。基本的に会社毎に別の命名ルールであり、鉄道APIの駅時刻表情報ID→駅ID→路線ID→事業者IDのような他のIDの導出は不可能と考えた方が無難
 */
class BusRoutePatternFareId(id: String) : OdptId(id) {
    /**
     * [事業者ID][OperatorId]に変換
     */
    fun toOperatorId(): OperatorId {
        val operatorName = id.split(":")[1].split(".")[0]
        return OperatorId("odpt.Operator:$operatorName")
    }
}

/**
 * バス系統ID（バス系統情報ID）
 * NOTE: バス系統IDの上位概念が[バス路線ID][BusRouteId]であるが、事業者により命名ルールが統一されていないため、必ずしも導出できるわけではないことに注意
 * @param id IDの値。命名ルール例：「odpt.BusroutePattern:会社名.路線名.系統パターン.方面番号」
 * NOTE: APIで明確な命名ルールが定義されていない。基本的に会社毎に別の命名ルールであり、鉄道APIの駅時刻表情報ID→駅ID→路線ID→事業者IDのような他のIDの導出は不可能と考えた方が無難
 */
class BusRoutePatternId(id: String) : OdptId(id) {
    /**
     * [事業者ID][OperatorId]に変換
     */
    fun toOperatorId(): OperatorId {
        val operatorName = id.split(":")[1].split(".")[0]
        return OperatorId("odpt.Operator:$operatorName")
    }
}

/**
 * バス停標柱ID
 * @param id IDの値。命名ルール例：「odpt.BusstopPole:会社名.バス停名.バス停番号.標柱番号」
 * NOTE: APIで明確な命名ルールが定義されていない。基本的に会社毎に別の命名ルールであり、鉄道APIの駅時刻表情報ID→駅ID→路線ID→事業者IDのような他のIDの導出は不可能と考えた方が無難
 */
class BusStopPoleId(id: String) : OdptId(id) {
    /**
     * [事業者ID][OperatorId]に変換
     */
    fun toOperatorId(): OperatorId {
        val operatorName = id.split(":")[1].split(".")[0]
        return OperatorId("odpt.Operator:$operatorName")
    }
}

/**
 * バス停標柱時刻表情報ID
 * @param id IDの値。命名ルール例：「odpt.BusstopPoleTimetable:会社名.路線名.バス停名.バス停番号.標柱番号.曜日・日付種別」
 * NOTE: APIで明確な命名ルールが定義されていない。基本的に会社毎に別の命名ルールであり、鉄道APIの駅時刻表情報ID→駅ID→路線ID→事業者IDのような他のIDの導出は不可能と考えた方が無難
 */
class BusStopPoleTimetableId(id: String) : OdptId(id) {
    /**
     * [事業者ID][OperatorId]に変換
     */
    fun toOperatorId(): OperatorId {
        val operatorName = id.split(":")[1].split(".")[0]
        return OperatorId("odpt.Operator:$operatorName")
    }
}

/**
 * バス時刻表情報ID
 * @param id IDの値。命名ルール例：「odpt.BusTimetable:会社名.路線名.系統パターン.方面番号.曜日・日付種別」
 * NOTE: APIで明確な命名ルールが定義されていない。基本的に会社毎に別の命名ルールであり、鉄道APIの駅時刻表情報ID→駅ID→路線ID→事業者IDのような他のIDの導出は不可能と考えた方が無難
 */
class BusTimetableId(id: String) : OdptId(id) {
    /**
     * [事業者ID][OperatorId]に変換
     */
    fun toOperatorId(): OperatorId {
        val operatorName = id.split(":")[1].split(".")[0]
        return OperatorId("odpt.Operator:$operatorName")
    }
}

/**
 * 曜日・日付情報ID
 */
class CalendarId(id: String) : OdptId(id)

/**
 * 日ID
 */
class DayId(id: String) : OdptId(id)

/**
 * フライト到着情報ID
 */
class FlightInformationArrivalId(id: String) : OdptId(id)

/**
 * フライト出発情報ID
 */
class FlightInformationDepartureId(id: String) : OdptId(id)

/**
 * フライト時刻表ID
 */
class FlightScheduleId(id: String) : OdptId(id)

/**
 * フライト状況ID
 */
class FlightStatusId(id: String) : OdptId(id)

/**
 * 事業者ID
 * @param id IDの値。命名ルール：「odpt.Operator:会社名」
 */
class OperatorId(id: String) : OdptId(id) {
    /**
     * [列車所属会社ID][TrainOwnerId]に変換
     * NOTE:v2版でのみ有効
     */
    fun toTrainOwnerId(): TrainOwnerId {
        return TrainOwnerId("odpt.Operator".toRegex().replace(id, "odpt.TrainOwner"))
    }
}

/**
 * 駅乗降人員数情報ID
 * @param id IDの値。命名ルール：「odpt.PassengerSurvey:会社名.駅名」または「odpt.PassengerSurvey:会社名.路線名.駅名」
 */
class PassengerSurveyId(id: String) : OdptId(id) {
    /**
     * [事業者ID][OperatorId]に変換
     */
    fun toOperatorId(): OperatorId {
        val operatorName = id.split(":")[1].split(".")[0]
        return OperatorId("odpt.Operator:$operatorName")
    }

    /**
     * [鉄道路線ID][RailwayId]に変換（変換できない場合はnullを返す）
     */
    fun toRailwayId(): RailwayId? {
        val split = id.split(":")[1].split(".")
        return if (split.size == 3) {
            RailwayId("odpt.Railway:${split[0]}.${split[1]}")
        } else null
    }

    /**
     * [駅ID][StationId]に変換（変換できない場合はnullを返す）
     */
    fun toStationId(): StationId? {
        val split = id.split(":")[1].split(".")
        return if (split.size == 3) {
            return StationId("odpt.PassengerSurvey".toRegex().replace(id, "odpt.Station"))
        } else null
    }

    fun validate(): Boolean {
        return "odpt.PassengerSurvey:(.*).(.*)(\\..*|)".toRegex().matches(id)
    }
}

/**
 * 地物情報ID
 */
class PlaceId(id: String) : OdptId(id)

/**
 * 鉄道方面ID（路線進行方向情報のID）
 * @param id IDの値。命名ルール：「odpt.RailDirection:進行方向」または「odpt.RailDirection:会社名.進行方向」
 */
class RailDirectionId(id: String) : OdptId(id) {
    fun validate(): Boolean {
        return "odpt.RailDirection:(.*)(\\..*|)".toRegex().matches(id)
    }
}

/**
 * 鉄道運賃情報ID
 * @param id IDの値。命名ルール：「odpt.RailwayFare:出発駅の会社名.出発駅の路線名.出発駅名.到着駅の会社名.到着駅の路線名.到着駅名」
 */
class RailwayFareId(id: String) : OdptId(id) {
    /**
     * 出発駅の[事業者ID][OperatorId]に変換
     */
    fun toFromOperatorId(): OperatorId {
        val operatorName = id.split(":")[1].split(".")[0]
        return OperatorId("odpt.Operator:$operatorName")
    }

    /**
     * 出発駅の[鉄道路線ID][RailwayId]に変換
     */
    fun toFromRailwayId(): RailwayId {
        val split = id.split(":")[1].split(".")
        return RailwayId("odpt.Railway:${split[0]}.${split[1]}")
    }

    /**
     * 出発駅の[駅ID][StationId]に変換
     */
    fun toFromStationId(): StationId {
        val split = id.split(":")[1].split(".")
        return StationId("odpt.Station:${split[0]}.${split[1]}.${split[2]}")
    }

    /**
     * 到着駅の[事業者ID][OperatorId]に変換
     */
    fun toToOperatorId(): OperatorId {
        val operatorName = id.split(":")[1].split(".")[3]
        return OperatorId("odpt.Operator:$operatorName")
    }

    /**
     * 到着駅の[鉄道路線ID][RailwayId]に変換
     */
    fun toToRailwayId(): RailwayId {
        val split = id.split(":")[1].split(".")
        return RailwayId("odpt.Railway:${split[3]}.${split[4]}")
    }

    /**
     * 到着駅の[駅ID][StationId]に変換
     */
    fun toToStationId(): StationId {
        val split = id.split(":")[1].split(".")
        return StationId("odpt.Station:${split[3]}.${split[4]}.${split[5]}")
    }

    fun validate(): Boolean {
        return "odpt.RailwayFare:(.*).(.*).(.*).(.*).(.*).(.*)".toRegex().matches(id)
    }
}

/**
 * 鉄道路線ID
 * @param id IDの値。命名ルール：「odpt.Railway:会社名.路線名」
 */
class RailwayId(id: String) : OdptId(id) {
    /**
     * [事業者ID][OperatorId]に変換
     */
    fun toOperatorId(): OperatorId {
        val operatorName = id.split(":")[1].split(".")[0]
        return OperatorId("odpt.Operator:$operatorName")
    }

    fun validate(): Boolean {
        return "odpt.Railway:(.*).(.*)".toRegex().matches(id)
    }
}

/**
 * 駅施設ID
 */
class StationFacilityId(id: String) : OdptId(id)

/**
 * 駅ID
 * @param id IDの値。命名ルール：「odpt.Station:会社名.路線名.駅名」
 */
class StationId(id: String) : OdptId(id) {
    /**
     * [事業者ID][OperatorId]に変換
     */
    fun toOperatorId(): OperatorId {
        val operatorName = id.split(":")[1].split(".")[0]
        return OperatorId("odpt.Operator:$operatorName")
    }

    /**
     * [鉄道路線ID][RailwayId]に変換
     */
    fun toRailwayId(): RailwayId {
        val split = id.split(":")[1].split(".")
        return RailwayId("odpt.Railway:${split[0]}.${split[1]}")
    }

    fun validate(): Boolean {
        return "odpt.Station:(.*).(.*).(.*)".toRegex().matches(id)
    }
}

/**
 * 駅時刻表情報ID
 * @param id IDの値。命名ルール：「odpt.StationTimetable:会社名.路線名.駅名.方面名.曜日・日付種別」
 */
class StationTimetableId(id: String) : OdptId(id) {
    /**
     * [事業者ID][OperatorId]に変換
     */
    fun toOperatorId(): OperatorId {
        val operatorName = id.split(":")[1].split(".")[0]
        return OperatorId("odpt.Operator:$operatorName")
    }

    /**
     * [鉄道路線ID][RailwayId]に変換
     */
    fun toRailwayId(): RailwayId {
        val split = id.split(":")[1].split(".")
        return RailwayId("odpt.Railway:${split[0]}.${split[1]}")
    }

    /**
     * [駅ID][StationId]に変換
     */
    fun toStationId(): StationId {
        val split = id.split(":")[1].split(".")
        return StationId("odpt.Station:${split[0]}.${split[1]}.${split[2]}")
    }

    fun validate(): Boolean {
        return "odpt.StationTimetable:(.*).(.*).(.*).(.*).(.*)".toRegex().matches(id)
    }
}

/**
 * 列車ID（列車ロケーション情報ID）
 * @param id IDの値。命名ルール：「odpt.Train:会社名.路線名.列車番号」
 */
class TrainId(id: String) : OdptId(id) {
    /**
     * [事業者ID][OperatorId]に変換
     */
    fun toOperatorId(): OperatorId {
        val operatorName = id.split(":")[1].split(".")[0]
        return OperatorId("odpt.Operator:$operatorName")
    }

    /**
     * [鉄道路線ID][RailwayId]に変換
     */
    fun toRailwayId(): RailwayId {
        val split = id.split(":")[1].split(".")
        return RailwayId("odpt.Railway:${split[0]}.${split[1]}")
    }

    fun validate(): Boolean {
        return "odpt.Train:(.*).(.*).(.*)".toRegex().matches(id)
    }
}

/**
 * 列車運行情報ID
 * @param id IDの値。命名ルール：「odpt.TrainInformation:会社名.路線名」または「odpt.TrainInformation:会社名」
 */
class TrainInformationId(id: String) : OdptId(id) {
    /**
     * [事業者ID][OperatorId]に変換
     */
    fun toOperatorId(): OperatorId {
        val operatorName = id.split(":")[1].split(".")[0]
        return OperatorId("odpt.Operator:$operatorName")
    }

    /**
     * [鉄道路線ID][RailwayId]に変換（変換できない場合はnullを返す）
     */
    fun toRailwayId(): RailwayId? {
        val split = id.split(":")[1].split(".")
        return if (split.size == 2) {
            RailwayId("odpt.TrainInformation".toRegex().replace(id, "odpt.Railway"))
        } else null
    }

    fun validate(): Boolean {
        return "odpt.TrainInformation:(.*)(\\..*|)".toRegex().matches(id)
    }
}

/**
 * 列車所属会社ID
 * @param id IDの値。命名ルール：「odpt.TrainOwner:会社名」
 * NOTE:v2版でのみ有効
 */
class TrainOwnerId(id: String) : OdptId(id) {
    /**
     * [事業者ID][OperatorId]に変換
     */
    fun toOperatorId(): OperatorId {
        return OperatorId("odpt.TrainOwner".toRegex().replace(id, "odpt.Operator"))
    }

    fun validate(): Boolean {
        return "odpt.TrainOwner:(.*)".toRegex().matches(id)
    }
}

/**
 * 列車時刻表情報ID
 * @param id IDの値。命名ルール：「odpt.TrainTimetable:会社名.路線名.列車番号.曜日・日付種別」。前記の命名ルールで重複が生じる場合は、末尾に .1, .2, .3, … をつけて区別する
 */
class TrainTimetableId(id: String) : OdptId(id) {
    /**
     * [事業者ID][OperatorId]に変換
     */
    fun toOperatorId(): OperatorId {
        val operatorName = id.split(":")[1].split(".")[0]
        return OperatorId("odpt.Operator:$operatorName")
    }

    /**
     * [鉄道路線ID][RailwayId]に変換
     */
    fun toRailwayId(): RailwayId {
        val split = id.split(":")[1].split(".")
        return RailwayId("odpt.Railway:${split[0]}.${split[1]}")
    }

    /**
     * [列車ID][TrainId]に変換
     */
    fun toTrainId(): TrainId {
        val split = id.split(":")[1].split(".")
        return TrainId("odpt.Train:${split[0]}.${split[1]}.${split[2]}")
    }

    fun validate(): Boolean {
        return "odpt.TrainTimetable:(.*).(.*).(.*).(.*)(\\.\\d*|)".toRegex().matches(id)
    }
}

/**
 * 列車種別ID
 * @param id IDの値。命名ルール：「odpt.TrainType:会社名.列車種別」
 */
class TrainTypeId(id: String) : OdptId(id) {
    fun validate(): Boolean {
        return "odpt.TrainType:(.*).(.*)".toRegex().matches(id)
    }
}
