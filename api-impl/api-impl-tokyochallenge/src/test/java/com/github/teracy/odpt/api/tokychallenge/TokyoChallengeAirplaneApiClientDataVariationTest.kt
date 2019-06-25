package com.github.teracy.odpt.api.tokychallenge

import com.github.teracy.odpt.api.tokychallenge.support.*
import com.github.teracy.odpt.core.v4.airplane.request.*
import com.github.teracy.odpt.core.v4.common.request.OperatorArgument
import com.github.teracy.odpt.model.AirportId
import com.github.teracy.odpt.testutil.ApiClientTest
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.assertj.core.api.Assertions.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.experimental.runners.Enclosed
import org.junit.runner.RunWith
import java.util.concurrent.TimeUnit

/**
 * 東京公共交通オープンデータチャレンジ航空機API向けクライアント実装のテスト（事業者毎のデータ実装差異を洗い出すためのテスト）
 * 対象事業者全てで実行して落ちるところがないことを確認する
 */
@RunWith(Enclosed::class)
class TokyoChallengeAirplaneApiClientDataVariationTest {
    /**
     * 航空機APIテスト基底クラス（事業者IDによる検索）
     */
    abstract class OperatorAirplaneApiClientTest(tokyoChallengeOperator: TokyoChallengeOperator) : ApiClientTest {
        private val operatorIdList = listOf(tokyoChallengeOperator.operatorId)
        private lateinit var consumerKey: String
        private lateinit var okHttpClient: OkHttpClient
        private lateinit var commonApiClient: TokyoChallengeCommonApiClient
        private lateinit var airplaneApiClient: TokyoChallengeAirplaneApiClient

        @Before
        fun setUp() {
            consumerKey = readConsumerKeyFile()

            val interceptor = HttpLoggingInterceptor()
                .setLevel(HttpLoggingInterceptor.Level.BASIC)
            okHttpClient = OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .addInterceptor(interceptor)
                .build()

            commonApiClient = TokyoChallengeCommonApiClient(okHttpClient, consumerKey)
            airplaneApiClient = TokyoChallengeAirplaneApiClient(okHttpClient, consumerKey)
        }

        @After
        fun tearDown() {
            // 一気に流したら429が返ってきてしまったので、ひとまず1テストあたり2000msecのインターバルをかませる
            Thread.sleep(2000)
        }

        /**
         * getOperatorのテスト
         */
        @Test
        fun getOperator() {
            runBlocking {
                val operatorList = commonApiClient.getOperator(OperatorArgument.ByOperatorId(operatorIdList))
                // 事業者情報に関してはAPI対応事業者なら必ず取得できるため、空でないことを確認する
                assertThat(operatorList).isNotEmpty
            }
        }

        /**
         * getFlightInformationArrivalのテスト
         */
        @Test
        fun getFlightInformationArrival() {
            runBlocking {
                // 対応していない事業者もあるため、実行できることだけを確認する（対応していない場合、実行はできるが空が返ってくる）
                airplaneApiClient.getFlightInformationArrival(
                    FlightInformationArrivalArgument.ByOperatorId(
                        operatorIdList
                    )
                )
            }
        }

        /**
         * getFlightInformationDepartureのテスト
         */
        @Test
        fun getFlightInformationDeparture() {
            runBlocking {
                // 対応していない事業者もあるため、実行できることだけを確認する（対応していない場合、実行はできるが空が返ってくる）
                airplaneApiClient.getFlightInformationDeparture(
                    FlightInformationDepartureArgument.ByOperatorId(
                        operatorIdList
                    )
                )
            }
        }

        /**
         * getFlightScheduleのテスト
         */
        @Test
        fun getFlightSchedule() {
            runBlocking {
                // 対応していない事業者もあるため、実行できることだけを確認する（対応していない場合、実行はできるが空が返ってくる）
                airplaneApiClient.getFlightSchedule(operatorList = operatorIdList.map { it.id })
            }
        }
    }

