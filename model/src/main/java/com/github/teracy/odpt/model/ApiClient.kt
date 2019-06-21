package com.github.teracy.odpt.model

/**
 * APIクライアント共通interface
 */
interface ApiClient {
    /**
     * APIアクセス用のアクセストークンを取得する
     */
    fun consumerKey(): String

    /**
     * APIの基底となるURLを取得する
     */
    fun baseUrl(): String
}
