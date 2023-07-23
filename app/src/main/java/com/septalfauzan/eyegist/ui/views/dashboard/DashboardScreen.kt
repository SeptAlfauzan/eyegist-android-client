package com.septalfauzan.eyegist.ui.views.dashboard

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.septalfauzan.eyegist.data.Device
import com.septalfauzan.eyegist.ui.components.ListItemDevice
import com.septalfauzan.eyegist.ui.theme.EyeGistTheme

private val dummyData = listOf<Device>(
    Device(ipAddress = "192.179.0.1"),
    Device(ipAddress = "192.179.0.12"),
    Device(ipAddress = "192.179.0.123"),
)

@Composable
fun DashboardScreen(
    onClick: (String) -> Unit = {},
    modifier: Modifier = Modifier
){
    Column(modifier = modifier
        .fillMaxSize()
        .padding(horizontal = 16.dp)) {
        DashboardScreenContent(onClick)
    }
}

@Composable
private fun DashboardScreenContent(onClick: (id: String) -> Unit = {}) {
    LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)){
        item {
            Text(text = "Perangkat di area jaringan anda", style = MaterialTheme.typography.h5.copy(
                fontWeight = FontWeight(700)
            ), modifier = Modifier.padding(bottom = 45.dp))
        }
        itemsIndexed(dummyData){index, item ->
            ListItemDevice(deviceName = item.name ?: "Perangkat ${index + 1}", deviceIp = item.ipAddress, onClick = { onClick(item.ipAddress) })
        }
    }
}

@Composable
@Preview(showBackground = true, device = Devices.PIXEL_4)
private fun Preview(){
    EyeGistTheme {
        DashboardScreen()
    }
}