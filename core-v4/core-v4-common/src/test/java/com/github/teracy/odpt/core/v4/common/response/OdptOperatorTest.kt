package com.github.teracy.odpt.core.v4.common.response

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.experimental.runners.Enclosed
import org.junit.runner.RunWith

/**
 * v4版事業者情報APIレスポンスのテスト
 */
@RunWith(Enclosed::class)
class OdptOperatorTest {
    /**
     * デシリアライズのテスト
     */
    class DeserializeTest {
        private val parameterizedType = Types.newParameterizedType(List::class.java, OdptOperator::class.java)

        lateinit var jsonAdapter: JsonAdapter<List<OdptOperator>>

        @Before
        fun setUp() {
            jsonAdapter = Moshi.Builder().build().adapter(parameterizedType)
        }

        /**
         * 東京公共交通オープンデータチャレンジAPIサンプルデータより
         * NOTE:odpt:operatorTitleがodpt:calendarTitleにtypoしていたので修正してある
         */
        @Test
        fun tokyoChallengeSampleData() {
            // テストデータ
            val json =
                "[ {\n" +
                        "  \"@context\" : \"http://vocab.odpt.org/context_odpt.jsonld\",\n" +
                        "  \"@id\" : \"urn:ucode:_00001C000000000000010000030FD7E5\",\n" +
                        "  \"@type\" : \"odpt:Operator\",\n" +
                        "  \"dc:date\" : \"2017-01-13T15:10:00+09:00\",\n" +
                        "  \"owl:sameAs\" : \"odpt.Operator:JR-East\",\n" +
                        "  \"dc:title\" : \"JR東日本\",\n" +
                        "  \"odpt:operatorTitle\" : {\n" +
                        "    \"ja\" : \"JR東日本\",\n" +
                        "    \"en\" : \"JR East\"\n" +
                        "  }\n" +
                        "} ]"
            val operatorList = jsonAdapter.fromJson(json)
            assertThat(operatorList).isNotNull
            assertThat(operatorList!!.size).isEqualTo(1)

            val operator0 = operatorList[0]
            assertThat(operator0.id).isEqualTo("urn:ucode:_00001C000000000000010000030FD7E5")
            assertThat(operator0.date).isEqualTo("2017-01-13T15:10:00+09:00")
            assertThat(operator0.sameAs).isEqualTo("odpt.Operator:JR-East")
            assertThat(operator0.title).isEqualTo("JR東日本")
            assertThat(operator0.operatorTitle).isNotNull
            assertThat(operator0.operatorTitle?.size).isEqualTo(2)
            assertThat(operator0.operatorTitle!!["ja"]).isEqualTo("JR東日本")
            assertThat(operator0.operatorTitle!!["en"]).isEqualTo("JR East")

            assertThat(operator0.operatorId.id).isEqualTo("odpt.Operator:JR-East")
        }
    }
}
