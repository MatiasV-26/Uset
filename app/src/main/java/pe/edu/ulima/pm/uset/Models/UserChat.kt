package pe.edu.ulima.pm.uset.Models

data class UserChat (
    var id: String ="",
    var name: String = "",
    var users: List<String> = emptyList()
)