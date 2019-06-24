package com.github.teracy.odpt.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.experimental.runners.Enclosed
import org.junit.runner.RunWith

@RunWith(Enclosed::class)
class OdptIdTest {
    /**
     * 空港IDのテスト
     */
    class AirportIdTest {
        // 東京公共交通オープンデータチャレンジサンプルデータより：羽田空港
        private val airportId = AirportId("odpt.Airport:HND")

        @Test
        fun validate() {
            assertThat(airportId.validate()).isTrue()
        }
    }

    /**
     * 空港ターミナルIDのテスト
     */
    class AirportTerminalIdTest {
        // 東京公共交通オープンデータチャレンジサンプルデータより：羽田空港国内線第1ターミナル
        private val airportTerminalId = AirportTerminalId("odpt.AirportTerminal:HND.DomesticTerminal1")

        @Test
        fun toAirportId() {
            val airportId = airportTerminalId.toAirportId()
            assertThat(airportId.id).isEqualTo("odpt.Airport:HND")
            assertThat(airportId.validate()).isTrue()
        }

        @Test
        fun validate() {
            assertThat(airportTerminalId.validate()).isTrue()
        }
    }

    /**
     * バス方面IDのテスト
     */
    class BusDirectionIdTest {
        // 東京公共交通オープンデータチャレンジサンプルデータより：東京都交通局・豊島五丁目団地方面
        private val busDirectionIdToei = BusDirectionId("odpt.BusDirection:Toei.Toshimagochoumedanchi")

        @Test
        fun toOperatorId() {
            val operatorIdToei = busDirectionIdToei.toOperatorId()
            assertThat(operatorIdToei.id).isEqualTo("odpt.Operator:Toei")
            assertThat(operatorIdToei.validate()).isTrue()
        }
    }

    /**
     * バス運行情報IDのテスト
     */
    class BusIdTest {
        // 東京公共交通オープンデータチャレンジサンプルデータより：東京都交通局・王57系統・V366号車
        private val busIdToei = BusId("odpt.Bus:Toei.Ou57.40301.1.V366")

        @Test
        fun toOperatorId() {
            val operatorIdToei = busIdToei.toOperatorId()
            assertThat(operatorIdToei.id).isEqualTo("odpt.Operator:Toei")
            assertThat(operatorIdToei.validate()).isTrue()
        }
    }

    /**
     * バス運賃情報IDのテスト
     */
    class BusRoutePatternFareIdTest {
        // 東京公共交通オープンデータチャレンジサンプルデータより：東京都交通局・王57系統・赤羽駅東口→東京都交通局・王57系統・赤羽二丁目
        private val busRoutePatternFareIdToei =
            BusRoutePatternFareId("odpt.BusroutePatternFare:Toei.Ou57.40301.1.1.Akabaneekihigashiguchi.Toei.Ou57.40301.1.2.Akabanenichoume")

        @Test
        fun toOperatorId() {
            val operatorIdToei = busRoutePatternFareIdToei.toOperatorId()
            assertThat(operatorIdToei.id).isEqualTo("odpt.Operator:Toei")
            assertThat(operatorIdToei.validate()).isTrue()
        }
    }

    /**
     * バス系統IDのテスト
     */
    class BusRoutePatternIdTest {
        // 東京公共交通オープンデータチャレンジサンプルデータより：東京都交通局・王57系統
        private val busRoutePatternIdToei = BusRoutePatternId("odpt.BusroutePattern:Toei.Ou57.40301.1")

        @Test
        fun toOperatorId() {
            val operatorIdToei = busRoutePatternIdToei.toOperatorId()
            assertThat(operatorIdToei.id).isEqualTo("odpt.Operator:Toei")
            assertThat(operatorIdToei.validate()).isTrue()
        }
    }

    /**
     * バス停標柱IDのテスト
     */
    class BusStopPoleIdTest {
        // 東京公共交通オープンデータチャレンジサンプルデータより：東京都交通局・王57系統・赤羽駅東口
        private val busStopPoleIdToei = BusStopPoleId("odpt.BusstopPole:Toei.Akabaneekihigashiguchi.21.1")

