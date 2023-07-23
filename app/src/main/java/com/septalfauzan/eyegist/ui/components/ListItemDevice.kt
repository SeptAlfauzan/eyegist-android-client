package com.septalfauzan.eyegist.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.septalfauzan.eyegist.ui.theme.EyeGistTheme

@Composable
fun ListItemDevice(
    deviceName: String,
    deviceIp: String,
    onClick: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    Card(
        shape = RoundedCornerShape(16.dp),
        border = BorderStroke(width = 1.dp, color = MaterialTheme.colors.primary),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier
                .widthIn(292.dp)
                .padding(horizontal = 24.dp, vertical = 16.dp)
        ) {
            OnlineIndicator(isOnline = true)
            DeviceInfoText(deviceName = deviceName, deviceIp = deviceIp)
            Spacer(modifier = Modifier.weight(1f))
            RoundedButton(text = "rincian", onClickAction = onClick)
        }
    }
}

@Composable
fun DeviceInfoText(deviceName: String, deviceIp: String) {
    Column {
        Text(text = deviceName)
        Text(
            text = "IP $deviceIp", style = TextStyle(
                color = Color(0xFF7C7C7C),
                fontWeight = FontWeight(300)
            )
        )
    }
}

@Composable
@Preview(showBackground = true)
private fun Preview() {
    EyeGistTheme {
        ListItemDevice(deviceName = "Perangkat 1", deviceIp = "192.168.0.1")
    }
}