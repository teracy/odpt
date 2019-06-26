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
 * v2版駅乗降人員数情報APIレスポンス
 */
@RunWith(Enclosed::class)
class OdptPassengerSurveyTest {
    /**
     * デシリアライズのテスト
     */
    class DeserializeTest {
        private val parameterizedType = Types.newParameterizedType(List::class.java, OdptPassengerSurvey::class.java)

        lateinit var jsonAdapter: JsonAdapter<List<OdptPassengerSurvey>>

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
                        "    \"@id\": \"urn:ucode:_00001C000000000000010000030C4504\",\n" +
                        "    \"@type\": \"odpt:PassengerSurvey\",\n" +
                        "    \"owl:sameAs\": \"odpt.PassengerSurvey:TokyoMetro.Ueno.2013\",\n" +
                        "    \"odpt:operator\": \"odpt.Operator:TokyoMetro\",\n" +
                        "    \"odpt:surveyYear\": 2013,\n" +
                        "    \"odpt:passengerJourneys\": 211539,\n" +
                        "    \"@context\": \"http://vocab.tokyometroapp.jp/context_odpt_PassengerSurvey.jsonld\"\n" +
                        "  }\n" +
                        "]"
            val passengerSurveyList = jsonAdapter.fromJson(json)
            assertThat(passengerSurveyList).isNotNull
            assertThat(passengerSurveyList!!.size).isEqualTo(1)

            val passengerSurvey0 = passengerSurveyList[0]
            assertThat(passengerSurvey0.id).isEqualTo("urn:ucode:_00001C000000000000010000030C4504")
            assertThat(passengerSurvey0.sameAs).isEqualTo("odpt.PassengerSurvey:TokyoMetro.Ueno.2013")
            assertThat(passengerSurvey0.operator).isEqualTo("odpt.Operator:TokyoMetro")
            assertThat(passengerSurvey0.surveyYear).isEqualTo(2013)
            assertThat(passengerSurvey0.passengerJourneys).isEqualTo(211539)

            assertThat(passengerSurvey0.passengerSurveyId.id).isEqualTo("odpt.PassengerSurvey:TokyoMetro.Ueno.2013")
            assertThat(passengerSurvey0.operatorId.id).isEqualTo("odpt.Operator:TokyoMetro")
        }
    }
}
