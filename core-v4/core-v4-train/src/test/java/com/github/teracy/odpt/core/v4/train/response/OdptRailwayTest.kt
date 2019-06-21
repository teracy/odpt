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
 * v4版鉄道路線情報APIレスポンスのテスト
 */
@RunWith(Enclosed::class)
class OdptRailwayTest {
    /**
     * デシリアライズのテスト
     */
    class DeserializeTest {
        private val parameterizedType = Types.newParameterizedType(List::class.java, OdptRailway::class.java)

        lateinit var jsonAdapter: JsonAdapter<List<OdptRailway>>

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
                        "  \"@type\" : \"odpt:Railway\",\n" +
                        "  \"dc:date\" : \"2017-01-13T15:10:00+09:00\",\n" +
                        "  \"owl:sameAs\" : \"odpt.Railway:JR-East.ChuoRapid\",\n" +
                        "  \"dc:title\" : \"中央線快速\",\n" +
                        "  \"odpt:railwayTitle\" : {\n" +
                        "    \"ja\" : \"中央線快速\",\n" +
                        "    \"en\" : \"Chuo Rapid Line\"\n" +
                        "  },\n" +
                        "  \"odpt:operator\" : \"odpt.Operator:JR-East\",\n" +
                        "  \"odpt:ascendingRailDirection\" : \"odpt.RailDirection:Outbound\",\n" +
                        "  \"odpt:descendingRailDirection\" : \"odpt.RailDirection:Inbound\",\n" +
                        "  \"odpt:stationOrder\" : [ {\n" +
                        "    \"odpt:index\" : 1,\n" +
                        "    \"odpt:station\" : \"odpt.Station:JR-East.ChuoRapid.Tokyo\",\n" +
                        "    \"odpt:stationTitle\" : {\n" +
                        "      \"ja\" : \"東京\",\n" +
                        "      \"en\" : \"Tokyo\"\n" +
                        "    }\n" +
                        "  }, {\n" +
                        "    \"odpt:index\" : 2,\n" +
                        "    \"odpt:station\" : \"odpt.Station:JR-East.ChuoRapid.Kanda\",\n" +
                        "    \"odpt:stationTitle\" : {\n" +
                        "      \"ja\" : \"神田\",\n" +
                        "      \"en\" : \"Kanda\"\n" +
                        "    }\n" +
                        "  }, {\n" +
                        "    \"odpt:index\" : 3,\n" +
                        "    \"odpt:station\" : \"odpt.Station:JR-East.ChuoRapid.Ochanomizu\",\n" +
                        "    \"odpt:stationTitle\" : {\n" +
                        "      \"ja\" : \"御茶ノ水\",\n" +
                        "      \"en\" : \"Ochanomizu\"\n" +
                        "    }\n" +
                        "  } ]\n" +
                        "} ]"
            val railwayList = jsonAdapter.fromJson(json)
            assertThat(railwayList).isNotNull
            assertThat(railwayList!!.size).isEqualTo(1)

            val railway0 = railwayList[0]
            assertThat(railway0.id).isEqualTo("urn:ucode:_00001C000000000000010000030FD7E5")
            assertThat(railway0.date).isEqualTo("2017-01-13T15:10:00+09:00")
            assertThat(railway0.sameAs).isEqualTo("odpt.Railway:JR-East.ChuoRapid")
            assertThat(railway0.title).isEqualTo("中央線快速")
            assertThat(railway0.titleMap).isNotNull
            assertThat(railway0.titleMap!!.size).isEqualTo(2)
            assertThat(railway0.titleMap!!["ja"]).isEqualTo("中央線快速")
            assertThat(railway0.titleMap!!["en"]).isEqualTo("Chuo Rapid Line")
            assertThat(railway0.kana).isNull()
            assertThat(railway0.operator).isEqualTo("odpt.Operator:JR-East")
            assertThat(railway0.lineCode).isNull()
            assertThat(railway0.lineColor).isNull()
            assertThat(railway0.region).isNull()
            assertThat(railway0.ascendingRailDirection).isEqualTo("odpt.RailDirection:Outbound")
            assertThat(railway0.descendingRailDirection).isEqualTo("odpt.RailDirection:Inbound")
            assertThat(railway0.stationOrderList.size).isEqualTo(3)
            val stationOrder0 = railway0.stationOrderList[0]
            assertThat(stationOrder0.station).isEqualTo("odpt.Station:JR-East.ChuoRapid.Tokyo")
            assertThat(stationOrder0.stationTitleMap).isNotNull
            assertThat(stationOrder0.stationTitleMap!!.size).isEqualTo(2)
            assertThat(stationOrder0.stationTitleMap!!["ja"]).isEqualTo("東京")
            assertThat(stationOrder0.stationTitleMap!!["en"]).isEqualTo("Tokyo")
            assertThat(stationOrder0.index).isEqualTo(1)
            assertThat(stationOrder0.stationId.id).isEqualTo("odpt.Station:JR-East.ChuoRapid.Tokyo")

            assertThat(railway0.railwayId.id).isEqualTo("odpt.Railway:JR-East.ChuoRapid")
            assertThat(railway0.operatorId.id).isEqualTo("odpt.Operator:JR-East")
            assertThat(railway0.ascendingRailDirectionId).isNotNull
            assertThat(railway0.ascendingRailDirectionId!!.id).isEqualTo("odpt.RailDirection:Outbound")
            assertThat(railway0.descendingRailDirectionId).isNotNull
            assertThat(railway0.descendingRailDirectionId!!.id).isEqualTo("odpt.RailDirection:Inbound")
        }
    }
}
