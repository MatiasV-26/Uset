package pe.edu.ulima.pm.uset.Fragments.Login

import android.content.Context
import android.widget.Toast
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class FirebaseClass {
    companion object{
        val auth =  Firebase.auth
        val db = Firebase.firestore

        fun isVerifiedEmailAndSignInWithEmailAndPassword(
            email:String, password: String, context: Context,
            goToChatActivity: ()->Unit, goToCreateProfileActivity: ()->Unit
        ){
            //1.Verification: Not empty fields
            if (email.isNotEmpty() && password.isNotEmpty()) {
                //1.Verification: Email pattern
                if(android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                    //2.Sign In: Check if credentials exist
                    auth.signInWithEmailAndPassword(email, password).addOnCompleteListener{
                        //3.Sign In Successful
                        if (it.isSuccessful){
                            //5.Verification: Check if Email has been verified
                            if(auth.currentUser!!.isEmailVerified) {
                                //7.Check if uid relates to a document
                                db.collection("users").document(updateUI()!!)
                                    .get().addOnSuccessListener {
                                        //8.uid RELATES document
                                        if(it.exists()){
                                            goToChatActivity()
                                        }
                                        //9.uid NOT RELATES document
                                        else{
                                            goToCreateProfileActivity()
                                        }
                                    }
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
                }else{
                    //1.Verification: Email pattern not matches
                    Toast.makeText(context, "El correo ingresado no es valido", Toast.LENGTH_SHORT).show()
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
        ){
            auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
                if(it.isSuccessful){
                    auth.currentUser!!.sendEmailVerification()
                    addFragmentRegistro04()
                }else{
                    Toast.makeText(context, "Error en registro", Toast.LENGTH_SHORT).show()
                }
            }
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
    }
}