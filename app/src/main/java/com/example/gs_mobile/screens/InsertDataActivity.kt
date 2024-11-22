package com.example.gs_mobile

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class PlacaSolarLocal(
    val cpfCnpj: String,
    val tipoPlaca: String,
    val capacidadeGeracao: String,
    val numInstalacoes: String
)

class InsertDataActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            InsertDataScreen(onNavigateToList = {
                startActivity(Intent(this, ListDataActivity::class.java))
                finish() // Opcional: encerra a tela atual
            })
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InsertDataScreen(onNavigateToList: () -> Unit) {
    var cpfCnpj by remember { mutableStateOf("") }
    var tipoPlaca by remember { mutableStateOf("") }
    var capacidadeGeracao by remember { mutableStateOf("") }
    var numInstalacoes by remember { mutableStateOf("") }

    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo), // Substitua pela sua logo
            contentDescription = "Logo da Empresa",
            modifier = Modifier
                .padding(bottom = 32.dp)
                .size(200.dp)
        )

        Text(
            text = "Cadastro de Placas Solares",
            color = Color.White,
            style = TextStyle(fontSize = 24.sp),
            modifier = Modifier.padding(bottom = 32.dp)
        )

        TextField(
            value = cpfCnpj,
            onValueChange = { cpfCnpj = it },
            label = { Text("CPF ou CNPJ", color = Color.Black) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color.White,
                focusedIndicatorColor = Color(0xFF00A8B5),
                unfocusedIndicatorColor = Color.Gray
            )
        )

        TextField(
            value = tipoPlaca,
            onValueChange = { tipoPlaca = it },
            label = { Text("Tipo da Placa Solar", color = Color.Black) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color.White,
                focusedIndicatorColor = Color(0xFF00A8B5),
                unfocusedIndicatorColor = Color.Gray
            )
        )

        TextField(
            value = capacidadeGeracao,
            onValueChange = { capacidadeGeracao = it },
            label = { Text("Capacidade de Geração (kW)", color = Color.Black) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color.White,
                focusedIndicatorColor = Color(0xFF00A8B5),
                unfocusedIndicatorColor = Color.Gray
            )
        )

        TextField(
            value = numInstalacoes,
            onValueChange = { numInstalacoes = it },
            label = { Text("Número de Instalações", color = Color.Black) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color.White,
                focusedIndicatorColor = Color(0xFF00A8B5),
                unfocusedIndicatorColor = Color.Gray
            )
        )

        Button(
            onClick = {
                // Salvar localmente usando SharedPreferences
                val sharedPreferences = context.getSharedPreferences("PlacasData", Context.MODE_PRIVATE)
                val editor = sharedPreferences.edit()

                // Recuperar dados existentes
                val existingDataString = sharedPreferences.getString("placasList", "") ?: ""
                val existingData = if (existingDataString.isNotEmpty()) {
                    existingDataString.split(";").map { it.split("|") }.map {
                        PlacaSolarLocal(it[0], it[1], it[2], it[3])
                    }.toMutableList()
                } else {
                    mutableListOf()
                }

                // Adicionar novo registro
                val newPlaca = PlacaSolarLocal(cpfCnpj, tipoPlaca, capacidadeGeracao, numInstalacoes)
                existingData.add(newPlaca)

                // Converter os dados atualizados de volta para string
                val updatedDataString = existingData.joinToString(";") {
                    "${it.cpfCnpj}|${it.tipoPlaca}|${it.capacidadeGeracao}|${it.numInstalacoes}"
                }

                // Salvar a lista atualizada
                editor.putString("placasList", updatedDataString)
                editor.apply()

                // Navegar para a lista
                onNavigateToList()
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
