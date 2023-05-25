package br.com.chat.ethan

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.chat.ethan.ui.theme.ChatEthanTheme
import android.graphics.Color.parseColor
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp

@Composable
fun ChatScreen(viewModel: ChatViewModel) {
    val customColor = Color(parseColor("#212121"))
    val scrollState = rememberLazyListState()
    val messages = viewModel.messages
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(customColor)
    ) {
        Toolbar()
        LazyColumn(
            state = scrollState,
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 8.dp)
        ) {
            item { Spacer(modifier = Modifier.padding(top = 16.dp)) }
            if (messages.isEmpty()) item { EmptyMessageState() }
            items(messages) { item ->
                when (item) {
                    is MessageType.UserMessage ->
                        ChatUserBalloon(content = item.content)
                    is MessageType.BotMessage ->
                        ChatBotBalloon(content = item.content)
                    is MessageType.Typing ->
                        TypingLoading()
                }
            }
        }
        Spacer(modifier = Modifier.weight(1f))
        SendMessageSection(viewModel = viewModel)
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
                text = content,
                color = Color.White,
                fontSize = 14.sp,
                textAlign = TextAlign.Start,
            )
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
fun ChatUserBalloon(
    content: String,
    modifier: Modifier = Modifier
) {
    val backgroundColor = Color(parseColor("#1957BE"))
    Row(modifier = modifier.padding(start = 64.dp, end = 8.dp)) {
        Spacer(modifier = Modifier.weight(1f))
        Text(
            modifier = Modifier
                .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp, bottomStart = 16.dp))
                .background(backgroundColor)
                .padding(horizontal = 16.dp, vertical = 8.dp)
            ,
            text = content,
            color = Color.White,
            fontSize = 14.sp,
            textAlign = TextAlign.Start,
        )
    }
}

@Composable
private fun EmptyMessageState(modifier: Modifier = Modifier) {
    val textColor = Color(parseColor("#9E9E9E"))
    val backgroundColor = Color(parseColor("#1A1A1A"))

    Text(
        modifier = modifier
            .clip(RoundedCornerShape(16.dp))
            .background(backgroundColor)
            .padding(horizontal = 40.dp, vertical = 8.dp)
        ,
        text = stringResource(R.string.text_balloon_inital),
        color = textColor,
        fontSize = 11.sp,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Center,
    )
}

@Composable
private fun SendMessageSection(
    modifier: Modifier = Modifier,
    viewModel: ChatViewModel,
) {
    val color = Color(parseColor("#131313"))
    val colorTextField = Color(parseColor("#1A1A1A"))
    val colorText = Color(parseColor("#474747"))
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(color)
            .padding(8.dp)

    ) {
        TextField(
            modifier = Modifier
                .weight(1f),
            value = viewModel.message.value,
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = colorTextField
            ),
            placeholder = {
                Text(
                    text = "Digite uma mensagem",
                    color = colorText
                )
            },
            shape = RoundedCornerShape(16.dp),
            onValueChange = { viewModel.setMessage(it) }
        )
        if (viewModel.onButtonVisible.value) {
            CircularIconButton(
                Modifier.padding(start = 8.dp),
                viewModel = viewModel
            )
        }
    }
}

@Composable
private fun CircularIconButton(
    modifier: Modifier = Modifier,
    viewModel: ChatViewModel,
) {
    val buttonColor = Color(parseColor("#1957BE"))
    Box(
        modifier = modifier
            .size(48.dp)
            .clip(CircleShape)
            .background(buttonColor, shape = CircleShape)
            .clickable { viewModel.sendMessage() },
        contentAlignment = Alignment.Center
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_send),
            contentDescription = null,
            tint = Color.White
        )
    }
}


@Preview(showBackground = true)
@Composable
fun ChatPreview() {
    ChatEthanTheme {
        val viewModel = ChatViewModel()
        ChatScreen(viewModel)
    }
}
