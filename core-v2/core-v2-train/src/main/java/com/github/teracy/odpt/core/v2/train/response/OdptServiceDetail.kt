package com.github.teracy.odpt.core.v2.train.response

import com.squareup.moshi.Json

/**
 * v2版駅施設情報APIレスポンスの駅の施設情報に含まれる詳細情報
 */
data class OdptServiceDetail(
    /**
     * 施設の利用可能開始時間（いつでも利用できる場合は省略）。基本的にはISO8601時刻形式（05:30など）であるが、「始発」と入る場合もある
     */
    @field:Json(name = "ugsrv:serviceStartTime")
    val serviceStartTime: String?,
    /**
     * 施設の利用可能終了時間（いつでも利用できる場合は省略）。基本的にはISO8601時刻形式（23:50など）であるが、「終車時」と入る場合もある
     */
    @field:Json(name = "ugsrv:serviceEndTime")
    val serviceEndTime: String?,
    /**
     * 施設利用可能時間やエスカレータの方向が曜日によって変わる場合に、次のいずれかを格納（曜日に依存しない場合は省略） 平日, 土休日, 土日祝, 日曜, 月曜, 火曜, 水曜, 木曜, 金曜, 土曜
     */
    @field:Json(name = "odpt:operationDay")
    val operationDay: String?,
    /**
     * エスカレータの方向名（施設がエスカレータの場合に格納。上り、下り、上り・下りの3種類が存在。不明な場合は省略）
     */
    @field:Json(name = "ug:direction")
    val direction: String?
)
