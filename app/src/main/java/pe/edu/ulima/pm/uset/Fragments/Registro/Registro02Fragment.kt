package pe.edu.ulima.pm.uset.Fragments.Registro

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import pe.edu.ulima.pm.uset.R
import pe.edu.ulima.pm.uset.databinding.FragmentRegistro01Binding
import pe.edu.ulima.pm.uset.databinding.FragmentRegistro02Binding

class Registro02Fragment : Fragment() {


    private var _binding: FragmentRegistro02Binding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    // [START declare_auth]
    private lateinit var auth: FirebaseAuth
    // [END declare_auth]
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // [START initialize_auth]
        // Initialize Firebase Auth
        auth = Firebase.auth
        // [END initialize_auth]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRegistro02Binding.inflate(inflater, container, false)
        binding.btnEnviar.setOnClickListener {
            var correo = binding.tilCorreoEditText.text.toString()
            var pswrd = "123"
            Log.i("aa", correo)
            if(correo!!.isNotEmpty()){
                Toast.makeText(context, correo, Toast.LENGTH_SHORT).show()
                // [START create_user_with_email]
                auth.createUserWithEmailAndPassword(correo, pswrd)
                    .addOnCompleteListener(requireActivity()) {
                        if (it.isSuccessful){
                            Log.i("Enviado", "Enviado")
                        }else{
                            Log.i("Error", correo)
                            Log.i("Error2", pswrd)
                        }
                    }

            }
        }
        return binding.root
    }
    // [START on_start_check_user]
    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        if(currentUser != null){
            reload();
        }
    }

    private fun reload() {

    }
    // [END on_start_check_user]

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}