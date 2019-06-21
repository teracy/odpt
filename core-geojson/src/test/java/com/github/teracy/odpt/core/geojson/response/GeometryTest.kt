package com.github.teracy.odpt.core.geojson.response

import com.github.teracy.odpt.core.geojson.adapter.GeometryAdapter
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.experimental.runners.Enclosed
import org.junit.runner.RunWith

/**
 * GeoJSONのテスト
 * NOTE:テストデータは[https://geojson.org/geojson-spec.html]を利用
 */
@RunWith(Enclosed::class)
class GeometryTest {
    /**
     * デシリアライズのテスト
     */
    class DeserializeTest {
        private val parameterizedType = Types.newParameterizedType(List::class.java, Geometry::class.java)

        lateinit var objectAdapter: JsonAdapter<Geometry>
        lateinit var listAdapter: JsonAdapter<List<Geometry>>

        @Before
        fun setUp() {
            objectAdapter = Moshi.Builder()
                .add(GeometryAdapter.FACTORY)
                .build()
                .adapter(Geometry::class.java)
            listAdapter = Moshi.Builder()
                .add(GeometryAdapter.FACTORY)
                .build()
                .adapter(parameterizedType)
        }

        /**
         * Pointのテスト
         */
        @Test
        fun point() {
            // テストデータ
            val json = "{ \"type\": \"Point\", \"coordinates\": [100.0, 0.0] }"
            val geometry: Geometry? = objectAdapter.fromJson(json)
            assertThat(geometry).isNotNull
            assertThat(geometry!!.javaClass).isEqualTo(Point::class.java)
            assertThat(geometry.geometryTypeValue).isEqualTo("Point")
            assertThat(geometry.geometryType).isEqualTo(GeometryType.POINT)

            val point = geometry as Point
            assertThat(point.coordinates.size).isEqualTo(2)
            assertThat(point.coordinates[0]).isEqualTo(100.0)
            assertThat(point.coordinates[1]).isEqualTo(0.0)
        }

        /**
         * MultiPointのテスト
         */
        @Test
        fun multiPoint() {
            // テストデータ
            val json = "{ \"type\": \"MultiPoint\",\n" +
                    "    \"coordinates\": [ [100.0, 0.0], [101.0, 1.0] ]\n" +
                    "    }"
            val geometry: Geometry? = objectAdapter.fromJson(json)
            assertThat(geometry).isNotNull
            assertThat(geometry!!.javaClass).isEqualTo(MultiPoint::class.java)
            assertThat(geometry.geometryTypeValue).isEqualTo("MultiPoint")
            assertThat(geometry.geometryType).isEqualTo(GeometryType.MULTI_POINT)

            val multiPoint = geometry as MultiPoint
            assertThat(multiPoint.coordinates.size).isEqualTo(2)

            assertThat(multiPoint.coordinates[0].size).isEqualTo(2)
            assertThat(multiPoint.coordinates[0][0]).isEqualTo(100.0)
            assertThat(multiPoint.coordinates[0][1]).isEqualTo(0.0)

            assertThat(multiPoint.coordinates[1].size).isEqualTo(2)
            assertThat(multiPoint.coordinates[1][0]).isEqualTo(101.0)
            assertThat(multiPoint.coordinates[1][1]).isEqualTo(1.0)
        }

        /**
         * LineStringのテスト
         */
        @Test
        fun lineString() {
            // テストデータ
            val json = "{ \"type\": \"LineString\",\n" +
                    "    \"coordinates\": [ [100.0, 0.0], [101.0, 1.0] ]\n" +
                    "    }"
            val geometry: Geometry? = objectAdapter.fromJson(json)
            assertThat(geometry).isNotNull
            assertThat(geometry!!.javaClass).isEqualTo(LineString::class.java)
            assertThat(geometry.geometryTypeValue).isEqualTo("LineString")
            assertThat(geometry.geometryType).isEqualTo(GeometryType.LINE_STRING)

            val lineString = geometry as LineString
            assertThat(lineString.coordinates.size).isEqualTo(2)

            assertThat(lineString.coordinates[0].size).isEqualTo(2)
            assertThat(lineString.coordinates[0][0]).isEqualTo(100.0)
            assertThat(lineString.coordinates[0][1]).isEqualTo(0.0)

            assertThat(lineString.coordinates[1].size).isEqualTo(2)
            assertThat(lineString.coordinates[1][0]).isEqualTo(101.0)
            assertThat(lineString.coordinates[1][1]).isEqualTo(1.0)
        }

