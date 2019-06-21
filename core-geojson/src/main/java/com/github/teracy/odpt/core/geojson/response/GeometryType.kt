package com.github.teracy.odpt.core.geojson.response

/**
 * GeoJSONのジオメトリの"type"メンバの値
 */
enum class GeometryType(val typeValue: String) {
    POINT("Point"),
    MULTI_POINT("MultiPoint"),
    LINE_STRING("LineString"),
    MULTI_LINE_STRING("MultiLineString"),
    POLYGON("Polygon"),
    MULTI_POLYGON("MultiPolygon"),
    GEOMETRY_COLLECTION("GeometryCollection"),
    FEATURE("Feature"),
    FEATURE_COLLECTION("FeatureCollection");

    companion object {
        /**
         * 文字列からの変換
         */
        fun fromTypeString(string: String?): GeometryType? {
            return GeometryType.values().firstOrNull { it.typeValue == string }
        }
    }
}
