package com.github.teracy.odpt.core.geojson.response

import com.squareup.moshi.Json

/**
 * GeoJSONのジオメトリ基底クラス
 */
open class Geometry(
    /**
     * ジオメトリの種類を規定する
     */
    @field:Json(name = "type")
    val geometryTypeValue: String
) {
    constructor(geometryType: GeometryType) : this(geometryType.typeValue)
    val geometryType: GeometryType?
        get() = GeometryType.fromTypeString(geometryTypeValue)
}