        /**
         * MultiLineStringのテスト
         */
        @Test
        fun multiLineString() {
            // テストデータ
            val json = "{ \"type\": \"MultiLineString\",\n" +
                    "    \"coordinates\": [\n" +
                    "        [ [100.0, 0.0], [101.0, 1.0] ],\n" +
                    "        [ [102.0, 2.0], [103.0, 3.0] ]\n" +
                    "      ]\n" +
                    "    }"
            val geometry: Geometry? = objectAdapter.fromJson(json)
            assertThat(geometry).isNotNull
            assertThat(geometry!!.javaClass).isEqualTo(MultiLineString::class.java)
            assertThat(geometry.geometryTypeValue).isEqualTo("MultiLineString")
            assertThat(geometry.geometryType).isEqualTo(GeometryType.MULTI_LINE_STRING)

            val multiLineString = geometry as MultiLineString
            assertThat(multiLineString.coordinates.size).isEqualTo(2)

            assertThat(multiLineString.coordinates[0].size).isEqualTo(2)
            assertThat(multiLineString.coordinates[0][0].size).isEqualTo(2)
            assertThat(multiLineString.coordinates[0][0][0]).isEqualTo(100.0)
            assertThat(multiLineString.coordinates[0][0][1]).isEqualTo(0.0)

            assertThat(multiLineString.coordinates[0][1].size).isEqualTo(2)
            assertThat(multiLineString.coordinates[0][1][0]).isEqualTo(101.0)
            assertThat(multiLineString.coordinates[0][1][1]).isEqualTo(1.0)

            assertThat(multiLineString.coordinates[1][0].size).isEqualTo(2)
            assertThat(multiLineString.coordinates[1][0][0]).isEqualTo(102.0)
            assertThat(multiLineString.coordinates[1][0][1]).isEqualTo(2.0)

            assertThat(multiLineString.coordinates[1][1].size).isEqualTo(2)
            assertThat(multiLineString.coordinates[1][1][0]).isEqualTo(103.0)
            assertThat(multiLineString.coordinates[1][1][1]).isEqualTo(3.0)
        }

        /**
         * Polygonのテスト（穴なし）
         */
        @Test
        fun polygon() {
            // テストデータ
            val json = "{ \"type\": \"Polygon\",\n" +
                    "    \"coordinates\": [\n" +
                    "      [ [100.0, 0.0], [101.0, 0.0], [101.0, 1.0], [100.0, 1.0], [100.0, 0.0] ]\n" +
                    "      ]\n" +
                    "   }"
            val geometry: Geometry? = objectAdapter.fromJson(json)
            assertThat(geometry).isNotNull
            assertThat(geometry!!.javaClass).isEqualTo(Polygon::class.java)
            assertThat(geometry.geometryTypeValue).isEqualTo("Polygon")
            assertThat(geometry.geometryType).isEqualTo(GeometryType.POLYGON)

            val polygon = geometry as Polygon
            assertThat(polygon.coordinates.size).isEqualTo(1)

            assertThat(polygon.coordinates[0].size).isEqualTo(5)

            assertThat(polygon.coordinates[0][0].size).isEqualTo(2)
            assertThat(polygon.coordinates[0][0][0]).isEqualTo(100.0)
            assertThat(polygon.coordinates[0][0][1]).isEqualTo(0.0)

            assertThat(polygon.coordinates[0][1].size).isEqualTo(2)
            assertThat(polygon.coordinates[0][1][0]).isEqualTo(101.0)
            assertThat(polygon.coordinates[0][1][1]).isEqualTo(0.0)

            assertThat(polygon.coordinates[0][2].size).isEqualTo(2)
            assertThat(polygon.coordinates[0][2][0]).isEqualTo(101.0)
            assertThat(polygon.coordinates[0][2][1]).isEqualTo(1.0)

            assertThat(polygon.coordinates[0][3].size).isEqualTo(2)
            assertThat(polygon.coordinates[0][3][0]).isEqualTo(100.0)
            assertThat(polygon.coordinates[0][3][1]).isEqualTo(1.0)

            assertThat(polygon.coordinates[0][4].size).isEqualTo(2)
            assertThat(polygon.coordinates[0][4][0]).isEqualTo(100.0)
            assertThat(polygon.coordinates[0][4][1]).isEqualTo(0.0)
        }

