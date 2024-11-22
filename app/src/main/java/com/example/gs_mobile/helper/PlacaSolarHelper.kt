package com.example.gs_mobile.helper

import android.app.Activity
import android.content.Context
import com.example.gs_mobile.model.PlacaSolar

// Função para salvar as placas solares nas preferências
fun updatePlacasInPreferences(context: Context, updatedPlacas: List<PlacaSolar>) {
    val sharedPreferences = context.getSharedPreferences("PlacasData", Context.MODE_PRIVATE)
    val editor = sharedPreferences.edit()

    // Converter a lista de placas solares em uma string
    val placasString = updatedPlacas.joinToString(";") { placa ->
        "${placa.cpfCnpj}|${placa.tipoPlaca}|${placa.capacidadeGeracao}|${placa.numInstalacoes}"
    }

    // Salvar a string no SharedPreferences
    editor.putString("placasList", placasString)
    editor.apply()  // Commit a alteração
}

// Função para obter as placas solares do SharedPreferences
fun getPlacasFromPreferences(context: Context): List<PlacaSolar> {
    val sharedPreferences = context.getSharedPreferences("PlacasData", Context.MODE_PRIVATE)
    val dataString = sharedPreferences.getString("placasList", "") ?: ""

    // Se não houver dados, retorna uma lista vazia
    return if (dataString.isEmpty()) {
        emptyList()
    } else {
        // Caso haja dados, converte a string de volta para a lista de objetos PlacaSolar
        dataString.split(";")
            .filter { it.isNotBlank() }
            .map {
                val fields = it.split("|")
                PlacaSolar(fields[0], fields[1], fields[2], fields[3])
            }
    }
}

// Função para retornar à tela de listagem
fun returnToList(context: Context) {
    // Finaliza a tela atual e volta para a tela anterior (ListDataActivity)
    (context as? Activity)?.finish()
}
