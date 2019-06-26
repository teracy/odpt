package com.github.teracy.odpt.api.tokyometro.support

import com.github.teracy.odpt.model.TrainOwnerId
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

/**
 * 東京メトロオープンデータAPIで利用可能な列車所有会社のテスト
 */
class TrainOwnersTest {
    @Test
    fun fromIdString() {
        // 正常ケース
        assertThat(TrainOwners.fromId("odpt.TrainOwner:TokyoMetro")).isEqualTo(TrainOwners.TOKYO_METRO)
        assertThat(TrainOwners.fromId("odpt.TrainOwner:JR-East")).isEqualTo(TrainOwners.JR_EAST)
        // 変換できないケース
        assertThat(TrainOwners.fromId("odpt.TrainOwner:Keisei")).isNull()
        assertThat(TrainOwners.fromId(null)).isNull()
    }

    @Test
    fun fromTrainOwnerId() {
        // 正常ケース
        assertThat(TrainOwners.fromId(TrainOwnerId("odpt.TrainOwner:TokyoMetro"))).isEqualTo(TrainOwners.TOKYO_METRO)
        assertThat(TrainOwners.fromId(TrainOwnerId("odpt.TrainOwner:JR-East"))).isEqualTo(TrainOwners.JR_EAST)
        // 変換できないケース
        assertThat(TrainOwners.fromId(TrainOwnerId("odpt.TrainOwner:Keisei"))).isNull()
    }
}
