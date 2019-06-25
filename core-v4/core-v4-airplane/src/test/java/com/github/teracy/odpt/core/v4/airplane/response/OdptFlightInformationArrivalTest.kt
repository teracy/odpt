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
 * v4版フライト到着情報APIレスポンスのテスト
 */
@RunWith(Enclosed::class)
class OdptFlightInformationArrivalTest {
    /**
     * デシリアライズのテスト
     */
    class DeserializeTest {
        private val parameterizedType =
            Types.newParameterizedType(List::class.java, OdptFlightInformationArrival::class.java)

        lateinit var jsonAdapter: JsonAdapter<List<OdptFlightInformationArrival>>

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
                        "  \"@id\" : \"urn:uuid:ff91feee-2d2c-4365-902e-26375fdf9d8b\",\n" +
                        "  \"@type\" : \"odpt:FlightInformationArrival\",\n" +
                        "  \"dc:date\" : \"2017-12-06T23:15:01+09:00\",\n" +
                        "  \"owl:sameAs\" : \"odpt.FlightInformationArrival:NAA.NRT.NH832\",\n" +
                        "  \"odpt:operator\" : \"odpt.Operator:NAA\",\n" +
                        "  \"odpt:airline\" : \"odpt.Operator:ANA\",\n" +
                        "  \"odpt:flightNumber\" : [ \"NH832\" ],\n" +
                        "  \"odpt:flightStatus\" : \"odpt.FlightStatus:Arrived\",\n" +
                        "  \"odpt:scheduledArrivalTime\" : \"06:45\",\n" +
                        "  \"odpt:actualArrivalTime\" : \"06:48\",\n" +
                        "  \"odpt:arrivalAirport\" : \"odpt.Airport:NRT\",\n" +
                        "  \"odpt:arrivalAirportTerminal\" : \"odpt.AirportTerminal:NRT.Terminal1\",\n" +
                        "  \"odpt:arrivalGate\" : \"27\",\n" +
                        "  \"odpt:baggageClaim\" : \"27\",\n" +
                        "  \"odpt:originAirport\" : \"odpt.Airport:SGN\",\n" +
                        "  \"odpt:aircraftType\" : \"788\"\n" +
                        "} ]"
            val flightInformationArrivalList = jsonAdapter.fromJson(json)
            assertThat(flightInformationArrivalList).isNotNull
            assertThat(flightInformationArrivalList!!.size).isEqualTo(1)

            val flightInformationArrival0 = flightInformationArrivalList[0]
            assertThat(flightInformationArrival0.id).isEqualTo("urn:uuid:ff91feee-2d2c-4365-902e-26375fdf9d8b")
            assertThat(flightInformationArrival0.date).isEqualTo("2017-12-06T23:15:01+09:00")
            assertThat(flightInformationArrival0.valid).isNull()
            assertThat(flightInformationArrival0.sameAs).isEqualTo("odpt.FlightInformationArrival:NAA.NRT.NH832")
            assertThat(flightInformationArrival0.operator).isEqualTo("odpt.Operator:NAA")
            assertThat(flightInformationArrival0.airline).isEqualTo("odpt.Operator:ANA")

            assertThat(flightInformationArrival0.flightNumberList.size).isEqualTo(1)
            assertThat(flightInformationArrival0.flightNumberList[0]).isEqualTo("NH832")

            assertThat(flightInformationArrival0.flightStatus).isEqualTo("odpt.FlightStatus:Arrived")
            assertThat(flightInformationArrival0.summaryMap).isNull()
            assertThat(flightInformationArrival0.textMap).isNull()
            assertThat(flightInformationArrival0.scheduledArrivalTime).isEqualTo("06:45")
            assertThat(flightInformationArrival0.estimatedArrivalTime).isNull()
            assertThat(flightInformationArrival0.actualArrivalTime).isEqualTo("06:48")
            assertThat(flightInformationArrival0.arrivalAirport).isEqualTo("odpt.Airport:NRT")
            assertThat(flightInformationArrival0.arrivalAirportTerminal).isEqualTo("odpt.AirportTerminal:NRT.Terminal1")
            assertThat(flightInformationArrival0.arrivalGate).isEqualTo("27")
            assertThat(flightInformationArrival0.baggageClaim).isEqualTo("27")
            assertThat(flightInformationArrival0.originAirport).isEqualTo("odpt.Airport:SGN")
            assertThat(flightInformationArrival0.viaAirportList).isNull()
            assertThat(flightInformationArrival0.aircraftType).isEqualTo("788")

            assertThat(flightInformationArrival0.flightInformationArrivalId.id).isEqualTo("odpt.FlightInformationArrival:NAA.NRT.NH832")
            assertThat(flightInformationArrival0.operatorId.id).isEqualTo("odpt.Operator:NAA")
            assertThat(flightInformationArrival0.airlineOperatorId).isNotNull
            assertThat(flightInformationArrival0.airlineOperatorId!!.id).isEqualTo("odpt.Operator:ANA")
            assertThat(flightInformationArrival0.flightStatusId).isNotNull
            assertThat(flightInformationArrival0.flightStatusId!!.id).isEqualTo("odpt.FlightStatus:Arrived")
            assertThat(flightInformationArrival0.arrivalAirportId.id).isEqualTo("odpt.Airport:NRT")
            assertThat(flightInformationArrival0.arrivalAirportTerminalId).isNotNull
            assertThat(flightInformationArrival0.arrivalAirportTerminalId!!.id).isEqualTo("odpt.AirportTerminal:NRT.Terminal1")
            assertThat(flightInformationArrival0.originAirportId).isNotNull
            assertThat(flightInformationArrival0.originAirportId!!.id).isEqualTo("odpt.Airport:SGN")
            assertThat(flightInformationArrival0.viaAirportIdList).isNull()
        }
    }
}
