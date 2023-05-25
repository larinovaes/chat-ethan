package br.com.chat.ethan

sealed class MessageType {
    data class UserMessage(val content: String): MessageType()
    data class BotMessage(val content: String): MessageType()
    object Typing: MessageType()
}
