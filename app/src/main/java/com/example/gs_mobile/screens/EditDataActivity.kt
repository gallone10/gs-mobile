package com.example.gs_mobile

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

class EditDataActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val itemId = intent.getStringExtra("itemId")
            EditDataScreen(navController = rememberNavController(), itemId = itemId)
        }
    }
}

@Composable
fun EditDataScreen(navController: NavController, itemId: String?) {
    val textState = remember { mutableStateOf("") }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        TextField(
            value = textState.value,
            onValueChange = { textState.value = it },
            label = { Text("Editar dado") }
        )
        Button(onClick = {
            // Atualize o Firestore com o novo dado
            navController.navigate("list")
        }) {
            Text("Salvar")
        }
    }
}
