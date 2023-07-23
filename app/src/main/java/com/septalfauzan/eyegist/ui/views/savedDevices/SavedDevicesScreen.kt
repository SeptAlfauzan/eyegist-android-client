package com.septalfauzan.eyegist.ui.views.savedDevices

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.septalfauzan.eyegist.data.Device
import com.septalfauzan.eyegist.ui.components.DeviceCard
import com.septalfauzan.eyegist.ui.theme.EyeGistTheme


private val dummyData = listOf<Device>(
    Device(
        name = "CCTV rumah",
        ipAddress = "192.179.0.1",
        addedDate = "12/12/2023"
    ),
    Device(
        name = "CCTV rumah 2",
        ipAddress = "192.179.0.1",
        addedDate = "12/12/2023"
    ),
    Device(
        name = "CCTV kantor",
        ipAddress = "192.179.0.1",
        addedDate = "12/12/2023"
    ),
)

@Composable
fun SavedDevicesScreen(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize().padding(horizontal = 16.dp)
    ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item(span = { GridItemSpan(2) }) {
                Text(
                    text = "Perangkat yang disimpan", style = MaterialTheme.typography.h5.copy(
                        fontWeight = FontWeight(700)
                    ), modifier = Modifier.padding(bottom = 45.dp)
                )
            }
            items(dummyData) {
                DeviceCard(
                    isOnline = true,
                    ipAddress = it.ipAddress,
                    addedDate = it.addedDate ?: "-"
                )
            }
        }
    }
}

@Composable
@Preview(showBackground = true, device = Devices.PIXEL_4)
private fun Preview() {
    EyeGistTheme {
        SavedDevicesScreen()
    }
}