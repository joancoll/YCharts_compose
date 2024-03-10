package cat.dam.andy.ycharts_compose.ui.compositions

import androidx.compose.foundation.layout.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import cat.dam.andy.ycharts_compose.R
import cat.dam.andy.ycharts_compose.ui.compositions.AppBarWithBackButton
import cat.dam.andy.ycharts_compose.ui.theme.YChartsTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBarWithBackButton(title: String, onBackPressed: () -> Unit) {
    val customTopAppBarColors = TopAppBarDefaults.topAppBarColors(
        containerColor = YChartsTheme.colors.button // Estableix el color de fons aquí
    )
    TopAppBar(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp),
        colors = customTopAppBarColors,
        title = {})
    Box(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Spacer(modifier = Modifier.width(5.dp))
            IconButton(
                onClick = { onBackPressed() },
                modifier = Modifier
                    .size(32.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_back_arrow),
                    contentDescription = "Back",
                    tint = Color.Unspecified
                )
            }
        }
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = title,
            color = YChartsTheme.colors.text,
            textAlign = TextAlign.Center,
            style = YChartsTheme.typography.header
        )
    }
}