        /**
         * Polygonのテスト（穴あり）
         */
        @Test
        fun polygonWithHole() {
            // テストデータ
            val json = "{ \"type\": \"Polygon\",\n" +
                    "    \"coordinates\": [\n" +
                    "      [ [100.0, 0.0], [101.0, 0.0], [101.0, 1.0], [100.0, 1.0], [100.0, 0.0] ],\n" +
                    "      [ [100.2, 0.2], [100.8, 0.2], [100.8, 0.8], [100.2, 0.8], [100.2, 0.2] ]\n" +
                    "      ]\n" +
                    "   }"
            val geometry: Geometry? = objectAdapter.fromJson(json)
            assertThat(geometry).isNotNull
            assertThat(geometry!!.javaClass).isEqualTo(Polygon::class.java)
            assertThat(geometry.geometryTypeValue).isEqualTo("Polygon")
            assertThat(geometry.geometryType).isEqualTo(GeometryType.POLYGON)

            val polygon = geometry as Polygon
            assertThat(polygon.coordinates.size).isEqualTo(2)

            assertThat(polygon.coordinates[0].size).isEqualTo(5)

            assertThat(polygon.coordinates[0][0].size).isEqualTo(2)
            assertThat(polygon.coordinates[0][0][0]).isEqualTo(100.0)
            assertThat(polygon.coordinates[0][0][1]).isEqualTo(0.0)

            assertThat(polygon.coordinates[0][1].size).isEqualTo(2)
            assertThat(polygon.coordinates[0][1][0]).isEqualTo(101.0)
            assertThat(polygon.coordinates[0][1][1]).isEqualTo(0.0)

            assertThat(polygon.coordinates[0][2].size).isEqualTo(2)
            assertThat(polygon.coordinates[0][2][0]).isEqualTo(101.0)
            assertThat(polygon.coordinates[0][2][1]).isEqualTo(1.0)

            assertThat(polygon.coordinates[0][3].size).isEqualTo(2)
            assertThat(polygon.coordinates[0][3][0]).isEqualTo(100.0)
            assertThat(polygon.coordinates[0][3][1]).isEqualTo(1.0)

            assertThat(polygon.coordinates[0][4].size).isEqualTo(2)
            assertThat(polygon.coordinates[0][4][0]).isEqualTo(100.0)
            assertThat(polygon.coordinates[0][4][1]).isEqualTo(0.0)

            assertThat(polygon.coordinates[1].size).isEqualTo(5)

            assertThat(polygon.coordinates[1][0].size).isEqualTo(2)
            assertThat(polygon.coordinates[1][0][0]).isEqualTo(100.2)
            assertThat(polygon.coordinates[1][0][1]).isEqualTo(0.2)

            assertThat(polygon.coordinates[1][1].size).isEqualTo(2)
            assertThat(polygon.coordinates[1][1][0]).isEqualTo(100.8)
            assertThat(polygon.coordinates[1][1][1]).isEqualTo(0.2)

            assertThat(polygon.coordinates[1][2].size).isEqualTo(2)
            assertThat(polygon.coordinates[1][2][0]).isEqualTo(100.8)
            assertThat(polygon.coordinates[1][2][1]).isEqualTo(0.8)

            assertThat(polygon.coordinates[1][3].size).isEqualTo(2)
            assertThat(polygon.coordinates[1][3][0]).isEqualTo(100.2)
            assertThat(polygon.coordinates[1][3][1]).isEqualTo(0.8)

            assertThat(polygon.coordinates[1][4].size).isEqualTo(2)
            assertThat(polygon.coordinates[1][4][0]).isEqualTo(100.2)
            assertThat(polygon.coordinates[1][4][1]).isEqualTo(0.2)
        }

