package pe.edu.ulima.pm.uset.Models

import java.util.*

data class Friend (
    var FriendId : String = "",
    val esFriend : Boolean = true,
    var since :Date= Date()
)
