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
 * v4版列車ロケーション情報APIレスポンスのテスト
 */
@RunWith(Enclosed::class)
class OdptTrainTest {
    /**
     * デシリアライズのテスト
     */
    class DeserializeTest {
        private val parameterizedType = Types.newParameterizedType(List::class.java, OdptTrain::class.java)

        lateinit var jsonAdapter: JsonAdapter<List<OdptTrain>>

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
                        "  \"@id\" : \"urn:uuid:5d7dd592-ef17-4b69-b955-5b4fe5f7e350\",\n" +
                        "  \"@type\" : \"odpt:Train\",\n" +
                        "  \"dc:date\" : \"2017-12-07T01:24:33+09:00\",\n" +
                        "  \"owl:sameAs\" : \"odpt.Train:JR-East.Utsunomiya.565M\",\n" +
                        "  \"odpt:operator\" : \"odpt.Operator:JR-East\",\n" +
                        "  \"odpt:railway\" : \"odpt.Railway:JR-East.Utsunomiya\",\n" +
                        "  \"odpt:railDirection\" : \"odpt.RailwayDirection:Outbound\",\n" +
                        "  \"odpt:trainNumber\" : \"565M\",\n" +
                        "  \"odpt:fromStation\" : \"odpt.Station:JR-East.Utsunomiya.Suzumenomiya\",\n" +
                        "  \"odpt:toStation\" : \"odpt.Station:JR-East.Utsunomiya.Utsunomiya\",\n" +
                        "  \"odpt:destinationStation\" : [ \"odpt.Station:JR-East.Utsunomiya.Utsunomiya\" ],\n" +
                        "  \"odpt:index\" : 1,\n" +
                        "  \"odpt:delay\" : 0,\n" +
                        "  \"odpt:carComposition\" : 15\n" +
                        "} ]"
            val trainList = jsonAdapter.fromJson(json)
            assertThat(trainList).isNotNull
            assertThat(trainList!!.size).isEqualTo(1)

            val train0 = trainList[0]
            assertThat(train0.id).isEqualTo("urn:uuid:5d7dd592-ef17-4b69-b955-5b4fe5f7e350")
            assertThat(train0.date).isEqualTo("2017-12-07T01:24:33+09:00")
            assertThat(train0.valid).isNull()
            assertThat(train0.sameAs).isEqualTo("odpt.Train:JR-East.Utsunomiya.565M")
            assertThat(train0.operator).isEqualTo("odpt.Operator:JR-East")
            assertThat(train0.railway).isEqualTo("odpt.Railway:JR-East.Utsunomiya")
            assertThat(train0.railDirection).isEqualTo("odpt.RailwayDirection:Outbound")
            assertThat(train0.trainNumber).isEqualTo("565M")
            assertThat(train0.trainType).isNull()
            assertThat(train0.trainNameMap).isNull()
            assertThat(train0.fromStation).isEqualTo("odpt.Station:JR-East.Utsunomiya.Suzumenomiya")
            assertThat(train0.toStation).isEqualTo("odpt.Station:JR-East.Utsunomiya.Utsunomiya")
            assertThat(train0.originStationList).isNull()
            assertThat(train0.destinationStationList).isNotNull
            assertThat(train0.destinationStationList!!.size).isEqualTo(1)
            assertThat(train0.destinationStationList!![0]).isEqualTo("odpt.Station:JR-East.Utsunomiya.Utsunomiya")
            assertThat(train0.viaStationList).isNull()
            assertThat(train0.viaRailwayList).isNull()
            assertThat(train0.trainOwner).isNull()
            assertThat(train0.index).isEqualTo(1)
            assertThat(train0.delay).isEqualTo(0)
            assertThat(train0.carComposition).isEqualTo(15)
            assertThat(train0.note).isNull()

            assertThat(train0.trainId.id).isEqualTo("odpt.Train:JR-East.Utsunomiya.565M")
            assertThat(train0.operatorId.id).isEqualTo("odpt.Operator:JR-East")
            assertThat(train0.railwayId.id).isEqualTo("odpt.Railway:JR-East.Utsunomiya")
            assertThat(train0.railDirectionId).isNotNull
            assertThat(train0.railDirectionId!!.id).isEqualTo("odpt.RailwayDirection:Outbound")
            assertThat(train0.trainTypeId).isNull()
            assertThat(train0.fromStationId).isNotNull
            assertThat(train0.fromStationId!!.id).isEqualTo("odpt.Station:JR-East.Utsunomiya.Suzumenomiya")
            assertThat(train0.toStationId).isNotNull
            assertThat(train0.toStationId!!.id).isEqualTo("odpt.Station:JR-East.Utsunomiya.Utsunomiya")
            assertThat(train0.originStationIdList).isNull()
            assertThat(train0.destinationStationIdList).isNotNull
            assertThat(train0.destinationStationIdList!!.size).isEqualTo(1)
            assertThat(train0.destinationStationIdList!![0].id).isEqualTo("odpt.Station:JR-East.Utsunomiya.Utsunomiya")
            assertThat(train0.viaStationIdList).isNull()
            assertThat(train0.viaRailwayIdList).isNull()
            assertThat(train0.trainOwnerOperatorId).isNull()
        }
    }
}
