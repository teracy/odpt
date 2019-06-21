package com.github.teracy.odpt.core.geojson.response

import com.squareup.moshi.Json

/**
 * GeoJSONのジオメトリ："MultiLineString"
 */
data class MultiLineString(
    /**
     * 2つ以上の座標の配列の配列（個々の座標メンバは、投影された座標では[東行, 北行]の順、地理座標では[経度, 緯度]の順）
     */
    @field:Json(name = "coordinates")
    val coordinates: List<List<List<Double>>>
) : Geometry(GeometryType.MULTI_LINE_STRING)
