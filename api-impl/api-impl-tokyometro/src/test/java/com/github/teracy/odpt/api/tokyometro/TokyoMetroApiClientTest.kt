package com.github.teracy.odpt.api.tokyometro

import com.github.teracy.odpt.api.tokyometro.support.Operators
import com.github.teracy.odpt.api.tokyometro.support.Railways
import com.github.teracy.odpt.core.v2.train.request.*
import com.github.teracy.odpt.testutil.ApiClientTest
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.assertj.core.api.Assertions.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test
import java.util.concurrent.TimeUnit


/**
 * 東京メトロオープンデータAPI向けクライアント実装のテスト
 */
class TokyoMetroApiClientTest : ApiClientTest {
    private val operatorId = Operators.TOKYO_METRO.operatorId
    private val railwayId = Railways.TOKYO_METRO_GINZA.railwayId
    private lateinit var consumerKey: String
    private lateinit var okHttpClient: OkHttpClient
    private lateinit var apiClient: TokyoMetroApiClient

    companion object {
        // 東京駅から半径500mで検索する
        private const val LONGITUDE = 139.767125
        private const val LATITUDE = 35.681236
        private const val RADIUS = 500.0
    }

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

        apiClient = TokyoMetroApiClient(okHttpClient, consumerKey)
    }

    @After
    fun tearDown() {
        // 他のテストで一気に流したら429が返ってきてしまったので、ひとまず1テストあたり1000msecのインターバルをかませる
        Thread.sleep(1000)
    }

    /**
     * getStationTimetableのテスト
     */
    @Test
    fun getStationTimetable() {
        runBlocking {
            // 東京メトロは唯一の事業者なので、どのAPIでも必ず取得できる
            val stationTimetableList = apiClient.getStationTimetable(StationTimetableArgument.ByRailwayId(railwayId))
            assertThat(stationTimetableList).isNotEmpty
        }
    }

    /**
     * getTrainInformationのテスト
     */
    @Test
    fun getTrainInformation() {
        runBlocking {
            // 東京メトロは唯一の事業者なので、どのAPIでも必ず取得できる
            val trainInformationList = apiClient.getTrainInformation(TrainInformationArgument.ByRailwayId(railwayId))
            assertThat(trainInformationList).isNotEmpty
        }
    }

    /**
     * getTrainのテスト
     */
    @Test
    fun getTrain() {
        runBlocking {
            // 東京メトロは唯一の事業者なので、どのAPIでも必ず取得できる
            val trainList = apiClient.getTrain(TrainArgument.ByRailwayId(railwayId))
            assertThat(trainList).isNotEmpty
        }
    }

    /**
     * getStationのテスト（データ検索API）
     */
    @Test
    fun getStation() {
        runBlocking {
            // 東京メトロは唯一の事業者なので、どのAPIでも必ず取得できる
            val stationList = apiClient.getStation(StationArgument.ByRailwayId(railwayId))
            assertThat(stationList).isNotEmpty
        }
    }

    /**
     * getStationのテスト（地物情報検索API）
     */
    @Test
    fun getStationByPoi() {
        runBlocking {
            // 東京駅なので必ず存在するはず
            val stationList = apiClient.getStation(longitude = LONGITUDE, latitude = LATITUDE, radius = RADIUS)
            assertThat(stationList).isNotEmpty
        }
    }

    /**
     * getStationFacilityのテスト
     */
    @Test
    fun getStationFacility() {
        runBlocking {
            // これのみ固有識別子のみを引数に取るため、直接TrainDataPointApiClientのメソッドを呼んでいる
            val stationFacilityList = apiClient.getStationFacility()
            assertThat(stationFacilityList).isNotEmpty
        }
    }

    /**
     * getPassengerSurveyのテスト
     */
    @Test
    fun getPassengerSurvey() {
        runBlocking {
            // 東京メトロは唯一の事業者なので、どのAPIでも必ず取得できる
            val passengerSurveyList = apiClient.getPassengerSurvey(PassengerSurveyArgument.ByOperatorId(operatorId))
            assertThat(passengerSurveyList).isNotEmpty
        }
    }

    /**
     * getRailwayのテスト（データ検索API）
     */
    @Test
    fun getRailway() {
        runBlocking {
            // 東京メトロは唯一の事業者なので、どのAPIでも必ず取得できる
            val railwayList = apiClient.getRailway(RailwayArgument.ByRailwayId(railwayId))
            assertThat(railwayList).isNotEmpty
        }
    }

    /**
     * getRailwayのテスト（地物情報検索API）
     */
    @Test
    fun getRailwayByPoi() {
        runBlocking {
            // 東京駅なので必ず存在するはず
            val railwayList = apiClient.getRailway(longitude = LONGITUDE, latitude = LATITUDE, radius = RADIUS)
            assertThat(railwayList).isNotEmpty
        }
    }

    /**
     * getRailwayFareのテスト
     */
    @Test
    fun getRailwayFare() {
        runBlocking {
            // 東京メトロは唯一の事業者なので、どのAPIでも必ず取得できる
            val railwayFareList = apiClient.getRailwayFare(RailwayFareArgument.ByOperatorId(operatorId))
            assertThat(railwayFareList).isNotEmpty
        }
    }

    /**
     * getTrainTimetableのテスト
     */
    @Test
    fun getTrainTimetable() {
        runBlocking {
            // 東京メトロは唯一の事業者なので、どのAPIでも必ず取得できる
            val trainTimetableList = apiClient.getTrainTimetable(TrainTimetableArgument.ByRailwayId(railwayId))
            assertThat(trainTimetableList).isNotEmpty
        }
    }

    /**
     * getUgPoiのテスト（データ検索API）
     */
    @Test
    fun getUgPoi() {
        runBlocking {
            val ugPoiList = apiClient.getUgPoi()
            assertThat(ugPoiList).isNotEmpty
        }
    }

    /**
     * getUgPoiのテスト（地物情報検索API）
     */
    @Test
    fun getUgPoiByPoi() {
        runBlocking {
            // 東京駅なので必ず存在するはず
            val ugPoiList = apiClient.getUgPoi(longitude = LONGITUDE, latitude = LATITUDE, radius = RADIUS)
            assertThat(ugPoiList).isNotEmpty
        }
    }

    /**
     * getMlitStationのテスト
     */
    @Test
    fun getMlitStation() {
        runBlocking {
            // 東京駅なので必ず存在するはず
            val mlitStationList = apiClient.getMlitStation(longitude = LONGITUDE, latitude = LATITUDE, radius = RADIUS)
            assertThat(mlitStationList).isNotEmpty
        }
    }

    /**
     * getMlitRailwayのテスト
     */
    @Test
    fun getMlitRailway() {
        runBlocking {
            // 東京駅なので必ず存在するはず
            val mlitRailwayList = apiClient.getMlitRailway(longitude = LONGITUDE, latitude = LATITUDE, radius = RADIUS)
            assertThat(mlitRailwayList).isNotEmpty
        }
    }

    /**
     * getGeometryのテスト
     */
    @Test
    fun getGeometry() {
        runBlocking {
            // 正常ケース：URLは東京メトロオープンデータサンプルデータを元に補完したもの
            val geometry =
                apiClient.getGeometry("https://api.tokyometroapp.jp/api/v2/places/urn:ucode:_00001C000000000000010000030C4406.geojson")
            assertThat(geometry).isNotEmpty
            assertThat(geometry.size).isEqualTo(1)
        }
        runBlocking {
            // 異常ケース：URLは東京メトロオープンデータサンプルデータそのまま（正しくない）
            val geometry =
                apiClient.getGeometry("https://api.tokyometroapp.jp/api/v2/places/ucode_00001C000000000000010000030C4406")
            assertThat(geometry).isEmpty()
        }
    }
}
