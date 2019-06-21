package com.github.teracy.odpt.core.v4.common.request

import com.github.teracy.odpt.model.CalendarId

/**
 * 曜日・日付区分情報検索引数
 */
sealed class CalendarArgument(
    /**
     * 固有識別子(ucode)のリスト
     */
    val idList: List<String>? = null,
    /**
     * 固有識別子別名（曜日・日付区分情報ID）のリスト
     */
    val sameAsList: List<String>? = null
) {
    /**
     * 固有識別子による検索
     *
     * @param idList 固有識別子のリスト
     */
    class ById(idList: List<String>) :
        CalendarArgument(idList = idList)

    /**
     * 曜日・日付区分情報IDによる検索
     *
     * @param calendarIdList 曜日・日付区分情報IDのリスト
     */
    class ByCalendarId(calendarIdList: List<CalendarId>) :
        CalendarArgument(sameAsList = calendarIdList.map { it.id })
}
