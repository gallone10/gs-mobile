package com.example.gs_mobile

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class EditDataActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Recupera os dados do Intent
        val cpfCnpj = intent.getStringExtra("cpfCnpj") ?: ""
        val tipoPlaca = intent.getStringExtra("tipoPlaca") ?: ""
        val capacidadeGeracao = intent.getStringExtra("capacidadeGeracao") ?: ""
        val numInstalacoes = intent.getStringExtra("numInstalacoes") ?: ""

        setContent {
            EditDataScreen(
                cpfCnpj = cpfCnpj,
                tipoPlaca = tipoPlaca,
                capacidadeGeracao = capacidadeGeracao,
                numInstalacoes = numInstalacoes,
                context = this@EditDataActivity // Passando o contexto da Activity
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditDataScreen(
    cpfCnpj: String,
    tipoPlaca: String,
    capacidadeGeracao: String,
    numInstalacoes: String,
    context: Context // Agora recebendo o contexto como parâmetro
) {
    var updatedCpfCnpj by remember { mutableStateOf(cpfCnpj) }
    var updatedTipoPlaca by remember { mutableStateOf(tipoPlaca) }
    var updatedCapacidadeGeracao by remember { mutableStateOf(capacidadeGeracao) }
    var updatedNumInstalacoes by remember { mutableStateOf(numInstalacoes) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(16.dp)
    ) {
        // Logo no topo
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo), // Substitua por sua logo
                contentDescription = "Logo",
                modifier = Modifier.size(200.dp)
            )
        }

        Text(
            text = "Editar Dados",
            style = TextStyle(fontSize = 24.sp, color = Color.White),
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Campos de edição
        TextField(
            value = updatedCpfCnpj,
            onValueChange = { updatedCpfCnpj = it },
            label = { Text("CPF/CNPJ") },
            colors = TextFieldDefaults.textFieldColors(
                focusedIndicatorColor = Color(0xFF00A8B5),
                unfocusedIndicatorColor = Color.Gray
            ),
            modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
        )
        TextField(
            value = updatedTipoPlaca,
            onValueChange = { updatedTipoPlaca = it },
            label = { Text("Tipo de Placa") },
            colors = TextFieldDefaults.textFieldColors(
                focusedIndicatorColor = Color(0xFF00A8B5),
                unfocusedIndicatorColor = Color.Gray
            ),
            modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
        )
        TextField(
            value = updatedCapacidadeGeracao,
            onValueChange = { updatedCapacidadeGeracao = it },
            label = { Text("Capacidade de Geração") },
            colors = TextFieldDefaults.textFieldColors(
                focusedIndicatorColor = Color(0xFF00A8B5),
                unfocusedIndicatorColor = Color.Gray
            ),
            modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
        )
        TextField(
            value = updatedNumInstalacoes,
            onValueChange = { updatedNumInstalacoes = it },
            label = { Text("Número de Instalações") },
            colors = TextFieldDefaults.textFieldColors(
                focusedIndicatorColor = Color(0xFF00A8B5),
                unfocusedIndicatorColor = Color.Gray
            ),
            modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp)
        )

        // Botões para Editar e Excluir
        Button(
            onClick = {
                saveChanges(updatedCpfCnpj, updatedTipoPlaca, updatedCapacidadeGeracao, updatedNumInstalacoes, context)
                navigateBackToListActivity(context)
            },
            colors = androidx.compose.material3.ButtonDefaults.buttonColors(containerColor = Color(0xFF00A8B5)),
            modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
        ) {
            Text(text = "Salvar Alterações", color = Color.White)
        }

        Button(
            onClick = {
                deletePlaca(cpfCnpj, context)
                navigateBackToListActivity(context)
            },
            colors = androidx.compose.material3.ButtonDefaults.buttonColors(containerColor = Color.Red),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Excluir Dados", color = Color.White)
        }
    }
}

fun saveChanges(
    cpfCnpj: String,
    tipoPlaca: String,
    capacidadeGeracao: String,
    numInstalacoes: String,
    context: Context
) {
    // Recuperando as preferências compartilhadas
    val sharedPreferences = context.getSharedPreferences("PlacasData", Context.MODE_PRIVATE)
    val dataString = sharedPreferences.getString("placasList", "") ?: ""

    // Verificando se há dados para atualizar
    val placasList = if (dataString.isNotEmpty()) dataString.split(";").map { it.split("|") } else emptyList()

    // Atualizando a lista com os novos dados
    val updatedPlacasList = placasList.map {
        if (it[0] == cpfCnpj) {
            // Substitui os dados da placa com base no CPF/CNPJ
            listOf(cpfCnpj, tipoPlaca, capacidadeGeracao, numInstalacoes)
        } else {
            it
        }
    }

    // Convertendo a lista atualizada de volta para o formato de string
    val updatedDataString = updatedPlacasList.joinToString(";") { it.joinToString("|") }

    // Verificando se houve alguma alteração, se sim, atualizar SharedPreferences
    if (updatedDataString != dataString) {
        sharedPreferences.edit().putString("placasList", updatedDataString).apply()
        Toast.makeText(context, "Dados atualizados com sucesso", Toast.LENGTH_SHORT).show()
    } else {
        Toast.makeText(context, "Nenhuma alteração detectada", Toast.LENGTH_SHORT).show()
    }
}

fun deletePlaca(cpfCnpj: String, context: Context) {
    val sharedPreferences = context.getSharedPreferences("PlacasData", Context.MODE_PRIVATE)
    val dataString = sharedPreferences.getString("placasList", "") ?: ""
    val placasList = if (dataString.isNotEmpty()) dataString.split(";").map { it.split("|") } else emptyList()

    // Filtra a placa que deve ser excluída
    val updatedPlacasList = placasList.filter { it[0] != cpfCnpj }

    // Salva a lista sem a placa excluída
    val updatedDataString = updatedPlacasList.joinToString(";") { it.joinToString("|") }
    sharedPreferences.edit().putString("placasList", updatedDataString).apply()

    Toast.makeText(context, "Placa excluída com sucesso", Toast.LENGTH_SHORT).show()
}

fun navigateBackToListActivity(context: Context) {
    val intent = Intent(context, ListDataActivity::class.java)
    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP // Isso garante que a tela de listagem será recarregada
    context.startActivity(intent)
}
