package com.example.gs_mobile



import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gs_mobile.model.PlacaSolar


class ListDataActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val placas = loadPlacasFromPreferences()
        setContent {
            ListDataScreen(placas) { selectedPlaca ->
                navigateToEditDataActivity(selectedPlaca)
            }
        }
    }

    private fun loadPlacasFromPreferences(): List<PlacaSolar> {
        val sharedPreferences = getSharedPreferences("PlacasData", Context.MODE_PRIVATE)
        val dataString = sharedPreferences.getString("placasList", "") ?: ""
        return if (dataString.isEmpty()) emptyList() else dataString.split(";").filter {
            it.isNotBlank()
        }.map {
            val fields = it.split("|")
            PlacaSolar(fields[0], fields[1], fields[2], fields[3])
        }
    }



    private fun navigateToEditDataActivity(selectedPlaca: PlacaSolar) {
        val intent = Intent(this, EditDataActivity::class.java).apply {
            putExtra("cpfCnpj", selectedPlaca.cpfCnpj)
            putExtra("tipoPlaca", selectedPlaca.tipoPlaca)
            putExtra("capacidadeGeracao", selectedPlaca.capacidadeGeracao)
            putExtra("numInstalacoes", selectedPlaca.numInstalacoes)
        }
        startActivity(intent)
    }
}

@Composable
fun ListDataScreen(dataList: List<PlacaSolar>, onItemClick: (PlacaSolar) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(16.dp)
    ) {
        // Espaço para a logo
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

        LazyColumn {
            items(dataList) { item ->
                ListItem(item = item, onItemClick = onItemClick)
            }
        }
    }
}

@Composable
fun ListItem(item: PlacaSolar, onItemClick: (PlacaSolar) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFF1E1E1E))
            .padding(16.dp)
            .clickable { onItemClick(item) } // Manipulador de clique
    ) {
        Text(text = "CPF/CNPJ: ${item.cpfCnpj}", style = TextStyle(fontSize = 16.sp, color = Color.White))
        Text(text = "Tipo: ${item.tipoPlaca}", style = TextStyle(fontSize = 16.sp, color = Color.White))
        Text(text = "Capacidade: ${item.capacidadeGeracao} kW", style = TextStyle(fontSize = 16.sp, color = Color.White))
        Text(text = "Instalações: ${item.numInstalacoes}", style = TextStyle(fontSize = 16.sp, color = Color.White))
    }
}