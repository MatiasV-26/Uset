package pe.edu.ulima.pm.uset.Fragments.Login

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.lifecycleScope
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pe.edu.ulima.pm.uset.ChatActivity

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
        fun signInWithEmailAndPassword(email:String, password:String, activity: Activity, context: Context): Task<AuthResult>? {
            if (email.isNotEmpty() && password.isNotEmpty()) {
                return auth.signInWithEmailAndPassword(email, password)
            }
            else{
                if (email.isEmpty())
                    Toast.makeText(context, "Ingrese un correo", Toast.LENGTH_SHORT).show()
                else
                    Toast.makeText(context, "Ingrese una contraseña", Toast.LENGTH_SHORT).show()
            }
            return null
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