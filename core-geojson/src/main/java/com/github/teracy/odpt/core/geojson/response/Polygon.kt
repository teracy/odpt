package com.github.teracy.odpt.core.geojson.response

import com.squareup.moshi.Json

/**
 * GeoJSONのジオメトリ："Polygon"
 */
data class Polygon(
    /**
     * LinearRing座標配列の配列
     * （LinearRingは4つ以上の位置を持ってLineStringで閉じられた折れ線です）
     * （個々の座標メンバは、投影された座標では[東行, 北行]の順、地理座標では[経度, 緯度]の順）
     *
     * 一番外側のListにおいて、メンバが1つであれば穴のないPolygon、2つであれば2つ目のLinerRingによって穴が定義される
     */
    @field:Json(name = "coordinates")
    val coordinates: List<List<List<Double>>>
) : Geometry(GeometryType.POLYGON)
