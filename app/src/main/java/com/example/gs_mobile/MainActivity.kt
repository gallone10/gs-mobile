package com.example.gs_mobile

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.platform.LocalContext

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
    val context = LocalContext.current  // Contexto correto dentro da função Composable

    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black)
                .padding(paddingValues),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo), // Substitua com o recurso da sua logo
                contentDescription = "Logo",
                modifier = Modifier
                    .padding(bottom = 32.dp)
                    .size(300.dp)
            )

            // Botão de Login
            Button(
                onClick = {
                    // Navegar para LoginActivity
                    val intent = Intent(context, LoginActivity::class.java)
                    context.startActivity(intent)
                },
                modifier = Modifier
                    .padding(horizontal = 32.dp, vertical = 8.dp)
                    .height(45.dp)
                    .fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF00A8B5)) // Cor alterada
            ) {
                Text(text = "Login", color = Color.White, style = TextStyle(fontSize = 16.sp))
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Botão de Cadastro
            Button(
                onClick = {
                    // Navegar para CadastroActivity
                    val intent = Intent(context, CadastroActivity::class.java)
                    context.startActivity(intent)
                },
                modifier = Modifier
                    .padding(horizontal = 32.dp, vertical = 8.dp)
                    .height(45.dp)
                    .fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF00A8B5)) // Cor alterada
            ) {
                Text(text = "Cadastro", color = Color.White, style = TextStyle(fontSize = 16.sp))
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Botão de Inserir Dados
            Button(
                onClick = {
                    // Navegar para InsertDataActivity
                    val intent = Intent(context, InsertDataActivity::class.java)
                    context.startActivity(intent)
                },
                modifier = Modifier
                    .padding(horizontal = 32.dp, vertical = 8.dp)
                    .height(45.dp)
                    .fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF00A8B5)) // Cor alterada
            ) {
                Text(text = "Inserir Dados", color = Color.White, style = TextStyle(fontSize = 16.sp))
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Botão de Listagem
            Button(
                onClick = {
                    // Navegar para ListDataActivity
                    val intent = Intent(context, ListDataActivity::class.java)
                    context.startActivity(intent)
                },
                modifier = Modifier
                    .padding(horizontal = 32.dp, vertical = 8.dp)
                    .height(45.dp)
                    .fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF00A8B5)) // Cor alterada
            ) {
                Text(text = "Ver Listagem", color = Color.White, style = TextStyle(fontSize = 16.sp))
            }
        }
    }
}
