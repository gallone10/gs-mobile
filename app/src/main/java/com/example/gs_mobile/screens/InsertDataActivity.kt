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
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
    // State variables to hold the input values
    var tipoPlaca by remember { mutableStateOf("") }
    var capacidadeGeracao by remember { mutableStateOf("") }
    var numInstalacoes by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Logo da empresa
        Image(
            painter = painterResource(id = R.drawable.logo), // Substitua pela sua logo
            contentDescription = "Logo da Empresa",
            modifier = Modifier
                .padding(bottom = 32.dp)
                .size(200.dp)
        )

        // Título da tela
        Text(
            text = "Cadastro de Placas Solares",
            color = Color.White,
            style = TextStyle(fontSize = 24.sp),
            modifier = Modifier.padding(bottom = 32.dp)
        )

        // Campo para inserir o tipo de placa solar
        TextField(
            value = tipoPlaca,
            onValueChange = { tipoPlaca = it },
            label = { Text("Tipo da Placa Solar") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color(0xFF333333), // Cor de fundo do campo
                focusedIndicatorColor = Color(0xFF00A8B5), // Cor do indicador quando o campo está em foco
                unfocusedIndicatorColor = Color.Gray // Cor do indicador quando o campo não está em foco
            )
        )

        // Campo para inserir a capacidade de geração de energia
        TextField(
            value = capacidadeGeracao,
            onValueChange = { capacidadeGeracao = it },
            label = { Text("Capacidade de Geração (kW)") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color(0xFF333333),
                focusedIndicatorColor = Color(0xFF00A8B5),
                unfocusedIndicatorColor = Color.Gray
            )
        )

        // Campo para inserir o número de instalações
        TextField(
            value = numInstalacoes,
            onValueChange = { numInstalacoes = it },
            label = { Text("Número de Instalações") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color(0xFF333333),
                focusedIndicatorColor = Color(0xFF00A8B5),
                unfocusedIndicatorColor = Color.Gray
            )
        )

        // Botão para salvar os dados inseridos
        Button(
            onClick = {
                // Aqui você pode adicionar a lógica para salvar os dados inseridos
                // Exemplo: salvarDados(tipoPlaca, capacidadeGeracao, numInstalacoes)
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
