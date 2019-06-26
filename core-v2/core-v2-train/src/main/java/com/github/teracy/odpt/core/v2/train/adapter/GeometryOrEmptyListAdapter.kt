package com.github.teracy.odpt.core.v2.train.adapter

import com.github.teracy.odpt.core.geojson.response.Geometry
import com.squareup.moshi.*
import java.io.IOException
import java.lang.reflect.Type

/**
 * GeoJSONのジオメトリか空のリストが返ってくるAPIを解釈するためのMoshiアダプタ
 * NOTE:v2版の地物情報API向け。Objectを返す定義のAPIだがリクエストに合致するデータがない場合に空Arrayを返すため苦肉の策として実装してある
 */
class GeometryOrEmptyListAdapter(val moshi: Moshi) : JsonAdapter<List<Geometry>>() {
    companion object {
        val FACTORY: Factory = object : Factory {
            override fun create(
                type: Type,
                annotations: MutableSet<out Annotation>,
                moshi: Moshi
            ): GeometryOrEmptyListAdapter? {
                val listType = Types.newParameterizedType(List::class.java, Geometry::class.java)
                if (type == listType) {
                    // List<Geometry>ならカスタムAdapterを利用
                    return GeometryOrEmptyListAdapter(moshi)
                }
                return null
            }
        }
    }

    override fun fromJson(reader: JsonReader): List<Geometry>? {
        val adapter = moshi.adapter(Geometry::class.java)
        try {
            reader.beginArray()
            // arrayの場合
            val list = arrayListOf<Geometry>()
            while (reader.hasNext()) {
                adapter.fromJson(reader)?.let {
                    list.add(it)
                }
            }
            reader.endArray()
            return list
        } catch (e: JsonDataException) {
            // objectの場合
            return adapter.fromJson(reader)?.let {
                arrayListOf(it)
            } ?: arrayListOf()
        } catch (e: IOException) {
            return null
        }
    }

    override fun toJson(writer: JsonWriter, value: List<Geometry>?) {
        val adapter = moshi.adapter(Geometry::class.java)
        writer.beginArray()
        value?.forEach { adapter.toJson(writer, it) }
        writer.endArray()
    }
}
