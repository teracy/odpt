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
 * v4版曜日・日付区分情報APIレスポンスのテスト
 */
@RunWith(Enclosed::class)
class OdptCalendarTest {
    /**
     * デシリアライズのテスト
     */
    class DeserializeTest {
        private val parameterizedType = Types.newParameterizedType(List::class.java, OdptCalendar::class.java)

        lateinit var jsonAdapter: JsonAdapter<List<OdptCalendar>>

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
                        "  \"@type\" : \"odpt:Calendar\",\n" +
                        "  \"dc:date\" : \"2017-01-13T15:10:00+09:00\",\n" +
                        "  \"owl:sameAs\" : \"odpt.Calendar:Weekday\",\n" +
                        "  \"dc:title\" : \"平日\",\n" +
                        "  \"odpt:calendarTitle\" : {\n" +
                        "    \"ja\" : \"平日\",\n" +
                        "    \"en\" : \"Weekday\"\n" +
                        "  }\n" +
                        "} ]"
            val calendarList = jsonAdapter.fromJson(json)
            assertThat(calendarList).isNotNull
            assertThat(calendarList!!.size).isEqualTo(1)

            val calendar0 = calendarList[0]
            assertThat(calendar0.id).isEqualTo("urn:ucode:_00001C000000000000010000030FD7E5")
            assertThat(calendar0.date).isEqualTo("2017-01-13T15:10:00+09:00")
            assertThat(calendar0.sameAs).isEqualTo("odpt.Calendar:Weekday")
            assertThat(calendar0.title).isEqualTo("平日")
            assertThat(calendar0.calendarTitle).isNotNull
            assertThat(calendar0.calendarTitle?.size).isEqualTo(2)
            assertThat(calendar0.calendarTitle!!["ja"]).isEqualTo("平日")
            assertThat(calendar0.calendarTitle!!["en"]).isEqualTo("Weekday")
            assertThat(calendar0.day).isNull()
            assertThat(calendar0.duration).isNull()

            assertThat(calendar0.calenderId.id).isEqualTo("odpt.Calendar:Weekday")
        }
    }
}
