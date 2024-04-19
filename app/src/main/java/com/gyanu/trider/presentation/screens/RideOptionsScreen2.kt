package com.gyanu.trider.presentation.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.ListItem
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.CameraPositionState
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.Polyline
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.maps.android.compose.rememberMarkerState
import com.gyanu.trider.R
import com.gyanu.trider.Screen
import com.gyanu.trider.components.SwipeButton
import com.gyanu.trider.components.TailoredMapwithPolyLine
import com.gyanu.trider.components.TailoredMapwithPolyLine2
import com.gyanu.trider.components.ThreeLineListItem
import com.gyanu.trider.components.ThreeLineListItemCab
import com.gyanu.trider.components.ThreeLineListItemRickshaw
import com.gyanu.trider.data.Strings
import com.gyanu.trider.data.iGoogleMapsApi
import com.gyanu.trider.presentation.MapViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.await
import retrofit2.converter.gson.GsonConverterFactory
import com.google.maps.android.compose.GoogleMap as GoogleMap

@Composable
fun RideOptionsScreen2(vm:MapViewModel,navController: NavController){


    val scope = rememberCoroutineScope()
    val (isComplete, setIsComplete) = remember {
        mutableStateOf(false)
    }


    var distance by remember { mutableStateOf(0) }
    val distanceScope = rememberCoroutineScope()

    LaunchedEffect(true) {
        distanceScope.launch {
            val  api = Retrofit.Builder().baseUrl(Strings.GooglePlacesAutocompeleBaseURL).addConverterFactory(
                GsonConverterFactory.create()).build().create(iGoogleMapsApi::class.java)

            val res = api.getDistanceMatrixDetails(origins = vm.mstate.pickupPinLoaction.latitude.toString()+","+vm.mstate.pickupPinLoaction.longitude.toString(), destinations = vm.mstate.dropPinLocation.latitude.toString()+","+vm.mstate.dropPinLocation.longitude.toString()+"|"+vm.mstate.dropPinLocation2.latitude.toString()+","+vm.mstate.dropPinLocation2.longitude.toString()).await()

            if(res.status=="OK"){

                distance =   res.rows[0].elements[0].distance.value.toInt()+ res.rows[0].elements[1].distance.value.toInt()
            }

        }
    }



    TailoredMapwithPolyLine2(vm = vm)

    Column(modifier = Modifier.fillMaxSize()) {

        Column(modifier = Modifier
            .fillMaxHeight(0.5f)
            .fillMaxWidth(1f), horizontalAlignment = Alignment.Start, verticalArrangement = Arrangement.Bottom) {

            Card(shape = RoundedCornerShape(30.dp), colors = CardDefaults.cardColors(containerColor = Color.White), elevation = CardDefaults.cardElevation(10.dp)){
            IconButton(onClick = { navController.popBackStack() }) {

                Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")

               }




            }
            
            Spacer(modifier = Modifier
                .height(20.dp)
                .width(10.dp))





        }

        Column(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()) {



            Card(modifier = Modifier
                .fillMaxHeight(0.25f)
                .fillMaxWidth(1f), border = BorderStroke(1.dp,Color.Black)) {
                ThreeLineListItem(
                    headlineString = "Bike",
                    SupportingContentString = "1 min away",
                    overlineContent = "Quick Ride",
                    trailingContentString = (20+(distance/200)).toString()
                )
            }
            Card(modifier = Modifier
                .fillMaxHeight(0.3f)
                .fillMaxWidth(1f), border = BorderStroke(1.dp,Color.Black)) {
                ThreeLineListItemCab(
                    headlineString = "Cab",
                    SupportingContentString = "2 min away",
                    overlineContent = "Comfort Ride",
                    trailingContentString = (20+(distance/100)).toString()
                )
            }


            Column(horizontalAlignment = Alignment.CenterHorizontally) {


                Card(modifier = Modifier
                    .fillMaxHeight(0.5f)
                    .fillMaxWidth(1f), border = BorderStroke(1.dp,Color.Black)) {
                    ThreeLineListItemRickshaw(
                        headlineString = "Auto",
                        SupportingContentString = "5 min away",
                        overlineContent = "Hassel-free Rides",
                        trailingContentString = (20+(distance/120)).toString()
                    )
                }

            Card(colors = CardDefaults.cardColors(containerColor = Color.White), elevation = CardDefaults.elevatedCardElevation(30.dp)) {
                /*
                SwipeButton(
                    backgroundColor = Color.LightGray,
                    shape = RoundedCornerShape(30.dp),
                    onSwipe = {  },
                    icon = {
                        Icon(
                            imageVector = Icons.Filled.Home,
                            contentDescription = ""
                        )
                    },
                    text = "Swipe to Close"
                )
                
                 */

                SwipeButton(text = "Book Bike", isComplete = isComplete, onSwipe = {
                    scope.launch {
                        delay(2000)
                        setIsComplete(true)
                    }
                })

            }
                
            }


        }

    }


}