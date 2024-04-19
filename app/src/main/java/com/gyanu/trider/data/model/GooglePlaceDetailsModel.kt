package com.gyanu.trider.data.model

data class GooglePlaceDetailsModel(
    val html_attributions: List<Any>,
    val result: Results,
    val status: String
)


data class Geometry(
    val location: Location,
    val viewport: Viewport
)

data class Location(
    val lat: Double,
    val lng: Double
)

data class Northeast(
    val lat: Double,
    val lng: Double
)

data class Results(
    val formatted_address: String,
    val geometry: Geometry
)

data class Southwest(
    val lat: Double,
    val lng: Double
)

data class Viewport(
    val northeast: Northeast,
    val southwest: Southwest
)