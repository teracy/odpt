package com.github.teracy.odpt.core.v4.airplane.response

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.experimental.runners.Enclosed
import org.junit.runner.RunWith


/**
 * v4版空港情報APIレスポンスのテスト
 */
@RunWith(Enclosed::class)
class OdptAirportTest {
    /**
     * デシリアライズのテスト
     */
    class DeserializeTest {
        private val parameterizedType = Types.newParameterizedType(List::class.java, OdptAirport::class.java)

        lateinit var jsonAdapter: JsonAdapter<List<OdptAirport>>

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
                        "  \"@type\" : \"odpt:Airport\",\n" +
                        "  \"dc:date\" : \"2017-01-13T15:10:00+09:00\",\n" +
                        "  \"owl:sameAs\" : \"odpt.Airport:HND\",\n" +
                        "  \"dc:title\" : \"東京(羽田)\",\n" +
                        "  \"odpt:airportTitle\" : {\n" +
                        "    \"ja\" : \"東京(羽田)\",\n" +
                        "    \"en\" : \"Tokyo (Haneda)\"\n" +
                        "  },\n" +
                        "  \"odpt:airportTerminal\" : [ \"odpt.AirportTerminal:HND.DomesticTerminal1\", \"odpt.AirportTerminal:HND.DomesticTerminal2\", \"odpt.AirportTerminal:HND.InternationalTerminal\" ]\n" +
                        "} ]"
            val airportList = jsonAdapter.fromJson(json)
            assertThat(airportList).isNotNull
            assertThat(airportList!!.size).isEqualTo(1)

            val airport0 = airportList[0]
            assertThat(airport0.id).isEqualTo("urn:ucode:_00001C000000000000010000030FD7E5")
            assertThat(airport0.date).isEqualTo("2017-01-13T15:10:00+09:00")
            assertThat(airport0.sameAs).isEqualTo("odpt.Airport:HND")
            assertThat(airport0.title).isEqualTo("東京(羽田)")

            assertThat(airport0.titleMap).isNotNull
            assertThat(airport0.titleMap!!.size).isEqualTo(2)
            assertThat(airport0.titleMap!!["ja"]).isEqualTo("東京(羽田)")
            assertThat(airport0.titleMap!!["en"]).isEqualTo("Tokyo (Haneda)")

            assertThat(airport0.airportTerminalList).isNotNull
            assertThat(airport0.airportTerminalList!!.size).isEqualTo(3)
            assertThat(airport0.airportTerminalList!![0]).isEqualTo("odpt.AirportTerminal:HND.DomesticTerminal1")
            assertThat(airport0.airportTerminalList!![1]).isEqualTo("odpt.AirportTerminal:HND.DomesticTerminal2")
            assertThat(airport0.airportTerminalList!![2]).isEqualTo("odpt.AirportTerminal:HND.InternationalTerminal")

            assertThat(airport0.longitude).isNull()
            assertThat(airport0.latitude).isNull()
            assertThat(airport0.region).isNull()
            assertThat(airport0.airportId.id).isEqualTo("odpt.Airport:HND")

            assertThat(airport0.airportTerminalIdList).isNotNull
            assertThat(airport0.airportTerminalIdList!!.size).isEqualTo(3)
            assertThat(airport0.airportTerminalIdList!![0].id).isEqualTo("odpt.AirportTerminal:HND.DomesticTerminal1")
            assertThat(airport0.airportTerminalIdList!![1].id).isEqualTo("odpt.AirportTerminal:HND.DomesticTerminal2")
            assertThat(airport0.airportTerminalIdList!![2].id).isEqualTo("odpt.AirportTerminal:HND.InternationalTerminal")
        }
    }
}
