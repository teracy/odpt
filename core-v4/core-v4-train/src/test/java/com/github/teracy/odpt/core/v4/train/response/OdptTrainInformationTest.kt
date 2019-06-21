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
 * v4版列車運行情報APIレスポンスのテスト
 */
@RunWith(Enclosed::class)
class OdptTrainInformationTest {
    /**
     * デシリアライズのテスト
     */
    class DeserializeTest {
        private val parameterizedType = Types.newParameterizedType(List::class.java, OdptTrainInformation::class.java)

        lateinit var jsonAdapter: JsonAdapter<List<OdptTrainInformation>>

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
                        "  \"@context\" : \"http://vocab.odpt.org/context_odpt.json\",\n" +
                        "  \"@id\" : \"urn:ucode:_00001C000000000000010000030C3BE4\",\n" +
                        "  \"@type\" : \"odpt:TrainInformation\",\n" +
                        "  \"dc:date\" : \"2017-12-07T01:25:03+09:00\",\n" +
                        "  \"owl:sameAs\" : \"odpt.TrainInformation:TokyoMetro.Ginza\",\n" +
                        "  \"dct:valid\" : \"2017-12-07T01:30:03+09:00\",\n" +
                        "  \"odpt:timeOfOrigin\" : \"2017-11-21T11:31:00+09:00\",\n" +
                        "  \"odpt:operator\" : \"odpt.Operator:TokyoMetro\",\n" +
                        "  \"odpt:railway\" : \"odpt.Railway:TokyoMetro.Ginza\",\n" +
                        "  \"odpt:trainInformationText\" : {\n" +
                        "    \"ja\" : \"現在、平常どおり運転しています。\",\n" +
                        "    \"en\" : \"Running on schedule.\"\n" +
                        "  }\n" +
                        "} ]"
            val trainInformationList = jsonAdapter.fromJson(json)
            assertThat(trainInformationList).isNotNull
            assertThat(trainInformationList!!.size).isEqualTo(1)

            val trainInformation0 = trainInformationList[0]
            assertThat(trainInformation0.id).isEqualTo("urn:ucode:_00001C000000000000010000030C3BE4")
            assertThat(trainInformation0.date).isEqualTo("2017-12-07T01:25:03+09:00")
            assertThat(trainInformation0.valid).isEqualTo("2017-12-07T01:30:03+09:00")
            assertThat(trainInformation0.sameAs).isEqualTo("odpt.TrainInformation:TokyoMetro.Ginza")
            assertThat(trainInformation0.timeOfOrigin).isEqualTo("2017-11-21T11:31:00+09:00")
            assertThat(trainInformation0.operator).isEqualTo("odpt.Operator:TokyoMetro")
            assertThat(trainInformation0.railway).isEqualTo("odpt.Railway:TokyoMetro.Ginza")
            assertThat(trainInformation0.trainInformationStatus).isNull()
            assertThat(trainInformation0.trainInformationText).isNotNull
            assertThat(trainInformation0.trainInformationText!!.size).isEqualTo(2)
            assertThat(trainInformation0.trainInformationText!!["ja"]).isEqualTo("現在、平常どおり運転しています。")
            assertThat(trainInformation0.trainInformationText!!["en"]).isEqualTo("Running on schedule.")
            assertThat(trainInformation0.railDirection).isNull()
            assertThat(trainInformation0.trainInformationArea).isNull()
            assertThat(trainInformation0.trainInformationKind).isNull()
            assertThat(trainInformation0.fromStation).isNull()
            assertThat(trainInformation0.toStation).isNull()
            assertThat(trainInformation0.trainInformationRange).isNull()
            assertThat(trainInformation0.trainInformationCause).isNull()
            assertThat(trainInformation0.transferRailwayList).isNull()
            assertThat(trainInformation0.resumeEstimate).isNull()

            assertThat(trainInformation0.trainInformationId.id).isEqualTo("odpt.TrainInformation:TokyoMetro.Ginza")
            assertThat(trainInformation0.operatorId.id).isEqualTo("odpt.Operator:TokyoMetro")
            assertThat(trainInformation0.railwayId).isNotNull
            assertThat(trainInformation0.railwayId!!.id).isEqualTo("odpt.Railway:TokyoMetro.Ginza")
            assertThat(trainInformation0.railDirectionId).isNull()
            assertThat(trainInformation0.fromStationId).isNull()
            assertThat(trainInformation0.toStationId).isNull()
            assertThat(trainInformation0.transferRailwayIdList).isNull()
        }
    }
}
