package com.github.teracy.odpt.testutil

import java.io.IOException

/**
 * APIクライアントテストクラスinterface
 */
interface ApiClientTest : ReadFileUtil {
    /**
     * APIアクセス用のアクセストークンをファイルから読み込む
     * @return アクセストークン
     * @throws OdptTestException アクセストークンを記載したファイルがない、または読み込みに失敗した場合
     */
    fun readConsumerKeyFile(): String {
        try {
            return readFile("consumer_key.txt")
        } catch (e: IOException) {
            throw OdptTestException("test/resources直下にAPIアクセス用のアクセストークンを記載したconsumer_key.txtを作成してください")
        }
    }
}
