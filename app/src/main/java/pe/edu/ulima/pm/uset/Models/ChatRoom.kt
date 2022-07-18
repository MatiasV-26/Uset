package pe.edu.ulima.pm.uset.Models

import java.util.*

data class ChatRoom (
        var id: String ="",
        var users: List<String> = emptyList(),
        var temporal: Boolean = false
)