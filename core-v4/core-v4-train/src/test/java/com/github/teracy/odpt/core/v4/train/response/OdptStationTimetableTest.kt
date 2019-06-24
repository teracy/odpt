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
 * v4版駅時刻表情報APIレスポンスのテスト
 */
@RunWith(Enclosed::class)
class OdptStationTimetableTest {
    /**
     * デシリアライズのテスト
     */
    class DeserializeTest {
        private val parameterizedType = Types.newParameterizedType(List::class.java, OdptStationTimetable::class.java)

        lateinit var jsonAdapter: JsonAdapter<List<OdptStationTimetable>>

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
                        "  \"@type\" : \"odpt:StationTimetable\",\n" +
                        "  \"dc:date\" : \"2017-01-13T15:10:00+09:00\",\n" +
                        "  \"dct:issued\" : \"2017-01-01\",\n" +
                        "  \"owl:sameAs\" : \"odpt.StationTimetable:JR-East.ChuoRapid.Tokyo.Outbound.Weekday\",\n" +
                        "  \"odpt:operator\" : \"odpt.Operator:JR-East\",\n" +
                        "  \"odpt:railway\" : \"odpt.Railway:JR-East.ChuoRapid\",\n" +
                        "  \"odpt:station\" : \"odpt.Station:JR-East.ChuoRapid.Tokyo\",\n" +
                        "  \"odpt:railDirection\" : \"odpt.RailDirection:JR-East.Outbound\",\n" +
                        "  \"odpt:calendar\" : \"odpt.Calendar:Weekday\",\n" +
                        "  \"odpt:stationTimetableObject\" : [ {\n" +
                        "    \"odpt:departureTime\" : \"06:00\",\n" +
                        "    \"odpt:destinationStation\" : [ \"odpt.Station:JR-East.ChuoRapid.Takao\" ],\n" +
                        "    \"odpt:trainType\" : \"odpt.TrainType:JR-East.Rapid\"\n" +
                        "  }, {\n" +
                        "    \"odpt:departureTime\" : \"06:10\",\n" +
                        "    \"odpt:destinationStation\" : [ \"odpt.Station:JR-East.ChuoRapid.Takao\" ],\n" +
                        "    \"odpt:trainType\" : \"odpt.TrainType:JR-East.Rapid\"\n" +
                        "  }, {\n" +
                        "    \"odpt:departureTime\" : \"06:20\",\n" +
                        "    \"odpt:destinationStation\" : [ \"odpt.Station:JR-East.ChuoRapid.Takao\" ],\n" +
                        "    \"odpt:trainType\" : \"odpt.TrainType:JR-East.Rapid\"\n" +
                        "  } ]\n" +
                        "} ]"
            val stationTimetableList = jsonAdapter.fromJson(json)
            assertThat(stationTimetableList).isNotNull
            assertThat(stationTimetableList!!.size).isEqualTo(1)

            val stationTimetable0 = stationTimetableList[0]
            assertThat(stationTimetable0.id).isEqualTo("urn:ucode:_00001C000000000000010000030FD7E5")
            assertThat(stationTimetable0.date).isEqualTo("2017-01-13T15:10:00+09:00")
            assertThat(stationTimetable0.issued).isEqualTo("2017-01-01")
            assertThat(stationTimetable0.valid).isNull()
            assertThat(stationTimetable0.sameAs).isEqualTo("odpt.StationTimetable:JR-East.ChuoRapid.Tokyo.Outbound.Weekday")
            assertThat(stationTimetable0.operator).isEqualTo("odpt.Operator:JR-East")
            assertThat(stationTimetable0.railway).isEqualTo("odpt.Railway:JR-East.ChuoRapid")
            assertThat(stationTimetable0.railwayTitleMap).isNull()
            assertThat(stationTimetable0.station).isEqualTo("odpt.Station:JR-East.ChuoRapid.Tokyo")
            assertThat(stationTimetable0.stationTitleMap).isNull()
            assertThat(stationTimetable0.railDirection).isEqualTo("odpt.RailDirection:JR-East.Outbound")
            assertThat(stationTimetable0.calendar).isEqualTo("odpt.Calendar:Weekday")

