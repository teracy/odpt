package com.github.teracy.odpt.core.v2.train.response

import com.github.teracy.odpt.model.BarrierFreeFacilityType
import com.github.teracy.odpt.model.LocatedArea
import com.github.teracy.odpt.model.MoverType
import com.github.teracy.odpt.model.ToiletAssistant
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.experimental.runners.Enclosed
import org.junit.runner.RunWith

/**
 * v2版駅施設情報APIレスポンスのテスト
 */
@RunWith(Enclosed::class)
class OdptStationFacilityTest {
    /**
     * デシリアライズのテスト
     */
    class DeserializeTest {
        private val parameterizedType = Types.newParameterizedType(List::class.java, OdptStationFacility::class.java)

        lateinit var jsonAdapter: JsonAdapter<List<OdptStationFacility>>

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
                        "    \"@context\": \"http://vocab.tokyometroapp.jp/context_odpt_StationFacility.jsonld\",\n" +
                        "    \"@id\": \"urn:ucode:_00001C000000000000010000030C478A\",\n" +
                        "    \"@type\": \"odpt:StationFacility\",\n" +
                        "    \"owl:sameAs\": \"odpt.StationFacility:TokyoMetro.KokkaiGijidomae\",\n" +
                        "    \"dc:date\": \"2014-10-24T22:07:51+09:00\",\n" +
                        "    \"odpt:barrierfreeFacility\": [\n" +
                        "      {\n" +
                        "        \"@id\": \"urn:ucode:_00001C000000000000010000030D033F\",\n" +
                        "        \"@type\": \"ug:Elevator\",\n" +
                        "        \"owl:sameAs\": \"odpt.StationFacility:TokyoMetro.Marunouchi.KokkaiGijidomae.Inside.Elevator.1\",\n" +
                        "        \"ugsrv:categoryName\": \"エレベーター\",\n" +
                        "        \"odpt:placeName\": \"１番線ホーム（第2車両付近）～国会議事堂方面改札\",\n" +
                        "        \"odpt:locatedAreaName\": \"改札内\"\n" +
                        "      },\n" +
                        "      {\n" +
                        "        \"@id\": \"urn:ucode:_00001C000000000000010000030D0340\",\n" +
                        "        \"@type\": \"ug:Elevator\",\n" +
                        "        \"owl:sameAs\": \"odpt.StationFacility:TokyoMetro.Marunouchi.KokkaiGijidomae.Inside.Elevator.2\",\n" +
                        "        \"ugsrv:categoryName\": \"エレベーター\",\n" +
                        "        \"odpt:placeName\": \"２番線ホーム（第2車両付近）～国会議事堂方面改札\",\n" +
                        "        \"odpt:locatedAreaName\": \"改札内\"\n" +
                        "      },\n" +
                        "      {\n" +
                        "        \"@id\": \"urn:ucode:_00001C000000000000010000030D0344\",\n" +
                        "        \"@type\": \"ug:Escalator\",\n" +
                        "        \"owl:sameAs\": \"odpt.StationFacility:TokyoMetro.Marunouchi.KokkaiGijidomae.Outside.Escalator.1\",\n" +
                        "        \"ugsrv:categoryName\": \"エスカレーター\",\n" +
                        "        \"odpt:serviceDetail\": [\n" +
                        "          {\n" +
                        "            \"odpt:operationDay\": \"平日\",\n" +
                        "            \"ugsrv:serviceStartTime\": \"始発\",\n" +
                        "            \"ugsrv:serviceEndTime\": \"17:00\",\n" +
                        "            \"ug:direction\": \"上り\"\n" +
                        "          },\n" +
                        "          {\n" +
                        "            \"odpt:operationDay\": \"平日\",\n" +
                        "            \"ugsrv:serviceStartTime\": \"17:00\",\n" +
                        "            \"ugsrv:serviceEndTime\": \"終車時\",\n" +
                        "            \"ug:direction\": \"下り\"\n" +
                        "          },\n" +
                        "          {\n" +
                        "            \"odpt:operationDay\": \"土日祝\",\n" +
                        "            \"ug:direction\": \"上り\"\n" +
                        "          }\n" +
                        "        ],\n" +
                        "        \"odpt:placeName\": \"国会議事堂方面改札～１番出入口\",\n" +
                        "        \"odpt:locatedAreaName\": \"改札外\"\n" +
                        "      },\n" +
                        "      {\n" +
                        "        \"@id\": \"urn:ucode:_00001C000000000000010000030D034C\",\n" +
                        "        \"@type\": \"spac:Stairlift\",\n" +
                        "        \"owl:sameAs\": \"odpt.StationFacility:TokyoMetro.Chiyoda.KokkaiGijidomae.Inside.Stairlift.1\",\n" +
                        "        \"ugsrv:categoryName\": \"階段昇降機\",\n" +
                        "        \"odpt:placeName\": \"ホーム～南北線連絡通路\",\n" +
                        "        \"odpt:locatedAreaName\": \"改札内\"\n" +
                        "      },\n" +
                        "      {\n" +
                        "        \"@id\": \"urn:ucode:_00001C000000000000010000030D034D\",\n" +
                        "        \"@type\": \"ug:Toilet\",\n" +
                        "        \"owl:sameAs\": \"odpt.StationFacility:TokyoMetro.Chiyoda.KokkaiGijidomae.Outside.Toilet.1\",\n" +
                        "        \"ugsrv:categoryName\": \"トイレ\",\n" +
                        "        \"odpt:placeName\": \"国会議事堂方面改札外\",\n" +
                        "        \"odpt:locatedAreaName\": \"改札外\",\n" +
                        "        \"spac:hasAssistant\": [\n" +
                        "          \"spac:WheelchairAccessible\",\n" +
                        "          \"ug:BabyChair\",\n" +
                        "          \"ug:BabyChangingTable\",\n" +
                        "          \"ug:ToiletForOstomate\"\n" +
                        "        ]\n" +
                        "      },\n" +
                        "      {\n" +
                        "        \"@id\": \"urn:ucode:_00001C000000000000010000030D034F\",\n" +
                        "        \"@type\": \"ug:Link\",\n" +
                        "        \"owl:sameAs\": \"odpt.StationFacility:TokyoMetro.Chiyoda.KokkaiGijidomae.Outside.AccessibleRoute.1\",\n" +
                        "        \"ugsrv:categoryName\": \"ハンドル型電動車いす利用可能経路\",\n" +
                        "        \"odpt:locatedAreaName\": \"改札外\",\n" +
                        "        \"spac:isAvailableTo\": \"spac:MobilityScooter\",\n" +
                        "        \"ugsrv:remark\": \"国会議事堂横2番出入口エレベーターで改札階へお越しください。千代田線の利用および乗り換えも可能です。\"\n" +
                        "      }\n" +
                        "    ],\n" +
                        "    \"odpt:platformInformation\": [\n" +
                        "      {\n" +
                        "        \"odpt:railway\": \"odpt.Railway:TokyoMetro.Marunouchi\",\n" +
                        "        \"odpt:carComposition\": 6,\n" +
                        "        \"odpt:carNumber\": 1,\n" +
                        "        \"odpt:railDirection\": \"odpt.RailDirection:TokyoMetro.Ikebukuro\"\n" +
                        "      },\n" +
                        "      {\n" +
                        "        \"odpt:railway\": \"odpt.Railway:TokyoMetro.Marunouchi\",\n" +
                        "        \"odpt:carComposition\": 6,\n" +
                        "        \"odpt:carNumber\": 3,\n" +
                        "        \"odpt:railDirection\": \"odpt.RailDirection:TokyoMetro.Ikebukuro\",\n" +
                        "        \"odpt:transferInformation\": [\n" +
                        "          {\n" +
                        "            \"odpt:railway\": \"odpt.Railway:TokyoMetro.Ginza\",\n" +
                        "            \"odpt:necessaryTime\": 13\n" +
                        "          },\n" +
                        "          {\n" +
                        "            \"odpt:railway\": \"odpt.Railway:TokyoMetro.Chiyoda\",\n" +
                        "            \"odpt:necessaryTime\": 4\n" +
                        "          },\n" +
                        "          {\n" +
                        "            \"odpt:railway\": \"odpt.Railway:TokyoMetro.Namboku\",\n" +
                        "            \"odpt:necessaryTime\": 10\n" +
                        "          }\n" +
                        "        ],\n" +
                        "        \"odpt:barrierfreeFacility\": [\n" +
                        "          \"odpt.StationFacility:TokyoMetro.Marunouchi.KokkaiGijidomae.Outside.Toilet.1\"\n" +
                        "        ],\n" +
                        "        \"odpt:surroundingArea\": [\n" +
                        "          \"国会議事堂\"\n" +
                        "        ]\n" +
                        "      }\n" +
                        "    ]\n" +
                        "  }\n" +
                        "]"
            val stationFacilityList = jsonAdapter.fromJson(json)
            assertThat(stationFacilityList).isNotNull
            assertThat(stationFacilityList!!.size).isEqualTo(1)

