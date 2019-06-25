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
 * v4版空港ターミナル情報APIレスポンスのテスト
 */
@RunWith(Enclosed::class)
class OdptAirportTerminalTest {
    /**
     * デシリアライズのテスト
     */
    class DeserializeTest {
        private val parameterizedType = Types.newParameterizedType(List::class.java, OdptAirportTerminal::class.java)

        lateinit var jsonAdapter: JsonAdapter<List<OdptAirportTerminal>>

        @Before
        fun setUp() {
            jsonAdapter = Moshi.Builder().build().adapter(parameterizedType)
        }

        /**
         * 東京公共交通オープンデータチャレンジAPIサンプルデータより
         * NOTE: サンプルデータの[OdptAirportTerminal.airport]のJSON変数名が誤っていたので是正してある
         */
        @Test
        fun tokyoChallengeSampleData() {
            // テストデータ
            val json =
                "[ {\n" +
                        "  \"@context\" : \"http://vocab.odpt.org/context_odpt.jsonld\",\n" +
                        "  \"@id\" : \"urn:ucode:_00001C000000000000010000030FD7E5\",\n" +
                        "  \"@type\" : \"odpt:AirportTerminal\",\n" +
                        "  \"dc:date\" : \"2017-01-13T15:10:00+09:00\",\n" +
                        "  \"owl:sameAs\" : \"odpt.AirportTerminal:HND.DomesticTerminal1\",\n" +
                        "  \"dc:title\" : \"国内線第１ターミナル\",\n" +
                        "  \"odpt:airportTerminalTitle\" : {\n" +
                        "    \"ja\" : \"国内線第１ターミナル\",\n" +
                        "    \"en\" : \"Domestic Terminal 1\"\n" +
                        "  },\n" +
                        "  \"odpt:airport\" : \"odpt.Airport:HND\"\n" +
                        "} ]"
            val airportTerminalList = jsonAdapter.fromJson(json)
            assertThat(airportTerminalList).isNotNull
            assertThat(airportTerminalList!!.size).isEqualTo(1)

            val airportTerminal0 = airportTerminalList[0]
            assertThat(airportTerminal0.id).isEqualTo("urn:ucode:_00001C000000000000010000030FD7E5")
            assertThat(airportTerminal0.date).isEqualTo("2017-01-13T15:10:00+09:00")
            assertThat(airportTerminal0.sameAs).isEqualTo("odpt.AirportTerminal:HND.DomesticTerminal1")
            assertThat(airportTerminal0.title).isEqualTo("国内線第１ターミナル")

            assertThat(airportTerminal0.titleMap).isNotNull
            assertThat(airportTerminal0.titleMap!!.size).isEqualTo(2)
            assertThat(airportTerminal0.titleMap!!["ja"]).isEqualTo("国内線第１ターミナル")
            assertThat(airportTerminal0.titleMap!!["en"]).isEqualTo("Domestic Terminal 1")

            assertThat(airportTerminal0.airport).isEqualTo("odpt.Airport:HND")
            assertThat(airportTerminal0.longitude).isNull()
            assertThat(airportTerminal0.latitude).isNull()
            assertThat(airportTerminal0.region).isNull()
            assertThat(airportTerminal0.airportTerminalId.id).isEqualTo("odpt.AirportTerminal:HND.DomesticTerminal1")
            assertThat(airportTerminal0.airportId.id).isEqualTo("odpt.Airport:HND")
        }
    }
}
