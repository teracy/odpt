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
 * v4版フライト出発情報APIレスポンスのテスト
 */
@RunWith(Enclosed::class)
class OdptFlightInformationDepartureTest {
    /**
     * デシリアライズのテスト
     */
    class DeserializeTest {
        private val parameterizedType =
            Types.newParameterizedType(List::class.java, OdptFlightInformationDeparture::class.java)

        lateinit var jsonAdapter: JsonAdapter<List<OdptFlightInformationDeparture>>

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
                        "  \"@id\" : \"urn:uuid:f476346b-ae6d-4102-99d8-2bf80d7c4dd8\",\n" +
                        "  \"@type\" : \"odpt:FlightInformationDeparture\",\n" +
                        "  \"dc:date\" : \"2017-12-06T23:20:02+09:00\",\n" +
                        "  \"owl:sameAs\" : \"odpt.FlightInformationDeparture:NAA.NRT.9W4807\",\n" +
                        "  \"odpt:operator\" : \"odpt.Operator:NAA\",\n" +
                        "  \"odpt:airline\" : \"odpt.Operator:JAI\",\n" +
                        "  \"odpt:flightNumber\" : [ \"9W4807\" ],\n" +
                        "  \"odpt:flightStatus\" : \"odpt.FlightStatus:Takeoff\",\n" +
                        "  \"odpt:scheduledDepartureTime\" : \"08:30\",\n" +
                        "  \"odpt:actualDepartureTime\" : \"08:32\",\n" +
                        "  \"odpt:departureAirport\" : \"odpt.Airport:NRT\",\n" +
                        "  \"odpt:departureAirportTerminal\" : \"odpt.AirportTerminal:NRT.Terminal2\",\n" +
                        "  \"odpt:departureGate\" : \"85\",\n" +
                        "  \"odpt:checkInCounter\" : [ \"B\", \"C\" ],\n" +
                        "  \"odpt:destinationAirport\" : \"odpt.Airport:HKG\",\n" +
                        "  \"odpt:aircraftType\" : \"788\"\n" +
                        "} ]"
            val flightInformationDepartureList = jsonAdapter.fromJson(json)
            assertThat(flightInformationDepartureList).isNotNull
            assertThat(flightInformationDepartureList!!.size).isEqualTo(1)

            val flightInformationDeparture0 = flightInformationDepartureList[0]
            assertThat(flightInformationDeparture0.id).isEqualTo("urn:uuid:f476346b-ae6d-4102-99d8-2bf80d7c4dd8")
            assertThat(flightInformationDeparture0.date).isEqualTo("2017-12-06T23:20:02+09:00")
            assertThat(flightInformationDeparture0.valid).isNull()
            assertThat(flightInformationDeparture0.sameAs).isEqualTo("odpt.FlightInformationDeparture:NAA.NRT.9W4807")
            assertThat(flightInformationDeparture0.operator).isEqualTo("odpt.Operator:NAA")
            assertThat(flightInformationDeparture0.airline).isEqualTo("odpt.Operator:JAI")

            assertThat(flightInformationDeparture0.flightNumberList.size).isEqualTo(1)
            assertThat(flightInformationDeparture0.flightNumberList[0]).isEqualTo("9W4807")

            assertThat(flightInformationDeparture0.flightStatus).isEqualTo("odpt.FlightStatus:Takeoff")
            assertThat(flightInformationDeparture0.summaryMap).isNull()
            assertThat(flightInformationDeparture0.textMap).isNull()
            assertThat(flightInformationDeparture0.scheduledDepartureTime).isEqualTo("08:30")
            assertThat(flightInformationDeparture0.estimatedDepartureTime).isNull()
            assertThat(flightInformationDeparture0.actualDepartureTime).isEqualTo("08:32")
            assertThat(flightInformationDeparture0.departureAirport).isEqualTo("odpt.Airport:NRT")
            assertThat(flightInformationDeparture0.departureAirportTerminal).isEqualTo("odpt.AirportTerminal:NRT.Terminal2")
            assertThat(flightInformationDeparture0.departureGate).isEqualTo("85")

            assertThat(flightInformationDeparture0.checkInCounterList).isNotNull
            assertThat(flightInformationDeparture0.checkInCounterList!!.size).isEqualTo(2)
            assertThat(flightInformationDeparture0.checkInCounterList!![0]).isEqualTo("B")
            assertThat(flightInformationDeparture0.checkInCounterList!![1]).isEqualTo("C")

            assertThat(flightInformationDeparture0.destinationAirport).isEqualTo("odpt.Airport:HKG")
            assertThat(flightInformationDeparture0.viaAirportList).isNull()
            assertThat(flightInformationDeparture0.aircraftType).isEqualTo("788")

            assertThat(flightInformationDeparture0.flightInformationDepartureId.id).isEqualTo("odpt.FlightInformationDeparture:NAA.NRT.9W4807")
            assertThat(flightInformationDeparture0.operatorId.id).isEqualTo("odpt.Operator:NAA")
            assertThat(flightInformationDeparture0.airlineOperatorId).isNotNull
            assertThat(flightInformationDeparture0.airlineOperatorId!!.id).isEqualTo("odpt.Operator:JAI")
            assertThat(flightInformationDeparture0.flightStatusId).isNotNull
            assertThat(flightInformationDeparture0.flightStatusId!!.id).isEqualTo("odpt.FlightStatus:Takeoff")
            assertThat(flightInformationDeparture0.departureAirportId.id).isEqualTo("odpt.Airport:NRT")
            assertThat(flightInformationDeparture0.departureAirportTerminalId).isNotNull
            assertThat(flightInformationDeparture0.departureAirportTerminalId!!.id).isEqualTo("odpt.AirportTerminal:NRT.Terminal2")
            assertThat(flightInformationDeparture0.destinationAirportId).isNotNull
            assertThat(flightInformationDeparture0.destinationAirportId!!.id).isEqualTo("odpt.Airport:HKG")
            assertThat(flightInformationDeparture0.viaAirportIdList).isNull()
        }
    }
}
