package com.gyanu.trider.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap.OnMyLocationButtonClickListener
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLngBounds
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.Polyline
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.maps.android.compose.rememberMarkerState
import com.gyanu.trider.R
import com.gyanu.trider.presentation.AppEvents
import com.gyanu.trider.presentation.MapViewModel
import kotlinx.coroutines.launch


@Composable
fun TailoredMap(vm:MapViewModel){

    val initialZoom = 6f
    val finalZoom = 15f

    GoogleMap( modifier = Modifier
        .fillMaxHeight(vm.mstate.mapHeight)

        .fillMaxWidth(vm.mstate.mapWidth).blur(vm.mstate.mapBlur.dp), properties = vm.mstate.Properties, uiSettings = MapUiSettings(zoomControlsEnabled = false, myLocationButtonEnabled = false), cameraPositionState = vm.cameraPositionVmState, onMapLoaded = { if(vm.islocationpermissiongiven && !vm.mstate.Properties.isMyLocationEnabled) { vm.OnEvent(AppEvents.EnableMyLocation,"")  }  }){

        if(vm.cameraPositionVmState.isMoving)
        {
            vm.OnEvent(AppEvents.PickupCamMoving,"")

        }

        if(!vm.cameraPositionVmState.isMoving && vm.mstate.pickupPinLoaction!=vm.mstate.tempMoviePickupPinLocation)
        {
            vm.OnEvent(AppEvents.FetchPickupLocation,"")
        }


    }

}

@Composable
fun TailoredMapwithPolyLine(vm:MapViewModel){


    val width: Int = LocalConfiguration.current.screenWidthDp
    val height: Int = LocalConfiguration.current.screenHeightDp
    val padding = (width * 0.10).toInt()
    val scope = rememberCoroutineScope()
 var   boundsbuilder = LatLngBounds.builder().include(vm.mstate.pickupPinLoaction) .include(vm.mstate.dropPinLocation)
    var mkrBounds = boundsbuilder.build()
    var cu = CameraUpdateFactory.newLatLngBounds(mkrBounds,width,height/2,padding)
    val initialZoom = 12f
    val finalZoom = 12f
    val tempDropLoc by remember {
        mutableStateOf(vm.mstate.dropPinLocation)
    }

    val tempDropLocString by remember {
        mutableStateOf(vm.mstate.dropAddressLocationString)
    }

val cameraPosition = rememberCameraPositionState{ position = CameraPosition.fromLatLngZoom(vm.mstate.pickupPinLoaction,10f) }
    var tempPolylineList by remember {
        mutableStateOf(vm.polylineLis)
    }


    val pickupmarkerState = rememberMarkerState(null,vm.mstate.pickupPinLoaction)
    val dropmarkerState = rememberMarkerState(null,vm.mstate.dropPinLocation)




   GoogleMap(properties = vm.mstate.Properties, uiSettings = MapUiSettings(zoomControlsEnabled = false, compassEnabled = false, myLocationButtonEnabled = false, rotationGesturesEnabled = false, mapToolbarEnabled = false),modifier = Modifier
       .fillMaxWidth(1f)
       .fillMaxHeight(0.5f), cameraPositionState = cameraPosition, onMapLoaded = {scope.launch { cameraPosition.animate(cu,1500)
        vm.OnEvent(AppEvents.ResetDestinations,"")
       vm.OnEvent(AppEvents.AllowNavigation,"")
     }

       }){

        Polyline(points = tempPolylineList)

       Marker(state = pickupmarkerState, icon = BitmapDescriptorFactory.fromResource(R.drawable.rapidogreenpinresize), title = vm.mstate.pickupAddressLocationString ){

       }

       Marker(state = dropmarkerState , icon = BitmapDescriptorFactory.fromResource(R.drawable.rapidoredpinresize), title = tempDropLocString){

       }
   }



}


@Composable
fun TailoredMapwithPolyLine2(vm:MapViewModel){


    val width: Int = LocalConfiguration.current.screenWidthDp
    val height: Int = LocalConfiguration.current.screenHeightDp
    val padding = (width * 0.10).toInt()
    val scope = rememberCoroutineScope()
    var   boundsbuilder = LatLngBounds.builder().include(vm.mstate.pickupPinLoaction) .include(vm.mstate.dropPinLocation).include(vm.mstate.dropPinLocation2)
    var mkrBounds = boundsbuilder.build()
    var cu = CameraUpdateFactory.newLatLngBounds(mkrBounds,width,height/2,padding)
    val initialZoom = 12f
    val finalZoom = 12f
    val tempDropLoc by remember {
        mutableStateOf(vm.mstate.dropPinLocation)
    }

    val tempDropLocString by remember {
        mutableStateOf(vm.mstate.dropAddressLocationString)
    }

    val tempDropLoc2 by remember {
        mutableStateOf(vm.mstate.dropPinLocation2)
    }

    val tempDropLocString2 by remember {
        mutableStateOf(vm.mstate.dropAddressLocationString2)
    }

    val cameraPosition = rememberCameraPositionState{ position = CameraPosition.fromLatLngZoom(vm.mstate.pickupPinLoaction,10f) }
    var tempPolylineList by remember {
        mutableStateOf(vm.polylineLis)
    }

    var tempPolylineList2 by remember {
        mutableStateOf(vm.polylineLis2)
    }


    val pickupmarkerState = rememberMarkerState(null,vm.mstate.pickupPinLoaction)
    val dropmarkerState = rememberMarkerState(null,vm.mstate.dropPinLocation)
    val dropmarkerState2 = rememberMarkerState(null,vm.mstate.dropPinLocation2)




    GoogleMap(properties = vm.mstate.Properties, uiSettings = MapUiSettings(zoomControlsEnabled = false, compassEnabled = false, myLocationButtonEnabled = false, rotationGesturesEnabled = false, mapToolbarEnabled = false),modifier = Modifier
        .fillMaxWidth(1f)
        .fillMaxHeight(0.5f), cameraPositionState = cameraPosition, onMapLoaded = {scope.launch { cameraPosition.animate(cu,1500)
        vm.OnEvent(AppEvents.ResetDestinations,"")
        vm.OnEvent(AppEvents.ResetDestinations2,"")
        vm.OnEvent(AppEvents.AllowNavigation,"")
    }

    }){

        Polyline(points = tempPolylineList)

        Polyline(points = tempPolylineList2)

        Marker(state = pickupmarkerState, icon = BitmapDescriptorFactory.fromResource(R.drawable.rapidogreenpinresize), title = vm.mstate.pickupAddressLocationString ){

        }

        Marker(state = dropmarkerState , icon = BitmapDescriptorFactory.fromResource(R.drawable.rapidoredpinresize), title = tempDropLocString){

        }
        Marker(state = dropmarkerState2 , icon = BitmapDescriptorFactory.fromResource(R.drawable.rapidobluepinresize), title = tempDropLocString2){

        }
    }



}

