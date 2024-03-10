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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import cat.dam.andy.ycharts_compose.R
import cat.dam.andy.ycharts_compose.ui.compositions.AppBarWithBackButton
import cat.dam.andy.ycharts_compose.ui.theme.YChartsTheme
import co.yml.charts.axis.AxisData
import co.yml.charts.common.utils.DataUtils
import co.yml.charts.common.utils.DataUtils.getColorPaletteList
import co.yml.charts.ui.barchart.GroupBarChart
import co.yml.charts.ui.barchart.StackedBarChart
import co.yml.charts.ui.barchart.models.BarPlotData
import co.yml.charts.ui.barchart.models.GroupBarChartData

class GroupedBarChartActivity : ComponentActivity() {

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            YChartsTheme {
                Scaffold(modifier = Modifier.fillMaxSize(),
                    contentColor = YChartsTheme.colors.background,
                    topBar = {
                        AppBarWithBackButton(
                            stringResource(id = R.string.title_grouped_bar_chart),
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
                        val barChartListSize = 10
                        val maxRange = 100
                        val yStepSize = 10
                        val eachGroupBarSize = 3
                        val groupBarData = DataUtils.getGroupBarChartData(barChartListSize, maxRange, eachGroupBarSize)
                        val plotData = BarPlotData( groupBarList = groupBarData,
                            barColorPaletteList = getColorPaletteList(eachGroupBarSize))

                        val xAxisData = AxisData.Builder()
                            .axisStepSize(30.dp)
                            .steps(groupBarData.size - 1)
                            .bottomPadding(40.dp)
                            .axisLabelAngle(20f)
                            .labelData { index -> groupBarData[index].label }
                            .build()

                        val yAxisData = AxisData.Builder()
                            .steps(yStepSize)
                            .labelAndAxisLinePadding(20.dp)
                            .axisOffset(20.dp)
                            .labelData { index -> (index * (maxRange / yStepSize)).toString() }
                            .build()

                        val groupBarChartData = GroupBarChartData(
                            barPlotData = plotData,
                            xAxisData = xAxisData,
                            yAxisData = yAxisData
                        )
                        Column() {
                            GroupBarChart(
                                modifier = Modifier.height(300.dp),
                                groupBarChartData = groupBarChartData
                            )
                            StackedBarChart(
                                modifier = Modifier.height(300.dp),
                                groupBarChartData = groupBarChartData
                            )
                        }
                    }
                }
            }
        }
    }
}


