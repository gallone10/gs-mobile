package com.example.gs_mobile

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreen()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    val navController = rememberNavController()

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Menu Principal") })
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black)
                .padding(paddingValues),  // Aplicando padding no layout
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Logo com tamanho ajustado
            Image(
                painter = painterResource(id = R.drawable.logo),  // Coloque o nome correto da sua imagem aqui
                contentDescription = "Logo",
                modifier = Modifier
                    .padding(bottom = 32.dp)
                    .size(200.dp)  // Aumentando o tamanho da logo para 200dp x 200dp
            )

            // Botão de Login com cor e tamanho ajustados
            Button(
                onClick = { navController.navigate("login") },
                modifier = Modifier
                    .padding(horizontal = 32.dp, vertical = 8.dp)
                    .height(45.dp)  // Definindo altura menor para o botão
                    .fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF003366))  // Azul escuro
            ) {
                Text(text = "Login", color = Color.White, style = TextStyle(fontSize = 16.sp))
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Botão de Cadastro com cor e tamanho ajustados
            Button(
                onClick = { navController.navigate("register") },
                modifier = Modifier
                    .padding(horizontal = 32.dp, vertical = 8.dp)
                    .height(45.dp)  // Definindo altura menor para o botão
                    .fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF003366))  // Azul escuro
            ) {
                Text(text = "Cadastro", color = Color.White, style = TextStyle(fontSize = 16.sp))
            }
        }

        // Configuração de navegação
        NavHost(navController = navController, startDestination = "home") {
            composable("home") { MainScreen() }
            composable("login") { LoginScreen() }
            composable("cadastro") { RegisterScreen() }

        }
    }
}

@Composable
fun LoginScreen() {
    // Coloque aqui o código para a tela de Login
    Text(text = "Tela de Login", color = Color.White)
}

@Composable
fun RegisterScreen() {
    // Coloque aqui o código para a tela de Cadastro
    Text(text = "Tela de Cadastro", color = Color.White)
}
