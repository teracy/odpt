package com.github.teracy.odpt.core.geojson.response

import com.squareup.moshi.Json

/**
 * GeoJSONのジオメトリ："GeometryCollection"
 */
data class GeometryCollection(
    /**
     * ジオメトリオブジェクトの集合
     */
    @field:Json(name = "geometries")
    val geometries: List<Geometry>
) : Geometry(GeometryType.GEOMETRY_COLLECTION)
