package com.github.teracy.odpt.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.experimental.runners.Enclosed
import org.junit.runner.RunWith

@RunWith(Enclosed::class)
class OdptIdTest {
    /**
     * 駅乗降人員数情報IDのテスト
     */
    class PassengerSurveyIdTest {
        // フォーマット「odpt.PassengerSurvey:会社名.駅名」
        private val passengerSurveyId1 = PassengerSurveyId("odpt.PassengerSurvey:JR-East.Tokyo")
        // フォーマット「odpt.PassengerSurvey:会社名.路線名.駅名」
        private val passengerSurveyId2 = PassengerSurveyId("odpt.PassengerSurvey:Toei.Asakusa.Daimon")

        @Test
        fun toOperatorId() {
            val operatorId1 = passengerSurveyId1.toOperatorId()
            assertThat(operatorId1.id).isEqualTo("odpt.Operator:JR-East")

            val operatorId2 = passengerSurveyId2.toOperatorId()
            assertThat(operatorId2.id).isEqualTo("odpt.Operator:Toei")
        }

        @Test
        fun toRailwayId() {
            val railwayId1 = passengerSurveyId1.toRailwayId()
            assertThat(railwayId1).isNull()

            val railwayId2 = passengerSurveyId2.toRailwayId()
            assertThat(railwayId2).isNotNull
            assertThat(railwayId2!!.id).isEqualTo("odpt.Railway:Toei.Asakusa")
            assertThat(railwayId2!!.validate()).isTrue
        }

        @Test
        fun toStationId() {
            val stationId1 = passengerSurveyId1.toStationId()
            assertThat(stationId1).isNull()

            val stationId2 = passengerSurveyId2.toStationId()
            assertThat(stationId2).isNotNull
            assertThat(stationId2!!.id).isEqualTo("odpt.Station:Toei.Asakusa.Daimon")
        }
    }

    /**
     * 鉄道路線IDのテスト
     */
    class RailwayIdTest {
        private val railwayId = RailwayId("odpt.Railway:Keisei.Main")

        @Test
        fun toOperatorId() {
            val operatorId = railwayId.toOperatorId()
            assertThat(operatorId.id).isEqualTo("odpt.Operator:Keisei")
        }
    }

    /**
     * 鉄道運賃情報IDのテスト
     */
    class RailwayFareIdTest {
        private val railwayFareId =
            RailwayFareId("odpt.RailwayFare:TokyoMetro.Marunouchi.Tokyo.TokyoMetro.Tozai.Nakano")

        @Test
        fun toFromOperatorId() {
            val operatorId = railwayFareId.toFromOperatorId()
            assertThat(operatorId.id).isEqualTo("odpt.Operator:TokyoMetro")
        }

        @Test
        fun toFromRailwayId() {
            val railwayId = railwayFareId.toFromRailwayId()
            assertThat(railwayId.id).isEqualTo("odpt.Railway:TokyoMetro.Marunouchi")
        }

        @Test
        fun toFromStationId() {
            val stationId = railwayFareId.toFromStationId()
            assertThat(stationId.id).isEqualTo("odpt.Station:TokyoMetro.Marunouchi.Tokyo")
        }

        @Test
        fun toToOperatorId() {
            val operatorId = railwayFareId.toToOperatorId()
            assertThat(operatorId.id).isEqualTo("odpt.Operator:TokyoMetro")
        }

        @Test
        fun toToRailwayId() {
            val railwayId = railwayFareId.toToRailwayId()
            assertThat(railwayId.id).isEqualTo("odpt.Railway:TokyoMetro.Tozai")
        }

        @Test
        fun toToStationId() {
            val stationId = railwayFareId.toToStationId()
            assertThat(stationId.id).isEqualTo("odpt.Station:TokyoMetro.Tozai.Nakano")
        }
    }

    /**
     * 駅IDのテスト
     */
    class StationIdTest {
        private val stationId = StationId("odpt.Station:Keio.Inokashira.InokashiraKoen")

        @Test
        fun toOperatorId() {
            val operatorId = stationId.toOperatorId()
            assertThat(operatorId.id).isEqualTo("odpt.Operator:Keio")
        }

        @Test
        fun toRailwayId() {
            val railwayId = stationId.toRailwayId()
            assertThat(railwayId.id).isEqualTo("odpt.Railway:Keio.Inokashira")
        }
    }

    /**
     * 駅時刻表情報IDのテスト
     */
    class StationTimetableIdest {
        private val stationTimetableId =
            StationTimetableId("odpt.StationTimetable:JR-East.ChuoRapid.Tokyo.Outbound.Weekday")

