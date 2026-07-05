package com.example.poultryfarm.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.poultryfarm.MainViewModel

@Composable
fun DailyEntryScreen(viewModel: MainViewModel, navController: NavHostController) {
    val halls by viewModel.halls.collectAsState()
    var date by remember { mutableStateOf("") }
    val deaths = remember { mutableStateMapOf<Int, String>() }

    Scaffold(topBar = { TopAppBar(title = { Text("ثبت تلفات روزانه") }) }) { padding ->
        Column(Modifier.fillMaxSize().padding(padding).padding(16.dp)) {
            OutlinedTextField(
                value = date,
                onValueChange = { date = it },
                label = { Text("تاریخ (مثال 1404/11/26)") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(Modifier.height(12.dp))
            if (halls.isEmpty()) {
                Text("اول از بخش «سالن‌ها» حداقل یک سالن اضافه کن.")
            } else {
                LazyColumn(Modifier.weight(1f)) {
                    items(halls) { hall ->
                        OutlinedTextField(
                            value = deaths[hall.id] ?: "",
                            onValueChange = { deaths[hall.id] = it },
                            label = { Text("تلفات ${hall.name} (موجودی اولیه ${hall.initialCount})") },
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                            modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)
                        )
                    }
                }
                Button(
                    onClick = {
                        val map = deaths.mapNotNull { (id, v) -> v.toIntOrNull()?.let { id to it } }.toMap()
                        if (date.isNotBlank() && map.isNotEmpty()) {
                            viewModel.saveDailyEntries(date, map)
                            navController.popBackStack()
                        }
                    },
                    modifier = Modifier.fillMaxWidth()
                ) { Text("ذخیره") }
            }
        }
    }
}
