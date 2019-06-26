package com.github.teracy.odpt.core.v2.train.response

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.experimental.runners.Enclosed
import org.junit.runner.RunWith

/**
 * v2版国土交通省国土数値情報の鉄道駅情報APIレスポンスのテスト
 */
@RunWith(Enclosed::class)
class MlitStationTest {
    /**
     * デシリアライズのテスト
     */
    class DeserializeTest {
        private val parameterizedType = Types.newParameterizedType(List::class.java, MlitStation::class.java)

        lateinit var jsonAdapter: JsonAdapter<List<MlitStation>>

        @Before
        fun setUp() {
            jsonAdapter = Moshi.Builder().build().adapter(parameterizedType)
        }

        /**
         * 東京メトロオープンデータAPIサンプルデータより
         */
        @Test
        fun tokyoMetroSampleData() {
            // テストデータ
            val json =
                "[\n" +
                        "  {\n" +
                        "    \"@id\": \"urn:ucode:_00001C000000000000010000030BC6A5\",\n" +
                        "    \"@type\": \"mlit:Station\",\n" +
                        "    \"@context\": \"https://vocab.tokyometroapp.jp/context_mlit_Station.jsonld\",\n" +
                        "    \"mlit:stationName\": \"大手町\",\n" +
                        "    \"mlit:railwayName\": \"11号線半蔵門線\",\n" +
                        "    \"mlit:operatorName\": \"東京地下鉄\",\n" +
                        "    \"ug:region\": \"https://api.tokyometroapp.jp/api/v2/places/urn:ucode:_00001C000000000000010000030BC6A5.geojson\"\n" +
                        "  },\n" +
                        "  {\n" +
                        "    \"@id\": \"urn:ucode:_00001C000000000000010000030BDB53\",\n" +
                        "    \"@type\": \"mlit:Station\",\n" +
                        "    \"@context\": \"http://vocab.tokyometroapp.jp/context_mlit_Station.jsonld\",\n" +
                        "    \"mlit:stationName\": \"大手町\",\n" +
                        "    \"mlit:railwayName\": \"4号線丸ノ内線\",\n" +
                        "    \"mlit:operatorName\": \"東京地下鉄\",\n" +
                        "    \"ug:region\": \"https://api.tokyometroapp.jp/api/v2/places/urn:ucode:_00001C000000000000010000030BDB53.geojson\"\n" +
                        "  },\n" +
                        "  {\n" +
                        "    \"@id\": \"urn:ucode:_00001C000000000000010000030BDB94\",\n" +
                        "    \"@type\": \"mlit:Station\",\n" +
                        "    \"@context\": \"http://vocab.tokyometroapp.jp/context_mlit_Station.jsonld\",\n" +
                        "    \"mlit:stationName\": \"大手町\",\n" +
                        "    \"mlit:railwayName\": \"9号線千代田線\",\n" +
                        "    \"mlit:operatorName\": \"東京地下鉄\",\n" +
                        "    \"ug:region\": \"https://api.tokyometroapp.jp/api/v2/places/urn:ucode:_00001C000000000000010000030BDB94.geojson\"\n" +
                        "  },\n" +
                        "  {\n" +
                        "    \"@id\": \"urn:ucode:_00001C000000000000010000030BDB65\",\n" +
                        "    \"@type\": \"mlit:Station\",\n" +
                        "    \"@context\": \"http://vocab.tokyometroapp.jp/context_mlit_Station.jsonld\",\n" +
                        "    \"mlit:stationName\": \"大手町\",\n" +
                        "    \"mlit:railwayName\": \"5号線東西線\",\n" +
                        "    \"mlit:operatorName\": \"東京地下鉄\",\n" +
                        "    \"ug:region\": \"https://api.tokyometroapp.jp/api/v2/places/urn:ucode:_00001C000000000000010000030BDB65.geojson\"\n" +
                        "  }\n" +
                        "]"
            val railwayList = jsonAdapter.fromJson(json)
            assertThat(railwayList).isNotNull
            assertThat(railwayList!!.size).isEqualTo(4)

            val railway0 = railwayList[0]
            assertThat(railway0.id).isEqualTo("urn:ucode:_00001C000000000000010000030BC6A5")
            assertThat(railway0.longitude).isNull()
            assertThat(railway0.latitude).isNull()
            assertThat(railway0.region).isEqualTo("https://api.tokyometroapp.jp/api/v2/places/urn:ucode:_00001C000000000000010000030BC6A5.geojson")
            assertThat(railway0.stationName).isEqualTo("大手町")
            assertThat(railway0.operatorName).isEqualTo("東京地下鉄")
            assertThat(railway0.railwayName).isEqualTo("11号線半蔵門線")
        }
    }
}
