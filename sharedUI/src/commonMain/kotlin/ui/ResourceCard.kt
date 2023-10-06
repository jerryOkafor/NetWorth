@file:OptIn(ExperimentalResourceApi::class)

package ui

import OneAndHalfHorizontalSpacer
import TwoVerticalSpacer
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource

@Composable
fun ResourceCard() {
    Card(colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "Resources", style = MaterialTheme.typography.titleMedium)
            TwoVerticalSpacer()
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    modifier = Modifier.size(32.dp),
                    painter = painterResource("ic_calendar_clock.xml"),
                    contentDescription = null,
                    colorFilter = ColorFilter.tint(contentColorFor(MaterialTheme.colorScheme.surface))
                )
                OneAndHalfHorizontalSpacer()
                Text(text = "Get Fund Report", style = MaterialTheme.typography.titleMedium)
            }
            TwoVerticalSpacer()
            Divider(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(color = Color(0xFFD9DBE9))
            )
            TwoVerticalSpacer()
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    modifier = Modifier.size(32.dp),
                    painter = painterResource("ic_history.xml"),
                    contentDescription = null,
                    colorFilter = ColorFilter.tint(contentColorFor(MaterialTheme.colorScheme.surface))
                )
                OneAndHalfHorizontalSpacer()
                Text(text = "Transaction History", style = MaterialTheme.typography.titleMedium)
            }
            TwoVerticalSpacer()
            Divider(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(color = Color(0xFFD9DBE9))
            )

            TwoVerticalSpacer()
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    modifier = Modifier.size(32.dp),
                    painter = painterResource("ic_money.xml"),
                    contentDescription = null,
                    colorFilter = ColorFilter.tint(contentColorFor(MaterialTheme.colorScheme.surface))
                )
                OneAndHalfHorizontalSpacer()
                Text(text = "Request Withdrawal", style = MaterialTheme.typography.titleMedium)
            }
            TwoVerticalSpacer()
        }
    }
}