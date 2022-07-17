package pe.edu.ulima.pm.uset.Fragments.Registro

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import pe.edu.ulima.pm.uset.R
import pe.edu.ulima.pm.uset.databinding.FragmentRegistro01Binding
import pe.edu.ulima.pm.uset.databinding.FragmentRegistro02Binding

class Registro02Fragment : Fragment() {


    private var _binding: FragmentRegistro02Binding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
            Log.i("aa", correo.toString())
            if(correo!!.isNotEmpty()){
                Toast.makeText(context, correo, Toast.LENGTH_SHORT).show()
                FirebaseAuth.getInstance().createUserWithEmailAndPassword(correo, pswrd)
                    .addOnCompleteListener {
                        if (it.isSuccessful){
                            Log.i("Enviado", "Enviado")
                        }else{
                            Log.i("Error", "Error")
                        }
                    }

            }
        }
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}