            val stationFacility0 = stationFacilityList[0]
            assertThat(stationFacility0.id).isEqualTo("urn:ucode:_00001C000000000000010000030C478A")
            assertThat(stationFacility0.sameAs).isEqualTo("odpt.StationFacility:TokyoMetro.KokkaiGijidomae")
            assertThat(stationFacility0.date).isEqualTo("2014-10-24T22:07:51+09:00")

            assertThat(stationFacility0.barrierFreeFacilityList).isNotNull
            assertThat(stationFacility0.barrierFreeFacilityList!!.size).isEqualTo(6)
            // エレベーター
            val barrierFreeFacility0 = stationFacility0.barrierFreeFacilityList!![0]
            assertThat(barrierFreeFacility0.id).isEqualTo("urn:ucode:_00001C000000000000010000030D033F")
            assertThat(barrierFreeFacility0.category).isEqualTo(BarrierFreeFacilityType.ELEVATOR)
            assertThat(barrierFreeFacility0.sameAs).isEqualTo("odpt.StationFacility:TokyoMetro.Marunouchi.KokkaiGijidomae.Inside.Elevator.1")
            assertThat(barrierFreeFacility0.categoryName).isEqualTo("エレベーター")
            assertThat(barrierFreeFacility0.serviceDetail).isNull()
            assertThat(barrierFreeFacility0.placeName).isEqualTo("１番線ホーム（第2車両付近）～国会議事堂方面改札")
            assertThat(barrierFreeFacility0.locatedAreaName).isEqualTo("改札内")
            assertThat(barrierFreeFacility0.locatedArea).isEqualTo(LocatedArea.INSIDE)
            assertThat(barrierFreeFacility0.remark).isNull()
            assertThat(barrierFreeFacility0.toiletAssistant).isNull()
            assertThat(barrierFreeFacility0.moverType).isNull()

