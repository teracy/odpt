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
 * v4版駅乗降人員数情報APIレスポンスのテスト
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
         * 東京公共交通オープンデータチャレンジAPIサンプルデータより
         */
        @Test
        fun tokyoChallengeSampleData() {
            // テストデータ
            val json =
                "[ {\n" +
                        "  \"@context\" : \"http://vocab.odpt.org/context_odpt.jsonld\",\n" +
                        "  \"@id\" : \"urn:ucode:_00001C000000000000010000030FD7E5\",\n" +
                        "  \"@type\" : \"odpt:PassengerSurvey\",\n" +
                        "  \"dc:date\" : \"2017-01-13T06:10:00+0000\",\n" +
                        "  \"owl:sameAs\" : \"odpt.PassengerSurvey:JR-East.Tokyo\",\n" +
                        "  \"odpt:operator\" : \"odpt.Operator:JR-East\",\n" +
                        "  \"odpt:railway\" : [ \"odpt.Railway:JR-East.Yamanote\", \"odpt.Railway:JR-East.ChuoRapid\" ],\n" +
                        "  \"odpt:station\" : [ \"odpt.Station:JR-East.Yamanote.Tokyo\", \"odpt.Station:JR-East.ChuoRapid.Tokyo\" ],\n" +
                        "  \"odpt:includeAlighting\" : false,\n" +
                        "  \"odpt:passengerSurveyObject\" : [ {\n" +
                        "    \"odpt:surveyYear\" : 2016,\n" +
                        "    \"odpt:passengerJourneys\" : 12340\n" +
                        "  }, {\n" +
                        "    \"odpt:surveyYear\" : 2017,\n" +
                        "    \"odpt:passengerJourneys\" : 12345\n" +
                        "  } ]\n" +
                        "} ]"
            val passengerSurveyList = jsonAdapter.fromJson(json)
            assertThat(passengerSurveyList).isNotNull
            assertThat(passengerSurveyList!!.size).isEqualTo(1)

            val passengerSurvey0 = passengerSurveyList[0]
            assertThat(passengerSurvey0.id).isEqualTo("urn:ucode:_00001C000000000000010000030FD7E5")
            assertThat(passengerSurvey0.sameAs).isEqualTo("odpt.PassengerSurvey:JR-East.Tokyo")
            assertThat(passengerSurvey0.date).isEqualTo("2017-01-13T06:10:00+0000")
            assertThat(passengerSurvey0.operator).isEqualTo("odpt.Operator:JR-East")
            assertThat(passengerSurvey0.stationList.size).isEqualTo(2)
            assertThat(passengerSurvey0.stationList[0]).isEqualTo("odpt.Station:JR-East.Yamanote.Tokyo")
            assertThat(passengerSurvey0.railwayList.size).isEqualTo(2)
            assertThat(passengerSurvey0.railwayList[0]).isEqualTo("odpt.Railway:JR-East.Yamanote")
            assertThat(passengerSurvey0.includeAlighting).isEqualTo(false)
            assertThat(passengerSurvey0.passengerSurveyObjectList.size).isEqualTo(2)
            val passengerSurveyObject0 = passengerSurvey0.passengerSurveyObjectList[0]
            assertThat(passengerSurveyObject0.surveyYear).isEqualTo(2016)
            assertThat(passengerSurveyObject0.passengerJourneys).isEqualTo(12340)

            assertThat(passengerSurvey0.passengerSurveyId.id).isEqualTo("odpt.PassengerSurvey:JR-East.Tokyo")
            assertThat(passengerSurvey0.operatorId.id).isEqualTo("odpt.Operator:JR-East")
            assertThat(passengerSurvey0.stationIdList.size).isEqualTo(2)
            assertThat(passengerSurvey0.stationIdList[0].id).isEqualTo("odpt.Station:JR-East.Yamanote.Tokyo")
            assertThat(passengerSurvey0.railwayIdList.size).isEqualTo(2)
            assertThat(passengerSurvey0.railwayIdList[0].id).isEqualTo("odpt.Railway:JR-East.Yamanote")
        }
    }
}
