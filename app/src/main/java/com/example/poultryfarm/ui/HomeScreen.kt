package com.example.poultryfarm.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.poultryfarm.HallReport
import com.example.poultryfarm.MainViewModel

@Composable
fun HomeScreen(viewModel: MainViewModel, navController: NavHostController) {
    val dates by viewModel.dates.collectAsState()
    Scaffold(
        topBar = { TopAppBar(title = { Text("گزارش روزانه مرغداری") }) },
        floatingActionButton = {
            FloatingActionButton(onClick = { navController.navigate("daily_entry") }) {
                Icon(Icons.Filled.Add, contentDescription = "ثبت روز جدید")
            }
        }
    ) { padding ->
        if (dates.isEmpty()) {
            Box(Modifier.fillMaxSize().padding(padding), contentAlignment = Alignment.Center) {
                Text("هنوز روزی ثبت نشده. با دکمه + شروع کن.")
            }
        } else {
            LazyColumn(Modifier.fillMaxSize().padding(padding), contentPadding = PaddingValues(12.dp)) {
                items(dates) { date ->
                    DateSummaryCard(viewModel, date)
                    Spacer(Modifier.height(8.dp))
                }
            }
        }
    }
}

@Composable
fun DateSummaryCard(viewModel: MainViewModel, date: String) {
    var report by remember(date) { mutableStateOf<List<HallReport>>(emptyList()) }
    LaunchedEffect(date) { report = viewModel.getReportForDate(date) }

    Card(Modifier.fillMaxWidth()) {
        Column(Modifier.padding(12.dp)) {
            Text(date, style = MaterialTheme.typography.titleMedium)
            Spacer(Modifier.height(4.dp))
            val totalDeathsToday = report.sumOf { it.deathsToday }
            val totalRemaining = report.sumOf { it.remaining }
            Text("تلفات ثبت‌شده در این تاریخ: $totalDeathsToday قطعه")
            Text("موجودی کل بعد از این تاریخ: $totalRemaining قطعه")
        }
    }
}
