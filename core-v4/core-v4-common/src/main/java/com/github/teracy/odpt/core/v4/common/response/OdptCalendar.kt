package com.github.teracy.odpt.core.v4.common.response

import com.github.teracy.odpt.model.CalendarId
import com.squareup.moshi.Json

/**
 * v4版曜日・日付区分情報APIレスポンス
 */
data class OdptCalendar(
    /**
     * 固有識別子(ucode)
     */
    @field:Json(name = "@id")
    val id: String,
    /**
     * データ生成日時（ISO8601 日付時刻形式）
     */
    @field:Json(name = "dc:date")
    val date: String?,
    /**
     * 固有識別子。命名ルール：odpt.Calendar:名称(.期間)
     */
    @field:Json(name = "owl:sameAs")
    val sameAs: String,
    /**
     * カレンダー名称（日本語）
     */
    @field:Json(name = "dc:title")
    val title: String?,
    /**
     * カレンダー名称（多言語対応）
     */
    @field:Json(name = "odpt:calendarTitle")
    val calendarTitle: Map<String, String>?,
    /**
     * 該当する日付のリスト（ISO8601 日付時刻形式）
     */
    @field:Json(name = "odpt:day")
    val day: List<String>?,
    /**
     * カレンダー有効期間（ISO8601 期間形式）
     */
    @field:Json(name = "odpt:duration")
    val duration: String?
) {
    /**
     * 固有識別子を表す曜日・日付情報ID
     */
    val calenderId: CalendarId
        get() = CalendarId(sameAs)
}
