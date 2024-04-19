package com.gyanu.trider.presentation.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.ListItem
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.gyanu.trider.R
import com.gyanu.trider.R.color

@SuppressLint("ResourceAsColor")
@Composable
fun profileScreen(navController: NavController){
    Column {
         Row (verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Start){
             IconButton(onClick = { navController.popBackStack() }) {
                 Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "")
             }

             Text(text = "Profile", fontSize = 20.sp)
         }


        Spacer(modifier = Modifier.height(15.dp))

        Column(
            Modifier
                .fillMaxHeight()
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Card(elevation = CardDefaults.cardElevation(15.dp), colors = CardDefaults.cardColors( containerColor = Color.Yellow, contentColor = Color.Yellow)) {
                androidx.compose.material3.ListItem(
                    headlineContent = { Text(text = "Name") },
                    leadingContent = {
                        Icon(
                            imageVector = Icons.Filled.Person,
                            contentDescription = ""
                        )
                    },
                    supportingContent = {
                        Text(
                            text = "Vignaan Chintapalli"
                        )
                    })

                androidx.compose.material3.ListItem(
                    headlineContent = { Text(text = "Mobile") },
                    leadingContent = {
                        Icon(
                            imageVector = Icons.Filled.Phone,
                            contentDescription = ""
                        )
                    },
                    supportingContent = {
                        Text(
                            text = "+919603680535"
                        )
                    })

                androidx.compose.material3.ListItem(
                    headlineContent = { Text(text = "Email") },
                    leadingContent = {
                        Icon(
                            imageVector = Icons.Filled.Email,
                            contentDescription = ""
                        )
                    },
                    supportingContent = {
                        Text(
                            text = "vignaan@outlook.com"
                        )
                    })

                androidx.compose.material3.ListItem(
                    headlineContent = { Text(text = "Rating") },
                    leadingContent = {
                        Icon(
                            imageVector = Icons.Filled.Star,
                            contentDescription = ""
                        )
                    },
                    supportingContent = {
                        Text(
                            text = "4.5"
                        )
                    })

            }
        }
    }
}