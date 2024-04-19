package com.gyanu.trider.presentation

import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.maps.android.compose.MapProperties
import com.gyanu.trider.ui.theme.WhiteGrayMapStyle

data class MapState (

   val Properties: MapProperties = MapProperties(mapStyleOptions = MapStyleOptions(WhiteGrayMapStyle.Json), isMyLocationEnabled = false),
   val mapHeight:Float = 1f,
   val mapWidth:Float = 1f,
   val pickupAddressLocationString:String = "",
   val selecteddropAddressLocationString:String="",
   val dropAddressLocationString:String = "",
   val dropAddressLocationString2:String = "",
   val selecteddropAddressLocationString2:String="",
   val openMenu:Boolean = false,
   val pickupPinLoaction:LatLng = LatLng(0.0,0.0),
   val dropPinLocation:LatLng = LatLng(0.0,0.0),
   val selectedDropPinLocation:LatLng = LatLng(0.0,0.0),
   val selectedDropPinLocation2:LatLng = LatLng(0.0,0.0),
   val dropPinLocation2:LatLng = LatLng(0.0,0.0),
   val tempMovieDropPinLocation:LatLng = LatLng(0.0,0.0),
   val selectedtempMovieDropPinLocation:LatLng = LatLng(0.0,0.0),
   val selectedtempMovieDropPinLocation2:LatLng = LatLng(0.0,0.0),
   val tempMovieDropPinLocation2:LatLng = LatLng(0.0,0.0),
   val tempMoviePickupPinLocation:LatLng = LatLng(0.0,0.0),
   val pickupLocationPlaceId:String = "",
   val dropLocationPlaceId:String = "",
   val dropLocationPlaceId2:String = "",
   val dont_trigger_reverse_geocoding:Boolean=false,
   val dont_trigger_drop_reverse_geocoding:Boolean=false,
   val dont_trigger_drop_reverse_geocoding2:Boolean=false,
   val polylinemapHeight:Float = 0.5f,
   val polylinemapWidth:Float = 1f,
   val pickupPlaceholder:String = "PickUp?",
   val selectedDropPlaceholder:String="Drop",
   val selectedDropPlaceholder2:String="Drop",
   val dropPlaceholder:String = "Drop?",
   val dropPlaceholder2:String = "Drop?",
   val canNavigate:Boolean = true,
   val mapBlur:Int =0,
   val has2destinations:Boolean = false

)