            assertThat(barrierFreeFacility0.stationFacilityId!!.id).isEqualTo("odpt.StationFacility:TokyoMetro.Marunouchi.KokkaiGijidomae.Inside.Elevator.1")

            // エスカレーター
            val barrierFreeFacility2 = stationFacility0.barrierFreeFacilityList!![2]
            assertThat(barrierFreeFacility2.id).isEqualTo("urn:ucode:_00001C000000000000010000030D0344")
            assertThat(barrierFreeFacility2.category).isEqualTo(BarrierFreeFacilityType.ESCALATOR)
            assertThat(barrierFreeFacility2.sameAs).isEqualTo("odpt.StationFacility:TokyoMetro.Marunouchi.KokkaiGijidomae.Outside.Escalator.1")
            assertThat(barrierFreeFacility2.categoryName).isEqualTo("エスカレーター")
            assertThat(barrierFreeFacility2.serviceDetail).isNotNull
            assertThat(barrierFreeFacility2.serviceDetail!!.size).isEqualTo(3)
            val serviceDetail0 = barrierFreeFacility2.serviceDetail!![0]
            assertThat(serviceDetail0.serviceStartTime).isEqualTo("始発")
            assertThat(serviceDetail0.serviceEndTime).isEqualTo("17:00")
            assertThat(serviceDetail0.operationDay).isEqualTo("平日")
            assertThat(serviceDetail0.direction).isEqualTo("上り")
            assertThat(barrierFreeFacility2.placeName).isEqualTo("国会議事堂方面改札～１番出入口")
            assertThat(barrierFreeFacility2.locatedAreaName).isEqualTo("改札外")
            assertThat(barrierFreeFacility2.locatedArea).isEqualTo(LocatedArea.OUTSIDE)
            assertThat(barrierFreeFacility2.remark).isNull()
            assertThat(barrierFreeFacility2.toiletAssistant).isNull()
            assertThat(barrierFreeFacility2.moverType).isNull()

            assertThat(barrierFreeFacility2.stationFacilityId!!.id).isEqualTo("odpt.StationFacility:TokyoMetro.Marunouchi.KokkaiGijidomae.Outside.Escalator.1")

