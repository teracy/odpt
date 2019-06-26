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
 * v2版列車ロケーション情報APIレスポンス
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
         * 東京メトロオープンデータAPIサンプルデータより
         */
        @Test
        fun tokyoMetroSampleData() {
            // テストデータ
            val json =
                "[\n" +
                        "  {\n" +
                        "    \"@context\": \"http://vocab.tokyometroapp.jp/context_odpt_Train.jsonld\",\n" +
                        "    \"@type\": \"odpt:Train\",\n" +
                        "    \"@id\": \"urn:ucode:_00001C000000000000010000030C4D61\",\n" +
                        "    \"dc:date\": \"2014-09-08T20:53:51+09:00\",\n" +
                        "    \"dct:valid\": \"2014-09-08T20:54:51+09:00\",\n" +
                        "    \"odpt:frequency\": 60,\n" +
                        "    \"odpt:railway\": \"odpt.Railway:TokyoMetro.Chiyoda\",\n" +
                        "    \"owl:sameAs\": \"odpt.Train:TokyoMetro.Chiyoda.A2098S4\",\n" +
                        "    \"odpt:trainNumber\": \"A2098S4\",\n" +
                        "    \"odpt:trainType\": \"odpt.TrainType:TokyoMetro.Local\",\n" +
                        "    \"odpt:delay\": 0,\n" +
                        "    \"odpt:startingStation\": \"odpt.Station:TokyoMetro.Chiyoda.KitaAyase\",\n" +
                        "    \"odpt:terminalStation\": \"odpt.Station:TokyoMetro.Chiyoda.Ayase\",\n" +
                        "    \"odpt:fromStation\": \"odpt.Station:TokyoMetro.Chiyoda.KitaAyase\",\n" +
                        "    \"odpt:toStation\": null,\n" +
                        "    \"odpt:railDirection\": \"odpt.RailDirection:TokyoMetro.Ayase\",\n" +
                        "    \"odpt:trainOwner\": \"odpt.TrainOwner:TokyoMetro\"\n" +
                        "  },\n" +
                        "  {\n" +
                        "    \"@context\": \"http://vocab.tokyometroapp.jp/context_odpt_train.jsonld\",\n" +
                        "    \"@type\": \"odpt:Train\",\n" +
                        "    \"@id\": \"urn:ucode:_00001C000000000000010000030C4D65\",\n" +
                        "    \"dc:date\": \"2014-09-08T20:53:51+09:00\",\n" +
                        "    \"dct:valid\": \"2014-09-08T20:54:51+09:00\",\n" +
                        "    \"odpt:frequency\": 60,\n" +
                        "    \"odpt:railway\": \"odpt.Railway:TokyoMetro.Chiyoda\",\n" +
                        "    \"owl:sameAs\": \"odpt.Train:TokyoMetro.Chiyoda.A2029K\",\n" +
                        "    \"odpt:trainNumber\": \"A2029K\",\n" +
                        "    \"odpt:trainType\": \"odpt.TrainType:TokyoMetro.Local\",\n" +
                        "    \"odpt:delay\": 0,\n" +
                        "    \"odpt:startingStation\": \"odpt.Station:JR-East.Joban.Abiko\",\n" +
                        "    \"odpt:terminalStation\": \"odpt.Station:TokyoMetro.Chiyoda.YoyogiUehara\",\n" +
                        "    \"odpt:fromStation\": \"odpt.Station:TokyoMetro.Chiyoda.Yushima\",\n" +
                        "    \"odpt:toStation\": null,\n" +
                        "    \"odpt:railDirection\": \"odpt.RailDirection:TokyoMetro.YoyogiUehara\",\n" +
                        "    \"odpt:trainOwner\": \"odpt.TrainOwner:JR-East\"\n" +
                        "  }\n" +
                        "]"
            val trainList = jsonAdapter.fromJson(json)
            assertThat(trainList).isNotNull
            assertThat(trainList!!.size).isEqualTo(2)

            val train0 = trainList[0]
            assertThat(train0.id).isEqualTo("urn:ucode:_00001C000000000000010000030C4D61")
            assertThat(train0.sameAs).isEqualTo("odpt.Train:TokyoMetro.Chiyoda.A2098S4")
            assertThat(train0.trainNumber).isEqualTo("A2098S4")
            assertThat(train0.trainType).isEqualTo("odpt.TrainType:TokyoMetro.Local")
            assertThat(train0.date).isEqualTo("2014-09-08T20:53:51+09:00")
            assertThat(train0.valid).isEqualTo("2014-09-08T20:54:51+09:00")
            assertThat(train0.frequency).isEqualTo(60)
            assertThat(train0.railway).isEqualTo("odpt.Railway:TokyoMetro.Chiyoda")
            assertThat(train0.trainOwner).isEqualTo("odpt.TrainOwner:TokyoMetro")
            assertThat(train0.railDirection).isEqualTo("odpt.RailDirection:TokyoMetro.Ayase")
            assertThat(train0.delay).isEqualTo(0)
            assertThat(train0.startingStation).isEqualTo("odpt.Station:TokyoMetro.Chiyoda.KitaAyase")
            assertThat(train0.terminalStation).isEqualTo("odpt.Station:TokyoMetro.Chiyoda.Ayase")
            assertThat(train0.fromStation).isEqualTo("odpt.Station:TokyoMetro.Chiyoda.KitaAyase")
            assertThat(train0.toStation).isNull()

            assertThat(train0.trainId.id).isEqualTo("odpt.Train:TokyoMetro.Chiyoda.A2098S4")
            assertThat(train0.trainTypeId.id).isEqualTo("odpt.TrainType:TokyoMetro.Local")
            assertThat(train0.railwayId.id).isEqualTo("odpt.Railway:TokyoMetro.Chiyoda")
            assertThat(train0.trainOwnerId.id).isEqualTo("odpt.TrainOwner:TokyoMetro")
            assertThat(train0.railDirectionId.id).isEqualTo("odpt.RailDirection:TokyoMetro.Ayase")
            assertThat(train0.startingStationId.id).isEqualTo("odpt.Station:TokyoMetro.Chiyoda.KitaAyase")
            assertThat(train0.terminalStationId.id).isEqualTo("odpt.Station:TokyoMetro.Chiyoda.Ayase")
            assertThat(train0.fromStationId.id).isEqualTo("odpt.Station:TokyoMetro.Chiyoda.KitaAyase")
            assertThat(train0.toStationId).isNull()
        }
    }
}
