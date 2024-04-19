package com.gyanu.trider.presentation

import android.annotation.SuppressLint
import androidx.compose.material3.BottomSheetScaffoldState
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.CameraPositionState
import com.google.maps.android.compose.MapType
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.maps.android.ktx.model.cameraPosition
import com.gyanu.trider.data.Strings
import com.gyanu.trider.data.iGoogleMapsApi
import com.gyanu.trider.data.iGooglePlacesAutoComplete
import com.gyanu.trider.data.model.GoogleDirections
import com.gyanu.trider.data.model.GoogleReverseGeocodingModel
import com.gyanu.trider.data.model.PlusCode
import com.gyanu.trider.data.model.Prediction
import com.gyanu.trider.data.model.Results
import com.gyanu.trider.data.model.decode
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.await
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

@OptIn(ExperimentalMaterial3Api::class)
class MapViewModel:ViewModel() {



var polylineLis:List<LatLng> by mutableStateOf(emptyList())
    var polylineLis2:List<LatLng> by mutableStateOf(emptyList())

var mstate  by   mutableStateOf(MapState())

    var cameraPositionVmState = CameraPositionState(position = CameraPosition.fromLatLngZoom(LatLng(17.4065, 78.4772),15f))
    var dropCameraPositionVmState = CameraPositionState(position = CameraPosition.fromLatLngZoom(LatLng(17.4065, 78.4772),15f))
    var dropCameraPositionVmState2 = CameraPositionState(position = CameraPosition.fromLatLngZoom(LatLng(17.4065, 78.4772),15f))

    val mDrawerState = DrawerState(DrawerValue.Closed)

 var pickupPredictionsList:List<Prediction> by mutableStateOf(emptyList())
    var dropPredictionsList:List<Prediction> by mutableStateOf(emptyList())
    var dropPredictionsList2:List<Prediction> by mutableStateOf(emptyList())


    var gDirections:GoogleDirections? by mutableStateOf(null)
    var gDirections2:GoogleDirections? by mutableStateOf(null)

    var pickupReverseGeoApiPlusCodeData:List<Results> by mutableStateOf(emptyList())

var islocationpermissiongiven by mutableStateOf(false)




