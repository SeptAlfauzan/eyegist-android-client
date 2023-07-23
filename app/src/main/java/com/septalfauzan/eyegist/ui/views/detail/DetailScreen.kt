package com.septalfauzan.eyegist.ui.views.detail

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.septalfauzan.eyegist.R
import com.septalfauzan.eyegist.ui.components.RoundedButton

@Composable
fun DetailScreen(ip: String, onClickAction: (String) -> Unit, modifier: Modifier = Modifier) {
    DetailScreenContent(ip, onClickAction, modifier)
}

@Composable
private fun DetailScreenContent(
    ip: String,
    onClickAction: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var isSaved by rememberSaveable {
        mutableStateOf(false)
    }
    val context = LocalContext.current

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(172.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(Color.DarkGray.copy(alpha = 0.1f))
        ) {
            Image(
                painter = painterResource(id = R.drawable.cctv),
                contentDescription = "cctv icon",
                modifier = Modifier
                    .size(88.dp)
                    .align(Alignment.Center)
                    .padding(16.dp)
            )
        }
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
            Text(
                text = "Simpan perangkat", style = MaterialTheme.typography.caption.copy(
                    color = MaterialTheme.colors.onSurface.copy(alpha = 0.3f)
                )
            )
            Spacer(modifier = Modifier.size(8.dp))
            IconButton(onClick = {
                isSaved = !isSaved
                Toast.makeText(
                    context,
                    "Perangkat berhasil di${if (isSaved) "simpan" else "hapus"}",
                    Toast.LENGTH_SHORT
                ).show()
            }, modifier = Modifier.size(24.dp)) {
                Icon(
                    imageVector = if (isSaved) Icons.Default.Bookmark else Icons.Default.BookmarkBorder,
                    contentDescription = "bookmark"
                )
            }
        }
        DetailTextInfo(ip = ip, name = "No name")
        RoundedButton(text = "buka preview", onClickAction = { onClickAction(ip) })
    }
}

@Composable
private fun DetailTextInfo(ip: String, name: String) {
    val labelTextStyle = MaterialTheme.typography.body1.copy(
        color = MaterialTheme.colors.onSurface.copy(alpha = 0.3f)
    )
    Column(verticalArrangement = Arrangement.spacedBy(8.dp), modifier = Modifier.fillMaxWidth()) {
        Text(
            text = "Detail informasi",
            style = labelTextStyle.copy(fontSize = 24.sp)
        )
        Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
            Text(
                text = "Alamat IP",
                style = labelTextStyle
            )
            Text(text = ip, style = MaterialTheme.typography.body1)
        }
        Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
            Text(
                text = "Nama perangkat",
                style = labelTextStyle
            )
            Text(text = name, style = MaterialTheme.typography.body1)
        }
    }
}

@Composable
@Preview(showBackground = true, device = Devices.PIXEL_4)
private fun Detail() {
    DetailScreen(ip = "192.168.0.12", {})
}