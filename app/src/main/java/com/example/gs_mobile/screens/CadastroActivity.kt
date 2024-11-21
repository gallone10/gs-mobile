package com.example.gs_mobile

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.util.Patterns
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
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.firebase.auth.FirebaseAuth

class CadastroActivity : ComponentActivity() {

    private val autenticacao by lazy {
        FirebaseAuth.getInstance()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CadastroScreen { email, password ->
                cadastrarUsuario(email, password)
            }
        }
    }

    private fun cadastrarUsuario(email: String, senha: String) {
        if (!isValidEmail(email)) {
            AlertDialog.Builder(this)
                .setTitle("Erro")
                .setMessage("Por favor, insira um email válido.")
                .setNegativeButton("OK") { dialog, posicao -> }
                .create()
                .show()
            return
        }

        autenticacao.createUserWithEmailAndPassword(email, senha)
            .addOnSuccessListener { authResult ->
                val id = authResult.user?.uid
                val email = authResult.user?.email

                AlertDialog.Builder(this)
                    .setTitle("Usuário Criado")
                    .setMessage("Usuário criado com sucesso!")
                    .setPositiveButton("OK") { dialog, posicao ->
                        startActivity(Intent(this, MainActivity::class.java))
                    }
                    .create()
                    .show()
            }
            .addOnFailureListener { exception ->
                val mensagemErro = exception.message

                AlertDialog.Builder(this)
                    .setTitle("Erro")
                    .setMessage("Erro ao criar usuário: $mensagemErro")
                    .setNegativeButton("OK") { dialog, posicao -> }
                    .create()
                    .show()
            }
    }

    // Função para validar o formato do email
    private fun isValidEmail(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CadastroScreen(onCadastroClick: (String, String) -> Unit) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo), // Substitua pelo ID correto da sua logo
            contentDescription = "Logo",
            modifier = Modifier
                .size(300.dp)
                .padding(bottom = 16.dp)
        )

        Text("Cadastro", fontSize = 24.sp, color = Color.White)

        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email", color = Color.Gray) },
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color(0xFFF5F5F5),  // Cor Off White
                focusedTextColor = Color.Black,
                unfocusedTextColor = Color.Black,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Senha", color = Color.Gray) },
            visualTransformation = PasswordVisualTransformation(),
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color(0xFFF5F5F5),  // Cor Off White
                focusedTextColor = Color.Black,
                unfocusedTextColor = Color.Black,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = confirmPassword,
            onValueChange = { confirmPassword = it },
            label = { Text("Confirme a Senha", color = Color.Gray) },
            visualTransformation = PasswordVisualTransformation(),
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color(0xFFF5F5F5),  // Cor Off White
                focusedTextColor = Color.Black,
                unfocusedTextColor = Color.Black,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                if (password == confirmPassword) {
                    onCadastroClick(email, password)
                } else {
                    // Exibir mensagem de erro se as senhas não coincidirem
                }
            },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF00A8B5)), // Cor alterada
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
        ) {
            Text(text = "Cadastrar", color = Color.White)
        }
    }
}
