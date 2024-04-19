@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)

package com.gyanu.trider.presentation.screens

import android.annotation.SuppressLint
import android.content.Context
import android.inputmethodservice.Keyboard.Row
import android.location.Location
import android.location.LocationManager
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectVerticalDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomSheetScaffoldDefaults
import androidx.compose.material.BottomSheetValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.rememberBottomSheetState
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.gyanu.trider.components.SearchAddressBar
import com.gyanu.trider.components.TailoredMap
import com.gyanu.trider.components.TwoLineListItem
import com.gyanu.trider.presentation.MapViewModel
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.SheetValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberStandardBottomSheetState
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.CameraPositionState
import com.gyanu.trider.R
import com.gyanu.trider.R.drawable
import com.gyanu.trider.Screen
import com.gyanu.trider.components.DisplayAdd
import com.gyanu.trider.components.SearchDestinationAddressBar
import com.gyanu.trider.components.SearchDestinationAddressBar2
import com.gyanu.trider.components.SearchDestinationAddressBarResults
import com.gyanu.trider.components.SearchDestinationAddressBarResults2
import com.gyanu.trider.presentation.AppEvents
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch




@SuppressLint("MissingPermission")
@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun HomeScreen(vm:MapViewModel,navController: NavController= rememberNavController(),fusedLocationProviderClient: FusedLocationProviderClient) {

    val scope = rememberCoroutineScope()
    
    val sheetState = rememberStandardBottomSheetState(initialValue = SheetValue.PartiallyExpanded,)

    val scaffoldstate = rememberBottomSheetScaffoldState(bottomSheetState = sheetState)

    val tempstate = remember{ mutableStateOf(0.5f) }

    val plusminusicon = remember{ mutableStateOf(Icons.Filled.Add) }

    
    //Map with pickup location
    TailoredMap(vm)

    // Pick up pin at the centre of the map
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Spacer(modifier = Modifier.height(15.dp))
        Row(horizontalArrangement = Arrangement.Start) {
            Card(shape = RoundedCornerShape(40.dp), modifier = Modifier, colors = CardDefaults.cardColors(Color.White), elevation = CardDefaults.cardElevation(15.dp)) {

                IconButton(onClick = {

                        scope.launch {

                             vm.mDrawerState.open()
                        }

                }) {
                    Icon(imageVector = Icons.Filled.Menu, contentDescription = "")


                }
            }
            Spacer(modifier = Modifier.width(15.dp))

            SearchAddressBar(vm)
            Spacer(modifier = Modifier.width(15.dp))

            Card(colors = CardDefaults.cardColors(containerColor = Color.White), elevation = CardDefaults.cardElevation(15.dp)) {
                IconButton(onClick = { vm.OnEvent(AppEvents.ToggleMapType, "") }) {
                    Icon(
                        painter = painterResource(id = R.drawable.eyeglasses),
                        contentDescription = ""
                    )
                }

            }

        }
        Spacer(modifier = Modifier.height(30.dp))




        for (placesdetails in vm.pickupPredictionsList) {

            if (placesdetails.structured_formatting.main_text != null) {

                var maintextString = placesdetails.structured_formatting.main_text

                var secondaryString = ""

                if (maintextString.length > 35) {
                    maintextString = maintextString.substring(0, 35) + "..."
                }

                if (placesdetails.structured_formatting.secondary_text != null) {

                    secondaryString = placesdetails.structured_formatting.secondary_text

                    if (secondaryString.length > 35) {
                        secondaryString = secondaryString.substring(0, 35) + "..."
                    }
                }


                    vm.OnEvent(AppEvents.BlurMap,"")


                Card(shape = RoundedCornerShape(40.dp), onClick = {

                    vm.OnEvent(AppEvents.FetchPickupLocaionCoords,placesdetails.place_id,placesdetails)
                    vm.pickupPredictionsList =
                        emptyList()
                    vm.OnEvent(AppEvents.UnBlurMap,"")

                }) {
                    TwoLineListItem(
                        headlineString = maintextString,
                        SupportingContentString = secondaryString
                    )

                }

                Spacer(modifier = Modifier.height(10.dp))
            }


        }



    }


