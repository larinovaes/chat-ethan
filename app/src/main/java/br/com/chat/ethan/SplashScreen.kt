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
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import br.com.chat.ethan.ui.theme.ChatEthanTheme
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    navController: NavController = rememberNavController()
) {
    val customColor = Color(parseColor("#212121"))
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(customColor),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo_ethan),
            contentDescription = "My Icon"
        )
    }
    LaunchedEffect(key1 = Unit) {
        delay(2000)
        navController.navigate("CHAT")
    }
}

@Preview(showBackground = true)
@Composable
fun SplashPreview() {
    ChatEthanTheme {
        SplashScreen()
    }
}