        @Test
        fun toOperatorId() {
            val operatorIdToei = busStopPoleIdToei.toOperatorId()
            assertThat(operatorIdToei.id).isEqualTo("odpt.Operator:Toei")
            assertThat(operatorIdToei.validate()).isTrue()
        }
    }

    /**
     * バス停標柱時刻表情報IDのテスト
     */
    class BusStopPoleTimetableIdTest {
        // 東京公共交通オープンデータチャレンジサンプルデータより：東京都交通局・王57系統・赤羽駅東口・豊島五丁目団地行・平日
        private val busStopPoleTimetableIdToei =
            BusStopPoleTimetableId("odpt.BusstopPoleTimetable:Toei.Ou57.Akabaneekihigashiguchi.21.1.Toshimagochoumedanchi.Weekday")

        @Test
        fun toOperatorId() {
            val operatorIdToei = busStopPoleTimetableIdToei.toOperatorId()
            assertThat(operatorIdToei.id).isEqualTo("odpt.Operator:Toei")
            assertThat(operatorIdToei.validate()).isTrue()
        }
    }

    /**
     * バス時刻表情報IDのテスト
     */
    class BusTimetableIdTest {
        // 東京公共交通オープンデータチャレンジサンプルデータより：西武バス・練高01系統・平日
        private val busTimetableIdSeibuBus =
            BusTimetableId("odpt.BusTimetable:SeibuBus.NeriDaka01.1001.1.1.Weekday")

        @Test
        fun toOperatorId() {
            val operatorIdToei = busTimetableIdSeibuBus.toOperatorId()
            assertThat(operatorIdToei.id).isEqualTo("odpt.Operator:SeibuBus")
            assertThat(operatorIdToei.validate()).isTrue()
        }
    }

    /**
     * フライト到着情報IDのテスト
     */
    class FlightInformationArrivalIdTest {
        // 東京公共交通オープンデータチャレンジサンプルデータより：成田国際空港・成田空港・NH832便
        private val flightInformationArrivalId =
            FlightInformationArrivalId("odpt.FlightInformationArrival:NAA.NRT.NH832")

        @Test
        fun toOperatorId() {
            val operatorId = flightInformationArrivalId.toOperatorId()
            assertThat(operatorId.id).isEqualTo("odpt.Operator:NAA")
            assertThat(operatorId.validate()).isTrue()
        }

        @Test
        fun toArrivalAirportId() {
            val airportId = flightInformationArrivalId.toArrivalAirportId()
            assertThat(airportId.id).isEqualTo("odpt.Airport:NRT")
            assertThat(airportId.validate()).isTrue()
        }

        @Test
        fun toFlightNumber() {
            val flightNumber = flightInformationArrivalId.toFlightNumber()
            assertThat(flightNumber).isEqualTo("NH832")
        }

        @Test
        fun validate() {
            assertThat(flightInformationArrivalId.validate()).isTrue()
        }
    }

    /**
     * フライト出発情報IDのテスト
     */
    class FlightInformationDepartureIdTest {
        // 東京公共交通オープンデータチャレンジサンプルデータより：成田国際空港・成田空港・9W4807便
        private val flightInformationDepartureId =
            FlightInformationDepartureId("odpt.FlightInformationDeparture:NAA.NRT.9W4807")

        @Test
        fun toOperatorId() {
            val operatorId = flightInformationDepartureId.toOperatorId()
            assertThat(operatorId.id).isEqualTo("odpt.Operator:NAA")
            assertThat(operatorId.validate()).isTrue()
        }

        @Test
        fun toArrivalAirportId() {
            val airportId = flightInformationDepartureId.toDepartureAirportId()
            assertThat(airportId.id).isEqualTo("odpt.Airport:NRT")
            assertThat(airportId.validate()).isTrue()
        }

        @Test
        fun toFlightNumber() {
            val flightNumber = flightInformationDepartureId.toFlightNumber()
            assertThat(flightNumber).isEqualTo("9W4807")
        }

        @Test
        fun validate() {
            assertThat(flightInformationDepartureId.validate()).isTrue()
        }
    }

    /**
     * フライト時刻表IDのテスト
     */
    class FlightScheduleIdTest {
        // 東京公共交通オープンデータチャレンジサンプルデータより：東京国際空港ターミナル・羽田空港→石見空港・水曜日
        private val flightScheduleId = FlightScheduleId("odpt.FlightSchedule:HND-TIAT.HND.IWJ.Wednesday")