        /**
         * MultiPolygonのテスト
         */
        @Test
        fun multiPolygon() {
            // テストデータ
            val json = "{ \"type\": \"MultiPolygon\",\n" +
                    "    \"coordinates\": [\n" +
                    "      [[[102.0, 2.0], [103.0, 2.0], [103.0, 3.0], [102.0, 3.0], [102.0, 2.0]]],\n" +
                    "      [[[100.0, 0.0], [101.0, 0.0], [101.0, 1.0], [100.0, 1.0], [100.0, 0.0]],\n" +
                    "       [[100.2, 0.2], [100.8, 0.2], [100.8, 0.8], [100.2, 0.8], [100.2, 0.2]]]\n" +
                    "      ]\n" +
                    "    }"
            val geometry: Geometry? = objectAdapter.fromJson(json)
            assertThat(geometry).isNotNull
            assertThat(geometry!!.javaClass).isEqualTo(MultiPolygon::class.java)
            assertThat(geometry.geometryTypeValue).isEqualTo("MultiPolygon")
            assertThat(geometry.geometryType).isEqualTo(GeometryType.MULTI_POLYGON)

            val multiPolygon = geometry as MultiPolygon
            assertThat(multiPolygon.coordinates.size).isEqualTo(2)

            assertThat(multiPolygon.coordinates[0].size).isEqualTo(1)
            // region Polygon0
            assertThat(multiPolygon.coordinates[0][0].size).isEqualTo(5)

            assertThat(multiPolygon.coordinates[0][0][0].size).isEqualTo(2)
            assertThat(multiPolygon.coordinates[0][0][0][0]).isEqualTo(102.0)
            assertThat(multiPolygon.coordinates[0][0][0][1]).isEqualTo(2.0)

            assertThat(multiPolygon.coordinates[0][0][1].size).isEqualTo(2)
            assertThat(multiPolygon.coordinates[0][0][1][0]).isEqualTo(103.0)
            assertThat(multiPolygon.coordinates[0][0][1][1]).isEqualTo(2.0)

            assertThat(multiPolygon.coordinates[0][0][2].size).isEqualTo(2)
            assertThat(multiPolygon.coordinates[0][0][2][0]).isEqualTo(103.0)
            assertThat(multiPolygon.coordinates[0][0][2][1]).isEqualTo(3.0)

            assertThat(multiPolygon.coordinates[0][0][3].size).isEqualTo(2)
            assertThat(multiPolygon.coordinates[0][0][3][0]).isEqualTo(102.0)
            assertThat(multiPolygon.coordinates[0][0][3][1]).isEqualTo(3.0)

            assertThat(multiPolygon.coordinates[0][0][4].size).isEqualTo(2)
            assertThat(multiPolygon.coordinates[0][0][4][0]).isEqualTo(102.0)
            assertThat(multiPolygon.coordinates[0][0][4][1]).isEqualTo(2.0)
            // endregion

            assertThat(multiPolygon.coordinates[1].size).isEqualTo(2)
            // region Polygon1-0
            assertThat(multiPolygon.coordinates[1][0].size).isEqualTo(5)

            assertThat(multiPolygon.coordinates[1][0][0].size).isEqualTo(2)
            assertThat(multiPolygon.coordinates[1][0][0][0]).isEqualTo(100.0)
            assertThat(multiPolygon.coordinates[1][0][0][1]).isEqualTo(0.0)

            assertThat(multiPolygon.coordinates[1][0][1].size).isEqualTo(2)
            assertThat(multiPolygon.coordinates[1][0][1][0]).isEqualTo(101.0)
            assertThat(multiPolygon.coordinates[1][0][1][1]).isEqualTo(0.0)

            assertThat(multiPolygon.coordinates[1][0][2].size).isEqualTo(2)
            assertThat(multiPolygon.coordinates[1][0][2][0]).isEqualTo(101.0)
            assertThat(multiPolygon.coordinates[1][0][2][1]).isEqualTo(1.0)

            assertThat(multiPolygon.coordinates[1][0][3].size).isEqualTo(2)
            assertThat(multiPolygon.coordinates[1][0][3][0]).isEqualTo(100.0)
            assertThat(multiPolygon.coordinates[1][0][3][1]).isEqualTo(1.0)

            assertThat(multiPolygon.coordinates[1][0][4].size).isEqualTo(2)
            assertThat(multiPolygon.coordinates[1][0][4][0]).isEqualTo(100.0)
            assertThat(multiPolygon.coordinates[1][0][4][1]).isEqualTo(0.0)
            // endregion
            // region Polygon1-1
            assertThat(multiPolygon.coordinates[1][1].size).isEqualTo(5)

            assertThat(multiPolygon.coordinates[1][1][0].size).isEqualTo(2)
            assertThat(multiPolygon.coordinates[1][1][0][0]).isEqualTo(100.2)
            assertThat(multiPolygon.coordinates[1][1][0][1]).isEqualTo(0.2)

            assertThat(multiPolygon.coordinates[1][1][1].size).isEqualTo(2)
            assertThat(multiPolygon.coordinates[1][1][1][0]).isEqualTo(100.8)
            assertThat(multiPolygon.coordinates[1][1][1][1]).isEqualTo(0.2)

            assertThat(multiPolygon.coordinates[1][1][2].size).isEqualTo(2)
            assertThat(multiPolygon.coordinates[1][1][2][0]).isEqualTo(100.8)
            assertThat(multiPolygon.coordinates[1][1][2][1]).isEqualTo(0.8)

            assertThat(multiPolygon.coordinates[1][1][3].size).isEqualTo(2)
            assertThat(multiPolygon.coordinates[1][1][3][0]).isEqualTo(100.2)
            assertThat(multiPolygon.coordinates[1][1][3][1]).isEqualTo(0.8)

            assertThat(multiPolygon.coordinates[1][1][4].size).isEqualTo(2)
            assertThat(multiPolygon.coordinates[1][1][4][0]).isEqualTo(100.2)
            assertThat(multiPolygon.coordinates[1][1][4][1]).isEqualTo(0.2)
            // endregion
        }


