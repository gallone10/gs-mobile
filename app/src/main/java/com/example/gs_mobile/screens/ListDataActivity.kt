package com.example.gs_mobile

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.google.firebase.firestore.FirebaseFirestore

class ListDataActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ListDataScreen(navController = rememberNavController())
        }
    }
}

@Composable
fun ListDataScreen(navController: NavController) {
    val items = remember { mutableStateListOf<String>() }

    LaunchedEffect(Unit) {
        FirebaseFirestore.getInstance().collection("dados").get()
            .addOnSuccessListener { result ->
                items.clear()
                for (document in result) {
                    items.add(document.getString("data") ?: "")
                }
            }
    }

    Column(modifier = Modifier.padding(16.dp)) {
        items.forEach { item ->
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Text(text = item)
                Button(onClick = { navController.navigate("edit/${item}") }) {
                    Text("Editar")
                }
            }
        }
    }
}
