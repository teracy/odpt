package com.github.teracy.odpt.api.tokyometro.support

import com.github.teracy.odpt.model.RailDirectionId
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

/**
 * 東京メトロオープンデータAPIで利用可能な方面のテスト
 */
class RailDirectionsTest {
    @Test
    fun fromIdString() {
        // 正常ケース
        assertThat(RailDirections.fromId("odpt.RailDirection:TokyoMetro.Ikebukuro")).isEqualTo(RailDirections.Ikebukuro)
        assertThat(RailDirections.fromId("odpt.RailDirection:TokyoMetro.Wakoshi")).isEqualTo(RailDirections.Wakoshi)
        // 変換できないケース
        assertThat(RailDirections.fromId("odpt.RailDirection:Outbound")).isNull()
        assertThat(RailDirections.fromId(null)).isNull()
    }

    @Test
    fun fromRailDirectionId() {
        // 正常ケース
        assertThat(RailDirections.fromId(RailDirectionId("odpt.RailDirection:TokyoMetro.Ikebukuro")))
            .isEqualTo(RailDirections.Ikebukuro)
        assertThat(RailDirections.fromId(RailDirectionId("odpt.RailDirection:TokyoMetro.Wakoshi")))
            .isEqualTo(RailDirections.Wakoshi)
        // 変換できないケース
        assertThat(RailDirections.fromId(RailDirectionId("odpt.RailDirection:Outbound"))).isNull()
    }
}
