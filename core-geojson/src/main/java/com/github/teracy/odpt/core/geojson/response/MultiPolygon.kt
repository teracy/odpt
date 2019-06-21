package com.github.teracy.odpt.core.geojson.response

import com.squareup.moshi.Json

/**
 * GeoJSONのジオメトリ："MultiPolygon"
 */
data class MultiPolygon(
    /**
     * Polygon座標配列の配列
     */
    @field:Json(name = "coordinates")
    val coordinates: List<List<List<List<Double>>>>
) : Geometry(GeometryType.MULTI_POLYGON)