        @Test
        fun toOperatorId() {
            val operatorId = stationTimetableId.toOperatorId()
            assertThat(operatorId.id).isEqualTo("odpt.Operator:JR-East")
        }

        @Test
        fun toRailwayId() {
            val railwayId = stationTimetableId.toRailwayId()
            assertThat(railwayId.id).isEqualTo("odpt.Railway:JR-East.ChuoRapid")
        }

        @Test
        fun toStationId() {
            val stationId = stationTimetableId.toStationId()
            assertThat(stationId.id).isEqualTo("odpt.Station:JR-East.ChuoRapid.Tokyo")
        }
    }

    /**
     * 列車IDのテスト
     */
    class TrainIdTest {
        private val trainId = TrainId("odpt.Train:JR-East.Yamanote.123M")

        @Test
        fun toOperatorId() {
            val operatorId = trainId.toOperatorId()
            assertThat(operatorId.id).isEqualTo("odpt.Operator:JR-East")
        }

        @Test
        fun toRailwayId() {
            val railwayId = trainId.toRailwayId()
            assertThat(railwayId.id).isEqualTo("odpt.Railway:JR-East.Yamanote")
        }
    }

    /**
     * 列車運行情報IDのテスト
     */
    class TrainInformationIdTest {
        // フォーマット「odpt.TrainInformation:会社名」
        private val trainInformationId1 = TrainInformationId("odpt.TrainInformation:Seibu")
        // フォーマット「odpt.TrainInformation:会社名.路線名」
        private val trainInformationId2 = TrainInformationId("odpt.TrainInformation:JR-East.Yamanote")

        @Test
        fun toOperatorId() {
            val operatorId1 = trainInformationId1.toOperatorId()
            assertThat(operatorId1.id).isEqualTo("odpt.Operator:Seibu")
//            assertThat(operatorId1!!.validate()).isTrue()

            val operatorId2 = trainInformationId2.toOperatorId()
            assertThat(operatorId2.id).isEqualTo("odpt.Operator:JR-East")
//            assertThat(operatorId2!!.validate()).isTrue()
        }

        @Test
        fun toRailwayId() {
            val railwayId1 = trainInformationId1.toRailwayId()
            assertThat(railwayId1).isNull()

            val railwayId2 = trainInformationId2.toRailwayId()
            assertThat(railwayId2).isNotNull
            assertThat(railwayId2!!.id).isEqualTo("odpt.Railway:JR-East.Yamanote")
            assertThat(railwayId2!!.validate()).isTrue
        }

        @Test
        fun validate() {
            assertThat(trainInformationId1.validate()).isTrue
            assertThat(trainInformationId2.validate()).isTrue
        }
    }

    /**
     * 列車時刻表情報IDのテスト
     */
    class TrainTimetableIdTest {
        private val trainTimetableId1 = TrainTimetableId("odpt.TrainTimetable:JR-East.ChuoRapid.123M.Weekday")
        private val trainTimetableId2 = TrainTimetableId("odpt.TrainTimetable:JR-East.ChuoRapid.2110H.SaturdayHoliday.1")

        @Test
        fun toOperatorId() {
            val operatorId1 = trainTimetableId1.toOperatorId()
            assertThat(operatorId1.id).isEqualTo("odpt.Operator:JR-East")
//            assertThat(operatorId1!!.validate()).isTrue()

            val operatorId2 = trainTimetableId2.toOperatorId()
            assertThat(operatorId2.id).isEqualTo("odpt.Operator:JR-East")
//            assertThat(operatorId2!!.validate()).isTrue()
        }

        @Test
        fun toRailwayId() {
            val railwayId1 = trainTimetableId1.toRailwayId()
            assertThat(railwayId1.id).isEqualTo("odpt.Railway:JR-East.ChuoRapid")

            val railwayId2 = trainTimetableId2.toRailwayId()
            assertThat(railwayId2.id).isEqualTo("odpt.Railway:JR-East.ChuoRapid")
        }

        @Test
        fun toTrainId() {
            val trainId1 = trainTimetableId1.toTrainId()
            assertThat(trainId1.id).isEqualTo("odpt.Train:JR-East.ChuoRapid.123M")

            val trainId2 = trainTimetableId2.toTrainId()
            assertThat(trainId2.id).isEqualTo("odpt.Train:JR-East.ChuoRapid.2110H")
        }

        @Test
        fun validate() {
            assertThat(trainTimetableId1.validate()).isTrue
            assertThat(trainTimetableId2.validate()).isTrue
        }
    }
}
