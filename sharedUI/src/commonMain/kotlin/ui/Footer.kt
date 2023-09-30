@file:OptIn(ExperimentalResourceApi::class)

package ui

import ThreeVerticalSpacer
import TwoVerticalSpacer
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource

@Composable
fun Footer() {
    Box(modifier = Modifier.fillMaxWidth().wrapContentSize()) {
        Column(
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                modifier = Modifier.size(250.dp),
                painter = painterResource("ic_footer.webp"),
                contentDescription = null,
            )
            TwoVerticalSpacer()
            Text(
                text = "Download our Mobile\nApp and be updated",
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodySmall,
                color = contentColorFor(MaterialTheme.colorScheme.surface),
                maxLines = 2,
            )
            ThreeVerticalSpacer()
            Button(onClick = {}) { Text(text = "Download App") }
            ThreeVerticalSpacer()
        }
    }
}