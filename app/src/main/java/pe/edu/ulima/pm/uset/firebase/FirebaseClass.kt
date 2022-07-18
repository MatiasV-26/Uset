package pe.edu.ulima.pm.uset.Fragments.Login

import android.content.Context
import android.widget.Toast
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class FirebaseClass {
    companion object{
        val auth =  Firebase.auth

        fun isVerifiedEmailAndSignInWithEmailAndPassword(
            email:String, password: String, context: Context,
            goToChatActivity: ()->Unit, goToCreateProfileActivity: ()->Unit
        ){
            //1.Verification: Not empty fields
            if (email.isNotEmpty() && password.isNotEmpty()) {
                //2.Sign In: Check if credentials exist
                auth.signInWithEmailAndPassword(email, password).addOnCompleteListener{
                    //3.Sign In Successful
                    if (it.isSuccessful){
                        //5.Verification: Check if Email has been verified
                        if(auth.currentUser!!.isEmailVerified) {
                            //TODO *****************************************************************
                            //7.Check if uid relates to a document
                            //8.uid RELATION document
                            // *** goToChatActivity()
                            //9.uid NOT RELATION document
                            // *** goToCreateProfileActivity()
                            //TODO *****************************************************************
                        }else{
                            //6.NOT Verified email
                            Toast.makeText(context, "Verifique su correo para continuar...", Toast.LENGTH_SHORT).show()
                            //Send email verification
                            auth.currentUser!!.sendEmailVerification()
                        }
                    }
                    //4.Sign In Unsuccessful
                    else{
                        Toast.makeText(context, "Credenciales incorrectas", Toast.LENGTH_SHORT).show()
                    }
                }
            }
            //1.Verification: Not empty fields
            else{
                if (email.isEmpty())
                    //Empty Email
                    Toast.makeText(context, "Ingrese un correo", Toast.LENGTH_SHORT).show()
                else{
                    //Empty password
                    Toast.makeText(context, "Ingrese una contraseña", Toast.LENGTH_SHORT).show()
                }
            }
        }

        fun createUserWithEmailAndPassword(
            email:String, password: String, context: Context,
            addFragmentRegistro04: ()->Unit
        ): Task<AuthResult>? {
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

        fun signInWithCredential(
            credential:AuthCredential,
            context:Context
        ): Task<AuthResult>?{
            return auth.signInWithCredential(credential)
        }

        fun updateUI():String? {
            return auth.currentUser?.uid
        }
    }
}