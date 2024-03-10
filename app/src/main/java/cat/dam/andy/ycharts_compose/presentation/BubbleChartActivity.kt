package cat.dam.andy.ycharts_compose.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import cat.dam.andy.ycharts_compose.R
import cat.dam.andy.ycharts_compose.ui.compositions.AppBarWithBackButton
import cat.dam.andy.ycharts_compose.ui.theme.YChartsTheme
import co.yml.charts.axis.AxisData
import co.yml.charts.common.model.Point
import co.yml.charts.common.utils.DataUtils
import co.yml.charts.ui.bubblechart.BubbleChart
import co.yml.charts.ui.bubblechart.model.BubbleChartData
import co.yml.charts.ui.linechart.model.GridLines

class BubbleChartActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            YChartsTheme {
                Scaffold(modifier = Modifier.fillMaxSize(),
                    contentColor = YChartsTheme.colors.background,
                    topBar = {
                        AppBarWithBackButton(
                            stringResource(id = R.string.title_bubble_chart),
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
                        val barChartData = DataUtils.getLineChartData(listSize, maxRange = 100)
                        val pointsData: List<Point> =
                            listOf(Point(0f, 40f), Point(1f, 90f), Point(2f, 0f), Point(3f, 60f), Point(4f, 10f))

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

                        val bubbleData = BubbleChartData(
                            DataUtils.getBubbleChartDataWithGradientStyle(pointsData),
                            xAxisData = xAxisData,
                            yAxisData = yAxisData,
                            gridLines = GridLines()
                        )
                        BubbleChart(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(500.dp),
                            bubbleChartData = bubbleData
                        )
                    }
                }
            }
        }
    }
}
