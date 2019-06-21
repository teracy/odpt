package com.github.teracy.odpt.core.geojson.adapter

import com.github.teracy.odpt.core.geojson.response.*
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonReader
import com.squareup.moshi.JsonWriter
import com.squareup.moshi.Moshi
import java.io.IOException
import java.lang.reflect.Type

/**
 * GeoJSONのジオメトリを解釈するためのMoshiアダプタ
 */
class GeometryAdapter(val moshi: Moshi) : JsonAdapter<Geometry>() {
    companion object {
        val FACTORY: Factory = object : Factory {
            override fun create(
                type: Type,
                annotations: MutableSet<out Annotation>,
                moshi: Moshi
            ): GeometryAdapter? {
                if (type == Geometry::class.java) {
                    // GeometryならカスタムAdapterを利用
                    return GeometryAdapter(moshi)
                }
                return null
            }
        }
    }

    override fun fromJson(reader: JsonReader): Geometry? {
        try {
            // 直にnullだった場合の回避
            if (reader.peek() != JsonReader.Token.BEGIN_OBJECT) {
                reader.nextNull<Unit>()
                return null
            }
            // readerは先頭の状態のまま、"type"だけをチェックする
            val newReader = reader.peekJson()
            newReader.beginObject()
            while (newReader.hasNext()) {
                if (newReader.peek() != JsonReader.Token.NAME) {
                    // NAMEでないなら次へ
                    newReader.readJsonValue()
                    continue
                }
                // NAMEの場合
                if (newReader.nextName() == "type") {
                    when (newReader.nextString()) {
                        GeometryType.POINT.typeValue -> {
                            return Moshi.Builder().build().adapter(Point::class.java).fromJson(reader)
                        }
                        GeometryType.MULTI_POINT.typeValue -> {
                            return Moshi.Builder().build().adapter(MultiPoint::class.java).fromJson(reader)
                        }
                        GeometryType.LINE_STRING.typeValue -> {
                            return Moshi.Builder().build().adapter(LineString::class.java).fromJson(reader)
                        }
                        GeometryType.MULTI_LINE_STRING.typeValue -> {
                            return Moshi.Builder().build().adapter(MultiLineString::class.java).fromJson(reader)
                        }
                        GeometryType.POLYGON.typeValue -> {
                            return Moshi.Builder().build().adapter(Polygon::class.java).fromJson(reader)
                        }
                        GeometryType.MULTI_POLYGON.typeValue -> {
                            return Moshi.Builder().build().adapter(MultiPolygon::class.java).fromJson(reader)
                        }
                        GeometryType.GEOMETRY_COLLECTION.typeValue -> {
                            // GeometryCollectionはGeometryを再帰的に内包する
                            return Moshi.Builder()
                                .add(FACTORY)
                                .build().adapter(GeometryCollection::class.java).fromJson(reader)
                        }
                        GeometryType.FEATURE.typeValue -> {
                            // GeometryCollectionはGeometryを内包する
                            return Moshi.Builder()
                                .add(FACTORY)
                                .build().adapter(Feature::class.java).fromJson(reader)
                        }
                        GeometryType.FEATURE_COLLECTION.typeValue -> {
                            // FeatureCollectionはFeatureを内包する
                            return Moshi.Builder()
                                .add(FACTORY)
                                .build().adapter(FeatureCollection::class.java).fromJson(reader)
                        }
                    }
                }
            }
            return null
        } catch (e: IOException) {
            return null
        }
    }

    override fun toJson(writer: JsonWriter, value: Geometry?) {
        writer.beginObject()
        when (value?.geometryType) {
            GeometryType.POINT -> {
                moshi.adapter(Point::class.java).toJson(writer, value as Point)
            }
            GeometryType.MULTI_POINT -> {
                moshi.adapter(MultiPoint::class.java).toJson(writer, value as MultiPoint)
            }
            GeometryType.LINE_STRING -> {
                moshi.adapter(LineString::class.java).toJson(writer, value as LineString)
            }
            GeometryType.MULTI_LINE_STRING -> {
                moshi.adapter(MultiLineString::class.java).toJson(writer, value as MultiLineString)
            }
            GeometryType.POLYGON -> {
                moshi.adapter(Polygon::class.java).toJson(writer, value as Polygon)
            }
            GeometryType.MULTI_POLYGON -> {
                moshi.adapter(MultiPolygon::class.java).toJson(writer, value as MultiPolygon)
            }
            GeometryType.GEOMETRY_COLLECTION -> {
                moshi.adapter(GeometryCollection::class.java).toJson(writer, value as GeometryCollection)
            }
            GeometryType.FEATURE -> {
                moshi.adapter(Feature::class.java).toJson(writer, value as Feature)
            }
            GeometryType.FEATURE_COLLECTION -> {
                moshi.adapter(FeatureCollection::class.java).toJson(writer, value as FeatureCollection)
            }
            else -> {
            }
        }
        writer.endObject()
    }
}
