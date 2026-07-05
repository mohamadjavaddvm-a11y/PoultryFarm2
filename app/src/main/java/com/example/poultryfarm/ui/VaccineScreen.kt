package com.example.poultryfarm.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.poultryfarm.MainViewModel

@Composable
fun VaccineScreen(viewModel: MainViewModel) {
    val list by viewModel.vaccines.collectAsState(initial = emptyList())
    Scaffold(topBar = { TopAppBar(title = { Text("برنامه واکسیناسیون") }) }) { padding ->
        LazyColumn(Modifier.fillMaxSize().padding(padding), contentPadding = PaddingValues(12.dp)) {
            items(list) { v ->
                Card(Modifier.fillMaxWidth().padding(vertical = 4.dp)) {
                    Column(Modifier.padding(12.dp)) {
                        Text(v.vaccineType, style = MaterialTheme.typography.titleMedium)
                        Text("تاریخ: ${v.date}  |  سن جوجه: ${v.chickAge} روزگی")
                        Text("روش: ${v.method}")
                        Text("برند: ${v.brand}")
                        Text("دوز: ${v.dose}")
                        Text("سالن‌ها: ${v.halls}")
                    }
                }
            }
        }
    }
}
