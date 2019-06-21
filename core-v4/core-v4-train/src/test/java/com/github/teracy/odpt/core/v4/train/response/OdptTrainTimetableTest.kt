package com.github.teracy.odpt.core.v4.train.response

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.experimental.runners.Enclosed
import org.junit.runner.RunWith

/**
 * v4版列車時刻表情報APIレスポンスのテスト
 */
@RunWith(Enclosed::class)
class OdptTrainTimetableTest {
    /**
     * デシリアライズのテスト
     */
    class DeserializeTest {
        private val parameterizedType = Types.newParameterizedType(List::class.java, OdptTrainTimetable::class.java)

        lateinit var jsonAdapter: JsonAdapter<List<OdptTrainTimetable>>

        @Before
        fun setUp() {
            jsonAdapter = Moshi.Builder().build().adapter(parameterizedType)
        }

        /**
         * 東京公共交通オープンデータチャレンジAPIサンプルデータより
         */
        @Test
        fun tokyoChallengeSampleData() {
            // テストデータ
            val json =
                "[ {\n" +
                        "  \"@context\" : \"http://vocab.odpt.org/context_odpt.jsonld\",\n" +
                        "  \"@id\" : \"urn:ucode:_00001C000000000000010000030FD7E5\",\n" +
                        "  \"@type\" : \"odpt:TrainTimetable\",\n" +
                        "  \"dc:date\" : \"2017-01-13T15:10:00+09:00\",\n" +
                        "  \"dct:issued\" : \"2017-01-01\",\n" +
                        "  \"owl:sameAs\" : \"odpt.TrainTimetable:JR-East.ChuoRapid.123M.Weekday\",\n" +
                        "  \"odpt:operator\" : \"odpt.Operator:JR-East\",\n" +
                        "  \"odpt:railway\" : \"odpt.Railway:JR-East.ChuoRapid\",\n" +
                        "  \"odpt:railDirection\" : \"odpt.RailDirection:Outbound\",\n" +
                        "  \"odpt:calendar\" : \"odpt.Calendar:Weekday\",\n" +
                        "  \"odpt:trainNumber\" : \"123M\",\n" +
                        "  \"odpt:trainType\" : \"odpt.TrainType:JR-East.Rapid\",\n" +
                        "  \"odpt:originStation\" : [ \"odpt.Station:JR-East.ChuoRapid.Tokyo\" ],\n" +
                        "  \"odpt:destinationStation\" : [ \"odpt.Station:JR-East.ChuoRapid.Takao\" ],\n" +
                        "  \"odpt:trainTimetableObject\" : [ {\n" +
                        "    \"odpt:departureTime\" : \"06:00\",\n" +
                        "    \"odpt:departureStation\" : \"odpt.Station:JR-East.ChuoRapid.Tokyo\"\n" +
                        "  }, {\n" +
                        "    \"odpt:departureTime\" : \"06:30\",\n" +
                        "    \"odpt:departureStation\" : \"odpt.Station:JR-East.ChuoRapid.Tachikawa\"\n" +
                        "  }, {\n" +
                        "    \"odpt:arrivalTime\" : \"07:00\",\n" +
                        "    \"odpt:arrivalStation\" : \"odpt.Station:JR-East.ChuoRapid.Takao\"\n" +
                        "  } ]\n" +
                        "} ]"
            val trainTimetableList = jsonAdapter.fromJson(json)
            assertThat(trainTimetableList).isNotNull
            assertThat(trainTimetableList!!.size).isEqualTo(1)

            val trainTimetable0 = trainTimetableList[0]
            assertThat(trainTimetable0.id).isEqualTo("urn:ucode:_00001C000000000000010000030FD7E5")
            assertThat(trainTimetable0.date).isEqualTo("2017-01-13T15:10:00+09:00")
            assertThat(trainTimetable0.issued).isEqualTo("2017-01-01")
            assertThat(trainTimetable0.valid).isNull()
            assertThat(trainTimetable0.sameAs).isEqualTo("odpt.TrainTimetable:JR-East.ChuoRapid.123M.Weekday")
            assertThat(trainTimetable0.operator).isEqualTo("odpt.Operator:JR-East")
            assertThat(trainTimetable0.railway).isEqualTo("odpt.Railway:JR-East.ChuoRapid")
            assertThat(trainTimetable0.railDirection).isEqualTo("odpt.RailDirection:Outbound")
            assertThat(trainTimetable0.calendar).isEqualTo("odpt.Calendar:Weekday")
            assertThat(trainTimetable0.train).isNull()
            assertThat(trainTimetable0.trainNumber).isEqualTo("123M")
            assertThat(trainTimetable0.trainType).isEqualTo("odpt.TrainType:JR-East.Rapid")
            assertThat(trainTimetable0.trainNameMap).isNull()
            assertThat(trainTimetable0.trainOwner).isNull()

            assertThat(trainTimetable0.originStationList).isNotNull
            assertThat(trainTimetable0.originStationList!!.size).isEqualTo(1)
            assertThat(trainTimetable0.originStationList!![0]).isEqualTo("odpt.Station:JR-East.ChuoRapid.Tokyo")

            assertThat(trainTimetable0.destinationStationList).isNotNull
            assertThat(trainTimetable0.destinationStationList!!.size).isEqualTo(1)
            assertThat(trainTimetable0.destinationStationList!![0]).isEqualTo("odpt.Station:JR-East.ChuoRapid.Takao")

            assertThat(trainTimetable0.viaStationList).isNull()
            assertThat(trainTimetable0.viaRailwayList).isNull()
            assertThat(trainTimetable0.previousTrainTimetableList).isNull()
            assertThat(trainTimetable0.nextTrainTimetableList).isNull()

            assertThat(trainTimetable0.trainTimetableObject.size).isEqualTo(3)
            val trainTimetableObject0 = trainTimetable0.trainTimetableObject[0]
            assertThat(trainTimetableObject0.arrivalTime).isNull()
            assertThat(trainTimetableObject0.arrivalStation).isNull()
            assertThat(trainTimetableObject0.departureTime).isEqualTo("06:00")
            assertThat(trainTimetableObject0.departureStation).isEqualTo("odpt.Station:JR-East.ChuoRapid.Tokyo")
            assertThat(trainTimetableObject0.platformNumber).isNull()
            assertThat(trainTimetableObject0.platformNameMap).isNull()
            assertThat(trainTimetableObject0.note).isNull()

            assertThat(trainTimetableObject0.arrivalStationId).isNull()
            assertThat(trainTimetableObject0.departureStationId).isNotNull
            assertThat(trainTimetableObject0.departureStationId!!.id).isEqualTo("odpt.Station:JR-East.ChuoRapid.Tokyo")
            assertThat(trainTimetableObject0.note).isNull()

            val trainTimetableObject2 = trainTimetable0.trainTimetableObject[2]
            assertThat(trainTimetableObject2.arrivalTime).isEqualTo("07:00")
            assertThat(trainTimetableObject2.arrivalStation).isEqualTo("odpt.Station:JR-East.ChuoRapid.Takao")
            assertThat(trainTimetableObject2.departureTime).isNull()
            assertThat(trainTimetableObject2.departureStation).isNull()
            assertThat(trainTimetableObject2.platformNumber).isNull()
            assertThat(trainTimetableObject2.platformNameMap).isNull()
            assertThat(trainTimetableObject2.note).isNull()

            assertThat(trainTimetableObject2.arrivalStationId).isNotNull
            assertThat(trainTimetableObject2.arrivalStationId!!.id).isEqualTo("odpt.Station:JR-East.ChuoRapid.Takao")
            assertThat(trainTimetableObject2.departureStationId).isNull()

            assertThat(trainTimetable0.needsExtraFee).isFalse()
            assertThat(trainTimetable0.note).isNull()

            assertThat(trainTimetable0.trainTimetableId.id).isEqualTo("odpt.TrainTimetable:JR-East.ChuoRapid.123M.Weekday")
            assertThat(trainTimetable0.operatorId.id).isEqualTo("odpt.Operator:JR-East")
            assertThat(trainTimetable0.railwayId.id).isEqualTo("odpt.Railway:JR-East.ChuoRapid")
            assertThat(trainTimetable0.railDirectionId).isNotNull
            assertThat(trainTimetable0.railDirectionId!!.id).isEqualTo("odpt.RailDirection:Outbound")
            assertThat(trainTimetable0.calendarId).isNotNull
            assertThat(trainTimetable0.calendarId!!.id).isEqualTo("odpt.Calendar:Weekday")
            assertThat(trainTimetable0.trainId).isNull()
            assertThat(trainTimetable0.trainTypeId).isNotNull
            assertThat(trainTimetable0.trainTypeId!!.id).isEqualTo("odpt.TrainType:JR-East.Rapid")
            assertThat(trainTimetable0.trainOwnerOperatorId).isNull()

            assertThat(trainTimetable0.originStationIdList).isNotNull
            assertThat(trainTimetable0.originStationIdList!!.size).isEqualTo(1)
            assertThat(trainTimetable0.originStationIdList!![0].id).isEqualTo("odpt.Station:JR-East.ChuoRapid.Tokyo")

            assertThat(trainTimetable0.destinationStationIdList).isNotNull
            assertThat(trainTimetable0.destinationStationIdList!!.size).isEqualTo(1)
            assertThat(trainTimetable0.destinationStationIdList!![0].id).isEqualTo("odpt.Station:JR-East.ChuoRapid.Takao")

            assertThat(trainTimetable0.viaStationIdList).isNull()
            assertThat(trainTimetable0.viaRailwayIdList).isNull()
            assertThat(trainTimetable0.previousTrainTimetableIdList).isNull()
            assertThat(trainTimetable0.nextTrainTimetableIdList).isNull()
        }
    }
}
