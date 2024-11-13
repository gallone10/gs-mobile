package com.example.gs_mobile

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.firebase.auth.FirebaseAuth

class RegisterActivity : ComponentActivity() {
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RegisterScreen()
        }
    }

    @Composable
    fun RegisterScreen() {
        var email by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }
        var confirmPassword by remember { mutableStateOf("") }
        var isRegistering by remember { mutableStateOf(false) }

        fun registerUser() {
            if (email.isNotEmpty() && password.isNotEmpty() && password == confirmPassword) {
                isRegistering = true
                auth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this@RegisterActivity) { task ->
                        if (task.isSuccessful) {
                            Toast.makeText(this@RegisterActivity, "Cadastro bem-sucedido!", Toast.LENGTH_SHORT).show()
                        } else {
                            Toast.makeText(this@RegisterActivity, "Falha no cadastro: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                        }
                        isRegistering = false
                    }
            } else {
                Toast.makeText(this@RegisterActivity, "Preencha todos os campos corretamente", Toast.LENGTH_SHORT).show()
            }
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Título
            Text(text = "Cadastrar", fontSize = 24.sp, color = Color.White)

            Spacer(modifier = Modifier.height(32.dp))

            // Campo de E-mail
            BasicTextField(
                value = email,
                onValueChange = { email = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .background(Color.Gray),
                singleLine = true,
                textStyle = LocalTextStyle.current.copy(color = Color.White),
                decorationBox = { innerTextField ->
                    Row(modifier = Modifier.padding(8.dp)) {
                        innerTextField()
                    }
                }
            )
            Spacer(modifier = Modifier.height(16.dp))

            // Campo de Senha
            BasicTextField(
                value = password,
                onValueChange = { password = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .background(Color.Gray),
                visualTransformation = PasswordVisualTransformation(),
                singleLine = true,
                textStyle = LocalTextStyle.current.copy(color = Color.White),
                decorationBox = { innerTextField ->
                    Row(modifier = Modifier.padding(8.dp)) {
                        innerTextField()
                    }
                }
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Confirmar Senha
            BasicTextField(
                value = confirmPassword,
                onValueChange = { confirmPassword = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .background(Color.Gray),
                visualTransformation = PasswordVisualTransformation(),
                singleLine = true,
                textStyle = LocalTextStyle.current.copy(color = Color.White),
                decorationBox = { innerTextField ->
                    Row(modifier = Modifier.padding(8.dp)) {
                        innerTextField()
                    }
                }
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Botão de Cadastro
            Button(
                onClick = { registerUser() },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp),
                enabled = !isRegistering
            ) {
                Text(text = if (isRegistering) "Cadastrando..." else "Cadastrar", color = Color.White)
            }
        }
    }
}
