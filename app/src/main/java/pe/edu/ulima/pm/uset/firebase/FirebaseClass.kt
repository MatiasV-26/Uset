package pe.edu.ulima.pm.uset.Fragments.Login


import android.content.Context
import android.widget.Toast
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class FirebaseClass {
    companion object{
        val auth =  Firebase.auth
        var auth2 = FirebaseAuth.getInstance()
        fun isVerifiedEmailAndSignInWithEmailAndPassword(email:String,password: String,context: Context):Task<AuthResult>?{
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

        fun createUserWithEmailAndPassword(email:String,password:String,context:Context): Task<AuthResult>? {
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

        fun signInWithCredential(credential:AuthCredential,context:Context): Task<AuthResult>?{
            return auth.signInWithCredential(credential)
        }
        fun updateUI():String? {
            return auth.currentUser?.uid
        }
        fun Correo():String? {
            return auth.currentUser?.email
        }
        fun UpdateAuth(){
            auth2 = FirebaseAuth.getInstance()
        }
    }
}