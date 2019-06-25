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
 * v4版フライト状況情報APIレスポンスのテスト
 */
@RunWith(Enclosed::class)
class OdptFlightStatusTest {
    /**
     * デシリアライズのテスト
     */
    class DeserializeTest {
        private val parameterizedType = Types.newParameterizedType(List::class.java, OdptFlightStatus::class.java)

        lateinit var jsonAdapter: JsonAdapter<List<OdptFlightStatus>>

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
                        "  \"@type\" : \"odpt:FlightStatus\",\n" +
                        "  \"dc:date\" : \"2017-01-13T15:10:00+09:00\",\n" +
                        "  \"owl:sameAs\" : \"odpt.FlightStatus:CheckIn\",\n" +
                        "  \"dc:title\" : \"搭乗手続中\",\n" +
                        "  \"odpt:flightStatusTitle\" : {\n" +
                        "    \"ja\" : \"搭乗手続中\",\n" +
                        "    \"en\" : \"Check in\"\n" +
                        "  }\n" +
                        "} ]"
            val flightStatusList = jsonAdapter.fromJson(json)
            assertThat(flightStatusList).isNotNull
            assertThat(flightStatusList!!.size).isEqualTo(1)

            val flightStatus0 = flightStatusList[0]
            assertThat(flightStatus0.id).isEqualTo("urn:ucode:_00001C000000000000010000030FD7E5")
            assertThat(flightStatus0.date).isEqualTo("2017-01-13T15:10:00+09:00")
            assertThat(flightStatus0.sameAs).isEqualTo("odpt.FlightStatus:CheckIn")
            assertThat(flightStatus0.title).isEqualTo("搭乗手続中")

            assertThat(flightStatus0.titleMap).isNotNull
            assertThat(flightStatus0.titleMap!!.size).isEqualTo(2)
            assertThat(flightStatus0.titleMap!!["ja"]).isEqualTo("搭乗手続中")
            assertThat(flightStatus0.titleMap!!["en"]).isEqualTo("Check in")

            assertThat(flightStatus0.flightStatusId.id).isEqualTo("odpt.FlightStatus:CheckIn")
        }
    }
}
