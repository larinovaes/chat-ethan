package br.com.chat.ethan

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.com.chat.ethan.ui.theme.ChatEthanTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ChatEthanTheme {
               val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = "SPLASH"
                ) {
                    composable("SPLASH") {
                        SplashScreen(navController)
                    }
                    composable("CHAT") {
                        ChatScreen(ChatViewModel())
                    }
                }
            }
        }
    }
}
