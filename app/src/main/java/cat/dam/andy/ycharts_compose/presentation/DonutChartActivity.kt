package cat.dam.andy.ycharts_compose.presentation

import android.graphics.Typeface
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
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
import co.yml.charts.ui.piechart.charts.DonutPieChart
import co.yml.charts.ui.piechart.models.PieChartConfig
import co.yml.charts.ui.piechart.models.PieChartData

class DonutChartActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            YChartsTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    contentColor = YChartsTheme.colors.background,
                    topBar = {
                        AppBarWithBackButton(
                            stringResource(id = R.string.title_donut_chart),
                            onBackPressed = {
                                onBackPressed()
                            })
                    })
                {
                    val context = LocalContext.current
                    Box(
                        modifier = Modifier
                            .padding(it)
                            .fillMaxSize()
                    ) {
                        Spacer(modifier = Modifier.height(20.dp))
                        val donutChartData = PieChartData(
                            slices = listOf(
                                PieChartData.Slice("HP", 15f, Color.Red),
                                PieChartData.Slice("Dell", 30f, Color.Blue),
                                PieChartData.Slice("Lenovo", 40f,  Color.Green),
                                PieChartData.Slice("Asus", 10f, Color.Cyan)
                            ),
                            PlotType.Donut
                        )
                        val donutChartConfig = PieChartConfig(
                            showSliceLabels = true,
                            sliceLabelTextSize = 40.sp,
                            sliceLabelTextColor = Color.Black,
                            sliceLabelTypeface = Typeface.DEFAULT_BOLD,
                            labelVisible = true,
                            labelColorType = PieChartConfig.LabelColorType.SPECIFIED_COLOR,
                            labelColor = Color.Black,
                            labelFontSize = 40.sp,
                            labelTypeface = Typeface.DEFAULT_BOLD,
                            labelType = PieChartConfig.LabelType.PERCENTAGE,
                            isAnimationEnable = true,
                            animationDuration = 1500,
                            backgroundColor = Color.White,
                        )
                        val legendsConfig = LegendsConfig(
                            legendLabelList = listOf(
                                LegendLabel(Color.Red, "HP"),
                                LegendLabel(Color.Blue, "Dell"),
                                LegendLabel(Color.Green, "Lenovo"),
                                LegendLabel(Color.Cyan, "Asus"),
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
                        Column(){
                            DonutPieChart(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(500.dp),
                                donutChartData,
                                donutChartConfig
                            )
                            Legends(legendsConfig = legendsConfig)
                        }
                    }
                }
            }
        }
    }
}