            assertThat(stationTimetable0.stationTimetableObjectList.size).isEqualTo(3)
            val stationTimetableObject0 = stationTimetable0.stationTimetableObjectList[0]
            assertThat(stationTimetableObject0.arrivalTime).isNull()
            assertThat(stationTimetableObject0.departureTime).isEqualTo("06:00")
            assertThat(stationTimetableObject0.originStationList).isNull()
            assertThat(stationTimetableObject0.destinationStationList).isNotNull
            assertThat(stationTimetableObject0.destinationStationList!!.size).isEqualTo(1)
            assertThat(stationTimetableObject0.destinationStationList!![0]).isEqualTo("odpt.Station:JR-East.ChuoRapid.Takao")
            assertThat(stationTimetableObject0.viaStationList).isNull()
            assertThat(stationTimetableObject0.viaRailwayList).isNull()
            assertThat(stationTimetableObject0.train).isNull()
            assertThat(stationTimetableObject0.trainNumber).isNull()
            assertThat(stationTimetableObject0.trainType).isNotNull()
            assertThat(stationTimetableObject0.trainType!!).isEqualTo("odpt.TrainType:JR-East.Rapid")
            assertThat(stationTimetableObject0.trainName).isNull()
            assertThat(stationTimetableObject0.trainOwner).isNull()
            assertThat(stationTimetableObject0.isLast).isFalse()
            assertThat(stationTimetableObject0.isOrigin).isFalse()
            assertThat(stationTimetableObject0.platformNumber).isNull()
            assertThat(stationTimetableObject0.platformNameMap).isNull()
            assertThat(stationTimetableObject0.carComposition).isNull()
            assertThat(stationTimetableObject0.note).isNull()

            assertThat(stationTimetableObject0.originStationIdList).isNull()
            assertThat(stationTimetableObject0.destinationStationIdList).isNotNull
            assertThat(stationTimetableObject0.destinationStationIdList!!.size).isEqualTo(1)
            assertThat(stationTimetableObject0.destinationStationIdList!![0].id).isEqualTo("odpt.Station:JR-East.ChuoRapid.Takao")
            assertThat(stationTimetableObject0.viaStationIdList).isNull()
            assertThat(stationTimetableObject0.viaRailwayIdList).isNull()
            assertThat(stationTimetableObject0.trainId).isNull()
            assertThat(stationTimetableObject0.trainTypeId).isNotNull
            assertThat(stationTimetableObject0.trainTypeId!!.id).isEqualTo("odpt.TrainType:JR-East.Rapid")
            assertThat(stationTimetableObject0.trainOwnerOperatorId).isNull()

            assertThat(stationTimetable0.note).isNull()

            assertThat(stationTimetable0.stationTimetableId.id).isEqualTo("odpt.StationTimetable:JR-East.ChuoRapid.Tokyo.Outbound.Weekday")
            assertThat(stationTimetable0.operatorId.id).isEqualTo("odpt.Operator:JR-East")
            assertThat(stationTimetable0.railwayId.id).isEqualTo("odpt.Railway:JR-East.ChuoRapid")
            assertThat(stationTimetable0.stationId).isNotNull
            assertThat(stationTimetable0.stationId!!.id).isEqualTo("odpt.Station:JR-East.ChuoRapid.Tokyo")
            assertThat(stationTimetable0.railDirectionId).isNotNull
            assertThat(stationTimetable0.railDirectionId!!.id).isEqualTo("odpt.RailDirection:JR-East.Outbound")
            assertThat(stationTimetable0.calendarId).isNotNull
            assertThat(stationTimetable0.calendarId!!.id).isEqualTo("odpt.Calendar:Weekday")
        }
    }
}
