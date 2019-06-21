package com.github.teracy.odpt.api.odptdatacenter

import com.github.teracy.odpt.model.ApiClient

/**
 * 公共交通オープンデータセンターAPIクライアントinterface
 */
interface OdptDataCenterApiClient : ApiClient {
    companion object {
        private const val BASE_URL = "https://api.odpt.org/"
    }

    override fun baseUrl(): String {
        return BASE_URL
    }
}
