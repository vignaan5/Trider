package com.gyanu.trider.data.model

data class GoogleReverseGeocodingModel(
    val plus_code: PlusCode,
    val results: List<Result>,
    val status: String
)

data class AddressComponent(
    val long_name: String,
    val short_name: String,
    val types: List<String>
)

data class Result(
    val address_components: List<AddressComponent>,
    val formatted_address: String,
    val geometry: Geometry,
    val place_id: String,
    val plus_code: PlusCode,
    val types: List<String>
)


data class PlusCode(
    val compound_code: String,
    val global_code: String
)

