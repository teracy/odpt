package com.github.teracy.odpt.core.v2.train.response

import com.github.teracy.odpt.testutil.ApiResponseTest
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.experimental.runners.Enclosed
import org.junit.runner.RunWith

/**
 * v2版駅時刻表情報APIレスポンスのテスト
 */
@RunWith(Enclosed::class)
class OdptStationTimetableTest {
    /**
     * デシリアライズのテスト
     */
    class DeserializeTest : ApiResponseTest {
        private val parameterizedType = Types.newParameterizedType(List::class.java, OdptStationTimetable::class.java)

        lateinit var jsonAdapter: JsonAdapter<List<OdptStationTimetable>>

        @Before
        fun setUp() {
            jsonAdapter = Moshi.Builder().build().adapter(parameterizedType)
        }

        /**
         * 東京メトロオープンデータAPIサンプルデータより
         * NOTE: odpt:weekdays,odpt:saturdays,odpt:holidaysがmalformedだったため、修正してある
         */
        @Test
        fun tokyoMetroSampleData() {
            // テストデータ
            val json =
                "[\n" +
                        "  {\n" +
                        "    \"@id\": \"urn:ucode:_00001C000000000000010000030C3D7E\",\n" +
                        "    \"@type\": \"odpt:StationTimetable\",\n" +
                        "    \"@context\": \"http://vocab.tokyometroapp.jp/context_odpt_StationTimetable.jsonld\",\n" +
                        "    \"owl:sameAs\": \"odpt.StationTimetable:TokyoMetro.Tozai.Otemachi.Nakano\",\n" +
                        "    \"odpt:station\": \"odpt.Station:TokyoMetro.Tozai.Otemachi\",\n" +
                        "    \"odpt:railway\": \"odpt.Railway:TokyoMetro.Tozai\",\n" +
                        "    \"odpt:operator\": \"odpt.Operator:TokyoMetro\",\n" +
                        "    \"odpt:railDirection\": \"odpt.RailDirection:TokyoMetro.Nakano\",\n" +
                        "    \"dc:date\": \"2014-08-25T21:16:03+09:00\",\n" +
                        "    \"odpt:weekdays\": [\n" +
                        "      {\n" +
                        "        \"odpt:departureTime\": \"05:09\",\n" +
                        "        \"odpt:destinationStation\": \"odpt.Station:TokyoMetro.Tozai.Nakano\",\n" +
                        "        \"odpt:trainType\": \"odpt.TrainType:TokyoMetro.Local\"\n" +
                        "      },\n" +
                        "      {\n" +
                        "        \"odpt:departureTime\": \"05:18\",\n" +
                        "        \"odpt:destinationStation\": \"odpt.Station:TokyoMetro.Tozai.Nakano\",\n" +
                        "        \"odpt:trainType\": \"odpt.TrainType:TokyoMetro.Local\"\n" +
                        "      },\n" +
                        "      {\n" +
                        "        \"odpt:departureTime\": \"05:30\",\n" +
                        "        \"odpt:destinationStation\": \"odpt.Station:TokyoMetro.Tozai.Nakano\",\n" +
                        "        \"odpt:trainType\": \"odpt.TrainType:TokyoMetro.Local\"\n" +
                        "      },\n" +
                        "      {\n" +
                        "        \"odpt:departureTime\": \"05:36\",\n" +
                        "        \"odpt:destinationStation\": \"odpt.Station:TokyoMetro.Tozai.Nakano\",\n" +
                        "        \"odpt:trainType\": \"odpt.TrainType:TokyoMetro.Local\"\n" +
                        "      },\n" +
                        "      {\n" +
                        "        \"odpt:departureTime\": \"05:48\",\n" +
                        "        \"odpt:destinationStation\": \"odpt.Station:TokyoMetro.Tozai.Nakano\",\n" +
                        "        \"odpt:trainType\": \"odpt.TrainType:TokyoMetro.Local\"\n" +
                        "      },\n" +
                        "      {\n" +
                        "        \"odpt:departureTime\": \"05:44\",\n" +
                        "        \"odpt:destinationStation\": \"odpt.Station:JR-East.Chuo.Mitaka\",\n" +
                        "        \"odpt:trainType\": \"odpt.TrainType:TokyoMetro.Local\"\n" +
                        "      }\n" +
                        "    ],\n" +
                        "    \"odpt:saturdays\": [],\n" +
                        "    \"odpt:holidays\": []\n" +
                        "  }\n" +
                        "]"
            val stationTimetableList = jsonAdapter.fromJson(json)
            assertThat(stationTimetableList).isNotNull
            assertThat(stationTimetableList!!.size).isEqualTo(1)

            val stationTimetable0 = stationTimetableList[0]
            assertThat(stationTimetable0.id).isEqualTo("urn:ucode:_00001C000000000000010000030C3D7E")
            assertThat(stationTimetable0.sameAs).isEqualTo("odpt.StationTimetable:TokyoMetro.Tozai.Otemachi.Nakano")
            assertThat(stationTimetable0.date).isEqualTo("2014-08-25T21:16:03+09:00")
            assertThat(stationTimetable0.station).isEqualTo("odpt.Station:TokyoMetro.Tozai.Otemachi")
            assertThat(stationTimetable0.railway).isEqualTo("odpt.Railway:TokyoMetro.Tozai")
            assertThat(stationTimetable0.operator).isEqualTo("odpt.Operator:TokyoMetro")
            assertThat(stationTimetable0.railDirection).isEqualTo("odpt.RailDirection:TokyoMetro.Nakano")

            assertThat(stationTimetable0.weekdayStationTimetableObjectList).isNotNull
            assertThat(stationTimetable0.weekdayStationTimetableObjectList!!.size).isEqualTo(6)
            val weekdays0 = stationTimetable0.weekdayStationTimetableObjectList!![0]
            assertThat(weekdays0.departureTime).isEqualTo("05:09")
            assertThat(weekdays0.destinationStation).isEqualTo("odpt.Station:TokyoMetro.Tozai.Nakano")
            assertThat(weekdays0.trainType).isEqualTo("odpt.TrainType:TokyoMetro.Local")
            assertThat(weekdays0.isLast).isFalse()
            assertThat(weekdays0.isOrigin).isFalse()
            assertThat(weekdays0.carComposition).isNull()
            assertThat(weekdays0.note).isNull()
            assertThat(weekdays0.destinationStationId.id).isEqualTo("odpt.Station:TokyoMetro.Tozai.Nakano")
            assertThat(weekdays0.trainTypeId.id).isEqualTo("odpt.TrainType:TokyoMetro.Local")

            assertThat(stationTimetable0.saturdayStationTimetableObjectList).isEmpty()
            assertThat(stationTimetable0.holidayStationTimetableObjectList).isEmpty()

            assertThat(stationTimetable0.stationTimetableId.id).isEqualTo("odpt.StationTimetable:TokyoMetro.Tozai.Otemachi.Nakano")
            assertThat(stationTimetable0.stationId.id).isEqualTo("odpt.Station:TokyoMetro.Tozai.Otemachi")
            assertThat(stationTimetable0.railwayId.id).isEqualTo("odpt.Railway:TokyoMetro.Tozai")
            assertThat(stationTimetable0.operatorId.id).isEqualTo("odpt.Operator:TokyoMetro")
            assertThat(stationTimetable0.railDirectionId.id).isEqualTo("odpt.RailDirection:TokyoMetro.Nakano")
        }

        /**
         * 東京メトロオープンデータAPIサンプルURLより（東西線大手町駅）
         * https://api.tokyometroapp.jp/api/v2/datapoints?rdf:type=odpt:StationTimetable&odpt:station=odpt.Station:TokyoMetro.Tozai.Otemachi&acl:consumerKey=ACL_CONSUMERKEY
         */
        @Test
        fun tokyoMetroTozaiOtemachi() {
            // テストデータ
            val json = readJsonFile("StationTimetableTozaiOtemachi.json")
            val stationTimetableList = jsonAdapter.fromJson(json)
            assertThat(stationTimetableList).isNotNull
            assertThat(stationTimetableList!!.size).isEqualTo(2)

            // データが非常に多いので、weekdays/saturdays/holidaysのNotEmptyのみ検査
            val stationTimetable0 = stationTimetableList[0]
            assertThat(stationTimetable0.weekdayStationTimetableObjectList).isNotEmpty
            assertThat(stationTimetable0.saturdayStationTimetableObjectList).isNotEmpty
            assertThat(stationTimetable0.holidayStationTimetableObjectList).isNotEmpty
        }
    }
}
