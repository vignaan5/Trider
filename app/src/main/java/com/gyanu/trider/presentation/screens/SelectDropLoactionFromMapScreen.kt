package com.gyanu.trider.presentation.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.rememberCameraPositionState
import com.gyanu.trider.R
import com.gyanu.trider.R.drawable
import com.gyanu.trider.components.SearchDestinationAddressBar
import com.gyanu.trider.components.SearchDestinationAddressBarResults
import com.gyanu.trider.components.TailoredMap
import com.gyanu.trider.components.TwoLineListItem
import com.gyanu.trider.presentation.AppEvents
import com.gyanu.trider.presentation.MapViewModel
import kotlinx.coroutines.launch

@SuppressLint("SuspiciousIndentation")
@Composable
fun SelectDropLoactionFromMap(vm:MapViewModel,navController: NavController){

  val scope = rememberCoroutineScope()

    GoogleMap( modifier = Modifier
        .fillMaxSize()
        .blur(vm.mstate.mapBlur.dp), properties = vm.mstate.Properties, uiSettings = MapUiSettings(zoomControlsEnabled = false, myLocationButtonEnabled = false), cameraPositionState = vm.dropCameraPositionVmState) {

        if(vm.dropCameraPositionVmState.isMoving)
        {
            vm.OnEvent(AppEvents.DropCamMoving,"")
        }

        if(!vm.dropCameraPositionVmState.isMoving && vm.mstate.selectedDropPinLocation!=vm.mstate.selectedtempMovieDropPinLocation)
        {
            vm.OnEvent(AppEvents.FetchSelectedDropLocation,"")
        }


    }

     Column(modifier =  Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Bottom) {
                if(!vm.dropCameraPositionVmState.isMoving && vm.dropPredictionsList.count()==0 && vm.mstate.selecteddropAddressLocationString.count()>0)
             Button(onClick = {
                 scope.launch {   vm.OnEvent(AppEvents.ConfirmDrop,"") }
                 navController.popBackStack()

             }) {
                 Text(text = "Confirm Drop")
             }


     }


    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
        if(!vm.dropCameraPositionVmState.isMoving) {
            Image(painter = painterResource(id = drawable.rapidoredpin1), contentDescription = "",modifier = Modifier.height(45.dp))

        }
        else {
            Image(
                painter = painterResource(id = drawable.rapidoredpin2),
                contentDescription = "",
                modifier = Modifier.height(45.dp)
            )
        }
        
        

        
        
    }

    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Top) {
        Spacer(modifier = Modifier.height(10.dp))
        Row {
            IconButton(onClick = {navController.popBackStack() }) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription =""
                )
            }

            Spacer(modifier = Modifier.width(10.dp))

            SearchDestinationAddressBarLocal(vm = vm)

        }
        Spacer(modifier = Modifier.height(5.dp))
        SearchDestinationAddressBarResults(vm = vm)
        Spacer(modifier = Modifier.height(10.dp))
        SearchDestinationAddressBarResultsLoacl(vm = vm)

    }

    }


@Composable
fun SelectDropLoactionFromMap2(vm:MapViewModel, navController: NavController){

    val scope = rememberCoroutineScope()

    GoogleMap( modifier = Modifier
        .fillMaxSize()
        .blur(vm.mstate.mapBlur.dp), properties = vm.mstate.Properties, uiSettings = MapUiSettings(zoomControlsEnabled = false, myLocationButtonEnabled = false), cameraPositionState = vm.dropCameraPositionVmState2) {

        if(vm.dropCameraPositionVmState2.isMoving)
        {
            vm.OnEvent(AppEvents.DropCamMoving2,"")
        }

        if(!vm.dropCameraPositionVmState2.isMoving && vm.mstate.selectedDropPinLocation2!=vm.mstate.selectedtempMovieDropPinLocation2)
        {
            vm.OnEvent(AppEvents.FetchSelectedDropLocation2,"")
        }


    }

    Column(modifier =  Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Bottom) {
        if(!vm.dropCameraPositionVmState2.isMoving && vm.dropPredictionsList2.count()==0 && vm.mstate.selecteddropAddressLocationString2.count()>0)
            Button(onClick = {
                scope.launch {   vm.OnEvent(AppEvents.ConfirmDrop2,"") }
                navController.popBackStack()

            }) {
                Text(text = "Confirm Drop")
            }


    }


    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
        if(!vm.dropCameraPositionVmState2.isMoving) {
            Image(painter = painterResource(id = drawable.rapidobluepin1), contentDescription = "",modifier = Modifier.height(45.dp))

        }
        else {
            Image(
                painter = painterResource(id = drawable.rapidobluepin2),
                contentDescription = "",
                modifier = Modifier.height(45.dp)
            )
        }





    }

    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Top) {
        Spacer(modifier = Modifier.height(10.dp))
        Row {
            IconButton(onClick = {navController.popBackStack() }) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription =""
                )
            }

            Spacer(modifier = Modifier.width(10.dp))

            SearchDestinationAddressBarLocal2(vm = vm)

        }

        Spacer(modifier = Modifier.height(10.dp))
        SearchDestinationAddressBarResultsLoacl2(vm = vm)

    }

}