        /**
         * GeometryCollectionのテスト
         */
        @Test
        fun geometryCollection() {
            // テストデータ
            val json = "{ \"type\": \"GeometryCollection\",\n" +
                    "    \"geometries\": [\n" +
                    "      { \"type\": \"Point\",\n" +
                    "        \"coordinates\": [100.0, 0.0]\n" +
                    "        },\n" +
                    "      { \"type\": \"LineString\",\n" +
                    "        \"coordinates\": [ [101.0, 0.0], [102.0, 1.0] ]\n" +
                    "        }\n" +
                    "    ]\n" +
                    "  }"
            val geometry: Geometry? = objectAdapter.fromJson(json)
            assertThat(geometry).isNotNull
            assertThat(geometry!!.javaClass).isEqualTo(GeometryCollection::class.java)
            assertThat(geometry.geometryTypeValue).isEqualTo("GeometryCollection")
            assertThat(geometry.geometryType).isEqualTo(GeometryType.GEOMETRY_COLLECTION)

            val geometryCollection = geometry as GeometryCollection
            assertThat(geometryCollection.geometries.size).isEqualTo(2)

            assertThat(geometryCollection.geometries[0].geometryTypeValue).isEqualTo("Point")
            assertThat(geometryCollection.geometries[0].geometryType).isEqualTo(GeometryType.POINT)
            val point0 = geometryCollection.geometries[0] as Point
            assertThat(point0.coordinates.size).isEqualTo(2)
            assertThat(point0.coordinates[0]).isEqualTo(100.0)
            assertThat(point0.coordinates[1]).isEqualTo(0.0)

            assertThat(geometryCollection.geometries[1].geometryTypeValue).isEqualTo("LineString")
            assertThat(geometryCollection.geometries[1].geometryType).isEqualTo(GeometryType.LINE_STRING)
            val lineString1 = geometryCollection.geometries[1] as LineString
            assertThat(lineString1.coordinates.size).isEqualTo(2)

            assertThat(lineString1.coordinates[0].size).isEqualTo(2)
            assertThat(lineString1.coordinates[0][0]).isEqualTo(101.0)
            assertThat(lineString1.coordinates[0][1]).isEqualTo(0.0)

            assertThat(lineString1.coordinates[1].size).isEqualTo(2)
            assertThat(lineString1.coordinates[1][0]).isEqualTo(102.0)
            assertThat(lineString1.coordinates[1][1]).isEqualTo(1.0)
        }

