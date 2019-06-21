package com.github.teracy.odpt.api.odptdatacenter

import com.github.teracy.odpt.core.v4.common.request.CalendarArgument
import com.github.teracy.odpt.core.v4.common.request.OperatorArgument
import com.github.teracy.odpt.model.CalendarId
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
 * 公共交通オープンデータセンター共通API向けクライアント実装のテスト
 */
@RunWith(Enclosed::class)
class OdptDataCenterCommonApiClientTest {
    /**
     * 公共交通オープンデータセンター共通API向けクライアント実装テスト基底クラス
     */
    abstract class CommonApiClientTest : ApiClientTest {
        lateinit var consumerKey: String
        lateinit var okHttpClient: OkHttpClient
        lateinit var apiClient: OdptDataCenterCommonApiClient

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

            apiClient = OdptDataCenterCommonApiClient(okHttpClient, consumerKey)
        }

        @After
        fun tearDown() {
            // 一気に流したら429が返ってきてしまったので、ひとまず1テストあたり2000msecのインターバルをかませる
            Thread.sleep(2000)
        }
    }

    /**
     * getCalendarのテスト
     */
    class CalendarTest : CommonApiClientTest() {
        /**
         * パラメータ無しで取得した場合のテスト
         */
        @Test
        fun getCalendarWithoutParams() {
            runBlocking {
                val calendarList = apiClient.getCalendar()
                assertThat(calendarList).isNotNull
                assertThat(calendarList).isNotEmpty
            }
        }

        /**
         * 固有識別子を指定して取得した場合のテスト
         */
        @Test
        fun getCalendarById() {
            runBlocking {
                // 4件指定の内、3件は正しい固有識別子で1件は固有識別子ではなく固有識別子別名
                val argument = CalendarArgument.ById(
                    idList = listOf(
                        "urn:ucode:_00001C000000000000010000030E6565",
                        "urn:ucode:_00001C00000000000001000003230769",
                        "urn:ucode:_00001C00000000000001000003230768",
                        "odpt.Calendar:SaturdayHoliday"
                    )
                )
                val calendarList = apiClient.getCalendar(argument)
                assertThat(calendarList).isNotNull
                assertThat(calendarList).isNotEmpty
                assertThat(calendarList.size).isEqualTo(3)
            }
        }

        /**
         * 固有識別子別名を指定して取得した場合のテスト
         */
        @Test
        fun getCalendarBySameAs() {
            runBlocking {
                // 4件指定の内、1件は正しい固有識別子別名で3件は固有識別子別名ではなく固有識別子
                val argument = CalendarArgument.ByCalendarId(
                    calendarIdList = listOf(
                        CalendarId("urn:ucode:_00001C000000000000010000030E6565"),
                        CalendarId("urn:ucode:_00001C00000000000001000003230769"),
                        CalendarId("urn:ucode:_00001C00000000000001000003230768"),
                        CalendarId("odpt.Calendar:SaturdayHoliday")
                    )
                )
                val calendarList = apiClient.getCalendar(argument)
                assertThat(calendarList).isNotNull
                assertThat(calendarList).isNotEmpty
                assertThat(calendarList.size).isEqualTo(1)
            }
        }
    }

    /**
     * getOperatorのテスト
     */
    class OperatorTest : CommonApiClientTest() {
        /**
         * パラメータ無しで取得した場合のテスト
         */
        @Test
        fun getOperatorWithoutParams() {
            runBlocking {
                val operatorList = apiClient.getOperator()
                assertThat(operatorList).isNotNull
                assertThat(operatorList).isNotEmpty
            }
        }

        /**
         * 固有識別子を指定して取得した場合のテスト
         */
        @Test
        fun getOperatorById() {
            runBlocking {
                // 2件指定の内、1件は正しい固有識別子で1件は固有識別子ではなく固有識別子別名
                val argument = OperatorArgument.ById(
                    idList = listOf(
                        "urn:ucode:_00001C000000000000010000030E6609",
                        "odpt.Operator:Toei"
                    )
                )
                val operatorList = apiClient.getOperator(argument)
                assertThat(operatorList).isNotNull
                assertThat(operatorList).isNotEmpty
                assertThat(operatorList.size).isEqualTo(1)
            }
        }

        /**
         * 固有識別子別名を指定して取得した場合のテスト
         */
        @Test
        fun getOperatorBySameAs() {
            runBlocking {
                // 2件指定の内、1件は正しい固有識別子別名で1件は固有識別子別名ではなく固有識別子
                val operatorList = apiClient.getOperator(
                    sameAsList = listOf(
                        "urn:ucode:_00001C000000000000010000030E6609",
                        "odpt.Operator:Toei"
                    )
                )
                assertThat(operatorList).isNotNull
                assertThat(operatorList).isNotEmpty
                assertThat(operatorList.size).isEqualTo(1)
            }
        }
    }
}
