@file:OptIn(ExperimentalMaterial3Api::class)

package com.gyanu.trider.components

import android.service.autofill.OnClickAction
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.gyanu.trider.R
import com.gyanu.trider.presentation.AppEvents
import com.gyanu.trider.presentation.MapViewModel

@Composable
fun SearchAddressBar(vm:MapViewModel){
    Card( elevation = CardDefaults.cardElevation(10.dp),shape = RoundedCornerShape(30.dp),colors = CardDefaults.cardColors(containerColor =  Color.White), modifier = Modifier.fillMaxWidth(0.7f)) {
        TextField(leadingIcon = { Image(painter = painterResource(id = R.drawable.pickupicon), contentDescription = "",
            Modifier
                .height(15.dp)
                ) }, colors = TextFieldDefaults.colors(focusedContainerColor = Color.White, unfocusedContainerColor = Color.White),trailingIcon = { IconButton(onClick = { vm.OnEvent(AppEvents.ClearPickupAddressText,"") }) {

           if(vm.mstate.pickupAddressLocationString.length>0){ Icon(imageVector = Icons.Filled.Clear, contentDescription = "")}
        }},value = vm.mstate.pickupAddressLocationString, onValueChange = {vm.OnEvent(AppEvents.PickUpAddressTextChanged,it)}, shape = RoundedCornerShape(30.dp), singleLine = true, modifier = Modifier, placeholder = { Text(
            text = vm.mstate.pickupPlaceholder
        )} )
    }
}



@Composable
fun SearchDestinationAddressBar(vm:MapViewModel){

    Card(shape = RoundedCornerShape(30.dp),colors = CardDefaults.cardColors(containerColor =  Color.White), elevation = CardDefaults.cardElevation(10.dp), modifier = Modifier.fillMaxWidth(0.7f)) {
        OutlinedTextField(leadingIcon = { Image(painter = painterResource(id = R.drawable.dropicon), contentDescription = "",
            Modifier
                .height(15.dp)
                .align(Alignment.CenterHorizontally)) },colors = TextFieldDefaults.colors(focusedContainerColor = Color.White, unfocusedContainerColor = Color.White),trailingIcon = { IconButton(onClick = { vm.OnEvent(AppEvents.ClearDropAddressText,"") }) {

            if(vm.mstate.dropAddressLocationString.length>0){ Icon(imageVector = Icons.Filled.Clear, contentDescription = "")}
        }},value = vm.mstate.dropAddressLocationString, onValueChange = {vm.OnEvent(AppEvents.DropAddressTextChanged,it)}, shape = RoundedCornerShape(30.dp), singleLine = true, modifier = Modifier, placeholder = {Text(vm.mstate.dropPlaceholder)} )
    }
}


@Composable
fun SearchDestinationAddressBar2(vm:MapViewModel){
    Card(shape = RoundedCornerShape(30.dp),colors = CardDefaults.cardColors(containerColor =  Color.White), elevation = CardDefaults.cardElevation(10.dp),modifier = Modifier.fillMaxWidth(0.7f)) {
        OutlinedTextField(leadingIcon = { Image(painter = painterResource(id = R.drawable.dropblueicon), contentDescription = "",Modifier.height(15.dp)) },colors = TextFieldDefaults.colors(focusedContainerColor = Color.White, unfocusedContainerColor = Color.White),trailingIcon = { IconButton(onClick = { vm.OnEvent(AppEvents.ClearDropAddressText2,"") }) {

            if(vm.mstate.dropAddressLocationString2.length>0){ Icon(imageVector = Icons.Filled.Clear, contentDescription = "")}
        }},value = vm.mstate.dropAddressLocationString2, onValueChange = {vm.OnEvent(AppEvents.DropAddressTextChanged2,it)}, shape = RoundedCornerShape(30.dp), singleLine = true, modifier = Modifier, placeholder = {Text(vm.mstate.dropPlaceholder2)} )
    }
}



@Composable
fun SearchDestinationAddressBarResults(vm:MapViewModel){


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

                vm.OnEvent(AppEvents.FetchDropLocaionCoords,placesdetails.place_id,placesdetails)
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



@Composable
fun SearchDestinationAddressBarResults2(vm:MapViewModel){


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

                vm.OnEvent(AppEvents.FetchDropLocaionCoords2,placesdetails.place_id,placesdetails)
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


