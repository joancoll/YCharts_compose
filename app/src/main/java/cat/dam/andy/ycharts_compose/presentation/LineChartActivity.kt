package cat.dam.andy.ycharts_compose.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import cat.dam.andy.ycharts_compose.R
import cat.dam.andy.ycharts_compose.ui.compositions.AppBarWithBackButton
import cat.dam.andy.ycharts_compose.ui.theme.YChartsTheme
import co.yml.charts.axis.AxisData
import co.yml.charts.common.extensions.formatToSinglePrecision
import co.yml.charts.common.model.Point
import co.yml.charts.ui.linechart.LineChart
import co.yml.charts.ui.linechart.model.GridLines
import co.yml.charts.ui.linechart.model.IntersectionPoint
import co.yml.charts.ui.linechart.model.Line
import co.yml.charts.ui.linechart.model.LineChartData
import co.yml.charts.ui.linechart.model.LinePlotData
import co.yml.charts.ui.linechart.model.LineStyle
import co.yml.charts.ui.linechart.model.LineType
import co.yml.charts.ui.linechart.model.SelectionHighlightPoint
import co.yml.charts.ui.linechart.model.SelectionHighlightPopUp
import co.yml.charts.ui.linechart.model.ShadowUnderLine

class LineChartActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            YChartsTheme {
                Scaffold(modifier = Modifier.fillMaxSize(),
                    contentColor = YChartsTheme.colors.background,
                    topBar = {
                        AppBarWithBackButton(
                            stringResource(id = R.string.title_line_chart),
                            onBackPressed = {
                                onBackPressed()
                            })
                    })
                {
                    Box(modifier = Modifier.padding(it)) {
                        val pointsData: List<Point> =
                            listOf(
                                Point(0f, 40f),
                                Point(1f, 90f),
                                Point(2f, 0f),
                                Point(3f, 60f),
                                Point(4f, 10f)
                            )
                        val steps = pointsData.size
                        val xAxisData = AxisData.Builder()
                            .axisStepSize(100.dp)
                            .backgroundColor(Color.Blue)
                            .steps(pointsData.size - 1)
                            .labelData { i -> i.toString() }
                            .labelAndAxisLinePadding(15.dp)
                            .build()

                        val yAxisData = AxisData.Builder()
                            .steps(steps)
                            .backgroundColor(Color.Red)
                            .labelAndAxisLinePadding(20.dp)
                            .labelData { i ->
                                val yScale = 100f / steps
                                (i * yScale).formatToSinglePrecision()
                            }.build()

                        fun Float.formatToSinglePrecision(): String {
                            return "%.1f".format(this)
                        }

                        val lineChartData = LineChartData(
                            linePlotData = LinePlotData(
                                lines = listOf(
                                    Line(
                                        dataPoints = pointsData,
                                        LineStyle(),
                                        IntersectionPoint(),
                                        SelectionHighlightPoint(),
                                        ShadowUnderLine(),
                                        SelectionHighlightPopUp()
                                    )
                                ),
                            ),
                            xAxisData = xAxisData,
                            yAxisData = yAxisData,
                            gridLines = GridLines(),
                            backgroundColor = Color.White
                        )
                        val lineChartData2 = LineChartData(
                            linePlotData = LinePlotData(
                                lines = listOf(
                                    Line(
                                        dataPoints = pointsData,
                                        lineStyle = LineStyle(
                                            lineType = LineType.Straight(isDotted = true),
                                            color = Color.DarkGray,
                                        ),
                                        IntersectionPoint(),
                                        SelectionHighlightPoint(),
                                        ShadowUnderLine(
                                            brush = Brush.verticalGradient(
                                                colors = listOf(
                                                    Color.Green,  // part superior)
                                                    Color.Red, // part inferior)
                                                ),
                                            ),
                                            alpha = 1f
                                        ),
                                        SelectionHighlightPopUp()
                                    )
                                ),
                            ),
                            xAxisData = xAxisData,
                            yAxisData = yAxisData,
                            backgroundColor = Color.Cyan
                        )
                        Column() {
                            LineChart(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(300.dp),
                                lineChartData = lineChartData
                            )
                            LineChart(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(300.dp),
                                lineChartData = lineChartData2
                            )
                        }
                    }
                }
            }
        }
    }
}
