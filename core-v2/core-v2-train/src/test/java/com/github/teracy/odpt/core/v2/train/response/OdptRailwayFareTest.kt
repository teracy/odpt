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
 * v2版鉄道運賃情報APIレスポンスのテスト
 */
@RunWith(Enclosed::class)
class OdptRailwayFareTest {
    /**
     * デシリアライズのテスト
     */
    class DeserializeTest {
        private val parameterizedType = Types.newParameterizedType(List::class.java, OdptRailwayFare::class.java)

        lateinit var jsonAdapter: JsonAdapter<List<OdptRailwayFare>>

        @Before
        fun setUp() {
            jsonAdapter = Moshi.Builder().build().adapter(parameterizedType)
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
                        "    \"@id\": \"urn:ucode:_00001C000000000000010000030C4D72\",\n" +
                        "    \"@type\": \"odpt:RailwayFare\",\n" +
                        "    \"owl:sameAs\": \"odpt.RailwayFare:TokyoMetro.Ginza.TokyoMetro.Asakusa.Ginza.Tawaramachi\",\n" +
                        "    \"dc:date\": \"2014-08-29T16:03:36+09:00\",\n" +
                        "    \"odpt:operator\": \"odpt.Operator:TokyoMetro\",\n" +
                        "    \"odpt:fromStation\": \"odpt.Station:TokyoMetro.Ginza.Asakusa\",\n" +
                        "    \"odpt:toStation\": \"odpt.Station:TokyoMetro.Ginza.Tawaramachi\",\n" +
                        "    \"odpt:ticketFare\": 170,\n" +
                        "    \"odpt:childTicketFare\": 90,\n" +
                        "    \"odpt:icCardFare\": 165,\n" +
                        "    \"odpt:childIcCardFare\": 82,\n" +
                        "    \"@context\": \"http://vocab.tokyometroapp.jp/context_odpt_RailwayFare.jsonld\"\n" +
                        "  }\n" +
                        "]"
            val railwayFareList = jsonAdapter.fromJson(json)
            assertThat(railwayFareList).isNotNull
            assertThat(railwayFareList!!.size).isEqualTo(1)

            val railwayFare0 = railwayFareList[0]
            assertThat(railwayFare0.id).isEqualTo("urn:ucode:_00001C000000000000010000030C4D72")
            assertThat(railwayFare0.sameAs).isEqualTo("odpt.RailwayFare:TokyoMetro.Ginza.TokyoMetro.Asakusa.Ginza.Tawaramachi")
            assertThat(railwayFare0.date).isEqualTo("2014-08-29T16:03:36+09:00")
            assertThat(railwayFare0.operator).isEqualTo("odpt.Operator:TokyoMetro")
            assertThat(railwayFare0.fromStation).isEqualTo("odpt.Station:TokyoMetro.Ginza.Asakusa")
            assertThat(railwayFare0.toStation).isEqualTo("odpt.Station:TokyoMetro.Ginza.Tawaramachi")
            assertThat(railwayFare0.ticketFare).isEqualTo(170)
            assertThat(railwayFare0.childTicketFare).isEqualTo(90)
            assertThat(railwayFare0.icCardFare).isEqualTo(165)
            assertThat(railwayFare0.childIcCardFare).isEqualTo(82)

            assertThat(railwayFare0.railwayFareId.id).isEqualTo("odpt.RailwayFare:TokyoMetro.Ginza.TokyoMetro.Asakusa.Ginza.Tawaramachi")
            assertThat(railwayFare0.operatorId.id).isEqualTo("odpt.Operator:TokyoMetro")
            assertThat(railwayFare0.fromStationId.id).isEqualTo("odpt.Station:TokyoMetro.Ginza.Asakusa")
            assertThat(railwayFare0.toStationId.id).isEqualTo("odpt.Station:TokyoMetro.Ginza.Tawaramachi")
        }
    }
}