        /**
         * Featureのテスト
         */
        @Test
        fun feature() {
            // テストデータ
            val json = "{ \"type\": \"Feature\",\n" +
                    "      \"geometry\": {\"type\": \"Point\", \"coordinates\": [102.0, 0.5]},\n" +
                    "      \"properties\": {\"prop0\": \"value0\"}\n" +
                    "      }"
            val geometry: Geometry? = objectAdapter.fromJson(json)
            assertThat(geometry).isNotNull
            assertThat(geometry!!.javaClass).isEqualTo(Feature::class.java)
            assertThat(geometry.geometryTypeValue).isEqualTo("Feature")
            assertThat(geometry.geometryType).isEqualTo(GeometryType.FEATURE)

            val feature = geometry as Feature
            assertThat(feature.properties).isNotNull
            assertThat(feature.properties!!.size).isEqualTo(1)
            assertThat(feature.properties!!["prop0"]).isEqualTo("value0")

            assertThat(feature.geometry).isNotNull
            assertThat(feature.geometry!!.geometryTypeValue).isEqualTo("Point")
            assertThat(feature.geometry!!.geometryType).isEqualTo(GeometryType.POINT)

            val point = feature.geometry as Point
            assertThat(point.coordinates.size).isEqualTo(2)
            assertThat(point.coordinates[0]).isEqualTo(102.0)
            assertThat(point.coordinates[1]).isEqualTo(0.5)
        }


