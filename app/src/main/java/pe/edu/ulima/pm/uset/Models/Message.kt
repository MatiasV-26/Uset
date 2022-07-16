package pe.edu.ulima.pm.uset.Models

import java.util.*

data class Message (
    var content: String ="",
    var fromID: String = "",
    var date: Date = Date()
)