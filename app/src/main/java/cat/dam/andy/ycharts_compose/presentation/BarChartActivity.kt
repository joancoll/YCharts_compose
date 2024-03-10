package cat.dam.andy.ycharts_compose.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import cat.dam.andy.ycharts_compose.R
import cat.dam.andy.ycharts_compose.ui.compositions.AppBarWithBackButton
import cat.dam.andy.ycharts_compose.ui.theme.YChartsTheme
import co.yml.charts.axis.AxisData
import co.yml.charts.axis.DataCategoryOptions
import co.yml.charts.common.utils.DataUtils
import co.yml.charts.ui.barchart.BarChart
import co.yml.charts.ui.barchart.models.BarChartData
import co.yml.charts.ui.barchart.models.BarChartType
import co.yml.charts.ui.barchart.models.BarData
import co.yml.charts.ui.barchart.models.BarStyle

class BarChartActivity : ComponentActivity() {

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            YChartsTheme {
                Scaffold(modifier = Modifier.fillMaxSize(),
                    contentColor = YChartsTheme.colors.background,
                    topBar = {
                        AppBarWithBackButton(
                            stringResource(id = R.string.title_bar_chart),
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
                        val barChartListSize = 7
                        val maxRange = 100
                        val yStepSize = 10
                        val barData = DataUtils.getBarChartData(barChartListSize, maxRange, BarChartType.VERTICAL, DataCategoryOptions(true, true))
                        val xAxisData = AxisData.Builder()
                            .axisStepSize(30.dp)
                            .startDrawPadding(30.dp)
                            .steps(barData.size - 1)
                            .bottomPadding(40.dp)
                            .axisLabelAngle(20f)
                            .labelData { index -> barData[index].label }
                            .build()

                        val yAxisData = AxisData.Builder()
                            .steps(yStepSize)
                            .labelAndAxisLinePadding(20.dp)
                            .axisOffset(20.dp)
                            .labelData { index -> (index * (maxRange / yStepSize)).toString() }
                            .build()
                        val barChartData = BarChartData(
                            chartData = barData,
                            xAxisData = xAxisData,
                            yAxisData = yAxisData,
                        )

                        val barData2 = DataUtils.getBarChartData(barChartListSize, maxRange, BarChartType.HORIZONTAL, DataCategoryOptions(false, true))
                        val barChartData2 = BarChartData(
                            chartData = barData2,
                            xAxisData = yAxisData,
                            yAxisData = xAxisData,
                            barStyle = BarStyle(isGradientEnabled = true),
                            barChartType = BarChartType.HORIZONTAL
                        )

                        Column()
                        {
                            BarChart(modifier = Modifier.height(350.dp), barChartData = barChartData)
                            BarChart(modifier = Modifier.height(350.dp), barChartData = barChartData2)
                        }

                    }
                }
            }
        }
    }
}


