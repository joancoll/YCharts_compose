package cat.dam.andy.ycharts_compose

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import cat.dam.andy.ycharts_compose.presentation.*
import cat.dam.andy.ycharts_compose.ui.theme.YChartsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            YChartsTheme {
                Scaffold(modifier = Modifier.fillMaxSize(),
                    contentColor = YChartsTheme.colors.background,
                    topBar = { AppBar() })
                {
                    Column(
                        modifier = Modifier
                            .padding(it)
                            .fillMaxSize(),
                        verticalArrangement = Arrangement.Center
                    ) {

                        ChartButton(title = getString(R.string.title_line_chart), onClick = {
                            startActivity(
                                Intent(
                                    this@MainActivity,
                                    LineChartActivity::class.java
                                )
                            )
                            addActivityInOutAnim()
                        })

                        ChartButton(title = getString(R.string.title_bar_chart), onClick = {
                            startActivity(
                                Intent(
                                    this@MainActivity,
                                    BarChartActivity::class.java
                                )
                            )
                            addActivityInOutAnim()
                        })

                        ChartButton(title = getString(R.string.title_grouped_bar_chart), onClick = {
                            startActivity(
                                Intent(
                                    this@MainActivity,
                                    GroupedBarChartActivity::class.java
                                )
                            )
                            addActivityInOutAnim()
                        })

                        ChartButton(title = getString(R.string.title_pie_chart), onClick = {
                            startActivity(
                                Intent(
                                    this@MainActivity,
                                    PieChartActivity::class.java
                                )
                            )
                            addActivityInOutAnim()
                        })

                        ChartButton(title = getString(R.string.title_donut_chart), onClick = {
                            startActivity(
                                Intent(
                                    this@MainActivity,
                                    DonutChartActivity::class.java
                                )
                            )
                            addActivityInOutAnim()
                        })

                        ChartButton(
                            title = getString(R.string.title_bar_with_line_chart),
                            onClick = {
                                startActivity(
                                    Intent(
                                        this@MainActivity,
                                        CombinedLineAndBarChartActivity::class.java
                                    )
                                )
                                addActivityInOutAnim()
                            })

                        ChartButton(title = getString(R.string.title_wave_chart), onClick = {
                            startActivity(
                                Intent(
                                    this@MainActivity,
                                    WaveChartActivity::class.java
                                )
                            )
                            addActivityInOutAnim()
                        })

                        ChartButton(
                            title = getString(R.string.title_bubble_chart),
                            onClick = {
                                startActivity(
                                    Intent(
                                        this@MainActivity,
                                        BubbleChartActivity::class.java
                                    )
                                )
                                addActivityInOutAnim()
                            })



                    }
                }
            }
        }
    }


    private fun addActivityInOutAnim() {
        overridePendingTransition(
            R.anim.move_right_in_activity,
            R.anim.move_left_out_activity
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun AppBar() {
    val customTopAppBarColors = TopAppBarDefaults.topAppBarColors(
        containerColor = YChartsTheme.colors.button // Estableix el color de fons aquí
    )
    TopAppBar(
        modifier = Modifier
            .fillMaxWidth(),
        colors = customTopAppBarColors,
        title = {
            Text(
                text = stringResource(R.string.app_name),
                color = YChartsTheme.colors.text,
                textAlign = TextAlign.Center,
                style = YChartsTheme.typography.header
            )
        }
    )
}

@Composable
private fun ChartButton(title: String, onClick: () -> Unit) {
    Column {
        Spacer(modifier = Modifier.height(20.dp))
        Button(
            modifier = Modifier
                .padding(end = 10.dp, start = 10.dp)
                .fillMaxWidth()
                .height(50.dp), onClick = onClick,
            colors = ButtonDefaults.buttonColors(contentColor = YChartsTheme.colors.button)
        ) {
            Text(
                text = title,
                style = YChartsTheme.typography.button,
                color = YChartsTheme.colors.text
            )
        }
    }
}

/*
Copyright 2022 YCharts

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.*/
