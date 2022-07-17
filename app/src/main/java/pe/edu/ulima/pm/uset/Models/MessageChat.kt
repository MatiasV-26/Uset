package pe.edu.ulima.pm.uset.Models

import java.util.*

data class MessageChat (
    var content: String ="",
    var fromID: String = "",
    var date: Date = Date()
)