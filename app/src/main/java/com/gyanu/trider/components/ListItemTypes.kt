package com.gyanu.trider.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.resolveDefaults
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gyanu.trider.R
import com.gyanu.trider.R.drawable


@Composable
fun ThreeLineListItem(headlineString:String,SupportingContentString:String,trailingContentString:String="",overlineContent:String) {
    ListItem(
        headlineContent = { Text(headlineString) },
        overlineContent = { Text(overlineContent) },
        supportingContent = { Text(SupportingContentString) },
        leadingContent = {
            Image(
                painter = painterResource(id = R.drawable.bike2),
                contentDescription = "Localized description", modifier = Modifier
                    .fillMaxHeight(1f)
                    .fillMaxWidth(0.3f)
            )
        },
        trailingContent = { Text(trailingContentString , fontSize = 20.sp) }
    )
}

@Composable
fun ThreeLineListItemProfile(headlineString:String,SupportingContentString:String,trailingContentString:String="",overlineContent:String) {
    ListItem(
        headlineContent = { Text(headlineString) },
        overlineContent = { Text(overlineContent) },
        supportingContent = { Text(SupportingContentString) },
        leadingContent = {
            Icon(imageVector = Icons.Filled.AccountCircle, contentDescription = "" )
        },
        trailingContent = { Text(trailingContentString ) },  colors = ListItemDefaults.colors(containerColor = Color(android.graphics.Color.parseColor("#FFBF00"))))

}




@Composable
fun ThreeLineListItemCab(headlineString:String,SupportingContentString:String,trailingContentString:String="",overlineContent:String) {
    ListItem(
        headlineContent = { Text(headlineString) },
        overlineContent = { Text(overlineContent) },
        supportingContent = { Text(SupportingContentString) },
        leadingContent = {
            Image(
                painter = painterResource(id = R.drawable.car2),
                contentDescription = "Localized description", modifier = Modifier
                    .fillMaxHeight(1f)
                    .fillMaxWidth(0.3f)
            )
        },
        trailingContent = { Text(trailingContentString , fontSize = 20.sp) }
    )
}

@Composable
fun ThreeLineListItemRickshaw(headlineString:String,SupportingContentString:String,trailingContentString:String="",overlineContent:String) {
    ListItem(
        headlineContent = { Text(headlineString) },
        overlineContent = { Text(overlineContent) },
        supportingContent = { Text(SupportingContentString) },
        leadingContent = {
            Image(
                painter = painterResource(id = R.drawable.rickshawrz),
                contentDescription = "Localized description", modifier = Modifier
                    .fillMaxHeight(1f)
                    .fillMaxWidth(0.3f)
            )
        },
        trailingContent = { Text(trailingContentString , fontSize = 20.sp) }
    )
}

@Composable
fun CardwithTwoLines(headlineString:String,SupportingContentString:String,trailingContentString:String=""){
      Card(shape = RoundedCornerShape(40.dp)) {
          TwoLineListItem(headlineString= headlineString,SupportingContentString=SupportingContentString,trailingContentString=trailingContentString)
      }
}


@Composable
fun TwoLineListItem(headlineString:String,SupportingContentString:String,trailingContentString:String="",containerColor:String="#FFBF00") {
    ListItem(
        headlineContent = { Text(text = headlineString) },
        supportingContent = { Text(text = SupportingContentString) },
        trailingContent = { Text(text = trailingContentString) },
        leadingContent = {
            Icon(
                Icons.Filled.LocationOn,
                contentDescription = "",
            )
        }, colors = ListItemDefaults.colors(containerColor = Color(android.graphics.Color.parseColor("#FFBF00")))
    )
}