            // 階段昇降機
            val barrierFreeFacility3 = stationFacility0.barrierFreeFacilityList!![3]
            assertThat(barrierFreeFacility3.id).isEqualTo("urn:ucode:_00001C000000000000010000030D034C")
            assertThat(barrierFreeFacility3.category).isEqualTo(BarrierFreeFacilityType.STAIR_LIFT)
            assertThat(barrierFreeFacility3.sameAs).isEqualTo("odpt.StationFacility:TokyoMetro.Chiyoda.KokkaiGijidomae.Inside.Stairlift.1")
            assertThat(barrierFreeFacility3.categoryName).isEqualTo("階段昇降機")
            assertThat(barrierFreeFacility3.serviceDetail).isNull()
            assertThat(barrierFreeFacility3.placeName).isEqualTo("ホーム～南北線連絡通路")
            assertThat(barrierFreeFacility3.locatedAreaName).isEqualTo("改札内")
            assertThat(barrierFreeFacility3.locatedArea).isEqualTo(LocatedArea.INSIDE)
            assertThat(barrierFreeFacility3.remark).isNull()
            assertThat(barrierFreeFacility3.toiletAssistant).isNull()
            assertThat(barrierFreeFacility3.moverType).isNull()

            assertThat(barrierFreeFacility3.stationFacilityId!!.id).isEqualTo("odpt.StationFacility:TokyoMetro.Chiyoda.KokkaiGijidomae.Inside.Stairlift.1")

            // トイレ
            val barrierFreeFacility4 = stationFacility0.barrierFreeFacilityList!![4]
            assertThat(barrierFreeFacility4.id).isEqualTo("urn:ucode:_00001C000000000000010000030D034D")
            assertThat(barrierFreeFacility4.category).isEqualTo(BarrierFreeFacilityType.TOILET)
            assertThat(barrierFreeFacility4.sameAs).isEqualTo("odpt.StationFacility:TokyoMetro.Chiyoda.KokkaiGijidomae.Outside.Toilet.1")
            assertThat(barrierFreeFacility4.categoryName).isEqualTo("トイレ")
            assertThat(barrierFreeFacility4.serviceDetail).isNull()
            assertThat(barrierFreeFacility4.placeName).isEqualTo("国会議事堂方面改札外")
            assertThat(barrierFreeFacility4.locatedAreaName).isEqualTo("改札外")
            assertThat(barrierFreeFacility4.locatedArea).isEqualTo(LocatedArea.OUTSIDE)
            assertThat(barrierFreeFacility4.remark).isNull()
            assertThat(barrierFreeFacility4.toiletAssistant).isNotNull
            assertThat(barrierFreeFacility4.toiletAssistant!!.size).isEqualTo(4)
            assertThat(barrierFreeFacility4.toiletAssistant!![0]).isEqualTo(ToiletAssistant.WHEELCHAIR_ACCESSIBLE)
            assertThat(barrierFreeFacility4.toiletAssistant!![1]).isEqualTo(ToiletAssistant.BABY_CHAIR)
            assertThat(barrierFreeFacility4.toiletAssistant!![2]).isEqualTo(ToiletAssistant.BABY_CHANGING_TABLE)
            assertThat(barrierFreeFacility4.toiletAssistant!![3]).isEqualTo(ToiletAssistant.FOR_OSTOMATE)
            assertThat(barrierFreeFacility4.moverType).isNull()

            assertThat(barrierFreeFacility4.stationFacilityId!!.id).isEqualTo("odpt.StationFacility:TokyoMetro.Chiyoda.KokkaiGijidomae.Outside.Toilet.1")

            // ハンドル型電動車いす利用可能経路
            val barrierFreeFacility5 = stationFacility0.barrierFreeFacilityList!![5]
            assertThat(barrierFreeFacility5.id).isEqualTo("urn:ucode:_00001C000000000000010000030D034F")
            assertThat(barrierFreeFacility5.category).isEqualTo(BarrierFreeFacilityType.LINK)
            assertThat(barrierFreeFacility5.sameAs).isEqualTo("odpt.StationFacility:TokyoMetro.Chiyoda.KokkaiGijidomae.Outside.AccessibleRoute.1")
            assertThat(barrierFreeFacility5.categoryName).isEqualTo("ハンドル型電動車いす利用可能経路")
            assertThat(barrierFreeFacility5.serviceDetail).isNull()
            assertThat(barrierFreeFacility5.placeName).isNull()
            assertThat(barrierFreeFacility5.locatedAreaName).isEqualTo("改札外")
            assertThat(barrierFreeFacility5.locatedArea).isEqualTo(LocatedArea.OUTSIDE)
            assertThat(barrierFreeFacility5.remark).isEqualTo("国会議事堂横2番出入口エレベーターで改札階へお越しください。千代田線の利用および乗り換えも可能です。")
            assertThat(barrierFreeFacility5.toiletAssistant).isNull()
            assertThat(barrierFreeFacility5.moverType).isEqualTo(MoverType.AVAILABLE_TO_MOBILITY_SCOOTER)

