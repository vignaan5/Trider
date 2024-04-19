package com.gyanu.trider

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationRequest
import android.os.Bundle
import android.window.BackEvent
import androidx.activity.ComponentActivity
import androidx.activity.addCallback
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.gyanu.trider.components.SearchAddressBar
import com.gyanu.trider.components.SwipeButton
import com.gyanu.trider.components.TailoredMap
import com.gyanu.trider.components.ThreeLineListItem
import com.gyanu.trider.components.TwoLineListItem
import com.gyanu.trider.components.TwoLineListItemPayments
import com.gyanu.trider.components.TwoLineListItemProfile
import com.gyanu.trider.components.TwoLineListItemRides
import com.gyanu.trider.components.TwoLineListItemSafety
import com.gyanu.trider.components.TwoLineListItemSettings
import com.gyanu.trider.components.getanimatedColor

import com.gyanu.trider.presentation.MapViewModel
import com.gyanu.trider.presentation.screens.HomeScreen
import com.gyanu.trider.presentation.screens.SetupNavGraph
import com.gyanu.trider.ui.theme.TriderTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()



        var fussedLocation: FusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)
        lateinit var navController:NavHostController

        setContent {








                navController = rememberNavController()
                val vM = MapViewModel()


                val scope = rememberCoroutineScope()


                val infiniteTransition = rememberInfiniteTransition(label = "infinite transition")
                val animatedColor by infiniteTransition.animateColor(
                    initialValue = Color(0xFF60DDAD),
                    targetValue = Color(0xFF4285F4),
                    animationSpec = infiniteRepeatable(tween(1000), RepeatMode.Reverse),
                    label = "color"
                )


                ModalNavigationDrawer(
                    drawerContent = {

                        ModalDrawerSheet(
                            Modifier.padding(top = 10.dp),
                            drawerContainerColor = Color.White
                        ) {
                            Column {
                                Spacer(modifier = Modifier.fillMaxSize(0.015f))

                            }


                            Card(
                                shape = RoundedCornerShape(30.dp),
                                colors = CardDefaults.cardColors(containerColor = Color.White)
                            ) {
                                IconButton(onClick = { scope.launch { vM.mDrawerState.close() } }) {
                                    Icon(
                                        imageVector = Icons.Filled.ArrowBack,
                                        contentDescription = ""
                                    )
                                }
                            }

                            Column {
                                Spacer(modifier = Modifier.height(10.dp))
                            }

                            Column(
                                modifier = Modifier
                                    .fillMaxHeight(0.3f)
                                    .fillMaxWidth(0.95f)
                                    .align(Alignment.CenterHorizontally)
                                    .background(
                                        color = Color.White,
                                        shape = RoundedCornerShape(30.dp)
                                    ),
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {

                                Card(
                                    colors = CardDefaults.cardColors(containerColor = getanimatedColor()),
                                    elevation = CardDefaults.cardElevation(15.dp)
                                ) {
                                    Image(
                                        painter = painterResource(id = R.drawable.tridersvg),
                                        contentDescription = "Trider"
                                    )
                                }


                            }

                            NavigationDrawerItem(modifier = Modifier
                                .padding(top = 20.dp)
                                .height(100.dp),
                                label = {
                                    Column(
                                        horizontalAlignment = Alignment.CenterHorizontally,
                                        verticalArrangement = Arrangement.Center
                                    ) {


                                        //    Icon(imageVector = Icons.Filled.AccountCircle, contentDescription = "")
                                        Card(
                                            colors = CardDefaults.cardColors(containerColor = Color.Yellow),
                                            elevation = CardDefaults.cardElevation(15.dp)
                                        ) {
                                            TwoLineListItemProfile(
                                                headlineString = "Gyanu",
                                                SupportingContentString = "View your profile"
                                            )
                                        }


                                    }

                                },

                                selected = false,
                                onClick = { navController.navigate(Screen.Profile.route); scope.launch { vM.mDrawerState.close() } },
                                colors = NavigationDrawerItemDefaults.colors(
                                    unselectedContainerColor = Color.White
                                )
                            )


                            NavigationDrawerItem(modifier = Modifier
                                .padding(top = 20.dp)
                                .height(50.dp)
                                .fillMaxWidth(0.6f)
                                .align(Alignment.CenterHorizontally),
                                label = {
                                    Column(
                                        horizontalAlignment = Alignment.CenterHorizontally,
                                        verticalArrangement = Arrangement.Center
                                    ) {


                                        //    Icon(imageVector = Icons.Filled.AccountCircle, contentDescription = "")
                                        Card(
                                            colors = CardDefaults.cardColors(containerColor = Color.White),
                                            elevation = CardDefaults.cardElevation(15.dp)
                                        ) {
                                            TwoLineListItemPayments(
                                                headlineString = "Payments",
                                                SupportingContentString = ""
                                            )
                                        }


                                    }

                                },

                                selected = false,
                                onClick = { /*TODO*/ },
                                colors = NavigationDrawerItemDefaults.colors(
                                    unselectedContainerColor = Color.White
                                )
                            )



                            NavigationDrawerItem(modifier = Modifier
                                .padding(top = 20.dp)
                                .height(50.dp)
                                .fillMaxWidth(0.6f)
                                .align(Alignment.CenterHorizontally),
                                label = {
                                    Column(
                                        horizontalAlignment = Alignment.CenterHorizontally,
                                        verticalArrangement = Arrangement.Center
                                    ) {


                                        //    Icon(imageVector = Icons.Filled.AccountCircle, contentDescription = "")
                                        Card(
                                            colors = CardDefaults.cardColors(containerColor = Color.White),
                                            elevation = CardDefaults.cardElevation(15.dp)
                                        ) {
                                            TwoLineListItemRides(
                                                headlineString = "Your Rides",
                                                SupportingContentString = ""
                                            )
                                        }


                                    }

                                },

                                selected = false,
                                onClick = { /*TODO*/ },
                                colors = NavigationDrawerItemDefaults.colors(
                                    unselectedContainerColor = Color.White
                                )
                            )

                            NavigationDrawerItem(modifier = Modifier
                                .padding(top = 20.dp)
                                .height(50.dp)
                                .fillMaxWidth(0.6f)
                                .align(Alignment.CenterHorizontally),
                                label = {
                                    Column(
                                        horizontalAlignment = Alignment.CenterHorizontally,
                                        verticalArrangement = Arrangement.Center
                                    ) {


                                        //    Icon(imageVector = Icons.Filled.AccountCircle, contentDescription = "")
                                        Card(
                                            colors = CardDefaults.cardColors(containerColor = Color.White),
                                            elevation = CardDefaults.cardElevation(15.dp)
                                        ) {
                                            TwoLineListItemSettings(
                                                headlineString = "Settings",
                                                SupportingContentString = ""
                                            )
                                        }


                                    }

                                },

                                selected = false,
                                onClick = { /*TODO*/ },
                                colors = NavigationDrawerItemDefaults.colors(
                                    unselectedContainerColor = Color.White
                                )
                            )


                            NavigationDrawerItem(modifier = Modifier
                                .padding(top = 20.dp)
                                .height(50.dp)
                                .fillMaxWidth(0.6f)
                                .align(Alignment.CenterHorizontally),
                                label = {
                                    Column(
                                        horizontalAlignment = Alignment.CenterHorizontally,
                                        verticalArrangement = Arrangement.Center
                                    ) {


                                        //    Icon(imageVector = Icons.Filled.AccountCircle, contentDescription = "")
                                        Card(
                                            colors = CardDefaults.cardColors(containerColor = Color.White),
                                            elevation = CardDefaults.cardElevation(15.dp)
                                        ) {
                                            TwoLineListItemSafety(
                                                headlineString = "Safety",
                                                SupportingContentString = ""
                                            )
                                        }


                                    }

                                },

                                selected = false,
                                onClick = { /*TODO*/ },
                                colors = NavigationDrawerItemDefaults.colors(
                                    unselectedContainerColor = Color.White
                                )
                            )

                        }


                    },
                    gesturesEnabled = false,
                    modifier = Modifier.fillMaxSize(),
                    drawerState = vM.mDrawerState
                )
                {
                    SetupNavGraph(navController = navController, vM, fussedLocation)
                    getLocationPermission(vM)

                }



        }
    }

    override fun onPause() {
        super.onPause()
        println("-> Activity paused")
    }

     fun getLocationPermission(viewModel: MapViewModel) {


             val PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1234

        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            )
            == PackageManager.PERMISSION_GRANTED
        ) {
            viewModel.islocationpermissiongiven = true//we already have the permission
        } else {
            ActivityCompat.requestPermissions(
                this@MainActivity, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION), PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION)
        }
    }


}
