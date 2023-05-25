package br.com.chat.ethan

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import android.graphics.Color.parseColor
import androidx.compose.runtime.*
import kotlinx.coroutines.delay

@Composable
fun TypingLoading(modifier: Modifier = Modifier) {
    val botColor = Color(parseColor("#1A1A1A"))
    val typingProgressOne = "Digitando."
    val typingProgressTwo = "Digitando.."
    val typingProgressThree = "Digitando..."
    var textToDisplay by remember {
        mutableStateOf(typingProgressOne)
    }
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
            Text(
                modifier = Modifier
                    .clip(
                        RoundedCornerShape(
                            bottomEnd = 16.dp,
                            topEnd = 16.dp,
                            bottomStart = 16.dp
                        )
                    )
                    .padding(horizontal = 16.dp, vertical = 8.dp)
                ,
                text = textToDisplay,
                color = Color.White,
                fontSize = 14.sp,
                textAlign = TextAlign.Start,
            )
        }
        LaunchedEffect(Unit) {
            while (true) {
                textToDisplay = when (textToDisplay) {
                    typingProgressThree -> typingProgressOne
                    typingProgressOne -> typingProgressTwo
                    else -> typingProgressThree
                }
                delay(250L)
            }
        }
    }
}