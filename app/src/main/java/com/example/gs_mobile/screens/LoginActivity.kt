package com.example.gs_mobile

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

class LoginActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LoginScreen(navController = rememberNavController())
        }
    }
}

@Composable
fun LoginScreen(navController: NavController) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text("Tela de Login")
        Button(onClick = { navController.navigate("insert") }) {
            Text("Ir para inserção de dados")
        }
    }
}
