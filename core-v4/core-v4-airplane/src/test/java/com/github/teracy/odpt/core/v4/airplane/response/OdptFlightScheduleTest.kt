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
 * v4版フライト時刻表情報APIレスポンスのテスト
 */
@RunWith(Enclosed::class)
class OdptFlightScheduleTest {
    /**
     * デシリアライズのテスト
     */
    class DeserializeTest {
        private val parameterizedType = Types.newParameterizedType(List::class.java, OdptFlightSchedule::class.java)

        lateinit var jsonAdapter: JsonAdapter<List<OdptFlightSchedule>>

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
                        "  \"@type\" : \"odpt:FlightSchedule\",\n" +
                        "  \"dc:date\" : \"2017-10-31T23:06:38+09:00\",\n" +
                        "  \"owl:sameAs\" : \"odpt.FlightSchedule:HND-TIAT.HND.IWJ.Wednesday\",\n" +
                        "  \"odpt:operator\" : \"odpt.Operator:HND-TIAT\",\n" +
                        "  \"odpt:calendar\" : \"odpt.Calendar:Wednesday\",\n" +
                        "  \"odpt:originAirport\" : \"odpt.Airport:HND\",\n" +
                        "  \"odpt:destinationAirport\" : \"odpt.Airport:IWJ\",\n" +
                        "  \"odpt:flightScheduleObject\" : [ {\n" +
                        "    \"odpt:airline\" : \"odpt.Operator:ANA\",\n" +
                        "    \"odpt:flightNumber\" : [ \"NH575\" ],\n" +
                        "    \"odpt:originTime\" : \"10:35\",\n" +
                        "    \"odpt:destinationTime\" : \"12:15\",\n" +
                        "    \"odpt:isValidFrom\" : \"2017-10-01T00:00:00+09:00\",\n" +
                        "    \"odpt:isValidTo\" : \"2017-10-31T23:59:59+09:00\"\n" +
                        "  } ]\n" +
                        "} ]"
            val flightScheduleList = jsonAdapter.fromJson(json)
            assertThat(flightScheduleList).isNotNull
            assertThat(flightScheduleList!!.size).isEqualTo(1)

            val flightSchedule0 = flightScheduleList[0]
            assertThat(flightSchedule0.id).isEqualTo("urn:ucode:_00001C000000000000010000030FD7E5")
            assertThat(flightSchedule0.date).isEqualTo("2017-10-31T23:06:38+09:00")
            assertThat(flightSchedule0.sameAs).isEqualTo("odpt.FlightSchedule:HND-TIAT.HND.IWJ.Wednesday")
            assertThat(flightSchedule0.operator).isEqualTo("odpt.Operator:HND-TIAT")
            assertThat(flightSchedule0.calendar).isEqualTo("odpt.Calendar:Wednesday")
            assertThat(flightSchedule0.originAirport).isEqualTo("odpt.Airport:HND")
            assertThat(flightSchedule0.destinationAirport).isEqualTo("odpt.Airport:IWJ")

            assertThat(flightSchedule0.flightScheduleObjectList.size).isEqualTo(1)
            val flightScheduleObject0 = flightSchedule0.flightScheduleObjectList[0]
            assertThat(flightScheduleObject0.airline).isEqualTo("odpt.Operator:ANA")

            assertThat(flightScheduleObject0.flightNumberList.size).isEqualTo(1)
            assertThat(flightScheduleObject0.flightNumberList[0]).isEqualTo("NH575")

            assertThat(flightScheduleObject0.originTime).isEqualTo("10:35")
            assertThat(flightScheduleObject0.originDayDifference).isNull()
            assertThat(flightScheduleObject0.destinationTime).isEqualTo("12:15")
            assertThat(flightScheduleObject0.destinationDayDifference).isNull()
            assertThat(flightScheduleObject0.viaAirportList).isNull()
            assertThat(flightScheduleObject0.aircraftType).isNull()
            assertThat(flightScheduleObject0.validFrom).isEqualTo("2017-10-01T00:00:00+09:00")
            assertThat(flightScheduleObject0.validTo).isEqualTo("2017-10-31T23:59:59+09:00")
            assertThat(flightScheduleObject0.note).isNull()
            assertThat(flightScheduleObject0.airlineOperatorId.id).isEqualTo("odpt.Operator:ANA")
            assertThat(flightScheduleObject0.viaAirportIdList).isNull()

            assertThat(flightSchedule0.flightScheduleId.id).isEqualTo("odpt.FlightSchedule:HND-TIAT.HND.IWJ.Wednesday")
            assertThat(flightSchedule0.operatorId.id).isEqualTo("odpt.Operator:HND-TIAT")

            assertThat(flightSchedule0.calendarId).isNotNull
            assertThat(flightSchedule0.calendarId!!.id).isEqualTo("odpt.Calendar:Wednesday")

            assertThat(flightSchedule0.originAirportId.id).isEqualTo("odpt.Airport:HND")
            assertThat(flightSchedule0.destinationAirportId.id).isEqualTo("odpt.Airport:IWJ")
        }
    }
}
