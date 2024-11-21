package com.example.gs_mobile

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class InsertDataActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            InsertDataScreen()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InsertDataScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo), // Logo
            contentDescription = "Logo",
            modifier = Modifier
                .padding(bottom = 32.dp)
                .size(300.dp)
        )

        // Texto de exemplo, você pode adicionar formulários ou campos de inserção de dados
        Text(
            text = "Inserir Dados",
            color = Color.White,
            style = TextStyle(fontSize = 24.sp),
            modifier = Modifier.padding(bottom = 32.dp)
        )

        // Botão de exemplo para navegação ou outra ação
        Button(
            onClick = {
                // Aqui você pode colocar uma lógica para inserir os dados ou outra ação
            },
            modifier = Modifier
                .padding(horizontal = 32.dp, vertical = 8.dp)
                .height(45.dp)
                .fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF00A8B5))
        ) {
            Text(text = "Salvar Dados", color = Color.White, style = TextStyle(fontSize = 16.sp))
        }
    }
}
