package com.github.teracy.odpt.api.tokyometro.support

import com.github.teracy.odpt.model.RailwayId
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

/**
 * 東京メトロオープンデータAPIで利用可能な路線のテスト
 */
class RailwaysTest {
    @Test
    fun fromIdString() {
        // 正常ケース
        assertThat(Railways.fromId("odpt.Railway:TokyoMetro.Hanzomon")).isEqualTo(Railways.TOKYO_METRO_HANZOMON)
        assertThat(Railways.fromId("odpt.Railway:JR-East.Sobu")).isEqualTo(Railways.JR_EAST_SOBU)
        // 変換できないケース
        assertThat(Railways.fromId("odpt.Railway:JR-West.Sanin")).isNull()
        assertThat(Railways.fromId(null)).isNull()
    }

    @Test
    fun fromRailwayId() {
        // 正常ケース
        assertThat(Railways.fromId(RailwayId("odpt.Railway:TokyoMetro.Hanzomon"))).isEqualTo(Railways.TOKYO_METRO_HANZOMON)
        assertThat(Railways.fromId(RailwayId("odpt.Railway:JR-East.Sobu"))).isEqualTo(Railways.JR_EAST_SOBU)
        // 変換できないケース
        assertThat(Railways.fromId(RailwayId("odpt.Railway:JR-West.Sanin"))).isNull()
    }
}
