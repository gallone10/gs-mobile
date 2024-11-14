package com.example.gs_mobile

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

class InsertDataActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            InsertDataScreen(navController = rememberNavController())  // Passando o NavController
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InsertDataScreen(navController: NavController) {
    // Variáveis para armazenar os dados
    val milhasPorHora = remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)  // Fundo preto
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Caixa de texto para milhas por hora
        TextField(
            value = milhasPorHora.value,
            onValueChange = { milhasPorHora.value = it },
            label = { Text("Milhas por Hora", color = Color.Gray) },
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color(0xFFF5F5F5),  // Cor Off White
                focusedTextColor = Color.Black,
                unfocusedTextColor = Color.Black,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 16.dp)  // Espaço entre as caixas de texto
        )

        // Botão para salvar e listar
        Button(
            onClick = {
                // Aqui você pode salvar o dado no Firestore ou em qualquer outro lugar
                // Após salvar, navega para a tela "list"
                navController.navigate("list")  // Navega para a tela de listagem
            },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF003366)), // Azul escuro
            modifier = Modifier.fillMaxSize()
        ) {
            Text(text = "Salvar e Listar", color = Color.White)
        }
    }
}
