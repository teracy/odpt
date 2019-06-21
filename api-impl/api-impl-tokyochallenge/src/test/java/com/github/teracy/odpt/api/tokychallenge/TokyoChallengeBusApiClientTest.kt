package com.github.teracy.odpt.api.tokychallenge

import com.github.teracy.odpt.core.v4.bus.request.BusArgument
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
 * 東京公共交通オープンデータチャレンジバスAPI向けクライアント実装のテスト（実装そのもののテスト）
 */
@RunWith(Enclosed::class)
class TokyoChallengeBusApiClientTest {
    /**
     * 東京公共交通オープンデータチャレンジバスAPI向けクライアント実装テスト基底クラス
     */
    abstract class BusClientTest : ApiClientTest {
        lateinit var consumerKey: String
        lateinit var okHttpClient: OkHttpClient
        lateinit var apiClient: TokyoChallengeBusApiClient

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

            apiClient = TokyoChallengeBusApiClient(okHttpClient, consumerKey)
        }

        @After
        fun tearDown() {
            // 一気に流したら429が返ってきてしまったので、ひとまず1テストあたり2000msecのインターバルをかませる
            Thread.sleep(2000)
        }
    }

    /**
     * getBusのテスト
     */
    class BusTest : BusClientTest() {
        /**
         * パラメータ無しで取得した場合のテスト
         */
        @Test
        fun getBusWithoutParams() {
            runBlocking {
                val busList = apiClient.getBus()
                assertThat(busList).isNotEmpty
            }
        }

        /**
         * 固有識別子を指定して取得した場合のテスト
         */
        @Test
        fun getBusById() {
            runBlocking {
                // 3件指定の内、2件は正しい固有識別子で1件は固有識別子ではなく固有識別子別名
                val argument = BusArgument.ById(
                    listOf(
                        "urn:ucode:_00001C00000000000001000003100D60",
                        "urn:ucode:_00001C00000000000001000003100C0B",
                        "odpt.Bus:Toei.To02.32301.1.N322"
                    )
                )
                // リアルタイム情報のため呼び出し毎に結果が異なるので、実行できることだけ確認する
                apiClient.getBus(argument)
            }
        }
    }
}