@Composable
fun TwoLineListItemProfile(headlineString:String,SupportingContentString:String,trailingContentString:String="",containerColor:String="#FFBF00") {
    ListItem(
        headlineContent = { Text(text = headlineString) },
        supportingContent = { Text(text = SupportingContentString) },
        trailingContent = { Text(text = trailingContentString) },
        leadingContent = {
            Icon(
                Icons.Filled.Person,
                contentDescription = "",
            )
        }, colors = ListItemDefaults.colors(containerColor =  Color(android.graphics.Color.parseColor("#FFDF00")))
    )
}


@Composable
fun TwoLineListItemPayments(headlineString:String,SupportingContentString:String,trailingContentString:String="",containerColor:String="#FFBF00") {
    ListItem(
        headlineContent = { Text(text = headlineString) },
        supportingContent = { Text(text = SupportingContentString) },
        trailingContent = { Text(text = trailingContentString) },
        leadingContent = {
            Icon(
                painter = painterResource(id = R.drawable.creditcard),
                contentDescription = "",
            )
        }, colors = ListItemDefaults.colors(containerColor = Color(android.graphics.Color.parseColor("#FFDF00")) )
    )
}

@Composable
fun TwoLineListItemRides(headlineString:String,SupportingContentString:String,trailingContentString:String="",containerColor:String="#FFBF00") {
    ListItem(
        headlineContent = { Text(text = headlineString) },
        supportingContent = { Text(text = SupportingContentString) },
        trailingContent = { Text(text = trailingContentString) },
        leadingContent = {
           Icon(imageVector = Icons.Filled.DateRange, contentDescription = "")
        }, colors = ListItemDefaults.colors(containerColor = Color(android.graphics.Color.parseColor("#FFDF00")))
    )
}

@Composable
fun TwoLineListItemSettings(headlineString:String,SupportingContentString:String,trailingContentString:String="",containerColor:String="#FFBF00") {
    ListItem(
        headlineContent = { Text(text = headlineString) },
        supportingContent = { Text(text = SupportingContentString) },
        trailingContent = { Text(text = trailingContentString) },
        leadingContent = {
            Icon(imageVector = Icons.Filled.Settings, contentDescription = "")
        }, colors = ListItemDefaults.colors(containerColor = Color(android.graphics.Color.parseColor("#FFDF00")))
    )
}

@Composable
fun TwoLineListItemSafety(headlineString:String,SupportingContentString:String,trailingContentString:String="",containerColor:String="#FFBF00") {
    ListItem(
        headlineContent = { Text(text = headlineString) },
        supportingContent = { Text(text = SupportingContentString) },
        trailingContent = { Text(text = trailingContentString) },
        leadingContent = {
            Icon(imageVector = Icons.Filled.CheckCircle, contentDescription = "")
        }, colors = ListItemDefaults.colors(containerColor = Color(android.graphics.Color.parseColor("#FFDF00")))
    )
}


@Composable
fun TwoLineListItemBlueColor(headlineString:String,SupportingContentString:String,trailingContentString:String="",containerColor:String="#FFBF00") {
    ListItem(
        headlineContent = { Text(text = headlineString) },
        supportingContent = { Text(text = SupportingContentString) },
        trailingContent = { Text(text = trailingContentString) },
        leadingContent = {
            Icon(
                Icons.Filled.LocationOn,
                contentDescription = "",
            )
        }, colors = ListItemDefaults.colors(containerColor = Color(android.graphics.Color.parseColor("#000000")), headlineColor = Color.Red, trailingIconColor = Color.Red, leadingIconColor = Color.Red, overlineColor = Color.Red, supportingColor = Color.Red )
    )
}

@Composable
fun TwoLineListItemStandard(headlineString:String,SupportingContentString:String,trailingContentString:String="",containerColor:String="#FFBF00") {
    ListItem(
        headlineContent = { Text(text = headlineString) },
        supportingContent = { Text(text = SupportingContentString) },
        trailingContent = { Text(text = trailingContentString) },
        leadingContent = {
            Icon(
                Icons.Filled.LocationOn,
                contentDescription = "",
            )
        }, colors = ListItemDefaults.colors(containerColor = Color.White)
    )
}