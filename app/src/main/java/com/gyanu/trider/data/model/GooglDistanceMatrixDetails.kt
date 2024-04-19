package com.gyanu.trider.data.model

data class GooglDistanceMatrixDetails(
    val destination_addresses: List<String>,
    val origin_addresses: List<String>,
    val rows: List<Row>,
    val status: String
)


data class Element(
    val distance: Distance,
    val duration: Duration,
    val status: String
)

data class Row(
    val elements: List<Element>
)