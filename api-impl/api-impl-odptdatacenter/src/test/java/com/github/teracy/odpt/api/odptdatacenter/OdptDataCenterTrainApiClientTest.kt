package com.github.teracy.odpt.api.odptdatacenter

import com.github.teracy.odpt.core.v4.train.request.*
import com.github.teracy.odpt.model.*
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
 * 公共交通オープンデータセンター鉄道API向けクライアント実装のテスト
 */
@RunWith(Enclosed::class)
class OdptDataCenterTrainApiClientTest {
    /**
     * 公共交通オープンデータセンター鉄道API向けクライアント実装テスト基底クラス
     */
    abstract class TrainApiClientTest : ApiClientTest {
        lateinit var consumerKey: String
        lateinit var okHttpClient: OkHttpClient
        lateinit var apiClient: OdptDataCenterTrainApiClient

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

            apiClient = OdptDataCenterTrainApiClient(okHttpClient, consumerKey)
        }

        @After
        fun tearDown() {
            // 一気に流したら429が返ってきてしまったので、ひとまず1テストあたり2000msecのインターバルをかませる
            Thread.sleep(2000)
        }
    }

    /**
     * getPassengerSurveyのテスト
     */
    class PassengerSurveyTest : TrainApiClientTest() {
        /**
         * パラメータ無しで取得した場合のテスト
         */
        @Test
        fun getPassengerSurveyWithoutParams() {
            runBlocking {
                val passengerSurveyList = apiClient.getPassengerSurvey()
                assertThat(passengerSurveyList).isNotEmpty
            }
        }

        /**
         * 固有識別子を指定して取得した場合のテスト
         */
        @Test
        fun getPassengerSurveyById() {
            runBlocking {
                // 3件指定の内、2件は正しい固有識別子で1件は固有識別子ではなく固有識別子別名
                val argument = PassengerSurveyArgument.ById(
                    listOf(
                        "urn:ucode:_00001C00000000000001000003194FAF",
                        "urn:ucode:_00001C00000000000001000003194FEC",
                        "odpt.PassengerSurvey:Toei.AoyamaItchome"
                    )
                )
                val passengerSurveyList = apiClient.getPassengerSurvey(argument)
                assertThat(passengerSurveyList).isNotEmpty
                assertThat(passengerSurveyList.size).isEqualTo(2)
            }
        }

        /**
         * 固有識別子別名を指定して取得した場合のテスト
         */
        @Test
        fun getPassengerSurveyByPassengerSurveyId() {
            runBlocking {
                // 3件指定の内、1件は正しい固有識別子別名
                val argument = PassengerSurveyArgument.ByPassengerSurveyId(
                    listOf(
                        PassengerSurveyId("urn:ucode:_00001C00000000000001000003194FAF"),
                        PassengerSurveyId("urn:ucode:_00001C00000000000001000003194FEC"),
                        PassengerSurveyId("odpt.PassengerSurvey:Toei.AoyamaItchome")
                    )
                )
                val passengerSurveyList = apiClient.getPassengerSurvey(argument)
                assertThat(passengerSurveyList).isNotEmpty
                assertThat(passengerSurveyList.size).isEqualTo(1)
            }
        }

        /**
         * 駅IDを指定して取得した場合のテスト
         */
        @Test
        fun getPassengerSurveyByStationId() {
            runBlocking {
                // 正しい路線ID
                val argument = PassengerSurveyArgument.ByStationId(StationId("odpt.Station:Toei.Asakusa.Mita"))
                val passengerSurveyList = apiClient.getPassengerSurvey(argument)
                assertThat(passengerSurveyList).isNotEmpty
                assertThat(passengerSurveyList.size).isEqualTo(1)
            }
            runBlocking {
                // 誤った駅ID
                val argument = PassengerSurveyArgument.ByStationId(StationId("invalid"))
                val passengerSurveyList = apiClient.getPassengerSurvey(argument)
                assertThat(passengerSurveyList).isEmpty()
            }
            runBlocking {
                // Argumentでのwrapなしで1件指定
                val passengerSurveyList = apiClient.getPassengerSurvey(station = "odpt.Station:Toei.Asakusa.Mita")
                // 取得できる
                assertThat(passengerSurveyList).isNotEmpty
                assertThat(passengerSurveyList.size).isEqualTo(1)
            }
            runBlocking {
                // Argumentでのwrapなしで複数指定
                val station = listOf(
                    "odpt.Station:Toei.Asakusa.Mita",
                    "odpt.Station:Toei.Asakusa.Asakusa",
                    "odpt.Station:Toei.Asakusa.Daimon"
                ).joinToString(separator = ",")
                val passengerSurveyList = apiClient.getPassengerSurvey(station = station)
                // 取得できない
                assertThat(passengerSurveyList).isEmpty()
            }
        }

        /**
         * 路線IDを指定して取得した場合のテスト
         */
        @Test
        fun getPassengerSurveyByRailwayId() {
            runBlocking {
                // 正しい路線ID
                val argument = PassengerSurveyArgument.ByRailwayId(RailwayId("odpt.Railway:Toei.Asakusa"))
                val passengerSurveyList = apiClient.getPassengerSurvey(argument)
                // 空でないことだけ確かめる
                assertThat(passengerSurveyList).isNotEmpty
            }
            runBlocking {
                // 誤った路線ID
                val argument = PassengerSurveyArgument.ByRailwayId(RailwayId("invalid"))
                val passengerSurveyList = apiClient.getPassengerSurvey(argument)
                assertThat(passengerSurveyList).isEmpty()
            }
            runBlocking {
                // Argumentでのwrapなしで1件指定
                val passengerSurveyList = apiClient.getPassengerSurvey(railway = "odpt.Railway:Toei.Asakusa")
                // 取得できる（空でないことだけ確かめる）
                assertThat(passengerSurveyList).isNotEmpty
            }
            runBlocking {
                // Argumentでのwrapなしで複数指定
                val railway = listOf(
                    "odpt.Railway:Toei.Asakusa",
                    "odpt.Railway:Toei.Mita",
                    "odpt.Railway:Toei.Shinjuku"
                ).joinToString(separator = ",")
                val passengerSurveyList = apiClient.getPassengerSurvey(railway = railway)
                // 取得できない
                assertThat(passengerSurveyList).isEmpty()
            }
        }
    }

    /**
     * getRailDirectionのテスト
     */
    class RailDirectionTest : TrainApiClientTest() {
        /**
         * パラメータ無しで取得した場合のテスト
         */
        @Test
        fun getRailDirectionWithoutParams() {
            runBlocking {
                val railDirectionList = apiClient.getRailDirection()
                assertThat(railDirectionList).isNotEmpty
            }
        }

        /**
         * 固有識別子を指定して取得した場合のテスト
         */
        @Test
        fun getRailDirectionById() {
            runBlocking {
                // 3件指定の内、2件は正しい固有識別子で1件は固有識別子ではなく固有識別子別名
                val argument = RailDirectionArgument.ById(
                    listOf(
                        "urn:ucode:_00001C0000000000000100000323076F",
                        "urn:ucode:_00001C00000000000001000003230770",
                        "odpt.RailDirection:Toei.Waseda"
                    )
                )
                val railDirectionList = apiClient.getRailDirection(argument)
                assertThat(railDirectionList).isNotEmpty
                assertThat(railDirectionList.size).isEqualTo(2)
            }
        }

        /**
         * 固有識別子別名を指定して取得した場合のテスト
         */
        @Test
        fun getRailDirectionByRailDirectionId() {
            runBlocking {
                // 3件指定の内、1件は正しい固有識別子別名
                val argument = RailDirectionArgument.ByRailDirectionId(
                    listOf(
                        RailDirectionId("urn:ucode:_00001C0000000000000100000323076F"),
                        RailDirectionId("urn:ucode:_00001C00000000000001000003230770"),
                        RailDirectionId("odpt.RailDirection:Toei.Waseda")
                    )
                )
                val railDirectionList = apiClient.getRailDirection(argument)
                assertThat(railDirectionList).isNotEmpty
                assertThat(railDirectionList.size).isEqualTo(1)
            }
        }
    }

    /**
     * getRailwayのテスト
     */
    class RailwayTest : TrainApiClientTest() {
        /**
         * パラメータ無しで取得した場合のテスト
         */
        @Test
        fun getRailwayWithoutParams() {
            runBlocking {
                val railwayList = apiClient.getRailway()
                assertThat(railwayList).isNotEmpty
            }
        }

        /**
         * 固有識別子を指定して取得した場合のテスト
         */
        @Test
        fun getRailwayById() {
            runBlocking {
                // 3件指定の内、2件は正しい固有識別子で1件は固有識別子ではなく固有識別子別名
                val argument = RailwayArgument.ById(
                    listOf(
                        "urn:ucode:_00001C00000000000001000003100E72",
                        "urn:ucode:_00001C00000000000001000003100E6E",
                        "odpt.Railway:Toei.Mita"
                    )
                )
                val railwayList = apiClient.getRailway(argument)
                assertThat(railwayList).isNotEmpty
                assertThat(railwayList.size).isEqualTo(2)
            }
        }

        /**
         * 固有識別子別名を指定して取得した場合のテスト
         */
        @Test
        fun getRailwayByRailDirectionId() {
            runBlocking {
                // 3件指定の内、1件は正しい固有識別子別名
                val argument = RailwayArgument.ByRailwayId(
                    listOf(
                        RailwayId("urn:ucode:_00001C00000000000001000003100E72"),
                        RailwayId("urn:ucode:_00001C00000000000001000003100E6E"),
                        RailwayId("odpt.Railway:Toei.Mita")
                    )
                )
                val railwayList = apiClient.getRailway(argument)
                assertThat(railwayList).isNotEmpty
                assertThat(railwayList.size).isEqualTo(1)
            }
        }

        /**
         * 運行系統名を指定して取得した場合のテスト
         */
        @Test
        fun getRailwayByRailwayName() {
            // 3件中2件は正しい運行系統名
            val railwayNameList = listOf("浅草線", "新宿線", "大捜査線")
            runBlocking {
                val argument = RailwayArgument.ByRailwayName(railwayNameList = railwayNameList)
                val railwayList = apiClient.getRailway(argument)
                assertThat(railwayList).isNotEmpty
                assertThat(railwayList.size).isEqualTo(2)
            }
        }

        /**
         * 路線コードを指定して取得した場合のテスト
         */
        @Test
        fun getRailwayByLineCode() {
            // 4件中3件は正しい路線コード
            val lineCodeList = listOf("A", "S", "XXX", "NT")
            runBlocking {
                val argument = RailwayArgument.ByLineCode(lineCodeList = lineCodeList)
                val railwayList = apiClient.getRailway(argument)
                assertThat(railwayList).isNotEmpty
                assertThat(railwayList.size).isEqualTo(3)
            }
        }

        /**
         * 地物情報検索APIを利用して取得した場合のテスト
         */
        @Test
        fun getStationByPoi() {
            // （東武）浅草駅から半径500mで検索する
            val longitude = 139.797762
            val latitude = 35.710792
            val radius = 500.0
            runBlocking {
                // 検索引数指定無し
                val stationList = apiClient.getStation(
                    longitude = longitude,
                    latitude = latitude,
                    radius = radius
                )
                assertThat(stationList).isNotEmpty
                // 1件取得できるはず
                assertThat(stationList.size).isEqualTo(1)
            }
            runBlocking {
                // 固有識別子指定：浅草線浅草駅、浅草線浅草橋駅、浅草線蔵前駅
                val argument = StationArgument.ById(
                    listOf(
                        "urn:ucode:_00001C00000000000001000003102C8D",
                        "urn:ucode:_00001C00000000000001000003102C8B",
                        "urn:ucode:_00001C00000000000001000003102CCB"
                    )
                )
                val stationList = apiClient.getStation(
                    longitude = longitude,
                    latitude = latitude,
                    radius = radius,
                    argument = argument
                )
                assertThat(stationList).isNotEmpty
                // 1件取得できるはず
                assertThat(stationList.size).isEqualTo(1)
            }
            runBlocking {
                // 固有識別子別名指定：大江戸線両国駅（浅草駅半径500m外）、浅草線浅草駅、荒川線荒川一中前停留所（浅草駅半径500m外）
                val argument = StationArgument.ByStationId(
                    listOf(
                        StationId("odpt.Station:Toei.Oedo.Ryogoku"),
                        StationId("odpt.Station:Toei.Asakusa.Asakusa"),
                        StationId("odpt.Station:Toei.Arakawa.ArakawaItchumae")
                    )
                )
                val stationList = apiClient.getStation(
                    longitude = longitude,
                    latitude = latitude,
                    radius = radius,
                    argument = argument
                )
                assertThat(stationList).isNotEmpty
                // 1件取得できるはず
                assertThat(stationList.size).isEqualTo(1)
            }
            runBlocking {
                // 路線ID指定
                val argument = StationArgument.ByRailwayId(
                    listOf(
                        RailwayId("odpt.Railway:Toei.Arakawa"),
                        RailwayId("odpt.Railway:Toei.Oedo"),
                        RailwayId("odpt.Railway:Toei.Asakusa")
                    )
                )
                val stationList = apiClient.getStation(
                    longitude = longitude,
                    latitude = latitude,
                    radius = radius,
                    argument = argument
                )
                assertThat(stationList).isNotEmpty
                // 1件取得できるはず
                assertThat(stationList.size).isEqualTo(1)
            }
            runBlocking {
                // 駅名指定
                val argument = StationArgument.ByStationName(
                    listOf("三田", "浅草", "高島平", "新宿")
                )
                val stationList = apiClient.getStation(
                    longitude = longitude,
                    latitude = latitude,
                    radius = radius,
                    argument = argument
                )
                assertThat(stationList).isNotEmpty
                // 1件取得できるはず
                assertThat(stationList.size).isEqualTo(1)
            }
            runBlocking {
                // 駅コード指定（新宿線馬喰横山駅、浅草線本所吾妻橋駅、大江戸線蔵前駅、浅草線浅草橋駅、浅草線浅草橋駅
                val argument = StationArgument.ByStationCode(
                    listOf("S-09", "A-19", "E-11", "A-18", "A-16")
                )
                val stationList = apiClient.getStation(
                    longitude = longitude,
                    latitude = latitude,
                    radius = radius,
                    argument = argument
                )
                assertThat(stationList).isNotEmpty
                // 1件取得できるはず
                assertThat(stationList.size).isEqualTo(1)
            }
        }
    }

    /**
     * getRailwayFareのテスト
     */
    class RailwayFareTest : TrainApiClientTest() {
        /**
         * パラメータ無しで取得した場合のテスト
         */
        @Test
        fun getRailwayFareWithoutParams() {
            runBlocking {
                val railwayFareList = apiClient.getRailwayFare()
                assertThat(railwayFareList).isNotEmpty
            }
        }

        /**
         * 固有識別子を指定して取得した場合のテスト
         */
        @Test
        fun getRailwayFareById() {
            runBlocking {
                // 3件指定の内、2件は正しい固有識別子で1件は固有識別子ではなく固有識別子別名
                val argument = RailwayFareArgument.ById(
                    listOf(
                        "urn:ucode:_00001C0000000000000100000317834A",
                        "urn:ucode:_00001C00000000000001000003177C7B",
                        "odpt.RailwayFare:Toei.Arakawa.ArakawaItchumae.Toei.Arakawa.ArakawaNanachome"
                    )
                )
                val railwayFareList = apiClient.getRailwayFare(argument)
                assertThat(railwayFareList).isNotEmpty
                assertThat(railwayFareList.size).isEqualTo(2)
            }
        }

        /**
         * 固有識別子別名を指定して取得した場合のテスト
         */
        @Test
        fun getRailwayFareByRailwayFareId() {
            runBlocking {
                // 3件指定の内、1件は正しい固有識別子別名
                val argument = RailwayFareArgument.ByRailwayFareId(
                    listOf(
                        RailwayFareId("urn:ucode:_00001C0000000000000100000317834A"),
                        RailwayFareId("urn:ucode:_00001C00000000000001000003177C7B"),
                        RailwayFareId("odpt.RailwayFare:Toei.Arakawa.ArakawaItchumae.Toei.Arakawa.ArakawaNanachome")
                    )
                )
                val railwayFareList = apiClient.getRailwayFare(argument)
                assertThat(railwayFareList).isNotEmpty
                assertThat(railwayFareList.size).isEqualTo(1)
            }
        }

        /**
         * 駅間の始点駅の駅IDのみを指定して取得した場合のテスト
         */
        @Test
        fun getRailwayFareByFromStationId() {
            // 3件中2件は正しい駅ID
            val fromStationIdList = listOf(
                StationId("odpt.Station:Toei.Mita.Hakusan"),
                StationId("odpt.Station:Toei.Asakusa.Daimon"),
                StationId("invalid")
            )
            runBlocking {
                val argument = RailwayFareArgument.ByFromStationId(fromStationIdList = fromStationIdList)
                val railwayList = apiClient.getRailwayFare(argument)
                // 取得できる（空でないことだけ確かめる）
                assertThat(railwayList).isNotEmpty
            }
            runBlocking {
                // Argumentでのwrapなし
                val railwayFareList = apiClient.getRailwayFare(fromStationList = fromStationIdList.map { it.id })
                // 取得できる（空でないことだけ確かめる）
                assertThat(railwayFareList).isNotEmpty
            }
        }

        /**
         * 駅間の終点駅の駅IDのみを指定して取得した場合のテスト
         */
        @Test
        fun getRailwayFareByToStationId() {
            // 3件中2件は正しい駅ID
            val toStationIdList = listOf(
                StationId("odpt.Station:Toei.Mita.Hakusan"),
                StationId("odpt.Station:Toei.Asakusa.Daimon"),
                StationId("invalid")
            )
            runBlocking {
                val argument = RailwayFareArgument.ByToStationId(toStationIdList = toStationIdList)
                val railwayList = apiClient.getRailwayFare(argument)
                // 取得できる（空でないことだけ確かめる）
                assertThat(railwayList).isNotEmpty
            }
            runBlocking {
                // Argumentでのwrapなし
                val railwayFareList = apiClient.getRailwayFare(toStationList = toStationIdList.map { it.id })
                // 取得できる（空でないことだけ確かめる）
                assertThat(railwayFareList).isNotEmpty
            }
        }

        /**
         * 駅間の始発駅及び終点駅の駅IDを指定して取得した場合のテスト
         */
        @Test
        fun getRailwayFareByFromToStationId() {
            // 3件中2件は都営浅草線、1件は都営三田線
            val fromStationIdList = listOf(
                StationId("odpt.Station:Toei.Asakusa.Asakusabashi"),
                StationId("odpt.Station:Toei.Asakusa.Daimon"),
                StationId("odpt.Station:Toei.Mita.Kasuga")
            )
            // 5件中2件は都営浅草線、1件は都営三田線、1件は都営新宿線、1件は荒川線（いずれもfromStationIdListとの重複なし）
            val toStationIdList = listOf(
                StationId("odpt.Station:Toei.Asakusa.Takaracho"),
                StationId("odpt.Station:Toei.Asakusa.Oshiage"),
                StationId("odpt.Station:Toei.Mita.ShimuraSakaue"),
                StationId("odpt.Station:Toei.Shinjuku.ShinjukuSanchome"),
                StationId("odpt.Station:Toei.Arakawa.ArakawaShakomae")
            )
            runBlocking {
                val argument = RailwayFareArgument.ByFromToStationId(
                    fromStationIdList = fromStationIdList,
                    toStationIdList = toStationIdList
                )
                val railwayFareList = apiClient.getRailwayFare(argument)
                // 取得できる：地下鉄がfrom3xto4の12件、荒川線がfrom0xto1の0件、計12件のはず
                assertThat(railwayFareList).isNotEmpty
                assertThat(railwayFareList.size).isEqualTo(12)
            }
            runBlocking {
                // Argumentでのwrapなし
                val railwayList = apiClient.getRailwayFare(
                    fromStationList = fromStationIdList.map { it.id },
                    toStationList = toStationIdList.map { it.id }
                )
                // 取得できる：地下鉄がfrom3xto4の12件、荒川線がfrom0xto1の0件、計12件のはず
                assertThat(railwayList).isNotEmpty
                assertThat(railwayList.size).isEqualTo(12)
            }
        }
    }

    /**
     * getStationのテスト
     */
    class StationTest : TrainApiClientTest() {
        /**
         * パラメータ無しで取得した場合のテスト
         */
        @Test
        fun getStationWithoutParams() {
            runBlocking {
                val stationList = apiClient.getStation()
                assertThat(stationList).isNotEmpty
            }
        }

        /**
         * 固有識別子を指定して取得した場合のテスト
         */
        @Test
        fun getStationById() {
            runBlocking {
                // 3件指定の内、2件は正しい固有識別子で1件は固有識別子ではなく固有識別子別名
                val argument = StationArgument.ById(
                    listOf(
                        "urn:ucode:_00001C00000000000001000003102CE8",
                        "urn:ucode:_00001C00000000000001000003102CEB",
                        "odpt.Station:Toei.Arakawa.ArakawaNichome"
                    )
                )
                val stationList = apiClient.getStation(argument)
                assertThat(stationList).isNotEmpty
                assertThat(stationList.size).isEqualTo(2)
            }
        }

        /**
         * 固有識別子別名を指定して取得した場合のテスト
         */
        @Test
        fun getStationByStationId() {
            runBlocking {
                // 3件指定の内、1件は正しい固有識別子別名
                val argument = StationArgument.ByStationId(
                    listOf(
                        StationId("urn:ucode:_00001C00000000000001000003102CE8"),
                        StationId("urn:ucode:_00001C00000000000001000003102CEB"),
                        StationId("odpt.Station:Toei.Arakawa.ArakawaNichome")
                    )
                )
                val stationList = apiClient.getStation(argument)
                assertThat(stationList).isNotEmpty
                assertThat(stationList.size).isEqualTo(1)
            }
        }

        /**
         * 路線IDを指定して取得した場合のテスト
         */
        @Test
        fun getStationByRailwayId() {
            // 3件中2件は正しい路線ID
            val railwayIdList = listOf(
                RailwayId("odpt.Railway:Toei.Asakusa"),
                RailwayId("odpt.Railway:Toei.Shinjuku"),
                RailwayId("invalid")
            )
            runBlocking {
                val argument = StationArgument.ByRailwayId(railwayIdList = railwayIdList)
                val stationList = apiClient.getStation(argument)
                // 取得できる（空でないことだけ確かめる）
                assertThat(stationList).isNotEmpty
            }
        }

        /**
         * 駅名指定して取得した場合のテスト
         */
        @Test
        fun getStationByStationName() {
            // 4件中1件は正しい駅名（完全一致、単独路線）、1件は正しい駅名（完全一致、複数路線該当）、1件は部分一致、1件は部分一致でも全く該当なし
            val stationNameList = listOf("浅草", "三田", "王子", "北千住")
            runBlocking {
                val argument = StationArgument.ByStationName(stationNameList = stationNameList)
                val stationList = apiClient.getStation(argument)
                // 取得できる
                assertThat(stationList).isNotEmpty
                // 浅草線浅草駅、浅草線三田駅、三田線三田駅の3駅のはず
                assertThat(stationList.size).isEqualTo(3)
            }
        }

        /**
         * 駅コードを指定して取得した場合のテスト
         */
        @Test
        fun getStationByStationCode() {
            // 4件中1件は正しい駅コード（完全一致）、1件は前方一致、1件は部分一致、1件は部分一致でも全く該当なし
            val stationCodeList = listOf("S-17", "A-", "-06", "X-99")
            runBlocking {
                val argument = StationArgument.ByStationCode(stationCodeList = stationCodeList)
                val stationList = apiClient.getStation(argument)
                // 取得できる：新宿線船堀駅の1件のみのはず
                assertThat(stationList).isNotEmpty
                assertThat(stationList.size).isEqualTo(1)
            }
        }
    }

    /**
     * getStationTimetableのテスト
     */
    class StationTimetableTest : TrainApiClientTest() {
        /**
         * パラメータ無しで取得した場合のテスト
         */
        @Test
        fun getStationTimetableWithoutParams() {
            runBlocking {
                val stationTimetableListonList = apiClient.getStationTimetable()
                assertThat(stationTimetableListonList).isNotEmpty
            }
        }

        /**
         * 固有識別子を指定して取得した場合のテスト
         */
        @Test
        fun getStationTimetableById() {
            runBlocking {
                // 3件指定の内、2件は正しい固有識別子で1件は固有識別子ではなく固有識別子別名
                val argument = StationTimetableArgument.ById(
                    listOf(
                        "urn:ucode:_00001C0000000000000100000316E943",
                        "urn:ucode:_00001C000000000000010000031B31E0",
                        "odpt.StationTimetable:Toei.Arakawa.ArakawaItchumae.Toei.Minowabashi.Weekday"
                    )
                )
                val stationTimetableList = apiClient.getStationTimetable(argument)
                assertThat(stationTimetableList).isNotEmpty
                assertThat(stationTimetableList.size).isEqualTo(2)
            }
        }

        /**
         * 固有識別子別名を指定して取得した場合のテスト
         */
        @Test
        fun getStationTimetableByStationTimetableId() {
            runBlocking {
                // 3件指定の内、1件は正しい固有識別子別名
                val argument = StationTimetableArgument.ByStationTimetableId(
                    listOf(
                        StationTimetableId("urn:ucode:_00001C0000000000000100000316E943"),
                        StationTimetableId("urn:ucode:_00001C000000000000010000031B31E0"),
                        StationTimetableId("odpt.StationTimetable:Toei.Arakawa.ArakawaItchumae.Toei.Minowabashi.Weekday")
                    )
                )
                val stationTimetableList = apiClient.getStationTimetable(argument)
                assertThat(stationTimetableList).isNotEmpty
                assertThat(stationTimetableList.size).isEqualTo(1)
            }
        }

        /**
         * 路線IDを指定して取得した場合のテスト
         */
        @Test
        fun getStationTimetableByRailwayId() {
            // 3件中2件は正しい路線ID
            val railwayIdList = listOf(
                RailwayId("odpt.Railway:Toei.Asakusa"),
                RailwayId("odpt.Railway:Toei.Shinjuku"),
                RailwayId("invalid")
            )
            val railDirectionIdList = listOf(
                RailDirectionId("odpt.RailDirection:Northbound"),
                RailDirectionId("odpt.RailDirection:Eastbound")
            )
            val calendarIdList = listOf(CalendarId("odpt.Calendar:SaturdayHoliday"))
            runBlocking {
                // 路線IDのみ指定
                val argument = StationTimetableArgument.ByRailwayId(railwayIdList = railwayIdList)
                val stationTimetableList = apiClient.getStationTimetable(argument)
                // 取得できる：
                // 浅草線
                // 　押上駅・泉岳寺駅・西馬込駅除く17駅x2方面（南北）x2日付情報で68件、
                // 　泉岳寺駅が1駅x2方面（南北）x2日付情報で4件、
                // 　押上駅がが1駅x1方面（南）x2日付情報で2件、
                // 　西馬込駅が1駅x1方面（北）x2日付情報で2件、
                // 新宿線
                // 　新宿駅・本八幡駅を除く19駅x2方面（東西）x2日付情報で76件、
                // 　新宿駅が1駅x1方面（東）x2日付情報で2件、
                // 　本八幡駅が1駅x1方面（西）x2日付情報で2件、
                // 計156件のはず
                assertThat(stationTimetableList).isNotEmpty
                assertThat(stationTimetableList.size).isEqualTo(156)
            }
            runBlocking {
                // 路線IDと鉄道方面IDを指定
                val argument = StationTimetableArgument.ByRailwayId(
                    railwayIdList = railwayIdList,
                    railDirectionIdList = railDirectionIdList
                )
                val stationTimetableList = apiClient.getStationTimetable(argument)
                // 取得できる：
                // 浅草線
                // 　押上駅・泉岳寺駅・西馬込駅除く17駅x1方面（北）x2日付情報で34件、
                // 　泉岳寺駅が1駅x1方面（北）x2日付情報で2件、
                // 　押上駅がが1駅x0方面x2日付情報で0件、
                // 　西馬込駅が1駅x0方面（北）x2日付情報で2件、
                // 新宿線
                // 　新宿駅・本八幡駅を除く19駅x1方面（東）x2日付情報で38件、
                // 　新宿駅が1駅x1方面（東）x2日付情報で2件、
                // 　本八幡駅が1駅x0方面x2日付情報で0件、
                // 計38件のはず
                assertThat(stationTimetableList).isNotEmpty
                assertThat(stationTimetableList.size).isEqualTo(78)
            }
            runBlocking {
                // 路線IDと鉄道方面IDと日付情報IDを指定
                val argument = StationTimetableArgument.ByRailwayId(
                    railwayIdList = railwayIdList,
                    railDirectionIdList = railDirectionIdList,
                    calendarIdList = calendarIdList
                )
                val stationTimetableList = apiClient.getStationTimetable(argument)
                // 取得できる：
                // 浅草線
                // 　押上駅・泉岳寺駅・西馬込駅除く17駅x1方面（北）x1日付情報で17件、
                // 　泉岳寺駅が1駅x1方面（北）x1日付情報で1件、
                // 　押上駅がが1駅x0方面x1日付情報で0件、
                // 　西馬込駅が1駅x0方面（北）x1日付情報で1件、
                // 新宿線
                // 　新宿駅・本八幡駅を除く19駅x1方面（東）x1日付情報で19件、
                // 　新宿駅が1駅x1方面（東）x1日付情報で1件、
                // 　本八幡駅が1駅x0方面x1日付情報で0件、
                // 計19件のはず
                assertThat(stationTimetableList).isNotEmpty
                assertThat(stationTimetableList.size).isEqualTo(39)
            }
        }

        /**
         * 駅IDを指定して取得した場合のテスト
         */
        @Test
        fun getStationTimetableByStationId() {
            // 4件中3件は正しい駅ID
            val stationIdList = listOf(
                StationId("odpt.Station:Toei.Asakusa.Mita"),
                StationId("odpt.Station:Toei.Asakusa.Asakusa"),
                StationId("odpt.Station:Toei.Asakusa.Oshiage"),
                StationId("invalid")
            )
            val railDirectionIdList = listOf(RailDirectionId("odpt.RailDirection:Northbound"))
            val calendarIdList = listOf(CalendarId("odpt.Calendar:SaturdayHoliday"))
            runBlocking {
                // 駅IDのみ指定
                val argument = StationTimetableArgument.ByStationId(stationIdList = stationIdList)
                val stationTimetableList = apiClient.getStationTimetable(argument)
                // 取得できる：浅草線三田駅と浅草線浅草駅は2駅x2方面x2日付情報で8件、浅草線押上駅は1駅x1方面x2日付情報で2件の計10件のはず
                assertThat(stationTimetableList).isNotEmpty
                assertThat(stationTimetableList.size).isEqualTo(10)
            }
            runBlocking {
                // 駅IDと鉄道方面IDを指定
                val argument = StationTimetableArgument.ByStationId(
                    stationIdList = stationIdList,
                    railDirectionIdList = railDirectionIdList
                )
                val stationTimetableList = apiClient.getStationTimetable(argument)
                // 取得できる：浅草線三田駅と浅草線浅草駅は2駅x1方面x2日付情報で4件、浅草線押上駅は1駅x0方面x2日付情報で0件の計4件のはず
                assertThat(stationTimetableList).isNotEmpty
                assertThat(stationTimetableList.size).isEqualTo(4)
            }
            runBlocking {
                // 駅IDと鉄道方面IDと日付情報IDを指定
                val argument = StationTimetableArgument.ByStationId(
                    stationIdList = stationIdList,
                    railDirectionIdList = railDirectionIdList,
                    calendarIdList = calendarIdList
                )
                val stationTimetableList = apiClient.getStationTimetable(argument)
                // 取得できる：浅草線三田駅と浅草線浅草駅は2駅x1方面x1日付情報で2件、浅草線押上駅は1駅x0方面x1日付情報で0件の計2件のはず
                assertThat(stationTimetableList).isNotEmpty
                assertThat(stationTimetableList.size).isEqualTo(2)
            }
        }
    }

    /**
     * getTrainのテスト
     */
    class TrainTest : TrainApiClientTest() {
        /**
         * パラメータ無しで取得した場合のテスト
         */
        @Test
        fun getTrainWithoutParams() {
            runBlocking {
                // リアルタイム情報のため呼び出し毎に結果が異なるので、実行できることだけ確認する
                apiClient.getTrain()
            }
        }

        /**
         * 固有識別子を指定して取得した場合のテスト
         */
        @Test
        fun getTrainById() {
            runBlocking {
                // 3件指定の内、2件は正しい固有識別子で1件は固有識別子ではなく固有識別子別名
                val argument = TrainArgument.ById(
                    listOf(
                        "urn:uuid:032a2f3c-d240-4e3f-891d-22e94bf02bd8",
                        "urn:uuid:55d0018d-0120-4fd5-a576-d96fdca10602",
                        "odpt.Train:Toei.Mita.ODPT4790"
                    )
                )
                // リアルタイム情報のため呼び出し毎に結果が異なるので、実行できることだけ確認する
                apiClient.getTrain(argument)
            }
        }

        /**
         * 固有識別子別名を指定して取得した場合のテスト
         */
        @Test
        fun getTrainByTrainId() {
            runBlocking {
                // 3件指定の内、1件は正しい固有識別子別名
                val argument = TrainArgument.ByTrainId(
                    listOf(
                        TrainId("urn:uuid:032a2f3c-d240-4e3f-891d-22e94bf02bd8"),
                        TrainId("urn:uuid:55d0018d-0120-4fd5-a576-d96fdca10602"),
                        TrainId("odpt.Train:Toei.Mita.ODPT4790")
                    )
                )
                // リアルタイム情報のため呼び出し毎に結果が異なるので、実行できることだけ確認する
                apiClient.getTrain(argument)
            }
        }

        /**
         * 路線IDを指定して取得した場合のテスト
         */
        @Test
        fun getTrainByRailwayId() {
            // 3件中2件は正しい路線ID
            val railwayIdList = listOf(
                RailwayId("odpt.Railway:Toei.Arakawa"),
                RailwayId("odpt.Railway:Toei.Asakusa"),
                RailwayId("invalid")
            )
            runBlocking {
                val argument = TrainArgument.ByRailwayId(railwayIdList = railwayIdList)
                // リアルタイム情報のため呼び出し毎に結果が異なるので、実行できることだけ確認する
                apiClient.getTrain(argument)
            }
        }
    }

    /**
     * getTrainInformationのテスト
     */
    class TrainInformationTest : TrainApiClientTest() {
        /**
         * パラメータ無しで取得した場合のテスト
         */
        @Test
        fun getTrainInformationWithoutParams() {
            runBlocking {
                val trainInformationList = apiClient.getTrainInformation()
                assertThat(trainInformationList).isNotEmpty
                // 6路線なので全て平常運転でも6件存在するはず
                assertThat(trainInformationList.size).isEqualTo(6)
            }
        }

        /**
         * 固有識別子を指定して取得した場合のテスト
         */
        @Test
        fun getTrainInformationById() {
            runBlocking {
                // 3件指定の内、2件は正しい固有識別子で1件は固有識別子ではなく固有識別子別名
                val argument = TrainInformationArgument.ById(
                    listOf(
                        "urn:uuid:81dee9e8-9f67-496e-9251-fff49b805bcc",
                        "urn:uuid:1b6a8bae-3ee9-499b-ad9c-c127578018a9",
                        "odpt.TrainInformation:Toei.Shinjuku"
                    )
                )
                // リアルタイム情報のため呼び出し毎に結果が異なるので、実行できることだけ確認する
                apiClient.getTrainInformation(argument)
            }
        }

        /**
         * 固有識別子別名を指定して取得した場合のテスト
         */
        @Test
        fun getTrainInformationByTrainInformationId() {
            runBlocking {
                // 3件指定の内、1件は正しい固有識別子別名
                val argument = TrainInformationArgument.ByTrainInformationId(
                    listOf(
                        TrainInformationId("urn:uuid:81dee9e8-9f67-496e-9251-fff49b805bcc"),
                        TrainInformationId("urn:uuid:1b6a8bae-3ee9-499b-ad9c-c127578018a9"),
                        TrainInformationId("odpt.TrainInformation:Toei.Shinjuku")
                    )
                )
                val trainInformationList = apiClient.getTrainInformation(argument)
                assertThat(trainInformationList).isNotEmpty
                // 1路線なので全て平常運転でも1件存在するはず
                assertThat(trainInformationList.size).isEqualTo(1)
            }
        }

        /**
         * 路線IDを指定して取得した場合のテスト
         */
        @Test
        fun getTrainInformationByRailwayId() {
            // 3件中2件は正しい路線ID
            val railwayIdList = listOf(
                RailwayId("odpt.Railway:Toei.Arakawa"),
                RailwayId("odpt.Railway:Toei.Asakusa"),
                RailwayId("invalid")
            )
            runBlocking {
                val argument = TrainInformationArgument.ByRailwayId(railwayIdList = railwayIdList)
                val trainInformationList = apiClient.getTrainInformation(argument)
                assertThat(trainInformationList).isNotEmpty
                // 2路線なので全て平常運転でも2件存在するはず
                assertThat(trainInformationList.size).isEqualTo(2)
            }
        }
    }

    /**
     * getTrainTimetableのテスト
     */
    class TrainTimetableTest : TrainApiClientTest() {
        /**
         * パラメータ無しで取得した場合のテスト
         */
        @Test
        fun getTrainTimetableWithoutParams() {
            runBlocking {
                val trainTimetableList = apiClient.getTrainTimetable()
                // 空でないことだけ確認する
                assertThat(trainTimetableList).isNotEmpty
            }
        }

        /**
         * 固有識別子を指定して取得した場合のテスト
         */
        @Test
        fun getTrainTimetableById() {
            runBlocking {
                // 3件指定の内、2件は正しい固有識別子で1件は固有識別子ではなく固有識別子別名
                val argument = TrainTimetableArgument.ById(
                    listOf(
                        "urn:ucode:_00001C0000000000000100000310B02C",
                        "urn:ucode:_00001C0000000000000100000310AF2F",
                        "odpt.TrainTimetable:Toei.Arakawa.ODPT0060.Saturday"
                    )
                )
                val trainTimetableList = apiClient.getTrainTimetable(argument)
                assertThat(trainTimetableList).isNotEmpty
                assertThat(trainTimetableList.size).isEqualTo(2)
            }
        }

        /**
         * 固有識別子別名を指定して取得した場合のテスト
         */
        @Test
        fun getTrainTimetableByTrainTimetableId() {
            runBlocking {
                // 3件指定の内、1件は正しい固有識別子別名
                val argument = TrainTimetableArgument.ByTrainTimetableId(
                    listOf(
                        TrainTimetableId("urn:ucode:_00001C0000000000000100000310B02C"),
                        TrainTimetableId("urn:ucode:_00001C0000000000000100000310AF2F"),
                        TrainTimetableId("odpt.TrainTimetable:Toei.Arakawa.ODPT0060.Saturday")
                    )
                )
                val trainTimetableList = apiClient.getTrainTimetable(argument)
                assertThat(trainTimetableList).isNotEmpty
                assertThat(trainTimetableList.size).isEqualTo(1)
            }
        }

        /**
         * 路線IDを指定して取得した場合のテスト
         */
        @Test
        fun getTrainTimetableByRailwayId() {
            // 3件中2件は正しい路線ID
            val railwayIdList = listOf(
                RailwayId("odpt.Railway:Toei.Shinjuku"),
                RailwayId("odpt.Railway:Toei.Asakusa"),
                RailwayId("invalid")
            )
            // 前半3件は都営新宿線、前半2件は浅草線
            val trainNumberList = listOf("ODPT0001", "ODPT1317", "ODPT1968", "ODPT0092", "ODPT0093")
            // Expressは新宿線と浅草線の両方に、LimitedExpressは浅草線のみに存在
            val trainTypeIdList =
                listOf(TrainTypeId("odpt.TrainType:Toei.Express"), TrainTypeId("odpt.TrainType:Toei.LimitedExpress"))
            val calendarIdList = listOf(CalendarId("odpt.Calendar:SaturdayHoliday"))
            runBlocking {
                // 路線IDのみ指定
                val argument = TrainTimetableArgument.ByRailwayId(railwayIdList = railwayIdList)
                val trainTimetableList = apiClient.getTrainTimetable(argument)
                // 空でないことだけ確認する
                assertThat(trainTimetableList).isNotEmpty
            }
            runBlocking {
                // 路線IDと列車番号を指定
                val argument = TrainTimetableArgument.ByRailwayId(
                    railwayIdList = listOf(RailwayId("odpt.Railway:Toei.Shinjuku")),
                    trainNumberList = trainNumberList
                )
                val trainTimetableList = apiClient.getTrainTimetable(argument)
                // 空でないことだけ確認する
                assertThat(trainTimetableList).isNotEmpty
            }
            runBlocking {
                // 路線IDと列車種別IDを指定
                val argument = TrainTimetableArgument.ByRailwayId(
                    railwayIdList = listOf(RailwayId("odpt.Railway:Toei.Shinjuku")),
                    trainTypeIdList = trainTypeIdList
                )
                val trainTimetableList = apiClient.getTrainTimetable(argument)
                // 空でないことだけ確認する
                assertThat(trainTimetableList).isNotEmpty
            }
            runBlocking {
                // 路線IDと列車種別IDを指定（存在しない組み合わせ）
                val argument = TrainTimetableArgument.ByRailwayId(
                    railwayIdList = listOf(RailwayId("odpt.Railway:Toei.Shinjuku")),
                    trainTypeIdList = listOf(TrainTypeId("odpt.TrainType:Toei.LimitedExpress"))
                )
                val trainTimetableList = apiClient.getTrainTimetable(argument)
                assertThat(trainTimetableList).isEmpty()
            }
            runBlocking {
                // 路線IDと日付情報IDを指定
                val argument = TrainTimetableArgument.ByRailwayId(
                    railwayIdList = listOf(RailwayId("odpt.Railway:Toei.Shinjuku")),
                    calendarIdList = calendarIdList
                )
                val trainTimetableList = apiClient.getTrainTimetable(argument)
                // 空でないことだけ確認する
                assertThat(trainTimetableList).isNotEmpty
            }
        }

        /**
         * 列車IDを指定して取得した場合のテスト
         */
        @Test
        fun getTrainTimetableByTrainId() {
            // 4件中3件は正しい列車ID
            val trainIdList = listOf(
                TrainId("odpt.Train:Toei.Asakusa.ODPT1866"),
                TrainId("odpt.Train:Toei.Asakusa.ODPT0269"),
                TrainId("odpt.Train:Toei.Shinjuku.ODPT1968"),
                TrainId("invalid")
            )
            val calendarIdList = listOf(CalendarId("odpt.Calendar:SaturdayHoliday"))
            runBlocking {
                // 列車IDのみ指定
                val argument = TrainTimetableArgument.ByTrainId(trainIdList = trainIdList)
                val trainTimetableList = apiClient.getTrainTimetable(argument)
                // 空でないことだけ確認する
                assertThat(trainTimetableList).isNotEmpty
            }
            runBlocking {
                // 路線IDと日付情報IDを指定
                val argument =
                    TrainTimetableArgument.ByTrainId(trainIdList = trainIdList, calendarIdList = calendarIdList)
                val trainTimetableList = apiClient.getTrainTimetable(argument)
                // 空でないことだけ確認する
                assertThat(trainTimetableList).isNotEmpty
            }
        }
    }

    /**
     * getTrainTypeのテスト
     */
    class TrainTypeTest : TrainApiClientTest() {
        /**
         * パラメータ無しで取得した場合のテスト
         */
        @Test
        fun getTrainTypeWithoutParams() {
            runBlocking {
                val trainTypeList = apiClient.getTrainType()
                assertThat(trainTypeList).isNotEmpty
            }
        }

        /**
         * 固有識別子を指定して取得した場合のテスト
         */
        @Test
        fun getTrainTypeById() {
            runBlocking {
                // 3件指定の内、2件は正しい固有識別子で1件は固有識別子ではなく固有識別子別名
                val argument = TrainTypeArgument.ById(
                    listOf(
                        "urn:ucode:_00001C00000000000001000003230794",
                        "urn:ucode:_00001C00000000000001000003230795",
                        "odpt.TrainType:Toei.CommuterLimitedExpress"
                    )
                )
                val trainTypeList = apiClient.getTrainType(argument)
                assertThat(trainTypeList).isNotEmpty
                assertThat(trainTypeList.size).isEqualTo(2)
            }
        }

        /**
         * 固有識別子別名を指定して取得した場合のテスト
         */
        @Test
        fun getTrainTypeByRailDirectionId() {
            runBlocking {
                // 3件指定の内、1件は正しい固有識別子別名
                val argument = TrainTypeArgument.ByTrainTypeId(
                    listOf(
                        TrainTypeId("urn:ucode:_00001C00000000000001000003230794"),
                        TrainTypeId("urn:ucode:_00001C00000000000001000003230795"),
                        TrainTypeId("odpt.TrainType:Toei.CommuterLimitedExpress")
                    )
                )
                val trainTypeList = apiClient.getTrainType(argument)
                assertThat(trainTypeList).isNotEmpty
                assertThat(trainTypeList.size).isEqualTo(1)
            }
        }
    }
}
