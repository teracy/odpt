package com.github.teracy.odpt.core.v2.train.response

import com.github.teracy.odpt.model.adapter.ArrayOrObjectAdapter
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.experimental.runners.Enclosed
import org.junit.runner.RunWith

/**
 * v2版駅情報APIレスポンスのテスト
 */
@RunWith(Enclosed::class)
class OdptStationTest {
    /**
     * デシリアライズのテスト
     */
    class DeserializeTest {
        private val parameterizedType = Types.newParameterizedType(List::class.java, OdptStation::class.java)

        lateinit var jsonAdapter: JsonAdapter<List<OdptStation>>

        @Before
        fun setUp() {
            jsonAdapter = Moshi.Builder()
                .add(ArrayOrObjectAdapter.FACTORY)
                .build().adapter(parameterizedType)
        }

        /**
         * 東京メトロオープンデータAPIサンプルデータより
         */
        @Test
        fun tokyoMetroSampleData() {
            // テストデータ
            val json =
                "[\n" +
                        "  {\n" +
                        "    \"@id\": \"urn:ucode:_00001C000000000000010000030C46CD\",\n" +
                        "    \"@type\": \"odpt:Station\",\n" +
                        "    \"owl:sameAs\": \"odpt.Station:TokyoMetro.Hibiya.Ueno\",\n" +
                        "    \"dc:date\": \"2014-10-02T22:13:46+09:00\",\n" +
                        "    \"dc:title\": \"上野\",\n" +
                        "    \"ug:region\": \"https://api.tokyometroapp.jp/api/v2/places/urn:ucode:_00001C000000000000010000030C46CD.geojson\",\n" +
                        "    \"odpt:operator\": \"odpt.Operator:TokyoMetro\",\n" +
                        "    \"odpt:railway\": \"odpt.Railway:TokyoMetro.Hibiya\",\n" +
                        "    \"odpt:connectingRailway\": [\n" +
                        "      \"odpt.Railway:JR-East.Yamanote\",\n" +
                        "      \"odpt.Railway:JR-East.Utsunomiya\",\n" +
                        "      \"odpt.Railway:JR-East.Joban\",\n" +
                        "      \"odpt.Railway:JR-East.Takasaki\",\n" +
                        "      \"odpt.Railway:JR-East.KeihinTohoku\",\n" +
                        "      \"odpt.Railway:TokyoMetro.Ginza\",\n" +
                        "      \"odpt.Railway:Keisei.KeiseiMain\"\n" +
                        "    ],\n" +
                        "    \"odpt:facility\": \"odpt.StationFacility:TokyoMetro.Ueno\",\n" +
                        "    \"odpt:passengerSurvey\": [\n" +
                        "      \"odpt.PassengerSurvey:TokyoMetro.Ueno.2013\",\n" +
                        "      \"odpt.PassengerSurvey:TokyoMetro.Ueno.2012\",\n" +
                        "      \"odpt.PassengerSurvey:TokyoMetro.Ueno.2011\"\n" +
                        "    ],\n" +
                        "    \"odpt:stationCode\": \"H17\",\n" +
                        "    \"odpt:exit\": [\n" +
                        "      \"urn:ucode:_00001C000000000000010000030C4471\",\n" +
                        "      \"urn:ucode:_00001C000000000000010000030C446F\",\n" +
                        "      \"urn:ucode:_00001C000000000000010000030C4466\",\n" +
                        "      \"urn:ucode:_00001C000000000000010000030C4486\",\n" +
                        "      \"urn:ucode:_00001C000000000000010000030C4487\",\n" +
                        "      \"urn:ucode:_00001C000000000000010000030C4488\",\n" +
                        "      \"urn:ucode:_00001C000000000000010000030C446A\",\n" +
                        "      \"urn:ucode:_00001C000000000000010000030C446B\",\n" +
                        "      \"urn:ucode:_00001C000000000000010000030C446C\",\n" +
                        "      \"urn:ucode:_00001C000000000000010000030C4489\",\n" +
                        "      \"urn:ucode:_00001C000000000000010000030C446E\",\n" +
                        "      \"urn:ucode:_00001C000000000000010000030C447E\"\n" +
                        "    ],\n" +
                        "    \"@context\": \"http://vocab.tokyometroapp.jp/context_odpt_Station.jsonld\",\n" +
                        "    \"geo:lat\": 35.7115576470288,\n" +
                        "    \"geo:long\": 139.77706059628\n" +
                        "  }\n" +
                        "]"
            val stationList = jsonAdapter.fromJson(json)
            assertThat(stationList).isNotNull
            assertThat(stationList!!.size).isEqualTo(1)

            val station0 = stationList[0]
            assertThat(station0.id).isEqualTo("urn:ucode:_00001C000000000000010000030C46CD")
            assertThat(station0.sameAs).isEqualTo("odpt.Station:TokyoMetro.Hibiya.Ueno")
            assertThat(station0.title).isEqualTo("上野")
            assertThat(station0.date).isEqualTo("2014-10-02T22:13:46+09:00")
            assertThat(station0.longitude).isEqualTo(139.77706059628)
            assertThat(station0.latitude).isEqualTo(35.7115576470288)
            assertThat(station0.region).isEqualTo("https://api.tokyometroapp.jp/api/v2/places/urn:ucode:_00001C000000000000010000030C46CD.geojson")
            assertThat(station0.operator).isEqualTo("odpt.Operator:TokyoMetro")
            assertThat(station0.railway).isEqualTo("odpt.Railway:TokyoMetro.Hibiya")
            assertThat(station0.connectingRailwayList).isNotNull
            assertThat(station0.connectingRailwayList!!.size).isEqualTo(7)
            assertThat(station0.connectingRailwayList!![0]).isEqualTo("odpt.Railway:JR-East.Yamanote")
            assertThat(station0.facilityList.size).isEqualTo(1)
            assertThat(station0.facilityList[0]).isEqualTo("odpt.StationFacility:TokyoMetro.Ueno")
            assertThat(station0.passengerSurveyList.size).isEqualTo(3)
            assertThat(station0.passengerSurveyList[0]).isEqualTo("odpt.PassengerSurvey:TokyoMetro.Ueno.2013")
            assertThat(station0.stationCode).isEqualTo("H17")
            assertThat(station0.exit.size).isEqualTo(12)
            assertThat(station0.exit[0]).isEqualTo("urn:ucode:_00001C000000000000010000030C4471")

            assertThat(station0.stationId.id).isEqualTo("odpt.Station:TokyoMetro.Hibiya.Ueno")
            assertThat(station0.operatorId.id).isEqualTo("odpt.Operator:TokyoMetro")
            assertThat(station0.railwayId.id).isEqualTo("odpt.Railway:TokyoMetro.Hibiya")
            assertThat(station0.connectingRailwayIdList).isNotNull
            assertThat(station0.connectingRailwayIdList!!.size).isEqualTo(7)
            assertThat(station0.connectingRailwayIdList!![0].id).isEqualTo("odpt.Railway:JR-East.Yamanote")
            assertThat(station0.facilityIdList.size).isEqualTo(1)
            assertThat(station0.facilityIdList[0].id).isEqualTo("odpt.StationFacility:TokyoMetro.Ueno")
            assertThat(station0.passengerSurveyIdList.size).isEqualTo(3)
            assertThat(station0.passengerSurveyIdList[0].id).isEqualTo("odpt.PassengerSurvey:TokyoMetro.Ueno.2013")
        }
    }
}
