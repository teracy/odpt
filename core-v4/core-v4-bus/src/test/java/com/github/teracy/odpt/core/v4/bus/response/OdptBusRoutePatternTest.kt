package com.github.teracy.odpt.core.v4.bus.response

import com.github.teracy.odpt.core.geojson.adapter.GeometryAdapter
import com.github.teracy.odpt.core.geojson.response.LineString
import com.github.teracy.odpt.model.adapter.ArrayOrObjectAdapter
import com.github.teracy.odpt.testutil.ApiResponseTest
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.experimental.runners.Enclosed
import org.junit.runner.RunWith

@RunWith(Enclosed::class)
class OdptBusRoutePatternTest {
    /**
     * デシリアライズのテスト
     */
    class DeserializeTest : ApiResponseTest {
        private val parameterizedType = Types.newParameterizedType(List::class.java, OdptBusRoutePattern::class.java)

        lateinit var jsonAdapter: JsonAdapter<List<OdptBusRoutePattern>>

        @Before
        fun setUp() {
            jsonAdapter = Moshi.Builder()
                .add(ArrayOrObjectAdapter.FACTORY)
                .add(GeometryAdapter.FACTORY)
                .build()
                .adapter(parameterizedType)
        }

        // NOTE: 東京公共交通オープンデータチャレンジAPIサンプルデータは必須要件のものすら備えておらず、typoも多いため利用困難と判断した

        /**
         * 実データ（東京都交通局梅77甲より）
         * NOTE: 東京公共交通オープンデータチャレンジAPIサンプルデータが使い物にならないため、実データで確かめた
         */
        @Test
        fun toei_Ume77Kou_28603_2() {
            // テストデータ
            val json = readJsonFile("BusRoutePattern_Toei_Ume77Kou_28603_2.json")
            val busRoutePatternList = jsonAdapter.fromJson(json)
            assertThat(busRoutePatternList).isNotNull
            assertThat(busRoutePatternList!!.size).isEqualTo(1)

            val busRoutePattern0 = busRoutePatternList[0]
            assertThat(busRoutePattern0.id).isEqualTo("urn:ucode:_00001C0000000000000100000322BB7E")
            assertThat(busRoutePattern0.sameAs).isEqualTo("odpt.BusroutePattern:Toei.Ume77Kou.28603.2")
            assertThat(busRoutePattern0.date).isEqualTo("2019-05-31T03:04:58+09:00")
            assertThat(busRoutePattern0.valid).isNull()
            assertThat(busRoutePattern0.title).isEqualTo("梅７７甲 青梅駅前行")
            assertThat(busRoutePattern0.kana).isNull()

            // NOTE: APIではArrayで定義されているが東京都交通局実データではObject
            assertThat(busRoutePattern0.operatorList.size).isEqualTo(1)
            assertThat(busRoutePattern0.operatorList[0]).isEqualTo("odpt.Operator:Toei")

            assertThat(busRoutePattern0.busRoute).isEqualTo("odpt.Busroute:Toei.Ume77Kou")
            assertThat(busRoutePattern0.pattern).isEqualTo("28603")
            assertThat(busRoutePattern0.direction).isEqualTo("2")

            assertThat(busRoutePattern0.region).isNotNull
            assertThat(busRoutePattern0.region?.javaClass).isEqualTo(LineString::class.java)
            val lineString = busRoutePattern0.region as LineString
            // 外側のリストのサイズ（＝座標の数）は67のはず
            assertThat(lineString.coordinates.size).isEqualTo(67)
            // 内側のリストのサイズ（＝座標）は2のはず
            assertThat(lineString.coordinates[0].size).isEqualTo(2)
            // 内側のリストから抽出
            // 0番目の経度
            assertThat(lineString.coordinates[0][0]).isEqualTo(139.28513)
            // 0番目の緯度
            assertThat(lineString.coordinates[0][1]).isEqualTo(35.78465)

            assertThat(busRoutePattern0.busStopPoleOrder).isNotNull
            assertThat(busRoutePattern0.busStopPoleOrder).isNotEmpty
            assertThat(busRoutePattern0.busStopPoleOrder!!.size).isEqualTo(22)

            val busStopPoleOrder0 = busRoutePattern0.busStopPoleOrder!![0]
            assertThat(busStopPoleOrder0.busStopPole).isEqualTo("odpt.BusstopPole:Toei.KabeStationKitaguchi.338.1")
            assertThat(busStopPoleOrder0.index).isEqualTo(1)
            assertThat(busStopPoleOrder0.note).isEqualTo("河辺駅北口")
            assertThat(busStopPoleOrder0.openingDoorsToGetOnList).isNull()
            assertThat(busStopPoleOrder0.openingDoorsToGetOffList).isNull()
            assertThat(busStopPoleOrder0.busStopPoleId.id).isEqualTo("odpt.BusstopPole:Toei.KabeStationKitaguchi.338.1")

            assertThat(busRoutePattern0.note).isEqualTo("梅７７甲:河辺駅北口→青梅駅前:28603:2")
            assertThat(busRoutePattern0.busLocationUrl).isNull()

            assertThat(busRoutePattern0.busRoutePatternId.id).isEqualTo("odpt.BusroutePattern:Toei.Ume77Kou.28603.2")

            // NOTE: APIではArrayで定義されているが東京都交通局実データではObject
            assertThat(busRoutePattern0.operatorIdList.size).isEqualTo(1)
            assertThat(busRoutePattern0.operatorIdList[0].id).isEqualTo("odpt.Operator:Toei")

            assertThat(busRoutePattern0.busRouteId).isNotNull
            assertThat(busRoutePattern0.busRouteId!!.id).isEqualTo("odpt.Busroute:Toei.Ume77Kou")
        }
    }
}
