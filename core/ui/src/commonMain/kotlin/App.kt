@file:OptIn(ExperimentalResourceApi::class)

@file:Suppress("TooManyFunctions", "MultiLineIfElse", "MagicNumber")

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.contentColorFor
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.aay.compose.baseComponents.model.GridOrientation
import com.aay.compose.baseComponents.model.LegendPosition
import com.aay.compose.lineChart.LineChart
import com.aay.compose.lineChart.model.LineParameters
import com.aay.compose.lineChart.model.LineType
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import ui.Footer
import ui.ResourceCard
import ui.theme.HalfHorizontalSpacer
import ui.theme.HalfVerticalSpacer
import ui.theme.OneAndHalfHorizontalSpacer
import ui.theme.OneVerticalSpacer
import ui.theme.QuarterHorizontalSpacer
import ui.theme.TwoHorizontalSpacer
import ui.theme.TwoVerticalSpacer

private val LightColorScheme = lightColorScheme(
    surface = Color(0xFFFFFFFF),
    background = Color(0xFFF8F9FE),
)
private val DarkColorScheme = darkColorScheme(
    surface = Color(0xFF14142B),
    background = Color(0xFF25253C),
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun App(onAppThemeChange: (isDarkTheme: Boolean) -> Unit = {}) {
    var shouldUseDarkTheme by remember { mutableStateOf(true) }

    val colorScheme = remember(shouldUseDarkTheme) {
        if (!shouldUseDarkTheme) {
            LightColorScheme
        } else {
            DarkColorScheme
        }
    }

    MaterialTheme(colorScheme = colorScheme) {
        Column(
            Modifier
                .fillMaxSize()
                .background(color = MaterialTheme.colorScheme.background),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            CenterAlignedTopAppBar(
                title = { Text(text = "NetWorth") },
                colors = TopAppBarDefaults.topAppBarColors(),
                actions = {
                    Switch(
                        modifier = Modifier.padding(16.dp),
                        checked = shouldUseDarkTheme,
                        onCheckedChange = {
                            shouldUseDarkTheme = it
                            onAppThemeChange(shouldUseDarkTheme)
                        },
                        thumbContent = {
                            Icon(
                                painter = painterResource(
                                    if (shouldUseDarkTheme) "ic_dark_mode.xml" else "ic_light_mode.xml",
                                ),
                                contentDescription = "Toggle theme",
                            )
                        },
                    )
                },
            )
            Column(modifier = Modifier.padding(16.dp).verticalScroll(rememberScrollState())) {
                TwoVerticalSpacer()
                CurrentPortfolioCard()
                TwoVerticalSpacer()
                GrowthCard()
                TwoVerticalSpacer()
                Column(modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)) {
                    Text(
                        text = "Investments",
                        style = MaterialTheme.typography.headlineMedium,
                        color = contentColorFor(MaterialTheme.colorScheme.background),
                    )
                    TwoVerticalSpacer()
                    Row(
                        modifier = Modifier.height(IntrinsicSize.Min),
                        horizontalArrangement = Arrangement.spacedBy(16.dp),
                        verticalAlignment = Alignment.Top,
                    ) {
                        Column(
                            modifier = Modifier.weight(1F),
                            verticalArrangement = Arrangement.spacedBy(8.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                        ) {
                            Text(
                                text = "Active(2)",
                                style = MaterialTheme.typography.titleMedium,
                                color = Color(0xFF3B3DCC),
                            )
                            Divider(
                                modifier = Modifier.size(8.dp)
                                    .clip(CircleShape),
                                color = Color(0xFF3B3DCC),
                            )
                        }
                        Column(
                            modifier = Modifier.weight(1F),
                            verticalArrangement = Arrangement.spacedBy(8.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                        ) {
                            Text(
                                text = "InActive(2)",
                                style = MaterialTheme.typography.titleMedium,
                                color = Color(0xFFD9DBE9),
                            )
                        }
                    }
                }
                TwoVerticalSpacer()
                InvestmentCard(
                    name = "GrowFix Gold Dec 30",
                    icon = "ic_gfg.xml",
                    color = Color(0xFFF5C324),
                )
                TwoVerticalSpacer()
                InvestmentCard(
                    name = "GrowFix Wheels",
                    icon = "ic_gfw.xml",
                    color = Color(0xFFFF8A65),
                )
                TwoVerticalSpacer()
                ResourceCard()
                OneVerticalSpacer()
                Footer()
            }
        }
    }
}

@Composable
fun CurrentPortfolioCard() {
    Card(colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)) {
        Column(modifier = Modifier.padding(16.dp)) {
            Column {
                Text(
                    text = "Current Portfolio Value",
                    style = MaterialTheme.typography.labelMedium,
                )
                OneVerticalSpacer()
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "₹ 31,627.80",
                        style = MaterialTheme.typography.headlineLarge,
                        fontWeight = FontWeight.Bold,
                    )
                    HalfHorizontalSpacer()
                    Icon(
                        modifier = Modifier.scale(0.7F),
                        painter = painterResource("ic_arrow_up.xml"),
                        contentDescription = null,
                        tint = Color(0xFF53BB53),
                    )
                }
            }
            TwoVerticalSpacer()
            Divider(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(color = Color(0xFFD9DBE9)),
            )
            TwoVerticalSpacer()

            Row(
                modifier = Modifier,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    horizontalAlignment = Alignment.Start,
                ) {
                    Text(text = "Total Money Invested", style = MaterialTheme.typography.bodySmall)
                    Text(
                        text = "₹ 31,477.50",
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Bold,
                    )
                }
                TwoHorizontalSpacer()
                Column(
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    horizontalAlignment = Alignment.Start,
                ) {
                    Text(text = "Repayment Done", style = MaterialTheme.typography.bodySmall)
                    Text(
                        text = "₹ 0.00",
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Bold,
                    )
                }
            }
        }
    }
}

