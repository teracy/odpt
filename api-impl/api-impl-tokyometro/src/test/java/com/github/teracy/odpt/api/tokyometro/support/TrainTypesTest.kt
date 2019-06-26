package com.github.teracy.odpt.api.tokyometro.support

import com.github.teracy.odpt.model.TrainTypeId
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

/**
 * 東京メトロオープンデータAPIで利用可能な列車種別のテスト
 */
class TrainTypesTest {
    @Test
    fun fromIdString() {
        // 正常ケース
        assertThat(TrainTypes.fromId("odpt.TrainType:TokyoMetro.Local")).isEqualTo(TrainTypes.LOCAL)
        assertThat(TrainTypes.fromId("odpt.TrainType:TokyoMetro.RomanceCar")).isEqualTo(TrainTypes.ROMANCE_CAR)
        // 変換できないケース
        assertThat(TrainTypes.fromId("odpt.TrainType:JR-East.Rapid")).isNull()
        assertThat(TrainTypes.fromId(null)).isNull()
    }

    @Test
    fun fromTrainTypeId() {
        // 正常ケース
        assertThat(TrainTypes.fromId(TrainTypeId("odpt.TrainType:TokyoMetro.Local"))).isEqualTo(TrainTypes.LOCAL)
        assertThat(TrainTypes.fromId(TrainTypeId("odpt.TrainType:TokyoMetro.RomanceCar"))).isEqualTo(TrainTypes.ROMANCE_CAR)
        // 変換できないケース
        assertThat(TrainTypes.fromId(TrainTypeId("odpt.TrainType:JR-East.Rapid"))).isNull()
    }
}