    @SuppressLint("SuspiciousIndentation")
    fun OnEvent(event:AppEvents, arg:String, optionalArg:Any=-1){
        when(event)
        {
            is AppEvents.EnableMyLocation -> {



               mstate = mstate.copy(Properties = mstate.Properties.copy(isMyLocationEnabled = true))
            }


            is AppEvents.PickUpAddressTextChanged -> {

                mstate = mstate.copy(pickupAddressLocationString = arg)

                               if(arg=="" || arg.length<4)
                               {
                                      if(arg.length<4)
                                   pickupPredictionsList = emptyList()
                                   return
                               }
                viewModelScope.launch(Dispatchers.IO) {


               val  api = Retrofit.Builder().baseUrl(Strings.GooglePlacesAutocompeleBaseURL).addConverterFactory(GsonConverterFactory.create()).build().create(iGooglePlacesAutoComplete::class.java)


                val tempPredictions = api.getPredictions(input = arg, key =  Strings.GOOGLEMAPSAPIKEY).await()

                pickupPredictionsList = tempPredictions.predictions


               }
            }

            is AppEvents.DropAddressTextChanged ->{
                mstate = mstate.copy(dropAddressLocationString = arg)

                if(arg=="" || arg.length<4)
                {
                    if(arg.length<4)
                        dropPredictionsList = emptyList()
                    return
                }
                viewModelScope.launch(Dispatchers.IO) {


                    val  api = Retrofit.Builder().baseUrl(Strings.GooglePlacesAutocompeleBaseURL).addConverterFactory(GsonConverterFactory.create()).build().create(iGooglePlacesAutoComplete::class.java)


                    val tempPredictions = api.getPredictions(input = arg, key =  Strings.GOOGLEMAPSAPIKEY).await()

                    dropPredictionsList = tempPredictions.predictions


                }

            }

            is AppEvents.SelectedDropAddressTextChanged ->{
                mstate = mstate.copy(selecteddropAddressLocationString = arg)

                if(arg=="" || arg.length<4)
                {
                    if(arg.length<4)
                        dropPredictionsList = emptyList()
                    return
                }
                viewModelScope.launch(Dispatchers.IO) {


                    val  api = Retrofit.Builder().baseUrl(Strings.GooglePlacesAutocompeleBaseURL).addConverterFactory(GsonConverterFactory.create()).build().create(iGooglePlacesAutoComplete::class.java)


                    val tempPredictions = api.getPredictions(input = arg, key =  Strings.GOOGLEMAPSAPIKEY).await()

                    dropPredictionsList = tempPredictions.predictions


                }

            }
            is AppEvents.SelectedDropAddressTextChanged2 ->{
                mstate = mstate.copy(selecteddropAddressLocationString2 = arg)

                if(arg=="" || arg.length<4)
                {
                    if(arg.length<4)
                        dropPredictionsList2 = emptyList()
                    return
                }
                viewModelScope.launch(Dispatchers.IO) {


                    val  api = Retrofit.Builder().baseUrl(Strings.GooglePlacesAutocompeleBaseURL).addConverterFactory(GsonConverterFactory.create()).build().create(iGooglePlacesAutoComplete::class.java)


                    val tempPredictions = api.getPredictions(input = arg, key =  Strings.GOOGLEMAPSAPIKEY).await()

                    dropPredictionsList2 = tempPredictions.predictions


                }

            }
            is AppEvents.DropAddressTextChanged2 ->{
                mstate = mstate.copy(dropAddressLocationString2 = arg)

                if(arg=="" || arg.length<4)
                {
                    if(arg.length<4)
                        dropPredictionsList2 = emptyList()
                    return
                }
                viewModelScope.launch(Dispatchers.IO) {


                    val  api = Retrofit.Builder().baseUrl(Strings.GooglePlacesAutocompeleBaseURL).addConverterFactory(GsonConverterFactory.create()).build().create(iGooglePlacesAutoComplete::class.java)


                    val tempPredictions = api.getPredictions(input = arg, key =  Strings.GOOGLEMAPSAPIKEY).await()

                    dropPredictionsList2 = tempPredictions.predictions


                }

            }
            is AppEvents.ConfirmDrop->{
                mstate = mstate.copy(dropAddressLocationString = mstate.selecteddropAddressLocationString, dropPinLocation = mstate.selectedDropPinLocation, tempMovieDropPinLocation = mstate.selectedtempMovieDropPinLocation)
                dropPredictionsList= emptyList()
            }
            is AppEvents.ConfirmDrop2->{
                mstate = mstate.copy(dropAddressLocationString2 = mstate.selecteddropAddressLocationString2, dropPinLocation2 = mstate.selectedDropPinLocation2, tempMovieDropPinLocation2 = mstate.selectedtempMovieDropPinLocation2)
                dropPredictionsList2= emptyList()
            }

            is AppEvents.ClearDropAddressText -> {
               mstate = mstate.copy(dropAddressLocationString = "", mapBlur = 0)
                dropPredictionsList = emptyList()
            }
            is AppEvents.ClearSelectedDropAddressText -> {
                mstate = mstate.copy(selecteddropAddressLocationString = "", mapBlur = 0)
                dropPredictionsList = emptyList()
            }
            is AppEvents.ClearSelectedDropAddressText2 -> {
                mstate = mstate.copy(selecteddropAddressLocationString2 = "", mapBlur = 0)
                dropPredictionsList2 = emptyList()
            }
            is AppEvents.ClearDropAddressText2 -> {
                mstate = mstate.copy(dropAddressLocationString2 = "", mapBlur = 0)
                dropPredictionsList2 = emptyList()
            }

            is AppEvents.OpenMenuNavigationDrawer ->{

                           mstate = mstate.copy(openMenu = true)





            }

            is AppEvents.ToggleMapType->{
                   if(mstate.Properties.mapType==MapType.SATELLITE)
                   {
                       mstate = mstate.copy(mstate.Properties.copy(mapType = MapType.NORMAL))

                   }
               else mstate = mstate.copy(mstate.Properties.copy(mapType = MapType.SATELLITE))
            }


            is AppEvents.ClearPickupAddressText ->{
                mstate = mstate.copy(pickupAddressLocationString = "", mapBlur = 0)
                pickupPredictionsList = emptyList()
            }

            is AppEvents.PickupCamMoving -> {

                    if(!mstate.dont_trigger_reverse_geocoding) {
                        mstate = mstate.copy(tempMoviePickupPinLocation = cameraPositionVmState.position.target)
                    }
            }
            is AppEvents.DropCamMoving -> {

                if(!mstate.dont_trigger_drop_reverse_geocoding) {
                    mstate = mstate.copy(selectedtempMovieDropPinLocation = dropCameraPositionVmState.position.target)
                }
            }
            is AppEvents.DropCamMoving2 -> {

                if(!mstate.dont_trigger_drop_reverse_geocoding2) {
                    mstate = mstate.copy(selectedtempMovieDropPinLocation2 = dropCameraPositionVmState2.position.target)
                }
            }
            is AppEvents.FetchPickupLocation -> {
                mstate = mstate.copy(pickupPinLoaction = mstate.tempMoviePickupPinLocation, pickupAddressLocationString = "", pickupPlaceholder = "FetchingLocation...")

                val  api = Retrofit.Builder().baseUrl(Strings.GooglePlacesAutocompeleBaseURL).addConverterFactory(GsonConverterFactory.create()).build().create(iGoogleMapsApi::class.java)


                viewModelScope.launch {

                 val res =    api.getReverseGeoCodingDetails(latlng = mstate.pickupPinLoaction.latitude.toString()+","+mstate.pickupPinLoaction.longitude).await()


                    if(!res.results.isEmpty())
                    {
                        mstate = mstate.copy(pickupAddressLocationString = res.results[0].formatted_address)

                    }
                    else if(res.plus_code.compound_code!=null){

                        mstate = mstate.copy(pickupAddressLocationString = res.plus_code.compound_code)

                    }

                    mstate = mstate.copy(pickupPlaceholder = "Pickup?")

                }


            }
            is AppEvents.FetchDropLocation ->{  mstate = mstate.copy(dropPinLocation = mstate.tempMoviePickupPinLocation, dropAddressLocationString = "", dropPlaceholder = "FetchingLocation...")

                val  api = Retrofit.Builder().baseUrl(Strings.GooglePlacesAutocompeleBaseURL).addConverterFactory(GsonConverterFactory.create()).build().create(iGoogleMapsApi::class.java)


                viewModelScope.launch {

                    val res =    api.getReverseGeoCodingDetails(latlng = mstate.dropPinLocation.latitude.toString()+","+mstate.dropPinLocation.longitude).await()


                    if(!res.results.isEmpty())
                    {
                        mstate = mstate.copy(dropAddressLocationString = res.results[0].formatted_address)

                    }
                    else if(res.plus_code.compound_code!=null){

                        mstate = mstate.copy(dropAddressLocationString = res.plus_code.compound_code)

                    }

                    mstate = mstate.copy(dropPlaceholder = "Drop?")

                }

            }
            is AppEvents.FetchSelectedDropLocation ->{  mstate = mstate.copy(selectedDropPinLocation = mstate.selectedtempMovieDropPinLocation, selecteddropAddressLocationString = "", selectedDropPlaceholder = "FetchingLocation...")

                val  api = Retrofit.Builder().baseUrl(Strings.GooglePlacesAutocompeleBaseURL).addConverterFactory(GsonConverterFactory.create()).build().create(iGoogleMapsApi::class.java)


                viewModelScope.launch {

                    val res =    api.getReverseGeoCodingDetails(latlng = mstate.selectedtempMovieDropPinLocation.latitude.toString()+","+mstate.selectedtempMovieDropPinLocation.longitude).await()


                    if(!res.results.isEmpty())
                    {
                        mstate = mstate.copy(selecteddropAddressLocationString = res.results[0].formatted_address)

                    }
                    else if(res.plus_code.compound_code!=null){

                        mstate = mstate.copy(selecteddropAddressLocationString = res.plus_code.compound_code)

                    }

                    mstate = mstate.copy(selectedDropPlaceholder = "Drop?")

                }

            }


            is AppEvents.FetchSelectedDropLocation2 ->{  mstate = mstate.copy(selectedDropPinLocation2 = mstate.selectedtempMovieDropPinLocation2, selecteddropAddressLocationString2 = "", selectedDropPlaceholder2 = "FetchingLocation...")

                val  api = Retrofit.Builder().baseUrl(Strings.GooglePlacesAutocompeleBaseURL).addConverterFactory(GsonConverterFactory.create()).build().create(iGoogleMapsApi::class.java)


                viewModelScope.launch {

                    val res =    api.getReverseGeoCodingDetails(latlng = mstate.selectedtempMovieDropPinLocation2.latitude.toString()+","+mstate.selectedtempMovieDropPinLocation2.longitude).await()


                    if(!res.results.isEmpty())
                    {
                        mstate = mstate.copy(selecteddropAddressLocationString2 = res.results[0].formatted_address)

                    }
                    else if(res.plus_code.compound_code!=null){

                        mstate = mstate.copy(selecteddropAddressLocationString2 = res.plus_code.compound_code)

                    }

                    mstate = mstate.copy(selectedDropPlaceholder2 = "Drop?")

                }

            }

            is AppEvents.FetchDropLocation2 ->{  mstate = mstate.copy(dropPinLocation2 = mstate.tempMoviePickupPinLocation, dropAddressLocationString2 = "", dropPlaceholder2 = "FetchingLocation...")

                val  api = Retrofit.Builder().baseUrl(Strings.GooglePlacesAutocompeleBaseURL).addConverterFactory(GsonConverterFactory.create()).build().create(iGoogleMapsApi::class.java)


                viewModelScope.launch {

                    val res =    api.getReverseGeoCodingDetails(latlng = mstate.dropPinLocation2.latitude.toString()+","+mstate.dropPinLocation2.longitude).await()


                    if(!res.results.isEmpty())
                    {
                        mstate = mstate.copy(dropAddressLocationString2 = res.results[0].formatted_address)

                    }
                    else if(res.plus_code.compound_code!=null){

                        mstate = mstate.copy(dropAddressLocationString2 = res.plus_code.compound_code)

                    }

                    mstate = mstate.copy(dropPlaceholder2 = "Your Second Destination ?")

                }

            }
            is AppEvents.FetchPickupLocaionCoords -> {

                val  api = Retrofit.Builder().baseUrl(Strings.GooglePlacesAutocompeleBaseURL).addConverterFactory(GsonConverterFactory.create()).build().create(iGoogleMapsApi::class.java)
                viewModelScope.launch {

                    val res = api.getPlaceIdDetails(arg).await()

                    if(res.result.geometry.location!=null)
                    {
                        val tempLoc:LatLng = LatLng(res.result.geometry.location.lat,res.result.geometry.location.lng)



                        val tempPrediction:Prediction = optionalArg as Prediction

                        var txt = ""

                        if(tempPrediction.structured_formatting.secondary_text!=null)
                        {
                            txt = tempPrediction.structured_formatting.secondary_text
                        }
                        else if(tempPrediction.structured_formatting.main_text!=null){
                            txt = tempPrediction.structured_formatting.main_text

                        }

                        mstate = mstate.copy(tempMoviePickupPinLocation = tempLoc, pickupPinLoaction = tempLoc, pickupAddressLocationString = txt, dont_trigger_reverse_geocoding = true)

                        cameraPositionVmState.move(update = CameraUpdateFactory.newCameraPosition(
                            CameraPosition(mstate.tempMoviePickupPinLocation,cameraPositionVmState.position.zoom,cameraPositionVmState.position.tilt,cameraPositionVmState.position.bearing)
                        ))

                        mstate = mstate.copy(dont_trigger_reverse_geocoding = false)

                    }

                }

            }
            is AppEvents.BlurMap->{
                mstate = mstate.copy(mapBlur = 15)
            }
            is AppEvents.UnBlurMap->{
                mstate = mstate.copy(mapBlur = 0)
            }
            is AppEvents.AddSecondDestination -> {
                mstate = mstate.copy(has2destinations = true)
            }
            is AppEvents.RemoveSecondDestination -> {
                mstate = mstate.copy(has2destinations = false)

            }

            is AppEvents.AllowNavigation -> {
                mstate = mstate.copy(canNavigate = true)
            }
            is AppEvents.StopNavigation ->{
                mstate = mstate.copy(canNavigate = false)
            }
            is AppEvents.ResetDestinations->{
                mstate = mstate.copy(dropPinLocation = LatLng(0.0,0.0), dropAddressLocationString = "", tempMovieDropPinLocation = LatLng(0.0,0.0))
                polylineLis = emptyList()
            }
            is AppEvents.ResetDestinations2->{
                mstate = mstate.copy(dropPinLocation2 = LatLng(0.0,0.0), dropAddressLocationString2 = "", tempMovieDropPinLocation2 = LatLng(0.0,0.0))
                polylineLis2 = emptyList()
            }
            is AppEvents.ResetSelectedDestinations->{
                mstate = mstate.copy(selectedDropPinLocation = LatLng(0.0,0.0), selecteddropAddressLocationString = "", selectedtempMovieDropPinLocation = LatLng(0.0,0.0))
               dropCameraPositionVmState =  CameraPositionState(position = CameraPosition.fromLatLngZoom(LatLng(17.4065, 78.4772),15f))
                polylineLis = emptyList()
            }

            is AppEvents.ResetSelectedDestinations2->{
                mstate = mstate.copy(selectedDropPinLocation2 = LatLng(0.0,0.0), selecteddropAddressLocationString2 = "", selectedtempMovieDropPinLocation2 = LatLng(0.0,0.0))
                dropCameraPositionVmState2 =  CameraPositionState(position = CameraPosition.fromLatLngZoom(LatLng(17.4065, 78.4772),15f))
                polylineLis2 = emptyList()
            }

            is AppEvents.GetDirections->{
                val  api = Retrofit.Builder().baseUrl(Strings.GooglePlacesAutocompeleBaseURL).addConverterFactory(GsonConverterFactory.create()).build().create(iGoogleMapsApi::class.java)
                viewModelScope.launch {
                    val org:String = mstate.pickupPinLoaction.latitude.toString()+","+mstate.pickupPinLoaction.longitude.toString()
                    val dest:String = mstate.dropPinLocation.latitude.toString()+","+mstate.dropPinLocation.longitude.toString()

                    try {
                         val res = api.getDirections(origin = org, destination = dest, key = Strings.GOOGLEMAPSAPIKEY).await()
                        gDirections= res

                    }
                    catch (e:Exception)
                    {
                        println(e.message.toString())
                    }

                                if(gDirections!!.status=="OK"){
                                    polylineLis= decode(gDirections!!.routes[0].overview_polyline.points)

                                }
                                else{
                                    polylineLis = emptyList()
                                }

                }

            }
            is AppEvents.GetDirections2->{
                val  api = Retrofit.Builder().baseUrl(Strings.GooglePlacesAutocompeleBaseURL).addConverterFactory(GsonConverterFactory.create()).build().create(iGoogleMapsApi::class.java)
                viewModelScope.launch {
                    val org:String = mstate.pickupPinLoaction.latitude.toString()+","+mstate.pickupPinLoaction.longitude.toString()
                    val dest:String = mstate.dropPinLocation.latitude.toString()+","+mstate.dropPinLocation.longitude.toString()
                    val dest2:String = mstate.dropPinLocation2.latitude.toString()+","+mstate.dropPinLocation2.longitude.toString()

                    try {
                        val res1 = api.getDirections(origin = org, destination = dest, key = Strings.GOOGLEMAPSAPIKEY).await()
                        gDirections= res1
                        val res2 = api.getDirections(origin = org, destination = dest2, key = Strings.GOOGLEMAPSAPIKEY).await()
                        gDirections2= res2

                    }
                    catch (e:Exception)
                    {
                        println(e.message.toString())
                    }

                               if(gDirections2!!.status=="OK") {
                                   polylineLis2 =
                                       decode(gDirections2!!.routes[0].overview_polyline.points)
                               }
                                else{
                                    polylineLis2= emptyList()
                               }

                    if(gDirections!!.status=="OK") {
                        polylineLis =
                            decode(gDirections!!.routes[0].overview_polyline.points)
                    }
                    else{
                        polylineLis= emptyList()
                    }

                }

            }

            is AppEvents.FetchDropLocaionCoords -> {
                val  api = Retrofit.Builder().baseUrl(Strings.GooglePlacesAutocompeleBaseURL).addConverterFactory(GsonConverterFactory.create()).build().create(iGoogleMapsApi::class.java)
                viewModelScope.launch {

                    val res = api.getPlaceIdDetails(arg).await()

                    if(res.result.geometry.location!=null)
                    {
                        val tempLoc:LatLng = LatLng(res.result.geometry.location.lat,res.result.geometry.location.lng)



                        val tempPrediction:Prediction = optionalArg as Prediction

                        var txt = ""

                        if(tempPrediction.structured_formatting.secondary_text!=null)
                        {
                            txt = tempPrediction.structured_formatting.secondary_text
                        }
                        else if(tempPrediction.structured_formatting.main_text!=null){
                            txt = tempPrediction.structured_formatting.main_text

                        }

                        mstate = mstate.copy(tempMovieDropPinLocation = tempLoc, dropPinLocation = tempLoc, dropAddressLocationString = txt, dont_trigger_drop_reverse_geocoding = true)

                        dropCameraPositionVmState.move(update = CameraUpdateFactory.newCameraPosition(
                            CameraPosition(mstate.tempMovieDropPinLocation,dropCameraPositionVmState.position.zoom,dropCameraPositionVmState.position.tilt,dropCameraPositionVmState.position.bearing)
                        ))

                        mstate = mstate.copy(dont_trigger_drop_reverse_geocoding = false)

                    }

                }

            }
            is AppEvents.FetchSelectedDropLocaionCoords -> {
                val  api = Retrofit.Builder().baseUrl(Strings.GooglePlacesAutocompeleBaseURL).addConverterFactory(GsonConverterFactory.create()).build().create(iGoogleMapsApi::class.java)
                viewModelScope.launch {

                    val res = api.getPlaceIdDetails(arg).await()

                    if(res.result.geometry.location!=null)
                    {
                        val tempLoc:LatLng = LatLng(res.result.geometry.location.lat,res.result.geometry.location.lng)



                        val tempPrediction:Prediction = optionalArg as Prediction

                        var txt = ""

                        if(tempPrediction.structured_formatting.secondary_text!=null)
                        {
                            txt = tempPrediction.structured_formatting.secondary_text
                        }
                        else if(tempPrediction.structured_formatting.main_text!=null){
                            txt = tempPrediction.structured_formatting.main_text

                        }

                        mstate = mstate.copy(selectedtempMovieDropPinLocation = tempLoc, selectedDropPinLocation = tempLoc, selecteddropAddressLocationString = txt, dont_trigger_drop_reverse_geocoding = true)

                        dropCameraPositionVmState.move(update = CameraUpdateFactory.newCameraPosition(
                            CameraPosition(mstate.selectedtempMovieDropPinLocation,dropCameraPositionVmState.position.zoom,dropCameraPositionVmState.position.tilt,dropCameraPositionVmState.position.bearing)
                        ))

                        mstate = mstate.copy(dont_trigger_drop_reverse_geocoding = false)

                    }

                }

            }
            is AppEvents.FetchSelectedDropLocaionCoords2 -> {
                val  api = Retrofit.Builder().baseUrl(Strings.GooglePlacesAutocompeleBaseURL).addConverterFactory(GsonConverterFactory.create()).build().create(iGoogleMapsApi::class.java)
                viewModelScope.launch {

                    val res = api.getPlaceIdDetails(arg).await()

                    if(res.result.geometry.location!=null)
                    {
                        val tempLoc:LatLng = LatLng(res.result.geometry.location.lat,res.result.geometry.location.lng)



                        val tempPrediction:Prediction = optionalArg as Prediction

                        var txt = ""

                        if(tempPrediction.structured_formatting.secondary_text!=null)
                        {
                            txt = tempPrediction.structured_formatting.secondary_text
                        }
                        else if(tempPrediction.structured_formatting.main_text!=null){
                            txt = tempPrediction.structured_formatting.main_text

                        }

                        mstate = mstate.copy(selectedtempMovieDropPinLocation2 = tempLoc, selectedDropPinLocation2 = tempLoc, selecteddropAddressLocationString2 = txt, dont_trigger_drop_reverse_geocoding2 = true)

                        dropCameraPositionVmState2.move(update = CameraUpdateFactory.newCameraPosition(
                            CameraPosition(mstate.selectedtempMovieDropPinLocation2,dropCameraPositionVmState2.position.zoom,dropCameraPositionVmState2.position.tilt,dropCameraPositionVmState2.position.bearing)
                        ))

                        mstate = mstate.copy(dont_trigger_drop_reverse_geocoding2 = false)

                    }

                }

            }
            is AppEvents.FetchDropLocaionCoords2 -> {
                val  api = Retrofit.Builder().baseUrl(Strings.GooglePlacesAutocompeleBaseURL).addConverterFactory(GsonConverterFactory.create()).build().create(iGoogleMapsApi::class.java)
                viewModelScope.launch {

                    val res = api.getPlaceIdDetails(arg).await()

                    if(res.result.geometry.location!=null)
                    {
                        val tempLoc:LatLng = LatLng(res.result.geometry.location.lat,res.result.geometry.location.lng)



                        val tempPrediction:Prediction = optionalArg as Prediction

                        var txt = ""

                        if(tempPrediction.structured_formatting.secondary_text!=null)
                        {
                            txt = tempPrediction.structured_formatting.secondary_text
                        }
                        else if(tempPrediction.structured_formatting.main_text!=null){
                            txt = tempPrediction.structured_formatting.main_text

                        }

                        mstate = mstate.copy(tempMovieDropPinLocation2 = tempLoc, dropPinLocation2 = tempLoc, dropAddressLocationString2 = txt, dont_trigger_drop_reverse_geocoding2 = true)

                        dropCameraPositionVmState2.move(update = CameraUpdateFactory.newCameraPosition(
                            CameraPosition(mstate.tempMovieDropPinLocation2,dropCameraPositionVmState2.position.zoom,dropCameraPositionVmState2.position.tilt,dropCameraPositionVmState2.position.bearing)
                        ))

                        mstate = mstate.copy(dont_trigger_drop_reverse_geocoding2 = false)

                    }

                }

            }



        }
    }


}

