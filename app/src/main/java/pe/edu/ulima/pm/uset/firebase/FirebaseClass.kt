package pe.edu.ulima.pm.uset.Fragments.Login

import android.app.Activity
import android.content.Context
import android.widget.Toast
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class FirebaseClass {
    companion object{
        val auth =  Firebase.auth
        fun createUserWithEmailAndPassword(email:String,password:String,activity:Activity,context:Context):Boolean{
            var registered = false
            if (email.isNotEmpty() && password.isNotEmpty()) {
                if(password.length<6){
                    Toast.makeText(context, "La contraseña debe tener 6 caracteres a más", Toast.LENGTH_SHORT).show()
                }else{
                    auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(activity) {
                        if (it.isSuccessful){
                            Toast.makeText(context, "Registrado!", Toast.LENGTH_SHORT).show()
                            registered = true
                        }else{
                            Toast.makeText(context, "Ingrese una contraseña", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }else{
                if (email.isEmpty())
                    Toast.makeText(context, "Ingrese un correo", Toast.LENGTH_SHORT).show()
                else
                    Toast.makeText(context, "Ingrese una contraseña", Toast.LENGTH_SHORT).show()
            }
            return registered
        }
        fun signInWithEmailAndPassword(email:String, password:String, activity: Activity, context: Context):Boolean{
            var accessed = false
            if (email.isNotEmpty() && password.isNotEmpty()) {
                FirebaseClass.auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(activity) {
                    if (it.isSuccessful) {
                        accessed = true
                    } else {
                        Toast.makeText(context, "Credenciales incorrectas", Toast.LENGTH_SHORT).show()
                    }
                    updateUI(context)
                }
            }
            else{
                if (email.isEmpty())
                    Toast.makeText(context, "Ingrese un correo", Toast.LENGTH_SHORT).show()
                else
                    Toast.makeText(context, "Ingrese una contraseña", Toast.LENGTH_SHORT).show()
            }
            return accessed
        }
        fun updateUI(context:Context){
            val user = auth.currentUser
            if (user != null) {
                Toast.makeText(context, "FUNCION UPDATE UI USUARIO SIGNED IN> $user", Toast.LENGTH_SHORT).show()
                // User is signed in
            } else {
                Toast.makeText(context, "FUNCION UPDATE UI USUARIO NOT SIGNED IN> " + user.toString(), Toast.LENGTH_SHORT).show()
                // No user is signed in
            }
        }
    }
}