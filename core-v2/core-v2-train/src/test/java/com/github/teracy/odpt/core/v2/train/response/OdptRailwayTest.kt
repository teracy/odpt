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
 * v2版鉄道路線情報APIレスポンスのテスト
 */
@RunWith(Enclosed::class)
class OdptRailwayTest {
    /**
     * デシリアライズのテスト
     */
    class DeserializeTest {
        private val parameterizedType = Types.newParameterizedType(List::class.java, OdptRailway::class.java)

        lateinit var jsonAdapter: JsonAdapter<List<OdptRailway>>

        @Before
        fun setUp() {
            jsonAdapter = Moshi.Builder().build().adapter(parameterizedType)
        }

        /**
         * 東京メトロオープンデータAPIサンプルデータより
         * NOTE:odpt:womenOnlyCarがmalformedだったりtypoしていたりしていたため修正してある（実データで確認済み）
         */
        @Test
        fun tokyoMetroSampleData() {
            // テストデータ
            val json =
                "[\n" +
                        "  {\n" +
                        "    \"@context\": \"http://vocab.tokyometroapp.jp/context_odpt_Railway.jsonld\",\n" +
                        "    \"@id\": \"urn:ucode:_00001C000000000000010000030C46AE\",\n" +
                        "    \"@type\": \"odpt:Railway\",\n" +
                        "    \"ug:region\": \"https://api.tokyometroapp.jp/api/v2/places/urn:ucode:_00001C000000000000010000030C46AE.geojson\",\n" +
                        "    \"owl:sameAs\": \"odpt.Railway:TokyoMetro.Hibiya\",\n" +
                        "    \"odpt:operator\": \"odpt.Operator:TokyoMetro\",\n" +
                        "    \"dc:title\": \"日比谷\",\n" +
                        "    \"dc:date\": \"2014-09-17T01:15:51+09:00\",\n" +
                        "    \"odpt:stationOrder\": [\n" +
                        "      {\n" +
                        "        \"odpt:station\": \"odpt.Station:TokyoMetro.Hibiya.NakaMeguro\",\n" +
                        "        \"odpt:index\": 0\n" +
                        "      },\n" +
                        "      {\n" +
                        "        \"odpt:station\": \"odpt.Station:TokyoMetro.Hibiya.Ebisu\",\n" +
                        "        \"odpt:index\": 1\n" +
                        "      },\n" +
                        "      {\n" +
                        "        \"odpt:station\": \"odpt.Station:TokyoMetro.Hibiya.HiroO\",\n" +
                        "        \"odpt:index\": 2\n" +
                        "      },\n" +
                        "      {\n" +
                        "        \"odpt:station\": \"odpt.Station:TokyoMetro.Hibiya.Roppongi\",\n" +
                        "        \"odpt:index\": 3\n" +
                        "      }\n" +
                        "    ],\n" +
                        "    \"odpt:travelTime\": [\n" +
                        "      {\n" +
                        "        \"odpt:fromStation\": \"odpt.Station:TokyoMetro.Hibiya.NakaMeguro\",\n" +
                        "        \"odpt:toStation\": \"odpt.Station:TokyoMetro.Hibiya.Ebisu\",\n" +
                        "        \"odpt:necessaryTime\": 2,\n" +
                        "        \"odpt:trainType\": \"odpt.TrainType:Local\"\n" +
                        "      },\n" +
                        "      {\n" +
                        "        \"odpt:fromStation\": \"odpt.Station:TokyoMetro.Hibiya.Ebisu\",\n" +
                        "        \"odpt:toStation\": \"odpt.Station:TokyoMetro.Hibiya.HiroO\",\n" +
                        "        \"odpt:necessaryTime\": 3,\n" +
                        "        \"odpt:trainType\": \"odpt.TrainType:Local\"\n" +
                        "      },\n" +
                        "      {\n" +
                        "        \"odpt:fromStation\": \"odpt.Station:TokyoMetro.Hibiya.HiroO\",\n" +
                        "        \"odpt:toStation\": \"odpt.Station:TokyoMetro.Hibiya.Roppongi\",\n" +
                        "        \"odpt:trainType\": \"odpt.TrainType:Local\",\n" +
                        "        \"odpt:necessaryTime\": 3\n" +
                        "      }\n" +
                        "    ],\n" +
                        "    \"odpt:lineCode\": \"H\",\n" +
                        "    \"odpt:womenOnlyCar\": [\n" +
                        "        {\n" +
                        "          \"odpt:fromStation\": \"odpt.Station:TokyoMetro.Hibiya.KitaSenju\",\n" +
                        "          \"odpt:toStation\": \"odpt.Station:TokyoMetro.Hibiya.NakaMeguro\",\n" +
                        "          \"odpt:operationDay\": \"Weekday\",\n" +
                        "          \"odpt:availableTimeFrom\": \"07:30\",\n" +
                        "          \"odpt:availableTimeUntil\": \"09:00\",\n" +
                        "          \"odpt:carComposition\": 8,\n" +
                        "          \"odpt:carNumber\": 1\n" +
                        "        }\n" +
                        "    ]\n" +
                        "  }\n" +
                        "]"
            val railwayList = jsonAdapter.fromJson(json)
            assertThat(railwayList).isNotNull
            assertThat(railwayList!!.size).isEqualTo(1)

            val railway0 = railwayList[0]
            assertThat(railway0.id).isEqualTo("urn:ucode:_00001C000000000000010000030C46AE")
            assertThat(railway0.sameAs).isEqualTo("odpt.Railway:TokyoMetro.Hibiya")
            assertThat(railway0.date).isEqualTo("2014-09-17T01:15:51+09:00")
            assertThat(railway0.title).isEqualTo("日比谷")
            assertThat(railway0.region).isEqualTo("https://api.tokyometroapp.jp/api/v2/places/urn:ucode:_00001C000000000000010000030C46AE.geojson")
            assertThat(railway0.operator).isEqualTo("odpt.Operator:TokyoMetro")

            assertThat(railway0.stationOrder.size).isEqualTo(4)
            val stationOrder0 = railway0.stationOrder[0]
            assertThat(stationOrder0.station).isEqualTo("odpt.Station:TokyoMetro.Hibiya.NakaMeguro")
            assertThat(stationOrder0.index).isEqualTo(0)
            assertThat(stationOrder0.stationId.id).isEqualTo("odpt.Station:TokyoMetro.Hibiya.NakaMeguro")

            assertThat(railway0.travelTime.size).isEqualTo(3)
            val travelTime0 = railway0.travelTime[0]
            assertThat(travelTime0.fromStation).isEqualTo("odpt.Station:TokyoMetro.Hibiya.NakaMeguro")
            assertThat(travelTime0.toStation).isEqualTo("odpt.Station:TokyoMetro.Hibiya.Ebisu")
            assertThat(travelTime0.necessaryTime).isEqualTo(2)
            assertThat(travelTime0.trainType).isEqualTo("odpt.TrainType:Local")

            assertThat(travelTime0.fromStationId.id).isEqualTo("odpt.Station:TokyoMetro.Hibiya.NakaMeguro")
            assertThat(travelTime0.toStationId.id).isEqualTo("odpt.Station:TokyoMetro.Hibiya.Ebisu")
            assertThat(travelTime0.trainTypeId.id).isEqualTo("odpt.TrainType:Local")

            assertThat(railway0.lineCode).isEqualTo("H")

            assertThat(railway0.womenOnlyCar).isNotNull
            assertThat(railway0.womenOnlyCar!!.size).isEqualTo(1)
            val womenOnlyCar0 = railway0.womenOnlyCar!![0]
            assertThat(womenOnlyCar0.fromStation).isEqualTo("odpt.Station:TokyoMetro.Hibiya.KitaSenju")
            assertThat(womenOnlyCar0.toStation).isEqualTo("odpt.Station:TokyoMetro.Hibiya.NakaMeguro")
            assertThat(womenOnlyCar0.operationDay).isEqualTo("Weekday")
            assertThat(womenOnlyCar0.availableTimeFrom).isEqualTo("07:30")
            assertThat(womenOnlyCar0.availableTimeUntil).isEqualTo("09:00")
            assertThat(womenOnlyCar0.carComposition).isEqualTo(8)
            assertThat(womenOnlyCar0.carNumber).isEqualTo(1)
            assertThat(womenOnlyCar0.fromStationId).isNotNull
            assertThat(womenOnlyCar0.fromStationId!!.id).isEqualTo("odpt.Station:TokyoMetro.Hibiya.KitaSenju")
            assertThat(womenOnlyCar0.toStationId).isNotNull
            assertThat(womenOnlyCar0.toStationId!!.id).isEqualTo("odpt.Station:TokyoMetro.Hibiya.NakaMeguro")

            assertThat(railway0.railwayId.id).isEqualTo("odpt.Railway:TokyoMetro.Hibiya")
            assertThat(railway0.operatorId.id).isEqualTo("odpt.Operator:TokyoMetro")
        }
    }
}
