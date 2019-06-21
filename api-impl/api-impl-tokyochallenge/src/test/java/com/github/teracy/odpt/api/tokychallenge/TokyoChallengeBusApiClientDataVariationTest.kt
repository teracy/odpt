package com.github.teracy.odpt.api.tokychallenge

import com.github.teracy.odpt.api.tokychallenge.support.*
import com.github.teracy.odpt.core.v4.bus.request.*
import com.github.teracy.odpt.core.v4.common.request.OperatorArgument
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
 * 東京公共交通オープンデータチャレンジバスAPI向けクライアント実装のテスト（事業者毎のデータ実装差異を洗い出すためのテスト）
 * 対象事業者全てで実行して落ちるところがないことを確認する
 */
@RunWith(Enclosed::class)
class TokyoChallengeBusApiClientDataVariationTest {
    /**
     * バスAPIテスト基底クラス
     */
    abstract class BusApiClientTest(tokyoChallengeOperator: TokyoChallengeOperator) : ApiClientTest {
        private val operatorIdList = listOf(tokyoChallengeOperator.operatorId)
        private lateinit var consumerKey: String
        private lateinit var okHttpClient: OkHttpClient
        private lateinit var commonApiClient: TokyoChallengeCommonApiClient
        private lateinit var busApiClient: TokyoChallengeBusApiClient

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
            busApiClient = TokyoChallengeBusApiClient(okHttpClient, consumerKey)
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
         * getBusのテスト
         */
        @Test
        fun getBus() {
            runBlocking {
                // 対応していない事業者もあるため、実行できることだけを確認する（対応していない場合、実行はできるが空が返ってくる）
                busApiClient.getBus(BusArgument.ByOperatorId(operatorIdList))
            }
        }

        /**
         * getBusTimetableのテスト
         */
        @Test
        fun getBusTimetable() {
            runBlocking {
                // 対応していない事業者もあるため、実行できることだけを確認する（対応していない場合、実行はできるが空が返ってくる）
                busApiClient.getBusTimetable(BusTimetableArgument.ByOperatorId(operatorIdList))
            }
        }

        /**
         * getBusRoutePatternのテスト
         */
        @Test
        fun getBusRoutePattern() {
            runBlocking {
                // 対応していない事業者もあるため、実行できることだけを確認する（対応していない場合、実行はできるが空が返ってくる）
                busApiClient.getBusRoutePattern(BusRoutePatternArgument.ByOperatorId(operatorIdList))
            }
        }

        /**
         * getBusStopPoleのテスト
         */
        @Test
        fun getBusStopPole() {
            runBlocking {
                // 対応していない事業者もあるため、実行できることだけを確認する（対応していない場合、実行はできるが空が返ってくる）
                busApiClient.getBusStopPole(BusStopPoleArgument.ByOperatorId(operatorIdList))
            }
        }

        /**
         * getBusRoutePatternFareのテスト
         */
        @Test
        fun getBusRoutePatternFare() {
            runBlocking {
                // 対応していない事業者もあるため、実行できることだけを確認する（対応していない場合、実行はできるが空が返ってくる）
                busApiClient.getBusRoutePatternFare(BusRoutePatternFareArgument.ByOperatorId(operatorIdList))
            }
        }

        /**
         * getBusStopPoleTimetableのテスト
         */
        @Test
        fun getBusStopPoleTimetable() {
            runBlocking {
                // 対応していない事業者もあるため、実行できることだけを確認する（対応していない場合、実行はできるが空が返ってくる）
                busApiClient.getBusStopPoleTimetable(BusStopPoleTimetableArgument.ByOperatorId(operatorIdList))
            }
        }
    }

    /**
     * JRバス関東のテスト
     */
    class JrBusKantoTest : BusApiClientTest(JrBusKanto)

    /**
     * 関東バスのテスト
     */
    class KantoBusTest : BusApiClientTest(KantoBus)

    /**
     * 京王バスのテスト
     */
    class KeioBusTest : BusApiClientTest(KeioBus)

    /**
     * 国際興業のテスト
     */
    class KokusaiKogyoTest : BusApiClientTest(KokusaiKogyo)

    /**
     * 西東京バスのテスト
     */
    class NishiTokyoBusTest : BusApiClientTest(NishiTokyoBus)

    /**
     * 小田急バスのテスト
     */
    class OdakyuBusTest : BusApiClientTest(OdakyuBus)

    /**
     * 西武バスのテスト
     */
    class SeibuBusTest : BusApiClientTest(SeibuBus)

    /**
     * 東武バスのテスト
     */
    class TobuBusTest : BusApiClientTest(TobuBus)

    /**
     * 東京都交通局のテスト
     */
    class ToeiTest : BusApiClientTest(Toei)

    /**
     * 東急バスのテスト
     */
    class TokyuBusTest : BusApiClientTest(TokyuBus)
}