        @Test
        fun toOperatorId() {
            val operatorId = flightScheduleId.toOperatorId()
            assertThat(operatorId.id).isEqualTo("odpt.Operator:HND-TIAT")
            assertThat(operatorId.validate()).isTrue()
        }

        @Test
        fun toOriginAirportId() {
            val airportId = flightScheduleId.toOriginAirportId()
            assertThat(airportId.id).isEqualTo("odpt.Airport:HND")
            assertThat(airportId.validate()).isTrue()
        }

        @Test
        fun toDestinationAirportId() {
            val airportId = flightScheduleId.toDestinationAirportId()
            assertThat(airportId.id).isEqualTo("odpt.Airport:IWJ")
            assertThat(airportId.validate()).isTrue()
        }

        @Test
        fun validate() {
            assertThat(flightScheduleId.validate()).isTrue()
        }
    }

    /**
     * フライト状況IDのテスト
     */
    class FlightStatusIdTest {
        // 東京公共交通オープンデータチャレンジサンプルデータより：搭乗手続中
        private val flightStatusId = FlightStatusId("odpt.FlightStatus:CheckIn")

        @Test
        fun validate() {
            assertThat(flightStatusId.validate()).isTrue()
        }
    }

    /**
     * 事業者IDのテスト
     */
    class OperatorIdTest {
        // v2：東京メトロオープンデータサンプルデータより：東京メトロ
        private val operatorIdJrTokyoMetro = OperatorId("odpt.Operator:TokyoMetro")
        // v4：東京公共交通オープンデータチャレンジサンプルデータより：JR東日本
        private val operatorIdJrEast = OperatorId("odpt.Operator:JR-East")

        @Test
        fun toTrainOwnerId() {
            // v2版のみ
            val trainOwnerId = operatorIdJrTokyoMetro.toTrainOwnerId()
            assertThat(trainOwnerId.id).isEqualTo("odpt.TrainOwner:TokyoMetro")
            assertThat(trainOwnerId.validate()).isTrue()
        }

        @Test
        fun validate() {
            // v2/v4版共通
            assertThat(operatorIdJrEast.validate()).isTrue()
            assertThat(operatorIdJrTokyoMetro.validate()).isTrue()
        }
    }

    /**
     * 駅乗降人員数情報IDのテスト
     */
    class PassengerSurveyIdTest {
        // 東京公共交通オープンデータチャレンジサンプルデータより：フォーマット「odpt.PassengerSurvey:会社名.駅名」
        private val passengerSurveyId1 = PassengerSurveyId("odpt.PassengerSurvey:JR-East.Tokyo")
        // 東京公共交通オープンデータチャレンジAPI実データより：フォーマット「odpt.PassengerSurvey:会社名.路線名.駅名」
        private val passengerSurveyId2 = PassengerSurveyId("odpt.PassengerSurvey:Toei.Asakusa.Daimon")

        @Test
        fun toOperatorId() {
            val operatorId1 = passengerSurveyId1.toOperatorId()
            assertThat(operatorId1.id).isEqualTo("odpt.Operator:JR-East")
            assertThat(operatorId1.validate()).isTrue()

            val operatorId2 = passengerSurveyId2.toOperatorId()
            assertThat(operatorId2.id).isEqualTo("odpt.Operator:Toei")
            assertThat(operatorId2.validate()).isTrue()
        }

        @Test
        fun toRailwayId() {
            val railwayId1 = passengerSurveyId1.toRailwayId()
            assertThat(railwayId1).isNull()

            val railwayId2 = passengerSurveyId2.toRailwayId()
            assertThat(railwayId2).isNotNull
            assertThat(railwayId2!!.id).isEqualTo("odpt.Railway:Toei.Asakusa")
            assertThat(railwayId2.validate()).isTrue()
        }

        @Test
        fun toStationId() {
            val stationId1 = passengerSurveyId1.toStationId()
            assertThat(stationId1).isNull()

            val stationId2 = passengerSurveyId2.toStationId()
            assertThat(stationId2).isNotNull
            assertThat(stationId2!!.id).isEqualTo("odpt.Station:Toei.Asakusa.Daimon")
            assertThat(stationId2.validate()).isTrue()
        }