@Composable
fun GrowthCard(targetProgress: Float = 0.45F) {
    var progress by remember { mutableStateOf(0F) }
    val progressAnimation by animateFloatAsState(
        targetValue = progress,
        animationSpec = tween(
            durationMillis = 4000,
            delayMillis = 500,
            easing = FastOutSlowInEasing,
        ),
        label = "progressAnimation",
    )

    Card(colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)) {
        Row(
            modifier = Modifier.height(IntrinsicSize.Min).padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(24.dp),
        ) {
            Column(modifier = Modifier.weight(1F)) {
                Text(
                    text = "Growth",
                    style = MaterialTheme.typography.bodyLarge,
                )
                OneVerticalSpacer()
                HalfVerticalSpacer()
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "₹ 150.30",
                        style = MaterialTheme.typography.headlineLarge,
                        fontWeight = FontWeight.Bold,
                    )
                    HalfHorizontalSpacer()
                    Icon(
                        modifier = Modifier.scale(0.8F),
                        painter = painterResource("ic_arrow_up.xml"),
                        contentDescription = null,
                        tint = Color(0xFF53BB53),
                    )
                }
                TwoVerticalSpacer()
                HalfVerticalSpacer()
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Image(
                        modifier = Modifier.size(32.dp),
                        painter = painterResource("ic_gfg.xml"),
                        contentDescription = null,
                    )
                    OneAndHalfHorizontalSpacer()
                    Text(text = "GrowFix Gold", style = MaterialTheme.typography.bodyMedium)
                }
                TwoVerticalSpacer()
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Image(
                        modifier = Modifier.size(32.dp),
                        painter = painterResource("ic_gfw.xml"),
                        contentDescription = null,
                    )
                    OneAndHalfHorizontalSpacer()
                    Text(text = "GrowFix Wheels", style = MaterialTheme.typography.bodyMedium)
                }
            }

            Column(
                modifier = Modifier.weight(1F).fillMaxHeight(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Bottom,
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth(0.8F)
                        .aspectRatio(1F),
                ) {
                    CircularProgressIndicator(
                        modifier = Modifier.fillMaxSize(),
                        progress = progressAnimation,
                        strokeCap = StrokeCap.Round,
                        strokeWidth = 10.dp,
                        color = Color(0xFFF5C324),
                        trackColor = Color(0xFFFF8A65),
                    )
                    Row(
                        modifier = Modifier.align(Alignment.Center),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Text(text = "+0.47%")
                        QuarterHorizontalSpacer()
                        Icon(
                            modifier = Modifier.scale(0.7F),
                            painter = painterResource("ic_arrow_up.xml"),
                            contentDescription = null,
                            tint = Color(0xFF53BB53),
                        )
                    }
                }
            }
        }
    }

    LaunchedEffect(targetProgress) {
        progress = targetProgress
    }
}

