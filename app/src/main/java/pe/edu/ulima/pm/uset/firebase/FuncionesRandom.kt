package pe.edu.ulima.pm.uset.firebase

import java.util.*

class FuncionesRandom {
    companion object{
        val charset = ('a'..'z')+('A'..'Z')+('0'..'9')
        fun getRandomCode():String{
            return (1..15).map { charset.random() }.joinToString("")
        }
    }
}