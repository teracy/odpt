package com.github.teracy.odpt.testutil

import java.io.BufferedReader
import java.io.File
import java.io.FileInputStream
import java.io.IOException

/**
 * ファイルを読むテストのためのinterface
 */
interface ReadFileUtil {
    /**
     * ファイルを読み込む
     * @throws IOException ファイル読み込み失敗時
     */
    // NOTE: javaClass.classLoaderはnullableだが、気にせず利用する
    @Suppress("RECEIVER_NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
    fun readFile(fileName: String): String {
        val classLoader = javaClass.classLoader
        val resource = classLoader.getResource(fileName)
        val file = File(resource.path)
        FileInputStream(file.path).use {
            return it.bufferedReader().use(BufferedReader::readText)
        }
    }
}
