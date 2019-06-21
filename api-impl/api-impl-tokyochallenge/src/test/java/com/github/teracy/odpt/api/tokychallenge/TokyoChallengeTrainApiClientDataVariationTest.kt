package com.github.teracy.odpt.api.tokychallenge

import com.github.teracy.odpt.api.tokychallenge.support.*
import com.github.teracy.odpt.core.v4.common.request.OperatorArgument
import com.github.teracy.odpt.core.v4.train.request.*
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
 * 東京公共交通オープンデータチャレンジ鉄道API向けクライアント実装のテスト（事業者毎のデータ実装差異を洗い出すためのテスト）
 * 対象事業者全てで実行して落ちるところがないことを確認する
 */
@RunWith(Enclosed::class)
class TokyoChallengeTrainApiClientDataVariationTest {
    /**
     * 鉄道APIテスト基底クラス
     */
    abstract class TrainApiClientTest(tokyoChallengeOperator: TokyoChallengeOperator) : ApiClientTest {
        private val operatorIdList = listOf(tokyoChallengeOperator.operatorId)
        private lateinit var consumerKey: String
        private lateinit var okHttpClient: OkHttpClient
        private lateinit var commonApiClient: TokyoChallengeCommonApiClient
        private lateinit var trainApiClient: TokyoChallengeTrainApiClient

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
            trainApiClient = TokyoChallengeTrainApiClient(okHttpClient, consumerKey)
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
         * getPassengerSurveyのテスト
         */
        @Test
        fun getPassengerSurvey() {
            runBlocking {
                // 対応していない事業者もあるため、実行できることだけを確認する（対応していない場合、実行はできるが空が返ってくる）
                trainApiClient.getPassengerSurvey(PassengerSurveyArgument.ByOperatorId(operatorIdList))
            }
        }

        /**
         * getRailwayのテスト
         */
        @Test
        fun getRailway() {
            runBlocking {
                // 対応していない事業者もあるため、実行できることだけを確認する（対応していない場合、実行はできるが空が返ってくる）
                trainApiClient.getRailway(RailwayArgument.ByOperatorId(operatorIdList))
            }
        }

        /**
         * getRailwayFareのテスト
         */
        @Test
        fun getRailwayFare() {
            runBlocking {
                // 対応していない事業者もあるため、実行できることだけを確認する（対応していない場合、実行はできるが空が返ってくる）
                trainApiClient.getRailwayFare(RailwayFareArgument.ByOperatorId(operatorIdList))
            }
        }

        /**
         * getStationのテスト
         */
        @Test
        fun getStation() {
            runBlocking {
                // 対応していない事業者もあるため、実行できることだけを確認する（対応していない場合、実行はできるが空が返ってくる）
                trainApiClient.getStation(StationArgument.ByOperatorId(operatorIdList))
            }
        }

        /**
         * getStationTimetableのテスト
         */
        @Test
        fun getStationTimetable() {
            runBlocking {
                // 対応していない事業者もあるため、実行できることだけを確認する（対応していない場合、実行はできるが空が返ってくる）
                trainApiClient.getStationTimetable(StationTimetableArgument.ByOperatorId(operatorIdList))
            }
        }

        /**
         * getTrainのテスト
         */
        @Test
        fun getTrain() {
            runBlocking {
                // 対応していない事業者もあるため、実行できることだけを確認する（対応していない場合、実行はできるが空が返ってくる）
                trainApiClient.getTrain(TrainArgument.ByOperatorId(operatorIdList))
            }
        }

        /**
         * getTrainInformationのテスト
         */
        @Test
        fun getTrainInformation() {
            runBlocking {
                // 対応していない事業者もあるため、実行できることだけを確認する（対応していない場合、実行はできるが空が返ってくる）
                trainApiClient.getTrainInformation(TrainInformationArgument.ByOperatorId(operatorIdList))
            }
        }

        /**
         * getTrainTimetableのテスト
         */
        @Test
        fun getTrainTimetable() {
            runBlocking {
                // 対応していない事業者もあるため、実行できることだけを確認する（対応していない場合、実行はできるが空が返ってくる）
                trainApiClient.getTrainTimetable(TrainTimetableArgument.ByOperatorId(operatorIdList))
            }
        }

        /**
         * getTrainTypeのテスト
         */
        @Test
        fun getTrainType() {
            runBlocking {
                // 対応していない事業者もあるため、実行できることだけを確認する（対応していない場合、実行はできるが空が返ってくる）
                trainApiClient.getTrainType(TrainTypeArgument.ByOperatorId(operatorIdList))
            }
        }
    }

    /**
     * JR東日本のテスト
     */
    class JrEastTest : TrainApiClientTest(JrEast)

    /**
     * 京急電鉄のテスト
     */
    class KeikyuTest : TrainApiClientTest(Keikyu)

    /**
     * 京王電鉄のテスト
     */
    class KeioTest : TrainApiClientTest(Keio)

    /**
     * 京成電鉄のテスト
     */
    class KeiseiTest : TrainApiClientTest(Keisei)

    /**
     * 小田急電鉄のテスト
     */
    class OdakyuTest : TrainApiClientTest(Odakyu)

    /**
     * 西武鉄道のテスト
     */
    class SeibuTest : TrainApiClientTest(Seibu)

    /**
     * 東武鉄道のテスト
     */
    class TobuTest : TrainApiClientTest(Tobu)

    /**
     * 東京都交通局のテスト
     */
    class ToeiTest : TrainApiClientTest(Toei)

    /**
     * 東京メトロのテスト
     */
    class TokyoMetroTest : TrainApiClientTest(TokyoMetro)

    /**
     * 東急電鉄のテスト
     */
    class TokyuTest : TrainApiClientTest(Tokyu)

    /**
     * 東京臨海高速鉄道のテスト
     */
    class TwrTest : TrainApiClientTest(TwrRinkaiLine)

    /**
     * ゆりかもめのテスト
     */
    class YurikamomeTest : TrainApiClientTest(Yurikamome)
}
