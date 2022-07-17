package pe.edu.ulima.pm.uset.Fragments.Login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import pe.edu.ulima.pm.uset.databinding.FragmentLogin01Binding

class Login01Fragment : Fragment() {

    private var _binding: FragmentLogin01Binding? = null
    private val binding get() = _binding!!
    // FirebaseAuth
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Initialize Firebase Auth
        auth = Firebase.auth
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentLogin01Binding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tvOlvideMiContrasena.   setOnClickListener  { TvOlvideMiContrasena()    }
        binding.btnIngresar.            setOnClickListener  { BtnIngresar()             }
        binding.btnFacebook.            setOnClickListener  { BtnFacebook()             }
        binding.btnGoogle.              setOnClickListener  { BtnGoogle()               }
        binding.tvCrearUnaCuenta.       setOnClickListener  { TVCrearUnaCuenta()        }
    }

    //TODO: OnViewCreated Functions ****************************************************************
    private fun TvOlvideMiContrasena() {
        Toast.makeText(context, "Implementacion futura ...", Toast.LENGTH_SHORT).show()
    }
    private fun BtnIngresar() {
        val email = binding.tilCorreoEditText.text.toString().trim { it <= ' ' }
        val password = binding.tilIngresarContrasenaEditText.text.toString()
        if (email.isNotEmpty() && password.isNotEmpty()){
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(activity!!){
                if(it.isSuccessful) {
                    Toast.makeText(context, "LAS CREDENCIALES SON CORRECTAS", Toast.LENGTH_SHORT)
                        .show()
                    val user = auth.currentUser
                    updateUI(user)
                }else {
                    Toast.makeText(context, "LAS CREDENCIALES SON MALAS :(", Toast.LENGTH_SHORT)
                        .show()
                    updateUI(null)
                }
            }
        }
    }
    private fun BtnFacebook() {
        Toast.makeText(context, "BtnFacebook", Toast.LENGTH_SHORT).show()
    }
    private fun BtnGoogle() {
        Toast.makeText(context, "BtnGoogle", Toast.LENGTH_SHORT).show()
    }
    private fun TVCrearUnaCuenta() {
        Toast.makeText(context, "TVCrearUnaCuenta", Toast.LENGTH_SHORT).show()
    }

    private fun updateUI(user: FirebaseUser?) {
        Toast.makeText(context, user.toString(), Toast.LENGTH_SHORT).show()
    }
    //TODO: onViewCreated Functions ****************************************************************

    override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        if(currentUser != null){
            reload();
        }
    }

    //TODO: onStart Functions **********************************************************************
    private fun reload() {

    }
    //TODO: onStart Functions **********************************************************************

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}