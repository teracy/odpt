package com.github.teracy.odpt.core.geojson.response

import com.squareup.moshi.Json

/**
 * GeoJSONのジオメトリ："Point"
 */
data class Point(
    /**
     * 座標（投影された座標では[東行, 北行]の順、地理座標では[経度, 緯度]の順）
     */
    @field:Json(name = "coordinates")
    val coordinates: List<Double>
) : Geometry(GeometryType.POINT)
