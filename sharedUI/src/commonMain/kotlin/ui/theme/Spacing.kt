@file:Suppress("TooManyFunctions")

package ui.theme

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

// Horizontal
@Composable
fun RowScope.FillingSpacer() {
    Spacer(modifier = Modifier.weight(1F))
}

@Composable
fun QuarterHorizontalSpacer() {
    Spacer(modifier = Modifier.width(2.dp))
}

@Composable
fun HalfHorizontalSpacer() {
    Spacer(modifier = Modifier.width(4.dp))
}

@Composable
fun OneHorizontalSpacer() {
    Spacer(modifier = Modifier.width(8.dp))
}

@Composable
fun OneAndHalfHorizontalSpacer() {
    Spacer(modifier = Modifier.width(12.dp))
}

@Composable
fun TwoHorizontalSpacer() {
    Spacer(modifier = Modifier.width(16.dp))
}

// Vertical
@Composable
fun ColumnScope.FillingSpacer() {
    Spacer(modifier = Modifier.weight(1F))
}

@Composable
fun ThreeVerticalSpacer() {
    Spacer(modifier = Modifier.height(24.dp))
}

@Composable
fun TwoVerticalSpacer() {
    Spacer(modifier = Modifier.height(16.dp))
}

@Composable
fun OneVerticalSpacer() {
    Spacer(modifier = Modifier.height(8.dp))
}

@Composable
fun HalfVerticalSpacer() {
    Spacer(modifier = Modifier.height(4.dp))
}

@Composable
fun QuarterVerticalSpacer() {
    Spacer(modifier = Modifier.height(2.dp))
}
