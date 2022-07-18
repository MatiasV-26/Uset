package pe.edu.ulima.pm.uset.Models

import java.time.LocalDateTime
import java.util.*

data class Friend (
    var FriendId : String = "",
    val esFriend : Boolean = true,
    var since :LocalDateTime = LocalDateTime.now()
)