    /**
     * 全日空のテスト
     */
    class AllNipponAirwaysTest : OperatorAirplaneApiClientTest(AllNipponAirways)

    /**
     * 日本航空のテスト
     */
    class JapanAirlinesTest : OperatorAirplaneApiClientTest(JapanAirlines)

    /**
     * 日本空港ビルデングのテスト
     */
    class JapanAirportTerminalTest : OperatorAirplaneApiClientTest(JapanAirportTerminal)

    /**
     * 成田国際空港のテスト
     */
    class NaritaInternationalAirportTest : OperatorAirplaneApiClientTest(NaritaInternationalAirport)

    /**
     * 東京国際空港ターミナルのテスト
     */
    class TokyoInternationalAirTerminalTest : OperatorAirplaneApiClientTest(TokyoInternationalAirTerminal)

    /**
     * 航空機APIテスト基底クラス（空港IDによる検索）
     */
    abstract class AirportAirplaneApiClientTest(airportId: AirportId) : ApiClientTest {
        private val airportIdList = listOf(airportId)
        private lateinit var consumerKey: String
        private lateinit var okHttpClient: OkHttpClient
        private lateinit var commonApiClient: TokyoChallengeCommonApiClient
        private lateinit var airplaneApiClient: TokyoChallengeAirplaneApiClient

        @Before
        fun setUp() {
            consumerKey = readConsumerKeyFile()

            val interceptor = HttpLoggingInterceptor()
                .setLevel(HttpLoggingInterceptor.Level.BASIC)
            okHttpClient = OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .addInterceptor(interceptor)
                .build()

            commonApiClient = TokyoChallengeCommonApiClient(okHttpClient, consumerKey)
            airplaneApiClient = TokyoChallengeAirplaneApiClient(okHttpClient, consumerKey)
        }

        @After
        fun tearDown() {
            // 一気に流したら429が返ってきてしまったので、ひとまず1テストあたり2000msecのインターバルをかませる
            Thread.sleep(2000)
        }

        /**
         * getAirportのテスト
         */
        @Test
        fun getAirport() {
            runBlocking {
                val airportList = airplaneApiClient.getAirport(AirportArgument.ByAirportId(airportIdList))
                assertThat(airportList).isNotEmpty
            }
        }

        /**
         * getAirportTerminalのテスト
         */
        @Test
        fun getAirportTerminal() {
            runBlocking {
                val airportTerminalList =
                    airplaneApiClient.getAirportTerminal(AirportTerminalArgument.ByAirportId(airportIdList))
                assertThat(airportTerminalList).isNotEmpty
            }
        }

        /**
         * getFlightInformationArrivalのテスト
         */
        @Test
        fun getFlightInformationArrival() {
            runBlocking {
                val flightInformationArrivalList = airplaneApiClient.getFlightInformationArrival(
                    FlightInformationArrivalArgument.ByArrivalAirportId(airportIdList)
                )
                assertThat(flightInformationArrivalList).isNotEmpty
            }
        }

        /**
         * getFlightInformationDepartureのテスト
         */
        @Test
        fun getFlightInformationDeparture() {
            runBlocking {
                val flightInformationDepartureList = airplaneApiClient.getFlightInformationDeparture(
                    FlightInformationDepartureArgument.ByDepartureAirportId(airportIdList)
                )
                assertThat(flightInformationDepartureList).isNotEmpty
            }
        }

        /**
         * getFlightScheduleのテスト
         */
        @Test
        fun getBusRoutePatternFare() {
            runBlocking {
                val flightScheduleList =
                    airplaneApiClient.getFlightSchedule(FlightScheduleArgument.ByDestinationAirportId(airportIdList))
                assertThat(flightScheduleList).isNotEmpty
            }
        }
    }

    /**
     * 成田空港のテスト
     */
    class NrtTest : AirportAirplaneApiClientTest(AirportId("odpt.Airport:NRT"))

    /**
     * 羽田空港のテスト
     */
    class HndTest : AirportAirplaneApiClientTest(AirportId("odpt.Airport:HND"))
}
