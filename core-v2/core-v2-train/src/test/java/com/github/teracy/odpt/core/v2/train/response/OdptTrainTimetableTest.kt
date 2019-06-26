package com.github.teracy.odpt.core.v2.train.response

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.experimental.runners.Enclosed
import org.junit.runner.RunWith

/**
 * v2版列車時刻表情報APIレスポンスのテスト
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
         * 東京メトロオープンデータAPIサンプルデータより
         * NOTE:「(途中省略)」はmalformedになるので除外してある
         */
        @Test
        fun tokyoMetroSampleData() {
            // テストデータ
            val json =
                "[\n" +
                        "  {\n" +
                        "    \"@context\": \"http://vocab.tokyometroapp.jp/context_odpt_TrainTimetable.jsonld\",\n" +
                        "    \"@id\": \"urn:ucode:_00001C000000000000010000030D01E1\",\n" +
                        "    \"@type\": \"odpt:TrainTimetable\",\n" +
                        "    \"owl:sameAs\": \"odpt.TrainTimetable:TokyoMetro.Ginza.A0501.SaturdaysHolidays\",\n" +
                        "    \"odpt:trainNumber\": \"A0501\",\n" +
                        "    \"odpt:railway\": \"odpt.Railway:TokyoMetro.Ginza\",\n" +
                        "    \"odpt:operator\": \"odpt.Operator:TokyoMetro\",\n" +
                        "    \"dc:date\": \"2014-10-17T19:11:04+09:00\",\n" +
                        "    \"odpt:trainType\": \"odpt.TrainType:TokyoMetro.Local\",\n" +
                        "    \"odpt:railDirection\": \"odpt.RailDirection:TokyoMetro.Shibuya\",\n" +
                        "    \"odpt:saturdays\": [\n" +
                        "      {\n" +
                        "        \"odpt:departureTime\": \"05:01\",\n" +
                        "        \"odpt:departureStation\": \"odpt.Station:TokyoMetro.Ginza.Asakusa\"\n" +
                        "      },\n" +
                        "      {\n" +
                        "        \"odpt:departureTime\": \"05:03\",\n" +
                        "        \"odpt:departureStation\": \"odpt.Station:TokyoMetro.Ginza.Tawaramachi\"\n" +
                        "      },\n" +
                        "      {\n" +
                        "        \"odpt:departureTime\": \"05:05\",\n" +
                        "        \"odpt:departureStation\": \"odpt.Station:TokyoMetro.Ginza.Inaricho\"\n" +
                        "      },\n" +
                        "      {\n" +
                        "        \"odpt:departureTime\": \"05:06\",\n" +
                        "        \"odpt:departureStation\": \"odpt.Station:TokyoMetro.Ginza.Ueno\"\n" +
                        "      },\n" +
                        "      {\n" +
                        "        \"odpt:departureTime\": \"05:08\",\n" +
                        "        \"odpt:departureStation\": \"odpt.Station:TokyoMetro.Ginza.UenoHirokoji\"\n" +
                        "      },\n" +
                        "      {\n" +
                        "        \"odpt:departureTime\": \"05:09\",\n" +
                        "        \"odpt:departureStation\": \"odpt.Station:TokyoMetro.Ginza.Suehirocho\"\n" +
                        "      },\n" +
                        "      {\n" +
                        "        \"odpt:departureTime\": \"05:31\",\n" +
                        "        \"odpt:departureStation\": \"odpt.Station:TokyoMetro.Ginza.OmoteSando\"\n" +
                        "      },\n" +
                        "      {\n" +
                        "        \"odpt:arrivalTime\": \"05:33\",\n" +
                        "        \"odpt:arrivalStation\": \"odpt.Station:TokyoMetro.Ginza.Shibuya\"\n" +
                        "      }\n" +
                        "    ],\n" +
                        "    \"odpt:holidays\": [],\n" +
                        "    \"odpt:train\": \"odpt.Train:TokyoMetro.Ginza.A0501\",\n" +
                        "    \"odpt:terminalStation\": \"odpt.Station:TokyoMetro.Ginza.Shibuya\",\n" +
                        "    \"odpt:trainOwner\": \"odpt.TrainOwner:TokyoMetro\"\n" +
                        "  }\n" +
                        "]"
            val trainTimetableList = jsonAdapter.fromJson(json)
            assertThat(trainTimetableList).isNotNull
            assertThat(trainTimetableList!!.size).isEqualTo(1)

            val trainTimetable0 = trainTimetableList[0]
            assertThat(trainTimetable0.id).isEqualTo("urn:ucode:_00001C000000000000010000030D01E1")
            assertThat(trainTimetable0.sameAs).isEqualTo("odpt.TrainTimetable:TokyoMetro.Ginza.A0501.SaturdaysHolidays")
            assertThat(trainTimetable0.date).isEqualTo("2014-10-17T19:11:04+09:00")
            assertThat(trainTimetable0.trainNumber).isEqualTo("A0501")
            assertThat(trainTimetable0.railway).isEqualTo("odpt.Railway:TokyoMetro.Ginza")
            assertThat(trainTimetable0.train).isEqualTo("odpt.Train:TokyoMetro.Ginza.A0501")
            assertThat(trainTimetable0.operator).isEqualTo("odpt.Operator:TokyoMetro")
            assertThat(trainTimetable0.trainType).isEqualTo("odpt.TrainType:TokyoMetro.Local")
            assertThat(trainTimetable0.railDirection).isEqualTo("odpt.RailDirection:TokyoMetro.Shibuya")
            assertThat(trainTimetable0.startingStation).isNull()
            assertThat(trainTimetable0.terminalStation).isEqualTo("odpt.Station:TokyoMetro.Ginza.Shibuya")
            assertThat(trainTimetable0.trainOwner).isEqualTo("odpt.TrainOwner:TokyoMetro")

            assertThat(trainTimetable0.weekdayTrainTimetableObjectList).isNull()

            assertThat(trainTimetable0.saturdayTrainTimetableObjectList).isNotNull
            assertThat(trainTimetable0.saturdayTrainTimetableObjectList!!.size).isEqualTo(8)
            val saturdays0 = trainTimetable0.saturdayTrainTimetableObjectList!![0]
            assertThat(saturdays0.departureTime).isEqualTo("05:01")
            assertThat(saturdays0.departureStation)
            assertThat(saturdays0.departureStation).isEqualTo("odpt.Station:TokyoMetro.Ginza.Asakusa")
            assertThat(saturdays0.arrivalTime).isNull()
            assertThat(saturdays0.arrivalStation).isNull()
            assertThat(saturdays0.departureStationId).isNotNull
            assertThat(saturdays0.departureStationId!!.id).isEqualTo("odpt.Station:TokyoMetro.Ginza.Asakusa")
            assertThat(saturdays0.arrivalStationId).isNull()

            val saturdays7 = trainTimetable0.saturdayTrainTimetableObjectList!![7]
            assertThat(saturdays7.departureTime).isNull()
            assertThat(saturdays7.departureStation).isNull()
            assertThat(saturdays7.arrivalTime).isEqualTo("05:33")
            assertThat(saturdays7.arrivalStation).isEqualTo("odpt.Station:TokyoMetro.Ginza.Shibuya")
            assertThat(saturdays7.departureStationId).isNull()
            assertThat(saturdays7.arrivalStationId).isNotNull
            assertThat(saturdays7.arrivalStationId!!.id).isEqualTo("odpt.Station:TokyoMetro.Ginza.Shibuya")

            assertThat(trainTimetable0.holidayTrainTimetableObjectList).isNotNull
            assertThat(trainTimetable0.holidayTrainTimetableObjectList).isEmpty()

            assertThat(trainTimetable0.trainTimetableId.id).isEqualTo("odpt.TrainTimetable:TokyoMetro.Ginza.A0501.SaturdaysHolidays")
            assertThat(trainTimetable0.railwayId.id).isEqualTo("odpt.Railway:TokyoMetro.Ginza")
            assertThat(trainTimetable0.trainId.id).isEqualTo("odpt.Train:TokyoMetro.Ginza.A0501")
            assertThat(trainTimetable0.operatorId.id).isEqualTo("odpt.Operator:TokyoMetro")
            assertThat(trainTimetable0.trainTypeId.id).isEqualTo("odpt.TrainType:TokyoMetro.Local")
            assertThat(trainTimetable0.railDirectionId.id).isEqualTo("odpt.RailDirection:TokyoMetro.Shibuya")
            assertThat(trainTimetable0.terminalStationId.id).isEqualTo("odpt.Station:TokyoMetro.Ginza.Shibuya")
            assertThat(trainTimetable0.startingStationId).isNull()
            assertThat(trainTimetable0.trainOwnerId).isNotNull
            assertThat(trainTimetable0.trainOwnerId!!.id).isEqualTo("odpt.TrainOwner:TokyoMetro")
        }
    }
}
