package com.imolerodev.igvcalculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.imolerodev.igvcalculator.ui.theme.IGVCalculatorTheme
import java.text.NumberFormat

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            IGVCalculatorTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    IGVCalculatorLayout()
                }
            }
        }
    }
}

@Composable
fun IGVCalculatorLayout(modifier: Modifier = Modifier) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
        modifier = modifier
    ) {
        HeaderApp()
        BodyApp()
    }
}

@Composable
fun BodyApp(modifier: Modifier = Modifier) {
    var totalVenta by remember { mutableStateOf("") }

    val totalVentaDouble = totalVenta.toDoubleOrNull() ?: 0.0
    val igv = NumberFormat.getCurrencyInstance().format(totalVentaDouble * 0.18)
    val valorVenta: String = NumberFormat.getCurrencyInstance().format(totalVentaDouble * 0.82)
    Column(
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Center,
        modifier = modifier
    ) {
        DisplayItem(
            tag = stringResource(id = R.string.igv_item),
            value = igv
        )
        DisplayItem(
            tag = stringResource(id = R.string.valor_venta_item),
            value = valorVenta
        )
        EditNumberField(
            value = totalVenta,
            onValueChange = { totalVenta = it })
    }
}

@Composable
fun EditNumberField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        label = {
            Text(
                text = stringResource(id = R.string.total_factura)
            )
        },
        textStyle = MaterialTheme.typography.headlineMedium,
        singleLine = true,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number
        ),
        modifier = modifier
            .fillMaxWidth(),
    )
}

@Composable
fun DisplayItem(tag: String, value: String, modifier: Modifier = Modifier) {
    Row(
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
            .padding(16.dp)
            .fillMaxWidth()
    ) {
        Text(
            text = tag,
            style = MaterialTheme.typography.headlineSmall
        )
        Text(
            text = value,
            style = MaterialTheme.typography.headlineSmall
        )
    }
}

@Composable
fun HeaderApp(modifier: Modifier = Modifier) {
    Text(
        text = stringResource(id = R.string.title_app),
        style = MaterialTheme.typography.displaySmall,
        textAlign = TextAlign.Center,
        modifier = modifier
            .fillMaxWidth()
            .background(color = MaterialTheme.colorScheme.inversePrimary)
            .padding(16.dp)
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun IGVCalculatorLayoutPreview() {
    IGVCalculatorTheme {
        IGVCalculatorLayout()
    }
}