//for map bottom icons
    Column(
        modifier = Modifier
            .fillMaxWidth(0.95f)
            .fillMaxHeight(0.74f),
        horizontalAlignment = Alignment.End,
        verticalArrangement = Arrangement.Bottom
    ){
                 Card(elevation = CardDefaults.cardElevation(20.dp),shape = RoundedCornerShape(30.dp), colors = CardDefaults.cardColors(containerColor = Color.White, contentColor = Color(android.graphics.Color.parseColor("#004cff")))) {
                     IconButton(onClick = { scope.launch {

                         fusedLocationProviderClient.getCurrentLocation(LocationRequest.PRIORITY_HIGH_ACCURACY, null)
                             .addOnSuccessListener { location ->
                                 if (location != null) {

                                GlobalScope.launch {

                                    vm.cameraPositionVmState = CameraPositionState(position = CameraPosition.fromLatLngZoom(LatLng(location.latitude,location.longitude),15f))
                                    vm.mstate=vm.mstate.copy(tempMoviePickupPinLocation = LatLng(location.latitude,location.longitude),  pickupPinLoaction =LatLng(location.latitude,location.longitude) )

                                    //   vm.cameraPositionVmState.move(update = CameraUpdateFactory.newCameraPosition(CameraPosition(vm.mstate.tempMoviePickupPinLocation,vm.cameraPositionVmState.position.zoom,vm.cameraPositionVmState.position.tilt,vm.cameraPositionVmState.position.bearing)))
                                }




                                 }
                             }
                             .addOnFailureListener {
                                 println(it.message.toString())
                             }

                     } }) {
                         Icon(painter = painterResource(id = R.drawable.mylocation), contentDescription = "" )
                     }
                 }


    }



    //Pickup pin on home screen
    if (vm.pickupPredictionsList.count()==0) {


        Column(
            modifier = Modifier
                .fillMaxHeight(vm.mstate.mapHeight)
                .fillMaxWidth(vm.mstate.mapWidth),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Image(
                painter = if(!vm.cameraPositionVmState.isMoving){  painterResource(id = R.drawable.rapidogreenpin1)} else {painterResource(id = R.drawable.rapidogreenpin2)},
                contentDescription = "pickup",
                modifier = Modifier.height(45.dp)
            )
        }

    }
       

    BottomSheetScaffold(sheetContainerColor = Color.White,modifier = Modifier,sheetContent = {
              //BottomSheetContent


                     Column(
                         Modifier
                             .fillMaxSize()
                             , horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Top) {
                          if(scaffoldstate.bottomSheetState.currentValue.toString() == BottomSheetValue.Expanded.toString()){
                              Row(verticalAlignment = Alignment.CenterVertically) {
                                  //For Hamburger Menu
                                  Card(shape = RoundedCornerShape(40.dp), modifier = Modifier, colors = CardDefaults.cardColors(Color.White), elevation = CardDefaults.cardElevation(15.dp)) {

                                      IconButton(onClick = {

                                          scope.launch {

                                              vm.mDrawerState.open()
                                          }

                                      }) {
                                          Icon(imageVector = Icons.Filled.Menu, contentDescription = "")


                                      }
                                  }
                                  Spacer(modifier = Modifier.width(10.dp))
                                  SearchAddressBar(vm = vm)
                                  Spacer(modifier = Modifier.width(10.dp))
                                  Card(colors = CardDefaults.cardColors(containerColor = Color.White), elevation = CardDefaults.cardElevation(10.dp)) {
                                      IconButton(onClick = {
                                              scope.launch{scaffoldstate.bottomSheetState.partialExpand()}
                                      }) {
                                          Image(painter = painterResource(id = R.drawable.rapidogreenpin1), contentDescription = "")
                                      }
                                  }

                              }
                              Spacer(modifier = Modifier.height(10.dp))
                          }
                         Row(verticalAlignment = Alignment.CenterVertically) {
                                      if(!vm.mstate.has2destinations){
                            Card(colors = CardDefaults.cardColors(containerColor = Color.White)) {
                                IconButton(onClick = {
                                    vm.OnEvent(
                                        AppEvents.AddSecondDestination,
                                        ""
                                    )
                                }) {
                                    Icon(imageVector = Icons.Filled.Add, contentDescription = "")
                                }
                            } }
                             SearchDestinationAddressBar(vm = vm)

                             Spacer(modifier = Modifier.width(10.dp))

                             Card(colors = CardDefaults.cardColors(containerColor = Color.White), elevation = CardDefaults.cardElevation(10.dp), modifier = Modifier.width(
                                 (LocalConfiguration.current.screenWidthDp/9).dp)) {
                                 IconButton(onClick = {
                                     vm.OnEvent(AppEvents.ResetDestinations,"")
                                     vm.OnEvent(AppEvents.ResetSelectedDestinations,"")
                                        navController.navigate(Screen.Drop.route)
                                 }) {
                                    Image(painter = painterResource(id = R.drawable.rapidoredpin1), contentDescription = "")
                                 }
                             }

                         }

                         SearchDestinationAddressBarResults(vm = vm)
                         
                         Spacer(modifier = Modifier.height(5.dp))
                         
                         if(vm.mstate.has2destinations){
                             Row(verticalAlignment = Alignment.CenterVertically){
                             Card(colors = CardDefaults.cardColors(containerColor = Color.White)) {
                                 IconButton(onClick = {
                                     vm.OnEvent(
                                         AppEvents.RemoveSecondDestination,
                                         ""
                                     )
                                     vm.OnEvent(AppEvents.ClearDropAddressText2,"")
                                 }) {
                                     Icon(imageVector = Icons.Filled.Close, contentDescription = "", modifier = Modifier)
                                 }
                             }
                                 Spacer(modifier = Modifier.width(10.dp))
                             SearchDestinationAddressBar2(vm = vm)
                                 Spacer(modifier = Modifier.width(10.dp))

                                 Card(colors = CardDefaults.cardColors(containerColor = Color.White), elevation = CardDefaults.cardElevation(10.dp), modifier = Modifier.width(
                                     (LocalConfiguration.current.screenWidthDp/9).dp)) {
                                     IconButton(onClick = {
                                         vm.OnEvent(AppEvents.ResetDestinations2,"")
                                         vm.OnEvent(AppEvents.ResetSelectedDestinations2,"")
                                         navController.navigate(Screen.Drop2.route)
                                     }) {
                                         Image(painter = painterResource(id = R.drawable.rapidobluepin1), contentDescription = "")
                                     }
                                 }
                                 
                         }
                              Spacer(modifier = Modifier.height(5.dp))
                             
                             SearchDestinationAddressBarResults2(vm = vm)
                         
                         }



                                                  if (scaffoldstate.bottomSheetState.currentValue.toString() != BottomSheetValue.Expanded.toString()) {
                                                      Image(
                                                          painter = painterResource(id = R.drawable.tridersvg),
                                                          contentDescription = ""
                                                      )
                                                  }


                         Spacer(modifier = Modifier.height(10.dp))


                         DisplayAdd(300)

                         Column(verticalArrangement = Arrangement.Bottom, horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxSize(1f)) {
                             if (scaffoldstate.bottomSheetState.currentValue.toString() == BottomSheetValue.Expanded.toString()) {
                                 Image(
                                     painter = painterResource(id = R.drawable.tridersvg),
                                     contentDescription = ""
                                 )
                             }
                         }

                         if(vm.mstate.pickupPinLoaction!= LatLng(0.0,0.0) && vm.mstate.dropPinLocation!=LatLng(0.0,0.0) && vm.mstate.pickupAddressLocationString.length>0 && vm.mstate.dropAddressLocationString.length>0){

                                      if(vm.mstate.has2destinations)
                                      {

                                            vm.OnEvent(AppEvents.GetDirections2,"")

                                          if(vm.polylineLis.count()>0 && vm.polylineLis2.count()>0){
                                              if(vm.mstate.canNavigate)
                                              {
                                                  vm.OnEvent(AppEvents.StopNavigation,"")
                                                  navController.navigate(Screen.Detail2.route)

                                              }
                                          }

                                      }
                                  else
                                       {
                                          vm.OnEvent(AppEvents.GetDirections,"")

                                           if(vm.polylineLis.count()>0)
                                           {
                                               if(vm.mstate.canNavigate)
                                               {
                                                   vm.OnEvent(AppEvents.StopNavigation,"")
                                                   navController.navigate(Screen.Detail.route)

                                               }
                                           }

                                        }


                         }


                     }

    }, sheetPeekHeight = (LocalConfiguration.current.screenHeightDp/4).dp, scaffoldState = scaffoldstate, sheetShadowElevation = 40.dp) {

    }
            if(!scaffoldstate.bottomSheetState.isVisible){
               LaunchedEffect(null) {
                   scope.launch{
                       scaffoldstate.bottomSheetState.partialExpand()
                   }
               }
            }

}


  @SuppressLint("MissingPermission")
  fun getCurrentLocation() {

     lateinit var fussedLocationClient:FusedLocationProviderClient

      fussedLocationClient.getCurrentLocation(LocationRequest.PRIORITY_HIGH_ACCURACY, null)
          .addOnSuccessListener { location ->
              if (location != null) {

              }
          }



}








@Composable
fun  BottomsheetContent(scope: CoroutineScope,vm: MapViewModel) {

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Spacer(modifier = Modifier.height(15.dp))
        Row(horizontalArrangement = Arrangement.Start) {
            Card(
                shape = RoundedCornerShape(40.dp),
                modifier = Modifier,
                colors = CardDefaults.cardColors(Color.White), elevation = CardDefaults.cardElevation(15.dp)
            ) {

                IconButton(onClick = {

                    scope.launch {
                        vm.mDrawerState.open()
                    }.invokeOnCompletion { }

                }) {
                    Icon(imageVector = Icons.Filled.Menu, contentDescription = "")


                }
            }


        }
    }
}