@Composable
fun SearchDestinationAddressBarLocal(vm:MapViewModel){

    Card(shape = RoundedCornerShape(30.dp),colors = CardDefaults.cardColors(containerColor =  Color.White), elevation = CardDefaults.cardElevation(10.dp), modifier = Modifier.fillMaxWidth(0.7f)) {
        OutlinedTextField(leadingIcon = { Image(painter = painterResource(id = R.drawable.dropicon), contentDescription = "",
            Modifier
                .height(15.dp)
                .align(Alignment.CenterHorizontally)) },colors = TextFieldDefaults.colors(focusedContainerColor = Color.White, unfocusedContainerColor = Color.White),trailingIcon = {
            androidx.compose.material3.IconButton(onClick = {
                vm.OnEvent(
                    AppEvents.ClearSelectedDropAddressText,
                    ""
                )
            }) {

                if (vm.mstate.selecteddropAddressLocationString.length > 0) {
                    androidx.compose.material3.Icon(
                        imageVector = Icons.Filled.Clear,
                        contentDescription = ""
                    )
                }
            }
        },value = vm.mstate.selecteddropAddressLocationString, onValueChange = {vm.OnEvent(AppEvents.SelectedDropAddressTextChanged,it)}, shape = RoundedCornerShape(30.dp), singleLine = true, modifier = Modifier, placeholder = {Text(vm.mstate.dropPlaceholder)} )
    }
}

@Composable
fun SearchDestinationAddressBarLocal2(vm:MapViewModel){

    Card(shape = RoundedCornerShape(30.dp),colors = CardDefaults.cardColors(containerColor =  Color.White), elevation = CardDefaults.cardElevation(10.dp), modifier = Modifier.fillMaxWidth(0.7f)) {
        OutlinedTextField(leadingIcon = { Image(painter = painterResource(id = R.drawable.dropblueicon), contentDescription = "",
            Modifier
                .height(15.dp)
                .align(Alignment.CenterHorizontally)) },colors = TextFieldDefaults.colors(focusedContainerColor = Color.White, unfocusedContainerColor = Color.White),trailingIcon = {
            androidx.compose.material3.IconButton(onClick = {
                vm.OnEvent(
                    AppEvents.ClearSelectedDropAddressText2,
                    ""
                )
            }) {

                if (vm.mstate.selecteddropAddressLocationString2.length > 0) {
                    androidx.compose.material3.Icon(
                        imageVector = Icons.Filled.Clear,
                        contentDescription = ""
                    )
                }
            }
        },value = vm.mstate.selecteddropAddressLocationString2, onValueChange = {vm.OnEvent(AppEvents.SelectedDropAddressTextChanged2,it)}, shape = RoundedCornerShape(30.dp), singleLine = true, modifier = Modifier, placeholder = {Text(vm.mstate.dropPlaceholder2)} )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchDestinationAddressBarResultsLoacl(vm:MapViewModel){


    for (placesdetails in vm.dropPredictionsList) {

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

                vm.OnEvent(AppEvents.FetchSelectedDropLocaionCoords,placesdetails.place_id,placesdetails)
                vm.dropPredictionsList =
                    emptyList()
                vm.OnEvent(AppEvents.UnBlurMap,"")
            }, elevation = CardDefaults.cardElevation(10.dp)) {
                TwoLineListItem(
                    headlineString = maintextString,
                    SupportingContentString = secondaryString
                )

            }

            Spacer(modifier = Modifier.height(10.dp))
        }


    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchDestinationAddressBarResultsLoacl2(vm:MapViewModel){


    for (placesdetails in vm.dropPredictionsList2) {

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

                vm.OnEvent(AppEvents.FetchSelectedDropLocaionCoords2,placesdetails.place_id,placesdetails)
                vm.dropPredictionsList2 =
                    emptyList()
                vm.OnEvent(AppEvents.UnBlurMap,"")
            }, elevation = CardDefaults.cardElevation(10.dp)) {
                TwoLineListItem(
                    headlineString = maintextString,
                    SupportingContentString = secondaryString
                )

            }

            Spacer(modifier = Modifier.height(10.dp))
        }


    }
}