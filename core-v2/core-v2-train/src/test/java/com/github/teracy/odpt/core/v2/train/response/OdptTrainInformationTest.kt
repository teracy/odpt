package com.github.teracy.odpt.core.v2.train.response

import com.github.teracy.odpt.testutil.ApiResponseTest
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.experimental.runners.Enclosed
import org.junit.runner.RunWith

/**
 * v2版列車運行情報APIレスポンスのテスト
 */
@RunWith(Enclosed::class)
class OdptTrainInformationTest {
    /**
     * デシリアライズのテスト
     */
    class DeserializeTest : ApiResponseTest {
        private val parameterizedType = Types.newParameterizedType(List::class.java, OdptTrainInformation::class.java)

        lateinit var jsonAdapter: JsonAdapter<List<OdptTrainInformation>>

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
                        "    \"@context\": \"http://vocab.tokyometroapp.jp/context_odpt_TrainInformation.jsonld\",\n" +
                        "    \"@id\": \"urn:ucode:_00001C000000000000010000030C3BE6\",\n" +
                        "    \"dc:date\": \"2014-09-08T22:50:02+09:00\",\n" +
                        "    \"dct:valid\": \"2014-09-08T22:55:02+09:00\",\n" +
                        "    \"odpt:operator\": \"odpt.Operator:TokyoMetro\",\n" +
                        "    \"odpt:railway\": \"odpt.Railway:TokyoMetro.Hibiya\",\n" +
                        "    \"odpt:timeOfOrigin\": \"2014-08-30T23:30:00+09:00\",\n" +
                        "    \"odpt:trainInformationText\": \"現在、平常どおり運転しています。\",\n" +
                        "    \"@type\": \"odpt:TrainInformation\"\n" +
                        "  }\n" +
                        "]"
            val trainInformationList = jsonAdapter.fromJson(json)
            assertThat(trainInformationList).isNotNull
            assertThat(trainInformationList!!.size).isEqualTo(1)

            val trainInformation0 = trainInformationList[0]
            assertThat(trainInformation0.id).isEqualTo("urn:ucode:_00001C000000000000010000030C3BE6")
            assertThat(trainInformation0.date).isEqualTo("2014-09-08T22:50:02+09:00")
            assertThat(trainInformation0.valid).isEqualTo("2014-09-08T22:55:02+09:00")
            assertThat(trainInformation0.operator).isEqualTo("odpt.Operator:TokyoMetro")
            assertThat(trainInformation0.timeOfOrigin).isEqualTo("2014-08-30T23:30:00+09:00")
            assertThat(trainInformation0.railway).isEqualTo("odpt.Railway:TokyoMetro.Hibiya")
            assertThat(trainInformation0.trainInformationStatus).isNull()
            assertThat(trainInformation0.trainInformationText).isEqualTo("現在、平常どおり運転しています。")

            assertThat(trainInformation0.operatorId.id).isEqualTo("odpt.Operator:TokyoMetro")
            assertThat(trainInformation0.railwayId.id).isEqualTo("odpt.Railway:TokyoMetro.Hibiya")
        }

        /**
         * 東京メトロオープンデータAPIサンプルURLより（日比谷線）
         * https://api.tokyometroapp.jp/api/v2/datapoints?rdf:type=odpt:TrainInformation&odpt:railway=odpt.Railway:TokyoMetro.Hibiya&acl:consumerKey=ACL_CONSUMERKEY
         */
        @Test
        fun tokyoMetroHibiya() {
            // テストデータ
            val json = readJsonFile("TrainInformationHibiya.json")
            val trainInformationList = jsonAdapter.fromJson(json)
            assertThat(trainInformationList).isNotNull
            assertThat(trainInformationList!!.size).isEqualTo(1)

            val trainInformation0 = trainInformationList[0]
            assertThat(trainInformation0.id).isEqualTo("urn:ucode:_00001C000000000000010000030C3BE6")
            assertThat(trainInformation0.date).isEqualTo("2019-06-06T15:55:03+09:00")
            assertThat(trainInformation0.valid).isEqualTo("2019-06-06T16:00:03+09:00")
            assertThat(trainInformation0.operator).isEqualTo("odpt.Operator:TokyoMetro")
            assertThat(trainInformation0.timeOfOrigin).isEqualTo("2019-06-01T00:21:00+09:00")
            assertThat(trainInformation0.railway).isEqualTo("odpt.Railway:TokyoMetro.Hibiya")
            assertThat(trainInformation0.trainInformationStatus).isNull()
            assertThat(trainInformation0.trainInformationText).isEqualTo("現在、平常どおり運転しています。")

            assertThat(trainInformation0.operatorId.id).isEqualTo("odpt.Operator:TokyoMetro")
            assertThat(trainInformation0.railwayId.id).isEqualTo("odpt.Railway:TokyoMetro.Hibiya")
        }
    }
}
