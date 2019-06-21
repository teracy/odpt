package com.github.teracy.odpt.api.tokychallenge

import com.github.teracy.odpt.model.ApiClient

/**
 * 東京公共交通オープンデータチャレンジAPIクライアントinterface
 */
interface TokyoChallengeApiClient : ApiClient {
    companion object {
        private const val BASE_URL = "https://api-tokyochallenge.odpt.org/"
    }

    override fun baseUrl(): String {
        return BASE_URL
    }
}
