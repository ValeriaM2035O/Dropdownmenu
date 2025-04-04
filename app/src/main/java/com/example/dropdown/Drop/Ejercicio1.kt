package com.example.dropdown.Drop

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily




@Composable
fun Menu() {
    val colores = listOf("Amarillo", "Azul", "Morado", "Rosa", "Rojo")
    val tipografias = listOf("Arial", "Times New Roman", "Calibri", "Algerian")

    var seleccionarColor by remember { mutableStateOf(colores[0]) }
    var seleccionarTipografia by remember { mutableStateOf(tipografias[0]) }
    var textFieldValue by remember { mutableStateOf("") }

    var expandedColor by remember { mutableStateOf(false) }
    var expandedTipografia by remember { mutableStateOf(false) }

    var textoAplicado by remember { mutableStateOf("") }
    var colorAplicado by remember { mutableStateOf(Color.Black) }
    var tipografiaAplicada by remember { mutableStateOf<FontFamily>(FontFamily.Default) }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Opciones")

        // color
        OutlinedButton(onClick = { expandedColor = true }) {
            Text(text = seleccionarColor)
        }
        DropdownMenu(expanded = expandedColor, onDismissRequest = { expandedColor = false }) {
            colores.forEach { color ->
                DropdownMenuItem(text = { Text(color) }, onClick = {
                    seleccionarColor = color
                    expandedColor = false
                })
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // tipografía
        OutlinedButton(onClick = { expandedTipografia = true }) {
            Text(text = seleccionarTipografia)
        }
        DropdownMenu(expanded = expandedTipografia, onDismissRequest = { expandedTipografia = false }) {
            tipografias.forEach { tipografia ->
                DropdownMenuItem(text = { Text(tipografia) }, onClick = {
                    seleccionarTipografia = tipografia
                    expandedTipografia = false
                })
            }
        }

        Spacer(modifier = Modifier.height(16.dp))


        OutlinedTextField(
            value = textFieldValue,
            onValueChange = { textFieldValue = it },
            label = { Text("Ingrese texto") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))


        Button(onClick = {
            textoAplicado = textFieldValue
            colorAplicado = obtenerColor(seleccionarColor)
            tipografiaAplicada = obtenerTipografia(seleccionarTipografia)
        }) {
            Text(text = "Aplicar")
        }

        Spacer(modifier = Modifier.height(16.dp))


        Text(
            text = textoAplicado,
            color = colorAplicado,
            fontSize = 24.sp,
            fontFamily = tipografiaAplicada
        )
    }
}


fun obtenerColor(color: String): Color {
    return when (color) {
        "Amarillo" -> Color.Yellow
        "Azul" -> Color.Blue
        "Morado" -> Color.Magenta
        "Rosa" -> Color(0xFF673AB7)
        "Rojo" -> Color.Red
        else -> Color.Black
    }
}


fun obtenerTipografia(tipografia: String): FontFamily {
    return when (tipografia) {
        "Arial" -> FontFamily.SansSerif
        "Times New Roman" -> FontFamily.Serif
        "Calibri" -> FontFamily.Default
        "Algerian" -> FontFamily.Cursive
        else -> FontFamily.Default
    }
}