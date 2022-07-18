package pe.edu.ulima.pm.uset.Fragments.Login

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.lifecycleScope
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pe.edu.ulima.pm.uset.ChatActivity

class FirebaseClass {
    companion object{
        val auth =  Firebase.auth
        fun createUserWithEmailAndPassword(email:String,password:String,context:Context): Task<AuthResult>? {
            var registered = false
            Log.i("valor", registered.toString())
            if (email.isNotEmpty() && password.isNotEmpty()) {
                if(password.length<6){
                    Toast.makeText(context, "La contraseña debe tener 6 caracteres a más", Toast.LENGTH_SHORT).show()
                }else{
                    return auth.createUserWithEmailAndPassword(email, password)
                }
            }else{
                if (email.isEmpty())
                    Toast.makeText(context, "Ingrese un correo", Toast.LENGTH_SHORT).show()
                else
                    Toast.makeText(context, "Ingrese una contraseña", Toast.LENGTH_SHORT).show()
            }
            return null
        }
        fun signInWithEmailAndPassword(email:String, password:String, context: Context): Task<AuthResult>? {
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
        fun signInWithCredential(credential:AuthCredential,context:Context): Task<AuthResult>?{
            return auth.signInWithCredential(credential)
        }
        fun updateUI():String? {
            return auth.currentUser?.uid
        }
    }
}