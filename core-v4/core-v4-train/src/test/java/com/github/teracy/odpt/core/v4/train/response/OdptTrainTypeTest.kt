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
 * v4版列車種別情報APIレスポンスのテスト
 */
@RunWith(Enclosed::class)
class OdptTrainTypeTest {
    /**
     * デシリアライズのテスト
     */
    class DeserializeTest {
        private val parameterizedType = Types.newParameterizedType(List::class.java, OdptTrainType::class.java)

        lateinit var jsonAdapter: JsonAdapter<List<OdptTrainType>>

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
                        "  \"@type\" : \"odpt:TrainType\",\n" +
                        "  \"dc:date\" : \"2017-01-13T15:10:00+09:00\",\n" +
                        "  \"owl:sameAs\" : \"odpt.TrainType:JR-East.Local\",\n" +
                        "  \"odpt:operator\" : \"odpt.Operator:JR-East\",\n" +
                        "  \"dc:title\" : \"普通\",\n" +
                        "  \"odpt:trainTypeTitle\" : {\n" +
                        "    \"ja\" : \"普通\",\n" +
                        "    \"en\" : \"Local\"\n" +
                        "  }\n" +
                        "} ]"
            val trainTypeList = jsonAdapter.fromJson(json)
            assertThat(trainTypeList).isNotNull
            assertThat(trainTypeList!!.size).isEqualTo(1)

            val trainType0 = trainTypeList[0]
            assertThat(trainType0.id).isEqualTo("urn:ucode:_00001C000000000000010000030FD7E5")
            assertThat(trainType0.date).isEqualTo("2017-01-13T15:10:00+09:00")
            assertThat(trainType0.sameAs).isEqualTo("odpt.TrainType:JR-East.Local")
            assertThat(trainType0.operator).isEqualTo("odpt.Operator:JR-East")
            assertThat(trainType0.title).isEqualTo("普通")
            assertThat(trainType0.titleMap).isNotNull
            assertThat(trainType0.titleMap!!.size).isEqualTo(2)
            assertThat(trainType0.titleMap!!["ja"]).isEqualTo("普通")
            assertThat(trainType0.titleMap!!["en"]).isEqualTo("Local")

            assertThat(trainType0.trainTypeId.id).isEqualTo("odpt.TrainType:JR-East.Local")
            assertThat(trainType0.operatorId.id).isEqualTo("odpt.Operator:JR-East")
        }
    }
}
