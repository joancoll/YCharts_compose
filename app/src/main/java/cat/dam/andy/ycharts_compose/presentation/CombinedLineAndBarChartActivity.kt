
package cat.dam.andy.ycharts_compose.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import cat.dam.andy.ycharts_compose.R
import cat.dam.andy.ycharts_compose.ui.compositions.AppBarWithBackButton
import cat.dam.andy.ycharts_compose.ui.theme.YChartsTheme
import co.yml.charts.axis.AxisData
import co.yml.charts.common.utils.DataUtils
import co.yml.charts.ui.barchart.models.BarPlotData
import co.yml.charts.ui.barchart.models.BarStyle
import co.yml.charts.ui.combinedchart.CombinedChart
import co.yml.charts.ui.combinedchart.model.CombinedChartData
import co.yml.charts.ui.linechart.model.IntersectionPoint
import co.yml.charts.ui.linechart.model.Line
import co.yml.charts.ui.linechart.model.LinePlotData
import co.yml.charts.ui.linechart.model.LineStyle
import co.yml.charts.ui.linechart.model.SelectionHighlightPoint
import co.yml.charts.ui.linechart.model.SelectionHighlightPopUp

class CombinedLineAndBarChartActivity : ComponentActivity() {

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            YChartsTheme {
                Scaffold(modifier = Modifier.fillMaxSize(),
                    contentColor = YChartsTheme.colors.background,
                    topBar = {
                        AppBarWithBackButton(
                            stringResource(id = R.string.title_bar_with_line_chart),
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
                        val listSize = 10
                        val startRange = 0
                        val maxRange = 100
                        val yStepSize = 10
                        val lineChartData = DataUtils.getLineChartData(listSize, maxRange = 100)
                        val linePlotData = LinePlotData(
                            lines = listOf(
                                Line(lineChartData,
                                    lineStyle = LineStyle(color = Color.Blue),
                                    intersectionPoint = IntersectionPoint(),
                                    selectionHighlightPoint = SelectionHighlightPoint(),
                                    selectionHighlightPopUp = SelectionHighlightPopUp()
                                ),
                                Line(
                                    DataUtils.getLineChartData(listSize, startRange, maxRange),
                                    lineStyle = LineStyle(color = Color.Black),
                                    intersectionPoint = IntersectionPoint(),
                                    selectionHighlightPoint = SelectionHighlightPoint(),
                                    selectionHighlightPopUp = SelectionHighlightPopUp()
                                )
                            )
                        )

                        val maxValueRange = 100
                        val barSize = 10
                        val colorPaletteList = listOf(
                            Color.Blue,
                            Color.Red,
                            Color.Green,
                            Color.Yellow,
                            Color.Cyan,
                            Color.Magenta,
                            Color.Gray,
                            Color.LightGray,
                            Color.DarkGray,
                            Color.Black
                        )
                        val barChartData = DataUtils.getLineChartData(listSize, maxRange = 100)
                        val barPlotData = BarPlotData(
                            groupBarList = DataUtils.getGroupBarChartData(listSize, maxValueRange, barSize),
                            barStyle = BarStyle(barWidth = 35.dp),
                            barColorPaletteList = colorPaletteList
                        )

                        val xAxisData = AxisData.Builder()
                            .axisStepSize(30.dp)
                            .steps(maxOf(barChartData.size - 1, lineChartData.size - 1))
                            .bottomPadding(40.dp)
                            .labelData { index -> index.toString() }
                            .build()

                        val yAxisData = AxisData.Builder()
                            .steps(yStepSize)
                            .labelAndAxisLinePadding(20.dp)
                            .axisOffset(20.dp)
                            .labelData { index -> (index * (maxRange / yStepSize)).toString() }
                            .build()

                        val combinedChartData = CombinedChartData(
                            combinedPlotDataList = listOf(barPlotData, linePlotData),
                            xAxisData = xAxisData,
                            yAxisData = yAxisData
                        )
                        CombinedChart(
                            modifier = Modifier.height(400.dp),
                            combinedChartData
                        )



                    }
                }
            }
        }
    }
}

