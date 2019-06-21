package com.github.teracy.odpt.core.v4.bus.adapter

import com.squareup.moshi.*
import java.io.IOException
import java.lang.reflect.Type

/**
 * ArrayかObjectかどちらが返ってくるか曖昧な項目のためのMoshiアダプタ
 */
class ArrayOrObjectAdapter(val moshi: Moshi) : JsonAdapter<List<String>>() {
    companion object {
        val FACTORY: Factory = object : Factory {
            override fun create(
                type: Type,
                annotations: MutableSet<out Annotation>,
                moshi: Moshi
            ): ArrayOrObjectAdapter? {
                val listType = Types.newParameterizedType(List::class.java, String::class.java)
                if (type == listType) {
                    // List<String>ならカスタムAdapterを利用
                    return ArrayOrObjectAdapter(moshi)
                }
                return null
            }
        }
    }

    override fun fromJson(reader: JsonReader): List<String>? {
        try {
            reader.beginArray()
            // arrayの場合
            val list = arrayListOf<String>()
            while (reader.hasNext()) {
                list.add(reader.nextString())
            }
            reader.endArray()
            return list
        } catch (e: JsonDataException) {
            // objectの場合
            val list = arrayListOf<String>()
            list.add(reader.nextString())
            return list
        } catch (e: IOException) {
            return null
        }
    }

    override fun toJson(writer: JsonWriter, value: List<String>?) {
        val adapter = moshi.adapter(String::class.java)
        writer.beginArray()
        value?.forEach { adapter.toJson(writer, it) }
        writer.endArray()
    }
}