        @Test
        fun validate() {
            assertThat(passengerSurveyId1.validate()).isTrue()
            assertThat(passengerSurveyId2.validate()).isTrue()
        }
    }

    /**
     * 鉄道方面IDのテスト
     */
    class RailDirectionIdTest {
        // 東京公共交通オープンデータチャレンジサンプルデータより：フォーマット「odpt.RailDirection:進行方向」
        private val railDirectionId1 = RailDirectionId("odpt.RailDirection:Inbound")
        // 東京公共交通オープンデータチャレンジサンプルデータより：フォーマット「odpt.RailDirection:会社名.進行方向」
        private val railDirectionId2 = RailDirectionId("odpt.RailDirection:TokyoMetro.Wakoshi")

        @Test
        fun validate() {
            assertThat(railDirectionId1.validate()).isTrue()
            assertThat(railDirectionId2.validate()).isTrue()
        }
    }

    /**
     * 鉄道運賃情報IDのテスト
     */
    class RailwayFareIdTest {
        // 東京公共交通オープンデータチャレンジサンプルデータより：東京メトロ丸ノ内線東京駅→東京メトロ東西線中野駅
        private val railwayFareId =
            RailwayFareId("odpt.RailwayFare:TokyoMetro.Marunouchi.Tokyo.TokyoMetro.Tozai.Nakano")

        @Test
        fun toFromOperatorId() {
            val operatorId = railwayFareId.toFromOperatorId()
            assertThat(operatorId.id).isEqualTo("odpt.Operator:TokyoMetro")
            assertThat(operatorId.validate()).isTrue()
        }

        @Test
        fun toFromRailwayId() {
            val railwayId = railwayFareId.toFromRailwayId()
            assertThat(railwayId.id).isEqualTo("odpt.Railway:TokyoMetro.Marunouchi")
            assertThat(railwayId.validate()).isTrue()
        }

        @Test
        fun toFromStationId() {
            val stationId = railwayFareId.toFromStationId()
            assertThat(stationId.id).isEqualTo("odpt.Station:TokyoMetro.Marunouchi.Tokyo")
            assertThat(stationId.validate()).isTrue()
        }

        @Test
        fun toToOperatorId() {
            val operatorId = railwayFareId.toToOperatorId()
            assertThat(operatorId.id).isEqualTo("odpt.Operator:TokyoMetro")
            assertThat(operatorId.validate()).isTrue()
        }

        @Test
        fun toToRailwayId() {
            val railwayId = railwayFareId.toToRailwayId()
            assertThat(railwayId.id).isEqualTo("odpt.Railway:TokyoMetro.Tozai")
            assertThat(railwayId.validate()).isTrue()
        }

        @Test
        fun toToStationId() {
            val stationId = railwayFareId.toToStationId()
            assertThat(stationId.id).isEqualTo("odpt.Station:TokyoMetro.Tozai.Nakano")
            assertThat(stationId.validate()).isTrue()
        }

        @Test
        fun validate() {
            assertThat(railwayFareId.validate()).isTrue()
        }
    }

    /**
     * 鉄道路線IDのテスト
     */
    class RailwayIdTest {
        // 東京公共交通オープンデータチャレンジサンプルデータより：JR東日本山手線
        private val railwayId = RailwayId("odpt.Railway:JR-East.Yamanote")

        @Test
        fun toOperatorId() {
            val operatorId = railwayId.toOperatorId()
            assertThat(operatorId.id).isEqualTo("odpt.Operator:JR-East")
            assertThat(operatorId.validate()).isTrue()
        }

        @Test
        fun validate() {
            assertThat(railwayId.validate()).isTrue()
        }
    }

    /**
     * 駅施設IDのテスト
     */
    class StationFacilityIdTest {
        // 東京メトロオープンデータサンプルデータより：国会議事堂前駅
        private val stationFacilityId = StationFacilityId("odpt.StationFacility:TokyoMetro.KokkaiGijidomae")

        @Test
        fun toOperatorId() {
            val operatorId = stationFacilityId.toOperatorId()
            assertThat(operatorId.id).isEqualTo("odpt.Operator:TokyoMetro")
            assertThat(operatorId.validate()).isTrue()
        }

