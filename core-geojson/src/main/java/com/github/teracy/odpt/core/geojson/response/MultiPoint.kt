package com.github.teracy.odpt.core.geojson.response

import com.squareup.moshi.Json

/**
 * GeoJSONのジオメトリ："MultiPoint"
 */
data class MultiPoint(
    /**
     * 座標の配列（個々の座標メンバは、投影された座標では[東行, 北行]の順、地理座標では[経度, 緯度]の順）
     */
    @field:Json(name = "coordinates")
    val coordinates: List<List<Double>>
) : Geometry(GeometryType.MULTI_POINT)