            assertThat(barrierFreeFacility5.stationFacilityId!!.id).isEqualTo("odpt.StationFacility:TokyoMetro.Chiyoda.KokkaiGijidomae.Outside.AccessibleRoute.1")

            assertThat(stationFacility0.platformInformationList.size).isEqualTo(2)
            val platformInformation0 = stationFacility0.platformInformationList[0]
            assertThat(platformInformation0.railway).isEqualTo("odpt.Railway:TokyoMetro.Marunouchi")
            assertThat(platformInformation0.carComposition).isEqualTo(6)
            assertThat(platformInformation0.carNumber).isEqualTo(1)
            assertThat(platformInformation0.railDirection).isEqualTo("odpt.RailDirection:TokyoMetro.Ikebukuro")
            assertThat(platformInformation0.transferInformationList).isNull()
            assertThat(platformInformation0.barrierFreeFacilityList).isNull()
            assertThat(platformInformation0.surroundingArea).isNull()

            assertThat(platformInformation0.railwayId.id).isEqualTo("odpt.Railway:TokyoMetro.Marunouchi")
            assertThat(platformInformation0.railDirectionId.id).isEqualTo("odpt.RailDirection:TokyoMetro.Ikebukuro")

            val platformInformation1 = stationFacility0.platformInformationList[1]
            assertThat(platformInformation1.railway).isEqualTo("odpt.Railway:TokyoMetro.Marunouchi")
            assertThat(platformInformation1.carComposition).isEqualTo(6)
            assertThat(platformInformation1.carNumber).isEqualTo(3)
            assertThat(platformInformation1.railDirection).isEqualTo("odpt.RailDirection:TokyoMetro.Ikebukuro")
            assertThat(platformInformation1.transferInformationList).isNotNull
            assertThat(platformInformation1.transferInformationList!!.size).isEqualTo(3)

            val transferInformation0 = platformInformation1.transferInformationList!![0]
            assertThat(transferInformation0.railway).isEqualTo("odpt.Railway:TokyoMetro.Ginza")
            assertThat(transferInformation0.railDirection).isNull()
            assertThat(transferInformation0.necessaryTime).isEqualTo(13)
            assertThat(transferInformation0.railwayId.id).isEqualTo("odpt.Railway:TokyoMetro.Ginza")
            assertThat(transferInformation0.railDirectionId).isNull()

            assertThat(platformInformation1.barrierFreeFacilityList).isNotNull
            assertThat(platformInformation1.barrierFreeFacilityList!!.size).isEqualTo(1)
            assertThat(platformInformation1.barrierFreeFacilityList!![0]).isEqualTo("odpt.StationFacility:TokyoMetro.Marunouchi.KokkaiGijidomae.Outside.Toilet.1")
            assertThat(platformInformation1.surroundingArea).isNotNull
            assertThat(platformInformation1.surroundingArea!!.size).isEqualTo(1)
            assertThat(platformInformation1.surroundingArea!![0]).isEqualTo("国会議事堂")

            assertThat(platformInformation1.railwayId.id).isEqualTo("odpt.Railway:TokyoMetro.Marunouchi")
            assertThat(platformInformation1.railDirectionId.id).isEqualTo("odpt.RailDirection:TokyoMetro.Ikebukuro")
            assertThat(platformInformation1.barrierFreeFacilityIdList).isNotNull
            assertThat(platformInformation1.barrierFreeFacilityIdList!!.size).isEqualTo(1)
            assertThat(platformInformation1.barrierFreeFacilityIdList!![0].id).isEqualTo("odpt.StationFacility:TokyoMetro.Marunouchi.KokkaiGijidomae.Outside.Toilet.1")

            assertThat(stationFacility0.stationFacilityId.id).isEqualTo("odpt.StationFacility:TokyoMetro.KokkaiGijidomae")
        }
    }
}
