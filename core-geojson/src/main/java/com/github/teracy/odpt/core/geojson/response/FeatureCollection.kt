package com.github.teracy.odpt.core.geojson.response

import com.squareup.moshi.Json

/**
 * GeoJSONのフィーチャーコレクション
 */
data class FeatureCollection(
    @field:Json(name = "features")
    val features: List<Feature>
) : Geometry(GeometryType.FEATURE_COLLECTION)
