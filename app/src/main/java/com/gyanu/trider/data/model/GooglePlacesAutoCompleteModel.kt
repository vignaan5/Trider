package com.gyanu.trider.data.model

data class GooglePlacesAutoCompleteModel(
    val predictions: List<Prediction>,
    val status: String
)


//Subclasses down below are implemented in above mainclasses
data class MainTextMatchedSubstring(
    val length: Int,
    val offset: Int
)

data class MatchedSubstring(
    val length: Int,
    val offset: Int
)

data class Prediction(
    val description: String,
    val matched_substrings: List<MatchedSubstring>,
    val place_id: String,
    val reference: String,
    val structured_formatting: StructuredFormatting,
    val terms: List<Term>,
    val types: List<String>
)

data class StructuredFormatting(
    val main_text: String,
    val main_text_matched_substrings: List<MainTextMatchedSubstring>,
    val secondary_text: String
)

data class Term(
    val offset: Int,
    val value: String
)


