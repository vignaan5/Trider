@file:OptIn(ExperimentalWearMaterialApi::class)

package com.gyanu.trider.components

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.wear.compose.material.ExperimentalWearMaterialApi
import androidx.wear.compose.material.FractionalThreshold
import androidx.wear.compose.material.rememberSwipeableState
import androidx.wear.compose.material.swipeable
import kotlin.math.roundToInt

@Composable
fun getanimatedColor(): Color {
    val infiniteTransition = rememberInfiniteTransition(label = "infinite transition")
    val animatedColor by infiniteTransition.animateColor(
        initialValue = Color(android.graphics.Color.parseColor("#FFDF00")),
        targetValue = Color.Yellow,
        animationSpec = infiniteRepeatable(tween(10000), RepeatMode.Reverse),
        label = "color"
    )
    return animatedColor
}


@Composable
fun SwipeButton(
    modifier: Modifier = Modifier,
    shape: Shape = RoundedCornerShape(16.dp),
    backgroundColor: Color = Color.White,
    borderStroke: BorderStroke = BorderStroke(2.dp, Color.Black),
    elevation: Dp = 8.dp,
    icon: @Composable () -> Unit,
    text: String,
    textStyle: TextStyle = TextStyle(Color.Black, 20.sp),
    onSwipe: () -> Unit
) {
    val swipeableState = rememberSwipeableState(initialValue = 0)
    val textAlpha by animateFloatAsState(
        if (swipeableState.offset.value > 10f) (1 - swipeableState.progress.fraction) else 1f
    )

    if (swipeableState.isAnimationRunning) {
        DisposableEffect(Unit) {
            onDispose {
                if (swipeableState.currentValue == 1) {
                    onSwipe()
                }
            }
        }
    }

    Surface(
        modifier = modifier.fillMaxWidth(),
        shape = shape,
        color = backgroundColor,
        border = borderStroke,
        elevation = elevation
    ) {
        BoxWithConstraints(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
        ) {
            var iconSize by remember { mutableStateOf(IntSize.Zero) }
            val maxWidth = with(LocalDensity.current) {
                this@BoxWithConstraints.maxWidth.toPx() - iconSize.width
            }

            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.Center),
                textAlign = TextAlign.End,
                text = text,
                style = textStyle.copy(color = textStyle.color.copy(alpha = textAlpha))
            )
            Box(
                modifier = Modifier
                    .onGloballyPositioned {
                        iconSize = it.size
                    }
                    .swipeable(
                        state = swipeableState,
                        anchors = mapOf(
                            0f to 0,
                            maxWidth to 1
                        ),
                        thresholds = { _, _ -> FractionalThreshold(0.9f) },
                        orientation = Orientation.Horizontal
                    )
                    .offset {
                        IntOffset(swipeableState.offset.value.roundToInt(), 0)
                    }
            ) {
                icon()
            }
        }
    }
}