        @Test
        fun validate() {
            assertThat(stationFacilityId.validate()).isTrue()
        }
    }

    /**
     * 駅IDのテスト
     */
    class StationIdTest {
        // 東京公共交通オープンデータチャレンジサンプルデータより：JR東日本山手線東京駅
        private val stationId = StationId("odpt.Station:JR-East.Yamanote.Tokyo")

        @Test
        fun toOperatorId() {
            val operatorId = stationId.toOperatorId()
            assertThat(operatorId.id).isEqualTo("odpt.Operator:JR-East")
            assertThat(operatorId.validate()).isTrue()
        }

        @Test
        fun toRailwayId() {
            val railwayId = stationId.toRailwayId()
            assertThat(railwayId.id).isEqualTo("odpt.Railway:JR-East.Yamanote")
            assertThat(railwayId.validate()).isTrue()
        }

        @Test
        fun validate() {
            assertThat(stationId.validate()).isTrue()
        }
    }

    /**
     * 駅時刻表情報IDのテスト
     */
    class StationTimetableIdest {
        // 東京公共交通オープンデータチャレンジサンプルデータより：JR東日本中央快速線東京駅下り平日
        private val stationTimetableId =
            StationTimetableId("odpt.StationTimetable:JR-East.ChuoRapid.Tokyo.Outbound.Weekday")

        @Test
        fun toOperatorId() {
            val operatorId = stationTimetableId.toOperatorId()
            assertThat(operatorId.id).isEqualTo("odpt.Operator:JR-East")
            assertThat(operatorId.validate()).isTrue()
        }

        @Test
        fun toRailwayId() {
            val railwayId = stationTimetableId.toRailwayId()
            assertThat(railwayId.id).isEqualTo("odpt.Railway:JR-East.ChuoRapid")
            assertThat(railwayId.validate()).isTrue()
        }

        @Test
        fun toStationId() {
            val stationId = stationTimetableId.toStationId()
            assertThat(stationId.id).isEqualTo("odpt.Station:JR-East.ChuoRapid.Tokyo")
            assertThat(stationId.validate()).isTrue()
        }

        @Test
        fun validate() {
            assertThat(stationTimetableId.validate()).isTrue()
        }
    }

    /**
     * 列車IDのテスト
     */
    class TrainIdTest {
        // 東京公共交通オープンデータチャレンジサンプルデータより：JR東日本宇都宮線565M列車
        private val trainId = TrainId("odpt.Train:JR-East.Utsunomiya.565M")

        @Test
        fun toOperatorId() {
            val operatorId = trainId.toOperatorId()
            assertThat(operatorId.id).isEqualTo("odpt.Operator:JR-East")
            assertThat(operatorId.validate()).isTrue()
        }

        @Test
        fun toRailwayId() {
            val railwayId = trainId.toRailwayId()
            assertThat(railwayId.id).isEqualTo("odpt.Railway:JR-East.Utsunomiya")
            assertThat(railwayId.validate()).isTrue()
        }

        @Test
        fun toTrainNumber() {
            val trainNumber = trainId.toTrainNumber()
            assertThat(trainNumber).isEqualTo("565M")
        }

        @Test
        fun validate() {
            assertThat(trainId.validate()).isTrue()
        }
    }

    /**
     * 列車運行情報IDのテスト
     */
    class TrainInformationIdTest {
        // 東京公共交通オープンデータチャレンジAPI実データより：フォーマット「odpt.TrainInformation:会社名」
        private val trainInformationId1 = TrainInformationId("odpt.TrainInformation:Seibu")
        // 東京公共交通オープンデータチャレンジサンプルデータより：フォーマット「odpt.TrainInformation:会社名.路線名」
        private val trainInformationId2 = TrainInformationId("odpt.TrainInformation:TokyoMetro.Ginza")

        @Test
        fun toOperatorId() {
            val operatorId1 = trainInformationId1.toOperatorId()
            assertThat(operatorId1.id).isEqualTo("odpt.Operator:Seibu")
            assertThat(operatorId1.validate()).isTrue()

            val operatorId2 = trainInformationId2.toOperatorId()
            assertThat(operatorId2.id).isEqualTo("odpt.Operator:TokyoMetro")
            assertThat(operatorId2.validate()).isTrue()
        }