@Composable
fun InvestmentCard(name: String, icon: String, color: Color) {
    Card(colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    modifier = Modifier.size(32.dp),
                    painter = painterResource(icon),
                    contentDescription = null,
                )
                TwoHorizontalSpacer()
                Text(
                    text = name,
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold,
                )
                HalfHorizontalSpacer()
                Icon(
                    modifier = Modifier.scale(0.8F),
                    painter = painterResource("ic_chevron.xml"),
                    contentDescription = null,
                    tint = contentColorFor(MaterialTheme.colorScheme.surface),
                )
            }
            TwoVerticalSpacer()
            Row(
                modifier = Modifier,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    horizontalAlignment = Alignment.Start,
                ) {
                    Text(
                        text = "Current Portfolio Value",
                        style = MaterialTheme.typography.bodySmall,
                    )
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = "₹ 31,477.50",
                            style = MaterialTheme.typography.headlineSmall,
                            fontWeight = FontWeight.Bold,
                        )
                        HalfHorizontalSpacer()
                        Icon(
                            modifier = Modifier.scale(0.7F),
                            painter = painterResource("ic_arrow_up.xml"),
                            contentDescription = null,
                            tint = Color(0xFF53BB53),
                        )
                    }
                }
                TwoHorizontalSpacer()
                Column(
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    horizontalAlignment = Alignment.Start,
                ) {
                    Text(text = "Growth", style = MaterialTheme.typography.bodySmall)
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = "₹ 0.00",
                            style = MaterialTheme.typography.headlineSmall,
                            fontWeight = FontWeight.Bold,
                        )
                        HalfHorizontalSpacer()
                        Icon(
                            modifier = Modifier.scale(0.7F),
                            painter = painterResource("ic_arrow_up.xml"),
                            contentDescription = null,
                            tint = Color(0xFF53BB53),
                        )
                    }
                }
            }
            TwoVerticalSpacer()
            Divider(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(color = Color(0xFFD9DBE9)),
            )
            TwoVerticalSpacer()
            Row(
                modifier = Modifier,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    horizontalAlignment = Alignment.Start,
                ) {
                    Text(text = "Investment", style = MaterialTheme.typography.bodySmall)
                    Text(
                        text = "₹ 31,477.50",
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Bold,
                    )
                }
                TwoHorizontalSpacer()
                Column(
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    horizontalAlignment = Alignment.Start,
                ) {
                    Text(text = "Pre TAX Return", style = MaterialTheme.typography.bodySmall)
                    Text(
                        text = "11 %",
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Bold,
                    )
                }
            }
        }
        LineChartSample(modifier = Modifier.height(100.dp).fillMaxWidth(), color = color)
    }
}

// More on edge-to-edge = https://github.com/android/nowinandroid/pull/817/files
// Compose KMP Chart: https://github.com/TheChance101/AAY-chart

@Composable
fun LineChartSample(modifier: Modifier = Modifier, color: Color) {
    val testLineParameters: List<LineParameters> = listOf(
        LineParameters(
            label = "Growth",
            data = listOf(2.0, 3.0, 4.0, 6.0, 5.0, 7.33),
            lineColor = color,
            lineType = LineType.CURVED_LINE,
            lineShadow = true,
        ),
    )

    Box(modifier = modifier) {
        LineChart(
            modifier = Modifier.fillMaxWidth(),
            linesParameters = testLineParameters,
            isGrid = false,
            gridColor = Color.Blue,
            showYAxis = false,
            showXAxis = false,
            xAxisData = listOf(
                "2012",
                "2013",
                "2014",
                "2015",
                "2016",
                "2017",
            ),
            legendPosition = LegendPosition.DISAPPEAR,
            animateChart = true,
            showGridWithSpacer = false,
            yAxisStyle = TextStyle(
                fontSize = 14.sp,
                color = Color(0xFFF5C324),
            ),
            xAxisStyle = TextStyle(
                fontSize = 14.sp,
                color = Color(0xFFF5C324),
                fontWeight = FontWeight.W400,
            ),
            yAxisRange = 20,
            oneLineChart = false,
            gridOrientation = GridOrientation.VERTICAL,
        )
    }
}
