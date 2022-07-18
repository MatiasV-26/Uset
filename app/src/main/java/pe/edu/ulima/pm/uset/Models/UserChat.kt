package pe.edu.ulima.pm.uset.Models

import java.time.temporal.Temporal

data class UserChat (
    var id: String ="",
    var name: String = "",
    var users: List<String> = emptyList(),
    var temporal: Boolean = false
)