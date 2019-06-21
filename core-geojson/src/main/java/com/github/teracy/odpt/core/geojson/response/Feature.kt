package com.github.teracy.odpt.core.geojson.response

import com.squareup.moshi.Json

/**
 * GeoJSONのフィーチャーオブジェクト
 */
data class Feature(
    @field:Json(name = "properties")
    val properties: Map<String, Any>?,

    @field:Json(name = "geometry")
    val geometry: Geometry?
) : Geometry(GeometryType.FEATURE)