        /**
         * FeatureCollectionのテスト
         */
        @Test
        fun featureCollection() {
            // テストデータ
            val json = "{ \"type\": \"FeatureCollection\",\n" +
                    "  \"features\": [\n" +
                    "    { \"type\": \"Feature\",\n" +
                    "      \"geometry\": {\"type\": \"Point\", \"coordinates\": [102.0, 0.5]},\n" +
                    "      \"properties\": {\"prop0\": \"value0\"}\n" +
                    "      },\n" +
                    "    { \"type\": \"Feature\",\n" +
                    "      \"geometry\": {\n" +
                    "        \"type\": \"LineString\",\n" +
                    "        \"coordinates\": [\n" +
                    "          [102.0, 0.0], [103.0, 1.0], [104.0, 0.0], [105.0, 1.0]\n" +
                    "          ]\n" +
                    "        },\n" +
                    "      \"properties\": {\n" +
                    "        \"prop0\": \"value0\",\n" +
                    "        \"prop1\": 0.0\n" +
                    "        }\n" +
                    "      },\n" +
                    "    { \"type\": \"Feature\",\n" +
                    "       \"geometry\": {\n" +
                    "         \"type\": \"Polygon\",\n" +
                    "         \"coordinates\": [\n" +
                    "           [ [100.0, 0.0], [101.0, 0.0], [101.0, 1.0],\n" +
                    "             [100.0, 1.0], [100.0, 0.0] ]\n" +
                    "           ]\n" +
                    "       },\n" +
                    "       \"properties\": {\n" +
                    "         \"prop0\": \"value0\",\n" +
                    "         \"prop1\": {\"this\": \"that\"}\n" +
                    "         }\n" +
                    "       }\n" +
                    "     ]\n" +
                    "   }"
            val geometry: Geometry? = objectAdapter.fromJson(json)
            assertThat(geometry).isNotNull
            assertThat(geometry!!.javaClass).isEqualTo(FeatureCollection::class.java)
            assertThat(geometry.geometryTypeValue).isEqualTo("FeatureCollection")
            assertThat(geometry.geometryType).isEqualTo(GeometryType.FEATURE_COLLECTION)

            val featureCollection = geometry as FeatureCollection
            assertThat(featureCollection.features).isNotNull
            assertThat(featureCollection.features.size).isEqualTo(3)

            // region features[0]
            val feature0 = featureCollection.features[0]
            assertThat(feature0.properties).isNotNull
            assertThat(feature0.properties!!.size).isEqualTo(1)
            assertThat(feature0.properties!!["prop0"]).isEqualTo("value0")

            assertThat(feature0.geometry).isNotNull
            assertThat(feature0.geometry!!.geometryTypeValue).isEqualTo("Point")
            assertThat(feature0.geometry!!.geometryType).isEqualTo(GeometryType.POINT)

            val point0 = feature0.geometry as Point
            assertThat(point0.coordinates.size).isEqualTo(2)
            assertThat(point0.coordinates[0]).isEqualTo(102.0)
            assertThat(point0.coordinates[1]).isEqualTo(0.5)
            // endregion

            // region features[1]
            val feature1 = featureCollection.features[1]
            assertThat(feature1.properties).isNotNull
            assertThat(feature1.properties!!.size).isEqualTo(2)
            assertThat(feature1.properties!!["prop0"]).isEqualTo("value0")
            assertThat(feature1.properties!!["prop1"]).isEqualTo(0.0)

            assertThat(feature1.geometry).isNotNull
            assertThat(feature1.geometry!!.geometryTypeValue).isEqualTo("LineString")
            assertThat(feature1.geometry!!.geometryType).isEqualTo(GeometryType.LINE_STRING)

            val lineString1 = feature1.geometry as LineString
            assertThat(lineString1.coordinates.size).isEqualTo(4)

            assertThat(lineString1.coordinates[0].size).isEqualTo(2)
            assertThat(lineString1.coordinates[0][0]).isEqualTo(102.0)
            assertThat(lineString1.coordinates[0][1]).isEqualTo(0.0)

            assertThat(lineString1.coordinates[3].size).isEqualTo(2)
            assertThat(lineString1.coordinates[3][0]).isEqualTo(105.0)
            assertThat(lineString1.coordinates[3][1]).isEqualTo(1.0)
            // endregion

            // region features[2]
            val feature2 = featureCollection.features[2]
            assertThat(feature2.properties).isNotNull
            assertThat(feature2.properties!!.size).isEqualTo(2)
            assertThat(feature2.properties!!["prop0"]).isEqualTo("value0")
            assertThat(feature2.properties!!["prop1"]).isEqualTo(mapOf(Pair("this", "that")))

            assertThat(feature2.geometry).isNotNull
            assertThat(feature2.geometry!!.geometryTypeValue).isEqualTo("Polygon")
            assertThat(feature2.geometry!!.geometryType).isEqualTo(GeometryType.POLYGON)

            val polygon2 = feature2.geometry as Polygon
            assertThat(polygon2.coordinates.size).isEqualTo(1)

            assertThat(polygon2.coordinates[0].size).isEqualTo(5)

            assertThat(polygon2.coordinates[0][0].size).isEqualTo(2)
            assertThat(polygon2.coordinates[0][0][0]).isEqualTo(100.0)
            assertThat(polygon2.coordinates[0][0][1]).isEqualTo(0.0)

            assertThat(polygon2.coordinates[0][1].size).isEqualTo(2)
            assertThat(polygon2.coordinates[0][1][0]).isEqualTo(101.0)
            assertThat(polygon2.coordinates[0][1][1]).isEqualTo(0.0)

            assertThat(polygon2.coordinates[0][4].size).isEqualTo(2)
            assertThat(polygon2.coordinates[0][4][0]).isEqualTo(100.0)
            assertThat(polygon2.coordinates[0][4][1]).isEqualTo(0.0)
            // endregion

        }
    }
}
