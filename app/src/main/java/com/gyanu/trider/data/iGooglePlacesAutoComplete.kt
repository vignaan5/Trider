package com.gyanu.trider.data

import com.gyanu.trider.data.model.GooglePlacesAutoCompleteModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface iGooglePlacesAutoComplete {

    @GET("maps/api/place/autocomplete/json")
    fun getPredictions(@Query("input")input:String, @Query("key")key:String): Call<GooglePlacesAutoCompleteModel>

    companion object{
        const val BASE_URL = "https://maps.googleapis.com/"
    }

}