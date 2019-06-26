package com.github.teracy.odpt.api.tokyometro.support

import com.github.teracy.odpt.model.OperatorId
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

/**
 * 東京メトロオープンデータAPIで利用可能な事業者のテスト
 */
class OperatorsTest {
    @Test
    fun fromIdString() {
        // 正常ケース
        assertThat(Operators.fromId("odpt.Operator:TokyoMetro")).isEqualTo(Operators.TOKYO_METRO)
        // 変換できないケース
        assertThat(Operators.fromId("odpt.Operator:JR-East")).isNull()
        assertThat(Operators.fromId(null)).isNull()
    }

    @Test
    fun fromOperatorId() {
        // 正常ケース
        assertThat(Operators.fromId(OperatorId("odpt.Operator:TokyoMetro"))).isEqualTo(Operators.TOKYO_METRO)
        // 変換できないケース
        assertThat(Operators.fromId(OperatorId("odpt.Operator:JR-East"))).isNull()
    }
}
