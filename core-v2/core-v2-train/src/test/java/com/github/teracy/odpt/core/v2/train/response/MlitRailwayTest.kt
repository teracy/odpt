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
 * v2版国土交通省国土数値情報の鉄道路線情報APIレスポンスのテスト
 */
@RunWith(Enclosed::class)
class MlitRailwayTest {
    /**
     * デシリアライズのテスト
     */
    class DeserializeTest {
        private val parameterizedType = Types.newParameterizedType(List::class.java, MlitRailway::class.java)

        lateinit var jsonAdapter: JsonAdapter<List<MlitRailway>>

        @Before
        fun setUp() {
            jsonAdapter = Moshi.Builder().build().adapter(parameterizedType)
        }

        /**
         * 東京メトロオープンデータAPIサンプルデータより
         * NOTE:mlit:railwayNameがmlit:raiwayNameにtypoしていたため修正してある（実データで確認済み）
         */
        @Test
        fun tokyoMetroSampleData() {
            // テストデータ
            val json =
                "[\n" +
                        "  {\n" +
                        "    \"@id\": \"urn:ucode:_00001C000000000000010000030BBBAB\",\n" +
                        "    \"@type\": \"mlit:Railway\",\n" +
                        "    \"@context\": \"http://vocab.tokyometroapp.jp/context_mlit_Railway.jsonld\",\n" +
                        "    \"mlit:railwayName\": \"11号線半蔵門線\",\n" +
                        "    \"mlit:operatorName\": \"東京地下鉄\",\n" +
                        "    \"ug:region\": \"https://api.tokyometroapp.jp/api/v2/places/urn:ucode:_00001C000000000000010000030BBBAB.geojson\"\n" +
                        "  },\n" +
                        "  {\n" +
                        "    \"@id\": \"urn:ucode:_00001C000000000000010000030BBCD0\",\n" +
                        "    \"@type\": \"mlit:Railway\",\n" +
                        "    \"@context\": \"http://vocab.tokyometroapp.jp/context_mlit_Railway.jsonld\",\n" +
                        "    \"mlit:railwayName\": \"9号線千代田線\",\n" +
                        "    \"mlit:operatorName\": \"東京地下鉄\",\n" +
                        "    \"ug:region\": \"https://api.tokyometroapp.jp/api/v2/places/urn:ucode:_00001C000000000000010000030BBCD0.geojson\"\n" +
                        "  },\n" +
                        "  {\n" +
                        "    \"@id\": \"urn:ucode:_00001C000000000000010000030BBCCA\",\n" +
                        "    \"@type\": \"mlit:Railway\",\n" +
                        "    \"@context\": \"http://vocab.tokyometroapp.jp/context_mlit_Railway.jsonld\",\n" +
                        "    \"mlit:railwayName\": \"4号線丸ノ内線\",\n" +
                        "    \"mlit:operatorName\": \"東京地下鉄\",\n" +
                        "    \"ug:region\": \"https://api.tokyometroapp.jp/api/v2/places/urn:ucode:_00001C000000000000010000030BBCCA.geojson\"\n" +
                        "  },\n" +
                        "  {\n" +
                        "    \"@id\": \"urn:ucode:_00001C000000000000010000030BBCD7\",\n" +
                        "    \"@type\": \"mlit:Railway\",\n" +
                        "    \"@context\": \"http://vocab.tokyometroapp.jp/context_mlit_Railway.jsonld\",\n" +
                        "    \"mlit:railwayName\": \"6号線三田線\",\n" +
                        "    \"mlit:operatorName\": \"東京都\",\n" +
                        "    \"ug:region\": \"https://api.tokyometroapp.jp/api/v2/places/urn:ucode:_00001C000000000000010000030BBCD7.geojson\"\n" +
                        "  },\n" +
                        "  {\n" +
                        "    \"@id\": \"urn:ucode:_00001C000000000000010000030BBCCC\",\n" +
                        "    \"@type\": \"mlit:Railway\",\n" +
                        "    \"@context\": \"http://vocab.tokyometroapp.jp/context_mlit_Railway.jsonld\",\n" +
                        "    \"mlit:railwayName\": \"5号線東西線\",\n" +
                        "    \"mlit:operatorName\": \"東京地下鉄\",\n" +
                        "    \"ug:region\": \"https://api.tokyometroapp.jp/api/v2/places/urn:ucode:_00001C000000000000010000030BBCCC.geojson\"\n" +
                        "  }\n" +
                        "]"
            val railwayList = jsonAdapter.fromJson(json)
            assertThat(railwayList).isNotNull
            assertThat(railwayList!!.size).isEqualTo(5)

            val railway0 = railwayList[0]
            assertThat(railway0.id).isEqualTo("urn:ucode:_00001C000000000000010000030BBBAB")
            assertThat(railway0.longitude).isNull()
            assertThat(railway0.latitude).isNull()
            assertThat(railway0.region).isEqualTo("https://api.tokyometroapp.jp/api/v2/places/urn:ucode:_00001C000000000000010000030BBBAB.geojson")
            assertThat(railway0.operatorName).isEqualTo("東京地下鉄")
            assertThat(railway0.railwayName).isEqualTo("11号線半蔵門線")
        }
    }
}
