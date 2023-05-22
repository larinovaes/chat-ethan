package br.com.chat.ethan

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import android.graphics.Color.parseColor
import br.com.chat.ethan.ui.theme.ChatethanTheme

@Composable
fun SplashScreen(name: String) {
    val customColor = Color(parseColor("#212121"))
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(customColor)
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo_ethan),
            contentDescription = "My Icon"
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SplashPreview() {
    ChatethanTheme {
        SplashScreen("Android")
    }
}
