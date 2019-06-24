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
 * v4版駅情報APIレスポンスのテスト
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
                        "  \"@type\" : \"odpt:Station\",\n" +
                        "  \"dc:date\" : \"2017-01-13T15:10:00+09:00\",\n" +
                        "  \"owl:sameAs\" : \"odpt.Station:JR-East.Yamanote.Tokyo\",\n" +
                        "  \"dc:title\" : \"東京\",\n" +
                        "  \"odpt:stationTitle\" : {\n" +
                        "    \"ja\" : \"東京\",\n" +
                        "    \"en\" : \"Tokyo\"\n" +
                        "  },\n" +
                        "  \"odpt:operator\" : \"odpt.Operator:JR-East\",\n" +
                        "  \"odpt:railway\" : \"odpt.Railway:JR-East.Yamanote\",\n" +
                        "  \"geo:long\" : 139.1234,\n" +
                        "  \"geo:lat\" : 35.1234\n" +
                        "} ]"
            val stationList = jsonAdapter.fromJson(json)
            assertThat(stationList).isNotNull
            assertThat(stationList!!.size).isEqualTo(1)

            val station0 = stationList[0]
            assertThat(station0.id).isEqualTo("urn:ucode:_00001C000000000000010000030FD7E5")
            assertThat(station0.date).isEqualTo("2017-01-13T15:10:00+09:00")
            assertThat(station0.sameAs).isEqualTo("odpt.Station:JR-East.Yamanote.Tokyo")
            assertThat(station0.title).isEqualTo("東京")
            assertThat(station0.titleMap).isNotNull
            assertThat(station0.titleMap!!.size).isEqualTo(2)
            assertThat(station0.titleMap!!["ja"]).isEqualTo("東京")
            assertThat(station0.titleMap!!["en"]).isEqualTo("Tokyo")
            assertThat(station0.operator).isEqualTo("odpt.Operator:JR-East")
            assertThat(station0.railway).isEqualTo("odpt.Railway:JR-East.Yamanote")
            assertThat(station0.stationCode).isNull()
            assertThat(station0.longitude).isEqualTo(139.1234)
            assertThat(station0.latitude).isEqualTo(35.1234)
            assertThat(station0.region).isNull()
            assertThat(station0.exitList).isNull()
            assertThat(station0.connectingRailwayList).isNull()
            assertThat(station0.stationTimetableList).isNull()
            assertThat(station0.passengerSurveyList).isNull()

            assertThat(station0.stationId.id).isEqualTo("odpt.Station:JR-East.Yamanote.Tokyo")
            assertThat(station0.operatorId.id).isEqualTo("odpt.Operator:JR-East")
            assertThat(station0.railwayId.id).isEqualTo("odpt.Railway:JR-East.Yamanote")
            assertThat(station0.connectingRailwayIdList).isNull()
            assertThat(station0.stationTimetableIdList).isNull()
            assertThat(station0.passengerSurveyIdList).isNull()
        }
    }
}
