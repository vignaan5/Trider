package com.gyanu.trider.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.CardDefaults
import com.gyanu.trider.R
import com.gyanu.trider.R.drawable
import kotlinx.coroutines.delay


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DisplayAdd(height:Int){

    val colors by remember{ mutableStateOf( arrayOf<Color>(Color.Yellow,Color.Cyan,Color.LightGray,Color.Unspecified))}

    val pagerState = rememberPagerState(pageCount = {
        4
    })

androidx.compose.material3.Card(shape = RoundedCornerShape(30.dp), elevation = androidx.compose.material3.CardDefaults.cardElevation(10.dp)) {


    HorizontalPager(
        state = pagerState,
        modifier = Modifier
            .fillMaxWidth(0.8f)
            .height(height.dp)
            .background(colors[pagerState.currentPage])
    ) { page ->
        // Our page content

            Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxSize()) {
                Image(painter = painterResource(id = drawable.tridersvg), contentDescription = "",Modifier.fillMaxHeight())



        }
    }

    LaunchedEffect(key1 = 1) {
        while (true)
        {
            delay(4000)
                   if(pagerState.currentPage==3||pagerState.currentPage>=4)
                {pagerState.animateScrollToPage(0)}

                else{
                                   if(pagerState.currentPage+1<4)
                      pagerState.animateScrollToPage(pagerState.currentPage+1)
                   }

        }
    }

}
}

