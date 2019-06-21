package com.github.teracy.odpt.core.v4.train.response

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.experimental.runners.Enclosed
import org.junit.runner.RunWith

/**
 * v4版路線進行方向情報APIレスポンスのテスト
 */
@RunWith(Enclosed::class)
class OdptRailDirectionTest {
    /**
     * デシリアライズのテスト
     */
    class DeserializeTest {
        private val parameterizedType = Types.newParameterizedType(List::class.java, OdptRailDirection::class.java)

        lateinit var jsonAdapter: JsonAdapter<List<OdptRailDirection>>

        @Before
        fun setUp() {
            jsonAdapter = Moshi.Builder().build().adapter(parameterizedType)
        }

        /**
         * 東京公共交通オープンデータチャレンジAPIサンプルデータより
         */
        @Test
        fun tokyoChallengeSampleData() {
            // テストデータ
            val json =
                "[ {\n" +
                        "  \"@context\" : \"http://vocab.odpt.org/context_odpt.jsonld\",\n" +
                        "  \"@id\" : \"urn:ucode:_00001C000000000000010000030FD7E5\",\n" +
                        "  \"@type\" : \"odpt:RailDirection\",\n" +
                        "  \"dc:date\" : \"2017-01-13T06:10:00+0000\",\n" +
                        "  \"owl:sameAs\" : \"odpt.RailDirection:Inbound\",\n" +
                        "  \"dc:title\" : \"上り\",\n" +
                        "  \"odpt:railDirectionTitle\" : {\n" +
                        "    \"ja\" : \"上り\",\n" +
                        "    \"en\" : \"Inbound\"\n" +
                        "  }\n" +
                        "} ]"
            val railDirectionList = jsonAdapter.fromJson(json)
            assertThat(railDirectionList).isNotNull
            assertThat(railDirectionList!!.size).isEqualTo(1)

            val railDirection0 = railDirectionList[0]
            assertThat(railDirection0.id).isEqualTo("urn:ucode:_00001C000000000000010000030FD7E5")
            assertThat(railDirection0.date).isEqualTo("2017-01-13T06:10:00+0000")
            assertThat(railDirection0.sameAs).isEqualTo("odpt.RailDirection:Inbound")
            assertThat(railDirection0.title).isEqualTo("上り")
            assertThat(railDirection0.titleMap).isNotNull
            assertThat(railDirection0.titleMap!!.size).isEqualTo(2)
            assertThat(railDirection0.titleMap!!["ja"]).isEqualTo("上り")
            assertThat(railDirection0.titleMap!!["en"]).isEqualTo("Inbound")

            assertThat(railDirection0.railDirectionId.id).isEqualTo("odpt.RailDirection:Inbound")
        }
    }
}
