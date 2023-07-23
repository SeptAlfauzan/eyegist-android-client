package com.septalfauzan.eyegist.ui.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.septalfauzan.eyegist.ui.theme.EyeGistTheme

@Composable
fun RoundedButton(
    text: String,
    onClickAction: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onClickAction,
        modifier = modifier,
        shape = RoundedCornerShape(16.dp),
        colors = ButtonDefaults.buttonColors(
        )
    ) {
        Text(text = text, style = MaterialTheme.typography.caption)
    }
}

@Composable
@Preview(showBackground = true)
private fun Preview() {
    EyeGistTheme {
        RoundedButton(
            text = "Button"
        )
    }
}