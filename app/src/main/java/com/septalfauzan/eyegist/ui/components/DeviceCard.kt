package com.septalfauzan.eyegist.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.outlined.CameraAlt
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.septalfauzan.eyegist.R
import com.septalfauzan.eyegist.ui.theme.EyeGistTheme

@Composable
fun DeviceCard(
    isOnline: Boolean,
    ipAddress: String,
    addedDate: String,
    modifier: Modifier = Modifier
) {
    val cornerRadius = 16.dp
    val greyHexColor = 0xFF7C7C7C

    Card(
        shape = RoundedCornerShape(cornerRadius),
        border = BorderStroke(width = 1.dp, color = MaterialTheme.colors.primary)
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = modifier
                .width(138.dp)
                .padding(12.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                OnlineIndicator(isOnline = isOnline)
                Spacer(modifier = Modifier.weight(1f))
                IconButton(
                    onClick = { /*delete from database*/ },
                    modifier = Modifier.size(24.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "delete icon",
                        tint = Color(0xFFFD3636),
                    )
                }
            }
            Icon(
                imageVector = Icons.Outlined.CameraAlt,
                contentDescription = "camera icon",
                modifier = Modifier
                    .size(64.dp)
                    .align(Alignment.CenterHorizontally),
                tint = Color(greyHexColor)
            )

            TextCardContent(hexColor = greyHexColor, ipAddress = ipAddress, addedDate = addedDate)
            RoundedButton(text = stringResource(R.string.open), modifier = Modifier.fillMaxWidth())
        }
    }
}


@Composable
private fun TextCardContent(hexColor: Long, ipAddress: String, addedDate: String) {
    Column {
        Text(
            text = "Perangkat 1",
            modifier = Modifier.fillMaxWidth(),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            style = TextStyle(
                fontSize = 12.sp
            )
        )
        Text(
            text = ipAddress,
            modifier = Modifier.fillMaxWidth(),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            style = TextStyle(
                color = Color(hexColor),
                fontSize = 8.sp
            )
        )
        Text(
            text = "Ditambahkan",
            modifier = Modifier.fillMaxWidth(),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            style = TextStyle(
                color = Color(hexColor),
                fontSize = 8.sp
            )
        )
        Text(
            text = addedDate,
            modifier = Modifier.fillMaxWidth(),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            style = TextStyle(
                color = Color(hexColor),
                fontSize = 8.sp
            )
        )
    }
}

@Composable
@Preview(showBackground = true)
private fun Preview() {
    EyeGistTheme {
        DeviceCard(isOnline = true, addedDate = "12/12/2023", ipAddress = "IP: 192.168.0.1")
    }
}