        @Test
        fun toRailwayId() {
            val railwayId1 = trainInformationId1.toRailwayId()
            assertThat(railwayId1).isNull()

            val railwayId2 = trainInformationId2.toRailwayId()
            assertThat(railwayId2).isNotNull
            assertThat(railwayId2!!.id).isEqualTo("odpt.Railway:TokyoMetro.Ginza")
            assertThat(railwayId2.validate()).isTrue()
        }

        @Test
        fun validate() {
            assertThat(trainInformationId1.validate()).isTrue()
            assertThat(trainInformationId2.validate()).isTrue()
        }
    }

    /**
     * 列車所属会社IDのテスト
     */
    class TrainOwnerIdTest {
        // 東京メトロオープンデータサンプルデータより：小田急
        private val trainOwnerId = TrainOwnerId("odpt.TrainOwner:Odakyu")

        @Test
        fun toOperatorId() {
            val operatorId = trainOwnerId.toOperatorId()
            assertThat(operatorId.id).isEqualTo("odpt.Operator:Odakyu")
            assertThat(operatorId.validate()).isTrue()
        }

        @Test
        fun validate() {
            assertThat(trainOwnerId.validate()).isTrue()
        }
    }

    /**
     * 列車時刻表情報IDのテスト
     */
    class TrainTimetableIdTest {
        // 東京公共交通オープンデータチャレンジサンプルデータより：フォーマット「odpt.TrainTimetable:会社名.路線名.列車番号.曜日・日付種別」
        private val trainTimetableId1 = TrainTimetableId("odpt.TrainTimetable:JR-East.ChuoRapid.123M.Weekday")
        // 東京公共交通オープンデータチャレンジAPI実データより：フォーマット「odpt.TrainTimetable:会社名.路線名.列車番号.曜日・日付種別」＋「末尾に .1, .2, .3, …」
        private val trainTimetableId2 =
            TrainTimetableId("odpt.TrainTimetable:JR-East.ChuoRapid.2110H.SaturdayHoliday.1")

        @Test
        fun toOperatorId() {
            val operatorId1 = trainTimetableId1.toOperatorId()
            assertThat(operatorId1.id).isEqualTo("odpt.Operator:JR-East")
            assertThat(operatorId1.validate()).isTrue()

            val operatorId2 = trainTimetableId2.toOperatorId()
            assertThat(operatorId2.id).isEqualTo("odpt.Operator:JR-East")
            assertThat(operatorId2.validate()).isTrue()
        }

        @Test
        fun toRailwayId() {
            val railwayId1 = trainTimetableId1.toRailwayId()
            assertThat(railwayId1.id).isEqualTo("odpt.Railway:JR-East.ChuoRapid")
            assertThat(railwayId1.validate()).isTrue()

            val railwayId2 = trainTimetableId2.toRailwayId()
            assertThat(railwayId2.id).isEqualTo("odpt.Railway:JR-East.ChuoRapid")
            assertThat(railwayId2.validate()).isTrue()
        }

        @Test
        fun toTrainId() {
            val trainId1 = trainTimetableId1.toTrainId()
            assertThat(trainId1.id).isEqualTo("odpt.Train:JR-East.ChuoRapid.123M")
            assertThat(trainId1.validate()).isTrue()

            val trainId2 = trainTimetableId2.toTrainId()
            assertThat(trainId2.id).isEqualTo("odpt.Train:JR-East.ChuoRapid.2110H")
            assertThat(trainId2.validate()).isTrue()
        }

        @Test
        fun validate() {
            assertThat(trainTimetableId1.validate()).isTrue()
            assertThat(trainTimetableId2.validate()).isTrue()
        }
    }

    /**
     * 列車種別IDのテスト
     */
    class TrainTypeIdTest {
        // 東京公共交通オープンデータチャレンジサンプルデータより：JR東日本普通列車
        private val trainTypeId = TrainTypeId("odpt.TrainType:JR-East.Local")

        @Test
        fun toOperatorId() {
            val operatorId = trainTypeId.toOperatorId()
            assertThat(operatorId.id).isEqualTo("odpt.Operator:JR-East")
            assertThat(operatorId.validate()).isTrue()
        }

        @Test
        fun validate() {
            assertThat(trainTypeId.validate()).isTrue()
        }
    }
}
