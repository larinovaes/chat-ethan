package br.com.chat.ethan

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.yml.ychat.YChat
import kotlinx.coroutines.launch

class ChatViewModel : ViewModel() {

    val message = mutableStateOf("")

    val messages = mutableListOf<MessageType>()

    val onButtonVisible = mutableStateOf(message.value.isNotEmpty())

    private val chatCompletions by lazy {
        YChat.create(BuildConfig.API_KEY)
            .chatCompletions()
            .setMaxTokens(200)
            .addMessage("assistant", "Você é o assistante virtual chamado Ethan. Você como assistente tem como objetivo tirar dúvidas relacionadas a programação.")
    }

    fun sendMessage() = viewModelScope.launch {
        val messageToSend = message.value
        messages.add(MessageType.UserMessage(messageToSend))
        onLoading(true)
        runCatching { chatCompletions.execute(messageToSend) }
            .also { onLoading(false) }
            .onSuccess { messages.add(MessageType.BotMessage(it.first().content)) }
    }

    fun setMessage(message: String) {
        this.message.value = message
        buttonVisibleState()
    }

    private fun onLoading(isLoading: Boolean) {
        if (isLoading) {
            onButtonVisible.value = false
            messages.add(MessageType.Typing)
        } else {
            messages.remove(MessageType.Typing)
        }
        buttonVisibleState()
    }

    private fun buttonVisibleState() {
        onButtonVisible.value = message.value.isNotEmpty()
                && !messages.contains(MessageType.Typing)
    }
}
