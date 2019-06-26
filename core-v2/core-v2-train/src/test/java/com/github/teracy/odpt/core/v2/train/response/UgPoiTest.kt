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
 * v2版駅出入口情報APIレスポンスのテスト
 */
@RunWith(Enclosed::class)
class UgPoiTest {
    /**
     * デシリアライズのテスト
     */
    class DeserializeTest {
        private val parameterizedType = Types.newParameterizedType(List::class.java, UgPoi::class.java)

        lateinit var jsonAdapter: JsonAdapter<List<UgPoi>>

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
                        "    \"@id\": \"urn:ucode:_00001C000000000000010000030C44FA\",\n" +
                        "    \"@type\": \"ug:Poi\",\n" +
                        "    \"dc:title\": \"北参道出入口1\",\n" +
                        "    \"ug:region\": \"https://api.tokyometroapp.jp/api/v2/places/urn:ucode:_00001C000000000000010000030C44FA.geojson\",\n" +
                        "    \"ug:floor\": 1,\n" +
                        "    \"geo:lat\": 35.67926581033,\n" +
                        "    \"geo:long\": 139.70518750253,\n" +
                        "    \"ugsrv:categoryName\": \"出入口\",\n" +
                        "    \"@context\": \"http://vocab.tokyometroapp.jp/context_ug_Poi.jsonld\"\n" +
                        "  },\n" +
                        "  {\n" +
                        "    \"@id\": \"urn:ucode:_00001C000000000000010000030C44FB\",\n" +
                        "    \"@type\": \"ug:Poi\",\n" +
                        "    \"dc:title\": \"北参道出入口2\",\n" +
                        "    \"ug:region\": \"https://api.tokyometroapp.jp/api/v2/places/urn:ucode:_00001C000000000000010000030C44FB.geojson\",\n" +
                        "    \"ug:floor\": 1,\n" +
                        "    \"geo:lat\": 35.677697957679,\n" +
                        "    \"geo:long\": 139.70615692221,\n" +
                        "    \"ugsrv:categoryName\": \"出入口\",\n" +
                        "    \"@context\": \"http://vocab.tokyometroapp.jp/context_ug_Poi.jsonld\"\n" +
                        "  }\n" +
                        "]"
            val ugPoiList = jsonAdapter.fromJson(json)
            assertThat(ugPoiList).isNotNull
            assertThat(ugPoiList!!.size).isEqualTo(2)

            val ugPoi0 = ugPoiList[0]
            assertThat(ugPoi0.id).isEqualTo("urn:ucode:_00001C000000000000010000030C44FA")
            assertThat(ugPoi0.longitude).isEqualTo(139.70518750253)
            assertThat(ugPoi0.latitude).isEqualTo(35.67926581033)
            assertThat(ugPoi0.region).isEqualTo("https://api.tokyometroapp.jp/api/v2/places/urn:ucode:_00001C000000000000010000030C44FA.geojson")
            assertThat(ugPoi0.floor).isEqualTo(1.0)
            assertThat(ugPoi0.title).isEqualTo("北参道出入口1")
            assertThat(ugPoi0.categoryName).isEqualTo("出入口")
        }
    }
}
