package com.septalfauzan.eyegist.ui.views.preview

import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView

@Composable
fun PreviewScreen(ip: String, modifier: Modifier = Modifier){
    PreviewScreenContent(ip = ip, modifier = modifier)
}

@Composable
private fun PreviewScreenContent(modifier: Modifier = Modifier, ip: String){
    val mUrl = "https://www.geeksforgeeks.org"
    Column(modifier = modifier.fillMaxSize()) {
        AndroidView(factory = {
            WebView(it).apply {
                layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT,
                )
                webViewClient = WebViewClient()
                settings.javaScriptEnabled = true
                loadUrl(mUrl)
            }
        }, update = {
            it.loadUrl(mUrl)
        })
    }
}

@Composable
@Preview(showBackground = true, device = Devices.PIXEL_4)
private fun Preview(){
    PreviewScreen("192.168.0.1")
}