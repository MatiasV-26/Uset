package pe.edu.ulima.pm.uset.Models

import java.time.temporal.Temporal
import java.util.*

data class UserChat (
    var id: String ="",
    var name: String = "",
    var lastMsg: String ="",
    var lastMsgDate : Date = Date(),
    var users: List<String> = emptyList(),
    var temporal: Boolean = false
)