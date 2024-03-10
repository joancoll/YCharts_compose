package cat.dam.andy.ycharts_compose.presentation

import android.graphics.Typeface
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cat.dam.andy.ycharts_compose.R
import cat.dam.andy.ycharts_compose.ui.compositions.AppBarWithBackButton
import cat.dam.andy.ycharts_compose.ui.theme.YChartsTheme
import co.yml.charts.common.components.Legends
import co.yml.charts.common.model.LegendLabel
import co.yml.charts.common.model.LegendsConfig
import co.yml.charts.common.model.PlotType
import co.yml.charts.ui.piechart.charts.PieChart
import co.yml.charts.ui.piechart.models.PieChartConfig
import co.yml.charts.ui.piechart.models.PieChartData

class PieChartActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            YChartsTheme {
                Scaffold(modifier = Modifier.fillMaxSize(),
                    contentColor = YChartsTheme.colors.background,
                    topBar = {
                        AppBarWithBackButton(
                            stringResource(id = R.string.title_pie_chart),
                            onBackPressed = {
                                onBackPressed()
                            })
                    })
                {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(it),
                        contentAlignment = Alignment.TopCenter
                    ) {
                        val pieChartData = PieChartData(
                            slices = listOf(
                                PieChartData.Slice("SciFi", 65f, Color.Red),
                                PieChartData.Slice("Comedy", 35f, Color.Blue),
                                PieChartData.Slice("Drama", 10f, Color.Green),
                                PieChartData.Slice("Romance", 40f, Color.Cyan)
                            ),
                            PlotType.Pie
                        )
                        val pieChartConfig = PieChartConfig(
                            showSliceLabels = true,
                            sliceLabelTextSize = 20.sp,
                            sliceLabelTextColor = Color.Black,
                            sliceLabelTypeface = Typeface.DEFAULT_BOLD,
                            labelVisible = true,
                            labelColorType = PieChartConfig.LabelColorType.SLICE_COLOR,
                            labelColor = Color.Magenta,
                            labelFontSize = 10.sp,
                            labelTypeface = Typeface.DEFAULT_BOLD,
                            labelType = PieChartConfig.LabelType.PERCENTAGE,
                            isAnimationEnable = true,
                            animationDuration = 1500,
                            backgroundColor = Color.LightGray,
                        )
                        val legendsConfig = LegendsConfig(
                            legendLabelList = listOf(
                                LegendLabel(Color.Red, "SciFi"),
                                LegendLabel(Color.Blue, "Comedy"),
                                LegendLabel(Color.Green, "Drama"),
                                LegendLabel(Color.Cyan, "Romance"),
                            ),
                            textSize = 20.sp,
                            colorBoxSize = 20.dp,
                            textStyle = TextStyle(
                                color = Color.Black,
                                fontWeight = FontWeight.SemiBold
                            ),
                            gridColumnCount = 4,
                            spaceBWLabelAndColorBox = 5.dp,
                            )
                        Column() {
                            Legends(legendsConfig = legendsConfig)
                            PieChart(
                                modifier = Modifier
                                    .width(400.dp)
                                    .height(400.dp),
                                pieChartData,
                                pieChartConfig
                            )
                        }
                    }
                }
            }
        }
    }
}


