package br.com.chat.ethan

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.chat.ethan.ui.theme.ChatethanTheme
import  android.graphics.Color.parseColor
import androidx.compose.foundation.Image
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource

@Composable
fun ChatScreen() {
    val customColor = Color(parseColor("#212121"))
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(customColor)
    ) {
        Column {
            Toolbar()
            ChatEthanBalloon(modifier = Modifier.padding(vertical = 20.dp))
            ChatBalloon(content = stringResource(R.string.text_test))
            ChatBotBalloon(content = "Sim, qual é a sua pergunta?",
                modifier = Modifier.padding(vertical = 20.dp)
            )
            Container()
        }

    }
}

@Composable
fun ChatBotBalloon(content: String, modifier: Modifier = Modifier) {
    val botColor = Color(parseColor("#1A1A1A"))

    Row(modifier = modifier) {
        Image(
            modifier = Modifier.padding(start = 8.dp),
            painter = painterResource(R.drawable.ic_logo_circle),
            contentDescription = "logo ethan"
        )
        Surface(
            shape = RoundedCornerShape(8.dp),
            color = botColor,
            modifier = Modifier.padding(start = 8.dp, end = 102.dp)
        ) {
            Row(
                modifier = Modifier.padding(8.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = content,
                    color = Color.White
                )
            }
        }
    }
}

@Composable
fun Toolbar() {
    val customColor = Color(parseColor("#212121"))
    TopAppBar(
        title = {
            Box(modifier = Modifier
                .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) { Text(text = stringResource(id = R.string.app_toolbar)) }
        },
        backgroundColor = customColor,
        contentColor = Color.LightGray
    )
}

@Composable
fun ChatBalloon(content: String, modifier: Modifier = Modifier) {
    val userColor = Color(parseColor("#1957BE"))

    Row(modifier = modifier) {
        Surface(
            shape = RoundedCornerShape(8.dp),
            color = userColor,
            modifier = Modifier.padding(start = 102.dp, end = 8.dp)
        ) {
            Row(
                modifier = Modifier.padding(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = content,
                    color = Color.White
                )
            }
        }
    }
}

@Composable
fun ChatEthanBalloon(modifier: Modifier = Modifier) {
    val botColor = Color(parseColor("#1A1A1A"))

    Row(modifier = modifier) {
        Surface(
            modifier = Modifier.padding(horizontal = 24.dp),
            shape = RoundedCornerShape(24.dp),
            color = botColor
        ) {
            Row(modifier = Modifier.padding(horizontal = 40.dp),
                verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = stringResource(R.string.text_balloon_inital),
                    color = Color.White
                )
            }
        }
    }
}

@Composable
fun Container() {
    Column() {
        val color = Color(parseColor("#131313"))

        Surface(
            shape = RoundedCornerShape(1.dp),
            color = color
        ) {
            Image(
                painter = painterResource(R.drawable.ic_send),
                contentDescription = null,
                modifier = Modifier.size(48.dp)
            )
        }

    }
}

@Preview(showBackground = true)
@Composable
fun ChatPreview() {

    ChatethanTheme {
        ChatScreen()
    }
}
