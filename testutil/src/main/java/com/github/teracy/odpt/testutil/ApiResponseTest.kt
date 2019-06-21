package com.github.teracy.odpt.testutil

/**
 * API応答テストクラスinterface
 */
interface ApiResponseTest : ReadFileUtil {
    /**
     * テスト用データを格納したJSONファイルを読み込む
     * @return JSONを読み込んだString（ファイル読み込み失敗時は空文字列）
     */
    fun readJsonFile(fileName: String): String {
        return readFile(fileName)
    }
}
