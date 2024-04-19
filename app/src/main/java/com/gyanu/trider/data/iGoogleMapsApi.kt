package com.gyanu.trider.data

import com.gyanu.trider.data.model.GooglDistanceMatrixDetails
import com.gyanu.trider.data.model.GoogleDirections
import com.gyanu.trider.data.model.GooglePlaceDetailsModel
import com.gyanu.trider.data.model.GoogleReverseGeocodingModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface iGoogleMapsApi {

    @GET("maps/api/geocode/json")
    fun getReverseGeoCodingDetails(@Query("latlng")latlng:String,@Query("result_type")result_type:String="street_address",@Query("key")key:String=Strings.GOOGLEMAPSAPIKEY): Call<GoogleReverseGeocodingModel>

    @GET("maps/api/place/details/json")
    fun getPlaceIdDetails(@Query("place_id")place_id:String,@Query("fields")fields:String="geometry",@Query("key")key:String=Strings.GOOGLEMAPSAPIKEY):Call<GooglePlaceDetailsModel>

    @GET("maps/api/directions/json")
    fun getDirections(@Query("origin")origin:String,@Query("destination")destination:String,@Query("key")key:String=Strings.GOOGLEMAPSAPIKEY):Call<GoogleDirections>

    @GET("maps/api/distancematrix/json")
    fun getDistanceMatrixDetails(@Query("origins")origins:String,@Query("destinations")destinations:String,@Query("key")key:String=Strings.GOOGLEMAPSAPIKEY):Call<GooglDistanceMatrixDetails>


    companion object{
        const val BASE_URL = "https://maps.googleapis.com/"
    }
}