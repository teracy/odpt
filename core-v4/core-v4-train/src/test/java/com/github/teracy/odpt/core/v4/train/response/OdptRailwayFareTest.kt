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
 * v4版鉄道運賃情報APIレスポンスのテスト
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
         * 東京公共交通オープンデータチャレンジAPIサンプルデータより
         */
        @Test
        fun tokyoChallengeSampleData() {
            // テストデータ
            val json =
                "[ {\n" +
                        "  \"@context\" : \"http://vocab.odpt.org/context_odpt.jsonld\",\n" +
                        "  \"@id\" : \"urn:ucode:_00001C000000000000010000030FD7E5\",\n" +
                        "  \"@type\" : \"odpt:RailwayFare\",\n" +
                        "  \"dc:date\" : \"2017-01-13T15:10:00+09:00\",\n" +
                        "  \"owl:sameAs\" : \"odpt.RailwayFare:TokyoMetro.Marunouchi.Tokyo.TokyoMetro.Tozai.Nakano\",\n" +
                        "  \"odpt:operator\" : \"odpt.Operator:TokyoMetro\",\n" +
                        "  \"odpt:fromStation\" : \"odpt.Station:TokyoMetro.Marunouchi.Tokyo\",\n" +
                        "  \"odpt:toStation\" : \"odpt.Station:TokyoMetro.Tozai.Nakano\",\n" +
                        "  \"odpt:ticketFare\" : 240,\n" +
                        "  \"odpt:icCardFare\" : 237,\n" +
                        "  \"odpt:childTicketFare\" : 120,\n" +
                        "  \"odpt:childIcCardFare\" : 118\n" +
                        "} ]"
            val railwayFareList = jsonAdapter.fromJson(json)
            assertThat(railwayFareList).isNotNull
            assertThat(railwayFareList!!.size).isEqualTo(1)

            val railway0 = railwayFareList[0]
            assertThat(railway0.id).isEqualTo("urn:ucode:_00001C000000000000010000030FD7E5")
            assertThat(railway0.date).isEqualTo("2017-01-13T15:10:00+09:00")
            assertThat(railway0.issued).isNull()
            assertThat(railway0.valid).isNull()
            assertThat(railway0.sameAs).isEqualTo("odpt.RailwayFare:TokyoMetro.Marunouchi.Tokyo.TokyoMetro.Tozai.Nakano")
            assertThat(railway0.operator).isEqualTo("odpt.Operator:TokyoMetro")
            assertThat(railway0.fromStation).isEqualTo("odpt.Station:TokyoMetro.Marunouchi.Tokyo")
            assertThat(railway0.toStation).isEqualTo("odpt.Station:TokyoMetro.Tozai.Nakano")
            assertThat(railway0.ticketFare).isEqualTo(240)
            assertThat(railway0.icCardFare).isEqualTo(237)
            assertThat(railway0.childTicketFare).isEqualTo(120)
            assertThat(railway0.childIcCardFare).isEqualTo(118)
            assertThat(railway0.viaStationList).isNull()
            assertThat(railway0.viaRailwayList).isNull()
            assertThat(railway0.ticketType).isNull()
            assertThat(railway0.paymentMethodList).isNull()

            assertThat(railway0.railwayFareId.id).isEqualTo("odpt.RailwayFare:TokyoMetro.Marunouchi.Tokyo.TokyoMetro.Tozai.Nakano")
            assertThat(railway0.operatorId.id).isEqualTo("odpt.Operator:TokyoMetro")
            assertThat(railway0.fromStationId.id).isEqualTo("odpt.Station:TokyoMetro.Marunouchi.Tokyo")
            assertThat(railway0.toStationId.id).isEqualTo("odpt.Station:TokyoMetro.Tozai.Nakano")
            assertThat(railway0.viaStationIdList).isNull()
            assertThat(railway0.viaRailwayIdList).isNull()
        }
    }
}
